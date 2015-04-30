import com.dhanashree.businessoptimizer.ebay.*;
import com.dhanashree.businessoptimizer.walmart.*;

public class BusinessOptimizerMain {
	
	

	public static void main(String[] args) {
		
		String queryString = "hawkins pressure cooker";
		
		WalmartSearchManager w1 = new WalmartSearchManager();
		w1.searchItemByKeyword(queryString);
		EbaySearchManager e1 = new EbaySearchManager();
		e1.searchingByKeyword(queryString);
		
		
		
	}
}
