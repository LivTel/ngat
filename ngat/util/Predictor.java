package ngat.util;


/** This interface is provided to allow predictive models of various environmental
 * or other measurables to be constructed. It Provides methods for updating and
 * querying the model.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/Predictor.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 */
public interface Predictor {

    /** Implementors should provide a mechanism for updating the model
     * using the supplied reading at the specified time.
     * @param time The time the update is for.
     * @param value The value for the update.
     */
    public void   update(long time, double value);

    /** Implementors should provide a mechanism for updating the model
     * using the set of values at the specified times. The times should be
     * sequentially ordered - what if they are not?.
     * @param times The set of times the updates are for.
     * @param values The set of values for the updates.
     */
    public void   update(long[] times, double[] values);

    /** Implementors should return a prediction of the value of the model
     * variable at the specified time.
     * @param time The time for which a predcition is required.
     */
    public double predict(long time);

    /** Implementors should return an estimate of the probability of the 
     * model variable lying between <b>x1</b> and <b>x2</b> over the interval <b>[t1, t2]</b>.
     * @param time1 The start of the interval for which the prediction is required.
     * @param time2 The end of the interval for which the prediction is required.
     * @param x1 The lower limit for the prediction range.
     * @param x2 The upper limit for the prediction range.
     */
    public double predict(long time1, long time2, double x1, double x2);
    
}

/** $Log: not supported by cvs2svn $ */
