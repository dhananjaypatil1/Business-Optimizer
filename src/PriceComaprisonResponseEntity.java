import java.util.List;


public class PriceComaprisonResponseEntity {

	private int walmartItemCount = -1 ;
	private int ebayPageCount = -1;
	private List<ProductEntity> productEntities;
	
	
	
	
	public int getWalmartItemCount() {
		return walmartItemCount;
	}
	public void setWalmartItemCount(int walmartItemCount) {
		this.walmartItemCount = walmartItemCount;
	}
	public int getEbayPageCount() {
		return ebayPageCount;
	}
	public void setEbayPageCount(int ebayPageCount) {
		this.ebayPageCount = ebayPageCount;
	}
	public List<ProductEntity> getProductEntities() {
		return productEntities;
	}
	public void setProductEntities(List<ProductEntity> productEntities) {
		this.productEntities = productEntities;
	}
	
	
}
