package ngat.ngtcs.test;

import java.io.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class SerialFileReader
{
    /*=======================================================================*/
    /*                                                                       */
    /* CLASS FIELDS.                                                         */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: SerialFileReader.java,v 1.1 2003-07-01 10:13:54 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/


    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    public static void main( String args[] )
    {
	if( args.length < 1 )
	    {
		System.err.println( "need file to open" );
		System.exit( 1 );
	    }

	try
	    {
		FileInputStream fis = new FileInputStream( args[ 0 ] );

		char readIn = ' ';

		System.err.println( "About to read "+args[ 0 ]+" : available "+
				    "bytes = "+fis.available() );

		while( readIn != ( (char)-1 ) )
		    {
			readIn = (char)( fis.read() );
			System.err.print( readIn );
		    }
	    }
	catch( Exception e )
	    {
		System.err.println( e.toString() );
		System.exit( 2 );
	    }
	System.exit( 0 );
    }

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/
}
/*
 *    $Date: 2003-07-01 10:13:54 $
 * $RCSfile: SerialFileReader.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/SerialFileReader.java,v $
 *      $Id: SerialFileReader.java,v 1.1 2003-07-01 10:13:54 je Exp $
 *     $Log: not supported by cvs2svn $
 */
