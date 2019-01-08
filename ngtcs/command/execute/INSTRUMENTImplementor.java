package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Configure the telescope and TCS for a particular instrument. Thiscommand
 * will set the focal station, rotator, default focus, fold mirror position
 * and initialise the autoguider.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class INSTRUMENTImplementor extends CommandImplementor
{
  /*=========================================================================*/
  /*  /*                                                                     */
  /* CLASS FIELDS.  /*                                                       */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   * The timeout for the INSTRUMENT command (300 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 300000;

  /*=========================================================================*/
  /*  /*                                                                     */
  /* OBJECT FIELDS.  /*                                                      */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * The VirtualTelescope represented by the port name specified in the
   * INSTRUMENT command.
   */
  protected VirtualTelescope vt;

  /**
   * The FocalStation on the specified VirtualTelescope.
   */
  protected FocalStation fs;

  /*=========================================================================*/
  /*  /*                                                                     */
  /* CLASS METHODS.  /*                                                      */
  /*  /*                                                                     */
  /*=========================================================================*/


  /*=========================================================================*/
  /*  /*                                                                     */
  /* OBJECT METHODS.  /*                                                     */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * Just calls <code>super( t, c );</code>
   * @see CommandImplementor
   */
  public INSTRUMENTImplementor
    ( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * First, the active VirtualTelescope is set on the telescope.  If the
   * specified VT name does not correspond to a configured VT, then a failure
   * is returned.  Once the active VT is set on the telescope, the secondary
   * mirror focus and focus offsets are applied.  <b>NOTE:</b> focus offsets
   * (DFOCUS) are not reset between INSTRUMENT commands.  
   */
  public void execute()
  {
    INSTRUMENT i = (INSTRUMENT)command;
    String port = i.getPortName();

    VirtualTelescope vt = telescope.getActiveVirtualTelescope();
    FocalStation fs = vt.getFocalStation();
    TTL_SecondaryMirror sm = TTL_SecondaryMirror.getInstance();

    if( telescope.setActiveVirtualTelescope( port ) == false )
    {
      logError( "No such port descriptor ["+port+"] : execution terminated" );
      return;
    }

    SubCommandImplementorThread focusThread = new FOCUSImplementorThread();
    new Thread( focusThread ).start();

    SubCommandImplementorThread foldThread = new MOVE_FOLDImplementorThread();
    new Thread( foldThread ).start();

    // wait for focus command to finish
    while( ! focusThread.isFinished() )
    {
      sleep( 1000 );
    }
    if( ! focusThread.wasSuccessful() )
    {
      logError( "INSTRUMENT failed : DFOCUS failed : "+
		focusThread.getErrorMessage() );
      return;
    }

    // wait for fold thread to finish
    while( ! foldThread.isFinished() )
    {
      sleep( 1000 );
    }
    if( ! foldThread.wasSuccessful() )
    {
      logError
	( "INSTRUMENT failed : MOVE_FOLD failed : "+
	  foldThread.getErrorMessage() );
      return;
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


  /**
   * Internal class to provide concurrent operation for the
   * <code>INSTRUMENTImplementor.execute()</code> method.
   *
   * This class will run a thread to first set the secondary mirror focus
   * position to the new default of the INSTRUMENT command-specified port.
   * Then the previous offset is re-imlemented to maintain continuity.
   */
  protected class FOCUSImplementorThread extends SubCommandImplementorThread
  {
    /**
     * The Thread.run() method.  This thread moves the secondary mirror to the
     * new default position, and then moves the mirror to the currently set
     * focus offset.
  */
    public void run()
    {
      // change focus position
      TTL_SecondaryMirror sm = TTL_SecondaryMirror.getInstance();
      FOCUS focus =new FOCUS( command.getId()+"-FOCUS", fs.getFocus() );
      FOCUSImplementor focusImp = new FOCUSImplementor( telescope, focus );
      focusImp.execute();
      CommandDone cd = cd = focusImp.getDone();
      if( cd.getSuccessful() != true )
      {
	errorMessage = new String( cd.getErrorMessage() );
	finished = true;
	return;
      }

      // apply previous offset
      DFOCUS dFocus = 
	new DFOCUS( command.getId()+"-DFOCUS", sm.getFocusOffset() );
      DFOCUSImplementor dFocusImp = new DFOCUSImplementor( telescope, dFocus );
      dFocusImp.execute();
      cd = dFocusImp.getDone();
      if( cd.getSuccessful() != true )
      {
	errorMessage = new String( cd.getErrorMessage() );
	finished = true;
	return;
      }

      message = new String( cd.getReturnMessage() );
      errorMessage = new String( cd.getErrorMessage() );
      success = true;
      finished = true;
      return;
    }
  }


  /**
   * Internal class to provide concurrent operation for the
   * <code>INSTRUMENTImplementor.execute()</code> method.
   *
   * This class will run a thread to move the fold mirror to the INSTRUMENT
   * command-specified port.
   */
  protected class MOVE_FOLDImplementorThread
    extends SubCommandImplementorThread
  {
    /**
     * The Thread.run() method.  This thread moves the science fold-mirror
     * to the orientation required by the outer INSTRUMENT command.
  */
    public void run()
    {
      // move fold mirror
      MOVE_FOLD moveFold =
	new MOVE_FOLD( command.getId()+"-MOVE_FOLD", fs.getPortNumber() );
      MOVE_FOLDImplementor moveFoldImpl =
	new MOVE_FOLDImplementor( telescope, moveFold );
      moveFoldImpl.execute();
      CommandDone cd = moveFoldImpl.getDone();
      success = cd.getSuccessful();
      message = new String( cd.getReturnMessage() );
      errorMessage = new String( cd.getErrorMessage() );
      finished = true;
    }
  }
}
/*
 *    $Date: 2013-07-04 10:16:31 $
 * $RCSfile: INSTRUMENTImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/INSTRUMENTImplementor.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.4  2003/10/14 13:55:15  je
 *     Implemented execute.
 *
 *     Revision 1.3  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
