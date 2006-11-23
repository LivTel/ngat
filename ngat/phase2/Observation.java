package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;
import java.text.*;


// Generated by O3J 


public class Observation extends NPDBObject implements Serializable {
   
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -3810373271457624615L;
     
    /**  number of millis for the exposure. */
    protected float exposeTime;

    /**  indicator of whether the exposure time should be corrected for conditions. */
    protected boolean conditionalExposure;
    
    /**  number of (MULTRUN) exposures to take. */
    protected int numRuns;
    
    /**  filepath (or URL) containing the data for this Observation. */
    protected String dataLocation;
    
    /** Mosaic settings.*/
    protected Mosaic mosaic;

    /** Offset from nominal source position in RA. (rads)*/
    protected double sourceOffsetRA;

    /** Offset from nominal source position in Dec. (rads)*/
    protected double sourceOffsetDec;
   
    /**  Reference to Telescope Configuration used. */
    protected TelescopeConfig telescopeConfig;
    
    /** Reference to Instrument Configuration used. */
    protected InstrumentConfig instrumentConfig;
    
    /** Reference to Pipeline reduction Configuration used. */
    protected PipelineConfig pipelineConfig;
    
    /** Reference to the observed Source object. */
    protected Source source;

    /** Latest time at which this Observation can be */
    protected long latestCompletionTime;


    // ##### START NEW STUFF

    /**  Determines size of focus offset (mm) (if any). */
    protected double focusOffset;
    
    /**  Indicator of whether AG should be used -default:NEVER. */
    protected int autoGuiderUsageMode;
  
    /**  Flag to indicate mode of angle specification (one of SKY, MOUNT, VERTICAL, FLOAT, VFLOAT). */
    protected int rotatorMode;
       
    /**  Angle offset of rotator in specified mode. */
    protected double rotatorAngle;

    // ### END NEW STUFF


     // Constructor.
    public Observation() {this("untitled");}
    
    public Observation(String name) {
	super(name);
    }
    
    // Accessors.   
    
    /** Sets the  number of millis for the exposure .*/
    public void setExposeTime(float in) { this.exposeTime = in;}
    
    /** Returns the  number of millis for the exposure. */
    public float getExposeTime() { return exposeTime;}
    
    /** Sets the  indicator of whether the exposure time should be corrected for conditions .*/
    public void setConditionalExposure(boolean in) {  this.conditionalExposure = in;}
    
    /** True if  indicator of whether the exposure time should be corrected for conditions. */
    public boolean isConditionalExposure() { return conditionalExposure;}
    
    /** Sets the  number of (MULTRUN) exposures to take .*/
    public void setNumRuns(int in) {  this.numRuns = in;}

    /** Returns the  number of (MULTRUN) exposures to take. */
    public int getNumRuns() { return numRuns;}
    
    /** Sets the  filepath (or URL) containing the data for this Observation .*/
    public void setDataLocation(String in) {  this.dataLocation = in;}
    
    /** Returns the  filepath (or URL) containing the data for this Observation. */
    public String getDataLocation() { return dataLocation;}
    
    /** Sets the mosaic pattern in use.*/
    public void setMosaic(Mosaic mosaic) {  this.mosaic = mosaic; }

    /** Returns the mosaic setting.*/ 
    public Mosaic getMosaic() { return mosaic; }

    /** Set offset from nominal source position in RA. (rads)*/
    public void setSourceOffsetRA(double in) {  this.sourceOffsetRA = in; }
    
    /** Returns the offset from nominal source position in RA. (rads)*/
    public double getSourceOffsetRA() { return sourceOffsetRA; }
    
    /** Set offset from nominal source position in Dec. (rads)*/
    public void setSourceOffsetDec(double in) {  this.sourceOffsetDec = in; }
    
    /** Returns the offset from nominal source position in Dec. (rads)*/
    public double getSourceOffsetDec() { return sourceOffsetDec; }

    /** Sets the  reference to Telescope Configuration used .*/
    public void setTelescopeConfig(TelescopeConfig in) {  this.telescopeConfig = in;}
    
    /** Returns the  reference to Telescope Configuration used. */
    public TelescopeConfig getTelescopeConfig() { return telescopeConfig;}
    
    /** Sets the  reference to Instrument Configuration used .*/
    public void setInstrumentConfig(InstrumentConfig in) {  this.instrumentConfig = in;}
    
    /** Returns the  reference to Instrument Configuration used. */
    public InstrumentConfig getInstrumentConfig() { return instrumentConfig;}
    
    /** Sets the  reference to Pipeline reduction Configuration used .*/
    public void setPipelineConfig(PipelineConfig in) {  this.pipelineConfig = in;}
    
