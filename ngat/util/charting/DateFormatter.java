package ngat.util.charting;

import java.util.*;
import java.text.*;

public class DateFormatter implements Formatter {
    
    protected SimpleDateFormat sdf;
    
    public DateFormatter(SimpleDateFormat sdf) {
	this.sdf = sdf;
    }
    
    /** Format the value.*/
    public String format(double value) {
	return sdf.format(new Date((long)value));
    }
    
}
