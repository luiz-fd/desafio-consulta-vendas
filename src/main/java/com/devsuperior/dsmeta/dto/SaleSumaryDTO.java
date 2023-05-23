package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSumaryProjection;

public class SaleSumaryDTO {

	private String sellerName; 
	private Double total;
	
	public SaleSumaryDTO(Double total, String sellerName) {
		this.total = total;
		this.sellerName = sellerName;
	}
	
	public SaleSumaryDTO(SaleSumaryProjection entity) {
		total = entity.getTotal();
		sellerName = entity.getSellerName();
	}

	public Double getTotal() {
		return total;
	}

	public String getSellerName() {
		return sellerName;
	}
	
	
}
