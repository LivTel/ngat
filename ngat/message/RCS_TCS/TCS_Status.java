package ngat.message.RCS_TCS;

import java.io.*;
import java.text.*;
import java.util.*;

import ngat.util.*;
import ngat.astrometry.*;

/** Holds the state of the TCS and telescope systems.
 * <br><br>
 * $Id: TCS_Status.java,v 1.4 2013-07-01 09:40:15 eng Exp $
 */
public class TCS_Status implements Serializable, Cloneable {

    // Constants.

    /** M/sec to knots.*/
    public static final double MSEC_TO_KNOTS = 1.9438445;

    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd @ HH:mm:ss.SSS");

    /** Constant: Indicates that quantity has not been set.*/
    public static final int NO_VALUE      = 0;
    
    /** Constant: Indicates the Autoguider state LOCKED (closed loop guiding).*/
    public static final int AG_LOCKED     = 111;

    /** Constant: Indicates the Autoguider state UNLOCKED (open loop tracking).*/
    public static final int AG_UNLOCKED   = 112;

    /** Constant: Indicates the Autoguider state SUSPENDED.*/
    public static final int AG_SUSPENDED  = 113;
    
    /** Constant: Indicates Autoguider guide star selection mode by RANK.*/
    public static final int AG_MODE_RANK  = 114;
 
    /** Constant: Indicates Autoguider guide star selection mode by star at PIXEL_POSITION.*/
    public static final int AG_MODE_PIXEL = 115;

    /** Constant: Indicates Autoguider guide star selection mode by star in magnitude RANGE.*/
    public static final int AG_MODE_RANGE = 116;
    

    /** Constant: Indicates that a drive is in state STOPPED.*/
    public static final int MOTION_STOPPED    = 220;

    /** Constant: Indicates that a drive is in state IN_POSITION.*/
    public static final int MOTION_INPOSITION = 221;

    /** Constant: Indicates that a drive is in state TRACKING.*/
    public static final int MOTION_TRACKING   = 222;

    /** Constant: Indicates that a drive is in state MOVING.*/
    public static final int MOTION_MOVING     = 223;

    /** Constant: Indicates that a drive is in state LIMIT.*/
    public static final int MOTION_LIMIT      = 224;

    /** Constant: Indicates that a drive is in state OVERRIDE.*/
    public static final int MOTION_OVERRIDE   = 225;

    /** Constant: Indicates that a drive is in state EXPIRED.*/
    public static final int MOTION_EXPIRED    = 226;

    /** Constant: Indicates that a drive is in state OFF_LINE.*/
    public static final int MOTION_OFF_LINE   = 227;
    
    /** Constant: Indicates that a drive is in state UNKNOWN.*/
    public static final int MOTION_UNKNOWN    = 228;

    /** Constant: Indicates (SIMULATION_MODE) that a drive is in state MOTION_INCREASING .*/
    public static final int MOTION_INCREASING = 230;

    /** Constant: Indicates (SIMULATION_MODE) that a drive is in state MOTION_DECREASING.*/
    public static final int MOTION_DECREASING = 231;

    /** Constant: Indicates (SIMULATION_MODE) that a drive is in state SLEWING.*/
    public static final int MOTION_SLEWING    = 232;

    /** Constant: Indicates that a drive is in state ERROR.*/
    public static final int MOTION_ERROR      = 233;

    /** Constant: Indicates that a drive is in state WARNING.*/
    public static final int MOTION_WARNING    = 234;
  

    /** Constant: Indicates that a mechanism is in state IN.*/
    public static final int POSITION_IN      = 340;

    /** Constant: Indicates that a mechanism is in state OUT.*/
    public static final int POSITION_OUT     = 341;

    /** Constant: Indicates that a mechanism is in state CLOSED.*/
    public static final int POSITION_CLOSED  = 342;
    
    /** Constant: Indicates that a mechanism is in state OPEN.*/
    public static final int POSITION_OPEN    = 343;

    /** Constant: Indicates that a mechanism is in state PARTIAL.*/
    public static final int POSITION_PARTIAL = 344;
    
    /** Constant: Indicates that a mechanism is in state UNKNOWN.*/
    public static final int POSITION_UNKNOWN = 345;


    /** Constant: Indicates that a mechanism is in location STOWED.*/
    public static final int POSITION_STOWED  = 351;
    
    /** Constant: Indicates that a mechanism is in location PORT_1.*/
    public static final int POSITION_PORT_1  = 352;
    
    /** Constant: Indicates that a mechanism is in location PORT_2.*/
    public static final int POSITION_PORT_2  = 353;
    
    /** Constant: Indicates that a mechanism is in location PORT_3.*/
    public static final int POSITION_PORT_3  = 354;
    
    /** Constant: Indicates that a mechanism is in location PORT_4.*/
    public static final int POSITION_PORT_4  = 355;

    /** Constant: Indicates that a mechanism is in location PORT_5.*/
    public static final int POSITION_PORT_5  = 356;
    
    /** Constant: Indicates that a mechanism is in location PORT_6.*/
    public static final int POSITION_PORT_6  = 357;
    
    /** Constant: Indicates that a mechanism is in location PORT_7.*/
    public static final int POSITION_PORT_7  = 358;
    
    /** Constant: Indicates that a mechanism is in location PORT_8.*/
    public static final int POSITION_PORT_8  = 359;

     /** Constant: Indicates that a mechanism is in state IN_LINE.*/
    public static final int POSITION_INLINE  = 360;

    /** Constant: Indicates that a mechanism is in state RETRACT.*/
    public static final int POSITION_RETRACT = 361;



     /** Constant: Indicates that a subsystem is in state OKAY.*/
    public static final int STATE_OKAY      = 460;

    /** Constant: Indicates that a subsystem is in state INIT.*/
    public static final int STATE_INIT      = 461;

    /** Constant: Indicates that a subsystem is in state STANDBY.*/
    public static final int STATE_STANDBY   = 462;

    /** Constant: Indicates that a subsystem is in state SUSPENDED.*/
    public static final int STATE_SUSPENDED = 463;

    /** Constant: Indicates that a subsystem is in state WARN.*/
    public static final int STATE_WARN      = 464;

    /** Constant: Indicatesthat a subsystem is in state FAILED.*/
    public static final int STATE_FAILED    = 465;

    /** Constant: Indicates that a subsystem is in state SAFE.*/
    public static final int STATE_SAFE      = 466;

    /** Constant: Indicates that a subsystem is in state OFF.*/
    public static final int STATE_OFF       = 467;
   
    /** Constant: Indicates that a subsystem is in state ON .*/
    public static final int STATE_ON        = 468;
   
    /** Constant: Indicates that a subsystem is in state INVALID.*/
    public static final int STATE_INVALID   = 469;

    /** Constant: Indicates that a subsystem is in state ERROR .*/
    public static final int STATE_ERROR     = 470;

    /** Constant: Indicates that a subsystem is in state UNKNOWN.*/
    public static final int STATE_UNKNOWN   = 471;

    /** Constant: Indicates that a subsystem is in state DISABLED.*/
    public static final int STATE_DISABLED  = 472;

    /** Constant: Indicates that a subsystem is in state ENABLED.*/
    public static final int STATE_ENABLED   = 473;

    /** Constant: Indicates that a subsystem is in state ENGAGED.*/
    public static final int STATE_ENGAGED   = 474;

    public static final int POWER_STATE_SHUTDOWN = 480;
    public static final int POWER_STATE_RESTART  = 481;
    public static final int POWER_STATE_OKAY     = 482;


    /** Constant: Indicates that a mechanism has hit its POSITIVE_LIMIT.*/
    public static final int LIMIT_POSITIVE           = 570;

    /** Constant: Indicates that a mechanism has hit its NEGITIVE_LIMIT.*/
    public static final int LIMIT_NEGATIVE           = 571;

    /** Constant: Indicates that a mechanism has hit its ZENITH_LIMIT.*/
    public static final int LIMIT_ZENITH             = 572;
    
    /** Constant: Indicates that a mechanism has NO_LIMIT.*/
    public static final int LIMIT_NO_LIMIT           = 573;

    /** Constant: Indicates that a mechanism is in its state SOURCE_RISES.*/
    public static final int LIMIT_SOURCE_RISES       = 574;

    /** Constant: Indicates that a mechanism is in its state SOURCE_SETS.*/
    public static final int LIMIT_SOURCE_SETS        = 575;
    
    /** Constant: Indicates that a mechanism is in its state SOURCE_NEVER_RISES.*/
    public static final int LIMIT_SOURCE_NEVER_RISES = 576;
    
    /** Constant: Indicates that a mechanism is in its state SOURCE_NEVER_SETS.*/
    public static final int LIMIT_SOURCE_NEVER_SETS  = 577;


    /** Constant: Indicates that the cassegrain rotator is in mode FLOAT.*/
    public static final int ROT_FLOAT    = 680; 
    
    /** Constant: Indicates that the cassegrain rotator is in mode VFLOAT.*/
    public static final int ROT_VFLOAT   = 681;
    
    /** Constant: Indicates that the cassegrain rotator is in mode MOUNT.*/
    public static final int ROT_MOUNT    = 682;
    
    /** Constant: Indicates that the cassegrain rotator is in mode SKY.*/
    public static final int ROT_SKY      = 683;

