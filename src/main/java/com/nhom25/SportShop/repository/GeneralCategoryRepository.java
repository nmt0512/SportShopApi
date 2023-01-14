package com.nhom25.SportShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom25.SportShop.entity.GeneralCategory;

public interface GeneralCategoryRepository extends JpaRepository<GeneralCategory, Integer>{
    GeneralCategory findByCode(String code);
}
