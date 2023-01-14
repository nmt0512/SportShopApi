package com.nhom25.SportShop.repository;

import com.nhom25.SportShop.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByItemId(Integer itemId);
}
