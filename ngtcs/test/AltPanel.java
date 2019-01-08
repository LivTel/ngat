package ngat.ngtcs.test;

import java.awt.*;
import java.text.DecimalFormat;

public class AltPanel extends Panel
{

    final Dimension SIZE = new Dimension(200,200);

    protected double alt = 0.0;

    protected DecimalFormat df = new DecimalFormat( "+000.00;-000.00" );

    AltPanel()
    {
	super();
	setBackground(Color.gray);
    }
	
    public void update(double newAlt)
    {
	alt = newAlt;
	repaint();
    }
	
    public Dimension getPreferredSize()
    { 
	return SIZE;
    }
	
    public void paint(Graphics g)
    {
	super.paint(g);
	int width  = getSize().width;
	int height = getSize().height;
	double x1, x2 = ( 0.1*(double)width ), x3;
	double y1, y2 = ( 0.1*(double)height ), y3, angle;

	g.setColor( Color.green );
	for( int i = 0; i < (int)( Math.abs( alt * 20.0 ) ); i++ )
	    {
		angle = (double)i / 20.0;
		x1 = Math.cos( Math.toRadians( angle ) );
		x2 = width * ( 0.1 + 0.8 * x1 );
		x3 = width * ( 0.1 + 0.85 * x1 );
		y1 = Math.sin( Math.toRadians( angle ) );
		y2 = height * ( 0.9 - 0.80 * y1 );
		y3 = height * ( 0.9 - 0.85 * y1 );
		g.drawLine( (int)x2, (int)y2, (int)x3, (int)y3 );
	    }

	g.setColor( Color.black );
	g.drawLine( (int)( (double)width * 0.1 ), 
		    (int)( (double)height * 0.9 ), (int)x2, (int)y2 );
	g.setColor(Color.yellow);
	g.drawString( df.format( alt ), 70, 105 );
    }
}
