// LTLookAndFeel.java -*- mode: Fundamental;-*-
// $Header: /space/home/eng/cjm/cvs/ngat/swing/LTLookAndFeel.java,v 0.1 1999-11-30 14:05:33 cjm Exp $
package ngat.swing;

import java.lang.*;
import java.util.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * This class is an LT specific look and feel.
 * @author Chris Mottram
 * @version $Revision: 0.1 $
 */
public class LTLookAndFeel extends MetalLookAndFeel
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: LTLookAndFeel.java,v 0.1 1999-11-30 14:05:33 cjm Exp $");

	/**
	 * Return a string that identifies this look and feel. 
	 * @return Returns 'LT'.
	 */
	public String getID()
	{
		return "LT";
	}

	/**
	 * Return a short string that identifies this look and feel. 
	 * @return Returns 'Liverpool Telescope'.
	 */
	public String getName()
	{
		return "Liverpool Telescope";
	}

	/**
	 * Return a one line description of this look and feel implementation.
	 * @return Returns 'Liverpool Telescope Look and Feel'.
	 */
	public String getDescription()
	{
		return "Liverpool Telescope Look and Feel";
	}

	/**
	 * If the underlying platform has a "native" look and feel, and this is an implementation of it, return true.
	 * @return This method returns false.
	 */
	public boolean isNativeLookAndFeel()
	{
		return false;
	}

	/**
	 * Return true if the underlying platform supports and or permits this look and feel.
	 * @return This method returns true.
	 */
	public boolean isSupportedLookAndFeel()
	{
		return true;
	}

	/**
	 * UIManager.setLookAndFeel calls this method before the first call (and typically the only call) 
	 * to getDefaults(). 
	 */
	public void initialize()
	{
		UIDefaults uiDefaults = null;

		super.initialize();
		uiDefaults = super.getDefaults();
		for(Enumeration e = uiDefaults.keys(); e.hasMoreElements() ;)
		{
			String keyString = (String)e.nextElement();
			System.out.println("key:"+keyString+":\t\tvalue:"+uiDefaults.get(keyString));
		}
	}

	/**
	 * UIManager.setLookAndFeel calls this method just before we're replaced by a new default look and feel. 
	 * This just calls the super-classes uninitialise.
	 */
	public void uninitialize()
	{
		super.uninitialize();
	}
}
//
// $Log: not supported by cvs2svn $
//
