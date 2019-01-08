package ngat.ngtcs.subsystem;

import java.io.*;
import java.net.*;
import java.util.*;

import ngat.util.logging.*;
import ngat.net.cil.*;
import ngat.ngtcs.*;
import ngat.ngtcs.command.*;



/**
 * A java (Datagram socket) implementation of the CIL interface, converting
 * to TTL's C-readable format.
 * 
 * @author $Author$ 
 * @version $Revision$
 *
 */
public class TTL_CIL implements Runnable, CIL
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   * The 28 bytes taken up by the 7 Int32_t headers.
   */
  public static int TTL_CIL_HEADER_LENGTH = 28;

  /**
   *
   */
  protected Logger logger;

  /**
   *
   */
  protected String logName;

  /**
   *
   */
  protected TTL_CIL_Node node;

  /**
   * The socket used for all CIL communication.
   */
  protected DatagramSocket socket;

  /**
   * Sequence number of the message to be sent.
   */
  protected int seqNo = 0;

  /**
   * The initialisation flag.
   */
  protected boolean initialised = false;

  /**
   *
   */
  protected List msgList;

  /**
   *
   */
  protected boolean shutdown = false;

  /**
   * Lock Object for receiving messages.
   */
  protected Object receiveMessageLock = new Object();

  /**
   * Constructor.
   */
  public TTL_CIL(  TTL_CIL_Node n )
  {
    node = n;
  }


  /**
   * Initialise the CIL communication socket.
   *
   * In this case a Datagram socket is instantiated and connected, a
   * <code></code>
   */
  public void initialise( int localPort,
			  InetAddress remoteHost, int remotePort )
    throws SocketException, IllegalArgumentException, SecurityException
  {
    if( initialised )
    {
      logger.log( 1, logName,
		  "TTL_CIL ["+this+"] already initialised" );
      return;
    }

    socket = new DatagramSocket( localPort );
    socket.connect( remoteHost, remotePort );

    msgList = new Vector();

    initialised = true;

    /*
      System.out.println( "TTL_CIL ["+this+"] initialised" );
    */
  }


  /**
   *
   */
  public TTL_CIL_Node getNode()
  {
    return( node );
  }


  /**
   *
   */
  public void sendMessage( CIL_Message m )
    throws IOException, SecurityException
  {
    sendMessage( m, true );
  }


  /**
   *
   */
  public void sendMessage( CIL_Message msg, boolean b )
    throws IOException, SecurityException
  {
    if( b == true )
      msg.setSequenceNumber( ++seqNo );

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream( baos );

    baos.flush();
    dos.flush();

    logger.log( 5, logName,
		"    txId = "+msg.getSourceNode()+"\n"+
		"    rxId = "+msg.getDestinationNode()+"\n"+
		"  msgInt = "+msg.getMessageClass()+"\n"+
		" service = "+msg.getServiceClass()+"\n"+
		"   seqNo = "+msg.getSequenceNumber()+"\n"+
		"    secs = "+msg.getSeconds()+"\n"+
		"nanosecs = "+msg.getNanoseconds() );


    /*
     * Write the CIL Routing Headers.
     */
    dos.writeInt( msg.getSourceNode() );
    dos.writeInt( msg.getDestinationNode() );
    dos.writeInt( msg.getMessageClass() );
    dos.writeInt( msg.getServiceClass() );
    dos.writeInt( msg.getSequenceNumber() );
    dos.writeInt( msg.getSeconds() );
    dos.writeInt( msg.getNanoseconds() );
    //dos.writeInt( msg.getMessageLength() );

    /*
     * Write the data itself, if there is any.
     */
    int len = msg.getMessageLength(); 
    if( len > 0 )
    {
      dos.write( msg.getMessageBody(), 0, len );
    }
    baos.close();

    /*
     * Pack the data/byte stream into a DatagramPacket and send.
     */
    byte buffer[] = new byte[ baos.size() ];
    buffer = baos.toByteArray();
 
    socket.send( new DatagramPacket( buffer, buffer.length ) );
  }


  /**
   * Send a message and wait for the response.  The response is assumed to be
   * the correct message if the RX node for the out going message is the same
   * as the TX node for the reply, and the reply has the same sequence number
   * as the message.
   * @param msg the message to send
   * @return the reply to <code><b>msg</b></code>
   */
  public CIL_Message sendMessageGetReply( CIL_Message msg )
    throws IOException, SecurityException
  {
    sendMessage( msg, true );
    return( (CIL_Message)getReply( msg ) );
  }
 

  /**
   *
   */
  public CIL_Message getReply( CIL_Message msg )
  {
    long msgSeqNo = msg.getSequenceNumber();
    int msgReceiver = msg.getDestinationNode();
    int oldCount = 0;

    // loop until reply is received.
    while( true )
    {
      if( ( msgList.size() == 0 ) ||
	  ( msgList.size() == oldCount ) )
      {
	try
	{
	  wait();
	}
	catch( InterruptedException ie )
	{
	  logger.log
	    ( 1, logName, "while WAITing for message"+
	      " an error occurred : "+ie.toString() );
	}
      }

      oldCount = msgList.size();
      for( int i = 0; i < oldCount; i++ )
      {
	CIL_Message m = null;

	DatagramPacket dp = (DatagramPacket)
	  ( msgList.get( i ) );
	try
	{
	  m = bytesToCIL_Message( dp );
	}
	catch( IOException ioe )
	{
	  logger.log( 1, logName, "an error occurred "+
		      "while parsing message ("+i+
		      ") in list : "+ioe.toString() );
	}

	if( ( m != null ) &&
	    ( ( m.getSequenceNumber() == msgSeqNo ) &&
	      ( m.getSourceNode() == msgReceiver ) ) )
	{
	  msgList.remove( m );
	  return m;
	}
      }
    }
  }


  /**
   * Take the next received message from the socket in the queue.
   * @return the next message
   */
  public CIL_Message receiveMessage()
    throws IOException
  {
    DatagramPacket packet;

    try
    {
      if( msgList.size() == 0 ) wait();

      packet = (DatagramPacket)( msgList.get( 0 ) );
      msgList.remove( 0 );
    }
    catch( Exception e )
    {
      logger.log( 1, logName, "an error occurred while receiving "+
		  "a message : "+e.toString() );
      packet = null;
    }

    CIL_Message msg = bytesToCIL_Message( packet );
    return( (CIL_Message)msg );
  }


  /**
   * Return the next sequence number.  This number set back to 0 (zero) when
   * it equals ??diddly??.
   * @return seqNo
   * @see #seqNo
   */
  public int getSequenceNumber()
  {
    return ++seqNo;
  }


  /**
   * Start a thread to listen for DatagramPackets on the socket initalised.
   *
   * This thread receives <code>DatagramPackets</code> and places them in
   * a <code>BlockingQueue</code>
   */
  public void run()
  {
    byte[] buffer = new byte[ 65536 ];
    DatagramPacket packet = new DatagramPacket( buffer, buffer.length );

    while( shutdown == false )
    {
      try
      {
	socket.receive( packet );

	synchronized( receiveMessageLock )
	{
	  msgList.add( packet );
	}
	notifyAll();
      }
      catch( Exception e )
      {
	if( shutdown != true )
	{
	  logger.log( 1, logName, e.toString() );
	}
	else
	{
	  return;
	}
      }
    }
  }



  /**
   * Convert a <code>DatagramPacket</code> into a
   * <code>CIL_Message</code>
   * @param packet the <code>DatagramPacket</code> to be converted
   * @return a converted <code>CIL_Message</code>
   * @see ngat.net.cil.CIL_Message
   */
  public CIL_Message bytesToCIL_Message( DatagramPacket packet )
    throws IOException
  {
    if( packet == null )
      return null;

    ByteArrayInputStream bais =
      new ByteArrayInputStream( packet.getData() );
    DataInputStream dis = new DataInputStream( bais );

    /*
     * Read the Headers.
     */
    int txId     = dis.readInt();
    int rxId     = dis.readInt();
    int msgInt   = dis.readInt();
    int sysSrv   = dis.readInt();
    int sysInt   = dis.readInt();
    int seqNo    = dis.readInt();
    int secs     = dis.readInt();
    int nanosecs = dis.readInt();
    //int msgLen   = dis.readInt();

    /*
     * Separate the TTL_Package int and the ServiceClass int
     */
    //int srvInt = sysSrv & 0x00FF;
    //int sysInt = sysSrv >> 16;

    System.err.println( "    txId = "+txId+"\n"+
			"    rxId = "+rxId+"\n"+
			"  msgInt = "+msgInt+"\n"+
			"  sysSrv = "+
			( Integer.toHexString( sysSrv ) )+"\n"+
			//"  sysInt = "+
			//( Integer.toHexString( sysInt ) )+"\n"+
			//"  srvInt = "+
			//( Integer.toHexString( srvInt ) )+"\n"+
			"   seqNo = "+seqNo+"\n"+
			"    secs = "+secs+"\n"+
			"nanosecs = "+nanosecs+"\n" );

    /*
     * Read the message body into a byte array.
     */
    byte[] data  = new byte[ packet.getLength() - TTL_CIL_HEADER_LENGTH ];

    if( data.length > 0 )
    {
      dis.read( data );
    }

    /*
     * Pack the data into a CIL_Message.
     */
    CIL_Message message = new CIL_Message
      (  txId, rxId, msgInt, sysSrv, seqNo, secs, nanosecs, data );

    return( message );
  }


  /**
   *
   */
  public void shutdown()
  {
    socket.close();
  }
}
/*
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_CIL.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_CIL.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:00:50  je
 *     Initial revision
 *
 */
