package ngat.ngtcs.common;

import java.lang.Double;
import java.util.Vector;

/**
 * Defines mathematical matricies.  NB not fully implemented yet!
 * 
 * @version $Revision: 1.1 $
 * @author $Author: je $
 */
public class Matrix implements java.io.Serializable
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
        new String( "$Id: Matrix.java,v 1.1 2003-07-01 10:13:04 je Exp $" );

    /**
     * Number of rows in this matrix
     */
    protected int nRows;

    /**
     * Number of cloumns in this matrix
     */
    protected int nColumns;

    /**
     * Vector of rows
     */
    protected Vector matrix;

    /**
     * Vector of elements
     */
    protected Vector rows;

    /**
     * Matrix Constructor.  Needs the number of rows and columns as the 
     * arguments, and creates a zero Matrix.
     * @param nRows number of rows
     * @param nColumns number of columns
     */
    public Matrix( int nRows, int nColumns )
    {
	this.nRows = nRows;
	this.nColumns = nColumns;
	this.matrix = new Vector();
	for( int i = 0; i < nRows; i++ )
	    {
		rows = new Vector();
		for( int j = 0; j < nColumns; j++ )
		    {
			rows.add( new Double( 0.0 ) );
		    }
		this.matrix.add( rows );
	    }
    }


    /**
     * Mutator method.  Set a Matrix element.
     * @param row row of element
     * @param column column of element
     * @param element value of element
     */
    public void setElement( int row, int column, double element )
    {
	( ( Vector )matrix.get( row - 1 ) ).
	    set( column - 1, new Double( element ) );
    }


    /**
     * Accessor method.  Retrieve a Matrix element
     * @param row row of element
     * @param column column of element
     * @return value of element
     */
    public double getElement( int row, int column )
    {
	return ( ( Double )( ( Vector )matrix.get( row - 1 ) ).
		 get( column - 1 ) ).doubleValue();
    }


    /**
     * Accessor method.  Retrieve the number of rows in this matrix.
     * @return integer number of rows
     */
    public int getNRows()
    {
	return nRows;
    }


    /**
     * Accessor method.  Retrieve the number of columns in this matrix.
     * @return integer number of columns
     */
    public int getNColumns()
    {
	return nColumns;
    }


    /**
     * Multiply matrices.  NOT IMPLEMENTED.<br>
     * @param m2
     * @return result of (m2)(this) = this x m2
     */
    public Matrix multiplyBy( Matrix m2 )
    {
	return m2;
    }


    /**
     * Normalise this matrix.
     * @return a new normalised matrix
     */
    public void normalise()
    {
	double modulusSquared = 0.0;
	double modulus = 0.0;
	double e = 0.0;

	for( int i = 1; i <= nRows; i++ )
	    {
		for( int j = 1; j <= nColumns; j++ )
		    {
			e = getElement( i, j );
			modulusSquared += e * e;
		    }
	    }

	modulus = Math.sqrt( modulusSquared );

	for( int i = 1; i <= nRows; i++ )
	    {
		for( int j = 1; j <= nColumns; j++ )
		    {
			setElement( i, j, ( getElement( i, j ) / modulus ) );
		    }
	    }
    }


    /**
     * Accessor method.  Returns a string format of this matrix
     * @return string of matrix
     */
    public String toString()
    {
	//	return NGTCSFormat.matrixString( this );
	//	return( ""+this );

	String output = "[ ";

	for( int i = 1; i <= nRows; i++ )
	    {
		for( int j = 1; j <= nColumns; j++ )
		    {
			output = output+getElement( i, j );
			if( i < nRows )
			    output = output+", ";
		    }
		if( i != nRows )
		    {
			output = output+"\n  ";
		    }
		else
		    {
			output = output+" ]";
		    }
	    }
	return output;

    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: Matrix.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Matrix.java,v $
 *     $Log: not supported by cvs2svn $
 */
