package com.StarDumBiriyani.App.Services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StarDumBiriyani.App.Entries.All_Shops;
import com.StarDumBiriyani.App.Entries.Daily_Stock_Entity;
import com.StarDumBiriyani.App.Entries.Stock_Entity;
import com.StarDumBiriyani.App.Functionalities.Essential_Operations;
import com.StarDumBiriyani.App.Repository.AllShop_Repository;
import com.StarDumBiriyani.App.Repository.Daily_Stock_Repository;
import com.StarDumBiriyani.App.Repository.Stock_Management_Repository;

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
		
		Stock_Entity stock_entity = new Stock_Entity();
		
		//before insert rice, oil, ginger garlic qty
		List<Stock_Entity> stock= stock_Management_Repository.getRice_Oil_GG_Qty();
		
//		System.out.println(stock.stream().findFirst().get().getRice_Qty()+" ---------------------");
		
		System.out.println("---------------- ----------------------------- -------------------------");
		
		try {
			stock.stream().findFirst().get().getRice_Qty();
		}catch (NoSuchElementException e) {
			// Rice 
			stock_entity.setRice_Amount(riceExpense);
			stock_entity.setRice_Qty(riceQty);
			// Oil
			stock_entity.setOil_Amount(oilExpense);
			stock_entity.setOil_Qty(oilQty);
			// Ginger Garlic
			stock_entity.setGingerGarlic_Amount(gingerGarlicExpense);
			stock_entity.setGingerGarlic_Qty(gingerGarlicQty);
			// Onion 
			stock_entity.setOnion_Amount(OnionExpense);
			stock_entity.setOnion_Qty(OnionQty);
			// Egg
			stock_entity.setEgg__Amount(eggStock);
			stock_entity.setEgg_Qty(eggTrayCount);
			// Masala Items
			stock_entity.setSpecialSalt_Amount(specialSalt);
			//
			stock_entity.setMasalaItems_Amount(masalaItems);
			// Food Colour
			stock_entity.setFoodColor_Amount(foodColour);
			// Tooth Stick
			stock_entity.setToothStick_Amount(toothStickExpense);
			// Jeera Sweet
			stock_entity.setJeeraandSweet_Amount(jeeraSweetExpense);
			// Water Bottle
			stock_entity.setWaterBottle_Amount(waterBottle);
			//
			stock_entity.setParcelCover_Amount(parcelCoverExpense);
			//
			stock_entity.setLargeParcelCover_Qty(largeCover);
			//
			stock_entity.setMediumParcelCover_Qty(mediumCover);
			//
			stock_entity.setSmallParcelCover_Qty(smallCover);
			//
			stock_entity.setGravyCover_Qty(gravyCover);
			//
			stock_entity.setCarryBag_Amount(TotalCarryBagExpense);
			//
			stock_entity.setLargeCarryBag_Qty(largeCarryBag);
			//
			stock_entity.setMediumCarryBag_Qty(mediumCarryBag);
			//
			stock_entity.setSmallCarryBag_Qty(smallCarryBag);
			//
			stock_entity.setPlateCover_Amount(plateCoverExpense);
			//
			stock_entity.setPlateCover_Qty(plateCoverQty);
			//
			stock_entity.setFoodContainer_Amount(foodContainerExpense);
			//
			stock_entity.setFoodContainer_Qty(foodContainerQty);
			//
			stock_entity.setRubberBand_Amount(RubberExpense);
			//
			stock_entity.setNotes(notes);
			//
			stock_entity.setStock_Fill_Date(Essential_Operations.getToday_Date());
			//
			stock_entity.setAll_Shops(all_Shops);
			
			Daily_Stock_Entity daily_Stock = new Daily_Stock_Entity();
			
			daily_Stock.setGinger_Garlic_Stock_Qty(gingerGarlicQty);
			daily_Stock.setOil_Stock_Qty(oilQty);
			daily_Stock.setRice_Stock_Qty(riceQty);
			daily_Stock.setStock_updated_Date(Essential_Operations.getToday_Date());
			daily_Stock.setUpdated("Yes");

			daily_Stock_Repository.save(daily_Stock);
			
			daily_Stock.setAll_Shops(all_Shops);
			
			stock_Management_Repository.save(stock_entity);
		}
		
		Integer Existing_Rice_Qty = stock.stream().findFirst().get().getRice_Qty();
		
		int Existing_Oil_Qty = stock.stream().findFirst().get().getOil_Qty();
		int Existing_Ginger_Garlic_Stock = stock.stream().findFirst().get().getGingerGarlic_Qty();
		
		int update_Set_Rice_Qty = Existing_Rice_Qty + riceQty;
		int update_Set_Oil_Qty = Existing_Oil_Qty + oilQty;
		int update_Set_Ginger_Garlic_Qty = Existing_Ginger_Garlic_Stock + gingerGarlicQty;
		System.out.println(Existing_Ginger_Garlic_Stock+" - "+ Existing_Oil_Qty+" - "+" Exi"+Existing_Rice_Qty+" ---- ");
		
		// Rice 
		stock_entity.setRice_Amount(riceExpense);
		stock_entity.setRice_Qty(update_Set_Rice_Qty);
		// Oil
		stock_entity.setOil_Amount(oilExpense);
		stock_entity.setOil_Qty(update_Set_Oil_Qty);
		// Ginger Garlic
		stock_entity.setGingerGarlic_Amount(gingerGarlicExpense);
		stock_entity.setGingerGarlic_Qty(update_Set_Ginger_Garlic_Qty);
		// Onion 
		stock_entity.setOnion_Amount(OnionExpense);
		stock_entity.setOnion_Qty(OnionQty);
		// Egg
		stock_entity.setEgg__Amount(eggStock);
		stock_entity.setEgg_Qty(eggTrayCount);
		// Masala Items
		stock_entity.setSpecialSalt_Amount(specialSalt);
		//
		stock_entity.setMasalaItems_Amount(masalaItems);
		// Food Colour
		stock_entity.setFoodColor_Amount(foodColour);
		// Tooth Stick
		stock_entity.setToothStick_Amount(toothStickExpense);
		// Jeera Sweet
		stock_entity.setJeeraandSweet_Amount(jeeraSweetExpense);
		// Water Bottle
		stock_entity.setWaterBottle_Amount(waterBottle);
		//
		stock_entity.setParcelCover_Amount(parcelCoverExpense);
		//
		stock_entity.setLargeParcelCover_Qty(largeCover);
		//
		stock_entity.setMediumParcelCover_Qty(mediumCover);
		//
		stock_entity.setSmallParcelCover_Qty(smallCover);
		//
		stock_entity.setGravyCover_Qty(gravyCover);
		//
		stock_entity.setCarryBag_Amount(TotalCarryBagExpense);
		//
		stock_entity.setLargeCarryBag_Qty(largeCarryBag);
		//
		stock_entity.setMediumCarryBag_Qty(mediumCarryBag);
		//
		stock_entity.setSmallCarryBag_Qty(smallCarryBag);
		//
		stock_entity.setPlateCover_Amount(plateCoverExpense);
		//
		stock_entity.setPlateCover_Qty(plateCoverQty);
		//
		stock_entity.setFoodContainer_Amount(foodContainerExpense);
		//
		stock_entity.setFoodContainer_Qty(foodContainerQty);
		//
		stock_entity.setRubberBand_Amount(RubberExpense);
		//
		stock_entity.setNotes(notes);
		//
		stock_entity.setStock_Fill_Date(Essential_Operations.getToday_Date());
		//
		stock_entity.setAll_Shops(all_Shops);
		
		Daily_Stock_Entity daily_Stock = new Daily_Stock_Entity();
		
		daily_Stock.setGinger_Garlic_Stock_Qty(update_Set_Ginger_Garlic_Qty);
		daily_Stock.setOil_Stock_Qty(update_Set_Oil_Qty);
		daily_Stock.setRice_Stock_Qty(update_Set_Rice_Qty);
		daily_Stock.setStock_updated_Date(Essential_Operations.getToday_Date());
		daily_Stock.setUpdated("Yes");

		daily_Stock_Repository.save(daily_Stock);
		
		daily_Stock.setAll_Shops(all_Shops);
		
		stock_Management_Repository.save(stock_entity);
		return "success";
	}
	
	public String getStockUpdateDate(int id) {
		return stock_Management_Repository.getStockDate(id);
	}
}
