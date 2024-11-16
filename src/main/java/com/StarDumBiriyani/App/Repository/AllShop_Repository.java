package com.StarDumBiriyani.App.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import com.StarDumBiriyani.App.Entries.Expenditure_Inventory_Entity;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.processing.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.StarDumBiriyani.App.Entries.All_Shops;


@Repository
public interface AllShop_Repository extends JpaRepository<All_Shops, Integer>{

	
	@Query(value = "select * from branch_name where id=:id;", nativeQuery = true)
	List<All_Shops> findByid(int id);
	
	@Query(value = "select shop_Code from branch_name where id=:id;",nativeQuery = true)
	int findshop_CodeById(int id);
	
	@Query(value = "select branch_name from branch_name where id=:id;", nativeQuery =  true)
	String getBranchName(int id);
	
	@Query(value="SELECT \n" +
			"    si.sale_inventory_date AS matched_date\n" +
			"FROM \n" +
			"    sale_inventory_entity si\n" +
			"JOIN \n" +
			"    expenditure_inventory_entity ei ON si.shop_id = ei.shop_id\n" +
			"WHERE \n" +
			"    si.shop_id = :id\n" +
			"    AND si.sale_inventory_date = ei.inventory_date\n" +
			"ORDER BY \n" +
			"    STR_TO_DATE(si.sale_inventory_date, '%d-%m-%Y') DESC\n" +
			"LIMIT 1;",nativeQuery = true)
	String getMatchDate(int id);

	@Modifying
	@Transactional
	@Query(value ="update branch_name set branch_name =:branchName,shop_Code =:shopCode,last_modified_date =:lastmodifiedDate where id = :id;",
			nativeQuery = true)
	int updateBranchInfo(String branchName, int shopCode, LocalDate lastmodifiedDate, int id);



}
