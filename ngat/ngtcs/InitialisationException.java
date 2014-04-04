package ngat.ngtcs; 
  
/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class InitialisationException extends Exception
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );


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
 *    $Date: 2013-07-02 14:43:13 $
 * $RCSfile: InitialisationException.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/InitialisationException.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:11:30  je
 *     Initial revision
 *
 */
