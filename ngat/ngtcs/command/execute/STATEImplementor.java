package ngat.ngtcs.command.execute;

import java.util.List;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;

/**
 *
 * The <code>STATE</code> command returns the current
 * <code>TelescopeState</code> <code>and SoftwareState</code> of the NGTCS
 * application.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
 *
 */
public class STATEImplementor extends CommandImplementor
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: STATEImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $" );

  /**
   * The timeout for the STATE command (5 seconds), in milliseconds
   */
  public static final int TIMEOUT = 5000;


  public STATEImplementor( Telescope ts, Command c )
  {
    super( ts, c );
  }


  /**
   *
   */
  public void execute()
  {
    TelescopeState telescopeState = telescope.getTelescopeState();
    SoftwareState softwareState = telescope.getSoftwareState();
    commandDone.setReturnMessage
      ( "\n software : "+softwareState+
	"\ntelescope : "+telescopeState+"\n" );
    commandDone.setSuccessful( true );
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
 * $RCSfile: STATEImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/STATEImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
