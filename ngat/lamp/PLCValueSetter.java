// PLCValueSetter.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/PLCValueSetter.java,v 1.4 2011-01-12 14:16:33 cjm Exp $
package ngat.lamp;

import java.lang.*;
import ngat.eip.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class holds information about one PLC address and the value it should contain.
 * This is used to set various values in the LT A&G lamp unit PLC.
 * @author Chris Mottram
 * @version $Revision: 1.4 $
 */
public class PLCValueSetter
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: PLCValueSetter.java,v 1.4 2011-01-12 14:16:33 cjm Exp $");
	/**
	 * A boolean, if true set the value during unit initialisation.
	 */
	protected boolean set = false;
	/**
	 * The PLC address of the value.
	 */
	protected String plcAddress = null;
	/**
	 * The configured value to set during unit initialisation
	 * if configured to do so.
	 */
	protected short value = 0;

	/**
	 * Load the config from a properties file.
	 * @param properties The property data.
	 * @param keyRoot The root key of the value to load. Properties with the key keyRoot+".set",
	 *        keyRoot+".plc_address" and keyRoot+".value" should exist.
	 * @see #set
	 * @see #plcAddress
	 * @see #value
	 */
	public void loadConfig(NGATProperties properties,String keyRoot) throws NullPointerException, 
										NGATPropertyException
	{
		// set - apparently getBoolean never fails!
		set = properties.getBoolean(keyRoot+".set");
		if(set)
		{
			// plcAddress
			plcAddress = properties.getProperty(keyRoot+".plc_address");
			if(plcAddress == null)
			{
				throw new NullPointerException(this.getClass().getName()+
				  ":loadConfig failed:Failed to find PLC Address in properties using key:"+
							       keyRoot+".plc_address");
			}
			// value - could be null/blank if set is false
			value = properties.getShort(keyRoot+".value");
		}
	}

	/**
	 * Set the specified value in the PLC, if configured to do so. If set is true:
	 * <ul>
	 * <li>Use the PLCConnection.setInteger method to set the PLC memory location specified by plcAddress to value.
	 * </ul>
	 * @param connection The connection details of how to connect to the PLC.
	 * @exception EIPNativeException Thrown if comms to the PLC has an error.
	 * @see #set
	 * @see #plcAddress
	 * @see #value
	 * @see PLCConnection#setInteger
	 */
	public void setValue(PLCConnection connection) throws EIPNativeException
	{
		setValue(this.getClass().getName(),null,connection);
	}

	/**
	 * Set the specified value in the PLC, if configured to do so. If set is true:
	 * <ul>
	 * <li>Use the PLCConnection.setInteger method to set the PLC memory location specified by plcAddress to value.
	 * </ul>
	 * @param clazz A string representing the class to put in the log record for all logs generated
	 *              by this call.
	 * @param source A string representing the source to put in the log record for all logs generated
	 *              by this call.
	 * @param connection The connection details of how to connect to the PLC.
	 * @exception EIPNativeException Thrown if comms to the PLC has an error.
	 * @see #set
	 * @see #plcAddress
	 * @see #value
	 * @see PLCConnection#setInteger
	 */
	public void setValue(String clazz,String source,PLCConnection connection) throws EIPNativeException
	{
		if(set)
		{
			connection.setInteger(clazz,source,plcAddress,value);
		}
	}

}
//
// $Log: not supported by cvs2svn $
// Revision 1.3  2008/10/09 14:15:09  cjm
// Rewrite so ngat.lamp now does PLC comms using ngat.eip rather than
// via ngat.df1 / ngat.serial.arcomess.
//
// Revision 1.2  2008/10/03 09:20:00  cjm
// Changes relating to libdf1 using libarcom_ess handles.
//
// Revision 1.1  2008/03/06 10:47:39  cjm
// Initial revision
//
//
