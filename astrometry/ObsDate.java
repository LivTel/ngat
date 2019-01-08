package ngat.astrometry;

import java.text.*;
import java.util.*;

/** Represents an Observation-Date. 
 * This starts at a given (Local) time on the actual date of creation 
 * and ends at the same (Local) time on the following actual day.
 * Methods are provided to return the (approximate) times of Sunrise, Sunset etc.
 */
public class ObsDate {

    public static SimpleTimeZone UTC = new SimpleTimeZone(0, "UTC");
    
    public static SimpleDateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm z");

    public static SimpleDateFormat udf     = new SimpleDateFormat("HH:mm 'Local'");
    public static SimpleDateFormat adf     = new SimpleDateFormat("HH'H' mm'M'");
    
    public static SimpleDateFormat sdf     = new SimpleDateFormat("DDD");
    
    /** A day in millis.*/
    public static final long ONE_DAY = 24*60*60*1000L;
    
    protected long sunrise;
    
    protected long sunset;
    
    protected long eveningTwilight;

    protected long nauticalEveningTwilight; 
    
    protected long morningTwilight;

    protected long nauticalMorningTwilight;
    
    protected long localNoon;
    
    protected long localMidnight;
    
    protected long startOfDay;
    
    protected int  startOfDayHours;

    protected int  timeZoneOffset;
    
    protected long midnight;

    protected long noon;

    protected double solardec;

    protected boolean twilightWarning;

    /** Create an ObsDate with the specified TimeZone offset and local Hour starting at
     * the specified (local) time. An ObsDate generated for TimeZone +4 and StartOfDayHours 15
     * would be defined to have a start at 1500 Local time which is 4 hours shifted
     * from UTC (i.e. starts at 1100 UTC).
     * @param timeZoneOffset  TimeZone offset of the site in hours from UTC.
     * @param startOfDayHours Local time of day (hour) when ObsDate is defined to start at.
     * @param time The actual time for which to generate an ObsDate.
     */
    public ObsDate(int timeZoneOffset, int startOfDayHours, long now) {
	this.timeZoneOffset  = timeZoneOffset;
	this.startOfDayHours = startOfDayHours;
       
	iso8601.setTimeZone(UTC);
	sdf.setTimeZone(UTC);

	Calendar cal = Calendar.getInstance();	
	cal.setTime(new Date(now));
	cal.setTimeZone(UTC);
	
	int hour = cal.get(Calendar.HOUR_OF_DAY);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE,      0);
	cal.set(Calendar.SECOND,      0);
	cal.set(Calendar.MILLISECOND, 0);
	midnight = cal.getTime().getTime();

	// Use horizon default refraction.
	Position sun = Astrometry.getSolarPosition();
	sunrise  = sun.getRiseTimeToday(Math.toRadians(-0.83333));	
	sunset   = sun.getSetTimeToday(Math.toRadians(-0.83333));
	solardec = sun.getDec();
	
	noon = midnight + 12*3600*1000L;
	
	// Time offset (in millis).
	long offset  = (long)(timeZoneOffset*3600*1000);

	localNoon     = noon - offset;
	localMidnight = localNoon - 12*3600*1000L;
	startOfDay    = localNoon + (long)(startOfDayHours-12)*3600*1000L;

	if (now < startOfDay) {
	    System.err.println("*At "+iso8601.format(new Date(now))+" With SOD "+iso8601.format(new Date(startOfDay))+
			       " It is (pre SOD). Resetting SOD back one day.");
	    startOfDay -= ONE_DAY;	 
	} else {
	    System.err.println("*With SOD "+iso8601.format(new Date(startOfDay))+
			       " It is (After SOD). SOD is OK.");
	}	
	
	// Twilight times.
	if (solardec > (Math.PI/2.0 - Position.phi- Math.toRadians(18.0))) {
	    twilightWarning = true;
	    morningTwilight = localMidnight+60000;
	    eveningTwilight = localMidnight + ONE_DAY-60000;
	} else {
	    morningTwilight = sun.getRiseTimeToday(Math.toRadians(-18.0));
	    eveningTwilight = sun.getSetTimeToday(Math.toRadians(-18.0));	
	}

