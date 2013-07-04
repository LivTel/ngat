package ngat.ngtcs.subsystem.ags;

import java.io.*;
import java.net.*;
import java.util.*;

import ngat.ngtcs.*;

import ngat.ngtcs.subsystem.*;

/**
 * This class implements the TTL AGS interface used with the AGG.
 * <p>
 * The interface is described in TTL document <b>97-001-AGS-TEL_ICD</b>.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class AGG_GuidePacketReader
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: AGG_GuidePacketReader.java,v 1.2 2013-07-04 12:55:03 cjm Exp $" );


  /**
   * Length of the TTL AGG guide packet in bytes.
   */
  protected final static int GUIDE_PACKET_LENGTH = 34;

  /**
   * Length of guide packet used for checksum.
   */
  protected final static int CHECKSUM_END = 29;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  protected Socket socket = null;

  /**
   *
   */
  protected BufferedReader br = null;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  public AGG_GuidePacketReader()
  {

  }


  /**
   * Initialise 
   */
  public void initialise( InetAddress ia, int port )
    throws InitialisationException
  {
    try
    {
      socket = new Socket( ia, port );
      socket.setSoTimeout( 0 );
      br = new BufferedReader
	( new InputStreamReader( socket.getInputStream() ) );
    }
    catch( Exception e )
    {
      throw new InitialisationException
	( "AGG Packet-reader failed to initialise : "+e );
    }
  }


  /**
   *
   */
  public void initialise( String filename )
    throws InitialisationException
  {
    try
    {
      br = new BufferedReader
	( new FileReader( filename ) );
    }
    catch( Exception e )
    {
      throw new InitialisationException
	( "AGG Packet-reader failed to initialise : "+e );
    }
  }


  /**
   * Read and return the next guide packet from the socket.
   * <p>
   * The bytes are read in and parsed into their respective values of
   * xPixel, yPixel, time, status and checksum.  The checksum comparison is
   * performed after all parsing.
   * @return an AGG_GuidePacket representing the bytes read in
   */
  public AGG_GuidePacket getGuidePacket()
    throws TTL_SystemException
  {
    String packetStr = null;
    double xPixel;
    double yPixel;
    double time;
    String timeStr;
    String stat;
    int chk;

    try
    {
      packetStr = br.readLine();
      StringTokenizer st = new StringTokenizer
	( packetStr, " " );

      xPixel = Double.parseDouble( st.nextToken() );
      yPixel = Double.parseDouble( st.nextToken() );
      timeStr = st.nextToken();
      time = Double.parseDouble( timeStr );
      stat = st.nextToken();
      chk = Integer.parseInt( st.nextToken() );
    }
    catch( NumberFormatException nfe )
    {
      throw new TTL_SystemException
	( "failed parsing numbers from bytes ["+packetStr+"] : "+
	  nfe );
    }
    catch( IOException ioe )
    {
      throw new TTL_SystemException
	( "failed creating/reading DataInputStream : "+ioe );
    }

    int sum = 0;
    for( int i = 0; i < CHECKSUM_END; i++ )
    {
      sum += (int)( packetStr.charAt( i ) );
    }

    if( chk != sum )
      throw new TTL_SystemException
	( "guide packet checksum ["+chk+"] does NOT equal actual"+
	  "checksum ["+sum+"]" );

    AGG_GuidePacket packet;

    switch( stat.charAt( 0 ) )
    {
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
	try
	{
	  packet = new AGG_GuidePacket( Integer.parseInt( stat ),
					xPixel, yPixel );
	}
	catch( NumberFormatException nfe )
	{
	  throw new TTL_SystemException
	    ( "failed parsing int ["+stat.charAt( 0 )+
	      "] : "+nfe );
	}
	break;

      case 'F':
	packet = new AGG_GuidePacket
	  ( AGG_GuidePacketError.FAILED_TO_FIND );
	break;

      case 'W':
	packet = new AGG_GuidePacket
	  ( AGG_GuidePacketError.TOO_NEAR_EDGE );
	break;

      default:
	throw new TTL_SystemException
	  ( "AGG returned non-valid status code ["+stat.charAt( 0 )+"]" );
    }

    int timeout;
    if( ( timeStr.equals( "-0000.00" ) )||
	( timeStr.equals( "00000.00" ) ) )
    {
      timeout = 0;
    }
    else if( time < 0.0 )
    {
      timeout = (int)( Math.abs( time ) );
    }
    else
    {
      timeout = (int)( 2.0 * time );
    }

    if( socket != null )
    {
      try
      {
	socket.setSoTimeout( timeout );
      }
      catch( SocketException se )
      {
	// check for timeout; if not error...
	//packet =  new AGG_GuidePacket
	//(  );
      }
    }
    else
    {
      throw new TTL_SystemException( "Socket has mysteriously disappeared!" );
    }

    return packet;
  }
}
/*
 *    $Date: 2013-07-04 12:55:03 $
 * $RCSfile: AGG_GuidePacketReader.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGG_GuidePacketReader.java,v $
 *      $Id: AGG_GuidePacketReader.java,v 1.2 2013-07-04 12:55:03 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:38  je
 *     Initial revision
 *
 */
