package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
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
  public static final String RevisionString =
    new String( "$Id: AGSELECTImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $" );

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
    ( ExecutionThread eT, Telescope t, Command c )
  {
    super( eT, t, c );
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
}
/*
 *    $Date: 2003-09-19 16:10:15 $
 * $RCSfile: AGSELECTImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGSELECTImplementor.java,v $
 *      $Id: AGSELECTImplementor.java,v 1.1 2003-09-19 16:10:15 je Exp $
 *     $Log: not supported by cvs2svn $
 */
