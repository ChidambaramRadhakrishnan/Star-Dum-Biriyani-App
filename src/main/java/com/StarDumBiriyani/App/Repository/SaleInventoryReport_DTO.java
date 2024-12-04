package com.StarDumBiriyani.App.Repository;

import com.StarDumBiriyani.App.Entries.SalesInventoryReportsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SaleInventoryReport_DTO extends JpaRepository<SalesInventoryReportsDTO, Integer> {


    @Query(value = "SELECT \n" +
            " s.id,\n" +
            "    s.cash,\n" +
            "    s.cash_balance,\n" +
            "    s.total_sale,\n" +
            "    s.upi,\n" +
            "    s.upi_Balance,\n" +
            "    e.biriyani_chicken_kg,\n" +
            "    e.biriyani_chicken_stock,\n" +
            "    e.cash_expense,\n" +
            "    e.chicken_expenses,\n" +
            "    e.curd_expenses,\n" +
            "    e.gas_expenses,\n" +
            "    e.ginger_garlic_used,\n" +
            "    e.kabab_chicken_stock,\n" +
            "    e.kabab_chicken_kg, \n" +
            "    e.auto_total_expense,\n" +
            "    e.note,\n" +
            "    e.oil_used,\n" +
            "    e.other_expenses,\n" +
            "    e.rice_used,\n" +
            "    e.salt_expenses,\n" +
            "    e.total_expenditure,\n" +
            "    e.upi_expense,\n" +
            "    e.vegetables_expenses,\n" +
            "    e.event_date\n" +
            "FROM \n" +
            "    sale_inventory_entity s\n" +
            "JOIN \n" +
            "    expenditure_inventory_entity e ON s.shop_id = e.shop_id\n" +
            "                                 AND s.event_date = e.event_date\n" +
            "WHERE \n" +
            "    s.shop_id = 1\n" +
            "ORDER BY \n" +
            "    s.event_date DESC;",nativeQuery = true)
    List<SalesInventoryReportsDTO> findsaleReports(int shopID);

}
