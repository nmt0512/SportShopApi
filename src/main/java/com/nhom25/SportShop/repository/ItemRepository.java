package com.nhom25.SportShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nhom25.SportShop.entity.Item;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByCategoryCode(String categoryCode);
	List<Item> findByNameContaining(String name);
	@Query(value = "SELECT * FROM Item WHERE Code = :code AND Color = :color AND Size = :size", nativeQuery = true)
	Item findByCodeAndColorAndSize(@Param("code") String code, @Param("color") String color, @Param("size") String size);
	@Query(value = "SELECT * FROM Item WHERE DATEDIFF(DAY, CreatedDate, GETDATE()) <= 7", nativeQuery = true)
	List<Item> findLatestItemInWeek();
	List<Item> findByCode(String code);
	@Query(value = "SELECT * FROM Item WHERE ItemId IN " +
			"(SELECT TOP 4 ItemId FROM BillItem GROUP BY ItemId ORDER BY COUNT(ItemId) DESC)", nativeQuery = true)
	List<Item> getMostRevenueItem();
	@Query(value = "SELECT * FROM Item WHERE ItemId IN " +
			"(SELECT TOP 4 ItemId FROM BillItem WHERE BillId IN (SELECT BillId FROM Bill WHERE DATEDIFF(DAY, CreatedDate, GETDATE()) <= 7) " +
			"GROUP BY ItemId ORDER BY COUNT(ItemId) DESC)", nativeQuery = true)
	List<Item> getBestSellerItem();

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Item WHERE Code = :code", nativeQuery = true)
	void deleteInBulkByCode(@Param("code") String code);
}
