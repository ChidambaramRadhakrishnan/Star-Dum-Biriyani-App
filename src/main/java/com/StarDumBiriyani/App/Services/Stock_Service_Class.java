package com.StarDumBiriyani.App.Services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StarDumBiriyani.App.Entries.All_Shops;
import com.StarDumBiriyani.App.Entries.Daily_Stock_Entity;
import com.StarDumBiriyani.App.Entries.Expenditure_Inventory_Entity;
import com.StarDumBiriyani.App.Entries.Stock_Entity;
import com.StarDumBiriyani.App.Functionalities.Essential_Operations;
import com.StarDumBiriyani.App.Repository.AllShop_Repository;
import com.StarDumBiriyani.App.Repository.Daily_Stock_Repository;
import com.StarDumBiriyani.App.Repository.Expenditure_Inventory_Repository;
import com.StarDumBiriyani.App.Repository.Stock_Management_Repository;

import jakarta.servlet.http.HttpSession;

@Service
public class Stock_Service_Class {

	@Autowired
	Stock_Management_Repository stock_Management_Repository;
	
	@Autowired
	Daily_Stock_Repository daily_Stock_Repository;
	
	@Autowired
	AllShop_Repository allShop_Repository;
	
	@Autowired
	Expenditure_Inventory_Repository expenditure_Inventory_Repository;
	
