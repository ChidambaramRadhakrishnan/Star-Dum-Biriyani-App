package com.StarDumBiriyani.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.StarDumBiriyani.App.Entries.Sale_Inventory_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface Sale_Inventory_Repository extends JpaRepository<Sale_Inventory_Entity, Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE sale_inventory_entity SET cash =:cash, cash_balance =:cashBalance,"
			+ " total_sale =:total_Sale, upi =:upi, upi_balance =:upi_Balance WHERE shop_id=:shop_id and sale_inventory_date=:date;\r\n"
			+ "",nativeQuery = true)
	int updateExisting(int cash, int cashBalance, int total_Sale, int upi, int upi_Balance, int shop_id, String date);
	
	@Query(value = "select updated from sale_inventory_entity where shop_id =:shop_Id and sale_inventory_date=:inventory_date order by id desc limit 1;",nativeQuery = true)
	String get_Update_StatusByID(int shop_Id, String inventory_date);
	
	@Query(value = "select * from sale_inventory_entity where shop_id = :id order by id desc limit 1;", nativeQuery = true)
	List<Sale_Inventory_Entity> getSale_Inventory_All_Data(int id);
	
}
