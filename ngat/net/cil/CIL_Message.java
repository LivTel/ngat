package ngat.net.cil;

/** Container for messages (responses) returned by the TTL
 * CIL library functions via UDP from the TCS.
 * <br><br>
 * $Id: CIL_Message.java,v 1.1 2006-05-16 18:14:00 cjm Exp $
 **/
public class CIL_Message {

    // Source /destination ids.

    /** The RCS ID - as specified in CIL Header Specification Doc.*/
    public static int RCS_ID = 18;
    //public static final int RCS_ID = 74;
    
    /** The TCS ID - as specified in CIL Header Specification Doc.*/
    public static int TCS_ID = 17;
    //public static final int TCS_ID = 17;

    // Message classes.
    /** The Command message class - as specified in CIL Header Specification Doc.*/
    public static final int COMMAND_CLASS  = 1;
    
    /** Indicates Response message class - as specified in CIL Header Specification Doc.*/
    public static final int RESPONSE_CLASS = 2;
    
    /** Indicates Acknowledge message class - as specified in CIL Header Specification Doc.*/
    public static final int ACK_CLASS      = 3;
    
    /** Indicates Actioned message class - as specified in CIL Header Specification Doc.*/
    public static final int ACTION_CLASS = 4;
    
    /** Indicates Completed message class - as specified in CIL Header Specification Doc.*/
    public static final int DONE_CLASS     = 5;
    
    /** Indicates Error message class - as specified in CIL Header Specification Doc.*/
    public static final int ERROR_CLASS    = 6;
    
   
    // ## This stuff should be in a config file.##
    /** The TCS command port - ## TBD to config ##.*/
    public static final int TCS_PORT = 5678;

    /** The TCS host address - ## TBD to config ##.*/
    public static final String TCS_HOST = "ltccd1.livjm.ac.uk";

    /** The Service type -  as specified in CIL Header Specification Doc. ## A DUMMY VALUE ##*/
    public static final int SERVICE_TYPE = 589824;

    
    /** CIL message sequence number.*/
    protected int sequenceNo;

    /** CIL Tx ID from eCilMsg_t.*/
    protected int txId;

    /** CIL Rx ID from eCilMsg_t.*/
    protected int rxId;

    /** CIL Message Class - defined in Cil.h */
    protected int messageClass;

    /** CIL Service class - defined in Cil.h */
    protected int serviceClass;

    /** Data returned from TCS.*/
    protected String data;

    /** The bytes.*/
    protected byte[] bytes;

    /** Create a CIL_Message using the supplied parameters.
     * @param sequenceNo CIL message sequence number.
     * @param txId CIL TxID from eCilMsg_t.
     * @param rxId CIL RxID from eCilMsg_t.
     * @param mClass CIL Message class.
     * @param sClass CIL Service class.
     * @param data data returned from TCS.*/
    CIL_Message(int sequenceNo, int txId, int rxId, int mClass, int sClass, String data) {
	this.sequenceNo   = sequenceNo;
	this.txId         = txId;
	this.rxId         = rxId;
	this.messageClass = mClass;
	this.serviceClass = sClass;
	this.data         = data;
    }
    
    /** Sets the CIL message sequence number.*/
    public void setSequenceNo(int sequenceNo) { this.sequenceNo = sequenceNo; }

    /** Returns the CIL message sequence number.*/
    public int  getSequenceNo() { return sequenceNo; }


    /** Sets the CIL Tx ID from eCilMsg_t.*/
    public void setTxId(int txId) { this.txId = txId; }
    
    /** Returns the CIL Tx ID from eCilMsg_t.*/
    public int  getTxId() { return txId; }


    /** Sets the CIL Rx ID from eCilMsg_t.*/
    public void setRxId(int rxId) { this.rxId = rxId; }
    
    /** Returns the CIL Rx ID from eCilMsg_t.*/
    public int  getRxId() { return rxId; }
    

    /** Sets the CIL Message class.*/
    public void setMessageClass(int mClass) { this.messageClass = mClass; }

    /** Returns the CIL Message class.*/
    public int  getMessageClass() { return messageClass; }


    /** Sets the CIL Service class.*/
    public void setServiceClass(int sClass) { this.serviceClass = sClass; }

    /** Returns the CIL Service class.*/
    public int  getServiceClass() { return serviceClass; }


    /** Sets the data returned from TCS.*/
    public void   setData(String data) { this.data = data; }
    
    /** Returns the data returned from TCS.*/
    public String getData() { return data; }

    public void setBytes(byte[] bytes) { this.bytes = bytes; }

    public byte[] getBytes() { return bytes; }

}

/** $Log: not supported by cvs2svn $ */
