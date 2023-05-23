package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleReportDTO> findBySellerName(String name, String minDate, String maxDate, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate maxData = today;
		LocalDate minData = today.minusYears(1L);
		if(!maxDate.isEmpty()) {
			maxData = LocalDate.parse(maxDate);
		}
		if(!minDate.isEmpty()) {
			minData = LocalDate.parse(minDate);
		}
		Page<SaleReportProjection> result = repository.searchBySeller(name, minData, maxData, pageable);
		Page<SaleReportDTO> resultDTO = result.map(x -> new SaleReportDTO(x));
		return resultDTO;
	}
}
