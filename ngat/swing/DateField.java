/*   
    Copyright 2006, Astrophysics Research Institute, Liverpool John Moores University.

    This file is part of NGAT.

    NGAT is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    NGAT is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with NGAT; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
package ngat.swing;

import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/** Component to capture date/time information.*/
public class DateField extends JPanel {

    /** Default date format.*/
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Parsing date format.*/
    public static SimpleDateFormat PARSE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");

    public static final SimpleTimeZone UTC = new SimpleTimeZone(0, "UTC");

    /** Default background color.*/
    public static final Color DEFAULT_BG_COLOR = new Color(153,204,153);

    public static final Border LOW_BEVEL = BorderFactory.createLoweredBevelBorder();

    /** Field to hold Year.*/
    protected JTextField yearField;

    /** Field to hold Month.*/
    protected JTextField monthField;
    
    /** Field to hold Day in month.*/
    protected JTextField dayField;
    
    /** Field to hold Hours.*/
    protected JTextField hourField;

    /** Field to hold Minutes.*/
    protected JTextField minuteField;
    
    /** Field to hold Seconds.*/
    protected JTextField secondField;

    /** Background color for panel.*/
    protected Color bgColor;

    /** Format to set/get content.*/
    protected SimpleDateFormat sdf;

    /** Currently set time.*/
    protected long time;
    
    /** Construct a DateField .*/
    public DateField() {
	this(DEFAULT_DATE_FORMAT);
    }
   
    /** Construct a DateField .*/
    public DateField(boolean showDate) {
	this(DEFAULT_DATE_FORMAT, true);
    }
    
    /** Construct a DateField .*/
    public DateField(long time) {
	this(DEFAULT_DATE_FORMAT, time, true);
    }
    
    /** Construct a DateField .*/
    public DateField(SimpleDateFormat sdf) {
	this(sdf, System.currentTimeMillis(), false);
    }

    /** Construct a DateField .*/
    public DateField(SimpleDateFormat sdf, boolean showDate) {
	this(sdf, System.currentTimeMillis(), true);
    }
   
    /** Construct a DateField .*/	
    public DateField(SimpleDateFormat sdf, long time, boolean showDate) {
	 
	super(new FlowLayout(FlowLayout.LEFT));
	         
	this.sdf  = sdf;
	this.time = time;
	
	sdf.setLenient(false);
	sdf.setTimeZone(UTC);

	PARSE_DATE_FORMAT.setTimeZone(UTC);

	add(new JLabel("Y"));
	yearField = new JTextField(4);	    
	add(yearField);
	
	add(new JLabel("M"));
	monthField = new JTextField(2);	    
	add(monthField);
	
	add(new JLabel("D"));
	dayField = new JTextField(2);
	add(dayField);
	
	add(new JLabel("at time"));
	hourField = new JTextField(2);
	add(hourField);
	
	add(new JLabel(":"));
	minuteField = new JTextField(2);
	add(minuteField);

	add(new JLabel(":"));
	secondField = new JTextField(2);
	add(secondField);
	
	//setBackground(DEFAULT_BG_COLOR);
	setBorder(LOW_BEVEL);
	
	if (showDate) {
	    displayTime(time);	    
	}

    }

    /** Set the date format.*/
    public void setDateFormat(String format) {
	sdf = new SimpleDateFormat(format);
	sdf.setLenient(false);
	sdf.setTimeZone(UTC);

    }

    /** Set the date format using existing SimpleDateFormat.*/
    public void setDateFormat(SimpleDateFormat sdf) {
	this.sdf = sdf;	
	sdf.setLenient(false);
	sdf.setTimeZone(UTC);

    }
    
    /** Clear the fields.*/
    public void clear() {
	yearField.setText("");
	monthField.setText("");
	dayField.setText("");
	hourField.setText("");
	minuteField.setText("");
	secondField.setText("");
    }

    /** Sets the time.*/
    public void setTime(long time) {
	this.time = time;
    }

    /** Returns the formatted time.*/
    public String getFormattedTime() {
	return 
	    yearField.getText().trim()+"-"+
	    monthField.getText().trim()+"-"+
	    dayField.getText().trim()+" "+
	    hourField.getText().trim()+":"+
	    minuteField.getText().trim()+":"+
	    secondField.getText().trim();	
    }

    /** Returns the time in the fields.*/
    public long getTime() throws ParseException {
	Date date = sdf.parse(getFormattedTime());
	if (date != null)
	    return date.getTime();
	throw new ParseException("No valid date could be parsed", 0);
    }

    /** Causes the speciifed date to be displayed.
     * @param atime The date/time to display.
     */
    public void displayTime(long atime) {
	
	// Format the date using curent formatter.

	String fmt = PARSE_DATE_FORMAT.format(new Date(atime));

	System.err.println("Time formats to: "+fmt);

	// Now extract the pieces. yyyy/mm/dd/hh/mm

	StringTokenizer st = new StringTokenizer(fmt, "/");

	if (st.countTokens() == 6) {

	    yearField.setText(st.nextToken());
	    monthField.setText(st.nextToken());
	    dayField.setText(st.nextToken());
	    hourField.setText(st.nextToken());
	    minuteField.setText(st.nextToken());
	    secondField.setText(st.nextToken());
	}


    }


}
