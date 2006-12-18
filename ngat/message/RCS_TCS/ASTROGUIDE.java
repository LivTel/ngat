package ngat.message.RCS_TCS;

import ngat.phase2.*;

/** TCS command: ASTROGUIDE.<br>
 * Instructs TCS to switch the autoguider state.<br>
 * see notes in RCS_TCS ICD.<br>
 * This is ASTROGUIDE !<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - state to put autoguider in.</dd>
 * <dd>&nbsp;values: { ON Ý OFF Ý SUSPEND Ý RESUME }</dd>
 * <dd>mode - guide star selection criterion.</dd>
 * <dd>&nbsp;values: { RANK Ý RANGE Ý PIXEL }</dd>
 * <dd>range1 - start of magnitude range for guide star selection.</dd>
 * <dd>&nbsp;values: 0.0 to 20.0 mag</dd>
 * <dd>range2 - end of magnitude range for guide star selection.</dd>
 * <dd>&nbsp;values: 0.0 to 20.0 mag</dd>
 * <dd>rank - brightness ranking of star to select for guiding.</dd>
 * <dd>&nbsp;values: 1 to 10</dd>
 * <dd>xPix - x coordinate of pixel where guide star is located.</dd>
 * <dd>&nbsp;values: 0 to 2047</dd>
 * <dd>yPix - y coordinate of pixel where guide star is located.</dd>
 * <dd>&nbsp;values: 0 to 2047</dd>
 * <dt>done params: </dt>
 * <dd>guidemag - magnitude of the selected guide star.</dd>
 * </dl>
 * <br>
 * $Id: ASTROGUIDE.java,v 1.1 2006-12-18 11:58:47 snf Exp $
 */
public class ASTROGUIDE extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates autoguider should be switched on (closed loop).*/
	public static final int ON = 0;

	/** Constant: Indicates autoguider should be switched off (open loop).*/
	public static final int OFF = 1;

	/** Constant: Indicates autoguider should continue to send corrections but TCS should ignore.*/
	public static final int SUSPEND = 2;

	/** Constant: Indicates TCS should start applying autoguider corrections again (after a SUSPEND).*/
	public static final int RESUME = 3;

	/** Constant: Indicates guide star should be chosen from the field of view as that with rank.*/
	public static final int RANK = 4;

	/** Constant: Indicates guide star should be chosen from any in specified magnitude range.*/
	public static final int RANGE = 5;

	/** Constant: Indicates guide star should be nearest to specified pixel coordinates.*/
	public static final int PIXEL = 6;

	// Variables.

	/** The state to put autoguider in.*/
	protected int state;

	/** The guide star selection criterion.*/
	protected int mode;

	/** The start of magnitude range for guide star selection.*/
	protected int range1;

	/** The end of magnitude range for guide star selection.*/
	protected int range2;

	/** The brightness ranking of star to select for guiding.*/
	protected int rank;

	/** The x coordinate of pixel where guide star is located.*/
	protected int xPix;

	/** The y coordinate of pixel where guide star is located.*/
	protected int yPix;

	/** Create a ASTROGUIDE with specified id.*/
	public ASTROGUIDE(String id) { super(id); }

	/** Set the state to put autoguider in.*/
	public void setState(int state) { this.state = state; }

	/** Get the state to put autoguider in.*/
	public int getState() { return state; }

	/** Set the guide star selection criterion.*/
	public void setMode(int mode) { this.mode = mode; }

	/** Get the guide star selection criterion.*/
	public int getMode() { return mode; }

	/** Set the start of magnitude range for guide star selection.*/
	public void setRange1(int range1) { this.range1 = range1; }

	/** Get the start of magnitude range for guide star selection.*/
	public int getRange1() { return range1; }

	/** Set the end of magnitude range for guide star selection.*/
	public void setRange2(int range2) { this.range2 = range2; }

	/** Get the end of magnitude range for guide star selection.*/
	public int getRange2() { return range2; }

	/** Set the brightness ranking of star to select for guiding.*/
	public void setRank(int rank) { this.rank = rank; }

	/** Get the brightness ranking of star to select for guiding.*/
	public int getRank() { return rank; }

	/** Set the x coordinate of pixel where guide star is located.*/
	public void setXPix(int xPix) { this.xPix = xPix; }

	/** Get the x coordinate of pixel where guide star is located.*/
	public int getXPix() { return xPix; }

	/** Set the y coordinate of pixel where guide star is located.*/
	public void setYPix(int yPix) { this.yPix = yPix; }

	/** Get the y coordinate of pixel where guide star is located.*/
	public int getYPix() { return yPix; }

	// Hand generated code.

} // class def. [ASTROGUIDE].

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2006/12/18 11:56:33  snf
/** Initial revision
/** */
