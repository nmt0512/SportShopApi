package com.nhom25.SportShop.repository;

import com.nhom25.SportShop.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByItemId(Integer itemId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Image WHERE ItemId = :itemId", nativeQuery = true)
    void deleteByItemId(@Param("itemId") Integer itemId);
}
