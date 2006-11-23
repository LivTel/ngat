package ngat.util;

import org.jdom.*;

/** Classes which should be configurable from a node in an XML (DOM) tree can implement
 * this interface to allow an XmlConfigurator to configure them.
 */
public interface XmlConfigurable {

    /** Configure the object using the xml tree rooted at the supplied org.jdom.Element.
     * @param node An instance of org.jdom.Element containing the root of the object's
     * configuration information tree.
     * @throws Exception if the configuration is invalid in some way.
     */
   public void configure(Element node) throws Exception;

}
