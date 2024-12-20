package com.StarDumBiriyani.App.Entries;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table
public class Sale_Inventory_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int total_sale;
	private int cash;
	private int upi;
	private int cash_balance;
	private int upi_balance;
	private String updated = "No";
	
	private String Sale_Inventory_Date;

	@Column(name = "event_date", columnDefinition = "DATE")
	private LocalDate eventDate;

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

	public String getSale_Inventory_Date() {
		return Sale_Inventory_Date;
	}

	public void setSale_Inventory_Date(String sale_Inventory_Date) {
		Sale_Inventory_Date = sale_Inventory_Date;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}
	
}
