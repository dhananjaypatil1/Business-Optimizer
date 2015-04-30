import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/priceService")
public class PriceComparisonService {
	private PriceComparisonProvider priceComparisonProvider = new PriceComparisonProvider();

	@GET
	@Path("/priceComparison")
	@Produces("application/json")
	public PriceComaprisonResponseEntity getPriceComparisonResponseEntity(
			@QueryParam("keyword") String keyword,
			@QueryParam("walmartStart") @DefaultValue("1")int walmartStart,
			@QueryParam("ebayStart") @DefaultValue("1") int ebayStart) {
		return priceComparisonProvider.getPriceComparison(keyword,
				walmartStart, ebayStart);
	}

}
