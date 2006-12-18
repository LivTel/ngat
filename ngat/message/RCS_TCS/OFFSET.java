package ngat.message.RCS_TCS;

import ngat.phase2.*;

/** TCS command: OFFSET.<br>
 * Instruct the TCS to make a position offset.<br>
 * 1).When mode is set to ARC offsets are made in the tangent plane.<br>
 * 2).When mode is set to TIME offsets are made with constant<br>
 *    displacements in ra and dec whatever the altitude.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mode - operational mode.</dd>
 * <dd>&nbsp;values: { ARC | TIME }</dd>
 * <dd>offsetRA - offset in RA.</dd>
 * <dd>&nbsp;values: +/- 1 arcsec</dd>
 * <dd>offsetDec - offset in dec.</dd>
 * <dd>&nbsp;values: +/- 1 arcsec</dd>
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 * $Id: OFFSET.java,v 1.1 2006-12-18 11:58:47 snf Exp $
 */
public class OFFSET extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates offsets are applied in the tangent plane.*/
	public static final int ARC = 0;

	/** Constant: Indicates offsets are applied by constant amount.*/
	public static final int TIME = 1;

	// Variables.

	/** The operational mode.*/
	protected int mode;

	/** The offset in RA.*/
	protected double offsetRA;

	/** The offset in dec.*/
	protected double offsetDec;

	/** Create a OFFSET with specified id.*/
	public OFFSET(String id) { super(id); }

	/** Set the operational mode.*/
	public void setMode(int mode) { this.mode = mode; }

	/** Get the operational mode.*/
	public int getMode() { return mode; }

	/** Set the offset in RA.*/
	public void setOffsetRA(double offsetRA) { this.offsetRA = offsetRA; }

	/** Get the offset in RA.*/
	public double getOffsetRA() { return offsetRA; }

	/** Set the offset in dec.*/
	public void setOffsetDec(double offsetDec) { this.offsetDec = offsetDec; }

	/** Get the offset in dec.*/
	public double getOffsetDec() { return offsetDec; }

	// Hand generated code.

	///** Return the LT specific command string representation.*/
	//public String getLTCommandString() { return "OFFSET"; }
	//
	///** Return the LT specific command arguments.*/
	//public String getLTCommandArgs() {
	//
	//     String args = "";
	//
	//     switch (mode) {
	//
	//     case ARC:
	//          args = "ARC";
	//     case TIME:
	//          args = "TIME";
	//     }
	//
	//     args = args + offsetRA + " " + offsetDec;
	//     return args;
	//}
} // class def. [OFFSET].

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/12/01 18:19:09  snf
/** Initial revision
/** */
