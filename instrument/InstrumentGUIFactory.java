package ngat.instrument;

import ngat.phase2.*;
import ngat.util.*;

import java.io.*;
import javax.swing.*;

/** A Factory for creating VirtualInstrument GUIs.
 * SId$
 */
public interface InstrumentGUIFactory {

    /** Concrete implementations must create an Instrument GUI Component 
     * from the supplied VirtualInstrument.*/
    public JComponent createGUIComponent(VirtualInstrument vi);

}
