package com.ivana.service;

import com.ivana.pojo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);

    Category getCategory(Long id);

    Page<Category> listCategory(Pageable pageable);

//    Category updateCategory(Long id, Category category);


    Category updateCategory(Category category);

    void deleteCategory(Long id);

    Category getByName(String name);
}
