package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SaleSumaryProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	
	@Query("SELECT t1.id as id, t1.date as date, t1.amount as amount, t2.name as sellerName " 
			+ "FROM Sale as t1 "
			+ "INNER JOIN Seller as t2 ON t1.seller.id = t2.id "
			+ "WHERE t1.date > :minDate AND t1.date < :maxDate AND "
			+ "UPPER( t2.name ) like UPPER( CONCAT ('%', :name, '%'))")
	Page<SaleReportProjection> searchBySeller(String name, LocalDate minDate, LocalDate maxDate, Pageable pageable);
	
	@Query("SELECT obj1.name as sellerName , SUM( obj2.amount) as total "
			+ "FROM Seller as obj1 "
			+ "INNER JOIN Sale as obj2 ON obj2.seller.id = obj1.id "
			+ "WHERE obj2.date BETWEEN :minDate AND :maxDate "
			+ "GROUP BY obj1.name")
	Page<SaleSumaryProjection> searchTotalAmountBySeller(LocalDate minDate, LocalDate maxDate, Pageable pageable);
	
}
