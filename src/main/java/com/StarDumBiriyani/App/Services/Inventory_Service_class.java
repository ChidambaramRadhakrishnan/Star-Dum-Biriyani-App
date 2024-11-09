package com.StarDumBiriyani.App.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StarDumBiriyani.App.Entries.All_Shops;
import com.StarDumBiriyani.App.Entries.Daily_Stock_Entity;
import com.StarDumBiriyani.App.Entries.Expenditure_Inventory_Entity;
import com.StarDumBiriyani.App.Entries.Sale_Inventory_Entity;
import com.StarDumBiriyani.App.Functionalities.Essential_Operations;
import com.StarDumBiriyani.App.Repository.AllShop_Repository;
import com.StarDumBiriyani.App.Repository.Daily_Stock_Repository;
import com.StarDumBiriyani.App.Repository.Expenditure_Inventory_Repository;
import com.StarDumBiriyani.App.Repository.Sale_Inventory_Repository;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Service
public class Inventory_Service_class {

	@Autowired
	AllShop_Repository allShop_Repository;

	@Autowired
	Sale_Inventory_Repository sale_Inventory_Repository;

	@Autowired
	Expenditure_Inventory_Repository expenditure_Inventory_Repository;

	@Autowired
	Daily_Stock_Repository daily_Stock_Repository;
	
	
	public List<Sale_Inventory_Entity> getAll_Sale_Inventory_Data(int id){
		return sale_Inventory_Repository.getSale_Inventory_All_Data(id);
	}
	
	public List<Expenditure_Inventory_Entity> getAll_Expenditure_Inventory_Data(int id){
		return expenditure_Inventory_Repository.get_Expenditure_Inventory(id);
	}
	
	

