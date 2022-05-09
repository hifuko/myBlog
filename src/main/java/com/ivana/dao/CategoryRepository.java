package com.ivana.dao;

import com.ivana.pojo.Category;
import com.ivana.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

//<User, Long>: object, id
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);



}
