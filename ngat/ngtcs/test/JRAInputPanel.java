package ngat.ngtcs.test;

import java.awt.*;
import java.text.*;
import javax.swing.*;

import ngat.ngtcs.common.*;

/**
 * Panel containing the hours, minutes and seconds fields to be able to input
 * a right ascension.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class JRAInputPanel extends JApplet
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );

    /**
     * Hour input field.
     */
    protected JTextField hourInput = new JTextField( " 00 " );

    /**
     * Minute input field.
     */
    protected JTextField minuteInput = new JTextField( " 00 " );

    /**
     * Second input field.
     */
    protected JTextField secondInput = new JTextField( " 00.0000 " );

    /**
     * This panel's LayoutManager.
     */
    protected GridBagLayout layout = new GridBagLayout();

    /**
     *
     */
    protected JFrame frame;

    /**
     * NumberFormatter for hours.
     */
    protected DecimalFormat hdf = new DecimalFormat( " 00;-00" );

    /**
     * NumberFormatter for minutes.
     */
    protected DecimalFormat mdf = new DecimalFormat( "00" );

    /**
     * NumberFormatter for seconds.
     */
    protected DecimalFormat sdf = new DecimalFormat( "00.000" );


    /**
     * Create a JRAInputPanel.
     */
    public JRAInputPanel( JFrame f )
    {
	frame = f;

	Container cp = getContentPane();
	cp.setLayout( layout );
	cp.add( new JLabel( "RA : " ) );
	cp.add( hourInput );
	cp.add( new JLabel( "h  " ) );
	cp.add( minuteInput );
	cp.add( new JLabel( "m  " ) );
	cp.add( secondInput );
	cp.add( new JLabel( "s" ) );
    }


    /**
     * Create a JRAInputPanel.
     */
    public JRAInputPanel()
    {
	Container cp = getContentPane();
	cp.setLayout( layout );
	cp.add( new JLabel( "RA : " ) );
	cp.add( hourInput );
	cp.add( new JLabel( "h  " ) );
	cp.add( minuteInput );
	cp.add( new JLabel( "m  " ) );
	cp.add( secondInput );
	cp.add( new JLabel( "s" ) );
    }


    /**
     * Return the value entered as radians.
     */
    public double getValue() throws NumberFormatException
    {
	double hours = 0.0, minutes = 0.0, seconds = 0.0;

	hours = ( new Double( hourInput.getText() ) ).doubleValue();
	minutes = ( new Double( minuteInput.getText() ) ).doubleValue();
	seconds = ( new Double( secondInput.getText() ) ).doubleValue();

	return ( ( ( seconds / 60.0 ) + minutes ) / 60.0 + hours ) *
	    Math.PI / 12.0;
    }


    /**
     * Return the entered value as a DegreeMinutesSeconds object
     */
    public HoursMinutesSeconds getHMS() throws NumberFormatException
    {
	return new HoursMinutesSeconds
	    ( Integer.parseInt( hourInput.getText() ),
	      Integer.parseInt( minuteInput.getText() ),
	      Integer.parseInt( secondInput.getText() ) );
    }


    /**
     * Set the hours, minutes and seconds fields.
     */
    public void setText( HoursMinutesSeconds hms )
    {
	hourInput.setText( hms.getHoursString() );
	minuteInput.setText( hms.getMinutesString() );
	secondInput.setText( hms.getSecondsString()+"."+
			     hms.getMillisecondsString() );
    }


    /**
     * Set the hours field.
     */
    public void setHours( int i )
    {
	hourInput.setText( hdf.format( i ) );
    }


    /**
     * Set the minutes field.
     */
    public void setMinutes( int i )
    {
	minuteInput.setText( mdf.format( i ) );
    }


    /**
     * Set the seconds field.
     */
    public void setSeconds( double d )
    {
	secondInput.setText( sdf.format( d ) );
    }


    /**
     * Return the value as an HH MM SS.SSS style string.
     */
    public String getValueString()
    {
	return( hourInput.getText()+" "+minuteInput.getText()+" "+
		secondInput.getText() );
    }
}
/*
 *    $Date: 2013-07-04 13:01:41 $
 * $RCSfile: JRAInputPanel.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/JRAInputPanel.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:54  je
 *     Initial revision
 *
 */

