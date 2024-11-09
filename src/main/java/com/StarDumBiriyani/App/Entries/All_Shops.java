package com.StarDumBiriyani.App.Entries;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Branch_Name")
public class All_Shops {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	public int id;
	
	public String BranchName;
	
	private int shopCode;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}

	public int getShopCode() {
		return shopCode;
	}

	public void setShopCode(int shopCode) {
		this.shopCode = shopCode;
	}
	
	
	
	
}
