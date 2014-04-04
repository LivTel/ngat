package ngat.util;

import java.util.*;

/** Basic Predictor model - uses an array to store model samples.
 * Predictions are made by extrapolating a linear regression of the
 * last <b>n</b> (setup parameter) samples. Probabilistic estimates
 * are made by counting the fraction of the last <b>n</b> 
 * (setup parameter) samples.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/StandardPredictor.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 * $Id$
 */
public class StandardPredictor implements Predictor {

    /** A name to identify this Predictor instance in logs.*/
    protected String id;
    
    /** Holds the most recent model values.*/
    protected CircularArray samples;

    /** Holds the number of samples used for Prediction.*/
    protected int sampleSize;
    
    /** Regression parameter. (predicted = aparm + bparm * t).*/
    private double aparm;
    
    /** Regression parameter. (predicted = aparm + bparm * t)*/
    private double bparm;

    /** Averaging prediction.*/
    private double prediction;

    /** Create a StandardPredictor using the supplied settings. If the value
     * of n is less than size then it is reset silently to size.
     * @param id A name to identify this Predictor instance in logs.
     * @param size The size of the array of samples to store.
     * @param n The number of samples to use for predictions (< size).
     */
    public StandardPredictor(String id, int size, int n) {
	this.id = id;
	samples = new CircularArray(size);
	this.sampleSize  = n;
	if (n < size)
	    this.sampleSize = size;
    }

    
    /** Provides a mechanism for updating the model
     * using the supplied reading at the specified time.
     * @param time The time the update is for.
     * @param value The value for the update.
     */
    public void   update(long time, double value) {
	if (samples.getCount() != 0) {
	    if (time < ((PredictorSample)samples.last()).time) return;
	}
	samples.insert(new PredictorSample(time, value));
	
//  	double tim = 0.0;
//  	double sx  = 0.0;
//  	double sy  = 0.0;
//  	double stt = 0.0;
//  	double t   = 0.0;
	
//  	PredictorSample samp = null;
	
//  	// Calculate the new regression parameters.
//  	Enumeration e1 = samples.reverse(sampleSize);
//  	while (e1.hasMoreElements()) {
//  	    samp = (PredictorSample)e1.nextElement();
//  	    sx  += (double)samp.time;
//  	    sy  += samp.value;
//  	}
	
//  	Enumeration e2 = samples.reverse(sampleSize);
//  	while (e2.hasMoreElements()) {  
//  	    samp   = (PredictorSample)e2.nextElement();
//  	    tim    = (double)samp.time;
//  	    t      = (tim - sx/sampleSize);
//  	    stt   += t * t; 
//  	    bparm += (tim - sx) * samp.value;
//  	}
	
//  	bparm = bparm/stt;
//  	aparm = (sy - sx * bparm)/sampleSize;
	
	double avge = 0.0;
	int   count = 0;
	PredictorSample samp = null;
	
  	// Calculate the new regression parameters.
 	Enumeration e1 = samples.reverse(sampleSize);  
	while (e1.hasMoreElements()) {
 	    samp  = (PredictorSample)e1.nextElement();
	    count++;
	    avge += samp.value;
 	}	
	
	prediction = avge/(double)count;

    }
    
    /** Provides a mechanism for updating the model
     * using the set of values at the specified times. The times should be
     * sequentially ordered - what if they are not?.
     * @param times The set of times the updates are for.
     * @param values The set of values for the updates.
     */
    public void   update(long[] times, double[] values) {
	int size = Math.min(times.length, values.length);
	for (int  i =0; i < size; i++) {
	    update(times[i], values[i]);
	}
    }

    /** Returns a prediction of the value of the model
     * variable at the specified time.
     * @param time The time for which a prediction is required.
     */
    public double predict(long time) {
	return prediction;
    }

    /** Returns an estimate of the probability of the 
     * model variable lying between <b>x1</b> and <b>x2</b> over the 
     * interval <b>[t1, t2]</b>.
     * @param time1 The start of the interval for which the prediction is required.
     * @param time2 The end of the interval for which the prediction is required.
     * @param x1 The lower limit for the prediction range.
     * @param x2 The upper limit for the prediction range.
     */
    public double predict(long time1, long time2, double x1, double x2) {
	int sum = 0;
	Enumeration e1 = samples.enumerate();
	PredictorSample samp = null;
	while (e1.hasMoreElements()) {
	    samp = (PredictorSample)e1.nextElement();
	    if ( (samp.value >= x1) && (samp.value <= x2) ) sum++;
	}	
	return sum/sampleSize;
    }
    
    /** Returns the name of this Predictor.*/
    public String getId() { return id; }
    
    /** Sets the name for this Predictor.*/
    public void setId(String id) { this.id = id; }

}

/** $Log: not supported by cvs2svn $ */
