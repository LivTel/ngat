package ngat.ngtcs.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class JExceptionMessage extends javax.swing.JOptionPane
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: JExceptionMessage.java,v 1.1 2003-07-01 10:13:54 je Exp $" );


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
 *    $Date: 2003-07-01 10:13:54 $
 * $RCSfile: JExceptionMessage.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/JExceptionMessage.java,v $
 *     $Log: not supported by cvs2svn $
 */