    /** Returns the  reference to Pipeline reduction Configuration used. */
    public PipelineConfig getPipelineConfig() { return pipelineConfig;}
    
    /** Sets the  reference to the observed Source object .*/
    public void setSource(Source in) {  this.source = in;}
    
    /** Returns the  reference to the observed Source object. */
    public Source getSource() { return source;}
    
    /** Returns latest time at which this Observation can be  made.*/
    public long getLatestCompletionTime() { return latestCompletionTime; }

    /** Sets the latest time at which this Observation can be  made.*/
    public void setLatestCompletionTime(long in) {  this.latestCompletionTime = in;}

    // ##### START NEW STUFF

    /** Sets the  determines size of focus offset (mm) (if any) .*/
    public void setFocusOffset(double in) {  this.focusOffset = in;}
    
    /** Returns the  determines size of focus offset (mm) (if any). */
    public double getFocusOffset() { return focusOffset;}    
    
    /** Sets the  indicator of whether AG should be used.*/
    public void setAutoGuiderUsageMode(int in) {  this.autoGuiderUsageMode = in;}

    /** Returns indicator of whether AG ahould be used. */
    public int getAutoGuiderUsageMode() { return autoGuiderUsageMode;}
    
    /** Sets the  flag to indicate mode of angle specification.*/
    public void setRotatorMode(int in) {  this.rotatorMode = in;}    
    
    /** Returns the  flag to indicate mode of angle specification. */
    public int getRotatorMode() { return rotatorMode;}
    
    /** Sets the  angle offset of rotator in specified mode .*/
    public void setRotatorAngle(double in) {  this.rotatorAngle = in;}   
    
    /** Returns the  angle offset of rotator in specified mode. */
    public double getRotatorAngle() { return rotatorAngle;}    


    // ### END NEW STUFF

    
    /**Formatted Text Output as XML.*/
    public void writeXml(PrintStream out, int level) { 
	//super.writeXml(out, level);
	out.println(tab(level)+"<observation name = '"+name+
		    "' srcId = '"+(source != null ? source.getName() : "-")+
		    "' icId  = '"+(instrumentConfig != null ? instrumentConfig.getName() : "-")+
		    "' tcId  = '"+(telescopeConfig != null ? telescopeConfig.getName() : "-")+
		    "'>");
	out.println(tab(level+1)+"<exposure exposeTime = '"+exposeTime+
		    "' conditional = '"+conditionalExposure+
		    "' repeats = '"+numRuns+"'/>");

	out.println(tab(level+1)+"<telescopeConfig>");
	out.println(tab(level+2)+"<focusOffset>"+focusOffset+"</focusOffset>");
	out.println(tab(level+2)+"<autoguider use = '"+TelescopeConfig.toAgModeString(autoGuiderUsageMode)+"'/>");
	out.println(tab(level+2)+"<rotator>");
	out.println(tab(level+3)+"<angle>"+Position.toDegrees(rotatorAngle,3)+"</angle>");
	out.println(tab(level+3)+"<mode>"+TelescopeConfig.toRotatorModeString(rotatorMode)+"</mode>");
	out.println(tab(level+2)+"</rotator>");
	out.println(tab(level+1)+"</telescopeConfig>");

	mosaic.writeXml(out, level+1);

	out.println(tab(level+1)+"<offsetRa>"+sourceOffsetRA+"</offsetRa>");
	out.println(tab(level+1)+"<offsetDec>"+sourceOffsetDec+"</offsetDec>");
	


	out.println(tab(level)+"</observation>");
    } // end (write).


