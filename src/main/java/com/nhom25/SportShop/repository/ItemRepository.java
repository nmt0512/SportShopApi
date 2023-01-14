package com.nhom25.SportShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nhom25.SportShop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findAll();
	List<Item> findByCategoryCode(String categoryCode);
	List<Item> findByNameContaining(String name);
}
