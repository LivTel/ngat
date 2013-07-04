package ngat.ngtcs.command.execute;

import java.util.List;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 *
 * The <code>TEST</code> command simply runs through the TRACK procedure,
 * outputting all details to the application terminal.
 * <p>
 * The output consists of:
 * <ul><li>
 * All <code>Target</code> parameters (name, coords, epoch etc.)
 * </li><li>
 * Current <code>RotatorMode</code> being used by <code>Telescope</code> and
 * requested position angle of rotator.
 * </li><li>
 * The timestamp being used for the transformations.
 * </li><li>
 * The Demanded mount and rotator positions.
 * </li><li>
 * The intermediate steps of the astrometric transformation (Apparent,
 * refracted, pointing model etc.)
 * </li></ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.4 $
 */
public class TESTImplementor extends CommandImplementor
{
    /**
     * String used to identify RCS revision details.
  */
    public static final String rcsid =
	new String( "$Id: TESTImplementor.java,v 1.4 2013-07-04 10:29:41 cjm Exp $" );

  /**
   * The timeout for the  command (3 seconds), in milliseconds
   */
  public static final int TIMEOUT = 3000;


    /**
     *
     *
  */
    public TESTImplementor( Telescope ts, Command c )
    {
	super( ts, c );
    }


    /**
     *
     *
  */
    public void execute()
    {
	TEST test                 = (TEST)command;
	Timer timer               = telescope.getTimer();
	VirtualTelescope activeVT = telescope.getActiveVirtualTelescope();
	Mount mount               = telescope.getMount();
	PointingModel pointing    = mount.getPointingModel();
	Target target             = test.getTarget();
	RotatorMode rotatorMode   = telescope.getRotatorMode();;
	double positionAngle      = telescope.getPositionAngle();
	ReportedTarget reported;
	Timestamp timestamp;
	double rotatorDemand, correctedRotator, reportedPA;
	XYZMatrix demand, attitudeCorrected, pointingCorrected;

	telescope.setCurrentTarget( target );
	timestamp = timer.trigger();

	System.out.println( "\n\n==========================================="+
			    "====================================\n" );
	System.out.println( "Target now set on Telescope ["+
			    telescope.getName()+"]\n"+"Target:\n=======\n"+
			    target );
	System.out.println( "             Rotator Mode : "+
			    rotatorMode.getName() );
	System.out.println( "           Position Angle : "+
			    Math.toDegrees( positionAngle ) );
	System.out.println( "\nTimestamp\n=========\n\t"+
			    Convert.formatTime( timestamp.getSeconds() ) );


	demand = activeVT.calcMountPosition( timestamp, target );
	rotatorDemand = activeVT.calcRotatorDemand( timestamp, target );


	System.out.println( "\nTheoretical position\n=================" );
	System.out.println( "        Mount Position : "+
			    Convert.xyzMatrixToAltAz( demand ) );
	System.out.println( "         Rotator Angle : "+
			    Math.toDegrees( rotatorDemand ) );


	attitudeCorrected = pointing.applyAttitudeCorrection
	    ( demand );
	pointingCorrected = pointing.applyPositionCorrection
	    ( attitudeCorrected );
	correctedRotator = pointing.applyRotatorCorrection
	    ( rotatorDemand );


	System.out.println( "\nCorrected position\n==================" );
	System.out.println( "    Attitude corrected : "+
			    Convert.xyzMatrixToAltAz
			    ( attitudeCorrected ) );
	System.out.println( "    Pointing corrected : "+
			    Convert.xyzMatrixToAltAz
			    ( pointingCorrected ) );
	System.out.println( "        Rotator demand : "+
			    Math.toDegrees( correctedRotator )+"\n" );


	reported = activeVT.calcObservedTarget( timestamp, demand );
	reportedPA = activeVT.calcPositionAngle( timestamp, demand );


	System.out.println( "\nReported position\n=================" );
	System.out.println( reported );
	System.out.println( "\nRotator"+
			    "\n=======" );
	System.out.println( "   Rotator Mode : "+
			    rotatorMode.getName() );
	System.out.println( " Position Angle : "+
			    Math.toDegrees( reportedPA ) );


	System.out.println( "\n=============================================="+
			    "=================================" );

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
 *    $Date: 2013-07-04 10:29:41 $
 * $RCSfile: TESTImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/TESTImplementor.java,v $
 *      $Id: TESTImplementor.java,v 1.4 2013-07-04 10:29:41 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.3  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
