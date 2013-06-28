package ngat.instrument;

import ngat.phase2.*;
import ngat.util.*;

import java.io.*;
/** A Factory for creating VirtualInstruments.
 * SId$
 */
public interface VirtualInstrumentFactory {

    /** Concrete implementations must create a VirtualInstrument from the supplied Instrument.*/
    public VirtualInstrument createVirtualInstrument(Instrument instrument);

}
