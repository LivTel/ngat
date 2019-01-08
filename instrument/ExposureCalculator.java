package ngat.instrument;

import ngat.phase2.*;
/** Calculates exposure times based on supplied information. 
 *
 * <dl>	
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/instrument/ExposureCalculator.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 */
public interface ExposureCalculator {
 
    /** Calculates the exposure time (millis) for the given Instrument as it is currently configured.   
     * @param sourceMagnitude The magnitude of the star for which the exposure is to be determined.
     * @param snr             The snr for this exposure.
     * @param skyMagnitude    Magnitude of the sky (mags.arcsec<sup>-2</sup>).
     * @param seeing          The current seeing (arcsec).
     * @return                The exposure time in millis.
     */
    public double calculateExposureTime(double sourceMagnitude, double snr, double skyMagnitude, double seeing);
       
    /** Calculates the exposure time (millis) for the given Instrument 
     * configured with the supplied InstrumentConfig.
     * @param config          The InstrumentConfig to use.
     * @param sourceMagnitude The magnitude of the star for which the exposure is to be determined.
     * @param snr             The snr for this exposure.
     * @param skyMagnitude    Magnitude of the sky (mags.arcsec<sup>-2</sup>).
     * @param seeing          The current seeing (arcsec).
     * @return                The exposure time in millis.
     */
    public double calculateExposureTime(InstrumentConfig config,
					double           sourceMagnitude,
					double           snr, 
					double           skyMagnitude, 
					double           seeing);
       
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2007/01/05 13:21:53  snf
/** Uptodate
/** */
