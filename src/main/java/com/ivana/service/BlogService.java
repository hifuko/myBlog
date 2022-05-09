package com.ivana.service;

import com.ivana.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface BlogService {

    Blog saveBlog(Blog blog);

    Blog getBlog(Long id);

    Page<Blog> listBlogs(Pageable pageable, Blog blog);

    Blog updateBlog(Blog blog);

    void deleteBlog(Long id);
}
