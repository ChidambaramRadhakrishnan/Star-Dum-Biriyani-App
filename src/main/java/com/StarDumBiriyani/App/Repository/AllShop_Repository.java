package com.StarDumBiriyani.App.Repository;

import java.util.List;

import org.hibernate.annotations.processing.Find;
import org.springframework.data.jpa.repository.JpaRepository;
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
	
	@Query(value="SELECT \r\n"
			+ "    si.sale_inventory_date AS matched_date\r\n"
			+ "FROM \r\n"
			+ "    sale_inventory_entity si\r\n"
			+ "JOIN \r\n"
			+ "    expenditure_inventory_entity ei ON si.shop_id = ei.shop_id\r\n"
			+ "JOIN \r\n"
			+ "    daily_stock_entity ds ON si.shop_id = ds.shop_id\r\n"
			+ "WHERE \r\n"
			+ "    si.shop_id = :id\r\n"
			+ "    AND si.sale_inventory_date = ei.inventory_date\r\n"
			+ "    AND si.sale_inventory_date = ds.stock_updated_date\r\n"
			+ "ORDER BY \r\n"
			+ "    STR_TO_DATE(si.sale_inventory_date, '%d-%m-%Y') DESC\r\n"
			+ "LIMIT 1;",nativeQuery = true)
	String getMatchDate(int id);
	

}