    /** Writes the descriptor info to a String as Html. 
     * @param protocolVersion Allows for future changes to the formatting.
     */
    public String toHtmlString(int protocolVersion) {
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(3);
	nf.setMinimumFractionDigits(1);
	
	StringBuffer buffer = new StringBuffer();
	buffer.append("<html> ");
	buffer.append("\n   <body> ");
	buffer.append("\n  ");
	
	buffer.append("\n   <!-- Group ID --> ");
	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>  ");
	String type = this.getClass().getName();
	type = type.substring(type.lastIndexOf(".")+1);
	buffer.append("\n     <tr> <td><font size = +2> <b>"+type+"</b> </font></td>   ");
	buffer.append("\n          <td bgcolor = white>  <font size = +2>"+name+"</font></td> </tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");
	
	buffer.append("\n   <!-- Ownership --> ");
	
	Path pp = new Path(path);
	
	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>   ");
	buffer.append("\n     <tr> <td colspan = 10> <b>Ownership</b> </td> </tr> ");
	buffer.append("\n     <tr> ");
	buffer.append("\n       <td> DB       </td> <td bgcolor = white>"+pp.getRootByName()+    "</td> ");
	buffer.append("\n       <td> TAG      </td> <td bgcolor = white>"+pp.getTagByName()+     "</td> ");
	buffer.append("\n       <td> PI       </td> <td bgcolor = white>"+pp.getUserByName()+    "</td>  ");
	buffer.append("\n       <td> Proposal </td> <td bgcolor = white>"+pp.getProposalByName()+"</td>  ");	
	buffer.append("\n       <td> Group    </td> <td bgcolor = white>"+pp.getGroupByName()+   "</td>  ");	
	
	buffer.append("\n     </tr> ");
	buffer.append("\n   </table> ");
	buffer.append("\n  ");


	buffer.append("\n   <!-- Exposure --> ");
	buffer.append("\n   <p>  ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>    ");
	buffer.append("\n    <tr> <td colspan = 4> <b> Exposure </b> </td> </tr>  ");

	buffer.append("\n    <tr>  ");	
	buffer.append("\n      <td> Exposure </td>");
	buffer.append("\n      <td bgcolor = white>"+nf.format(exposeTime/1000.0) +" secs </td>  ");	
	buffer.append("\n      <td bgcolor = white>X&nbsp;"+numRuns+"</td>");
	if (conditionalExposure)
	    buffer.append("\n      <td> <font color = red> Depends on conditions </font></td>  ");
	else 
	    buffer.append("\n      <td> <font color = green> Independant of conditions </font></td>  ");
 
	buffer.append("\n    </tr>  "); 
	buffer.append("\n   </table> ");
	buffer.append("\n  ");


	buffer.append("\n   <!- Mosaic -->");
	
	buffer.append("\n   <p>");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>  ");
	buffer.append("\n    <tr> <td colspan = 2> <b> Mosaic </b> </td> </tr>   ");  
	
	if (mosaic != null) {
	    String pattern = "NONE";
	    switch (mosaic.getPattern()) {
	    case Mosaic.VERTICAL:
		pattern = "VERTICAL";
		break;
	    case Mosaic.HORIZONTAL:
		pattern = "HORIZONTAL";
		break;	    
	    case Mosaic.CROSS:
		pattern = "CROSS";
		break;
	    case Mosaic.X:
		pattern = "X";
		break;    
	    case Mosaic.GRID:
		pattern = "GRID";
		break;    
	    case Mosaic.HOLE:
		pattern = "HOLE";
		break;	    
	    case Mosaic.SINGLE:
		pattern = "SINGLE";
		break;    
	    case Mosaic.SLOPE_UP:
		pattern = "SLOPE_UP";
		break;    
	    case Mosaic.SLOPE_DOWN:
		pattern = "SLOPE_DOWN";
		break;
	    default:
		pattern = "UNKNOWN";
	    }


	    buffer.append("\n     <tr>  ");
	    buffer.append("\n       <td> Pattern </td> <td bgcolor = white>"+pattern+" ("+mosaic.getPattern()+") </td>  ");        
	    buffer.append("\n     </tr> ");
	    buffer.append("\n");
	    buffer.append("\n     <tr> ");
	    buffer.append("\n       <td> RA Offset  </td> <td bgcolor = white>"+
			  nf.format(Math.toDegrees(mosaic.getOffsetRA())*3600.0)+" arcsec </td> ");  
	    buffer.append("\n     </tr> ");
	    buffer.append("\n");
	    buffer.append("\n     <tr> ");
	    buffer.append("\n       <td> Dec Offset </td> <td bgcolor = white>"+
			  nf.format(Math.toDegrees(mosaic.getOffsetDec())*3600.0)+" arcsec </td> ");  
	    buffer.append("\n     </tr>");
	    
	    if (mosaic.getScaleToPixel())
		buffer.append("\n     <tr><td colspan = 2><font color = red> Scale to pixel </font></td></tr>");
	} else {
	    buffer.append("\n     <tr><td bgcolor = yellow colspan = 2><font color = red> No Mosaic Defined </font></td></tr>");
	}
	
	buffer.append("\n");
	buffer.append("\n   </table> ");

	buffer.append("\n   <!- Target Offsets -->");
	
	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>  "); 
	buffer.append("\n     <tr> <td colspan = 2> <b> Offsets </b> </td> </tr>  ");   
	buffer.append("\n     <tr>  ");
	buffer.append("\n       <td> RA Offset </td> <td bgcolor = white>"+
		      nf.format(Math.toDegrees(sourceOffsetRA)*3600.0)+" arcsec </td> ");         
	buffer.append("\n     </tr> ");
	buffer.append("\n     <tr>");  
	buffer.append("\n       <td> Dec Offset</td> <td bgcolor = white>"+
		      nf.format(Math.toDegrees(sourceOffsetDec)*3600.0)+" arcsec </td>");          
	buffer.append("\n     </tr> ");
	
	buffer.append("\n   </table> ");

	buffer.append("\n   <!-- Links -->");
	
	buffer.append("\n   <p> ");
	buffer.append("\n   <table border = 0 bgcolor = #ADD8E6>  "); 
	buffer.append("\n     <tr> <td colspan = 3> <b> Uses </b> </td> </tr> ");
	
	// SOURCE
	buffer.append("\n     <tr> ");	
	buffer.append("\n      <td> Target </td> ");
	if (source != null) {
	    type = source.getClass().getName();
	    type = type.substring(type.lastIndexOf(".")+1);
	    buffer.append("\n      <td bgcolor = white><i>"+type+"</i></td>"+
			  " <td bgcolor = white><u>"+source.getName()+"</u></td> ");
	} else 
	    buffer.append("\n      <td bgcolor = white colspan = 2><font color = red> NOT SPECIFIED </font></td>");
	
	buffer.append("\n     </tr>");
	
	// INST-CFG
	buffer.append("\n     <tr> ");	
	buffer.append("\n      <td> InstrumentConfig </td> ");
	if (instrumentConfig != null) { 
	    type = instrumentConfig.getClass().getName();
	    type = type.substring(type.lastIndexOf(".")+1);
	    buffer.append("\n      <td bgcolor = white><i>"+type+"</i></td>"+
			  " <td bgcolor = white><u>"+instrumentConfig.getName()+"</u></td> ");
	} else 
	    buffer.append("\n      <td bgcolor = white colspan = 2><font color = red> NOT SPECIFIED </font></td>");
	
	buffer.append("\n     </tr>");

	// TELE-CFG
	buffer.append("\n     <tr> ");	
	buffer.append("\n      <td> TelescopeConfig </td> ");
	if (telescopeConfig != null)
	    buffer.append("\n      <td bgcolor = white colspan = 2><u>"+
			  telescopeConfig.getName()+"</u></td> ");
	else 
	    buffer.append("\n      <td bgcolor = white colspan = 2><font color = red> NOT SPECIFIED </font></td>");
	
	buffer.append("\n     </tr>");
	
	// PIPE-CFG
	buffer.append("\n     <tr> ");	
	buffer.append("\n      <td> PipelineConfig </td> ");
	if (pipelineConfig != null)
	    buffer.append("\n      <td bgcolor = white colspan = 2><u>"+
			  pipelineConfig.getName()+"</u></td> ");
	else 
	    buffer.append("\n      <td bgcolor = white colspan = 2><font color = red> NOT SPECIFIED </font></td>");
	
	buffer.append("\n     </tr>");

	buffer.append("\n   </table> ");
  
	return buffer.toString();

    }


   
    /** Clone Constructor. References to attached TC, IC, PC and Src are copied from
     * the current Observation rather than cloned into new instances.*/
    public NPDBObject copy() {
	Observation newObservation = new Observation(name);
	newObservation.setPath(path);
	newObservation.setExposeTime(exposeTime);
	newObservation.setConditionalExposure(conditionalExposure);
	newObservation.setNumRuns(numRuns);
	newObservation.setDataLocation(dataLocation);
	newObservation.setMosaic((Mosaic)mosaic.copy());
	newObservation.setSourceOffsetRA(sourceOffsetRA);
	newObservation.setSourceOffsetDec(sourceOffsetDec);
	newObservation.setFocusOffset(focusOffset);
	newObservation.setAutoGuiderUsageMode(autoGuiderUsageMode);
	newObservation.setRotatorMode(rotatorMode);
	newObservation.setRotatorAngle(rotatorAngle);
	newObservation.setTelescopeConfig(telescopeConfig);
	newObservation.setInstrumentConfig(instrumentConfig);
	newObservation.setPipelineConfig(pipelineConfig);
	newObservation.setSource(source);
	
	return newObservation;
	
    } // end (copy).


    public String toString() {
	return "[Observation: "+name+
	    ", Lock="+lock+
	    ", Exposure="+exposeTime+" ("+(conditionalExposure ? "conditional" : "fixed")+")"+
	    ", Multrun="+numRuns+
	    ", Ag="+TelescopeConfig.toAgModeString(autoGuiderUsageMode)+
	    ", Rot="+TelescopeConfig.toRotatorModeString(rotatorMode)+" Angle="+Position.toDegrees(rotatorAngle,3)+
	    ", FOff="+focusOffset+
	    ", Mosaic="+mosaic+
	    ", Offsets=("+sourceOffsetRA+", "+sourceOffsetDec+")"+	  
	    "]";
    }
    
} // end class def [Observation].
