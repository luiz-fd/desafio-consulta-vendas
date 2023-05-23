package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleReportProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	
	@Query("SELECT t1.id as id, t1.date as date, t1.amount as amount, t2.name as sellerName " 
			+ "FROM Sale as t1 "
			+ "INNER JOIN Seller as t2 ON t1.seller.id = t2.id "
			+ "WHERE t1.date > :minDate AND t1.date < :maxDate AND "
			+ "UPPER( t2.name ) like UPPER( CONCAT ('%', :name, '%'))")
	Page<SaleReportProjection> searchBySeller(String name, LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
