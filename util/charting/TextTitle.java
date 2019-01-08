package ngat.util.charting;

import java.awt.*;

/** Something which draws a title (e.g.text or image.*/
public class TextTitle implements Title {

    protected String title;

    public TextTitle(String title) {
	this.title = title;
    }

    /** Draw the title in the centre, top just above the bounds area.*/
    public void draw(Graphics g, Rectangle bounds) {
	// Work out the width of the string.
	FontMetrics fm = g.getFontMetrics();
	int w = fm.stringWidth(title);
	g.drawString(title, bounds.x + (bounds.width - w)/2, bounds.y - 10);
    }

}
