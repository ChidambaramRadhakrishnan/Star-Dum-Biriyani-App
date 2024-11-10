package com.StarDumBiriyani.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.StarDumBiriyani.App.Entries.Daily_Stock_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface Daily_Stock_Repository extends JpaRepository<Daily_Stock_Entity, Integer>{

	@Query(value = "select * from daily_stock_entity where shop_id =:id order by id desc limit 1;", nativeQuery = true)
	List<Daily_Stock_Entity> getDailyStock(int id);
	
	
	@Query(value = "select updated from daily_stock_entity where shop_id =:id and stock_updated_date =:date order by id desc limit 1;",nativeQuery = true)
	String getUpdateDate(int id, String date);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE daily_stock_entity SET\r\n"
			+ "ginger_garlic_stock_qty =:ginger_garlic_stock_qty,\r\n"
			+ "oil_stock_qty =:oil_stock_qty,\r\n"
			+ "rice_stock_qty =:rice_stock_qty\r\n"
			+ "WHERE shop_id =:id and stock_updated_date = :stock_date;", nativeQuery = true)
	int updateDailyStock(int rice_stock_qty,int oil_stock_qty,int ginger_garlic_stock_qty, int id, String stock_date);
	
}
