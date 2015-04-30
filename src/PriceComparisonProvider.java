import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.businessoptimizer.api.responses.Item;
import com.businessoptimizer.api.responses.SearchResponse;
import com.ebay.services.finding.FindItemsByKeywordsResponse;
import com.ebay.services.finding.SearchItem;
import com.ebay.services.finding.SortOrderType;

public class PriceComparisonProvider {
	private static final int NUM_ITEMS = 20;

	Comparator<ProductEntity> productEntityComparator = new Comparator<ProductEntity>() {

		@Override
		public int compare(ProductEntity o1, ProductEntity o2) {
			return (int) (o1.getPrice() - o2.getPrice());
		}
	};

	public PriceComaprisonResponseEntity getPriceComparison(String keyword,
			int walmartStart, int ebayStart) {
		PriceComaprisonResponseEntity priceComaprisonResponseEntity = null;
		try {
			WalmartSerchRunner walmartSerchRunner = new WalmartSerchRunner(
					keyword, walmartStart, "price", "asc", NUM_ITEMS);
			EbaySearchRunner ebaySearchRunner = new EbaySearchRunner(keyword,
					ebayStart, SortOrderType.PRICE_PLUS_SHIPPING_LOWEST,
					NUM_ITEMS);
			Thread ebayThread = new Thread(ebaySearchRunner);
			Thread walmartThread = new Thread(walmartSerchRunner);
			
			walmartThread.start();
			ebayThread.start();

			walmartThread.join();
			ebayThread.join();
		
			priceComaprisonResponseEntity = new PriceComaprisonResponseEntity();
			SearchResponse walmartResponse = walmartSerchRunner.getItemobj();
			FindItemsByKeywordsResponse ebayResponse = ebaySearchRunner
					.getResult();
			List<ProductEntity> productEntities = new ArrayList<ProductEntity>(
				(walmartResponse != null ? 	walmartResponse.getNumItems() : 0)
							+ (ebayResponse != null ? ebayResponse.getSearchResult().getItem().size() : 0));
			if(walmartResponse != null) {
			priceComaprisonResponseEntity.setWalmartItemCount(walmartStart
					+ walmartResponse.getNumItems() >= walmartResponse.getTotalResults() ? -1 : walmartStart
					+ walmartResponse.getNumItems());
			for (Item item : walmartResponse.getItems()) {
					productEntities.add(ProductEntityConverter.convertFromWalmartItem(item));
			}
			}
			if(ebayResponse != null) {
			priceComaprisonResponseEntity.setEbayPageCount(ebayResponse
					.getPaginationOutput().getPageNumber() == ebayResponse
					.getPaginationOutput().getTotalPages() ? -1 : ebayResponse
					.getPaginationOutput().getPageNumber() + 1);
			for(SearchItem searchItem : ebayResponse.getSearchResult().getItem()){
				productEntities.add(ProductEntityConverter.convertFromEbayItem(searchItem));
			}
			}
			Collections.sort(productEntities, productEntityComparator);
			priceComaprisonResponseEntity.setProductEntities(productEntities);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return priceComaprisonResponseEntity;
	}

	public static void main(String[] args) {
		PriceComparisonProvider priceComparisonProvider = new PriceComparisonProvider();
		PriceComaprisonResponseEntity repsEntity = priceComparisonProvider.getPriceComparison("iphone 5s", 1, 1);
		if(repsEntity != null) {
			System.out.println(" =================================================================");
			System.out.println("WalmartStart "+ repsEntity.getWalmartItemCount() + "  ebayStart "+repsEntity.getEbayPageCount());
			for(ProductEntity productEntity :  repsEntity.getProductEntities()){
				System.out.println(productEntity);
			}
		}
	}
}
