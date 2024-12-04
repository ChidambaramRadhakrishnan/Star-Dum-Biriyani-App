package com.StarDumBiriyani.App.Services;

import com.StarDumBiriyani.App.Entries.SalesInventoryReportsDTO;
import com.StarDumBiriyani.App.Repository.SaleInventoryReport_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleReport_All_DTO_Service {

    @Autowired
    SaleInventoryReport_DTO saleInventoryReportDto;

    public List<SalesInventoryReportsDTO> getFindSales(int id){
        return saleInventoryReportDto.findsaleReports(id);
    }
}
