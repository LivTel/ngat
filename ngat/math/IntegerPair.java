package ngat.math;

import java.io.*;
/** Stores a pair of Integer coordinates.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id: IntegerPair.java,v 1.1 2001-07-11 10:49:20 snf Exp $
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/math/IntegerPair.java,v $
 * </dl>
 * @author $Author: snf $
 * @version $Revision: 1.1 $
 */
public class IntegerPair implements Serializable {
    
    /** X - Coordinate.*/
    public int i;

    /** Y - Coordinate.*/
    public int j;

    public IntegerPair() {
	i = 0;
	j = 0;
    }
    
    public IntegerPair(int i, int j) {
	this.i = i;
	this.j = j;
    }

}

/** $Log: not supported by cvs2svn $ */
