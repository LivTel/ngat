// LampInterface.java
// $Header: /space/home/eng/cjm/cvs/ngat/lamp/LampInterface.java,v 1.2 2011-01-12 14:16:33 cjm Exp $
package ngat.lamp;

import java.lang.*;
import ngat.util.logging.*;

/**
 * This interface is an abstract definition of operations that can be performed on a lamp
 * installed in a lamp unit. This allows the turning on and off of lamps, measuring their brightness
 * and error checking.
 * @author Chris Mottram
 * @version $Revision$
 */
public interface LampInterface
{
	public String getName();
	public void init() throws Exception;
	public void turnLampOn() throws Exception;
	public void turnLampOn(String clazz,String source) throws Exception;
	public void turnLampOff() throws Exception;
	public void turnLampOff(String clazz,String source) throws Exception;
	public boolean isLampOn() throws Exception;
	public boolean isLampOn(String clazz,String source) throws Exception;
}
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2008/03/06 10:47:39  cjm
// Initial revision
//
//
