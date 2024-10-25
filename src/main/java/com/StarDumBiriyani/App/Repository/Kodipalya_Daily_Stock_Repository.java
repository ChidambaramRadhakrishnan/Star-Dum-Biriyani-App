package com.StarDumBiriyani.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.StarDumBiriyani.App.KodiPalya_Entries.Kodipalya_Daily_Stock;

@Repository
public interface Kodipalya_Daily_Stock_Repository extends JpaRepository<Kodipalya_Daily_Stock, Integer>{

	@Query(value = "select * from kodipalya_daily_stock order by id desc limit 1;", nativeQuery = true)
	List<Kodipalya_Daily_Stock> getAllDailyStock();
	
}
