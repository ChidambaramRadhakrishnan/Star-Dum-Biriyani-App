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
		/*
		Try block is going to
		firstly, check id is there or not in db
		if it's there, it'll update it.
		If record is not there, it'll create new record
		 */
			
			try {
//				int ide = stock.stream().findFirst().get().getId();
				
//				System.out.println("---------------------  "+ide);
							
				// Daily Stock
				
				List<Stock_Entity> stock=stock_Management_Repository.getLastStockRecord(id);
//				get previous date record
				int daily_rice_Qty= stock.stream().findFirst().get().getRice_Qty();
				int daily_oil_Qty =stock.stream().findFirst().get().getOil_Qty();
				int daily_ginger_garlic_Qty =stock.stream().findFirst().get().getGingerGarlic_Qty();
				String last_Inventory_Date = stock.stream().findFirst().get().getInventoryDate();
				System.out.println("-----------------------------------------------");
				System.out.println(daily_oil_Qty+" drq"+daily_oil_Qty+" doq"+ daily_ginger_garlic_Qty+" dggq");
//				adding the rice, oil and ginger qty with existing data
				int updated_rice_Qty = riceQty + daily_rice_Qty;
				int updated_oil_Qty = oilQty + daily_oil_Qty;
				int updated_ginget_garlic_Qty = gingerGarlicQty + daily_ginger_garlic_Qty;
				//
				String update_Status = stock_Management_Repository.getUpdateStatus(id, Essential_Operations.getToday_Date());
				//
				
				//  
				/*
				 * Existing dated stock is there
				 * then it'll get updated it on same record
				 */
				
				if("Yes".equals(update_Status) || update_Status != null) {
					
					List<Expenditure_Inventory_Entity> expenditure_Inventory_Entities= expenditure_Inventory_Repository.get_Expenditure_Inventory(id);
					
//					Integer Existing_Rice_Qty = stock.stream().findFirst().get().getRice_Qty();
//
//					Integer Existing_Oil_Qty = stock.stream().findFirst().get().getOil_Qty();
//					Integer Existing_Ginger_Garlic_Stock = stock.stream().findFirst().get().getGingerGarlic_Qty();
 //					
					stock_Management_Repository.updateStock(TotalCarryBagExpense,eggTrayCount, eggStock, foodColour, foodContainerExpense,
							foodContainerQty, gingerGarlicExpense, updated_ginget_garlic_Qty, gravyCover, masalaItems, jeeraSweetExpense, largeCarryBag,
							largeCover, mediumCarryBag, mediumCover, notes, oilExpense, updated_oil_Qty, OnionExpense, OnionQty, parcelCoverExpense, plateCoverExpense,
							plateCoverQty, riceExpense, updated_rice_Qty, RubberExpense, smallCarryBag, smallCover, specialSalt, toothStickExpense, waterBottle,"Yes"
							,id, Essential_Operations.getToday_Date());
					
					System.out.println(" ---- -  + new updating stocks");
					
					page = "redirect:/loggedInventory?stockUpdateSuccess";

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
					stock_entity.setInventoryDate(last_Inventory_Date);
					//
					stock_entity.setAll_Shops(all_Shops);
					
					stock_Management_Repository.save(stock_entity);

					page = "redirect:/loggedInventory?stockSuccess";
					
				}	
				/*
				 * A new and Fresh Record will place.
				 */
			}catch (Exception e) {
				// TODO: handle exception
				
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
				
				stock_Management_Repository.save(stock_entity);

				page = "redirect:/loggedInventory?stockSuccess";
			}
		return page;
	}
	
	public String getStockUpdateDate(int id) {
		return stock_Management_Repository.getStockDate(id);
	}

	public List<Stock_Entity> getLastStockUpdateRecord(int id){
		return stock_Management_Repository.getLastStockRecord(id);
	}
}
