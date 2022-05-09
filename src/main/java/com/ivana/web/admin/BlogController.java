package com.ivana.web.admin;

import com.ivana.pojo.Blog;
import com.ivana.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService service;




    @GetMapping("/blogs")
    public String listBlogs(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                            Blog blog,
                            Model model){
        model.addAttribute("page", service.listBlogs(pageable, blog));
        return "../admin/manageBlogs";
    }

    //ajax
    @GetMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                            Blog blog,
                            Model model){
        model.addAttribute("page", service.listBlogs(pageable, blog));
        return "../admin/manageBlogs :: blogList"; //only the blogList fragment
    }


}
