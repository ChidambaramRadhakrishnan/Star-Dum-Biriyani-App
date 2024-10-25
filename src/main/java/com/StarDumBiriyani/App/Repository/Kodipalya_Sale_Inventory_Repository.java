package com.StarDumBiriyani.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StarDumBiriyani.App.KodiPalya_Entries.Kodipalya_Sale_Inventory;

@Repository
public interface Kodipalya_Sale_Inventory_Repository extends JpaRepository<Kodipalya_Sale_Inventory, Integer>{
	
}
