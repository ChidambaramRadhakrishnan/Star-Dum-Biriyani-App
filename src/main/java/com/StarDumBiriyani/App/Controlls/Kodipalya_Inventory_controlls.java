package com.StarDumBiriyani.App.Controlls;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.StarDumBiriyani.App.Services.All_Shop_Service;
import com.StarDumBiriyani.App.Services.Inventory_Service_class;
import com.StarDumBiriyani.App.Shops.All_Shops;

@Controller
public class Kodipalya_Inventory_controlls {
	
	@Autowired
	Inventory_Service_class inventory_Service_class;
	
	@Autowired
	All_Shop_Service all_Shop_Service;
	

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
			@RequestParam("kabab_chicken_Stock") int kabab_chicken_Stock) {
		
		inventory_Service_class.addNewInventory(totalSale, totalCash, totalUPI, cashBalance, upiBalance, totalExpenditure, chickenExpense, biriyaniChicken, kababChicken, gasExpense, saltExpense, corianderMintExpense, GreenChillyExpense, curdExpense, otherExpense, notes, biriyani_chicken_Stock, kabab_chicken_Stock);

		return "success";
	}

}
