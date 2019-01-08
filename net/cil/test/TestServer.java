import java.net.*;
import ngat.net.cil.*;
import ttl.common.*;
import ttl.net.cil.*;
/**
 * Basic TestServer to listen to the RCS commands, print them out and reply.
 */
public class TestServer
{
    private TTL_CIL cil;


    public TestServer( int localPort, String remoteHostName, int remotePort )
	throws Exception
    {
	cil = new TTL_CIL();

	InetAddress remoteHost = InetAddress.getByName( remoteHostName );
	cil.initialise( localPort, remoteHost, remotePort );
	System.out.println( "TTL_CIL initialised" );

	while( true )
	    {
		System.out.println( "\nListening on TTL_CIL port" );
		TTL_CIL_Message msgIn =
		    (TTL_CIL_Message)( cil.receiveMessage() );

		System.out.println( "Message received..." );
		System.out.println( msgIn );

		TTL_CIL_Message msgOut = new TTL_CIL_Message
		    ( msgIn.getDestinationNode(),
		      msgIn.getSourceNode(),
		      TTL_CIL_MessageClass.E_TTL_CIL_COM_CLASS,
		      msgIn.getServiceClass(),
		      msgIn.getSystem(),
		      msgIn.getSequenceNumber(),
		      msgIn.getSeconds(),
		      msgIn.getNanoseconds(),
		      new String( "A reply!" ).getBytes() );

		cil.sendMessage
		    ( (CIL_Message)msgOut );
	    }
    }


    public static void main( String args[] )
    {
	if( args.length != 3 )
	    {
		System.err.println
		    ( "Need localPort, remoteHost and remotePort" );
		System.exit( 1 );
	    }

	try
	    {
		int i1 = Integer.parseInt( args[ 0 ] );
		System.out.println( "local port = "+i1 );
		int i2 = Integer.parseInt( args[ 2 ] );
		System.out.println( "remote port = "+i2 );
		new TestServer( i1, args[ 1 ], i2 );
	    }
	catch( Exception e )
	    {
		System.err.println( e );
		System.exit( 2 );
	    }
    }
}