    /** Constant: Indicates that the cassegrain rotator is in mode VERTICAL.*/
    public static final int ROT_VERTICAL = 684;

    /** Constant: Indicates rain detector state ALERT (i.e. rain detected).*/
    public static final int RAIN_CLEAR = 790;

    /** Constant: Indicates rain detector state CLEAR (i.e. no rain detected).*/
    public static final int RAIN_ALERT = 791;

    /** Stores the String representations of the various state codes.*/
    static Hashtable codes = new Hashtable();

   
    // ------------
    // Time stamps.
    // ------------
 
    /** Records the time this Status was generated (This is somewhat dubious as each component may
     * have been generated at different times - use the individual segment timestamp).*/
    public long timeStamp;
   
    /** Astrometry data segment.*/
    public Astrometry astrometry;
    
    /** Autoguider data segment.*/
    public Autoguider autoguider;
    
    /** Calibrate data segment.*/
    public Calibrate calibrate; 
    
    /** Focal Station data segment.*/
    public FocalStation focalStation;
    
    /** Limits data segment.*/
    public Limits limits;   
    
    /** Mechanisms data segment.*/
    public Mechanisms mechanisms;
       
    /** Meteorology data segment.*/
    public Meteorology meteorology;
  
    /** Services data segment.*/
    public Services services;
	
    /** SourceBlock data segment.*/
    public SourceBlock source;
       
    /** State data segment.*/
    public State state;
       
    /** TCS Version data segment.*/
    public Version version;
   
    /** Time data segment.*/
    public Time time;
  
    /** Network data segment.*/
    public Network network;

    /** Create a TCS_Status using preset values for the System names.*/
    public TCS_Status() {
	timeStamp = System.currentTimeMillis();

	astrometry   = new Astrometry();       
	autoguider   = new Autoguider();  
	calibrate    = new Calibrate();       
	focalStation = new FocalStation();       
	limits       = new Limits();         
	mechanisms   = new Mechanisms();          
	meteorology  = new Meteorology();     
	services     = new Services();	   
	source       = new SourceBlock();          
	state        = new State();        
	version      = new Version();  
	time         = new Time();
	network      = new Network();

	autoguider.agSelected        = "LT_AG";
	focalStation.station         = "CASSEGRAIN";
	focalStation.instr           = "RATCam";
	focalStation.ag              = "LT_AG";
	mechanisms.azName            = "AZ01";
	mechanisms.altName           = "ALT01";
	mechanisms.rotName           = "ROT01";
	mechanisms.encShutter1Name   = "NORTH";
	mechanisms.encShutter2Name   = "SOUTH";
	mechanisms.foldMirrorName    = "FOLD01";
	mechanisms.primMirrorName    = "PRIMARY";
	mechanisms.secMirrorName     = "SECONDARY";
	mechanisms.primMirrorSysName = "MIRROR_SUPPORT_IFACE";
	source.srcName               = "";
	version.tcsVersion           = "ARI_LT_SIM_01";
	network.networkState         = STATE_UNKNOWN;
    }

    /** Builds the code table.*/
    public static final void mapCodes() {
	codes.put(new Integer (AG_LOCKED),                "LOCKED");
	codes.put(new Integer (AG_UNLOCKED),              "UNLOCKED");
	codes.put(new Integer (AG_SUSPENDED ),            "SUSPENDED");
	codes.put(new Integer (AG_MODE_RANK ),            "RANK");
	codes.put(new Integer (AG_MODE_PIXEL ),           "PIXEL");
	codes.put(new Integer (AG_MODE_RANGE),            "RANGE");  
   
	codes.put(new Integer (MOTION_STOPPED),           "STOPPED");
	codes.put(new Integer (MOTION_INPOSITION ),       "IN POSN");
	codes.put(new Integer (MOTION_TRACKING ),         "TRACKING");
	codes.put(new Integer (MOTION_MOVING),            "MOVING");	
	codes.put(new Integer (MOTION_LIMIT),             "LIMIT");
	codes.put(new Integer (MOTION_OVERRIDE),          "OVERRIDE");   
	codes.put(new Integer (MOTION_EXPIRED),           "EXPIRED"); 
	codes.put(new Integer (MOTION_OFF_LINE),          "OFF-LINE");
	codes.put(new Integer (MOTION_UNKNOWN),           "UNKNOWN");
	codes.put(new Integer (MOTION_INCREASING ),       "MOTION-INCREASING");
	codes.put(new Integer (MOTION_DECREASING ),       "MOTION-DECREASING");
	codes.put(new Integer (MOTION_SLEWING ),          "MOTION-SLEWING");
	codes.put(new Integer (MOTION_WARNING ),          "WARNING");
	

	codes.put(new Integer (POSITION_IN),              "IN");
	codes.put(new Integer (POSITION_OUT),             "OUT");
	codes.put(new Integer (POSITION_CLOSED),          "CLOSED");
	codes.put(new Integer (POSITION_OPEN),            "OPEN");
	codes.put(new Integer (POSITION_PARTIAL),         "PARTIAL");
	codes.put(new Integer (POSITION_UNKNOWN),         "POSITION-UNKNOWN");

	codes.put(new Integer (POSITION_STOWED),          "STOWED");
	codes.put(new Integer (POSITION_PORT_1),          "PORT 1");
	codes.put(new Integer (POSITION_PORT_2),          "PORT 2");
	codes.put(new Integer (POSITION_PORT_3),          "PORT 3");
	codes.put(new Integer (POSITION_PORT_4),          "PORT 4");
	codes.put(new Integer (POSITION_PORT_5),          "PORT 5");
	codes.put(new Integer (POSITION_PORT_6),          "PORT 6");
	codes.put(new Integer (POSITION_PORT_7),          "PORT 7");
	codes.put(new Integer (POSITION_PORT_8),          "PORT 8");

	codes.put(new Integer (POSITION_INLINE),          "IN LINE");
	codes.put(new Integer (POSITION_RETRACT),         "RETRACT");
	
	codes.put(new Integer (STATE_OKAY),               "OKAY");
	codes.put(new Integer (STATE_INIT),               "INIT");
	codes.put(new Integer (STATE_STANDBY),            "STANDBY");
	codes.put(new Integer (STATE_SUSPENDED),          "SUSPEND");
	codes.put(new Integer (STATE_WARN),               "WARN");
	codes.put(new Integer (STATE_FAILED),             "FAILED");
	codes.put(new Integer (STATE_SAFE),               "SAFE");
	codes.put(new Integer (STATE_ON),                 "ON");
	codes.put(new Integer (STATE_OFF),                "OFF");
	codes.put(new Integer (STATE_INVALID),            "INVALID");
	codes.put(new Integer (STATE_ERROR ),             "ERROR");
	codes.put(new Integer (STATE_UNKNOWN),            "UNKNOWN");
	codes.put(new Integer (STATE_DISABLED),           "DISABLED");
	codes.put(new Integer (STATE_ENABLED),            "ENABLED");
	codes.put(new Integer (STATE_ENGAGED),            "ENGAGED");


	codes.put(new Integer (LIMIT_POSITIVE ),          "POSITIVE-LIMIT");
	codes.put(new Integer (LIMIT_NEGATIVE),           "NEGATIVE-LIMIT");
	codes.put(new Integer (LIMIT_ZENITH),             "ZENITH-LIMIT");
	codes.put(new Integer (LIMIT_NO_LIMIT),           "NO-LIMIT");
	codes.put(new Integer (LIMIT_SOURCE_RISES ),      "SOURCE-RISES");
	codes.put(new Integer (LIMIT_SOURCE_SETS),        "SOURCE-SETS");
	codes.put(new Integer (LIMIT_SOURCE_NEVER_RISES), "SOURCE-NEVER-RISES");
	codes.put(new Integer (LIMIT_SOURCE_NEVER_SETS),  "SOURCE-NEVER-SETS");

	codes.put(new Integer (ROT_FLOAT),                "FLOAT");
	codes.put(new Integer (ROT_VFLOAT ),              "VFLOAT");
	codes.put(new Integer (ROT_MOUNT ),               "MOUNT");
	codes.put(new Integer (ROT_SKY ),                 "SKY");	
	codes.put(new Integer (ROT_VERTICAL ),            "VERTICAL");	

	codes.put(new Integer (RAIN_CLEAR),               "CLEAR");
	codes.put(new Integer (RAIN_ALERT),               "SET");

    }
    
    /** Returns the String for a given State code.*/
    public static String codeString(int code) {
	Integer cd = new Integer(code);
	if (codes.containsKey(cd))
	    return (String)codes.get(cd);
	else 
	    return "UNKNOWN";
    }
	    
    /** Returns the code number for a given status string.*/
    public static int getCode(String codeString) {
	if (!codes.containsValue(codeString.trim())) {
	    //System.err.println("StatusMap::Unknown:["+codeString+"]");
	    return NO_VALUE;
	}
	Iterator it = codes.keySet().iterator();
	while (it.hasNext()) {
	    Integer ik = (Integer)it.next();
	    String val = (String)codes.get(ik);
	    if (val.equals(codeString.trim())) 
		return ik.intValue();	    
	}
	System.err.println("TCS_Status::Status code mapping::Unknown code:["+codeString.trim()+"]");
	return NO_VALUE;
    }
    
