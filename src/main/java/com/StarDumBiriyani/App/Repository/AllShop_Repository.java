package com.StarDumBiriyani.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.StarDumBiriyani.App.Shops.All_Shops;


@Repository
public interface AllShop_Repository extends JpaRepository<All_Shops, Integer>{
	
	

}
