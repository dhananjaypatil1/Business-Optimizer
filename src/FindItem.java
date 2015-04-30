/*
Copyright (c) 2011 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/


import java.util.List;

import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindItemsByKeywordsRequest;
import com.ebay.services.finding.FindItemsByKeywordsResponse;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.services.finding.PaginationInput;
import com.ebay.services.finding.SearchItem;

/**
 * A sample to show eBay Finding servcie call using the simplied interface 
 * provided by the findingKit.
 * 
 * @author boyang
 * 
 */
public class FindItem {
	
	public static void main(String[] args) {
		
		 try {
			 
	        	// initialize service end-point configuration
	        	ClientConfig config = new ClientConfig();
	        	// endpoint address can be overwritten here, by default, production address is used,
	        	// to enable sandbox endpoint, just uncomment the following line
	        	//config.setEndPointAddress("http://svcs.sandbox.ebay.com/services/search/FindingService/v1");
	        	config.setApplicationId("SanJoseS-5df1-4811-afe3-ffad5bc92b7d");

	        	//create a service client
	            FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);
	            
	            //create request object
	            FindItemsByKeywordsRequest request = new FindItemsByKeywordsRequest();
	            //set request parameters
	            request.setKeywords("hawkins pressure cooker");
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

    // Basic service call flow:
    // 1. Setup client configuration
    // 2. Create service client
    // 3. Create outbound request and setup request parameters
    // 4. Call the operation on the service client and receive inbound response
    // 5. Handle response accordingly
    // Handle exception accordingly if any of the above steps goes wrong.

}