    /** Returns A copy (clone) of this TCS_Status.*/
    public TCS_Status copy() { 
	try {
	    return (TCS_Status)this.clone();
	} catch (CloneNotSupportedException c) {
	    return null;
	}
    }
    
    /** Returns a readable version of the TCS_Status.*/
    public String toString() {
	StringBuffer buffer = new StringBuffer("TCS_Status: "+sdf.format(new Date(timeStamp)));

	buffer.append("\nStatus was generated: "+time);
	
	// Astrometry.
	buffer.append("\nAstrometry Segment:");
	buffer.append("\n Refraction pressure:    "+ astrometry.refractionPressure+" mBar.");  
	buffer.append("\n Refraction temperature: "+ astrometry.refractionTemperature+" K.");    
	buffer.append("\n Refraction humidity:    "+ astrometry.refractionHumidity+" %.");	
	buffer.append("\n Refraction wavelength:  "+ astrometry.refractionWavelength+" microns.");	
	buffer.append("\n UT1 - UTC:              "+ astrometry.ut1_utc+" secs.");	
	buffer.append("\n TDT - UDT:              "+ astrometry.tdt_utc+" secs.");    
	buffer.append("\n Polar correction X:     "+ astrometry.polarMotion_X+" arcsecs.");
	buffer.append("\n Polar correction Y:     "+ astrometry.polarMotion_Y+" arcsecs.");	
	buffer.append("\n Current airmass:        "+ astrometry.airmass);
	buffer.append("\n A/G wavelength:         "+ astrometry.agwavelength+" microns.");
	
	// Autoguider.
	buffer.append("\nAutoguider Segment:");
	buffer.append("\n Current autoguider selected: "+ autoguider.agSelected);	
	buffer.append("\n Autoguider status:           "+ codeString(autoguider.agStatus));    
    
	buffer.append("\n Guide star magnitude:        "+ autoguider.guideStarMagnitude);	
	buffer.append("\n FWHM:                        "+ autoguider.fwhm+" pixels.");
	
	buffer.append("\n Mirror demand position:      "+ autoguider.agMirrorDemand+" mm.");    
	buffer.append("\n Mirror position:             "+ autoguider.agMirrorPos+" mm.");	
	buffer.append("\n Mirror status:               "+ codeString(autoguider.agMirrorStatus));
    
	buffer.append("\n Focus demand position:       "+ autoguider.agFocusDemand+" mm.");	
	buffer.append("\n Focus position:              "+ autoguider.agFocusPos+" mm.");
	buffer.append("\n Focus status:                "+ codeString(autoguider.agFocusStatus));
	
	buffer.append("\n Filter demand:               "+ codeString(autoguider.agFilterDemand));
	buffer.append("\n Filter position:             "+ codeString(autoguider.agFilterDemand));
	buffer.append("\n Filter status:               "+ codeString(autoguider.agFilterStatus));
  
	// Calibrate.
	buffer.append("\nCalibrate Segment:");
	buffer.append("\n Default Azimuth error:    "+ calibrate.defAzError+" arcsecs.");
	buffer.append("\n Default Altitude error:   "+ calibrate.defAltError+" arcsecs.");	
	buffer.append("\n Default collimator error: "+ calibrate.defCollError+" arcsecs.");
	
	buffer.append("\n Current Azimuth error:    "+ calibrate.currAzError+" arcsecs.");	
	buffer.append("\n Current Altitude error:   "+ calibrate.currAltError+" arcsecs.");
	buffer.append("\n Current Collimator error: "+ calibrate.currCollError+" arcsecs.");

	buffer.append("\n Last Azimuth error:       "+ calibrate.currAzError+" arcsecs.");
	buffer.append("\n Last Azimuth rms:         "+ calibrate.lastAzRms+" arcsecs.");
	
	buffer.append("\n Last Altitude error:      "+ calibrate.currAltError+" arcsecs.");
	buffer.append("\n Last Altitude rms:        "+ calibrate.lastAltRms+" arcsecs.");
	
	buffer.append("\n Last Collimator error:    "+ calibrate.currCollError+" arcsecs.");
	buffer.append("\n Last Collimator rms:      "+ calibrate.lastCollRms+" arcsecs.");
	
	buffer.append("\n Last Sky sigma:           "+ calibrate.lastSkyRms+" arcsecs.");
   
	// Focal Station.
	buffer.append("\nFocal-Station Segment:");
	buffer.append("\n Current instrument station id: "+ focalStation.station);	
	buffer.append("\n Current instrument name:       "+ focalStation.instr);	
	buffer.append("\n Current autoguider name:       "+ focalStation.ag);
 
	// Limits.
	buffer.append("\nLimits Segment:");
	buffer.append("\n Azimuth positive limit:  "+ limits.azPosLimit+" degrees.");	
	buffer.append("\n Azimuth negative limit:  "+ limits.azNegLimit+" degrees.");	
	buffer.append("\n Altitude positive limit: "+ limits.altPosLimit+" degrees.");	
	buffer.append("\n Altitude negative limit: "+ limits.altNegLimit+" degrees.");	
	buffer.append("\n Rotator positive limit:  "+ limits.rotPosLimit+" degrees.");	
	buffer.append("\n Rotator negative limit:  "+ limits.rotNegLimit+" degrees.");
	
	buffer.append("\n Time to Azimuth limit:   "+ limits.timeToAzLimit+" secs."); 
	buffer.append("\n Azimuth limit sense:     "+ codeString(limits.azLimitSense));

	buffer.append("\n Time to Altitude limit:  "+ limits.timeToAltLimit+" secs."); 
	buffer.append("\n Altitude limit sense:    "+ codeString(limits.altLimitSense));

	buffer.append("\n Time to Rotator limit:   "+ limits.timeToRotLimit+" secs.");
	buffer.append("\n Rotator limit sense:     "+ codeString(limits.rotLimitSense));
	  
	// Mechanisms.
	buffer.append("\nMechanisms Segment:");
	buffer.append("\n Azimuth drive id:                      "+ mechanisms.azName);	
	buffer.append("\n  Azimuth demand position:              "+ mechanisms.azDemand+" degrees.");	
	buffer.append("\n  Current Azimuth position:             "+ mechanisms.azPos+" degrees.");	
	buffer.append("\n  Current Azimuth status:               "+ codeString(mechanisms.azStatus));
	
	buffer.append("\n Altitude drive id:                     "+ mechanisms.altName);	
	buffer.append("\n  Altitude demand position:             "+ mechanisms.altDemand+" degrees.");	
	buffer.append("\n  Current Altitude position:            "+ mechanisms.altPos+" degrees.");	
	buffer.append("\n  Current Altitude status:              "+ codeString(mechanisms.altStatus));
	
	buffer.append("\n Rotator id:                            "+ mechanisms.rotName);	
	buffer.append("\n  Rotator demand position:              "+ mechanisms.rotDemand+" degrees.");	
	buffer.append("\n  Current Rotator position:             "+ mechanisms.rotPos+" degrees.");	
	buffer.append("\n  Rotator mode:                         "+ codeString(mechanisms.rotMode));	
	buffer.append("\n  Rotator sky position angle:           "+ mechanisms.rotSkyAngle+" degrees.");	
	buffer.append("\n  Rotator status:                       "+ codeString(mechanisms.rotStatus));
	
	buffer.append("\n Enclosure shutter 1 id:                "+ mechanisms.encShutter1Name);	
	buffer.append("\n  Enclosure shutter 1 demand position:  "+ codeString(mechanisms.encShutter1Demand));	
	buffer.append("\n  Enclosure shutter 1 current position  "+ codeString(mechanisms.encShutter1Pos));	 
	buffer.append("\n  Current Enclosure 1 shutter status:   "+ codeString(mechanisms.encShutter1Status));

	buffer.append("\n Enclosure shutter 2 id:                "+ mechanisms.encShutter2Name);	
	buffer.append("\n  Enclosure shutter 2 demand position:  "+ codeString(mechanisms.encShutter2Demand));	
	buffer.append("\n  Enclosure shutter 2 current position  "+ codeString(mechanisms.encShutter2Pos));	 
	buffer.append("\n  Current Enclosure 2 shutter status:   "+ codeString(mechanisms.encShutter2Status));
	
	buffer.append("\n Fold mirror id:                        "+ mechanisms.foldMirrorName);	
	buffer.append("\n  Fold mirror demand position:          "+ codeString(mechanisms.foldMirrorDemand));	
	buffer.append("\n  Fold mirror current position:         "+ codeString(mechanisms.foldMirrorPos));	
	buffer.append("\n  Current Fold mirror:                  "+ codeString(mechanisms.foldMirrorStatus));
	
	buffer.append("\n Primary mirror cover id:               "+ mechanisms.primMirrorName);	
	buffer.append("\n  Primary mirror cover demand position: "+ codeString(mechanisms.primMirrorCoverDemand));
	buffer.append("\n  Primary mirror cover current position:"+ codeString(mechanisms.primMirrorCoverPos));
	buffer.append("\n  Current Primary mirror cover status:  "+ codeString(mechanisms.primMirrorCoverStatus));
	
	buffer.append("\n Secondary mirror id:                   "+ mechanisms.secMirrorName);	
	buffer.append("\n  Secondary mirror demand position:     "+ mechanisms.secMirrorDemand+" mm.");	
	buffer.append("\n  Current Secondary mirror position:    "+ mechanisms.secMirrorPos+" mm.");	
	buffer.append("\n  Current Focus offset:                 "+ mechanisms.focusOffset);	
	buffer.append("\n  Secondary mirror status:              "+ codeString(mechanisms.secMirrorStatus));
	
	buffer.append("\n Primary mirror system id:              "+ mechanisms.primMirrorSysName);	
	buffer.append("\n  Current Primary mirror system status: "+ codeString(mechanisms.primMirrorSysStatus));
	
	// Meteorology.
	buffer.append("\nMeteorology Segment:");
	buffer.append("\n WMS status:                   "+ codeString(meteorology.wmsStatus));
	
	buffer.append("\n External rain state:          "+ codeString(meteorology.rainState));
	
	buffer.append("\n Serrurier truss temperature:  "+ meteorology.serrurierTrussTemperature+" K.");
	buffer.append("\n Oil temperature:              "+ meteorology.oilTemperature+" K.");
	buffer.append("\n Primary mirror temperature:   "+ meteorology.primMirrorTemperature+" K.");
	buffer.append("\n Secondary mirror temperature: "+ meteorology.secMirrorTemperature+" K.");
	
	buffer.append("\n External (air) temperature:   "+ meteorology.extTemperature+" K.");
	
	buffer.append("\n External wind speed:          "+ meteorology.windSpeed+" m/s.");
	buffer.append("\n External wind direction:      "+ meteorology.windDirn+" deg.");
	
	buffer.append("\n External air pressure:        "+ meteorology.pressure+" mbar.");
	
	buffer.append("\n External humidity:            "+ meteorology.humidity+" %.");
	buffer.append("\n Light level:                  "+ meteorology.lightLevel+" W/m2.");
	
	// Services.
	buffer.append("\nServices Segment:");
	buffer.append("\nPower state: "+codeString(services.powerState));

	// Source Block.
	buffer.append("\nSource  Segment:");
	buffer.append("\nCurrent Source name:        "+ source.srcName);
	
	buffer.append("\n RA:                        "+ Position.toHMSString(Math.toRadians(source.srcRa)));	
	buffer.append("\n Declination:               "+ Position.toDMSString(Math.toRadians(source.srcDec)));	
	buffer.append("\n Equinox:                   "+ source.srcEquinox);	
	buffer.append("\n Epoch:                     "+ source.srcEpoch);
	buffer.append("\n Non-sidereal tracking RA:  "+ source.srcNsTrackRA+" sec/year.");
	buffer.append("\n Non-sidereal tracking Dec: "+ source.srcNsTrackDec+" arcsec/year.");
	buffer.append("\n Proper motion RA:          "+ source.srcPmRA+" sec/year.");	
	buffer.append("\n Proper motion declination: "+ source.srcPmDec+" arcsec/year.");	
	buffer.append("\n Parallax:                  "+ source.srcParallax+" arcsec.");
	buffer.append("\n Radial Velocity:           "+ source.srcRadialVelocity+" km/s.");
	
	// State.
	buffer.append("\nState Segment:");
	buffer.append("\n Current control state:     "+ codeString(state.networkControlState));	
	buffer.append("\n Engineering Override state:"+ codeString(state.engineeringOverrideState));
	buffer.append("\n Current telescope state:   "+ codeString(state.telescopeState));	
	buffer.append("\n TCS state:                 "+ codeString(state.tcsState));
	buffer.append("\n System Restart flag:       "+ state.systemRestartFlag);
	buffer.append("\n System Shutdown flag:      "+ state.systemShutdownFlag);

	// Version.
	buffer.append("\nVersion Segment:");
	buffer.append("\n TCS version number: "+version.tcsVersion);
    
	// Time.
	buffer.append("\nTime Segment:");
	buffer.append("\n Current time (MJD): "+ time.mjd+" mjd.");	
	buffer.append("\n Current time (UT1): "+ time.ut1+" secs.");	
	buffer.append("\n Current time (LST): "+ time.lst+" ??.");

	return buffer.toString();
	
    }
    
