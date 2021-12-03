package framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private Properties properties = new Properties();

    public ConfigLoader() {
        properties = new Properties();
    }

    public ConfigLoader(final String resourceName) {
        properties = appendFromResource(properties, resourceName);
    }

    public ConfigLoader(final String defaultResourceName, final String resourceName) {
        this(defaultResourceName);
        properties = appendFromResource(new Properties(properties), resourceName);
    }

    private Properties appendFromResource(final Properties objProperties, final String resourceName) {
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);

        if (inStream != null) {
            try {
                objProperties.load(inStream);
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(String.format("Resource \"%1$s\" could not be found", resourceName));
        }
        return objProperties;
    }

    public String getProperty(final String key) {
        return properties.getProperty(key);
    }


    public String getProperty(final String key, final String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }


    public void setProperty(final String key, final String value) {
        properties.setProperty(key, value);
    }

}

//    private static FileInputStream mainFileInputStream;
//    private static FileInputStream localFileInputStream;
//    private static Properties config;
//    private static Properties localConfig;
//
//    public static void configSettings(String local) {
//        // static {
//        try {
//            mainFileInputStream = new FileInputStream("src/test/resources/selenium.properties");
//            localFileInputStream = new FileInputStream(String.format("localization/local_%1$s.properties", local));
//            config = new Properties();
//            localConfig = new Properties();
//            config.load(mainFileInputStream);
//            config.load(localFileInputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (mainFileInputStream != null)
//                try {
//                    mainFileInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            if (localFileInputStream != null)
//                try {
//                    localFileInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
//    }
//
//    public static String getProperty(String key) {
//        return config.getProperty(key);

//}

