package com.StarDumBiriyani.App.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.StarDumBiriyani.App.KodiPalya_Entries.Kodipalya_Daily_Stock;
import com.StarDumBiriyani.App.Repository.AllShop_Repository;
import com.StarDumBiriyani.App.Repository.Kodipalya_Daily_Stock_Repository;
import com.StarDumBiriyani.App.Repository.Kodipalya_Stock_Management_Repository;
import com.StarDumBiriyani.App.Shops.All_Shops;

@Service
public class Daily_Stock_Service_Class {

	@Autowired
	Kodipalya_Daily_Stock_Repository daily_Stock_Repository;

	@Autowired
	AllShop_Repository allShop_Repository;

	public ResponseEntity<Kodipalya_Daily_Stock> add_Stock_Item_Entries(int rice_Stock_Qty, int oil_Stock_Qty,
			int ginger_Garlic_Stock_Qty) {

		All_Shops all_Shops = allShop_Repository.findById(1).get();

		Kodipalya_Daily_Stock daily_Stock = new Kodipalya_Daily_Stock();

		daily_Stock.setRice_Stock_Qty(rice_Stock_Qty);

		daily_Stock.setOil_Stock_Qty(oil_Stock_Qty);

		daily_Stock.setGinger_Garlic_Stock_Qty(ginger_Garlic_Stock_Qty);

		daily_Stock.setAll_Shops(all_Shops);

		daily_Stock_Repository.save(daily_Stock);

		return ResponseEntity.ok(daily_Stock);

	}
}
