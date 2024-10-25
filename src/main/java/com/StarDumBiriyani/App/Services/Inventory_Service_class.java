package com.StarDumBiriyani.App.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StarDumBiriyani.App.KodiPalya_Entries.Kodipalya_Daily_Stock;
import com.StarDumBiriyani.App.KodiPalya_Entries.Kodipalya_Expenditure_Inventory;
import com.StarDumBiriyani.App.KodiPalya_Entries.Kodipalya_Sale_Inventory;
import com.StarDumBiriyani.App.Repository.AllShop_Repository;
import com.StarDumBiriyani.App.Repository.Kodipalya_Daily_Stock_Repository;
import com.StarDumBiriyani.App.Repository.Kodipalya_Expenditure_Inventory_Repository;
import com.StarDumBiriyani.App.Repository.Kodipalya_Sale_Inventory_Repository;
import com.StarDumBiriyani.App.Shops.All_Shops;

@Service
public class Inventory_Service_class implements Inventory_Services {

	@Autowired
	AllShop_Repository allShop_Repository;

	@Autowired
	Kodipalya_Sale_Inventory_Repository kodipalya_Sale_Inventory_Repository;

	@Autowired
	Kodipalya_Expenditure_Inventory_Repository kodipalya_Expenditure_Inventory_Repository;

	@Autowired
	Kodipalya_Daily_Stock_Repository daily_Stock_Repository;

	public void addNewInventory(int totalSale, int totalCash, int totalUPI, int cashBalance, int upiBalance,
			int totalExpenditure, int chickenExpense, int biriyaniChicken, int kababChicken, int gasExpense,
			int saltExpense, int corianderMintExpense, int GreenChillyExpense, int curdExpense, int otherExpense,
			String notes, int biriyani_chicken_Stock, int kabab_chicken_Stock, int riceUsed, int oilUsed, int ginger_Garlic_Used) {

		try {

			All_Shops all_Shops = allShop_Repository.findById(1).get();

			List<Kodipalya_Daily_Stock> stock = daily_Stock_Repository.getAllDailyStock();
			int Existing_Rice_Qty = stock.stream().findFirst().get().getRice_Stock_Qty();
			int Existing_Oil_Qty = stock.stream().findFirst().get().getOil_Stock_Qty();
			int Existing_Ginger_Garlic_Qty =  stock.stream().findFirst().get().getGinger_Garlic_Stock_Qty();
			
			int update_Rice_Used_Qty = Existing_Rice_Qty - riceUsed;
			int update_Oil_Used_Qty = Existing_Oil_Qty - oilUsed;
			int update_Ginger_Garlic_Used_Qty = Existing_Ginger_Garlic_Qty - ginger_Garlic_Used;

			Kodipalya_Sale_Inventory kodipalya_Sale_Inventory = new Kodipalya_Sale_Inventory();

			kodipalya_Sale_Inventory.setTotal_sale(totalSale);
			kodipalya_Sale_Inventory.setCash(totalCash);
			kodipalya_Sale_Inventory.setUpi(totalUPI);
			kodipalya_Sale_Inventory.setCash_balance(cashBalance);
			kodipalya_Sale_Inventory.setUpi_balance(upiBalance);
			kodipalya_Sale_Inventory.setAll_Shops(all_Shops);

			kodipalya_Sale_Inventory_Repository.save(kodipalya_Sale_Inventory);

			Kodipalya_Expenditure_Inventory kodipalya_Expenditure_Inventory = new Kodipalya_Expenditure_Inventory();

			kodipalya_Expenditure_Inventory.setTotal_Expenditure(totalExpenditure);
			kodipalya_Expenditure_Inventory.setChicken_Expenses(chickenExpense);
			kodipalya_Expenditure_Inventory.setBiriyani_Chicken_Kg(biriyaniChicken);
			kodipalya_Expenditure_Inventory.setKabab_Chicken_Kg(kababChicken);
			kodipalya_Expenditure_Inventory.setGas_Expenses(gasExpense);
			kodipalya_Expenditure_Inventory.setSalt_Expenses(saltExpense);
			kodipalya_Expenditure_Inventory.setCorianderLeaf_Mint_Expenses(corianderMintExpense);
			kodipalya_Expenditure_Inventory.setGreenChilly_Expenses(GreenChillyExpense);
			kodipalya_Expenditure_Inventory.setCurd_Expenses(curdExpense);
			kodipalya_Expenditure_Inventory.setOther_Expenses(otherExpense);
			kodipalya_Expenditure_Inventory.setNote(notes);
			kodipalya_Expenditure_Inventory.setBiriyani_Chicken_Stock(biriyani_chicken_Stock);
			kodipalya_Expenditure_Inventory.setKabab_Chicken_Stock(kabab_chicken_Stock);
			kodipalya_Expenditure_Inventory.setAll_Shops(all_Shops);
			kodipalya_Expenditure_Inventory.setKodipalya_Sale_Inventory(kodipalya_Sale_Inventory);
			
			Kodipalya_Daily_Stock daily_Stock = new Kodipalya_Daily_Stock();
			
			daily_Stock.setRice_Stock_Qty(update_Rice_Used_Qty);
			daily_Stock.setOil_Stock_Qty(update_Oil_Used_Qty);
			daily_Stock.setGinger_Garlic_Stock_Qty(update_Ginger_Garlic_Used_Qty);
			daily_Stock.setAll_Shops(all_Shops);
			
			daily_Stock_Repository.save(daily_Stock);

			kodipalya_Expenditure_Inventory_Repository.save(kodipalya_Expenditure_Inventory);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
