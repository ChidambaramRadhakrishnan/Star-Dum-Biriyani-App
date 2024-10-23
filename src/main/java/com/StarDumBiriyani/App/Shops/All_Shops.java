package com.StarDumBiriyani.App.Shops;

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
	
}
