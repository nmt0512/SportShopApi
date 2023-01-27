package com.nhom25.SportShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nhom25.SportShop.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findAll();
	List<Item> findByCategoryCode(String categoryCode);
	List<Item> findByNameContaining(String name);
	@Query(value = "SELECT * FROM Item WHERE Code = :code AND Color = :color AND Size = :size", nativeQuery = true)
	Item findByCodeAndColorAndSize(@Param("code") String code, @Param("color") String color, @Param("size") String size);
}
