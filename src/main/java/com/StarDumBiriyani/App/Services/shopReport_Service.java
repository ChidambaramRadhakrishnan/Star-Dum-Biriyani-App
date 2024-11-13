package com.StarDumBiriyani.App.Services;

import com.StarDumBiriyani.App.Entries.Shop_ReportDTO;
import com.StarDumBiriyani.App.Repository.shopReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class shopReport_Service {

    @Autowired
    shopReportRepository shopreportRepository;

    public List<Shop_ReportDTO> getShop_Report(){
        return shopreportRepository.getShopReport();
    }

}
