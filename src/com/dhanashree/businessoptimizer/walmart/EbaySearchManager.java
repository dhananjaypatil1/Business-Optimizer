package com.dhanashree.businessoptimizer.walmart;

import java.util.List;

import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindItemsByKeywordsRequest;
import com.ebay.services.finding.FindItemsByKeywordsResponse;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.services.finding.PaginationInput;
import com.ebay.services.finding.SearchItem;

public class EbaySearchManager {
	
	String apiKey = "SanJoseS-5df1-4811-afe3-ffad5bc92b7d";
//	String queryString = "iphone 6";
	public void searchingByKeyword(String queryString){
		
		 try {
			 
	        	// initialize service end-point configuration
	        	ClientConfig config = new ClientConfig();
	        	// endpoint address can be overwritten here, by default, production address is used,
	        	// to enable sandbox endpoint, just uncomment the following line
	        	//config.setEndPointAddress("http://svcs.sandbox.ebay.com/services/search/FindingService/v1");
	        	config.setApplicationId(apiKey);

	        	//create a service client
	            FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);
	            
	            //create request object
	            FindItemsByKeywordsRequest request = new FindItemsByKeywordsRequest();
	            //set request parameters
	            request.setKeywords(queryString);
	            PaginationInput pi = new PaginationInput();
	            pi.setEntriesPerPage(200);
	            request.setPaginationInput(pi);
	            
	            //call service
	            FindItemsByKeywordsResponse result = serviceClient.findItemsByKeywords(request);
	            System.out.println(result.getPaginationOutput().getTotalPages());
	            while(result.getPaginationOutput().getTotalPages() >  result.getPaginationOutput().getPageNumber()){
	            //output result
	            System.out.println("pageNo : "+result.getPaginationOutput().getPageNumber()+"Ack = "+result.getAck());
	            System.out.println("Find " + result.getSearchResult().getCount() + " items." );

	            List<SearchItem> items = result.getSearchResult().getItem();
	            for(SearchItem item : items) {
	            	System.out.print(item.getTitle()+": ");
	            	System.out.println(item.getSellingStatus().getCurrentPrice().getCurrencyId()+" "+item.getSellingStatus().getCurrentPrice().getValue());
	            }
	            pi.setPageNumber(result.getPaginationOutput().getPageNumber() + 1);
	            request.setPaginationInput(pi);
	            result = serviceClient.findItemsByKeywords(request);
	            }
	            
	        } catch (Exception ex) {
	        	// handle exception if any
	            ex.printStackTrace();
	        }
	}

}
