package ngat.ngtcs;

import java.util.*;
import java.text.*;
import java.io.*;

import ngat.util.logging.*;

/**
 * LogFormatter for the NGTCS logs.
 * 
 * This formatter produces:
 * <pre>
 * HH:MM:SS.S >> [ message source ] - message
 * </pre>
 * or
 * <pre>
 * HH:MM:SS.S >> [ message source ] - exception
 * exception stack trace...
 * </pre>
 *
 * @author $Author$ 
 * @version $Revision$
 */
public class StandardLogFormatter extends LogFormatter
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );

    /**
     * Format of the logged time.
     */
    SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss.S" );


    /**
     * Format the incoming LogRecord.
     */
    public String format( LogRecord record )
    {	
	if( record.getException() == null )
	    {
		return( sdf.format( new Date( record.getTime() ) )+
			" >> ["+record.getClazz()+"] - "+
			record.getMessage() );
	    }
	else
	    {
		Exception e = record.getException();
		ByteArrayOutputStream baos = new ByteArrayOutputStream( 16384 );
		PrintWriter w = new PrintWriter(baos);
		e.printStackTrace(w);
		w.close();
		w = null;
		return( sdf.format( new Date( record.getTime() ) )+
			" >> ["+record.getMessage()+"] - "+e+"\n"+
			baos.toString() );
	    }
    }


    /**
     * Start of logging message.
     * @return start-of-logging message
     */
    public String getHead()
    {
	return( "NGTCS Logging started ["+
		( new SimpleDateFormat( "dd/MM/yyyy - HH:mm:ss" ).
		    format( new Date( System.currentTimeMillis() ) ) )+"]" );
    }


    /**
     * End of logging message.
     * @return end-of-logging message
     */
    public String getTail()
    {
	return( "NGTCS Logging terminated ["+
		( new SimpleDateFormat( "dd/MM/yyyy - HH:mm:ss" ).
		    format( new Date( System.currentTimeMillis() ) ) )+"]" );
    }


    /** 
     * Return the file name extension for the formatter.
     * @return log - the filename format for NGTCS logs is
     * <br><b>ngtcs_ddmmyyyy.log</b>
     */
    public String getExtensionName()
    { 
	return "log";
    }
}
/*
 *    $Date: 2013-07-02 15:26:49 $
 * $RCSfile: StandardLogFormatter.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/StandardLogFormatter.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:11:30  je
 *     Initial revision
 *
 */
