package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.entity.Category;
import com.nhom25.SportShop.repository.CategoryRepository;
import com.nhom25.SportShop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public Category findCategoryByCode(String code) {
        return categoryRepo.findByCode(code);
    }

    @Override
    public List<Category> findByGeneralCode(String gcCode) {
        return categoryRepo.findByGeneralCode(gcCode);
    }
}
