package ngat.util;

import java.io.*;
import org.jdom.*;
import org.jdom.input.*;

/** A utility class for configuring XmlConfigurable target objects.*/
public class XmlConfigurator {

    /** Root element.*/
    private Element root;

    /** Prepare an XmlConfigurator with the supplied root element.*/
    private XmlConfigurator(Element root) {
	this.root = root;
    }

    /** Creates an instance of XmlConfigurator, with the tree parsed from the supplied file.
     * The configurator can then be used to configure an XmlConfigurable target.
     */
    public static XmlConfigurator use(File file) throws Exception {

	SAXBuilder builder = new SAXBuilder();
	Document doc = builder.build(file);
	Element root = doc.getRootElement();
	
	return new XmlConfigurator(root);

    }

    /** Configure an XmlConfigurable target object using the Configurator's root element.
     * @param target An instance of XmlConfigurable.
     */
    public void configure(XmlConfigurable target) throws Exception {
	target.configure(root);
    }

}
