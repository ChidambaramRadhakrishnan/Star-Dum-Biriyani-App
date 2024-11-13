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

    @Query(value = "SELECT \n" +
            "    a.id, \n" +
            "    a.branch_name, \n" +
            "    s.total_sale,\n" +
            "    s.sale_inventory_date,\n" +
            "    e.biriyani_chicken_kg,\n" +
            "    e.biriyani_chicken_stock,\n" +
            "    e.kabab_chicken_kg,\n" +
            "    e.kabab_chicken_stock,\n" +
            "    e.auto_total_expense,\n" +
            "    e.rice_used\n" +
            "FROM \n" +
            "    branch_name a\n" +
            "LEFT JOIN sale_inventory_entity s \n" +
            "    ON a.id = s.shop_id \n" +
            "    AND s.sale_inventory_date = (SELECT MAX(sale_inventory_date) \n" +
            "                         FROM sale_inventory_entity \n" +
            "                         WHERE shop_id = a.id)\n" +
            "LEFT JOIN expenditure_inventory_entity e \n" +
            "    ON a.id = e.shop_id \n" +
            "    AND e.inventory_date = (SELECT MAX(inventory_date) \n" +
            "                         FROM expenditure_inventory_entity \n" +
            "                         WHERE shop_id = a.id);\n",nativeQuery = true)
    List<Shop_ReportDTO> getShopReport();

}
