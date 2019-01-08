package ngat.net;

import java.io.*;
import java.math.*;

/**
 * Encapsulates client authentication information. Most of this class is now
 * deprecated with use of SSL certificates. WIll be used only to transfer
 * a basic userid.
 */
public class Crypto implements Serializable{

    /** Mask constant = 2e64 -1.*/  
    private final static BigInteger MASK64 = new BigInteger("FFFFFFFFFFFFFFFF",16);
    
    /** mask constant = 2e32 -1.*/
  private final static BigInteger MASK32 = new BigInteger("FFFFFFFF",16);

    /** The crypto sequence.*/
    private BigInteger sequence;

    /** The userid of the client/server.*/
    private String userid;
    
    /** Create a default Crypto using uid 'test' and code 0xFFFF.*/
    public Crypto() {
	this.userid = "test";
	this.sequence = new BigInteger("FFFF", 16);
    }

    /** Create a Crypto using specified uid.
     * @param uid The user id of the sender.
     */
    public Crypto(String userid) {
	this.userid = userid;
    }

    /** Create a Crypto using specified uid and sequence.
     * @param uid The user id of the sender.
     * @param seq The Crypto sequence.*/
    public Crypto(String userid, BigInteger seq) {
	this.userid = userid;
	this.sequence = seq;
    }
    
    /** Returns the user's id as a String. Used by Authenticator to access the passwordtable.*/
    public String getUserId() { return userid;}
    
    /** Returns the chopped user id decrypted from the sequence.*/
    public BigInteger getId(BigInteger key) {
	return (sequence.divide(key).shiftRight(128).and(MASK32));
    }

    /** Returns the sequence.*/
    public BigInteger getSequence() { return sequence; }

    /** Returns the sequence as a hex String.*/
    public String getSequenceHex() { return sequence.toString(16).toUpperCase(); }
    
    /** Returns the Sender Token decrypted from the sequence. */
    public BigInteger getToken2(BigInteger key) {
	return (sequence.divide(key).shiftRight(64).and(MASK64));
    }
    
    /** returns the Receiver Token decrypted from the sequence. */
    public BigInteger getToken1(BigInteger key) {
	return (sequence.divide(key).and(MASK64));
    }   
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.2  2001/02/19 12:49:43  snf
/** Changed package.
/** */
