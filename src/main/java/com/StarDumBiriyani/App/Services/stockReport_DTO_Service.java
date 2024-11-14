package com.StarDumBiriyani.App.Services;


import com.StarDumBiriyani.App.Entries.Stock_ReportDTO_Entities;
import com.StarDumBiriyani.App.Repository.stock_ReportDTO_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class stockReport_DTO_Service {

    @Autowired
    stock_ReportDTO_Repository stockReportDTORepository;

    public List<Stock_ReportDTO_Entities> getStock_Report(){
        return stockReportDTORepository.getStock_Report();
    }
}
