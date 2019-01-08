package ngat.util;

import java.util.*;

/** Supplies statistics about a population. Samples are added using addSample() and then
 * relevant stats can be extracted using the various methods.
 */
public class PopulationStatistics {

    private List samples;

    public  PopulationStatistics() {
	samples = new Vector();
    }

    public void addSample(double d) {
	samples.add(new Double(d));
    }

    public void clear() { samples.clear(); }

    public int getNumber() { return samples.size();}

    public double getAverage() {

	if (samples.size() == 0)
	    return 0.0;

	double av = 0.0;
	Iterator it = samples.iterator();
	while (it.hasNext()) {

	    Double dd = (Double)it.next();
	    double d = dd.doubleValue();

	    av += d;
	}

	return av/samples.size();
    }

    public double getMinimum() {

	double min = Double.MAX_VALUE;
	Iterator it = samples.iterator();
	while (it.hasNext()) {

	    Double dd = (Double)it.next();
	    double d = dd.doubleValue();

	    if (d < min)
		min = d;

	}

	return min;
    }
    
    public double getMaximum() {

	double max = 1.0 - Double.MAX_VALUE;
	Iterator it = samples.iterator();
	while (it.hasNext()) {

	    Double dd = (Double)it.next();
	    double d = dd.doubleValue();

	    if (d > max)
		max = d;

	}

	return max;
    }

    public double getStandardDeviation() {

	if (samples.size() < 1)
	    return 0.0;

	double sum  = 0.0;
	double sum2 = 0.0;
	int    nn   = 0;
	Iterator it = samples.iterator();
	while (it.hasNext()) {

	    Double dd = (Double)it.next();
	    double d = dd.doubleValue();
	    nn++;
	    sum += d;
	    sum2 += d*d;
	}

	return Math.sqrt((sum2 - sum*sum/(double)nn)/((double)(nn-1)));

    }

    /** String stuff.*/
    public String toString() {
	return ""+getNumber()+" "+getAverage()+" "+getMinimum()+" "+getMaximum()+" "+getStandardDeviation();
    }

}
