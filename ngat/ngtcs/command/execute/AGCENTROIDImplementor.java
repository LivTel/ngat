package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.sdb.*;
import ngat.ngtcs.subsystem.ags.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AGCENTROIDImplementor
  extends CommandImplementor
{
  /*=======================================================================*/
  /*                                                                       */
  /* CLASS FIELDS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: AGCENTROIDImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/


  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/


  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

  /**
   *
   */
  public AGCENTROIDImplementor( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
  }


  /**
   * Execute the command.
   */
  public void execute()
  {
    TTL_DataValue val;
    commandDone = new AGCENTROIDDone( (AGCENTROID)command );

    TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();

    try
    {
      ( (AGCENTROIDDone)( commandDone ) ).
	setXPixel( ag.getCentroidXPixel() );

      ( (AGCENTROIDDone)( commandDone ) ).
	setYPixel( ag.getCentroidYPixel() );

      ( (AGCENTROIDDone)( commandDone ) ).
	setFWHM( ag.getCentroidFWHM() );

      ( (AGCENTROIDDone)( commandDone ) ).
	setMagnitude( ag.getCentroidMagnitude() );

      commandDone.setSuccessful( true );
    }
    catch( TTL_SystemException se )
    {
      logger.log( 1, logName, se );
      commandDone.setErrorMessage( se.toString() );
    }
  }
}
/*
 *    $Date: 2003-09-19 16:10:15 $
 * $RCSfile: AGCENTROIDImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGCENTROIDImplementor.java,v $
 *      $Id: AGCENTROIDImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $
 *     $Log: not supported by cvs2svn $
 */
