package com.StarDumBiriyani.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.StarDumBiriyani.App.KodiPalya_Entries.Daily_Stock_Entity;

@Repository
public interface Daily_Stock_Repository extends JpaRepository<Daily_Stock_Entity, Integer>{

	@Query(value = "select * from daily_stock_entity where shop_id =:id order by id desc limit 1;", nativeQuery = true)
	List<Daily_Stock_Entity> getDailyStock(int id);
	
}
