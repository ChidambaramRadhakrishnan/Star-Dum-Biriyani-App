package com.StarDumBiriyani.App.Controlls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Kodipalya_Inventory_controlls {
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/navigatetoLogin")
	public String navigateadmin() {
		return "index";
	}
	
	@GetMapping("/navigateToInventoryManagement")
	public String navigateToInventoryManagement() {
		return "inventory";
	}

}
