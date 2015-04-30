import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.businessoptimizer.api.SearchApi;
import com.businessoptimizer.api.responses.Item;
import com.businessoptimizer.api.responses.SearchResponse;

public class WalmartSerchRunner implements Runnable{
	
	private String keywordString;
	private int start;
	private String sort;
	private String order;
	private int numItems;
	private SearchResponse itemobj;
	
	public WalmartSerchRunner(String keywordString, int start, String sort, String order, int numItems) {
	 this.keywordString	= keywordString;
	 this.start = start;
	 this.sort = sort;
	 this.order = order;
	 this.numItems = numItems;
	}
	
	@Override
	public void run() {
		if(start == -1){
			return;
		}
		SearchApi obj = new SearchApi(PropertiesProvider.getProperty(PropertiesProvider.WALMART_API_KEY));
		try {
//			try {
//				YahooWeatherService yahoo =  new YahooWeatherService();
//				System.out.println(yahoo.getForecast(WoeIDProvider.getwoeIdFromZipCode("32608"), DegreeUnit.CELSIUS).description);
//			} catch ( Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			 itemobj = obj.getSearchResponse(keywordString, start, sort, order, numItems);
			System.out.println(itemobj.getNumItems());
			List<Item> listOfItems = itemobj.getItems();
		
		//	Item.BestMarketPlacePrice bestmarketplacepriceobject = new Item.BestMarketPlacePrice();
			//System.out.println(bestmarketplaceobject.getPrice());
			
//			for(int i=0;i<listOfItems.size();i++){
//				System.out.println(listOfItems.get(i).getModelNumber());
//				System.out.println(listOfItems.get(i).getName());
//				System.out.println(listOfItems.get(i).getSalePrice());
//			//	bestmarketplacepriceobject = listOfItems.get(i).getBestMarketplacePrice(); 
//			//	System.out.println(bestmarketplacepriceobject.getStandardShipRate());
//				
//			}
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
	
	public SearchResponse getItemobj() {
		return itemobj;
	}


}
