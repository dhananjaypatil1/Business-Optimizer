import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;




public class WoeIDProvider {
	static final String ZIPCODE_TOKEN = "$ZIP_CODE_REPLACE$";
	static String urlTemplate = "http://where.yahooapis.com/v1/places.q('"+ ZIPCODE_TOKEN +"')?appid=dj0yJmk9WG1qYTNOSVNhU0VBJmQ9WVdrOVlsVndNV1psTldjbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD1lNA--&view=short";
	public static String getwoeIdFromZipCode(String zipCode) throws ClientProtocolException, IOException, Exception{
		String woeId = "";
		String url = urlTemplate.replace(ZIPCODE_TOKEN, zipCode);
	HttpClient client = HttpClientBuilder.create().build();
	HttpGet request = new HttpGet(url);
	 
	
	HttpResponse response = client.execute(request);
 
	System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());
 
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	factory.setValidating(false);
	DocumentBuilder builder = factory.newDocumentBuilder();
	Document doc = builder.parse(response.getEntity().getContent());
	NodeList list = doc.getElementsByTagName("woeid");
	for(int i = 0; i < list.getLength(); i++){
	 woeId	= list.item(i).getTextContent();
	}
	
	
	return woeId;
	}
	
	public static void main(String[] args) {
		try {
			getwoeIdFromZipCode("32608");
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