	nauticalMorningTwilight = sun.getRiseTimeToday(Math.toRadians(-12.0));
	nauticalEveningTwilight = sun.getSetTimeToday(Math.toRadians(-12.0));
	    
	// Recompute SR SS if needed.
	if (sunset < startOfDay) {
	     System.err.println("*Advancing Sunset by one day.");
	     sunset += ONE_DAY;
	}
	if (sunrise < startOfDay) {
	     System.err.println("*Advancing Sunrise by one day.");
	     sunrise += ONE_DAY;
	}
	if (morningTwilight < startOfDay) {
	     System.err.println("*Advancing Morning twilight by one day.");
	     morningTwilight += ONE_DAY;
	}
	if (eveningTwilight < startOfDay) {
	     System.err.println("*Advancing evening twilight by one day.");
	     eveningTwilight += ONE_DAY;
	}
	if (nauticalMorningTwilight < startOfDay) {
	     System.err.println("*Advancing N-Morning twilight by one day.");
	     nauticalMorningTwilight += ONE_DAY;
	}
	if (nauticalEveningTwilight < startOfDay) {
	     System.err.println("*Advancing N-Evening twilight by one day.");
	     nauticalEveningTwilight += ONE_DAY;
	}

	if (sunset > (startOfDay+ONE_DAY)) {
	     System.err.println("*Backing off Sunset by one day.");
	     sunset -= ONE_DAY;
	}
	if (sunrise > (startOfDay+ONE_DAY)) {
	     System.err.println("*Backing off Sunrise by one day.");
	     sunrise -= ONE_DAY;
	}
	if (morningTwilight > (startOfDay+ONE_DAY)) {
	     System.err.println("*Backing off Morning twilight by one day.");
	     morningTwilight -= ONE_DAY;
	}
	if (eveningTwilight > (startOfDay+ONE_DAY)) {
	    System.err.println("*Backing off evening twilight by one day.");
	    eveningTwilight -= ONE_DAY;
	}
	if (nauticalMorningTwilight > (startOfDay+ONE_DAY)) {
	     System.err.println("*Backing off N-Morning twilight by one day.");
	     nauticalMorningTwilight -= ONE_DAY;
	}
	if (nauticalEveningTwilight > (startOfDay+ONE_DAY)) {
	     System.err.println("*Backing off N-Evening twilight by one day.");
	     nauticalEveningTwilight -= ONE_DAY;
	}
		
    }

    /** Create an ObsDate with the specified TimeZone offset and local Hour start for
     * the current time of day. An ObsDate generated for TimeZone +4 and StartOfDayHours 15
     * would be defined to have a start at 1500 Local time which is 4 hours shifted
     * from UTC (i.e. starts at 1100 UTC).
     * @param timeZoneOffset  TimeZone offset of the site in hours from UTC.
     * @param startOfDayHours Local time of day (hour) when ObsDate is defined to start at.  
     */
    public ObsDate(int timeZoneOffset, int startOfDayHours) {
	this(timeZoneOffset, startOfDayHours, System.currentTimeMillis());
    }

    /** Returns the time of Sunset.*/
    public long getSunset() {  return sunset; }
    
    /** Returns the time of Sunrise.*/
    public long getSunrise() { return sunrise; }
    
    /** Returns the time of Evening Twilight.*/
    public long getEveningTwilight() { return eveningTwilight; }
    
    /** Returns the time of Morning Twilight.*/
    public long getMorningTwilight() { return morningTwilight; }

    /** Returns the time of Nautical Evening Twilight.*/
    public long getNauticalEveningTwilight() { return nauticalEveningTwilight; }
    
    /** Returns the time of Nautical Morning Twilight.*/
    public long getNauticalMorningTwilight() { return nauticalMorningTwilight; }
    
    /** Returns the time of Local Noon.*/
    public long getLocalNoon() { return localNoon; }
    
    /** Returns the time of Local Midnight.*/
    public long getLocalMidnight() { return localMidnight; }
    
    /** Returns the time of UTC Noon.*/
    public long getNoon() {  return noon; }
    
    /** Returns the time of UTC Midnight.*/
    public long getMidnight() { return midnight; }

    /** Returns the Hour of Day for the start time of this ObsDate.*/
    public int  getStartOfDayHours() { return startOfDayHours; }
    
    /** Returns the Start time for this ObsDate.*/
    public long getStartOfDay() { return startOfDay; }

    /** Returns true if it is (PRE_NIGHT) i.e. between Start-of-day and Sunset.*/
    public boolean isPreNight(long time) { return ((time > startOfDay) && (time < sunset)); }

    /** Returns true if it is (MORNING_TWILIGHT) i.e. between Morning-twilight and Sunrise).*/
    public boolean isMorningTwilight(long time) { return ((time > morningTwilight) && (time < sunrise)); }

    /** Returns true if it is (EVENING_TWILIGHT) i.e. between Sunset and Evening-twilight).*/
    public boolean isEveningTwilight(long time) { return ((time > sunset) && (time < eveningTwilight)); }

    /**  Returns true if it is (NIGHT) i.e. between Sunset and Sunrise.*/
    public boolean isNight(long time) { return ((time > sunset) && (time < sunrise)); }

    /** Returns true if it is (POST_NIGHT) i.e. between Sunrise and End-of-day.*/
    public boolean isPostNight(long time) { return ((time > sunrise) && (time < (startOfDay+ONE_DAY))); }
    
    /** Returns the elapsed time since Start-of-day (millis).*/
    public long getElapsedTime(long time) { return (time - startOfDay); }

    public String getTimePeriod(long time) { 
	if (isPreNight(time))
	    return "Pre-Night";
	else if
	    (isNight(time))
	    return "Night";
	else if 
	    (isMorningTwilight(time))
	    return "Morning-Twilight";
	else if 
	    (isEveningTwilight(time))
	    return "Evening-Twilight";
	else if
	    (isPostNight(time))
	    return "Post-Night";
	else 
	    return "Not-this-day";
    }

    /** Returns the Day-in-year as a String for the Actual date which includes 
     * the StartOfDay time for this ObsDate.*/
    public String getDateStamp() {
	return sdf.format(new Date(getStartOfDay()));
    }

    /** Returns a String representation of this ObsDate.*/
    public String toString() {	
	long now = System.currentTimeMillis();
	
	return "ObsDate at "+iso8601.format(new Date(now))+
	    "\n\tTZ Offset:        "+timeZoneOffset+" hours"+
	    "\n\tStarting Hour:    "+udf.format(new Date(startOfDayHours*3600*1000L))+	   
	    "\n\tUTC Midnight:     "+iso8601.format(new Date(midnight))+
	    "\n\tUTC Noon:         "+iso8601.format(new Date(noon))+
	    "\n\tLocal Midnight:   "+iso8601.format(new Date(localMidnight))+
	    "\n\tLocal Noon:       "+iso8601.format(new Date(localNoon))+
	    "\n\tStart of Day:     "+iso8601.format(new Date(startOfDay))+
	    "\n\tEnd of Day:       "+iso8601.format(new Date(startOfDay+ONE_DAY))+
	    "\n\tMorning Twilight: "+iso8601.format(new Date(morningTwilight))+ 
	    (twilightWarning ? " *WARNING*" : "")+
	    "\n\tNautical (Morn):  "+iso8601.format(new Date(nauticalMorningTwilight))+
	    "\n\tSunrise:          "+iso8601.format(new Date(sunrise))+
	    "\n\tSunset:           "+iso8601.format(new Date(sunset))+
	    "\n\tNautical (Eve):   "+iso8601.format(new Date(nauticalEveningTwilight))+
	    "\n\tEvening Twilight: "+iso8601.format(new Date(eveningTwilight))+
	    (twilightWarning ? " *WARNING*" : "")+
	    "\n\tElapsed Time:     "+adf.format(new Date(now - startOfDay))+
	    "\n\tTime Period:      "+getTimePeriod(now)+
	    "\n\tDate Stamp:       "+getDateStamp();
    }
    
}
