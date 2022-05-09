package com.ivana.service;

import com.ivana.dao.BlogRepository;
import com.ivana.pojo.Blog;
import com.ivana.pojo.Category;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository repository;

    @Override
    public Blog saveBlog(Blog blog) {
        return repository.save(blog);

    }

    @Override
    public Blog getBlog(Long id) {
        return repository.getOne(id);
    }

    //list results for user's search
    //user input is encapsulated in blog object
    @Override
    public Page<Blog> listBlogs(Pageable pageable, Blog blog) {
        return repository.findAll(new Specification<Blog>() {
            //predicate: condition
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                //blog.getTitle() != null && !blog.getTitle().equals("")
                if (!Strings.isEmpty(blog.getTitle())){
                    predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%")); //like sql
                }
                if (blog.getCategory() != null){
                    predicates.add(cb.equal(root.<Category>get("category").get("id"), blog.getCategory().getId()));
                }
                if (blog.isRecommended()){
                    predicates.add(cb.equal(root.<Boolean>get("recommended"), blog.isRecommended()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        return repository.save(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        repository.deleteById(id);

    }
}
