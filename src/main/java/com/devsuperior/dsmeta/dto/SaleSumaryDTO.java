package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SaleSumaryProjection;

public class SaleSumaryDTO {

	private Double total;
	private String sellerName;
	
	public SaleSumaryDTO(Double amount, String sellerName) {
		this.total = amount;
		this.sellerName = sellerName;
	}
	
	public SaleSumaryDTO(Sale entity) {
		total = entity.getAmount();
		sellerName = entity.getSeller().getName();
	}
	
	public SaleSumaryDTO(SaleSumaryProjection entity) {
		total = entity.getTotal();
		sellerName = entity.getSellerName();
	}

	public Double getAmount() {
		return total;
	}

	public String getSellerName() {
		return sellerName;
	}
	
	
}
