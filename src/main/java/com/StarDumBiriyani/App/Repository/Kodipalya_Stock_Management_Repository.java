package com.StarDumBiriyani.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.StarDumBiriyani.App.KodiPalya_Entries.KodiPalya_Stock;

public interface Kodipalya_Stock_Management_Repository extends JpaRepository<KodiPalya_Stock, Integer>{

	@Query(value = "select * from kodi_palya_stock order by id desc limit 1;",nativeQuery = true)
	List<KodiPalya_Stock> getRice_Oil_GG_Qty();
}
