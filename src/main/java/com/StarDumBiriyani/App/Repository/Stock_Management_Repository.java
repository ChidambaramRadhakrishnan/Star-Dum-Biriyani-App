package com.StarDumBiriyani.App.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.StarDumBiriyani.App.Entries.Stock_Entity;

import jakarta.transaction.Transactional;

public interface Stock_Management_Repository extends JpaRepository<Stock_Entity, Integer>{

	@Query(value = "select * from stock_entity where shop_id =:id order by id desc limit 1;",nativeQuery = true)
	List<Stock_Entity> getLastStockRecord(int id);

	@Query(value ="select stock_fill_date from stock_entity where shop_id = :id order by id desc limit 1;",nativeQuery =  true)
	String getStockDate(int id);
	
	@Query(value = "select updated from stock_entity where shop_id =:id and stock_fill_date =:stock_date order by id desc limit 1;",nativeQuery = true)
	String getUpdateStatus(int id, String stock_date);

	@Query(value = "SELECT \n" +
			"    *\n" +
			"FROM \n" +
			"    stock_entity\n" +
			"WHERE \n" +
			"    shop_id =:shopID\n" +
			"    AND MONTH(event_date) = MONTH(CURRENT_DATE())\n" +
			"    AND YEAR(event_date) = YEAR(CURRENT_DATE());",nativeQuery = true)
	List<Stock_Entity> getStockReport(int shopID);
	
	
	@Modifying
	@Transactional
	@Query( value = "UPDATE  stock_entity SET\r\n"
			+ "carry_bag_amount =:carry_bag_amount,\r\n"
			+ "egg_qty  =:egg_qty,egg__amount  =:egg__amount,food_color_amount  =:food_color_amount,food_container_amount  =:food_container_amount,\r\n"
			+ "food_container_qty  =:food_container_qty,ginger_garlic_amount  =:ginger_garlic_amount,ginger_garlic_qty  =:ginger_garlic_qty,gravy_cover_qty  =:gravy_cover_qty,jeeraand_sweet_amount  =:jeeraand_sweet_amount,"
			+ "large_carry_bag_qty  =:large_carry_bag_qty,large_parcel_cover_qty  =:large_parcel_cover_qty,\r\n"
			+ "masala_items_amount  =:masala_items_amount,medium_carry_bag_qty  =:medium_carry_bag_qty,medium_parcel_cover_qty  =:medium_parcel_cover_qty, notes  =:notes,\r\n"
			+ "oil_amount  =:oil_amount,oil_qty  =:oil_qty,onion_amount  =:onion_amount,onion_qty  =:onion_qty,parcel_cover_amount  =:parcel_cover_amount,\r\n"
			+ "plate_cover_amount  =:plate_cover_amount,plate_cover_qty  =:plate_cover_qty,rice_amount  =:rice_amount,rice_qty  =:rice_qty,\r\n"
			+ "rubber_band_amount  =:rubber_band_amount,small_carry_bag_qty  =:small_carry_bag_qty,small_parcel_cover_qty  =:small_parcel_cover_qty,\r\n"
			+ "special_salt_amount  =:special_salt_amount,tooth_stick_amount  =:tooth_stick_amount,water_bottle_amount=:water_bottle_amount,updated  =:updated  \r\n"
			+ "WHERE  shop_id  =:id and stock_fill_date =:stock_date;\r\n"
			+ "", nativeQuery = true)
	int updateStock(int carry_bag_amount, int egg_qty,int egg__amount, int food_color_amount, int food_container_amount,
			int food_container_qty, int ginger_garlic_amount, int ginger_garlic_qty, int gravy_cover_qty, int masala_items_amount,
			int jeeraand_sweet_amount, int large_carry_bag_qty, int large_parcel_cover_qty, int medium_carry_bag_qty, int medium_parcel_cover_qty,
			String notes, int oil_amount, int oil_qty, int onion_amount, int onion_qty, int parcel_cover_amount, int plate_cover_amount,
			int plate_cover_qty, int rice_amount, int rice_qty, int rubber_band_amount, int small_carry_bag_qty, int small_parcel_cover_qty,
			int special_salt_amount, int tooth_stick_amount, int water_bottle_amount, String updated
			,int id, String stock_date);


	@Modifying
	@Transactional
	@Query( value ="UPDATE stock_entity\n" +
			"SET rice_qty =:riceQty,\n" +
			"oil_qty =:oilQty, ginger_garlic_qty =:gingerGarlicQty, inventory_date=:inventoryDate\n" +
			"WHERE id = (\n" +
			"    SELECT max_id FROM (\n" +
			"        SELECT MAX(id) AS max_id \n" +
			"        FROM stock_entity\n" +
			"        WHERE shop_id = :id\n" +
			"    ) AS last_record\n" +
			");\n", nativeQuery = true)
	int updateDailyInventory(int riceQty, int oilQty, int gingerGarlicQty, String inventoryDate, int id);
}
