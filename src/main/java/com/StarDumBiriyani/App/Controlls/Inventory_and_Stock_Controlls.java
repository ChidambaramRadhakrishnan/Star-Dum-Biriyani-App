package com.StarDumBiriyani.App.Controlls;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.StarDumBiriyani.App.Entries.*;
import com.StarDumBiriyani.App.Repository.SaleInventoryReport_DTO;
import com.StarDumBiriyani.App.Repository.Sale_Inventory_Repository;
import com.StarDumBiriyani.App.Services.*;
import com.StarDumBiriyani.App.Whatsapp.Whatsapp_Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.StarDumBiriyani.App.Functionalities.Essential_Operations;

import jakarta.servlet.http.HttpSession;

@Controller
public class Inventory_and_Stock_Controlls {

	@Autowired
	Inventory_Service_class inventory_Service_class;

	@Autowired
	All_Shop_Service all_Shop_Service;

	@Autowired
	Stock_Service_Class stock_Service;

	@Autowired
	Daily_Stock_Service_Class daily_Stock_Service;

	@Autowired
	shopReport_Service shop_report_Service;

	@Autowired
	stockReport_DTO_Service stockReportDtoService;

	@Autowired
	SaleInventoryReport_DTO saleInventoryReportDto;


//	Admin

	@GetMapping("admin/addNewShop")
	public String addNew_Shop(@RequestParam("branchName") String branchName,
							  @RequestParam("shopCode") int shopCode){
		return all_Shop_Service.addNewBranch(branchName, shopCode);
	}

	@GetMapping("admin/dashboard")
	public String adminIndex(Model model){

		System.out.println(" are you thre ");

		// Admin Data Section

		try{
			List<Shop_ReportDTO>  all_Shop_Data = shop_report_Service.getShop_Report();
			model.addAttribute("shopReport", all_Shop_Data);

			List<Stock_ReportDTO_Entities> stock_Report = stockReportDtoService.getStock_Report();
			model.addAttribute("stockReport", stock_Report);


		}catch (Exception e){
			e.printStackTrace();
		}

		//
		return "admin_dashboard";
	}

	@GetMapping("admin/shops")
	public String shop_Management(Model model){

        List<All_Shops> allBranch_Details = all_Shop_Service.getAllBranch();
        model.addAttribute("allBranch_Details",allBranch_Details);
        return "shops";
	}

	@GetMapping("admin/reports")
	public String reports(Model model){
		List<All_Shops> shop_Data = all_Shop_Service.getShop_Data();
		model.addAttribute("all_shop_Data", shop_Data);

		return "shop_Report";
	}

	@GetMapping("admin/shopReports")
	public String shopReports(@RequestParam("branch_id") int id,Model model){
		List<All_Shops> shop_Data = all_Shop_Service.getShop_Data();
		model.addAttribute("all_shop_Data", shop_Data);

		List<SalesInventoryReportsDTO> sales = saleInventoryReportDto.findsaleReports(id);

		int total_sale = inventory_Service_class.getTotal_Sale(id);

		int total_Expense = inventory_Service_class.getTotalExpense(id);

		List<Sale_Inventory_Entity> sale_Inventory = inventory_Service_class.getAll_Sale_Inventory_Data(id);

		int total_CashBalance = sale_Inventory.stream().findFirst().get().getCash_balance();
		int total_UpiBalance = sale_Inventory.stream().findFirst().get().getUpi_balance();

		int total_balance = total_CashBalance + total_UpiBalance;

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/EEEE-MM/MMMM-yyyy");

		String formattedDate = currentDate.format(formatter);

		model.addAttribute("Date",formattedDate);

		model.addAttribute("sales_Report",sales);
		model.addAttribute("totalSale",total_sale);
		model.addAttribute("totalExpense",total_Expense);

		model.addAttribute("cashBalance",total_CashBalance);
		model.addAttribute("upiBalance",total_UpiBalance);

		model.addAttribute("totalBalance", total_balance);

//		Stock Reports
		List<Stock_Entity> stocks = stock_Service.getStockReport(id);

		model.addAttribute("stockReport",stocks);

		List<Stock_Entity> lastStock = stock_Service.getLastStockUpdateRecord(id);

		model.addAttribute("riceStock",lastStock.stream().findFirst().get().getRice_Qty());
		model.addAttribute("oilStock",lastStock.stream().findFirst().get().getOil_Qty());
		model.addAttribute("gingerGarlicStock",lastStock.stream().findFirst().get().getGingerGarlic_Qty());

		return "shop_Report";
	}

