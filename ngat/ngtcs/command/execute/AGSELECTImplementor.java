package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Select which Autoguider to use.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
 */
public class AGSELECTImplementor
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
  public static final String rcsid =
    new String( "$Id: AGSELECTImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $" );

  /**
   * The timeout for the AGSELECT command (20 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 20000;

  /**
   *
   */
  protected static TTL_Autoguider selectedAutoguider = null;

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

  /**
   * 
   */
  public static TTL_Autoguider getSelectedAutoguider()
  {
    return selectedAutoguider;
  }

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/


  /**
   *
   */
  public AGSELECTImplementor
    ( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   *
   */
  public void execute()
  {
    String agName = ( (AGSELECT)command ).getName();
    PluggableSubSystem p = telescope.getPluggableSubSystem( agName );
    if( p == null )
    {
      commandDone.setErrorMessage
	( "Telescope ["+telescope.getName()+"] returned a null "+
	  "PluggableSubSystem for name ["+agName+"]" );
    }
    else
    {
      selectedAutoguider = (TTL_Autoguider)p;
      commandDone.setSuccessful( true );
    }
  }


  /**
   * Return the default timeout for this command execution.
   * @return TIMEOUT
   * @see #TIMEOUT
   */
  public int calcAcknowledgeTime()
  {
    return( TIMEOUT );
  }
}
/*
 *    $Date: 2003-09-26 09:58:41 $
 * $RCSfile: AGSELECTImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGSELECTImplementor.java,v $
 *      $Id: AGSELECTImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
