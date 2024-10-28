package com.StarDumBiriyani.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.StarDumBiriyani.App.KodiPalya_Entries.Stock_Entity;

public interface Stock_Management_Repository extends JpaRepository<Stock_Entity, Integer>{

	@Query(value = "select * from stock_entity order by id desc limit 1;",nativeQuery = true)
	List<Stock_Entity> getRice_Oil_GG_Qty();
}
