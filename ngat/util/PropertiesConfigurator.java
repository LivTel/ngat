package ngat.util;

import java.io.*;

/** A utility class for configuring PropertiesConfigurable target objects.*/
public class PropertiesConfigurator {

    /** Config.*/
    private ConfigurationProperties config;

    /** Prepare a PropertiesConfigurator with the supplied config.*/
    private PropertiesConfigurator(ConfigurationProperties config) {
	this.config = config;
    }

    /** Creates an instance of PropertiesConfigurator, with the config parsed from the supplied file.
     * The configurator can then be used to configure a PropertiesConfigurable target.
     */
    public static PropertiesConfigurator use(File file) throws Exception {

	ConfigurationProperties config = new ConfigurationProperties();
	config.load(new FileInputStream(file);

	return new PropertiesConfigurator(config);

    }

    /** Configure a PropertiesConfigurable target object using the Configurator's config.
     * @param target An instance of PropertiesConfigurable.
     */
    public void configure(PropertiesConfigurable target) throws Exception {
	target.configure(config);
    }

}
