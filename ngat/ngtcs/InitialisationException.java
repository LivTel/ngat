package ngat.ngtcs; 
  
/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class InitialisationException extends Exception
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: InitialisationException.java,v 1.1 2003-07-01 10:11:30 je Exp $" );


    /**
     *
     */
    public InitialisationException()
    {
        super();
    }


    /**
     * 
     */
    public InitialisationException( String exceptionString )
    {
        super( exceptionString );
    }


    /**
     *
     */
    public InitialisationException( Exception e )
    {
        super( "Could not initialise : "+e );
    }

}
/*
 *    $Date: 2003-07-01 10:11:30 $
 * $RCSfile: InitialisationException.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/InitialisationException.java,v $
 *     $Log: not supported by cvs2svn $
 */
