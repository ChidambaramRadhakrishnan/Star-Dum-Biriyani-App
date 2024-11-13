package com.StarDumBiriyani.App.Entries;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Shop_ReportDTO {

    @Id
    private int id;
    private String branchName;
    private int totalSale;
    private String saleInventoryDate;

    public int getAutoTotalExpense() {
        return autoTotalExpense;
    }

    public void setAutoTotalExpense(int autoTotalExpense) {
        this.autoTotalExpense = autoTotalExpense;
    }

    private int autoTotalExpense;
    private int biriyaniChickenKg;
    private int kababChickenKg;
    private int biriyaniChickenStock;
    private int kababChickenStock;
    private int rice_used;

    public String getSaleInventoryDate() {
        return saleInventoryDate;
    }

    public void setSaleInventoryDate(String saleInventoryDate) {
        this.saleInventoryDate = saleInventoryDate;
    }

    public int getBiriyaniChickenKg() {
        return biriyaniChickenKg;
    }

    public void setBiriyaniChickenKg(int biriyaniChickenKg) {
        this.biriyaniChickenKg = biriyaniChickenKg;
    }

    public int getKababChickenKg() {
        return kababChickenKg;
    }

    public void setKababChickenKg(int kababChickenKg) {
        this.kababChickenKg = kababChickenKg;
    }

    public int getKababChickenStock() {
        return kababChickenStock;
    }

    public void setKababChickenStock(int kababChickenStock) {
        this.kababChickenStock = kababChickenStock;
    }

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

    public int getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(int totalSale) {
        this.totalSale = totalSale;
    }

    public int getBiriyaniChickenStock() {
        return biriyaniChickenStock;
    }

    public void setBiriyaniChickenStock(int biriyaniChickenStock) {
        this.biriyaniChickenStock = biriyaniChickenStock;
    }

    public int getRice_used() {
        return rice_used;
    }

    public void setRice_used(int rice_used) {
        this.rice_used = rice_used;
    }
}
