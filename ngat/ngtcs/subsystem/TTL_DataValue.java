package ngat.ngtcs.subsystem;

import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_DataValue
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
   * The type of data held in this object.
   */
  protected TTL_DataType type = null;

  /**
   * The returned data value-holding object.
   */
  protected int value = 0;

  /**
   * The units in which the returned value is held in the SDB.
   */
  protected TTL_DataUnit units = null;

  /**
   * Timestamp at which the value was recorded.
   */
  protected Timestamp timestamp = null;

  /**
   * Boolean describing whether an error was encountered in the SDB.
   */
  protected boolean error = false;

  /**
   * Error message from the SDB.
   */
  protected String errorMsg = null;

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
  public TTL_DataValue( TTL_DataType t, int i, TTL_DataUnit u, Timestamp s )
    throws IllegalArgumentException
  {
    type = t;
    value = i;
    units = u;
    timestamp = s;
  }


  /**
   *
   */
  public TTL_DataValue( TTL_DataType t, int i )
    throws IllegalArgumentException
  {
    type = t;
    value = i;
    timestamp = new Timestamp
      ( 0, 0, CalendarType.GREGORIAN, TimescaleType.UTC );
  }


  /**
   *
   */
  public TTL_DataValue( String s )
  {
    errorMsg = s;
    error = true;
  }


  /**
   * Retrieve the type of data held in this object
   * @return type
   * @see #type
   */
  public TTL_DataType getType()
  {
    return type;
  }


  /**
   * Retrieve the value extracted from the system.
   * @return value
   * @see #value
   */
  public int getValue()
  {
    return value;
  }


  /**
   * Retrieve the units which the value is given in.
   * @return units
   * @see #units
   */
  public TTL_DataUnit getUnits()
  {
    return units;
  }


  /**
   * Retrieve the timestamp at which the value was recorded.
   * @return timestamp
   * @see #timestamp
   */
  public Timestamp getTimestamp()
  {
    return timestamp;
  }
    

  /**
   * Return the boolean description of whether an error was encountered
   * whilst polling the system..
   * @return error
   * @see #error
   */
  public boolean hasError()
  {
    return error;
  }


  /**
   * Return the error message generated by the system.
   * @return errorMsg
   * @see #errorMsg
   */
  public String getErrorMessage()
  {
    return errorMsg;
  }
}
/*
 *    $Date: 2013-07-04 10:55:32 $
 * $RCSfile: TTL_DataValue.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_DataValue.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:01:09  je
 *     Initial revision
 *
 */
