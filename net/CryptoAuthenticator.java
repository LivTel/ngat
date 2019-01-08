package ngat.net;

import java.util.*;
import java.math.*;

/** Implements an authenticator for encrypted authentication codes.
 *
 * $Id$ */
public class CryptoAuthenticator {
    
    /** Magic number for encryption.*/
    private final static long MAGIC = 1234L;
    
    /** Table of userid/password pairs in java.util.Properties format.*/
    private Properties passwordTable;
    
    /** Receiver uid.*/
    private String rid; 

    /** Sender's uid.*/
    private String suid;
   
    /** Sender's id chopped up and reformed as a key code.*/
    private BigInteger cid; 

    /** Encryption random token.*/
    private BigInteger R_Token;
    
    /** Encryption random token.*/
    private BigInteger S_Token;
    
    /** Key for the sender.*/
    private BigInteger key;

    /** Construct an Authenticator using the client's userid as reference into the password table.*/
    public CryptoAuthenticator(Properties passwordTable, String rid) {
	this.rid = rid;// my id.
	this.passwordTable = passwordTable;
	key = null;
	cid = null;
	S_Token = BigInteger.ZERO;
	R_Token = BigInteger.ZERO;
    }
     
    /** Accept a Crypto sequence, decrypt using own key and check for validity.
     * @exception AuthenticationException if the sender cannot be identified.
     *or the returned crypto is invalid for any reason.*/
    public void receiveInitialMessage(Crypto message) throws AuthenticationException {   	
	String uid = message.getUserId();
	//System.err.println("CryptoAuthenticator: recvd init msg with uid: "+uid);
	suid = uid;
	cid = makeUid(uid); // Chop the uid string
	//System.err.println("CryptoAuthenticator: cid: "+cid.toString(16).toUpperCase());
	// Get the client's key from the pwd table.
	key = getKeyforUser(uid);
	//System.err.println("CryptoAuthenticator: key: "+key.toString(16).toUpperCase());
	if (key == null) 
	    throw new InvalidUserException("No such client: "+uid);
	
	BigInteger msgcid     = message.getId(key);
	BigInteger msgS_Token = message.getToken2(key);
	BigInteger msgR_Token = message.getToken1(key);
	//System.err.println("CryptoAuthenticator:msgcid: "+msgcid.toString(16).toUpperCase()+
	//" msgSToken: "+msgS_Token.toString(16).toUpperCase()+
	//" msgRToken: "+msgR_Token.toString(16).toUpperCase());
	// Check the client's id first time, no R_Token.
	if (!msgcid.equals(cid)) 
	    throw new UnauthorizedUserException("Client not authenticated - invalid key.");	
	
	S_Token = msgS_Token; // save the sender's token.
    }
    
    /** Accept a Crypto sequence, decrypt using own key and check for validity.
     * @exception AuthenticationException if the sender cannot be identified.
     *or the returned crypto is invalid for any reason.*/
    public void receive(Crypto message) throws AuthenticationException {   
	BigInteger msgcid = message.getId(key);
	BigInteger msgS_Token = message.getToken2(key);
	BigInteger msgR_Token = message.getToken1(key);
	// From now on, check the Rtoken we sent last time.
	if (!msgR_Token.equals(R_Token)) 
	    throw new UnauthorizedUserException("Client not authenticated - invalid key.");	
	S_Token = msgS_Token; // save the sender's token.
    }
    
    /** Create the next Crypto sequence for sending based on previously received
     * stuff.*/
    public Crypto send() {
	BigInteger data = encrypt(rid, S_Token, makeR_Token());
	return new Crypto(rid, data);
    }
    
    /** Encryption - makes a random token.*/
    private BigInteger makeR_Token() {
	// Create a random 64bit R_token and save it.
	R_Token = new BigInteger(64, new Random(System.currentTimeMillis()));
	return R_Token;
    }
    
    /** Encrypts the uid with the S and R tokens.*/
    private BigInteger encrypt(String id, BigInteger Stoken, BigInteger Rtoken) {
	// Merge and encrypt id, Stoken, Rtoken using client's key.
	return (((makeUid(id).shiftLeft(128)).or(Stoken.shiftLeft(64)).or(Rtoken)).multiply(key));
    }
    
    /** Turns the user's uid into an un-encrypted BigInteger. */
    private BigInteger makeUid(String id) {
    
	char bytes[] = id.toCharArray();
	int nb = bytes.length;
	long generator = 0L;
	
	// Add each byte value to get a generator.
	for (int i = 0; i < nb; i++) {
	    generator += (int)bytes[i];
	}
	
	// Form a BI from the generator (there is nothing special about the multiplier).
	return new BigInteger(String.valueOf(generator*MAGIC));
	
    }

    /** Returns the user's key from the password table.
     * @return The user's private key or null if the user is not in the table.*/
    private final BigInteger getKeyforUser(String uid) {
	//System.err.println("CryptoAuthenticator: getKeyforUser: ["+uid+"]");
	String password = passwordTable.getProperty(uid);
	//System.err.println("CryptoAuthenticator: returned password: ["+password+"]");
	if (password == null) return null;
	char bytes[] = password.toCharArray();
	int nb = bytes.length;
	long seed = 1L;
	
	// Multiply by each byte value.
	for (int i = 0; i < nb; i++) {
	    seed = seed * (int)bytes[i];
	}
	
	// Add each byte value.
	for (int i = 0; i < nb; i++) {
	    seed = seed + (int)bytes[i];
	}
	
	// 128 bit key, prime with certainty > 1 - 7.8x10E-31
	BigInteger key = new BigInteger(128, 100, new Random(seed));
	//log("SC:getUserKey:",1,"key is: "+key.toString(16).toUpperCase());
	//return new BigInteger(128, 100, new Random(seed));
        return key;
    }

    /** Returns the client's password.*/
    public String getPasswordEntry(String uid) {
	return "Password for user: ["+uid+"] is ["+passwordTable.get(uid)+"]";
    }
    
    /** Returns the client's userid.*/
    public String getUid() { return suid; }

    /** Returns a reference to the passwordtable.*/
    public Properties getPasswordTable() { return passwordTable; }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2001/05/14 15:35:43  snf
/** Added cancel method implementation.
/**
/** Revision 1.3  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.2  2001/02/19 12:49:13  snf
/** Modified receive and receiveInit to throw AuthenticationException.
/**
/** Revision 1.1  2001/02/19 12:10:43  snf
/** Initial revision
/** */
