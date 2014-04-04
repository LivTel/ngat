package ngat.ngtcs.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class JExceptionMessage extends javax.swing.JOptionPane
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );


    /**
     *
     */
    public JExceptionMessage( Exception e, JFrame owner )
    {
	super( e.toString(), ERROR_MESSAGE );
	JDialog jd = createDialog( owner, "Error" );
	jd.setVisible( true );
    }
}
/*
 *    $Date: 2013-07-04 13:01:39 $
 * $RCSfile: JExceptionMessage.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/JExceptionMessage.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:54  je
 *     Initial revision
 *
 */
