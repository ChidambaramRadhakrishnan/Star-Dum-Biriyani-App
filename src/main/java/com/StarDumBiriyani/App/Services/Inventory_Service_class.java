package com.StarDumBiriyani.App.Services;

import java.time.LocalDate;
import java.util.List;

import com.StarDumBiriyani.App.Entries.*;
import com.StarDumBiriyani.App.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StarDumBiriyani.App.Functionalities.Essential_Operations;

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

	@Autowired
	Stock_Management_Repository stockManagementRepository;
	
	
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
			int ginger_Garlic_Used, int cashExpense, int upiExpense, int id) {

		try {
			All_Shops all_Shops = allShop_Repository.findById(id).get();

			String expenditure_Status = expenditure_Inventory_Repository.get_Update_StatusByID(all_Shops.getId(), Essential_Operations.getToday_Date());

			String Sale_Status = sale_Inventory_Repository.get_Update_StatusByID(all_Shops.getId(),Essential_Operations.getToday_Date());

			System.out.println(" ------------------------ <<<<<<<<<<<<<<<<<<<<< "+ id);

			List<Stock_Entity> stock = stockManagementRepository.getLastStockRecord(id);

			//Existing rice qty to get previous date stock inventories
			int Existing_Rice_Qty = stock.stream().findFirst().get().getRice_Qty();
			int Existing_Oil_Qty = stock.stream().findFirst().get().getOil_Qty();
			int Existing_Ginger_Garlic_Qty = stock.stream().findFirst().get().getGingerGarlic_Qty();

//			update rice qty means
//			reduce existing record inventory - today rice qty
			int update_Rice_Used_Qty = Existing_Rice_Qty - riceUsed;
			int update_Oil_Used_Qty = Existing_Oil_Qty - oilUsed;
			int update_Ginger_Garlic_Used_Qty = Existing_Ginger_Garlic_Qty - ginger_Garlic_Used;

			Sale_Inventory_Entity sale_Inventory = new Sale_Inventory_Entity();

			/*
			update stock inventory
			if record is already exist in database, this part of code will execute
			won't create new record, just update existing one
			 */
			if ("Yes".equals(expenditure_Status) && "Yes".equals(Sale_Status)) {

				//To calculate total expense (Systemised Total Expense)

				System.out.println("Updating Existing Inventory");

				int auto_total_expense = chickenExpense+gasExpense+corianderMintExpense+curdExpense+GreenChillyExpense+
						otherExpense+saltExpense;
				//
				sale_Inventory_Repository.updateExisting(totalCash, cashBalance, totalSale, totalUPI, upiBalance,
						all_Shops.getId(), Essential_Operations.getToday_Date());
				
				expenditure_Inventory_Repository.updateExistingEntry(biriyaniChicken, biriyani_chicken_Stock, chickenExpense, 
						corianderMintExpense, curdExpense, gasExpense, GreenChillyExpense, kababChicken, kabab_chicken_Stock, 
						otherExpense, saltExpense, totalExpenditure,riceUsed, oilUsed, ginger_Garlic_Used, cashExpense, upiExpense, auto_total_expense,all_Shops.getId(), 
						Essential_Operations.getToday_Date());

				System.out.println("-------------------------------- updated Existing expenditure Entry");

//				stockManagementRepository.updateDailyInventory(update_Rice_Used_Qty, update_Oil_Used_Qty,
//						update_Ginger_Garlic_Used_Qty, Essential_Operations.getToday_Date(), id);

//				if record is not present on today, it'll create new record
//
			} else {
				
				System.out.println("Added New Inventory");
				
				sale_Inventory.setTotal_sale(totalSale);
				sale_Inventory.setCash(totalCash);
				sale_Inventory.setUpi(totalUPI);
				sale_Inventory.setCash_balance(cashBalance);
				sale_Inventory.setUpi_balance(upiBalance);
				sale_Inventory.setSale_Inventory_Date(Essential_Operations.getToday_Date());
				sale_Inventory.setEventDate(LocalDate.now());
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
				Expenditure_Inventory.setRice_Used(riceUsed);
				Expenditure_Inventory.setOil_Used(oilUsed);
				Expenditure_Inventory.setGinger_Garlic_used(ginger_Garlic_Used);
				Expenditure_Inventory.setCashExpense(cashExpense);
				Expenditure_Inventory.setUpiExpense(upiExpense);
				Expenditure_Inventory.setAutoTotalExpense(chickenExpense+gasExpense+saltExpense+corianderMintExpense+GreenChillyExpense+curdExpense+otherExpense);
				Expenditure_Inventory.setInventory_Date(Essential_Operations.getToday_Date());
				Expenditure_Inventory.setUpdated("Yes");
				Expenditure_Inventory.setAll_Shops(all_Shops);
				Expenditure_Inventory.setSale_Inventory(sale_Inventory);
				Expenditure_Inventory.setEventDate(LocalDate.now());

				//
				stockManagementRepository.updateDailyInventory(update_Rice_Used_Qty, update_Oil_Used_Qty,
						update_Ginger_Garlic_Used_Qty, Essential_Operations.getToday_Date(), id);

				System.out.println(" --------------- UPdated");

				expenditure_Inventory_Repository.save(Expenditure_Inventory);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
