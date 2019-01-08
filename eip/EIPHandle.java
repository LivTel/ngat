// EIPHandle.java
// $Header: /space/home/eng/cjm/cvs/ngat/eip/EIPHandle.java,v 1.1 2008-10-09 14:14:21 cjm Exp $
package ngat.eip;

import java.lang.*;

import ngat.eip.*;

/**
 * This class provides a handle for referencing a connection made with EIPPLC (libtuxeip).
 * Instances of this Java class are mapped to an equivalent C layer EIP_Handle_T in the JNI layer.
 * @author Chris Mottram
 * @version $Revision$
 */
public class EIPHandle
{
	/**
	 * Revision Control System id string, showing the version of the Class
	 */
	public final static String RCSID = new String("$Id$");
	/**
	 * Hostname of the PLC, of the form xxx.xxx.xxx.xxx .
	 */
	protected String hostname = null;
	/**
	 * The backplane the PLC is on.
	 */
	protected int backplane = 0;
	/**
	 * The slot the PLC is in.
	 */
	protected int slot = 0;
	/**
	 * What type of PLC we are talking to.
	 * @see ngat.eip.EIPPLC#PLC_TYPE_NONE
	 * @see ngat.eip.EIPPLC#PLC_TYPE_MICROLOGIX1100
	 */
	protected int plcType = EIPPLC.PLC_TYPE_NONE;
	/**
	 * Whether the handle has been 'open'ed. (i.e. a socket connection made and
	 * connected session registered with the PLC).
	 */
	protected boolean open = false;

	/**
	 * Constructor.
	 */
	public EIPHandle()
	{
		super();
	}

	public String getHostname()
	{
		return hostname;
	}

	public int getBackplane()
	{
		return backplane;
	}

	public int getSlot()
	{
		return slot;
	}

	public int getPLCType()
	{
		return plcType;
	}

	public void setHostname(String s)
	{
		hostname = s;
	}

	public void setBackplane(int i)
	{
		backplane = i;
	}

	public void setSlot(int i)
	{
		slot = i;
	}

	public void setPLCType(int i)
	{
		plcType = i;
	}

	public void setOpen()
	{
		open = true;
	}

	public void setClose()
	{
		open = false;
	}

	public boolean isOpen()
	{
		return open;
	}

	public String toString()
	{
		return toString("");
	}

	public String toString(String prefix)
	{
		return new String(prefix+":"+this.getClass().getName()+":"+hostname+":Backplane = "+backplane+
				  ":Slot = "+slot+":PLC Type = "+plcType+":is Open = "+open);
	}
};
/*
** $Log: not supported by cvs2svn $
*/
