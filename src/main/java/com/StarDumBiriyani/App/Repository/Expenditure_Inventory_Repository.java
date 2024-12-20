package com.StarDumBiriyani.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.StarDumBiriyani.App.Entries.Expenditure_Inventory_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface Expenditure_Inventory_Repository extends JpaRepository<Expenditure_Inventory_Entity, Integer> {

	@Query(value = "select updated from expenditure_inventory_entity where shop_id =:shop_Id and inventory_Date =:inventory_Date order by id desc limit 1;", nativeQuery = true)
	String get_Update_StatusByID(int shop_Id, String inventory_Date);

	@Modifying
	@Transactional
	@Query(value = "UPDATE expenditure_inventory_entity SET biriyani_chicken_kg =:biriyani_chicken_kg, biriyani_chicken_stock =:biriyani_chicken_stock,\r\n"
			+ "chicken_expenses =:chicken_expenses, vegetables_expenses =:vegetables_expenses, curd_expenses =:curd_expenses,\r\n"
			+ "gas_expenses =:gas_expenses, kabab_chicken_kg =:kabab_chicken_kg, kabab_chicken_stock =:kabab_chicken_stock, "
			+ "other_expenses =:other_expenses, salt_expenses =:salt_expenses, rice_used =:rice_used, oil_used=:oil_used, ginger_garlic_used=:ginger_garlic_used, "
			+ "cash_expense =:cashExpense, upi_expense=:upiExpense, auto_total_expense =:auto_totalExpense," 
			+ "total_expenditure =:total_expenditure WHERE shop_id =:shop_id and inventory_date =:inventory_Date;\r\n"
			+ "", nativeQuery = true)
	int updateExistingEntry(int biriyani_chicken_kg, int biriyani_chicken_stock, int chicken_expenses, int curd_expenses, int gas_expenses, int vegetables_expenses,
			int kabab_chicken_kg, int kabab_chicken_stock, int other_expenses, int salt_expenses, int total_expenditure,
			int rice_used, int oil_used, int ginger_garlic_used,int cashExpense, int upiExpense,int auto_totalExpense, int shop_id, String inventory_Date);

	@Query(value = "select * from expenditure_inventory_entity where shop_id = :id order by id desc limit 1;", nativeQuery = true)
	List<Expenditure_Inventory_Entity> get_Expenditure_Inventory(int id);

	@Query(value = "SELECT \n" +
			"    SUM(auto_total_expense) AS total_sales_for_current_month\n" +
			"FROM \n" +
			"    expenditure_inventory_entity\n" +
			"WHERE \n" +
			"    shop_id = 1\n" +
			"    AND MONTH(event_date) = MONTH(CURRENT_DATE())\n" +
			"    AND YEAR(event_date) = YEAR(CURRENT_DATE());",nativeQuery = true)
	int getTotal_Expense(int id);

}
