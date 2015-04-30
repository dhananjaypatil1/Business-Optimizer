import com.businessoptimizer.api.responses.Item;
import com.ebay.services.finding.SearchItem;
public class ProductEntityConverter {

	public static ProductEntity convertFromWalmartItem(Item item){
		ProductEntity entity = new ProductEntity();
		entity.setName(item.getName());
		entity.setPrice(item.getSalePrice());
        entity.setProductUrl(item.getProductUrl());	
        entity.setImageUrl(item.getThumbnailImage());
        entity.setSource("Walmart");
       // entity.setUpc(item.getUpc());
        return entity;
	}
	
	public static ProductEntity convertFromEbayItem(SearchItem item){
		ProductEntity entity = new ProductEntity();
		entity.setName(item.getTitle());
		entity.setImageUrl(item.getGalleryURL());
		entity.setSource("Ebay");
		entity.setPrice(item.getSellingStatus().getConvertedCurrentPrice().getValue());
		entity.setProductUrl(item.getViewItemURL());
		return entity;
	}
}
