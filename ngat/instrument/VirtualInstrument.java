package ngat.instrument;

import ngat.phase2.*;
import ngat.util.*;

import java.io.*;
import java.util.*;

/** Holds long-term information about an Instrument.
 * SId$
 */
public interface VirtualInstrument {

     /** Load the VirtualInstrument setup from a file layed out in the 
      * java.util.Properties format. 
      * @param configFile The java.io.File containing the setup information 
      * for the Instrument layed out in java.util.Properties format. 
      * The format will depend on the specific type of VirtualInstrument.
      * e.g. for CCDs will have a list of filters etc.
      * @exception IOException If the file cannot be found or any other IO
      * problems occur while reading it.
      * @exception IllegalArgumentException If an number parsing error occurs
      * or an essential property is missing from the file.*/
    public void configure(File configFile) 
	 throws IOException, IllegalArgumentException;
    
    /** Returns True if the specified InstrumentConfig is valid for the period specified
     * by the two Dates (from and to).
     * @param config The InstrumentConfig to check.
     * @param from   The start of the period.
     * @param to     The end of the period.
     */
    public abstract boolean canBeConfigured(InstrumentConfig config, Date from, Date to);

    /** Returns a reference to the real Instrument represented by this VirtualInstrument.*/
    public Instrument getInstrument();

    /** Returns an Instrument representing the real instrument configured as for the
     * specified dates.
     * @param from   The start of the period.
     * @param to     The end of the period.
     */
    public Instrument getInstrument(Date from , Date to);

    /** Returns an ExposureCalculator for this VirtualInstrument during the specified period.
     * <p>The use envisioned would be something like the following:
     * <code>
     *  VirtualInstrument vi = myVIFactory.createVirtualInstrument(myInstrument);
     *  vi.configure(multiSemesterConfigFile);
     *  // Choose a period for the instrument setup.
     *  ExposureCalculator ex = vi.getExposureCalculator(AUG_12_2002, SEPT_23_2004);
     *  long time = ex.calculateExposure(aConfig, aStar, snr=25.0, sky=18.0, seeing=0.5);
     * </code>
     * @param from   The start of the period.
     * @param to     The end of the period.
     */
    public ExposureCalculator getExposureCalculator(Date from , Date to);

}
