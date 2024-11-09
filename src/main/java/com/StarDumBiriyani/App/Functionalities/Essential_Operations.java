package com.StarDumBiriyani.App.Functionalities;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Essential_Operations {
	
	/*
	 * Get Today's date 
	 * in format of date-month-year(24-07-2024)
	 * and return as String 
	 */
	public static String getToday_Date() {
		LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return date.format(formatter);
	}
	
	public static String RupeeConvertion(int number) {
		NumberFormat rupee = NumberFormat.getCurrencyInstance(new Locale("en","IN"));
		
		String INR = rupee.format(number);
		
		return INR;
		
	}

}
