package com.ivana.dao;

import com.ivana.pojo.Blog;
import com.ivana.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//<<Blog, Long>: object, id
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {




}
