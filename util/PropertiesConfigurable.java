package ngat.util;

public interface PropertiesConfigurable {

    /** Configure the object using the supplied properties.
     * @param config An instance of ConfigurationProperties containing the object's 
     * configuration information.
     * @throws Exception if the config is invalid in some way.
     */
   public void configure(ConfigurationProperties config) throws Exception;

}
