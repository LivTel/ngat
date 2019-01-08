package ngat.util;

import ngat.astrometry.*;

import java.io.*;
import java.util.*;
import java.text.*;

public class DateParser {
    
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm z");
    
    public static long SUNRISE;
    public static long SUNSET;
    public static long TODAY_SUNSET;
    public static long MORNING;
    public static long EVENING;
    public static long UTC_NOON;
    public static long LOCAL_NOON;
    
    /** ObsDate on which to base the parsing.*/
    ObsDate od;

    /** Create a DateParser based on an ObsDate.*/
    public DateParser(ObsDate od) {	
	this.od = od;	
    }


    public long parse(String args) {
	
	String data = args.trim();
	long   tt   = System.currentTimeMillis();

        //System.err.println("Read args ["+args+"]");

        if (data.startsWith("%")) {
            String zero = data.substring(1,3);
            //System.err.println("Zero["+zero+"]");
            String sign = data.substring(3,4);
            //System.err.println("Sign["+sign+"]");
            String dirn = "";
            int sig = 0;

            if (sign.equals("+")) {
                dirn = "AFTER";
                sig = +1;
            } else {
                dirn = "BEFORE";
                sig = -1;
            }

            String time = data.substring(4).trim();

            // Convert to hh:mm
            int hours = 0;
            int mins  = 0;
            try {
                hours = Integer.parseInt(time.substring(0,2));
                mins  = Integer.parseInt(time.substring(3));
            } catch (Exception e) {
                System.err.println("Parse Error");
            }
	    
            long delta = mins*60*1000L + hours*3600*1000L;	    
            if
                (zero.equals("sr")) {
                //System.err.println(time+" "+dirn+" SUNRISE");
                tt = od.getSunrise() + sig*delta;
            } else if
                (zero.equals("ss")) {  
		//System.err.println(time+" "+dirn+" SUNSET");
                 tt = od.getSunset() + sig*delta;
            } else if
                (zero.equals("mt")) {
		//System.err.println(time+" "+dirn+" START MORNING TWILIGHT");
		tt = od.getMorningTwilight() + sig*delta;
            } else if
                (zero.equals("et")) {  
		//System.err.println(time+" "+dirn+" END EVENING TWILIGHT");
		tt = od.getEveningTwilight() + sig*delta;
	    } else if
                (zero.equals("sd")) {  		
		tt = od.getStartOfDay() + sig*delta;
	    } else if
                (zero.equals("ed")) {  		
		tt = od.getStartOfDay()+od.ONE_DAY + sig*delta;		
            } else {
		System.err.println("unknown time code");
            }
        } else {
	    String time = data.trim();

            // Convert to hh:mm
            int hours = 0;
            int mins  = 0;
            try {
                hours = Integer.parseInt(time.substring(0,2));
                mins  = Integer.parseInt(time.substring(3));
            } catch (Exception e) {
                System.err.println("Parse Error");
            }
	    
            long delta = mins*60*1000L + hours*3600*1000L;	
	    
	    tt = od.getMidnight() + delta;
            System.err.println("UTC TIME "+data);
        }	
	return tt;
    }
    
}


