package com.dhanashree.businessoptimizer.ebay;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.walmart.openapi.SearchApi;
import com.walmart.openapi.responses.SearchResponse;
import com.walmart.openapi.responses.Item;

//import com.businessoptimizer.api.SearchApi;
//import com.businessoptimizer.api.responses.Item;
//import com.businessoptimizer.api.responses.SearchResponse;

public class WalmartSearchManager {
	
	String apiKey = "uvu6x8et7426qdw9dpwmqvp4";
//	String queryString = "ipod";
	
	public void searchItemByKeyword(String queryString){
		SearchApi SearchApiObject = new SearchApi(apiKey);
		
		
try {
			
			com.walmart.openapi.responses.SearchResponse itemobj = SearchApiObject.getSearchResponse(queryString);
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
