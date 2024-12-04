package com.StarDumBiriyani.App.Entries;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Stock_ReportDTO_Entities {

    @Id
    private int id;

    private String branchName;

    private int rice_Qty;
    private int oil_Qty;
    private int gingerGarlic_Qty;
    private String stock_Fill_Date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getRice_Qty() {
        return rice_Qty;
    }

    public void setRice_Qty(int rice_Qty) {
        this.rice_Qty = rice_Qty;
    }

    public int getOil_Qty() {
        return oil_Qty;
    }

    public void setOil_Qty(int oil_Qty) {
        this.oil_Qty = oil_Qty;
    }

    public int getGingerGarlic_Qty() {
        return gingerGarlic_Qty;
    }

    public void setGingerGarlic_Qty(int gingerGarlic_Qty) {
        this.gingerGarlic_Qty = gingerGarlic_Qty;
    }

    public String getStock_Fill_Date() {
        return stock_Fill_Date;
    }

    public void setStock_Fill_Date(String stock_Fill_Date) {
        this.stock_Fill_Date = stock_Fill_Date;
    }
}
