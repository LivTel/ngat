package ngat.util;


/** Holds the time and value for a Prediction sample.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/PredictorSample.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 * $Id$
 */
public class PredictorSample {

    /** The value for this sample.*/
    public double value;

    /** The time when this sample was taken.*/
    public long time;

    /** Create a PredictorSample with the specified time and value.
     */
    public PredictorSample(long time, double value) {
	this.time = time;
	this.value = value;
    }
    
}

/** $Log: not supported by cvs2svn $ */
