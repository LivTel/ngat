// LampUnitInterface.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/LampUnitInterface.java,v 1.1 2008-03-06 10:47:39 cjm Exp $
package ngat.lamp;

import java.lang.*;
import ngat.util.logging.*;

/**
 * This interface is an abstract definition of operations that can be performed on a lamp unit
 * installed on the telescope. This allows the turning on and off of lamps, measuring their brightness
 * and error checking.
 * @author Chris Mottram
 * @version $Revision: 1.1 $
 */
public interface LampUnitInterface
{
	public void init() throws Exception;
	public void turnLampOn(String lamp) throws Exception;
	public void turnLampOff(String lamp) throws Exception;
	public void turnAllLampsOff() throws Exception;
	public boolean isLampOn(String lamp) throws Exception;
	public boolean isError() throws Exception;
}
//
// $Log: not supported by cvs2svn $
//
