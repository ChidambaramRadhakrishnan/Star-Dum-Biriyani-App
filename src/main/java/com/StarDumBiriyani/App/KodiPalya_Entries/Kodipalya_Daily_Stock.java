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
public class Kodipalya_Daily_Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private All_Shops all_Shops;

	private int rice_Stock_Qty;
	private int oil_Stock_Qty;
	private int ginger_Garlic_Stock_Qty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public All_Shops getAll_Shops() {
		return all_Shops;
	}

	public void setAll_Shops(All_Shops all_Shops) {
		this.all_Shops = all_Shops;
	}

	public int getRice_Stock_Qty() {
		return rice_Stock_Qty;
	}

	public void setRice_Stock_Qty(int rice_Stock_Qty) {
		this.rice_Stock_Qty = rice_Stock_Qty;
	}

	public int getOil_Stock_Qty() {
		return oil_Stock_Qty;
	}

	public void setOil_Stock_Qty(int oil_Stock_Qty) {
		this.oil_Stock_Qty = oil_Stock_Qty;
	}

	public int getGinger_Garlic_Stock_Qty() {
		return ginger_Garlic_Stock_Qty;
	}

	public void setGinger_Garlic_Stock_Qty(int ginger_Garlic_Stock_Qty) {
		this.ginger_Garlic_Stock_Qty = ginger_Garlic_Stock_Qty;
	}

}
