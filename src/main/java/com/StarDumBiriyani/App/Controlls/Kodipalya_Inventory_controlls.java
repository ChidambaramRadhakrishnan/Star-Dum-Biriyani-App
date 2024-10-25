package com.StarDumBiriyani.App.Controlls;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.StarDumBiriyani.App.Services.All_Shop_Service;
import com.StarDumBiriyani.App.Services.Daily_Stock_Service_Class;
import com.StarDumBiriyani.App.Services.Inventory_Service_class;
import com.StarDumBiriyani.App.Services.Stock_Service_Class;
import com.StarDumBiriyani.App.Shops.All_Shops;

@Controller
public class Kodipalya_Inventory_controlls {
	
	@Autowired
	Inventory_Service_class inventory_Service_class;
	
	@Autowired
	All_Shop_Service all_Shop_Service;   
	
	@Autowired
	Stock_Service_Class stock_Service;
	
	@Autowired
	Daily_Stock_Service_Class daily_Stock_Service;
	
	@Autowired
	
	

	@GetMapping("/")
	public String home() {
		return "indess";
	}

	@GetMapping("/navigatetoLogin")
	public String navigateadmin() {
		return "administration";
	}

	@GetMapping("/navigateToInventoryManagement")
	public String navigateToInventoryManagement() {
		return "inventory";
	}
	
	@GetMapping("/navigateToBranch")
	public String navigateToBranch(Model model) {
		List<All_Shops> branchs= all_Shop_Service.getAllBranch();
		model.addAttribute("branchs", branchs);
		return all_Shop_Service.getAllShop();
	}
	
	@GetMapping("/branch_specifications")
	public String navigateToBranchSpecification() {
		return "branch_specifications";
	}

	@GetMapping("/navigateToStockManagement")
	public String navigateToStockManagement() {
		return "stock_management";
	}

	@GetMapping("/addNewUser")
	public String addnewUser(@RequestParam("totalSale") int totalSale, @RequestParam("totalCash") int totalCash,
			@RequestParam("totalUPI") int totalUPI, @RequestParam("cashBalance") int cashBalance,
			@RequestParam("upiBalance") int upiBalance, @RequestParam("totalExpenditure") int totalExpenditure,
			@RequestParam("chickenExpense") int chickenExpense, @RequestParam("biriyaniChicken") int biriyaniChicken,
			@RequestParam("kababChicken") int kababChicken, @RequestParam("gasExpense") int gasExpense,
			@RequestParam("saltExpense") int saltExpense,
			@RequestParam("corianderMintExpense") int corianderMintExpense,
			@RequestParam("GreenChillyExpense") int GreenChillyExpense, @RequestParam("curdExpense") int curdExpense,
			@RequestParam("otherExpense") int otherExpense, @RequestParam("notes") String notes,
			@RequestParam("biriyani_chicken_Stock") int biriyani_chicken_Stock,
			@RequestParam("kabab_chicken_Stock") int kabab_chicken_Stock, @RequestParam("riceUsed") int riceUsed,
			@RequestParam("oilUsed") int oilUsed, @RequestParam("ginger_garlic_Used") int ginger_garlic_Used) {
		
		inventory_Service_class.addNewInventory(totalSale, totalCash, totalUPI, cashBalance, upiBalance, 
				totalExpenditure, chickenExpense, biriyaniChicken, kababChicken, gasExpense, saltExpense, 
				corianderMintExpense, GreenChillyExpense, curdExpense, otherExpense, notes, biriyani_chicken_Stock,
				kabab_chicken_Stock, riceUsed, oilUsed, ginger_garlic_Used);

		return "success";
	}
	
	@GetMapping("/stockUpdation")
	public String stockUpdate(@RequestParam("riceExpense") int riceExpense, @RequestParam("riceQty") int riceQty,
			@RequestParam("oilExpense") int oilExpense, @RequestParam("oilQty") int oilQty, @RequestParam("gingerGarlicExpense") int gingerGarlicExpense,
			@RequestParam("gingerGarlicQty") int gingerGarlicQty, @RequestParam("OnionExpense") int OnionExpense, @RequestParam("OnionQty") int OnionQty,
			@RequestParam("eggStock") int eggStock, @RequestParam("eggTrayCount") int eggTrayCount, @RequestParam("masalaItems") int masalaItems,
			@RequestParam("specialSalt") int specialSalt, @RequestParam("foodColour") int foodColour, @RequestParam("toothStickExpense") int toothStickExpense,
			@RequestParam("jeeraSweetExpense") int jeeraSweetExpense, 
			@RequestParam("waterBottle") int waterBottle,
			@RequestParam("parcelCoverExpense") int parcelCoverExpense, @RequestParam("largeCover") int largeCover,
			@RequestParam("mediumCover") int mediumCover, @RequestParam("smallCover") int smallCover, @RequestParam("gravyCover") int gravyCover,
			@RequestParam("TotalCarryBagExpense") int TotalCarryBagExpense, @RequestParam("largeCarryBag") int largeCarryBag, 
			@RequestParam("mediumCarryBag") int mediumCarryBag, @RequestParam("smallCarryBag") int smallCarryBag, @RequestParam("plateCoverExpense") int plateCoverExpense, 
			@RequestParam("plateCoverQty") int plateCoverQty, @RequestParam("foodContainerExpense") int foodContainerExpense, 
			@RequestParam("foodContainerQty") int foodContainerQty, @RequestParam("RubberExpense") int RubberExpense, @RequestParam("notes") String notes) {
		
		return stock_Service.update_Stock_Entry(riceExpense, riceQty, oilExpense, oilQty, gingerGarlicExpense, gingerGarlicQty, OnionExpense, OnionQty,
				eggStock, eggTrayCount, masalaItems, specialSalt, foodColour, toothStickExpense, jeeraSweetExpense, waterBottle ,parcelCoverExpense,
				largeCover, mediumCover, smallCover, gravyCover, TotalCarryBagExpense, largeCarryBag, mediumCarryBag, smallCarryBag, plateCoverExpense,
				plateCoverQty, foodContainerExpense, foodContainerQty, RubberExpense, notes);
		
	}

}
