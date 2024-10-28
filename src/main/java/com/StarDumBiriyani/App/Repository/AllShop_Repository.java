package com.StarDumBiriyani.App.Repository;

import java.util.List;

import org.hibernate.annotations.processing.Find;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.StarDumBiriyani.App.Shops.All_Shops;


@Repository
public interface AllShop_Repository extends JpaRepository<All_Shops, Integer>{
	
	@Query(value = "select * from branch_name where id=:id;", nativeQuery = true)
	List<All_Shops> findByid(int id);

}
