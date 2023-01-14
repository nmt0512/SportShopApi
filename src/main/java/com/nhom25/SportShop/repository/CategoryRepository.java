package com.nhom25.SportShop.repository;

import com.nhom25.SportShop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    Category findByCode(String code);
    List<Category> findByGeneralCode(String generalCode);
}
