// $Header: /space/home/eng/cjm/cvs/ngat/math/Matrix.java,v 1.1 2000-11-10 13:33:59 je Exp $

package ngat.math;

import java.lang.*;
import java.util.*;

/**
 * Defines mathematical matricies.  NB not fully implemented yet!
 * @author $author$
 * @version $Revision: 1.1 $
 */
public class Matrix
{
    /**
     * Revision Control System id string, showing the version of the Class.
     */
    public static final String RCSID = new String( "$Id: Matrix.java,v 1.1 2000-11-10 13:33:59 je Exp $" );


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
     * Matrix Constructor.  Needs the number of rows and columns as the arguments, and creates a zero Matrix
     * @param nRows number of rows
     * @param nColumns number of columns
     */
    public Matrix( int nRows, int nColumns )
    {
	this.nRows = nRows;
	this.nColumns = nColumns;
	rows = new Vector();
	for( int i = 0; i < nColumns; i++ )
	    {
		rows.add( new Double( 0.0 ) );
	    }
	matrix = new Vector();
	for( int i = 0; i < nRows; i++ )
	    {
		matrix.add( rows );
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
	( ( Vector )matrix.get( row ) ).set( column, new Double( element ) );
    }


    /**
     * Accessor method.  Retrieve a Matrix element
     * @param row row of element
     * @param column column of element
     * @return value of element
     */
    public double getElement( int row, int column )
    {
	return ( ( Double )( ( Vector )matrix.get( row - 1 ) ).get( column - 1 ) ).doubleValue();
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
     * Multiply by a scalar.
     * @param scalar
     * @return new matrix
     */
    public Matrix multiplyByScalar( double scalar )
    {
	Matrix newMatrix = new Matrix( nRows, nColumns );
	for( int i = 0 ; i < nColumns ; i++ )
	    {
		for( int j = 0 ; j < nRows ; j++ )
		    {
			newMatrix.setElement( i, j, ( scalar * this.getElement( i, j ) ) );
		    }
	    }
	return newMatrix;
    }


    /**
     * Multiply matrices.  NOT IMPLEMENTED.<br>
     * @param m2
     * @return result of (m2)(this) = this x m2
     */
    public Matrix multiplyByMatrix( Matrix m2 )
    {
	return m2;
    }


    /**
     * Accessor method.  Returns a string format of this matrix
     * @return string of matrix
     */
    public String toString()
    {
	return NGTCSFormat.matrixString( this );
    }
}
