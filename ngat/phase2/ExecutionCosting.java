package ngat.phase2;

import java.io.*;
import java.util.*;

public class ExecutionCosting implements Serializable {
 
    public static double slewRate = 0.0;
    public static double offsetRate = 0.0;
    public static double configRate = 0.0;
    public static double readoutRate = 0.0;

    public int nSlew = 0;
    public int nOffset = 0;
    public int nConfig = 0;
    public int nExpose = 0;
    public double totalExpose;

    public ExecutionCosting() {}

    public double getTotal() {

	double slewCost    = slewRate*nSlew;
        double offsetCost  = offsetRate*nOffset;
        double configCost  = configRate*nConfig;
	double readoutCost = readoutRate*nExpose;
  
	double exposeCost  = totalExpose;
  
        double total = slewCost + offsetCost + configCost + exposeCost + readoutCost;
	return total;
    }

    public String toString() {
	double slewCost    = slewRate*nSlew;
	double offsetCost  = offsetRate*nOffset;
	double configCost  = configRate*nConfig;
	double exposeCost  = totalExpose;
	double readoutCost = readoutRate*nExpose;

	double total = slewCost + offsetCost + configCost + exposeCost + readoutCost;

	double efficiency = exposeCost/total;
	
	return 
	    "\n Slews:     "+nSlew+   " @ "+slewRate+   "s = "+slewCost+
	    "\n Offsets:   "+nOffset+ " @ "+offsetRate+ "s = "+offsetCost+
	    "\n Configs:   "+nConfig+ " @ "+configRate+ "s = "+configCost+
	    "\n Readouts:  "+nExpose+ " @ "+readoutRate+"s = "+readoutCost+
	    "\n Exposures: "+totalExpose+"s"+
	    "\n Efficiency:"+(100.0*efficiency)+"%"+
	    "\n --------------------------------------"+
	    "\n Total:     "+total+
	    "\n --------------------------------------";
    }
    
}
