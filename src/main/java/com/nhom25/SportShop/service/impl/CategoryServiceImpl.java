package com.nhom25.SportShop.service.impl;

import com.nhom25.SportShop.dto.CategoryResponse;
import com.nhom25.SportShop.entity.Category;
import com.nhom25.SportShop.entity.GeneralCategory;
import com.nhom25.SportShop.repository.CategoryRepository;
import com.nhom25.SportShop.repository.GeneralCategoryRepository;
import com.nhom25.SportShop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private GeneralCategoryRepository generalCategoryRepo;

    @Override
    public CategoryResponse getCategoryAndGeneralCategoryNames() {
        List<String> generalCategoryNameList = new ArrayList<>();
        List<String> categoryNameList = new ArrayList<>();
        for(GeneralCategory gc: generalCategoryRepo.findAll())
        {
            generalCategoryNameList.add(gc.getName());
        }
        for(Category cate: categoryRepo.findByGeneralCode("dung-cu"))
        {
            categoryNameList.add(cate.getName());
        }
        return new CategoryResponse(generalCategoryNameList, categoryNameList);
    }
}
