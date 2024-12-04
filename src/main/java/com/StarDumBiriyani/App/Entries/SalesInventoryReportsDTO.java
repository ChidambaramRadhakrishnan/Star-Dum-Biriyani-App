package com.StarDumBiriyani.App.Entries;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SalesInventoryReportsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int total_sale;
    private int cash;
    private int upi;
    private int cash_balance;
//    private int total_Balance;
    private int upi_balance;
    private int total_Expenditure;
    private int chicken_Expenses;
    private int biriyani_Chicken_Kg;
    private int kabab_Chicken_Kg;
    private int gas_Expenses;
    private int salt_Expenses;
    private int vegetables_Expenses;
    private int curd_Expenses;
    private int other_Expenses;
    private String note;
    private int biriyani_Chicken_Stock;
    private int kabab_Chicken_Stock;
    private int rice_Used;
    private int oil_Used;
    private int ginger_Garlic_used;
    private int cashExpense;
    private int upiExpense;
    private int AutoTotalExpense;

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    @Column(name = "event_Date", columnDefinition = "DATE")
    private LocalDate eventDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal_sale() {
        return total_sale;
    }

    public void setTotal_sale(int total_sale) {
        this.total_sale = total_sale;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getUpi() {
        return upi;
    }

    public void setUpi(int upi) {
        this.upi = upi;
    }

    public int getCash_balance() {
        return cash_balance;
    }

    public void setCash_balance(int cash_balance) {
        this.cash_balance = cash_balance;
    }

    public int getUpi_balance() {
        return upi_balance;
    }

//    public int getTotal_Balance() {
//        return total_Balance;
//    }
//
//    public void setTotal_Balance(int cash_balance, int upi_balance) {
//        this.total_Balance = cash_balance + upi_balance;
//    }

    public void setUpi_balance(int upi_balance) {
        this.upi_balance = upi_balance;
    }

    public int getTotal_Expenditure() {
        return total_Expenditure;
    }

    public void setTotal_Expenditure(int total_Expenditure) {
        this.total_Expenditure = total_Expenditure;
    }

    public int getChicken_Expenses() {
        return chicken_Expenses;
    }

    public void setChicken_Expenses(int chicken_Expenses) {
        this.chicken_Expenses = chicken_Expenses;
    }

    public int getBiriyani_Chicken_Kg() {
        return biriyani_Chicken_Kg;
    }

    public void setBiriyani_Chicken_Kg(int biriyani_Chicken_Kg) {
        this.biriyani_Chicken_Kg = biriyani_Chicken_Kg;
    }

    public int getKabab_Chicken_Kg() {
        return kabab_Chicken_Kg;
    }

    public void setKabab_Chicken_Kg(int kabab_Chicken_Kg) {
        this.kabab_Chicken_Kg = kabab_Chicken_Kg;
    }

    public int getGas_Expenses() {
        return gas_Expenses;
    }

    public void setGas_Expenses(int gas_Expenses) {
        this.gas_Expenses = gas_Expenses;
    }

    public int getSalt_Expenses() {
        return salt_Expenses;
    }

    public void setSalt_Expenses(int salt_Expenses) {
        this.salt_Expenses = salt_Expenses;
    }

    public int getVegetables_Expenses() {
        return vegetables_Expenses;
    }

    public void setVegetables_Expenses(int vegetables_Expenses) {
        this.vegetables_Expenses = vegetables_Expenses;
    }

    public int getCurd_Expenses() {
        return curd_Expenses;
    }

    public void setCurd_Expenses(int curd_Expenses) {
        this.curd_Expenses = curd_Expenses;
    }

    public int getOther_Expenses() {
        return other_Expenses;
    }

    public void setOther_Expenses(int other_Expenses) {
        this.other_Expenses = other_Expenses;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getBiriyani_Chicken_Stock() {
        return biriyani_Chicken_Stock;
    }

    public void setBiriyani_Chicken_Stock(int biriyani_Chicken_Stock) {
        this.biriyani_Chicken_Stock = biriyani_Chicken_Stock;
    }

    public int getKabab_Chicken_Stock() {
        return kabab_Chicken_Stock;
    }

    public void setKabab_Chicken_Stock(int kabab_Chicken_Stock) {
        this.kabab_Chicken_Stock = kabab_Chicken_Stock;
    }

    public int getRice_Used() {
        return rice_Used;
    }

    public void setRice_Used(int rice_Used) {
        this.rice_Used = rice_Used;
    }

    public int getOil_Used() {
        return oil_Used;
    }

    public void setOil_Used(int oil_Used) {
        this.oil_Used = oil_Used;
    }

    public int getGinger_Garlic_used() {
        return ginger_Garlic_used;
    }

    public void setGinger_Garlic_used(int ginger_Garlic_used) {
        this.ginger_Garlic_used = ginger_Garlic_used;
    }

    public int getCashExpense() {
        return cashExpense;
    }

    public void setCashExpense(int cashExpense) {
        this.cashExpense = cashExpense;
    }

    public int getUpiExpense() {
        return upiExpense;
    }

    public void setUpiExpense(int upiExpense) {
        this.upiExpense = upiExpense;
    }

    public int getAutoTotalExpense() {
        return AutoTotalExpense;
    }

    public void setAutoTotalExpense(int autoTotalExpense) {
        AutoTotalExpense = autoTotalExpense;
    }


}
