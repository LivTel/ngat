package ngat.ngtcs.test;

import java.awt.*;
import java.text.*;
import javax.swing.*;

import ngat.ngtcs.common.*;

/**
 * Panel containing the degrees, minutes and seconds fields to be able to input
 * a declination.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class JDecInputPanel extends JApplet
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );

    /**
     * Degree input field.
     */
    protected JTextField degreeInput = new JTextField( " +00 " );

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
     * NumberFormatter for degrees.
     */
    protected DecimalFormat ddf = new DecimalFormat( " 000;-000" );

    /**
     * NumberFormatter for minutes.
     */
    protected DecimalFormat mdf = new DecimalFormat( "00" );

    /**
     * NumberFormatter for seconds.
     */
    protected DecimalFormat sdf = new DecimalFormat( "00.000" );



    /**
     * Create a JDecInputPanel.
     */
    public JDecInputPanel( JFrame f )
    {
	frame = f;

	JLabel l;
        Container cp = getContentPane();
	cp.setLayout( layout );
        l = new JLabel( "Dec : " );
	cp.add( l );
        cp.add( degreeInput );
        cp.add( new JLabel( "\u00B0  " ) );
        cp.add( minuteInput );
        cp.add( new JLabel( "'  " ) );
        cp.add( secondInput );
        cp.add( new JLabel( "''" ) );
    }


    /**
     * Create a JDecInputPanel.
     */
    public JDecInputPanel()
    {
        Container cp = getContentPane();
	cp.setLayout( layout );
	cp.add( new JLabel( "Dec : " ) );
        cp.add( degreeInput );
        cp.add( new JLabel( "\u00B0  " ) );
        cp.add( minuteInput );
        cp.add( new JLabel( "'  " ) );
        cp.add( secondInput );
        cp.add( new JLabel( "''" ) );
    }


    /**
     * Return the value entered as radians.
     */
    public double getValue() throws NumberFormatException
    {
	double degrees = 0.0, minutes = 0.0, seconds = 0.0;
        String degreeInputText = degreeInput.getText();
	int decSign = 1;

	degrees = ( new Double( degreeInputText.replace( '+', ' ' ))).
	    doubleValue();
	decSign = (int)( degrees / Math.abs( degrees ) );
	minutes = (double)decSign *
	    ( new Double( minuteInput.getText() ) ).doubleValue();
	seconds = (double)decSign *
	    ( new Double( secondInput.getText() ) ).doubleValue();

	return ( ( ( seconds / 60.0 ) + minutes ) / 60.0 + degrees ) *
	    Math.PI / 180.0;
    }


    /**
     * Return the entered value as a DegreeMinutesSeconds object
     */
    public DegreesMinutesSeconds getDMS() throws NumberFormatException
    {
	return new DegreesMinutesSeconds
	    ( Integer.parseInt( degreeInput.getText() ),
	      Integer.parseInt( minuteInput.getText() ),
	      Integer.parseInt( secondInput.getText() ) );
    }


    /**
     * Set the degrees, minutes and seconds fields.
     */
    public void setText( DegreesMinutesSeconds hms )
    {
	degreeInput.setText( hms.getDegreesString() );
	minuteInput.setText( hms.getMinutesString() );
	secondInput.setText( hms.getSecondsString()+"."+
			     hms.getMillisecondsString() );
    }


    /**
     * Set the degrees field.
     */
    public void setDegrees( int i )
    {
	degreeInput.setText( ddf.format( i ) );
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
     * Return the value as a +DD MM SS.SSS style string.
     */
    public String getValueString()
    {
        String degreeInputText = degreeInput.getText();
	return( degreeInput.getText()+" "+minuteInput.getText()+" "+
		secondInput.getText() );
    }
}
/*
 *    $Date: 2013-07-04 13:01:36 $
 * $RCSfile: JDecInputPanel.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/JDecInputPanel.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:54  je
 *     Initial revision
 *
 */