    /** Superclass of all status data segments.*/
    public static abstract class Segment implements SerializableStatusCategory {
	
	/** The time (millis since 1970) when this data segment was last updated.*/
	public long timeStamp;

	/** Create a Status Segment.*/
	public Segment() {
	    timeStamp = System.currentTimeMillis();
	}

	/** Returns an Object (possibly null) represented by the key.*/
	public Object getEntry(String key) { return null; }

	/** Tries to return the raw status. Attempts as:
	 * String, int, double then gives up.
	 */
	public String getStatusEntryRaw(String key) {
	    try {
		return getStatusEntryId(key);
	    } catch (IllegalArgumentException iax1) {
		try {
		    return ""+getStatusEntryInt(key);
		} catch (IllegalArgumentException iax2) {
		    try {
			return ""+getStatusEntryDouble(key);
		    } catch (IllegalArgumentException iax3) {
			throw new IllegalArgumentException("TCS_Status: No such status key: "+key);	
		    }
		}
	    }
	}

	public long getTimeStamp() { return timeStamp; }
	
    }

    /** Holds the Astrometry data.*/
    public static class Astrometry extends Segment {
	
	/** Records the pressure used in the refraction calculations (mBar).*/
	public double refractionPressure;
	
	/** Records the temperature used in the refraction calculations (K).*/
	public double refractionTemperature;
	
	/** Records the humidity used in the refraction calculations (%).*/
	public double refractionHumidity;
	
	/** Records the wavelength used in the refraction calculations (microns).*/
	public double refractionWavelength;
	
	/** Records the difference between UT1 and UTC in use (secs).*/
	public double ut1_utc;

	/** Records the difference between TDT and UDT in use (secs).*/
	public double tdt_utc;
	
	/** Records the polar correction X (arcsec).*/
	public double polarMotion_X;
	
	/** Records the polar correction Y (arcsec).*/
	public double polarMotion_Y;
    
	/** Records the current airmass.*/
	public double airmass;

	/** Records the autoguider wavelength (microns).*/
	public double agwavelength;

	/** Returns a readable version of the TCS_Status$Segment .*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("Astrometry: "+sdf.format(new Date(this.timeStamp))+"(");  
	    buffer.append("RefPressure="+     refractionPressure);
	    buffer.append(", RefTemp="+       refractionTemperature );
	    buffer.append(", RefHumid="+      refractionHumidity );  
	    buffer.append(", RefWavelength="+ refractionWavelength  );  
	    buffer.append(", Ut1Utc="+        ut1_utc );  
	    buffer.append(", TdtUtc="+        tdt_utc );  
	    buffer.append(", PolarX="+        polarMotion_X );  
	    buffer.append(", PolarY="+        polarMotion_Y );  
	    buffer.append(", Airmass="+       airmass );  
	    buffer.append(", AgWavekength="+  agwavelength   );  
	    buffer.append(")");  
	    return buffer.toString();
	}

	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {	   
		throw new IllegalArgumentException("TCS_Status:Astrometry: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {
	    if (key.equals("refraction.pressure"))
		return refractionPressure;
	    else if 
		(key.equals("refraction.temperature"))
		return refractionTemperature;
	    else if 
		(key.equals("refraction.humidity"))
		return refractionHumidity;
	    else if 
		(key.equals("refraction.wavelength"))
		return refractionWavelength;
	    else if 
		(key.equals("ut1.utc"))
		return ut1_utc;
	    else if 
		(key.equals("tdt.utc"))
		return tdt_utc;
	    else if 
		(key.equals("polar.motion.x"))
		return polarMotion_X;
	    else if 
		(key.equals("polar.motion.y"))
		return polarMotion_Y;
	    else if 
		(key.equals("airmass"))
		return airmass;
	    else if 
		(key.equals("autoguider.wavelength"))
		return agwavelength;
	    else
		throw new IllegalArgumentException("TCS_Status:Astrometry: Unknown status key: "+key);	    
	}
	
	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException { 
	    throw new IllegalArgumentException("TCS_Status:Astrometry: Unknown status key: "+key);	    
	}
	

    }

    /** Holds the Autoguider data.*/
    public static class Autoguider  extends Segment {

	/** Records the current autoguider selected.*/
	public String agSelected;
	
	/** Records the current autoguider status.*/
	public int agStatus;

	/** Records the current autoguider software state.*/
	public int agSwState;
		
	/** NOT AVAILABLE FROM RGO TCS.*/
	public int agMode;
	
	/** Records the current guide star magnitude.*/
	public double guideStarMagnitude;
	
	/** Records the current autoguider FWHM (guide-pixels).*/
	public double fwhm;
	
	/** Records the current autoguider mirror demand position (mm).*/
	public double agMirrorDemand;
	
	/** Records the current autoguider mirror position (mm).*/
	public double agMirrorPos;
	
	/** Records the current autoguider mirror status.*/
	public int    agMirrorStatus;
	
	/** Records the current autoguider focus demand position (mm).*/
	public double agFocusDemand;
	
	/** Records the current autoguider focus position (mm).*/
	public double agFocusPos;
	
	/** Records the current autoguider focus status.*/
	public int    agFocusStatus;
	
	/** Records the current autoguider filter demand.*/
	public int agFilterDemand;
	
	/** Records the current autoguider filter position.*/
	public int agFilterPos;

