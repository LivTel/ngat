package ngat.ngtcs.test;

/**
 * Simple class to test the <code>Math.round</code> function.
 * <br>
 * The class should be used:<br>
 * <code>java ngat.ngtcs.test.RoundTest d.ddd</code><br>
 * where d.ddd is the double to be rounded.
 */
public class RoundTest
{
    public static void main( String args[] )
    {
	try
	    {
		double d = Double.parseDouble( args[ 0 ] );
		System.out.println( "Math.round( "+args[ 0 ]+" ) = "+
				    Math.round( d ) );
	    }
	catch( Exception e )
	    {
		System.err.println( e.toString() );
		System.exit( 1 );
	    }
	System.exit( 0 );
    }
}
