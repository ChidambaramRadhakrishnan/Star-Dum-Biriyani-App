package com.StarDumBiriyani.App.Repository;

import com.StarDumBiriyani.App.Entries.All_Shops;
import com.StarDumBiriyani.App.Entries.Expenditure_Inventory_Entity;
import com.StarDumBiriyani.App.Entries.Shop_ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface shopReportRepository extends JpaRepository<Shop_ReportDTO, Integer> {

    @Query(value = "WITH LatestSaleInventory AS (\n" +
            "    SELECT \n" +
            "        shop_id,\n" +
            "        total_sale,\n" +
            "        event_date AS sale_inventory_date,  -- Use the event_date column directly\n" +
            "        ROW_NUMBER() OVER (PARTITION BY shop_id ORDER BY event_date DESC) AS rn\n" +
            "    FROM \n" +
            "        sale_inventory_entity\n" +
            "),\n" +
            "LatestExpenditureInventory AS (\n" +
            "    SELECT \n" +
            "        shop_id,\n" +
            "        biriyani_chicken_kg,\n" +
            "        biriyani_chicken_stock,\n" +
            "        kabab_chicken_kg,\n" +
            "        kabab_chicken_stock,\n" +
            "        auto_total_expense,\n" +
            "        rice_used,\n" +
            "        event_date AS inventory_date,  -- Use the event_date column directly\n" +
            "        ROW_NUMBER() OVER (PARTITION BY shop_id ORDER BY event_date DESC) AS rn\n" +
            "    FROM \n" +
            "        expenditure_inventory_entity\n" +
            ")\n" +
            "SELECT \n" +
            "    a.id, \n" +
            "    a.branch_name, \n" +
            "    COALESCE(s.total_sale, 0) AS total_sale, \n" +
            "    s.sale_inventory_date, \n" +
            "    COALESCE(e.biriyani_chicken_kg, 0) AS biriyani_chicken_kg,\n" +
            "    COALESCE(e.biriyani_chicken_stock, 0) AS biriyani_chicken_stock,\n" +
            "    COALESCE(e.kabab_chicken_kg, 0) AS kabab_chicken_kg,\n" +
            "    COALESCE(e.kabab_chicken_stock, 0) AS kabab_chicken_stock,\n" +
            "    COALESCE(e.auto_total_expense, 0) AS auto_total_expense,\n" +
            "    COALESCE(e.rice_used, 0) AS rice_used\n" +
            "FROM \n" +
            "    branch_name a\n" +
            "LEFT JOIN LatestSaleInventory s \n" +
            "    ON a.id = s.shop_id AND s.rn = 1\n" +
            "LEFT JOIN LatestExpenditureInventory e \n" +
            "    ON a.id = e.shop_id AND e.rn = 1\n" +
            "ORDER BY \n" +
            "    a.id DESC;",nativeQuery = true)
    List<Shop_ReportDTO> getShopReport();

}
