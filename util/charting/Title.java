package ngat.util.charting;

import java.awt.*;

/** Something which draws a title (e.g.text or image.*/
public interface Title {

    /** Darw the title in the centre, top of the bounds area.*/
    public void draw(Graphics g, Rectangle bounds);

}
