package ngat.ngtcs.subsystem;

import java.lang.reflect.*;
import java.io.*;

import ngat.ngtcs.common.*;

/**
 * Handler class for the ObjectValue data structure used in the TTL_CIL
 * communication protocol.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_CIL_ObjectValue
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
    new String( "$Id$" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The data type representing the TTL OID (Object ID).
   */
  private TTL_DataType dataType = null;

  /**
   * The integer of the data type representing the TTL OID (Object ID).
   */
  private int dataTypeInt;

  /**
   * Integer representation of the TTL System error.
   */
  private int systemError = 0;

  /**
   * Description of the data length.
   */
  private boolean eightBytes = false;

  /**
   * The first 32 bits of data.
   */
  private int lowBytes = 0;

  /**
   * The last 32 bits of data.
   */
  private int highBytes = 0;

  /**
   * the timestamp of the data.
   */
  private Timestamp timestamp =
    new Timestamp( 0, 0, CalendarType.GREGORIAN, TimescalType.UTC );

  /**
   * The units of the data.
   */
  private TTL_DataUnit units = TTL_DataUnit.E_SDB_NO_UNITS;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  public static TTL_CIL_ObjectValue createFromByteArray( byte[] array,
							 Class dataType )
    throws Exception
  {
    ByteArrayInputStream bais = new ByteArrayInputStream( array );
    DataInputStream dis = new DataInputStream( bais );

    int _dataTypeInt = dis.readInt();
    int _systemError = dis.readInt();
    int _bytes = dis.readInt();
    int _lowBytes = dis.readInt();
    int _highBytes = dis.readInt();
    int _seconds = dis.readInt();
    int _nanoseconds = dis.readInt();
    int _units = dis.readInt();

    Class clsArr[] = { Integer.class };
    Object objArr[] = { _dataTypeInt };
    Method meth = dataType.getMethod( "getReference", clsArr );
    TTL_DataType _type = meth.invoke( null, objArr );

    Timestamp t = new Timestamp
      ( _seconds, _nanoseconds, CalendarType.GREGORIAN, TimescaleType.UTC );
    boolean _eightBytes = ( _bytes == 4 ? false : true );
    TTL_DataUnit _dataUnit = TTL_DataUnit.getReference( _units );

    return( new TTL_CIL_ObjectValue( _type, _systemError, _eightBytes,
				     _lowBytes, _highBytes, t, _dataUnit ) );
  }


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create an ObjectVlaue with all fields allocated.
   * @param ty the TTL_DataType
   * @param err the system error (zero for no error)
   * @param eight boolean describing whether the data is 8 bytes rather than 4
   * @param low the low 4 bytes of data
   * @param high the high 4 bytes of data
   * @param t the timestamp of the data value
   * @param u the units of the data
   * @see #dataType
   * @see #systemError
   * @see #eightBytes
   * @see #lowBytes
   * @see #highBytes
   * @see #timestamp
   * @see #units
   */
  public TTL_CIL_ObjectValue( TTL_DataType ty, int err,
			      boolean eight, int low, int high,
			      Timestamp t, TTL_DataUnit u )
  {
    dataType = ty;
    systemError = err;
    eightBytes = eight;
    lowBytes = low;
    highBytes = high;
    timestamp = t;
    units = u;
  }


  /**
   * Create an ObjectValue having the specified data type and 4 byte data
   * value.
   * @param ty the TTL_DataType
   * @param low the low 4 bytes of data
   */
  public TTL_CIL_ObjectValue( TTL_DataType ty, int low );
  {
    dataType = ty;
    lowBytes = low;
  }


  /**
   * The data type representing the TTL OID (Object ID).
   * @returnd ataType
   * @see #dataType
   */
  public TTL_DataType getDataType()
  {
    return( dataType );
  }


  /**
   * The int representation of the data type representing the TTL OID 
   * (Object ID).  If <code>dataType</code> is NOT <code>null</code> then
   * <code>dataType.getInt()</code> is returned, otherwise,
   * <code>dataTypeInt</code> is returned
   * @return dataType.getInt() or dataTypeInt as appropriate
   * @see #dataType
   * @see #dataTypeInt
   */
  public int getDataTypeInt()
  {
    if( dataType != null )
      return( dataType.getInt() );

    return( dataTypeInt );
  }


  /**
   * Integer representation of the TTL System error.
   * @return systemError
   * @see #systemError
   */
  public int getSystemError()
  {
    return( systemError );
  }


  /**
   * Description of the data length.
   * @return eightBytes
   * @see #eightBytes
   */
  public boolean isEightBytes()
  {
    return( eightBytes );
  }


  /**
   * The first 32 bits of data.
   * @return lowBytes
   * @see #lowBytes
   */
  public int getLowBytes()
  {
    return( lowBytes );
  }


  /**
   * The last 32 bits of data.
   * @return highBytes
   * @see #highBytes
   */
  public int getHighBytes()
  {
    return( highBytes );
  }


  /**
   * the timestamp of the data.
   * @return timestamp
   * @see #timestamp
   */
  public Timestamp getTimestamp()
  {
    return( timestamp );
  }


  /**
   * The units of the data.
   * @return units
   * @see #units
   */
  public TTL_DataUnit getUnits()
  {
    return( units );
  }


  /**
   * Return this TTL_CIL Object Value as a byte array for the `payload' of a
   * TTL CIL packet.
   * @return
   */
  public byte[] asByteArray()
    throws IOException
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream( baos );

    // write OID
    dos.writeInt( type.getInt() );
    // write error
    dos.writeInt( systemError );
    // write data type
    dos.writeInt( ( eightBytes ? 8 : 4 ) );
    // write high bytes (5-8)
    dos.writeInt( highBytes );
    // write low bytes (1-4)
    dos.writeInt( lowBytes );
    // write timestamp seconds
    dos.writeInt( (int)( timestamp.getSeconds() ) );
    // write timestamp nanoseconds
    dos.writeInt( (int)( timestamp.getNanoseconds() ) );
    // write units
    dos.writeInt( units.getInt() );

    return( baos.toByteArray );
  }


  /**
   * Return a String description of this object.
   */
  public String toString()
  {
    return( "OID["+( dataType == null ? getDataTypeInt() : getDataType() )+
	    "] Error["+systemError+"] 8-byte["+eightBytes+"] Low-bytes["+
	    lowBytes+"] High-bytes["+highBytes+"] Seconds["+
	    timestamp.getSeconds()+"] Nanoseconds["+timestamp.getNanoseconds()+
	    "] Units["+units.getName()+"]" );
  }
}
/*
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_CIL_ObjectValue.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_CIL_ObjectValue.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 */
