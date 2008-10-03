// PLCValueSetter.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp-df1-arcomess/PLCValueSetter.java,v 1.2 2008-10-03 09:20:00 cjm Exp $
package ngat.lamp;

import java.lang.*;
import ngat.df1.*;
import ngat.serial.arcomess.*;
import ngat.util.*;
import ngat.util.logging.*;

/**
 * This class holds information about one PLC address and the value it should contain.
 * This is used to set various values in the LT A&G lamp unit PLC.
 * @author Chris Mottram
 * @version $Revision: 1.2 $
 */
public class PLCValueSetter
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: PLCValueSetter.java,v 1.2 2008-10-03 09:20:00 cjm Exp $");
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
	 * <li>Connect to the controller via, the connectionParameters.connectToController method.
	 * <li>Use the df1.setInteger method to set the PLC memory location specified by plcAddress to value.
	 * <li>Disconnect from the controller - using connectionParameters.closeConnection
	 * </ul>
	 * @param df1 The PLC instance to communicate with.
	 * @param connectionParameters The connection details of how to connect to the PLC.
	 * @exception ArcomESSNativeException Thrown if comms to the Arcom ESS has an error.
	 * @exception Df1LibraryNativeException Thrown if comms to the PLC has an error.
	 * @see #set
	 * @see #plcAddress
	 * @see #value
	 * @see ConnectionParameters#connectToController
	 * @see ngat.df1.Df1Library#setInteger
	 * @see ConnectionParameters#closeConnection
	 */
	public void setValue(ArcomESS arcomESS,Df1Library df1,ConnectionParameters connectionParameters) throws 
		Df1LibraryNativeException, ArcomESSNativeException
	{
		if(set)
		{
			connectionParameters.connectToController(arcomESS);
			df1.setInteger(plcAddress,value);
			connectionParameters.closeConnection(arcomESS);
		}
	}

}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2008/03/06 10:47:39  cjm
// Initial revision
//
//
