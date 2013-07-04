package ngat.ngtcs.test;

import java.awt.*;
import java.text.DecimalFormat;
//import javax.swing.*;

public class AzPanel extends Panel
{
    final Dimension SIZE = new Dimension(200,200);
	
    protected double wrap;

    protected double az;

    protected DecimalFormat df = new DecimalFormat( "+000.00;-000.00" );

    protected final float coeff = 255.0f/270.f;

    AzPanel()
    {
	super();
	wrap = 0.0;
	setBackground(Color.gray);
    }
	
    public void update(double d)
    {
	az = ( d < 0.0 ? d + 360.0 : d );
	wrap = ( az > 270.0 ? az - 450.0 : az - 90.0 );
	repaint();
    }
	
    public Dimension getPreferredSize()
    { 
	return SIZE;
    }
	
    public void paint(Graphics g)
    {
	super.paint(g);

	double height = (double)getSize().height;
	double width  = (double)getSize().width;

	Color c = null;
	int diff = 0;
	double x1 = 0.0, x2 = width/2.0, x3 = 0.0;
	double y1 = 0.0, y2 = 0.0, y3 = 0.0;
	int wrapSign = ( wrap < 0.0 ? -1 : 1 );
	double angle = 0.0;

	for( int i = 0; i < (int)( Math.abs( wrap * 20.0 ) ); i++ )
	    {
		diff = (int)(coeff * (float)i / 20.0f);		
		c = new Color(diff,255-diff,0);
		g.setColor(c);
		angle = (double)( i * -wrapSign ) / 20.0;
		x1 = Math.cos( Math.toRadians( angle ) );
		x2 = 0.5 * width * ( 1.0 + 0.9 * x1 );
		x3 = 0.5 * width * ( 1.0 + 0.8 * x1 );
		y1 = Math.sin( Math.toRadians( angle ) );
		y2 = 0.5 * height * ( 1.0 - 0.9 * y1 );
		y3 = 0.5 * height * ( 1.0 - 0.8 * y1 );
		g.drawLine( (int)x2, (int)y2, (int)x3, (int)y3 );
		c = null;
	    }

	g.setColor( Color.black );
	g.drawLine( (int)( width / 2.0 ), (int)( height / 2.0 ), 
		    (int)x2, (int)y2 );
	g.drawString( "N", 97, 15 );
	g.drawString( "S", 97, 195 );
	g.drawString( "E", 190, 107 );
	g.drawString( "W", 5, 107 );
	g.setColor(Color.yellow);
	g.drawString( df.format( az ), 70, 90 );
	g.drawString( df.format( wrap ), 70, 120 );
    }
}
