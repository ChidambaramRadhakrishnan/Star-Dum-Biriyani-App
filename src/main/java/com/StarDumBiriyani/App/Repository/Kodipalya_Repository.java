package com.StarDumBiriyani.App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StarDumBiriyani.App.Entities.Kodipalya_Inventory;

@Repository
public interface Kodipalya_Repository extends JpaRepository<Kodipalya_Inventory, Integer>{

}
