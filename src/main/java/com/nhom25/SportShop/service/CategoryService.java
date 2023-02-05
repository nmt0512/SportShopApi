package com.nhom25.SportShop.service;

import com.nhom25.SportShop.dto.CategoryResponse;
import com.nhom25.SportShop.entity.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryByCode(String code);
    List<Category> findByGeneralCode(String gcCode);
    CategoryResponse getCategoryAndGeneralCategoryNames();
}
