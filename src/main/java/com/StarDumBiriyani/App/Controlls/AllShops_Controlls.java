package com.StarDumBiriyani.App.Controlls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.StarDumBiriyani.App.Repository.AllShop_Repository;

@Controller
public class AllShops_Controlls {

	
	@Autowired
	AllShop_Repository allShop_Repository;
	
	
	@GetMapping("/get")
	public void getAll() {
		System.out.println("Im perfect ");
		allShop_Repository.findAll().forEach(System.out::println);
	}
}
