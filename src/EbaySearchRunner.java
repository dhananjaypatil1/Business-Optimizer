import java.util.List;

import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindItemsByKeywordsRequest;
import com.ebay.services.finding.FindItemsByKeywordsResponse;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.services.finding.PaginationInput;
import com.ebay.services.finding.SearchItem;
import com.ebay.services.finding.SortOrderType;


public class EbaySearchRunner implements Runnable{
	private FindItemsByKeywordsResponse result;
	private String keywordString;
	private int start;
	private SortOrderType sort;
	private int numItems;
	public EbaySearchRunner(String keywordString, int start,SortOrderType sort, int numItems) {
		this.keywordString	= keywordString;
		 this.start = start;
		 this.sort = sort;
		 this.numItems = numItems;
	}

	@Override
	public void run() {
		if(start == -1){
			return;
		}
		 try {
			 
	        	// initialize service end-point configuration
	        	ClientConfig config = new ClientConfig();
	        	// endpoint address can be overwritten here, by default, production address is used,
	        	// to enable sandbox endpoint, just uncomment the following line
	        	//config.setEndPointAddress("http://svcs.sandbox.ebay.com/services/search/FindingService/v1");
	        	config.setApplicationId(PropertiesProvider.getProperty(PropertiesProvider.EBAY_API_KEY));

	        	//create a service client
	            FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);
	            
	            //create request object
	            FindItemsByKeywordsRequest request = new FindItemsByKeywordsRequest();
	            //set request parameters
	            request.setKeywords(keywordString);
	            request.setSortOrder(sort);
	            PaginationInput pi = new PaginationInput();
	            pi.setEntriesPerPage(numItems);
	            pi.setPageNumber(start);
	           
	            request.setPaginationInput(pi);
	            
	            result = serviceClient.findItemsByKeywords(request);
	            
	        } catch (Exception ex) {
	        	// handle exception if any
	            ex.printStackTrace();
	        }
	}

	public FindItemsByKeywordsResponse getResult() {
		return result;
	}


	
	

}
