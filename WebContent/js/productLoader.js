	var productHtmlTemplate = "<div class='col-md-4 portfolio-item'> <a href='#'><img class='img-responsive' src='{$image_src_placeholder$}' alt=''></a><h3><a href='{$product_url_placeholder$}'>{$product_name_placeholder$}</a></h3><h5><a href='#'>price: ${$price_placeholder$}</a></h5><p> <h7>source:{$source_placeholder$} </h7></p></div>";
	var productRowHtmlTemplate = " <div class='row added'>{$product_row$}</div>";
	var currentWalmartStart=1;
	var currentEbayStart=1;
	var nextWalmartStart= -1;
	var previousWalmartStart= -1;
	var nextEbayStart = -1;
	var prevousEbayStart = -1;
	
		function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	};
	
	function setPagination(productResponse){
		nextWalmartStart = productResponse.walmartItemCount;
		previousWalmartStart = currentWalmartStart - 20;
	    nextEbayStart = productResponse.ebayPageCount;
		prevousEbayStart = currentEbayStart -1;
	};	
	
	
	function populatePage(productResponse) {
		var products = productResponse.productEntities;
		var col = 0;
		var productRow="";
		var productsHtml="";
		for(var i=0; i<products.length; i++){
			var product = products[i];
			product.imageUrl = product.imageUrl ? product.imageUrl : "";
			productRow+= productHtmlTemplate.replace("{$image_src_placeholder$}",product.imageUrl )
			.replace("{$product_url_placeholder$}", product.productUrl)
			.replace("{$product_name_placeholder$}",product.name)
			.replace("{$price_placeholder$}",product.price)
			.replace("{$source_placeholder$}",product.source);
			col++;
			if(col === 3){
				productsHtml+= productRowHtmlTemplate.replace("{$product_row$}", productRow);
				col = 0;
				productRow="";
			}
		}
		setPagination(productResponse);
	    $(".added").remove();
	    $("#first_row").after(productsHtml);
	    $("#pageHeader").text("Search Results: "+keyword);
	};
	
    var keyword = getParameterByName("keyword");
	//var keyword = document.getElementsByName("keyword");
	var walmartStart = getParameterByName("walmartStart");
	var ebayStart = getParameterByName("ebayStart");
	
	function populateProductsByKeyword(keyword1,walmartStart,ebayStart) {
		keyword = keyword1;
		if(walmartStart && walmartStart>0) {
		currentWalmartStart= walmartStart;
		}
		if(ebayStart && ebayStart > 0) {
			currentEbayStart =  ebayStart;
		}
	var serviceUrl = "/BusinessOptimizer/services/priceService/priceComparison";
	var query="keyword="+keyword1;
	if(walmartStart && walmartStart > 0){
		query+"&walmartStart="+walmartStart;
	}
	if(ebayStart && ebayStart > 0){
		query+"&ebayStart="+ebayStart;
	}
	$.ajax({url: serviceUrl+"?"+query , success: function(result){
      populatePage(result);
    }});
	}
	
	populateProductsByKeyword(keyword,walmartStart, ebayStart);
	
	function searchProducts(){
		var searchKeyword = $("#keyword").val();
		populateProductsByKeyword(searchKeyword, null, null);
	}
	
	function getNextPage(){
		populateProductsByKeyword(keyword, nextWalmartStart, nextEbayStart);
	}

	function getPreviousPage(){
		populateProductsByKeyword(keyword, previousWalmartStart, prevousEbayStart);
	}
