package ngat.ngtcs.command.execute;

import java.util.*;
import java.text.*;
import java.io.*;

import ngat.util.logging.Logger;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 *
 * Output a TPOINT test file.
 * <br>
 * This command takes an input file (currently just the RA and Dec of a target)
 * and processes them (using defaults for pm RA, pllx etc.) for a single
 * timestamp through the astrometry routines and the pointing model application
 * and outputs the results to a file suitable for TPOINT.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class TPOINTImplementor extends CommandImplementor
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: TPOINTImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );

    private TPOINT tpoint;
    private BufferedReader reader;
    private double limit;
    private DecimalFormat df;
    private AstrometryCalculator astroCalc;
    private SiteData siteData;
    private IERSData iersData;
    private MeteorologicalData metData;
    private Mount mount;
    private TimestampedPosition timePos;
    private ReportedTarget reported;
    private Timestamp timestamp;
    private XYZMatrix demandPos;
    private XYZMatrix rawPos;
    private XYZMatrix preMountPos, attitudeCorrected;
    private XYZMatrix rawRADec, meanRADec;
    private double rawRA, rawDec, meanRA, meanDec;
    private VirtualTelescope vt;
    private List limits;
    private List positions;
    private ngat.ngtcs.subsystem.Timer timer;
    private PointingModel pointing;
    private double demandPA, rawPA;


    public TPOINTImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     *
     */
    public void execute()
    {
	name      = telescope.getName();
	tpoint    = (TPOINT)command;
	mount     = telescope.getMount();
	limit     = Math.sin( Math.toRadians( 11.0 ) );
	vt        = telescope.getActiveVirtualTelescope();
	timer     = telescope.getTimer();
	astroCalc = telescope.getAstrometryCalculator();
	pointing  = mount.getPointingModel();
	siteData  = astroCalc.getSiteData();
	iersData  = astroCalc.getIERSData();
	metData   = astroCalc.getMeteorologicalData();
	positions = new Vector();
	df        = new DecimalFormat();

	try
	    {
		reader = new BufferedReader
		    ( new FileReader( tpoint.getInputFile() ) );
		processFile();
	    }
	catch( Exception e )
	    {
		logger.log( 1, logName, e );
		System.err.println( e );
		commandDone.setErrorMessage( e.toString() );
	    }
    }


    private void processFile() throws IOException, NoSuchElementException
    {
	String line = reader.readLine();
	StringTokenizer tokenizer;
	double ra, dec;
	Target target;
	PrintStream ps = new PrintStream( new FileOutputStream
	    ( name+".dat" ) );
	java.util.Calendar calendar = java.util.Calendar.getInstance();

	if( line != null )
	    {
		do
		    {
			System.err.println( "read line ["+line+"]" );

			 tokenizer = new StringTokenizer( line );

			 ra = Double.parseDouble( tokenizer.nextToken() );
			 dec = Double.parseDouble( tokenizer.nextToken() );

			 target = new Target();
			 target.setRA( ra );
			 target.setDec( dec );

			 positions.add( target );

			 System.err.println( "target added" );

			 line = reader.readLine();
		    }
		while( line != null );
	    }

	timestamp = timer.trigger();

	calendar.setTime( new Date( timestamp.getSeconds()*1000 ) );

	int day = calendar.get( java.util.Calendar.DAY_OF_MONTH );
	int month = calendar.get( java.util.Calendar.MONTH );
	int year = calendar.get( java.util.Calendar.YEAR );
	int hour = calendar.get( java.util.Calendar.HOUR_OF_DAY );
	int minutes = calendar.get( java.util.Calendar.MINUTE );

	double day_fraction = ( ( (double)minutes / 60.0 ) + (double)hour )
	    / 24.0;

	df.applyPattern( "00.00" );

	ps.println( "!\n"+
		    "! "+name+" test data\n"+
		    "!\n"+
		    "! lon : "+siteData.getLongitude()+"\n"+
		    "! lat : "+siteData.getLatitude()+"\n"+
		    "! alt : "+siteData.getAltitude()+"\n"+
		    "! polmox : "+iersData.getPolarMotionX()+"\n"+
		    "! polmoy : "+iersData.getPolarMotionY()+"\n"+
		    "! leapsecs : "+iersData.getLeapseconds()+"\n"+
		    "! UT1 - UTC : "+iersData.getUT1_UTC()+"\n"+
		    "! pressure : "+metData.getPressure()+"\n"+
		    "! temperature (K): "+
		    metData.getTemperatureKelvin()+"\n"+
		    "! humidity : "+metData.getHumidity()+"\n"+
		    "! wavelength = 5.5E-07 metres\n"+
		    "\n\n"+
		    name+" test"+
		    "\n\n"+
		    ": ALTAZ\n"+
		    Convert.radiansTo180DMSString
		    ( siteData.getLatitude() )+
		    " "+year+" "+(month+1)+" "+
		    df.format( (double)day+day_fraction )+
		    " "+metData.getTemperatureCelsius()+" "+
		    metData.getPressure()+" "+siteData.getAltitude()+" "+
		    metData.getHumidity() );

	for( int i = 0; i < positions.size(); i++ )
	    {
		System.err.println( "processing target "+i );

		target = (Target)positions.get( i );
	
		telescope.setCurrentTarget( target );

		// calculate theoretical mount position
		demandPos = vt.calcMountPosition( timestamp, target );

		// calculate theoretical rotator demand
		demandPA = vt.calcRotatorDemand( timestamp, target );

		// correct theoretical position for attitude
		attitudeCorrected = pointing.applyAttitudeCorrection
		    ( demandPos );

		// correct attitude-adjusted position for pointing
		rawPos = pointing.applyPositionCorrection
		    ( attitudeCorrected );

		// correct rotator demand
		rawPA = pointing.applyRotatorCorrection( demandPA );

		// calculate mean coordinates of pointing-corrected position
		reported = vt.calcObservedTarget
		    ( timestamp, rawPos );

		// calculate mean RA and Dec of raw position
		rawRADec = reported.getObservedRADec();
		rawRA  = Math.atan2( rawRADec.getY(), rawRADec.getX() );
		rawDec = Math.asin( rawRADec.getZ() );

		// calculate mean RA and Dec of mean position
		reported = vt.calcObservedTarget
		    ( timestamp, demandPos );
		meanRADec = reported.getOutputRADec();
		meanRA  = Math.atan2( meanRADec.getY(), meanRADec.getX() );
		meanDec = Math.asin( meanRADec.getZ() );

		// output to file IF above refraction horizon
		if( ( reported.getObserved().getZ() > limit ) &&
		    ( reported.getObserved().getZ() < 0.49 * Math.PI ) )
		    {
			ps.print( Convert.radiansTo24HMSString
				  ( meanRA )
				  +" "+
				  Convert.radiansTo180DMSString
				  ( meanDec )
				  +" +0.0 +0.0 "+
				  target.getEquinox().getName()
				  +" " );

			ps.print( ( Convert.radiansTo24HMSString( rawRA ) )+
				  " "+
				  ( Convert.radiansTo180DMSString( rawDec ) )+
				  " "+
				  Convert.radiansTo24HFormattedTime
				  ( "00 00.000", reported.getLSTRadians() )+
				  "\n" );
		    }
	    }
	ps.close();

	System.err.println( "processing complete" );

	commandDone.setSuccessful( true );
    }
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: TPOINTImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/TPOINTImplementor.java,v $
 *      $Id: TPOINTImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