	public String add_New_Stock_Entry(int riceExpense, int riceQty, int oilExpense, int oilQty, int gingerGarlicExpense,
			int gingerGarlicQty, int OnionExpense, int OnionQty, int eggStock, int eggTrayCount,
			int masalaItems, int specialSalt,int foodColour, int toothStickExpense, int jeeraSweetExpense, int waterBottle,
			int parcelCoverExpense, int largeCover, int mediumCover, int smallCover, int gravyCover, int TotalCarryBagExpense,
			int largeCarryBag, int mediumCarryBag, int smallCarryBag, int plateCoverExpense, int plateCoverQty, int foodContainerExpense,
			int foodContainerQty, int RubberExpense, String notes, int id, HttpSession session) {
		
		String page = "";
		
		All_Shops all_Shops = allShop_Repository.findById(id).get();
		
		Stock_Entity stock_entity = new Stock_Entity();
		
		//before insert rice, oil, ginger garlic qty
		List<Stock_Entity> stock= stock_Management_Repository.getRice_Oil_GG_Qty();
			
			try {
				int ide = stock.stream().findFirst().get().getId();
				
				System.out.println("---------------------  "+ide);
				

//				
//				int update_Set_Rice_Qty = Existing_Rice_Qty + riceQty;
//				int update_Set_Oil_Qty = Existing_Oil_Qty + oilQty;
//				int update_Set_Ginger_Garlic_Qty = Existing_Ginger_Garlic_Stock + gingerGarlicQty;
				
				
				// Daily Stock
				
				List<Daily_Stock_Entity>  daily_Stock= daily_Stock_Repository.getDailyStock(id);
				
				int daily_rice_Qty= daily_Stock.stream().findFirst().get().getRice_Stock_Qty();
				
				int daily_oil_Qty =daily_Stock.stream().findFirst().get().getOil_Stock_Qty();
				
				int daily_ginger_garlic_Qty =daily_Stock.stream().findFirst().get().getGinger_Garlic_Stock_Qty();
				
				int updated_rice_Qty = riceQty + daily_rice_Qty;
				
				int updated_oil_Qty = oilQty + daily_oil_Qty;
				
				int updated_ginget_garlic_Qty = gingerGarlicQty + daily_ginger_garlic_Qty;
				
				//
				
				System.out.println("-----------------"+"after some line of code"+" id is --- "+ id);

				String update_Status = stock_Management_Repository.getUpdateStatus(id, Essential_Operations.getToday_Date());
				
				System.out.println("--------------------------- updated   --   -> " + update_Status);
				//
				
				//  
				/*
				 * Existing dated stock is there
				 * then it'll get updated it on same record
				 */
				
				if("Yes".equals(update_Status) || update_Status != null) {
					
					List<Expenditure_Inventory_Entity> expenditure_Inventory_Entities= expenditure_Inventory_Repository.get_Expenditure_Inventory(id);
					
					Integer Existing_Rice_Qty = stock.stream().findFirst().get().getRice_Qty();
					
					Integer Existing_Oil_Qty = stock.stream().findFirst().get().getOil_Qty();
					Integer Existing_Ginger_Garlic_Stock = stock.stream().findFirst().get().getGingerGarlic_Qty();
					
//					int expenditure_rice_Used =expenditure_Inventory_Entities.stream().findFirst().get().getRice_Used();
//					int expenditure_oil_Used = expenditure_Inventory_Entities.stream().findFirst().get().getOil_Used() ;
//					int expenditure_ginger_garlic_Used = expenditure_Inventory_Entities.stream().findFirst().get().getGinger_Garlic_used();
					
					
					
					int updated_Rice_Qty = daily_rice_Qty + riceQty;
					int updated_Oil_Qty = daily_oil_Qty + oilQty;
					int updated_Ginger_Garlic_Qty = daily_ginger_garlic_Qty + gingerGarlicQty;
					
					
 //					
					stock_Management_Repository.updateStock(TotalCarryBagExpense,eggTrayCount, eggStock, foodColour, foodContainerExpense,
							foodContainerQty, gingerGarlicExpense, gingerGarlicQty, gravyCover, masalaItems, jeeraSweetExpense, largeCarryBag,
							largeCover, mediumCarryBag, mediumCover, notes, oilExpense, oilQty, OnionExpense, OnionQty, parcelCoverExpense, plateCoverExpense,
							plateCoverQty, riceExpense, riceQty, RubberExpense, smallCarryBag, smallCover, specialSalt, toothStickExpense, waterBottle,"Yes"
							,id, Essential_Operations.getToday_Date());
					
					daily_Stock_Repository.updateDailyStock(updated_Rice_Qty, updated_Oil_Qty, updated_Ginger_Garlic_Qty, id, Essential_Operations.getToday_Date());
					
					System.out.println(" ---- -  + new updating stocks");
					
					page = "success";

					/*
					 * If there is no record found in database
					 * it'll create new row of data
					 */
				}else {
					
					System.out.println("doing here ---- after upading ");
					
					// Rice 
					stock_entity.setRice_Amount(riceExpense);
					stock_entity.setRice_Qty(updated_rice_Qty);
					// Oil
					stock_entity.setOil_Amount(oilExpense);
					stock_entity.setOil_Qty(updated_oil_Qty);
					// Ginger Garlic
					stock_entity.setGingerGarlic_Amount(gingerGarlicExpense);
					stock_entity.setGingerGarlic_Qty(updated_ginget_garlic_Qty);
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
					stock_entity.setUpdated("Yes");
					//
					stock_entity.setAll_Shops(all_Shops);
					
					Daily_Stock_Entity daily_Stock_Entity = new Daily_Stock_Entity();
					
					daily_Stock_Entity.setGinger_Garlic_Stock_Qty(updated_ginget_garlic_Qty);
					daily_Stock_Entity.setOil_Stock_Qty(updated_oil_Qty);
					daily_Stock_Entity.setRice_Stock_Qty(updated_rice_Qty);
					daily_Stock_Entity.setStock_updated_Date(Essential_Operations.getToday_Date());
					daily_Stock_Entity.setUpdated("Yes");

					daily_Stock_Repository.save(daily_Stock_Entity);
					
					daily_Stock_Entity.setAll_Shops(all_Shops);
					
					stock_Management_Repository.save(stock_entity);
					
					page = "success";
					
				}	
				/*
				 * A new and Fresh Record will placed.
				 */
			}catch (Exception e) {
				// TODO: handle exception
				
				e.printStackTrace();
				
				System.out.println(" doing here --------------................................... ");
				
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
				stock_entity.setUpdated("Yes");
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
				
				page = "success";
			}
		return page;
	}
	
	public String getStockUpdateDate(int id) {
		return stock_Management_Repository.getStockDate(id);
	}
}
