package ngat.ngtcs.subsystem.sdb;

import ngat.ngtcs.subsystem.*;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public class SDB_DataType
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
    new String( "$Id: SDB_DataType.java,v 1.1 2006-11-20 14:46:54 cjm Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The TTL_DataType to retrieve.
   */
  TTL_DataType dataType = null;

  /**
   * The node reporting <code>dataType</code> to the SDB.
   */
  TTL_CIL_Node node = null;

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
  public SDB_DataType( TTL_DataType t, TTL_CIL_Node n )
  {
    dataType = t;
    node = n;
  }


  /**
   *
   */
  public TTL_DataType getDataType()
  {
    return( dataType );
  }


  /**
   *
   */
  public TTL_CIL_Node getReportingNode()
  {
    return( node );
  }
}
/*
 *    $Date: 2006-11-20 14:46:54 $
 * $RCSfile: SDB_DataType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/sdb/SDB_DataType.java,v $
 *      $Id: SDB_DataType.java,v 1.1 2006-11-20 14:46:54 cjm Exp $
 *     $Log: not supported by cvs2svn $
 */
