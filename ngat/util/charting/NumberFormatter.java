package ngat.util.charting;

import java.text.*;

public class NumberFormatter implements Formatter {
    
    protected NumberFormat nf;
    
    public NumberFormatter(NumberFormat nf) {
	this.nf = nf;
    }

    /** Format the value.*/
    public String format(double value) {
	return nf.format(value);
    }

}
