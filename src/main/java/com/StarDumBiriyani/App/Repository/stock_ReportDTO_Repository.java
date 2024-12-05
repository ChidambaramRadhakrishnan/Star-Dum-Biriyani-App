package com.StarDumBiriyani.App.Repository;

import com.StarDumBiriyani.App.Entries.Stock_ReportDTO_Entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface stock_ReportDTO_Repository extends JpaRepository<Stock_ReportDTO_Entities, Integer> {

    @Query(value = "SELECT \n" +
            "    a.id, \n" +
            "    a.branch_name, \n" +
            "    COALESCE(stk.rice_qty, 0) AS rice_qty, \n" +
            "    COALESCE(stk.oil_qty, 0) AS oil_qty, \n" +
            "    COALESCE(stk.ginger_garlic_qty, 0) AS ginger_garlic_qty,\n" +
            "    stk.event_date,  -- Changed from stock_fill_date to event_date\n" +
            "    stk.stock_fill_date,\n" +
            "    stk.inventory_date\n" +
            "FROM \n" +
            "    branch_name a\n" +
            "LEFT JOIN stock_entity stk\n" +
            "    ON a.id = stk.shop_id \n" +
            "    AND stk.event_date = (  -- Using event_date for the latest record\n" +
            "        SELECT MAX(event_date)  -- Get the most recent event_date\n" +
            "        FROM stock_entity \n" +
            "        WHERE shop_id = a.id\n" +
            "    )\n" +
            "ORDER BY a.id DESC;",nativeQuery = true)
    List<Stock_ReportDTO_Entities> getStock_Report();
}
