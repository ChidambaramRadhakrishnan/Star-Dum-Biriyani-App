package com.StarDumBiriyani.App.KodiPalya_Entries;

import com.StarDumBiriyani.App.Shops.All_Shops;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class KodiPalya_Stock {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int Id;
	//
	private int rice_Amount;
	private int rice_Qty;
	private int oil_Amount;
	private int oil_Qty;
	private int gingerGarlic_Amount;
	private int gingerGarlic_Qty;
	private int onion_Amount;
	private int onion_Qty;
	private int egg__Amount;
	private int egg_Qty;
	private int masalaItems_Amount;
	//
	private int specialSalt_Amount;
	private int foodColor_Amount;
	private int toothStick_Amount;
	private int jeeraandSweet_Amount;
	private int waterBottle_Amount;
	//
	private int parcelCover_Amount;
	private int largeParcelCover_Qty;
	private int mediumParcelCover_Qty;
	private int smallParcelCover_Qty;
	private int gravyCover_Qty;
	//
	private int carryBag_Amount;
	private int largeCarryBag_Qty;
	private int mediumCarryBag_Qty;
	private int smallCarryBag_Qty;
	private int plateCover_Amount;
	private int plateCover_Qty;
	private int foodContainer_Amount;
	private int foodContainer_Qty;
	private int rubberBand_Amount;
	//
	private String notes;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private All_Shops all_Shops;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public int getRice_Amount() {
		return rice_Amount;
	}

	public void setRice_Amount(int rice_Amount) {
		this.rice_Amount = rice_Amount;
	}

	public int getRice_Qty() {
		return rice_Qty;
	}

	public void setRice_Qty(int rice_Qty) {
		this.rice_Qty = rice_Qty;
	}
	
	public int getGravyCover_Qty() {
		return gravyCover_Qty;
	}

	public void setGravyCover_Qty(int gravyCover_Qty) {
		this.gravyCover_Qty = gravyCover_Qty;
	}

	public int getOil_Amount() {
		return oil_Amount;
	}

	public void setOil_Amount(int oil_Amount) {
		this.oil_Amount = oil_Amount;
	}

	public int getOil_Qty() {
		return oil_Qty;
	}

	public void setOil_Qty(int oil_Qty) {
		this.oil_Qty = oil_Qty;
	}

	public int getGingerGarlic_Amount() {
		return gingerGarlic_Amount;
	}

	public void setGingerGarlic_Amount(int gingerGarlic_Amount) {
		this.gingerGarlic_Amount = gingerGarlic_Amount;
	}

	public int getGingerGarlic_Qty() {
		return gingerGarlic_Qty;
	}

	public void setGingerGarlic_Qty(int gingerGarlic_Qty) {
		this.gingerGarlic_Qty = gingerGarlic_Qty;
	}

	public int getOnion_Amount() {
		return onion_Amount;
	}

	public void setOnion_Amount(int onion_Amount) {
		this.onion_Amount = onion_Amount;
	}

	public int getOnion_Qty() {
		return onion_Qty;
	}

	public void setOnion_Qty(int onion_Qty) {
		this.onion_Qty = onion_Qty;
	}

	public int getEgg__Amount() {
		return egg__Amount;
	}

	public void setEgg__Amount(int egg__Amount) {
		this.egg__Amount = egg__Amount;
	}

	public int getEgg_Qty() {
		return egg_Qty;
	}

	public void setEgg_Qty(int egg_Qty) {
		this.egg_Qty = egg_Qty;
	}

	public int getMasalaItems_Amount() {
		return masalaItems_Amount;
	}

	public void setMasalaItems_Amount(int masalaItems_Amount) {
		this.masalaItems_Amount = masalaItems_Amount;
	}

	public int getSpecialSalt_Amount() {
		return specialSalt_Amount;
	}

	public void setSpecialSalt_Amount(int specialSalt_Amount) {
		this.specialSalt_Amount = specialSalt_Amount;
	}

	public int getFoodColor_Amount() {
		return foodColor_Amount;
	}

	public void setFoodColor_Amount(int foodColor_Amount) {
		this.foodColor_Amount = foodColor_Amount;
	}

	public int getToothStick_Amount() {
		return toothStick_Amount;
	}

	public void setToothStick_Amount(int toothStick_Amount) {
		this.toothStick_Amount = toothStick_Amount;
	}

	public int getJeeraandSweet_Amount() {
		return jeeraandSweet_Amount;
	}

	public void setJeeraandSweet_Amount(int jeeraandSweet_Amount) {
		this.jeeraandSweet_Amount = jeeraandSweet_Amount;
	}

	public int getWaterBottle_Amount() {
		return waterBottle_Amount;
	}

	public void setWaterBottle_Amount(int waterBottle_Amount) {
		this.waterBottle_Amount = waterBottle_Amount;
	}

	public int getParcelCover_Amount() {
		return parcelCover_Amount;
	}

	public void setParcelCover_Amount(int parcelCover_Amount) {
		this.parcelCover_Amount = parcelCover_Amount;
	}

	public int getLargeParcelCover_Qty() {
		return largeParcelCover_Qty;
	}

	public void setLargeParcelCover_Qty(int largeParcelCover_Qty) {
		this.largeParcelCover_Qty = largeParcelCover_Qty;
	}

	public int getMediumParcelCover_Qty() {
		return mediumParcelCover_Qty;
	}

	public void setMediumParcelCover_Qty(int mediumParcelCover_Qty) {
		this.mediumParcelCover_Qty = mediumParcelCover_Qty;
	}

	public int getSmallParcelCover_Qty() {
		return smallParcelCover_Qty;
	}

	public void setSmallParcelCover_Qty(int smallParcelCover_Qty) {
		this.smallParcelCover_Qty = smallParcelCover_Qty;
	}

	public int getCarryBag_Amount() {
		return carryBag_Amount;
	}

	public void setCarryBag_Amount(int carryBag_Amount) {
		this.carryBag_Amount = carryBag_Amount;
	}

	public int getLargeCarryBag_Qty() {
		return largeCarryBag_Qty;
	}

	public void setLargeCarryBag_Qty(int largeCarryBag_Qty) {
		this.largeCarryBag_Qty = largeCarryBag_Qty;
	}

	public int getMediumCarryBag_Qty() {
		return mediumCarryBag_Qty;
	}

	public void setMediumCarryBag_Qty(int mediumCarryBag_Qty) {
		this.mediumCarryBag_Qty = mediumCarryBag_Qty;
	}

	public int getSmallCarryBag_Qty() {
		return smallCarryBag_Qty;
	}

	public void setSmallCarryBag_Qty(int smallCarryBag_Qty) {
		this.smallCarryBag_Qty = smallCarryBag_Qty;
	}

	public int getPlateCover_Amount() {
		return plateCover_Amount;
	}

	public void setPlateCover_Amount(int plateCover_Amount) {
		this.plateCover_Amount = plateCover_Amount;
	}

	public int getPlateCover_Qty() {
		return plateCover_Qty;
	}

	public void setPlateCover_Qty(int plateCover_Qty) {
		this.plateCover_Qty = plateCover_Qty;
	}

	public int getFoodContainer_Amount() {
		return foodContainer_Amount;
	}

	public void setFoodContainer_Amount(int foodContainer_Amount) {
		this.foodContainer_Amount = foodContainer_Amount;
	}

	public int getFoodContainer_Qty() {
		return foodContainer_Qty;
	}

	public void setFoodContainer_Qty(int foodContainer_Qty) {
		this.foodContainer_Qty = foodContainer_Qty;
	}

	public int getRubberBand_Amount() {
		return rubberBand_Amount;
	}

	public void setRubberBand_Amount(int rubberBand_Amount) {
		this.rubberBand_Amount = rubberBand_Amount;
	}

	public All_Shops getAll_Shops() {
		return all_Shops;
	}

	public void setAll_Shops(All_Shops all_Shops) {
		this.all_Shops = all_Shops;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
