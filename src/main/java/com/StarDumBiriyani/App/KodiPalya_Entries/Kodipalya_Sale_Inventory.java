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
public class Kodipalya_Sale_Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int total_sale;
	private int cash;
	private int upi;
	private int cash_balance;
	private int upi_balance;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private All_Shops all_Shops;

	public All_Shops getAll_Shops() {
		return all_Shops;
	}

	public void setAll_Shops(All_Shops all_Shops) {
		this.all_Shops = all_Shops;
	}

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

	public void setUpi_balance(int upi_balance) {
		this.upi_balance = upi_balance;
	}

}
