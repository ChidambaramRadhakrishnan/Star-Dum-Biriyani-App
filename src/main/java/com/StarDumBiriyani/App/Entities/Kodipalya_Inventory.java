package com.StarDumBiriyani.App.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Kodipalya_Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;

}
