package ngat.util.charting;

import java.awt.*;

public class TextLabeller implements Labeller {
    
    /** Formatter to format the labels.*/
    protected Formatter formatter;

    public TextLabeller(Formatter formatter) {
	this.formatter = formatter;
    }
    
    /** If the formatter exists then value is formatted and drawn at (ix,iy)
     * otherwise a best-effort attempt is made by converting value to String.
     */
    public void drawLabel(Graphics g, int ix, int iy, double value) {
	if (formatter != null)
	    g.drawString(formatter.format(value), ix, iy);
	else
	    g.drawString(""+value, ix, iy);
    }

    public void setFormatter(Formatter formatter) {
	this.formatter = formatter;
    }
    
    public Formatter getFormatter() { return formatter; }

}