	/** Records the current autoguider filter status.*/
	public int agFilterStatus;

	/** Returns a readable version of the TCS_Status$Segment .*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("Autoguider: "+sdf.format(new Date(this.timeStamp))+"(");  
	    buffer.append("Selected="+       agSelected);
	    buffer.append(", Status="+       codeString(agStatus));
	    buffer.append(", SwState="+      codeString(agSwState));
	    buffer.append(", Mode="+         codeString(agMode));
	    buffer.append(", GuideMag="+     guideStarMagnitude);
	    buffer.append(", Fwhm="+         fwhm);
	    buffer.append(", MirrorDemand="+ agMirrorDemand);
	    buffer.append(", MirrorPos="+    agMirrorPos);
	    buffer.append(", MirrorStatus="+ codeString(agMirrorStatus));
	    buffer.append(", FocusDemand="+  agFocusDemand);
	    buffer.append(", FocusPos="+     agFocusPos);
	    buffer.append(", FocusStatus="+  codeString(agFocusStatus));
	    buffer.append(", FilterDemand="+ codeString(agFilterDemand));
	    buffer.append(", FilterPos="+    codeString(agFilterPos));
	    buffer.append(", FilterStatus="+ codeString(agFilterStatus));	  
	    buffer.append(")");  
	    return buffer.toString();
	}

	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {
	    if (key.equals("autoguider.selected"))
		return agSelected;	   
	    else
		throw new IllegalArgumentException("TCS_Status:Autoguider: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {
	    if 
		(key.equals("guide.star.magnitude"))
		return guideStarMagnitude;
	    else if 
		(key.equals("autoguider.fwhm"))
		return fwhm;
	    else if 
		(key.equals("autoguider.mirror.demand"))
		return agMirrorDemand;
	    else if 
		(key.equals("autoguider.mirror.position"))
		return agMirrorPos;	   
	    else if 
		(key.equals("autoguider.focus.demand"))
		return agFocusDemand;
	    else if 
		(key.equals("autoguider.focus.position"))
		return agFocusPos;	   
	    else
		throw new IllegalArgumentException("TCS_Status:Autoguider: Unknown status key: "+key);	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException {
	    if 
		(key.equals("autoguider.status"))
		return agStatus;
	    else if 
		(key.equals("autoguider.software.state"))
		return agSwState;
	    else if 
		(key.equals("autoguider.mode"))		
		return agMode; 
	    else if 
		(key.equals("autoguider.mirror.status"))
		return agMirrorStatus;	 
	    else if 
		(key.equals("autoguider.focus.status"))
		return agFocusStatus;
	    else if 
		(key.equals("autoguider.filter.demand"))
		return agFilterDemand;
	    else if 
		(key.equals("autoguider.filter.position"))
		return agFilterPos; 
	    else if 
		(key.equals("autoguider.filter.status"))
		return agFilterStatus;
	    else
		throw new IllegalArgumentException("TCS_Status:Autoguider: Unknown status key: "+key);	    
	}
	
    }
    
    /** Holds the Calibrate data.*/
    public static class Calibrate  extends Segment {

	// Defaults.
	
	/** Records the default Azimuth error (arcsec).*/
	public double defAzError;
	
	/** Records the default Altitude error (arcsec).*/
	public double defAltError;
	
	/** Records the default collimator error (arcsec).*/
	public double defCollError;
	
	// Current.

	/** Records the current Azimuth error (arcsec).*/
	public double currAzError;
	
	/** Records the current Altitude error (arcsec).*/
	public double currAltError;
	
	/** Records the current Collimator error (arcsec).*/
	public double currCollError;
    
	// Last.

	/** Records the last Azimuth error (arcsec).*/
	public double lastAzError;
	
	/** Records the last Azimuth rms (arcsec).*/
	public double lastAzRms;
	
	/** Records the last Altitude error (arcsec).*/
	public double lastAltError;
	
	/** Records the last Altitude rms (arcsec).*/
	public double lastAltRms;
	
	/** Records the last Collimator error (arcsec).*/
	public double lastCollError;
	
	/** Records the last Collimator rms (arcsec).*/
	public double lastCollRms;

