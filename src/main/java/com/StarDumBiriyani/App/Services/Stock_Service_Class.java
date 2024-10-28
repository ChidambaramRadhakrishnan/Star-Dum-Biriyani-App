package com.StarDumBiriyani.App.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StarDumBiriyani.App.KodiPalya_Entries.Stock_Entity;
import com.StarDumBiriyani.App.Functionalities.Essential_Operations;
import com.StarDumBiriyani.App.KodiPalya_Entries.Daily_Stock_Entity;
import com.StarDumBiriyani.App.Repository.AllShop_Repository;
import com.StarDumBiriyani.App.Repository.Daily_Stock_Repository;
import com.StarDumBiriyani.App.Repository.Stock_Management_Repository;
import com.StarDumBiriyani.App.Shops.All_Shops;

@Service
public class Stock_Service_Class {

	@Autowired
	Stock_Management_Repository stock_Management_Repository;
	
	@Autowired
	Daily_Stock_Repository daily_Stock_Repository;
	
	@Autowired
	AllShop_Repository allShop_Repository;
	
	public String add_New_Stock_Entry(int riceExpense, int riceQty, int oilExpense, int oilQty, int gingerGarlicExpense,
			int gingerGarlicQty, int OnionExpense, int OnionQty, int eggStock, int eggTrayCount,
			int masalaItems, int specialSalt,int foodColour, int toothStickExpense, int jeeraSweetExpense, int waterBottle,
			int parcelCoverExpense, int largeCover, int mediumCover, int smallCover, int gravyCover, int TotalCarryBagExpense,
			int largeCarryBag, int mediumCarryBag, int smallCarryBag, int plateCoverExpense, int plateCoverQty, int foodContainerExpense,
			int foodContainerQty, int RubberExpense, String notes, int id) {
		
		All_Shops all_Shops = allShop_Repository.findById(id).get();
		
		Stock_Entity kodiPalya_Stock = new Stock_Entity();
		
		//before insert rice, oil, ginger garlic qty
		List<Stock_Entity> stock= stock_Management_Repository.getRice_Oil_GG_Qty();
		
		System.out.println(stock.stream().findFirst().get().getRice_Qty()+" ---------------------");
		
		int Existing_Rice_Qty = stock.stream().findFirst().get().getRice_Qty();
		int Existing_Oil_Qty = stock.stream().findFirst().get().getOil_Qty();
		int Existing_Ginger_Garlic_Stock = stock.stream().findFirst().get().getGingerGarlic_Qty();
		
		int update_Set_Rice_Qty = Existing_Rice_Qty + riceQty;
		int update_Set_Oil_Qty = Existing_Oil_Qty + oilQty;
		int update_Set_Ginger_Garlic_Qty = Existing_Ginger_Garlic_Stock + gingerGarlicQty;
		System.out.println(Existing_Ginger_Garlic_Stock+" - "+ Existing_Oil_Qty+" - "+" Exi"+Existing_Rice_Qty+" ---- ");
		
		// Rice 
		kodiPalya_Stock.setRice_Amount(riceExpense);
		kodiPalya_Stock.setRice_Qty(update_Set_Rice_Qty);
		// Oil
		kodiPalya_Stock.setOil_Amount(oilExpense);
		kodiPalya_Stock.setOil_Qty(update_Set_Oil_Qty);
		// Ginger Garlic
		kodiPalya_Stock.setGingerGarlic_Amount(gingerGarlicExpense);
		kodiPalya_Stock.setGingerGarlic_Qty(update_Set_Ginger_Garlic_Qty);
		// Onion 
		kodiPalya_Stock.setOnion_Amount(OnionExpense);
		kodiPalya_Stock.setOnion_Qty(OnionQty);
		// Egg
		kodiPalya_Stock.setEgg__Amount(eggStock);
		kodiPalya_Stock.setEgg_Qty(eggTrayCount);
		// Masala Items
		kodiPalya_Stock.setSpecialSalt_Amount(specialSalt);
		//
		kodiPalya_Stock.setMasalaItems_Amount(masalaItems);
		// Food Colour
		kodiPalya_Stock.setFoodColor_Amount(foodColour);
		// Tooth Stick
		kodiPalya_Stock.setToothStick_Amount(toothStickExpense);
		// Jeera Sweet
		kodiPalya_Stock.setJeeraandSweet_Amount(jeeraSweetExpense);
		// Water Bottle
		kodiPalya_Stock.setWaterBottle_Amount(waterBottle);
		//
		kodiPalya_Stock.setParcelCover_Amount(parcelCoverExpense);
		//
		kodiPalya_Stock.setLargeParcelCover_Qty(largeCover);
		//
		kodiPalya_Stock.setMediumParcelCover_Qty(mediumCover);
		//
		kodiPalya_Stock.setSmallParcelCover_Qty(smallCover);
		//
		kodiPalya_Stock.setGravyCover_Qty(gravyCover);
		//
		kodiPalya_Stock.setCarryBag_Amount(TotalCarryBagExpense);
		//
		kodiPalya_Stock.setLargeCarryBag_Qty(largeCarryBag);
		//
		kodiPalya_Stock.setMediumCarryBag_Qty(mediumCarryBag);
		//
		kodiPalya_Stock.setSmallCarryBag_Qty(smallCarryBag);
		//
		kodiPalya_Stock.setPlateCover_Amount(plateCoverExpense);
		//
		kodiPalya_Stock.setPlateCover_Qty(plateCoverQty);
		//
		kodiPalya_Stock.setFoodContainer_Amount(foodContainerExpense);
		//
		kodiPalya_Stock.setFoodContainer_Qty(foodContainerQty);
		//
		kodiPalya_Stock.setRubberBand_Amount(RubberExpense);
		//
		kodiPalya_Stock.setNotes(notes);
		//
		kodiPalya_Stock.setStock_Fill_Date(Essential_Operations.getToday_Date());
		//
		kodiPalya_Stock.setAll_Shops(all_Shops);
		
		Daily_Stock_Entity daily_Stock = new Daily_Stock_Entity();
		
		daily_Stock.setGinger_Garlic_Stock_Qty(update_Set_Ginger_Garlic_Qty);
		daily_Stock.setOil_Stock_Qty(update_Set_Oil_Qty);
		daily_Stock.setRice_Stock_Qty(update_Set_Rice_Qty);
		daily_Stock.setStock_updated_Date(Essential_Operations.getToday_Date());
		daily_Stock.setUpdated("Yes");

		daily_Stock_Repository.save(daily_Stock);
		
		daily_Stock.setAll_Shops(all_Shops);
		
		stock_Management_Repository.save(kodiPalya_Stock);
		return "success";
	}
}
