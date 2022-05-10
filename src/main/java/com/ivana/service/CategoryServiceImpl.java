package com.ivana.service;

import com.ivana.dao.CategoryRepository;
import com.ivana.pojo.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CategoryRepository repository;

    @Override
    @Transactional
    public Category addCategory(Category category) {
        return repository.save(category);

    }

    @Override
    public Category getCategory(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Page<Category> listCategory(Pageable pageable) {
        return repository.findAll(pageable);
    }

//    @Override
//    @Transactional
//    public Category updateCategory(Long id, Category category) {
//        Category c = repository.getOne(id);
//        if (c == null){
//            throw new MyNotFoundException("Category doesn't exist.");
//        }
//
//        //copy all attribute values from category to c
//        BeanUtils.copyProperties(category, c);
//        return repository.save(c);
//    }


    @Override
    @Transactional
    public Category updateCategory(Category category) {
        return repository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        repository.deleteById(id);

    }

    @Override
    public Category getByName(String name) {
        return repository.findByName(name);
    }
}
