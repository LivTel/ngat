package ngat.net;

import java.math.*;
import java.util.*;
import java.security.*;

/** This class is used for generating authentication (Crypto) sequences from
 * a Client application. The Crypto class is used to carry the sequences
 * between client and server. It also acts to decrypt the server's response. 
 *
 * $Id$ */
public class CryptoSource {
    
    /** Magic number constant used in encryption.*/
    private final static long MAGIC = 1234L;
    
    private static String X = "0000000010101010101010101010101010101010101010101010101010101010";

    /** Random token from sender.*/
    private BigInteger S_Token;
    
    /** Random token from receiver.*/
    private BigInteger R_Token;
    
    /** User id of the sender.*/
    private String sid;
    
    /** Stores the password of the sender.*/
    private String password;
    
    /** Sender uid sequence.*/
    private BigInteger bid;
    
    /** Key made up from sender password.*/
    private BigInteger key;
    
    /** Pseudo-random number generator.*/
    private Random rgen;
    
    /** Create a CryptoSource using the specified sender id and password.
     * @param sid The sender id.
     * @param password The sender's password.*/
    public CryptoSource(String sid, String password) {
	this.sid = sid;
	this.password = password;
	bid = makeUid(sid);
	key = makeKey(password);
	//System.err.println("CryptoSource: key is: "+key.toString(16).toUpperCase());
	rgen = null;
	try {
	    //System.err.println("CryptoSource: Starting Secure Random generator.");
	    rgen = SecureRandom.getInstance("SHA1PRNG");
	    //System.err.println("CryptoSource:Ok done it: "+rgen);
	} catch (Exception nse) {
	    System.err.println("SHA1PRNG not found");
	}
	S_Token = BigInteger.ZERO;
	R_Token = BigInteger.ZERO;
    }
    
    /** Creates the initial message Crypto sequence. */
    public Crypto sendInitialMessage() {
	//System.err.println("CryptoSource: Starting to create message data.");
	BigInteger data = encrypt(sid, makeS_Token());
	//System.err.println("CryptoSource: send-initial: S Token is: "+S_Token.toString(16).toUpperCase());
	return new Crypto(sid, data);
    }
    
    /** Creates a new Crypto sequence. */
    public Crypto send() {	
	BigInteger data = encrypt(sid, makeS_Token(), R_Token);
	//System.err.println("CryptoSource: send: S Token is: "+S_Token.toString(16).toUpperCase());
	return new Crypto(sid, data);
    }
    
    /** Accept a Crypto sequence, decrypt using own key and check for validity.
     * @exception AuthenticationException if the sender cannot be identified.
     *or the returned crypto is invalid for any reason.*/
    public void receive(Crypto message) throws AuthenticationException {
	// The rid field is from the server and not used at present.
	BigInteger rid = message.getId(key);
	BigInteger msgS_Token = message.getToken2(key);
	BigInteger msgR_Token = message.getToken1(key);
	if (!msgS_Token.equals(S_Token)) 
	    throw new UnauthorizedUserException("Invalid crypto sequence");	
	R_Token = msgR_Token; // save the new R_token from other side.
	//System.err.println("CryptoSource: receive: R Token is: "+R_Token.toString(16).toUpperCase());
    }
    
    /** Generates a new random 64 bit Sender token and saves it. */
    private BigInteger makeS_Token() {
	// create a random 64bit S_token and save it.
	//System.err.println("CryptoSource:makeS_Token:Starting to create S_Token using: "+rgen);
	S_Token = new BigInteger(64, rgen);
	S_Token = new BigInteger(X);
	//System.err.println("CryptoSource:makeS_Token:OK done it: "+S_Token);
	return S_Token;
    }
    
    /** Encrypt the supplied id and Sender token using own key. */
    private BigInteger encrypt(String id, BigInteger Stoken) {
	// Merge and encrypt id and Stoken using key.	
	//System.err.println("CryptoSource:encrypt:Starting encrypt");
	BigInteger buid = makeUid(id);
	//System.err.println("CryptoSource:encrypt:Done built UID: "+buid);
	return (((buid.shiftLeft(128)).or(Stoken.shiftLeft(64))).multiply(key));
    }
    
    /** Encrypt the supplied id and Sender and Receiver tokens using own key. */
    private BigInteger encrypt(String id, BigInteger Stoken, BigInteger Rtoken) {
	// Merge and encrypt id, Stoken, Rtoken using key.
	//System.err.println("CryptoSource:encrypt:Starting encrypt");
	BigInteger buid = makeUid(id);
	//System.err.println("CryptoSource:encrypt:Done built UID: "+buid);
	return (((buid.shiftLeft(128)).or(Stoken.shiftLeft(64)).or(Rtoken)).multiply(key));
    }
    
    /** Makes up a crypto-key from the user's id entered (i.e. their password string). */
    private BigInteger makeKey(String passwd) {
	
	char bytes[] = passwd.toCharArray();
	int nb = bytes.length;
	long seed = 1L;
	BigInteger key;
	// Multiply by each byte value.
	for (int i = 0; i < nb; i++) {
	    seed = seed * (int)bytes[i];
	}
	
	// Add each byte value.
	for (int i = 0; i < nb; i++) {
	    seed = seed + (int)bytes[i];
	}
	key = new BigInteger(128, 100, new Random(seed));
	//key = new BigInteger(X);
	// 128 bit key, prime with certainty > 1 - 7.8x10E-31
	//return new BigInteger(128, 100, new Random(seed));
	return key;
    }
    
    /** Turns the user's uid into an un-encrypted BI. */
    private BigInteger makeUid(String id) {
	
	char bytes[] = id.toCharArray();
	int nb = bytes.length;
	long generator = 0L;
	//System.err.println("CryptoSource:Starting MakeUID");
	// Add each byte value to get a generator.
	for (int i = 0; i < nb; i++) {
	    generator += (int)bytes[i];
	}
	//System.err.println("CryptoSource:makeUid:Built UID generator: "+generator);
	// Form a BI from the generator (there is nothing special about the multiplier).
	//System.err.println("CryptoSource:makeUid:Starting to creat new BI code.");
	return new BigInteger(String.valueOf(generator*MAGIC));	
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2001/07/11 10:50:28  snf
/** backup.
/**
/** Revision 1.3  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.2  2001/02/19 12:48:44  snf
/** Modifed receive to throw AuthenticationException.
/** */
