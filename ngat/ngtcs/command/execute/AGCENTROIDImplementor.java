package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.sdb.*;
import ngat.ngtcs.subsystem.ags.*;

/**
 * This Implementor returns the details of the object used for autoguiding.
 * The details returned are: the (X,Y) pixel coordinates, the Full-width
 * half-maximum of the object's Gaussian point-spread function, and the
 * magnitude.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
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
    new String( "$Id: AGCENTROIDImplementor.java,v 1.2 2003-09-22 10:27:24 je Exp $" );

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
    commandDone = new AGCENTROIDDone( (AGCENTROID)command );
    TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();
    double x = 0.0, y = 0.0, f = 0.0, m = 0.0;

    try
    {
      x = ag.getCentroidXPixel();
      y = ag.getCentroidYPixel();
      f = ag.getCentroidFWHM();
      m = ag.getCentroidMagnitude();
    }
    catch( TTL_SystemException se )
    {
      logger.log( 1, logName, se );
      commandDone.setErrorMessage( se.toString() );
      return;
    }

    logger.log( 3, logName, "Autoguide centroid: (X,Y) = ("+x+", "+y+"); "+
		"FWHM = "+f+"; Magnitude = "+m );

    ( (AGCENTROIDDone)commandDone ).setXPixel( x );
    ( (AGCENTROIDDone)commandDone ).setYPixel( y );
    ( (AGCENTROIDDone)commandDone ).setFWHM( f );
    ( (AGCENTROIDDone)commandDone ).setMagnitude( m );

    commandDone.setSuccessful( true );
  }
}
/*
 *    $Date: 2003-09-22 10:27:24 $
 * $RCSfile: AGCENTROIDImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGCENTROIDImplementor.java,v $
 *      $Id: AGCENTROIDImplementor.java,v 1.2 2003-09-22 10:27:24 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
