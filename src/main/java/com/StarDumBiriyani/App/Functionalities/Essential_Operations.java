package com.StarDumBiriyani.App.Functionalities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

}
