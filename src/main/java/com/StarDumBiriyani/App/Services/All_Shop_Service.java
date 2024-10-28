package com.StarDumBiriyani.App.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StarDumBiriyani.App.Repository.AllShop_Repository;
import com.StarDumBiriyani.App.Shops.All_Shops;

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
	
}
