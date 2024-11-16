package com.StarDumBiriyani.App.Services;

import java.time.LocalDate;
import java.util.List;

import com.StarDumBiriyani.App.Entries.Expenditure_Inventory_Entity;
import com.StarDumBiriyani.App.Entries.Shop_ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StarDumBiriyani.App.Entries.All_Shops;
import com.StarDumBiriyani.App.Repository.AllShop_Repository;
import org.springframework.ui.Model;

@Service
public class All_Shop_Service {
	
	@Autowired
	AllShop_Repository allShop_Repository;
	
	public String getAllShop() {
		return "Branch";
	}
	
	public List<All_Shops> getAllBranch(){
		return allShop_Repository.findAll();
	}
	
	
	public List<All_Shops> getAll_Shop(int id){
		return allShop_Repository.findByid(id);
	}
	
	public String validateShopCode(int id, int shopCode) {
		String page = "";
		int actual_Shop_Code = allShop_Repository.findshop_CodeById(id);
		if(shopCode == actual_Shop_Code) {
			System.out.println("--------------------- shop id is not fonud");
			page = "Inventory_index";
		}
		return page;
	}
	
	public List<All_Shops> getShop_Data(){
		return allShop_Repository.findAll();
	}
	
	
	public String getBranchName(int id) {
		return allShop_Repository.getBranchName(id);
	}
	
	public String getCommonDate(int id) {
		return allShop_Repository.getMatchDate(id);
	}

	public String updateBranchDetails(String branchName, int shopCode, LocalDate date, int id){
		allShop_Repository.updateBranchInfo(branchName, shopCode, date,id);
		return "redirect:/admin/shops?updateSuccess";
	}

	public String addNewBranch(String branchName, int shopCode){
		All_Shops allShops = new All_Shops();
		allShops.setBranchName(branchName);
		allShops.setShopCode(shopCode);
		allShop_Repository.save(allShops);
		return "redirect:/admin/shops?newBranchSuccess";
	}

	public String deleteBranch(int id, Model model){
		allShop_Repository.deleteById(id);
		model.addAttribute("deleteSuccess", true);
		return "redirect:/admin/shops?deleteSuccess";
	}
}
