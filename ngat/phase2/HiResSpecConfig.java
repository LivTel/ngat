package ngat.phase2;

import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * MES Spectrograph configuration.
 */
public class HiResSpecConfig extends SpecConfig implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 723884528095028233L;
     
    // Variables.
    
    /** Name used to identify the filter-thingy/position.*/
    protected String filterSlideName;
    
    // Constructor.
    
    /** Create a HiResSpecConfig with no title.*/
    public HiResSpecConfig() {this("untitled");}

    /** Create a HiResSpecConfig with no title.*/
    public HiResSpecConfig(String name) {
	super(name);
    }
    
    // Accessors.
  
    /** Set the name to identify the filter-thingy/position.*/
    public void setFilterSlideName(String name) { filterSlideName = name; }
    
    /**Returns the identify of the filter-thingy/position.*/
    public String getFilterSlideName() { return filterSlideName; }
    
    // Descendant Mutators.
     
    /** NP -> P Translator. */
    public HiResSpecConfig(NPHiResSpecConfig npHiResSpecConfig) {
	super(npHiResSpecConfig);	
	filterSlideName= npHiResSpecConfig.getFilterSlideName()   	
    } // end (NP -> P Translator).
     
    /** P -> NP Translator.*/
    public void stuff(NPHiResSpecConfig npHiResSpecConfig) {
	super.stuff(npHiResSpecConfig);
	npHiResSpecConfig.setFilterSlideName(filterSlideName);
    } // end (P -> NP Translator).

    /** P -> NP Translator.*/
    public NPDBObject makeNP() {
	NPHiResSpecConfig npHiResSpecConfig = new NPHiResSpecConfig();
	stuff(npHiResSpecConfig);
	return npHiResSpecConfig;
    } // end (makeNp).
    

} // end class def [HiResSpecConfig].
