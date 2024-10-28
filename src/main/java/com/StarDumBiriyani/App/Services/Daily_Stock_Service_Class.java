package com.StarDumBiriyani.App.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.StarDumBiriyani.App.KodiPalya_Entries.Daily_Stock_Entity;
import com.StarDumBiriyani.App.Repository.AllShop_Repository;
import com.StarDumBiriyani.App.Repository.Daily_Stock_Repository;
import com.StarDumBiriyani.App.Repository.Stock_Management_Repository;
import com.StarDumBiriyani.App.Shops.All_Shops;

import jakarta.servlet.http.HttpSession;

@Service
public class Daily_Stock_Service_Class {

	@Autowired
	Daily_Stock_Repository daily_Stock_Repository;

	@Autowired
	AllShop_Repository allShop_Repository;

	public ResponseEntity<Daily_Stock_Entity> add_Stock_Item_Entries(int rice_Stock_Qty, int oil_Stock_Qty,
			int ginger_Garlic_Stock_Qty, int id) {
		
		

		All_Shops all_Shops = allShop_Repository.findById(id).get();

		Daily_Stock_Entity daily_Stock = new Daily_Stock_Entity();

		daily_Stock.setRice_Stock_Qty(rice_Stock_Qty);

		daily_Stock.setOil_Stock_Qty(oil_Stock_Qty);

		daily_Stock.setGinger_Garlic_Stock_Qty(ginger_Garlic_Stock_Qty);

		daily_Stock.setAll_Shops(all_Shops);

		daily_Stock_Repository.save(daily_Stock);

		return ResponseEntity.ok(daily_Stock);

	}
}
