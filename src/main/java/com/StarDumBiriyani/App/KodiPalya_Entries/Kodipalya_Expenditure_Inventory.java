package com.StarDumBiriyani.App.KodiPalya_Entries;

import com.StarDumBiriyani.App.Shops.All_Shops;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Kodipalya_Expenditure_Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int total_Expenditure;
	private int chicken_Expenses;
	private int biriyani_Chicken_Kg;
	private int kabab_Chicken_Kg;
	private int gas_Expenses;
	private int salt_Expenses;
	private int corianderLeaf_Mint_Expenses;
	private int greenChilly_Expenses;
	private int curd_Expenses;
	private int other_Expenses;
	private String note;
	private int biriyani_Chicken_Stock;
	private int kabab_Chicken_Stock;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private All_Shops all_Shops;
	
	@ManyToOne
	@JoinColumn(name = "sale_inventory_id")
	private Kodipalya_Sale_Inventory kodipalya_Sale_Inventory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getCorianderLeaf_Mint_Expenses() {
		return corianderLeaf_Mint_Expenses;
	}

	public void setCorianderLeaf_Mint_Expenses(int corianderLeaf_Mint_Expenses) {
		this.corianderLeaf_Mint_Expenses = corianderLeaf_Mint_Expenses;
	}

	public int getGreenChilly_Expenses() {
		return greenChilly_Expenses;
	}

	public void setGreenChilly_Expenses(int greenChilly_Expenses) {
		this.greenChilly_Expenses = greenChilly_Expenses;
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

	public All_Shops getAll_Shops() {
		return all_Shops;
	}

	public void setAll_Shops(All_Shops all_Shops) {
		this.all_Shops = all_Shops;
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

	public Kodipalya_Sale_Inventory getKodipalya_Sale_Inventory() {
		return kodipalya_Sale_Inventory;
	}

	public void setKodipalya_Sale_Inventory(Kodipalya_Sale_Inventory kodipalya_Sale_Inventory) {
		this.kodipalya_Sale_Inventory = kodipalya_Sale_Inventory;
	}
}