	/** Records the last Sky sigma (arcsec).*/
	public double lastSkyRms;
	
	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {	   
		throw new IllegalArgumentException("TCS_Status:Calibrate: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {
	    if (key.equals("default.azimuth.error"))
		return defAzError;
	    else if 
		(key.equals("default.altitude.error"))
		return defAltError;
	    else if 
		(key.equals("default.collimator.error"))
		return defCollError;
	    else if 
		(key.equals("current.azimuth.error"))
		return currAzError;
	    else if 
		(key.equals("current.altitude.error"))
		return currAltError;
	    else if 
		(key.equals("current.collimator.error"))
		return currCollError;
	    else if 
		(key.equals("last.azimuth.error"))
		return lastAzError;
	    else if 
		(key.equals("last.azimuth.rms"))
		return lastAzRms;
	    else if 
		(key.equals("last.altitude.error"))
		return lastAltError;
	    else if 
		(key.equals("last.altitude.rms"))
		return lastAltRms;
	    else if 
		(key.equals("last.collimator.error"))
		return lastCollError;
	    else if 
		(key.equals("last.collimator.rms"))
		return lastCollRms;
	    else if 
		(key.equals("last.sky.rms"))
		return lastSkyRms;
	    else
		throw new IllegalArgumentException("TCS_Status:Calibrate: Unknown status key: "+key);
	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException {
		throw new IllegalArgumentException("TCS_Status:Calibrate: Unknown status key: "+key);	    
	}
	
    }

    /** Holds the FocalStation data.*/
    public static class FocalStation  extends Segment {

	/** Records the current instrument station id.*/
	public String station;
	
	/** Records the current instrument name.*/
	public String instr;
	
	/** Records the current autoguider name.*/
	public String ag;
	
	/** Returns a readable version of the TCS_Status$Segment .*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("Focal: "+sdf.format(new Date(this.timeStamp))+"(");  
	    buffer.append("Station="+ station);
	    buffer.append(", Instr="+ instr);
	    buffer.append(", Ag="+    ag);
	    buffer.append(")");  
	    return buffer.toString();
	}

	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {
	    if (key.equals("station"))
		return station;	
	    else if 
		(key.equals("instrument"))
		return instr; 
	    else if 
		(key.equals("autoguider"))
		return ag; 
	    else
		throw new IllegalArgumentException("TCS_Status:FocalStation: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {	   
		throw new IllegalArgumentException("TCS_Status:FocalStation: Unknown status key: "+key);	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException {	   
		throw new IllegalArgumentException("TCS_Status:FocalStation: Unknown status key: "+key);	    
	}
	
    }

    /** Holds the Limits data.*/
    public static class Limits  extends Segment {
	
	/** Records the Azimuth positive limit (degrees).*/
	public double azPosLimit;
	
	/** Records the Azimuth negative limit (degrees).*/
	public double azNegLimit;
	
	/** Records the Altitude positive limit (degrees).*/
	public double altPosLimit;
	
	/** Records the Altitude negative limit (degrees).*/
	public double altNegLimit;
	
	/** Records the Rotator positive limit (degrees).*/
	public double rotPosLimit;
	
	/** Records the Rotator negative limit (degrees).*/
	public double rotNegLimit;	
	
	/** Records the time to Azimuth limit (secs).*/
	public double timeToAzLimit;
	
	/** Records the Azimuth limit sense.*/
	public int    azLimitSense;

	/** Records the time to Altitude limit (secs).*/
	public double timeToAltLimit;
	
	/** Records the Altitude limit sense.*/
	public int    altLimitSense;
	
	/** Records the time to Rotator limit (secs).*/
	public double timeToRotLimit;
	
	/** Records the Rotator limit sense.*/
	public int    rotLimitSense;
	
	/** Returns a readable version of the TCS_Status$Segment .*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("Limits: "+sdf.format(new Date(this.timeStamp))+"(");  
	    buffer.append("AzPosLimit="+       azPosLimit);
	    buffer.append(", AzNegLimit="+     azNegLimit+     "(degs)");
	    buffer.append(", AltPosLimit="+    altPosLimit+    "(degs)");
	    buffer.append(", AltNegLimit="+    altNegLimit+    "(degs)");
	    buffer.append(", RotPosLimit="+    rotPosLimit+    "(degs)");
	    buffer.append(", RotNegLimit="+    rotNegLimit+    "(degs)");
	    buffer.append(", TimeToAzLimit="+  (timeToAzLimit/3600)+  "(hrs)");
	    buffer.append(", AzLImitSense="+   codeString(azLimitSense));
	    buffer.append(", TimeToAltLimit="+ (timeToAltLimit/3600)+ "(hrs)");
	    buffer.append(", AltLimitSense="+  codeString(altLimitSense));
	    buffer.append(", TimeToRotLimit="+ (timeToRotLimit/3600)+ "(hrs)");
	    buffer.append(", RotLimitSense="+  codeString(rotLimitSense));
	    buffer.append(")");  
	    return buffer.toString();
	} 
	
	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {	
		throw new IllegalArgumentException("TCS_Status:Limits: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {
	    if (key.equals("azimuth.positive.limit"))
		return azPosLimit;
	    else if 
		(key.equals("azimuth.negative.limit"))
		return azNegLimit;
	    else if 
		(key.equals("altitude.positive.limit"))
		return altPosLimit;
	    else if 
		(key.equals("altitude.negative.limit"))
		return altNegLimit;
	    else if 
		(key.equals("rotator.positive.limit"))
		return rotPosLimit;
	    else if 
		(key.equals("rotator.negative.limit"))
		return rotNegLimit;
	    else if 
		(key.equals("azimuth.limit.time"))
		return timeToAzLimit;
	    else if 
		(key.equals("altitude.limit.time"))
		return timeToAltLimit;
	    else if 
		(key.equals("rotator.limit.time"))
		return timeToRotLimit; 	  
	    else
		throw new IllegalArgumentException("TCS_Status:Limits: Unknown status key: "+key);	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException {
	    if (key.equals("azimuth.limit.sense"))
		return azLimitSense;
	    else if 
		(key.equals("altitude.limit.sense"))
		return altLimitSense;
	    else if 
		(key.equals("rotator.limit.sense"))
		return rotLimitSense;	 
	    else
		throw new IllegalArgumentException("TCS_Status:Limits: Unknown status key: "+key);	    
	}
	
    }
    
    /** Holds the Mechanisms data.*/
    public static class Mechanisms  extends Segment {

	// --------------
	// Azimuth drive.
	// --------------
	
	/** Records the Azimuth drive id.*/
	public String azName;
	
	/** Records the Azimuth demand position (degrees).*/
	public double azDemand;
	
	/** Records the current Azimuth position (degrees).*/
	public double azPos;
	
	/** Records the current Azimuth status.*/
	public int azStatus = MOTION_OFF_LINE;
	
	// ---------------
	// Altitude drive.
	// ---------------

	/** Records the Altitude drive id.*/
	public String altName;
	
	/** Records the Altitude demand position (degrees).*/
	public double altDemand;
	
	/** Records the current Altitude position (degrees).*/
	public double altPos;
	
	/** Records the current Altitude status.*/
	public int altStatus = MOTION_OFF_LINE;
	
	// --------------
	// Rotator drive.
	// --------------

	/** Records the Rotator id.*/
	public String rotName;
	
	/** Records the Rotator demand position (degrees).*/
	public double rotDemand;
	
	/** Records the current Rotator position (degrees).*/
	public double rotPos;
	
	/** Records the Rotator mode.*/
	public int rotMode;
	
	/** Records the Rotator sky position angle (degrees).*/
	public double rotSkyAngle;
	
	/** Records the Rotator status.*/
	public int rotStatus = MOTION_OFF_LINE;
	
	// ----------
	// Enclosure.
	// ----------

	/** Records the Enclosure shutter (1) id (e.g. SOUTH).*/
	public String encShutter1Name; 
	
	/** Records the Enclosure shutter (1) demand position.*/
	public int encShutter1Demand;
	
	/** Records the Enclosure shutter (1) position.*/
	public int encShutter1Pos;

	/** Records the current Enclosure (1) shutter status.*/
	public int encShutter1Status;

	/** Records the Enclosure shutter (2) id (e.g. NORTH).*/
	public String encShutter2Name; 
	
	/** Records the Enclosure shutter (2) demand position.*/
	public int encShutter2Demand;
	
	/** Records the Enclosure shutter (2) position.*/
	public int encShutter2Pos;

	/** Records the current Enclosure (2) shutter status.*/
	public int encShutter2Status;

	// ------------
	// Fold Mirror.
	// ------------


	/** Records the Fold mirror id.*/
	public String foldMirrorName;
	
	/** Records the Fold mirror demand position.*/
	public int foldMirrorDemand;

	/** Records the Fold mirror position.*/
	public int foldMirrorPos;
	
	/** Records the current Fold mirror.*/
	public int foldMirrorStatus;
	
	// ---------------
	// Primary Mirror.
	// ---------------
 
	/** Records the Primary mirror cover id.*/
	public String primMirrorName;
	
	/** Records the Primary mirror cover demand position.*/
	public int primMirrorCoverDemand;

	/** Records the Primary mirror cover position.*/
	public int primMirrorCoverPos;
	
	/** Records the current Primary mirror cover status.*/
	public int primMirrorCoverStatus;
	
	// -------------------------
	// Secondary (Focus) Mirror.
	// -------------------------

	/** Records the Secondary mirror id.*/
	public String secMirrorName;
	
	/** Records the Secondary (Focus) mirror demand position (mm).*/
	public double secMirrorDemand;
	
	/** Records the current Secondary (Focus) mirror position (mm).*/
	public double secMirrorPos;
	
	/** Records the current Focus offset (mm).*/
	public double focusOffset;
	
	/** Records the Secondary mirror status.*/
	public int secMirrorStatus;
	
	// ----------------------
	// Primary Mirror System.
	// ----------------------

	/** Records the Primary mirror system id.*/
	public String primMirrorSysName;
	
	/** Records the current Primary mirror system status.*/
	public int primMirrorSysStatus;
    
	/** Returns a readable version of the TCS_Status.*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("Mechanisms: "+sdf.format(new Date(this.timeStamp))+"(");
	    buffer.append("\n Azimuth drive id:                      "+ azName);	
	    buffer.append("\n  Azimuth demand position:              "+ azDemand+" degrees.");	
	    buffer.append("\n  Current Azimuth position:             "+ azPos+" degrees.");	
	    buffer.append("\n  Current Azimuth status:               "+ codeString(azStatus)+"/"+azStatus);
	    
	    buffer.append("\n Altitude drive id:                     "+ altName);	
	    buffer.append("\n  Altitude demand position:             "+ altDemand+" degrees.");	
	    buffer.append("\n  Current Altitude position:            "+ altPos+" degrees.");	
	    buffer.append("\n  Current Altitude status:              "+ codeString(altStatus)+"/"+altStatus);
	    
	    buffer.append("\n Rotator id:                            "+ rotName);	
	    buffer.append("\n  Rotator demand position:              "+ rotDemand+" degrees.");	
	    buffer.append("\n  Current Rotator position:             "+ rotPos+" degrees.");	
	    buffer.append("\n  Rotator mode:                         "+ codeString(rotMode));	
	    buffer.append("\n  Rotator sky position angle:           "+ rotSkyAngle+" degrees.");	
	    buffer.append("\n  Rotator status:                       "+ codeString(rotStatus)+"/"+rotStatus);
	    
	    buffer.append("\n Enclosure shutter 1 id:                "+ encShutter1Name);	
	    buffer.append("\n  Enclosure shutter 1 demand position:  "+ codeString(encShutter1Demand));	
	    buffer.append("\n  Enclosure shutter 1 current position  "+ codeString(encShutter1Pos));	 
	    buffer.append("\n  Current Enclosure 1 shutter status:   "+ codeString(encShutter1Status)+"/"+encShutter1Status);
	    
	    buffer.append("\n Enclosure shutter 2 id:                "+ encShutter2Name);	
	    buffer.append("\n  Enclosure shutter 2 demand position:  "+ codeString(encShutter2Demand));	
	    buffer.append("\n  Enclosure shutter 2 current position  "+ codeString(encShutter2Pos));	 
	    buffer.append("\n  Current Enclosure 2 shutter status:   "+ codeString(encShutter2Status)+"/"+encShutter2Status);
	    
	    buffer.append("\n Fold mirror id:                        "+ foldMirrorName);	
	    buffer.append("\n  Fold mirror demand position:          "+ codeString(foldMirrorDemand));	
	    buffer.append("\n  Fold mirror current position:         "+ codeString(foldMirrorPos));	
	    buffer.append("\n  Current Fold mirror:                  "+ codeString(foldMirrorStatus));
	    
	    buffer.append("\n Primary mirror cover id:               "+ primMirrorName);	
	    buffer.append("\n  Primary mirror cover demand position: "+ codeString(primMirrorCoverDemand));
	    buffer.append("\n  Primary mirror cover current position:"+ codeString(primMirrorCoverPos));
	    buffer.append("\n  Current Primary mirror cover status:  "+ codeString(primMirrorCoverStatus)+"/"+primMirrorCoverStatus);
	    
	    buffer.append("\n Secondary mirror id:                   "+ secMirrorName);	
	    buffer.append("\n  Secondary mirror demand position:     "+ secMirrorDemand+" mm.");	
	    buffer.append("\n  Current Secondary mirror position:    "+ secMirrorPos+" mm.");	
	    buffer.append("\n  Current Focus offset:                 "+ focusOffset);	
	    buffer.append("\n  Secondary mirror status:              "+ codeString(secMirrorStatus));
	    
	    buffer.append("\n Primary mirror system id:              "+ primMirrorSysName);	
	    buffer.append("\n  Current Primary mirror system status: "+ codeString(primMirrorSysStatus));
	    buffer.append(")");
	    return buffer.toString();
	} 
	
	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {
	    if (key.equals("azimuth.node.name"))
		return azName;
	     else if 
		(key.equals("altitude.node.name"))
		return altName;
	    else if 
		(key.equals("rotator.node.name"))
		return rotName;
	    else if 
		(key.equals("enclosure.shutter.1.name"))
		return encShutter1Name;
	    else if 
		(key.equals("enclosure.shutter.2.name"))
		return encShutter2Name;
	    else if 
		(key.equals("fold.mirror.name"))
		return foldMirrorName;
	    else if 
		(key.equals("primary.mirror.name"))
		return primMirrorName;
	    else if 
		(key.equals("secondary.mirror.name"))
		return secMirrorName;
	    else if 
		(key.equals("primary.mirror.system.name"))
		return primMirrorSysName;	  
	    else
		throw new IllegalArgumentException("TCS_Status:Mechanisms: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {
	    if (key.equals("azimuth.demand"))
		return  azDemand;
	    else if 
		(key.equals("azimuth.position"))
		return  azPos;
	    else if 
		(key.equals("altitude.demand"))
		return altDemand;
	    else if 
		(key.equals("altitude.position"))
		return altPos;
	    else if 
		(key.equals("rotator.demand"))
		return rotDemand;
	    else if 
		(key.equals("rotator.position"))
		return rotPos;
	    else if 
		(key.equals("rotator.sky.angle"))
		return rotSkyAngle;
	    else if 
		(key.equals("secondary.mirror.demand"))
		return secMirrorDemand;
	    else if 
		(key.equals("secondary.mirror.position"))
		return secMirrorPos;
	    else if 
		(key.equals("focus.offset"))
		return focusOffset; 	   
	    else
		throw new IllegalArgumentException("TCS_Status:Mechanisms: Unknown status key: "+key);	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException {
	    if (key.equals("azimuth.status"))
		return azStatus;
	    else if 
		(key.equals("altitude.status"))
		return altStatus;
	    else if 
		(key.equals("rotator.status"))
		return rotStatus;
	    else if 
		(key.equals("rotator.mode"))
		return rotMode;
	    else if 
		(key.equals("enclosure.shutter.1.status"))
		return encShutter1Status;
	    else if 
		(key.equals("enclosure.shutter.1.demand"))
		return encShutter1Demand;
	    else if 
		(key.equals("enclosure.shutter.1.position"))
		return encShutter1Pos; 
	    else if 
		(key.equals("enclosure.shutter.2.status"))
		return encShutter2Status;
	    else if 
		(key.equals("enclosure.shutter.2.demand"))
		return encShutter2Demand;
	    else if
		(key.equals("enclosure.shutter.2.position"))
		return encShutter2Pos;
	    else if 
		(key.equals("fold.mirror.status"))
		return foldMirrorStatus;
	    else if 
		(key.equals("fold.mirror.demand"))
		return foldMirrorDemand;
	    else if 
		(key.equals("fold.mirror.position"))
		return foldMirrorPos; 
	    else if 
		(key.equals("primary.mirror.cover.status"))
		return primMirrorCoverStatus;
	    else if 
		(key.equals("primary.mirror.cover.demand"))
		return primMirrorCoverDemand;
	    else if 
		(key.equals("primary.mirror.cover.position"))
		return primMirrorCoverPos;
	    else if 
		(key.equals("secondary.mirror.system.status"))
		return secMirrorStatus; 
	    else if 
		(key.equals("primary.mirror.system.status"))
		return primMirrorSysStatus; 	  
	    else
		throw new IllegalArgumentException("TCS_Status:Mechanisms: Unknown status key: "+key);	    
	}
    }

    /** Holds the Meteorology data.*/
    public static class Meteorology  extends Segment {
	
	/** Records the WMS status.*/
	public int wmsStatus;

	/** Records the external rain state.*/
	public int rainState = RAIN_ALERT;

	/** Records the fraction of moisture covering the rain-sensor.*/
	public double moistureFraction = 1.0;

	/** Records the Truss temperature. (K)*/
	public double serrurierTrussTemperature;

	/** Records the oil temperature. (K)*/
	public double oilTemperature;

	/** Records the Primary Mirror temperature. (K)*/
	public double primMirrorTemperature;
	
	/** Records the Secondary (Focus) Mirror temperature. (K)*/
	public double secMirrorTemperature;

	/** Records the A&G Box temperature. (K)*/
	public double agBoxTemperature;

	/** Records the external air temperature (K).*/
	public double extTemperature = -99.0;
		
	/** Records the external wind speed (m/s).*/
	public double windSpeed = 99.99;
    	
	/** Records the external air pressure (mBar).*/
	public double pressure;

	/** Records the external humidity (fraction).*/
	public double humidity = 1.0;
	
	/** Records the external wind direction (degrees c/w North !!).*/
	public double windDirn;

	/** Records the Dew-point temperature. (K).*/
	public double dewPointTemperature = 0.0;

	/** Records the light level (W/m2). ##INSTRUMENT NOT YET IMPLEMENTED##*/
	public double lightLevel;

	/** Records the Cloud amount (?). ##INSTRUMENT NOT YET IMPLEMENTED##*/
	public double cloud;

	/** Records the Dust amount (?).##INSTRUMENT NOT YET IMPLEMENTED##*/
	public double dust;

	/** Returns a readable version of the TCS_Status$Segment .*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("Meteorology: "+sdf.format(new Date(this.timeStamp))+"(");  
	    buffer.append("WMSStatus="+        codeString(wmsStatus)+"/"+wmsStatus);
	    buffer.append(", Rain="+           codeString(rainState)+"/"+rainState);
	    buffer.append(", Moisture="+       moistureFraction);
	    buffer.append(", Truss Temp="+     serrurierTrussTemperature+" (\u0B00"+"C)");
	    buffer.append(", Oil Temp="+       oilTemperature+           " (\u0B00"+"C)");
	    buffer.append(", Prim Mirr Temp="+ primMirrorTemperature+    " (\u0B00"+"C)");
	    buffer.append(", Sec Mirr Temp="+  secMirrorTemperature+     " (\u0B00"+"C)");
	    buffer.append(", Ag Box Temp="+    agBoxTemperature+         " (\u0B00"+"C)");
	    buffer.append(", Ext Temp="+       extTemperature+           " (\u0B00"+"C)");
	    buffer.append(", Dew Point="+      dewPointTemperature+      " (\u0B00"+"C)");
	    buffer.append(", Wind Speed="+     windSpeed+                " (m/s)");
	    buffer.append(", Pressure="+       pressure+                 " (kPa)");
	    buffer.append(", Humidity="+       humidity*100+             " (%)");
	    buffer.append(", Wind Dirn="+      windDirn+                 " (\u0B00)");
	    buffer.append(", Light="+          lightLevel+               " (?)");
	    buffer.append(", Cloud="+          cloud+                    " (?)");
	    buffer.append(", Dust="+           dust+                     " (?)");
	    buffer.append(")");
	    return buffer.toString();
	}

	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {	
	    throw new IllegalArgumentException("TCS_Status:Meteorology: status key: "+key);	
	}
	
	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {
	    if 
		(key.equals("moisture"))
		return moistureFraction;
	    else if 
		(key.equals("truss.temperature"))
		return serrurierTrussTemperature;
	    else if 
		(key.equals("oil.temperature"))
		return oilTemperature;
	    else if 
		(key.equals("primary.mirror.temperature"))
		return primMirrorTemperature;
	    else if 
		(key.equals("secondary.mirror.temperature"))
		return secMirrorTemperature;
	    else if
		(key.equals("ag.box.temperature"))
		return agBoxTemperature;
	    else if 
		(key.equals("external.temperature"))
		return extTemperature;
	    else if 
		(key.equals("dew.point.temperature"))
		return dewPointTemperature;
	    else if 
		(key.equals("wind.speed"))
		return windSpeed;
	    else if 
		(key.equals("pressure"))
		return pressure;
	    else if 
		(key.equals("humidity"))
		return humidity;
	    else if 
		(key.equals("wind.direction"))
		return windDirn;
	    else if 
		(key.equals("light"))
		return lightLevel;
	    else if 
		(key.equals("cloud"))
		return cloud; 
	    else if 
		(key.equals("dust"))
		return dust;
	    else
		throw new IllegalArgumentException("TCS_Status:Meteorology: Unknown status key: "+key);
	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException {
	    if (key.equals("rain"))
		return rainState;	   
	    else if 
		(key.equals("wms.status"))
		return wmsStatus;	   
	    else
		throw new IllegalArgumentException("TCS_Status:Meteorology: Unknown status key: "+key);	    
	}
	
    }

    /** Holds the Services data.*/
    public static class Services  extends Segment {
	
	/** Records the state of the power system.*/
	public int powerState;

	/** Returns a readable version of the TCS_Status$Segment .*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("Services: "+sdf.format(new Date(this.timeStamp))+"(");  
	    buffer.append("PowerState="+ codeString(powerState));	
	    buffer.append(")");
	    return buffer.toString();
	}
	
	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {	   
		throw new IllegalArgumentException("TCS_Status:Services: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {
		throw new IllegalArgumentException("TCS_Status:Services: Unknown status key: "+key);	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException {
	    if (key.equals("power.state"))
		return powerState;	   
	    else
		throw new IllegalArgumentException("TCS_Status:Services: Unknown status key: "+key);
	    
	}
	

    }

    /** Holds the Source data.*/
    public static class SourceBlock  extends Segment {

	/** Records the current Source name.*/
	public String srcName;
	
	/** Records the current Source RA (degrees).*/
	public double srcRa;
	
	/** Records the current Source Declination (degrees).*/
	public double srcDec;
	
	/** Records the current Source equinox (Letter).*/
	public String srcEquinoxLetter;

	/** Records the current Source equinox (year).*/
	public double srcEquinox;
	
	/** Records the current Source epoch (year).*/
	public double srcEpoch;
	
	/** Records the current Source proper motion RA (sec/year).*/
	public double srcPmRA;
	
	/** Records the current Source proper motion declination (arcsec/year).*/
	public double srcPmDec;

	/** Records the current Source non-sidereal tracking rate in RA (sec/year).*/
	public double srcNsTrackRA;

	/** Records the current Source non-sidereal tracking rate in Dec (arcsec/year).*/
	public double srcNsTrackDec;

	/** Records the current Source parallax (arcsec).*/
	public double srcParallax;
	
	/** Records the current Source radial velocity (km/sec).*/
	public double srcRadialVelocity;

	/** Records the actual RA of the source - offsets included.*/
	public double srcActRa;

	/** Records the actual Dec of the source - offsets included.*/
	public double srcActDec;


	/** Returns a readable version of the TCS_Status$Segment .*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("Source: "+sdf.format(new Date(this.timeStamp))+"(");  
	    buffer.append("Name="+          srcName);	
	    buffer.append(", RA="+           Position.toHMSString(Math.toRadians(srcRa)));	
	    buffer.append(", Dec="+          Position.toDMSString(Math.toRadians(srcDec)));	
	    buffer.append(", Equinox="+      srcEquinoxLetter+srcEquinox);	
	    buffer.append(", Epoch="+        srcEpoch);
	    buffer.append(", NS-Track RA="+  srcNsTrackRA+     " (sec/year)");
	    buffer.append(", NS-Track Dec="+ srcNsTrackDec+    " (arcsec/year)");
	    buffer.append(", PM RA="+        srcPmRA+          " (sec/year)");	
	    buffer.append(", PM Dec="+       srcPmDec+         " (arcsec/year)");	
	    buffer.append(", Parallax="+     srcParallax+      " (arcsec)");
	    buffer.append(", Rad Vel="+      srcRadialVelocity+" (km/s)");
	    buffer.append(", ActRA="+           Position.toHMSString(Math.toRadians(srcActRa)));
            buffer.append(", ActDec="+          Position.toDMSString(Math.toRadians(srcActDec)));    
	    buffer.append(")");
	    return buffer.toString();
	}

	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {
	    if (key.equals("name"))
		return srcName;	 
	    else if 
		(key.equals("equinox.letter"))
		return srcEquinoxLetter;
	    else
		throw new IllegalArgumentException("TCS_Status:Source: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {
	    if (key.equals("ra"))
		return srcRa;
	    else if 
		(key.equals("dec"))
		return srcDec;
	    else if 
		(key.equals("equinox"))
		return srcEquinox;
	    else if 
		(key.equals("epoch"))
		return srcEpoch ;
	    else if 
		(key.equals("ns.tracking.ra"))
		return srcNsTrackRA;
	    else if 
		(key.equals("ns.tracking.dec"))
		return srcNsTrackDec;
	    else if 
		(key.equals("proper.motion.ra"))
		return srcPmRA;
	    else if 
		(key.equals("proper.motion.dec"))
		return srcPmDec;
	    else if 
		(key.equals("parallax"))
		return srcParallax;
	    else if 
		(key.equals("radial.velocity"))
		return srcRadialVelocity;
	    else if
		(key.equals("act.ra"))
		return srcActRa;
	    else if
		(key.equals("act.dec"))
		return srcActDec;
	    else
		throw new IllegalArgumentException("TCS_Status:Source: Unknown status key: "+key);
	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException { 
		throw new IllegalArgumentException("TCS_Status:Source: Unknown status key: "+key);	    
	}
	

    }

    /** Holds the State data.*/
    public static class State  extends Segment {

	/** Records the current Network Control Interface state.*/
	public int networkControlState;
	
	/** Records the current Engineering Override state.*/
	public int engineeringOverrideState;
	
	/** Records the current telescope state.*/
	public int telescopeState;
	
	/** Records TCS state.*/
	public int tcsState;

	/** Set True if a system-wide RESTART is imminent.*/
	public boolean systemRestartFlag;

	/** Set True if a system-wide SHUTDOWN is imminent.*/
	public boolean systemShutdownFlag;
	
	/** Returns a readable version of the TCS_Status$Segment .*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("State: "+sdf.format(new Date(this.timeStamp))+"(");  	  
	    buffer.append("NetControl="+           codeString(networkControlState));
	    buffer.append(", EngOverride="+        codeString(engineeringOverrideState));
	    buffer.append(", SysState="+           codeString(telescopeState));
	    buffer.append(", TCSState="+           codeString(tcsState));
	    buffer.append(", Restart="+            systemRestartFlag);
	    buffer.append(", Shutdown="+           systemShutdownFlag);
	    buffer.append(")");
	    return buffer.toString();
	}
	
	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {	   
		throw new IllegalArgumentException("TCS_Status:State: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException { 
		throw new IllegalArgumentException("TCS_Status:State: Unknown status key: "+key);	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException {
	    if (key.equals("network.control.state"))
		return networkControlState;
	    else if 
		(key.equals("engineering.override.state"))
		return engineeringOverrideState;
	    else if 
		(key.equals("system.state"))
		return telescopeState;
	    else if 
		(key.equals("tcs.state"))
		return tcsState;
	    else if 
		(key.equals("power.restart"))
		return (systemRestartFlag ? 1 : 0);
	    else if 
		(key.equals("power.shutdown"))
		return (systemShutdownFlag ? 1 : 0);	   
	    else
		throw new IllegalArgumentException("TCS_Status:State: Unknown status key: "+key);	    
	}
	
    }

    /** Holds the Version data.*/
    public static class Version  extends Segment {

	/** Records the TCS version number.*/
	public String tcsVersion;
    
	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {
	    if (key.equals("tcs.version"))
		return tcsVersion;	   
	    else
		throw new IllegalArgumentException("TCS_Status:Version: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {
	    throw new IllegalArgumentException("TCS_Status:Version: status key: "+key);	
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException {
	    throw new IllegalArgumentException("TCS_Status:Version: status key: "+key);	
	}
	

    }

    /** Holds the Time data.*/
    public static class Time  extends Segment {

	/** Records the current time (Modified Julian Date).*/
	public double mjd;
	
	/** Records the current time (UT1) (secs).*/
	public double ut1;
	
	/** Records the current time (Local Sidereal Time) (secs).*/
	public double lst;

	/** Returns a readable version of the TCS_Status$Segment .*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("Time: "+sdf.format(new Date(this.timeStamp))+"(");  
	    buffer.append("MJD="+ mjd);	
	    buffer.append(", UT1="+ ut1+" (secs)");	
	    buffer.append(", LST="+ lst+" (secs)");
	    double sod = ut1 - (Math.floor(mjd)*86400.0);
	    buffer.append(", UT(SOD)="+sod+")");
	    return buffer.toString();
	}

	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {	   
		throw new IllegalArgumentException("TCS_Status:Time: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException {
	    if (key.equals("mjd"))
		return mjd;
	    else if 
		(key.equals("ut1"))
		return ut1;
	    else if 
		(key.equals("lst"))
		return lst;	 
	    else
		throw new IllegalArgumentException("TCS_Status:Time: Unknown status key: "+key);	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException { 
		throw new IllegalArgumentException("TCS_Status:Time: status key: "+key);	    
	}
	

    }

    /** Holds the Network communications data.*/
    public static class Network  extends Segment {
	
	/** Records the current network state.*/
	public int networkState;
	
	/** Returns a readable version of the TCS_Status$Segment .*/
	public String toString() {
	    StringBuffer buffer = new StringBuffer("Network: "+sdf.format(new Date(this.timeStamp))+"(");
	    buffer.append("NetState="+codeString(networkState));
	    buffer.append(")");
	    return buffer.toString();
	}

	/** Returns any String Status entries.*/
	public String getStatusEntryId(String key) throws IllegalArgumentException {	 
		throw new IllegalArgumentException("TCS_Status:Network: status key: "+key);	
	}

	/** Returns any continuous Status entries.*/
	public double getStatusEntryDouble(String key) throws IllegalArgumentException { 
		throw new IllegalArgumentException("TCS_Status:Network: status key: "+key);	    
	}

	/** Returns any discrete Status entries.*/
	public int getStatusEntryInt(String key) throws IllegalArgumentException {
	    if (key.equals("network.state"))
		return networkState;	  
	    else
		throw new IllegalArgumentException("TCS_Status:Network: Unknown status key: "+key);	    
	}

    }
	
}



/** $Log: not supported by cvs2svn $
/** Revision 1.3  2008/08/21 10:30:48  eng
/** *** empty log message ***
/**
/** Revision 1.2  2007/10/17 07:29:27  snf
/** added actual RA and Dec with offsets.
/**
/** Revision 1.1  2006/12/18 11:58:47  snf
/** Initial revision
/**
/** Revision 1.3  2001/01/04 11:19:18  snf
/** Modified the System names.
/**
/** Revision 1.2  2000/12/22 13:34:34  snf
/** Fixed bug.
/**
/** Revision 1.1  2000/12/19 10:36:46  snf
/** Initial revision
/** */