	@GetMapping("admin/ModifyBranch")
	public String modify_Branch_Details(@RequestParam("shopId") int shopid,@RequestParam("branchNameUpdate") String branchName,
						@RequestParam("shopCodeUpdate") int shopCode){

		return all_Shop_Service.updateBranchDetails(branchName, shopCode, LocalDate.now(),shopid);
	}

	@RequestMapping(value = "admin/delete/{id}",method = RequestMethod.GET)
	public String deleteBranch(@RequestParam("shopId2") int id, Model model ) {
		List<All_Shops> allBranch_Details = all_Shop_Service.getAllBranch();
		model.addAttribute("allBranch_Details",allBranch_Details);
		return all_Shop_Service.deleteBranch(id,model);  // Deletes the branch by ID;  // Redirect to the page displaying the list of branches
	}
	// admin end

	@GetMapping("/")
	public String home() {
		return "indess";
	}

	@GetMapping("/navigatetoLogin")
	public String navigateadmin(Model model) {

		List<All_Shops> shop_Data = all_Shop_Service.getShop_Data();
		model.addAttribute("all_shop_Data", shop_Data);

		System.out.println("---------------------------");

		return "intial_Page";
	}

	@GetMapping("/navigateToInventoryManagement")
	public String navigateToInventoryManagement(Model model, HttpSession session) {
		model.addAttribute("shop_Name", (String) session.getAttribute("shop_Name"));

		System.out.println("----------------" + (String) session.getAttribute("shop_Name"));
		return "inventory";
	}

