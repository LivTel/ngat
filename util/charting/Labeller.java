package ngat.util.charting;

import java.awt.*;

public interface Labeller {

    /** Draw a label at (ix, iy) into the Graphics context for the
     * specified value.
     * @param g The Graphics context to draw into.
     * @param ix The screen x coordinate.
     * @param iy The screen y coordinate.
     * @param value The value for this label.
     */
    public void drawLabel(Graphics g, int ix, int iy, double value);
    
}
