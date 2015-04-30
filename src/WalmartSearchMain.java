import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.xml.bind.JAXBException;

import com.businessoptimizer.api.SearchApi;
import com.businessoptimizer.api.responses.Item;
import com.businessoptimizer.api.responses.Item.BestMarketPlacePrice;
import com.businessoptimizer.api.responses.SearchResponse;
import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.unit.DegreeUnit;


public class WalmartSearchMain {
	
	
	
	public static void main(String[] args) {
		String apiKey = "uvu6x8et7426qdw9dpwmqvp4";
		String queryString = "ipod";
		SearchApi obj = new SearchApi(apiKey);
		try {
			try {
				YahooWeatherService yahoo =  new YahooWeatherService();
				System.out.println(yahoo.getForecast(WoeIDProvider.getwoeIdFromZipCode("32608"), DegreeUnit.CELSIUS).description);
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			SearchResponse itemobj = obj.getSearchResponse(queryString, 1, "price", "asc", 20);
			String categoryId = "3944"; 
			itemobj.setCategoryId(categoryId);
			System.out.println(itemobj.getNumItems());
			List<Item> listOfItems = itemobj.getItems();
		
		//	Item.BestMarketPlacePrice bestmarketplacepriceobject = new Item.BestMarketPlacePrice();
			//System.out.println(bestmarketplaceobject.getPrice());
			
			for(int i=0;i<listOfItems.size();i++){
				System.out.println(listOfItems.get(i).getModelNumber());
				System.out.println(listOfItems.get(i).getName());
				System.out.println(listOfItems.get(i).getSalePrice());
				
			//	bestmarketplacepriceobject = listOfItems.get(i).getBestMarketplacePrice(); 
			//	System.out.println(bestmarketplacepriceobject.getStandardShipRate());
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
}