	@GetMapping("/logout")
	public String Logout(HttpSession session, HttpServletRequest request,
						 HttpServletResponse response) {

		session.removeAttribute("shop_Id");

		System.out.println("Seession shop id has removed");

		if (session != null) {
			session.invalidate(); // Invalidate the session completely
		}
		// Clear cookies
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setValue(null);
				cookie.setMaxAge(0);
				cookie.setPath("/"); // Ensure the cookie path matches the original path
				response.addCookie(cookie);
			}
			System.out.println("Cookies Cleared --");
		}
		return "indess";
	}

	@GetMapping("/navigateToBranch")
	public String navigateToBranch(Model model) {
		List<All_Shops> branchs = all_Shop_Service.getAllBranch();
		model.addAttribute("branchs", branchs);
		return all_Shop_Service.getAllShop();
	}

	@GetMapping("/branch_specifications/{id}")
	public String navigateToBranchSpecification(@PathVariable int id, HttpSession session, Model model) {
		List<All_Shops> shops_Data = all_Shop_Service.getAll_Shop(id);
		model.addAttribute("all_shop_Data", shops_Data);
		System.out.println("----------------------------------");
		shops_Data.stream().forEach(System.out::println);
		String shop_Name = shops_Data.get(0).getBranchName();
		int shop_Id = shops_Data.get(0).getId();
		session.setAttribute("shop_Name", shop_Name);
		session.setAttribute("shop_ID", shop_Id);
		return "branch_specifications";
	}

	@GetMapping("/navigateToStockManagement")
	public String navigateToStockManagement() {
		return "stock_management";
	}

	@PostMapping("/loginInventory")
	public String loginshopinventory(@RequestParam("branch_id") int id, @RequestParam("shop_Code") int shopCode,
			HttpSession session, Model model) {
		session.setAttribute("shop_Id", id);
		session.setAttribute("shopCode", shopCode);
		String branch_Name = all_Shop_Service.getBranchName(id);
		model.addAttribute("branchName", branch_Name);

		System.out.println(" ----------------------------------------       Admin / Login ----------");
		
		Integer shop_Id = (int)session.getAttribute("shop_Id");
		Integer shop_Code = (int)session.getAttribute("shopCode");

		System.out.println("-------------------------------");
		System.out.println(shop_Id+" session id");
		System.out.println(shop_Code+" shop code");
		System.out.println("-------------------------------");

		if (shop_Id == null || shop_Code == null) {
			return "redirect:/navigatetoLogin";
		}
		//
		String last_inventory_update_date = all_Shop_Service.getCommonDate(shop_Id);
		
		if(last_inventory_update_date == null || last_inventory_update_date.isEmpty()) {
			
			String last_Stock_Updated_Date=stock_Service.getStockUpdateDate(shop_Id);
			model.addAttribute("last_Stock_Update_Date", last_Stock_Updated_Date);
//			
			List<Stock_Entity> all_Stock_details = stock_Service.getLastStockUpdateRecord(id);
			model.addAttribute("daily_stock_details", all_Stock_details);
			
		}else {
			
		model.addAttribute("last_inventory_updated_Date", last_inventory_update_date);
		//
		String last_Stock_Updated_Date=stock_Service.getStockUpdateDate(shop_Id);
		model.addAttribute("last_Stock_Update_Date", last_Stock_Updated_Date);
		//
		List<Stock_Entity> all_Stock_details = stock_Service.getLastStockUpdateRecord(id);
		model.addAttribute("daily_stock_details", all_Stock_details);
		
		// Sale Inventory Data's
		
		List<Sale_Inventory_Entity> sale_Inventory_Details = inventory_Service_class.getAll_Sale_Inventory_Data(shop_Id);
		
		int total_Sale = sale_Inventory_Details.stream().findFirst().get().getTotal_sale();
		int total_cash = sale_Inventory_Details.stream().findFirst().get().getCash();
		int total_upi = sale_Inventory_Details.stream().findFirst().get().getUpi();
		int cash_Balance = sale_Inventory_Details.stream().findFirst().get().getCash_balance();
		int upi_Balance = sale_Inventory_Details.stream().findFirst().get().getUpi_balance();
		
		model.addAttribute("total_Sales", Essential_Operations.RupeeConvertion(total_Sale));
		model.addAttribute("total_Cash", Essential_Operations.RupeeConvertion(total_cash));
		model.addAttribute("total_UPI", Essential_Operations.RupeeConvertion(total_upi));
		model.addAttribute("cash_Balance", Essential_Operations.RupeeConvertion(cash_Balance));
		model.addAttribute("upi_Balance", Essential_Operations.RupeeConvertion(upi_Balance));
		
		//Expenditure Inventory Data's
		
		List<Expenditure_Inventory_Entity> expenditure_Inventory_Details = inventory_Service_class.getAll_Expenditure_Inventory_Data(id);
		
		model.addAttribute("total_Expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getTotal_Expenditure()));
		model.addAttribute("chicken_expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getChicken_Expenses()));
		model.addAttribute("biriyani_Chicken_Kg", expenditure_Inventory_Details.stream().findFirst().get().getBiriyani_Chicken_Kg());
		model.addAttribute("kabab_Chicken_Kg",expenditure_Inventory_Details.stream().findFirst().get().getKabab_Chicken_Kg());
		model.addAttribute("gas_Expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getGas_Expenses()));
		model.addAttribute("salt_expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getSalt_Expenses()));
		model.addAttribute("vegetable_expenses", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getVegetables_Expenses()));
		model.addAttribute("curd_Expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getCurd_Expenses()));
		model.addAttribute("other_Expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getOther_Expenses()));
		
		model.addAttribute("chicken_Stock", expenditure_Inventory_Details.stream().findFirst().get().getBiriyani_Chicken_Stock());
		model.addAttribute("Kabab_Stock", expenditure_Inventory_Details.stream().findFirst().get().getKabab_Chicken_Stock());
		
		model.addAttribute("riceUsed", expenditure_Inventory_Details.stream().findFirst().get().getRice_Used());
		model.addAttribute("oilUsed", expenditure_Inventory_Details.stream().findFirst().get().getOil_Used());
		model.addAttribute("ginger_garlic_used", expenditure_Inventory_Details.stream().findFirst().get().getGinger_Garlic_used());
		model.addAttribute("cashExpense", expenditure_Inventory_Details.stream().findFirst().get().getCashExpense());
		model.addAttribute("upiExpense", expenditure_Inventory_Details.stream().findFirst().get().getUpiExpense());

//		Daily Stock Data's
		
		model.addAttribute("rice_Qty", all_Stock_details.stream().findFirst().get().getRice_Qty());
		model.addAttribute("oil_Qty", all_Stock_details.stream().findFirst().get().getOil_Qty());
		model.addAttribute("ginger_Garlic_Qty", all_Stock_details.stream().findFirst().get().getGingerGarlic_Qty());
		
		}
		
		return all_Shop_Service.validateShopCode(shop_Id, shopCode);
	}

	@GetMapping("/loggedInventory")
	public String loginTo(HttpSession session, Model model) throws JsonProcessingException {
		int id = (int) session.getAttribute("shop_Id");
		int shopCode = (int) session.getAttribute("shopCode");
		String branch_Name = all_Shop_Service.getBranchName(id);
		model.addAttribute("branchName", branch_Name);

		System.out.println(" ----------------------------------------       Admin / Login ----------");

		int session_id = (int) session.getAttribute("shop_Id");
		//
		String last_inventory_update_date = all_Shop_Service.getCommonDate(id);

		if(last_inventory_update_date == null || last_inventory_update_date.isEmpty()) {

			String last_Stock_Updated_Date=stock_Service.getStockUpdateDate(id);
			model.addAttribute("last_Stock_Update_Date", last_Stock_Updated_Date);
//
			List<Stock_Entity> all_Stock_details = stock_Service.getLastStockUpdateRecord(id);
			model.addAttribute("daily_stock_details", all_Stock_details);

		}else {

			model.addAttribute("last_inventory_updated_Date", last_inventory_update_date);
			//
			String last_Stock_Updated_Date=stock_Service.getStockUpdateDate(id);
			model.addAttribute("last_Stock_Update_Date", last_Stock_Updated_Date);
			//
			List<Stock_Entity> all_Stock_details = stock_Service.getLastStockUpdateRecord(id);
			model.addAttribute("daily_stock_details", all_Stock_details);

			// Sale Inventory Data's

			List<Sale_Inventory_Entity> sale_Inventory_Details = inventory_Service_class.getAll_Sale_Inventory_Data(id);

			int total_Sale = sale_Inventory_Details.stream().findFirst().get().getTotal_sale();
			int total_cash = sale_Inventory_Details.stream().findFirst().get().getCash();
			int total_upi = sale_Inventory_Details.stream().findFirst().get().getUpi();
			int cash_Balance = sale_Inventory_Details.stream().findFirst().get().getCash_balance();
			int upi_Balance = sale_Inventory_Details.stream().findFirst().get().getUpi_balance();

			model.addAttribute("total_Sales", Essential_Operations.RupeeConvertion(total_Sale));
			model.addAttribute("total_Cash", Essential_Operations.RupeeConvertion(total_cash));
			model.addAttribute("total_UPI", Essential_Operations.RupeeConvertion(total_upi));
			model.addAttribute("cash_Balance", Essential_Operations.RupeeConvertion(cash_Balance));
			model.addAttribute("upi_Balance", Essential_Operations.RupeeConvertion(upi_Balance));

			//Expenditure Inventory Data's

			List<Expenditure_Inventory_Entity> expenditure_Inventory_Details = inventory_Service_class.getAll_Expenditure_Inventory_Data(id);

			model.addAttribute("total_Expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getTotal_Expenditure()));
			model.addAttribute("chicken_expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getChicken_Expenses()));
			model.addAttribute("biriyani_Chicken_Kg", expenditure_Inventory_Details.stream().findFirst().get().getBiriyani_Chicken_Kg());
			model.addAttribute("kabab_Chicken_Kg",expenditure_Inventory_Details.stream().findFirst().get().getKabab_Chicken_Kg());
			model.addAttribute("gas_Expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getGas_Expenses()));
			model.addAttribute("salt_expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getSalt_Expenses()));
			model.addAttribute("vegetable_expenses", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getVegetables_Expenses()));
			model.addAttribute("curd_Expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getCurd_Expenses()));
			model.addAttribute("other_Expense", Essential_Operations.RupeeConvertion(expenditure_Inventory_Details.stream().findFirst().get().getOther_Expenses()));

			model.addAttribute("chicken_Stock", expenditure_Inventory_Details.stream().findFirst().get().getBiriyani_Chicken_Stock());
			model.addAttribute("Kabab_Stock", expenditure_Inventory_Details.stream().findFirst().get().getKabab_Chicken_Stock());

			model.addAttribute("riceUsed", expenditure_Inventory_Details.stream().findFirst().get().getRice_Used());
			model.addAttribute("oilUsed", expenditure_Inventory_Details.stream().findFirst().get().getOil_Used());
			model.addAttribute("ginger_garlic_used", expenditure_Inventory_Details.stream().findFirst().get().getGinger_Garlic_used());
			model.addAttribute("cashExpense", expenditure_Inventory_Details.stream().findFirst().get().getCashExpense());
			model.addAttribute("upiExpense", expenditure_Inventory_Details.stream().findFirst().get().getUpiExpense());

			//Daily Stock Data's

			int rice_Stock = all_Stock_details.stream().findFirst().get().getRice_Qty();
			int oil_Stock = all_Stock_details.stream().findFirst().get().getOil_Qty();
			int gingerGarlic_Stock = all_Stock_details.stream().findFirst().get().getGingerGarlic_Qty();

			model.addAttribute("rice_Qty", all_Stock_details.stream().findFirst().get().getRice_Qty());
			model.addAttribute("oil_Qty", all_Stock_details.stream().findFirst().get().getOil_Qty());
			model.addAttribute("ginger_Garlic_Qty", all_Stock_details.stream().findFirst().get().getGingerGarlic_Qty());

			String warning_Msg = "";
			if(rice_Stock <=50 || oil_Stock <=50 || gingerGarlic_Stock <=50){
				warning_Msg = "Gentle Remainder have low stock. Better to Refill.";
			}else{
				warning_Msg = "";
			}

			//Whatsapp
			String msg =
					"----------------------- \n" +
					"Stock Report \n" +
					"----------------------- \n" +
					"Report Date "+Essential_Operations.getToday_Date()+"\n"+
					"From "+branch_Name+"\n"+
					"Rice Stock : "+rice_Stock+"Kg \n" +
					"Oil Stock : "+oil_Stock+"Liters \n" +
					"Ginger Garlic Stock : "+gingerGarlic_Stock+"Kg \n" +
					"Last Stock Used on : "+all_Stock_details.stream().findFirst().get().getInventoryDate()+"\n" +
					"Last Stock filled on : "+all_Stock_details.stream().findFirst().get().getStock_Fill_Date()+"\n" +
					"------------------------- \n" +
					"Note : "+warning_Msg+"";

			Whatsapp_Configuration.sendMsg(msg);

		}
		return all_Shop_Service.validateShopCode(id, shopCode);
	}

	@PostMapping("/addNew_Inventory")
	public String addnew_Inventory(@RequestParam("totalSale") int totalSale, @RequestParam("totalCash") int totalCash,
			@RequestParam("totalUPI") int totalUPI, @RequestParam("cashBalance") int cashBalance,
			@RequestParam("upiBalance") int upiBalance, @RequestParam("totalExpenditure") int totalExpenditure,
			@RequestParam("chickenExpense") int chickenExpense, @RequestParam("biriyaniChicken") int biriyaniChicken,
			@RequestParam("kababChicken") int kababChicken, @RequestParam("gasExpense") int gasExpense,
			@RequestParam("saltExpense") int saltExpense, @RequestParam("vegetables_Expenses") int vegetables_Expenses, @RequestParam("curdExpense") int curdExpense,
			@RequestParam("otherExpense") int otherExpense, @RequestParam("notes") String notes,
			@RequestParam("biriyani_chicken_Stock") int biriyani_chicken_Stock,
			@RequestParam("kabab_chicken_Stock") int kabab_chicken_Stock, @RequestParam("riceUsed") int riceUsed,
			@RequestParam("oilUsed") int oilUsed, @RequestParam("ginger_garlic_Used") int ginger_garlic_Used,
			@RequestParam("cashExpense") int cashExpense, @RequestParam("upiExpense") int upiExpense,
			Model model, HttpSession session) {

		session.setAttribute("rice_used_Qty", riceUsed);

		System.out.println((int)session.getAttribute("shop_Id")+"------------");
		int id = (int) session.getAttribute("shop_Id");

		return inventory_Service_class.addNewInventory(totalSale, totalCash, totalUPI, cashBalance, upiBalance,
				totalExpenditure, chickenExpense, biriyaniChicken, kababChicken, gasExpense, saltExpense,
				vegetables_Expenses, curdExpense, otherExpense, notes, biriyani_chicken_Stock,
				kabab_chicken_Stock, riceUsed, oilUsed, ginger_garlic_Used,cashExpense, upiExpense, (int) session.getAttribute("shop_Id"));

	}

	@GetMapping("/stockUpdation")
	public String stockUpdate(@RequestParam("riceExpense") int riceExpense, @RequestParam("riceQty") int riceQty,
			@RequestParam("oilExpense") int oilExpense, @RequestParam("oilQty") int oilQty,
			@RequestParam("gingerGarlicExpense") int gingerGarlicExpense,
			@RequestParam("gingerGarlicQty") int gingerGarlicQty, @RequestParam("OnionExpense") int OnionExpense,
			@RequestParam("OnionQty") int OnionQty, @RequestParam("eggStock") int eggStock,
			@RequestParam("eggTrayCount") int eggTrayCount, @RequestParam("masalaItems") int masalaItems,
			@RequestParam("specialSalt") int specialSalt, @RequestParam("foodColour") int foodColour,
			@RequestParam("toothStickExpense") int toothStickExpense,
			@RequestParam("jeeraSweetExpense") int jeeraSweetExpense, @RequestParam("waterBottle") int waterBottle,
			@RequestParam("parcelCoverExpense") int parcelCoverExpense, @RequestParam("largeCover") int largeCover,
			@RequestParam("mediumCover") int mediumCover, @RequestParam("smallCover") int smallCover,
			@RequestParam("gravyCover") int gravyCover, @RequestParam("TotalCarryBagExpense") int TotalCarryBagExpense,
			@RequestParam("largeCarryBag") int largeCarryBag, @RequestParam("mediumCarryBag") int mediumCarryBag,
			@RequestParam("smallCarryBag") int smallCarryBag, @RequestParam("plateCoverExpense") int plateCoverExpense,
			@RequestParam("plateCoverQty") int plateCoverQty,
			@RequestParam("foodContainerExpense") int foodContainerExpense,
			@RequestParam("foodContainerQty") int foodContainerQty, @RequestParam("RubberExpense") int RubberExpense,
			@RequestParam("notes") String notes, HttpSession session, Model model) {

		

		return stock_Service.add_New_Stock_Entry(riceExpense, riceQty, oilExpense, oilQty, gingerGarlicExpense,
				gingerGarlicQty, OnionExpense, OnionQty, eggStock, eggTrayCount, masalaItems, specialSalt, foodColour,
				toothStickExpense, jeeraSweetExpense, waterBottle, parcelCoverExpense, largeCover, mediumCover,
				smallCover, gravyCover, TotalCarryBagExpense, largeCarryBag, mediumCarryBag, smallCarryBag,
				plateCoverExpense, plateCoverQty, foodContainerExpense, foodContainerQty, RubberExpense, notes,
				 (Integer) session.getAttribute("shop_Id"),session);

	}

}
