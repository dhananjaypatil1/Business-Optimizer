import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PropertiesProvider {

	private static final Properties properties = new Properties();
	public static final String WALMART_API_KEY= "walmart_api_key";
	public static final String EBAY_API_KEY="ebay_api_key";
	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String propertyName){
		return properties.getProperty(propertyName);
	}
	
	
}