	public void addNewInventory(Integer totalSale, int totalCash, int totalUPI, int cashBalance, int upiBalance,
			int totalExpenditure, int chickenExpense, int biriyaniChicken, int kababChicken, int gasExpense,
			int saltExpense, int corianderMintExpense, int GreenChillyExpense, int curdExpense, int otherExpense,
			String notes, int biriyani_chicken_Stock, int kabab_chicken_Stock, int riceUsed, int oilUsed,
			int ginger_Garlic_Used, int id) {

		try {

			All_Shops all_Shops = allShop_Repository.findById(id).get();

			String expenditure_Status = expenditure_Inventory_Repository.get_Update_StatusByID(all_Shops.getId(), Essential_Operations.getToday_Date());

			String Sale_Status = sale_Inventory_Repository.get_Update_StatusByID(all_Shops.getId(),Essential_Operations.getToday_Date());

			List<Daily_Stock_Entity> stock = daily_Stock_Repository.getDailyStock(id);
			int Existing_Rice_Qty = stock.stream().findFirst().get().getRice_Stock_Qty();
			int Existing_Oil_Qty = stock.stream().findFirst().get().getOil_Stock_Qty();
			int Existing_Ginger_Garlic_Qty = stock.stream().findFirst().get().getGinger_Garlic_Stock_Qty();

			int update_Rice_Used_Qty = Existing_Rice_Qty - riceUsed;
			int update_Oil_Used_Qty = Existing_Oil_Qty - oilUsed;
			int update_Ginger_Garlic_Used_Qty = Existing_Ginger_Garlic_Qty - ginger_Garlic_Used;

			Sale_Inventory_Entity sale_Inventory = new Sale_Inventory_Entity();
			
			System.out.println("-------------- - --- " + totalExpenditure+"  -- -- "+ expenditure_Status  +" -- -- -"+"  --- Smiley : "+ Sale_Status);

			if ("Yes".equals(expenditure_Status) || expenditure_Status != null && "Yes".equals(Sale_Status) || Sale_Status != null) {
				
//				int revised_Rice_Qty = (Integer) session.getAttribute("rice_used_Qty") - riceUsed;
//				int revised_Oil_Qty = Existing_Oil_Qty - oilUsed;
//				int revised_Giner_Garlic_Qty = Existing_Ginger_Garlic_Qty - ginger_Garlic_Used;
//				
//				System.out.println("-------------"+" rice : "+ riceUsed);
//				System.out.println("-----  -- "+ "All Rice : "+ (Integer) session.getAttribute("rice_used_Qty"));
//				System.out.println("-------------"+" oil : "+ revised_Oil_Qty);
//				System.out.println("-------------"+" giner : "+ revised_Giner_Garlic_Qty);

				sale_Inventory_Repository.updateExisting(totalCash, cashBalance, totalSale, totalUPI, upiBalance,
						all_Shops.getId(), Essential_Operations.getToday_Date());
				
				expenditure_Inventory_Repository.updateExistingEntry(biriyaniChicken, biriyani_chicken_Stock, chickenExpense, 
						corianderMintExpense, curdExpense, gasExpense, GreenChillyExpense, kababChicken, kabab_chicken_Stock, 
						otherExpense, saltExpense, totalExpenditure, all_Shops.getId(), Essential_Operations.getToday_Date());
				

			} else {
				
				System.out.println("I'm second");
				

				sale_Inventory.setTotal_sale(totalSale);
				sale_Inventory.setCash(totalCash);
				sale_Inventory.setUpi(totalUPI);
				sale_Inventory.setCash_balance(cashBalance);
				sale_Inventory.setUpi_balance(upiBalance);
				sale_Inventory.setSale_Inventory_Date(Essential_Operations.getToday_Date());
				sale_Inventory.setUpdated("Yes");
				sale_Inventory.setAll_Shops(all_Shops);

				sale_Inventory_Repository.save(sale_Inventory);

				Expenditure_Inventory_Entity Expenditure_Inventory = new Expenditure_Inventory_Entity();

				Expenditure_Inventory.setTotal_Expenditure(totalExpenditure);
				Expenditure_Inventory.setChicken_Expenses(chickenExpense);
				Expenditure_Inventory.setBiriyani_Chicken_Kg(biriyaniChicken);
				Expenditure_Inventory.setKabab_Chicken_Kg(kababChicken);
				Expenditure_Inventory.setGas_Expenses(gasExpense);
				Expenditure_Inventory.setSalt_Expenses(saltExpense);
				Expenditure_Inventory.setCorianderLeaf_Mint_Expenses(corianderMintExpense);
				Expenditure_Inventory.setGreenChilly_Expenses(GreenChillyExpense);
				Expenditure_Inventory.setCurd_Expenses(curdExpense);
				Expenditure_Inventory.setOther_Expenses(otherExpense);
				Expenditure_Inventory.setNote(notes);
				Expenditure_Inventory.setBiriyani_Chicken_Stock(biriyani_chicken_Stock);
				Expenditure_Inventory.setKabab_Chicken_Stock(kabab_chicken_Stock);
				Expenditure_Inventory.setInventory_Date(Essential_Operations.getToday_Date());
				Expenditure_Inventory.setUpdated("Yes");
				Expenditure_Inventory.setAll_Shops(all_Shops);
				Expenditure_Inventory.setSale_Inventory(sale_Inventory);

				Daily_Stock_Entity daily_Stock = new Daily_Stock_Entity();

				daily_Stock.setRice_Stock_Qty(update_Rice_Used_Qty);
				daily_Stock.setOil_Stock_Qty(update_Oil_Used_Qty);
				daily_Stock.setGinger_Garlic_Stock_Qty(update_Ginger_Garlic_Used_Qty);
				daily_Stock.setStock_updated_Date(Essential_Operations.getToday_Date());
				daily_Stock.setUpdated("Yes");
				daily_Stock.setAll_Shops(all_Shops);

				daily_Stock_Repository.save(daily_Stock);

				expenditure_Inventory_Repository.save(Expenditure_Inventory);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
