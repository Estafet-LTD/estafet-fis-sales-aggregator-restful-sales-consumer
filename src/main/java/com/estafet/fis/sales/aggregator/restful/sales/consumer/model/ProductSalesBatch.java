package com.estafet.fis.sales.aggregator.restful.sales.consumer.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductSalesBatch {

	@JsonIgnore
	private Integer batchId;

	private String startDate;

	private List<ProductSale> productSales = new ArrayList<ProductSale>();

	public String getStartDate() {
		return startDate;
	}

	public List<ProductSale> getProductSales() {
		return productSales;
	}

}
