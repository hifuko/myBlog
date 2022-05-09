package com.ivana.web;

import com.ivana.pojo.Category;
import com.ivana.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public String list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){

        model.addAttribute("page", service.listCategory(pageable));
        return "../admin/manageCategories";
    }

    @GetMapping("/categories/input")
    public String input(Model model){
        model.addAttribute("category", new Category());
        return "../admin/createCategory";
    }

    @PostMapping("/categories")
    public String post(@Valid Category category, BindingResult result, RedirectAttributes attributes){ //name will be automatically encapsulated into the cat object


        if (result.hasErrors()){
            return "../admin/createCategory";
        }

        Category c = service.getByName(category.getName());
        if (c != null){
            System.out.println("Category already existed.");
            result.rejectValue("name", "nameError", "Category already existed.");
            return "../admin/createCategory";
        }

        Category c2 = service.addCategory(category);
        if (c2 == null){
            attributes.addFlashAttribute("message", "Creating a new category failed.");
        } else {
            attributes.addFlashAttribute("message", "Created a new category successfully.");
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/{id}/input")
    public String update(@PathVariable("id") Long id, Model model){
        Category c = service.getCategory(id);
        model.addAttribute("category", c);

        return "../admin/createCategory";
    }


    @PostMapping("/categories/{id}")
    public String update(@Valid Category category, BindingResult result, @PathVariable("id") Long id, RedirectAttributes attributes){
        if (result.hasErrors()){
            return "../admin/createCategory";
        }

        Category c = service.getByName(category.getName());
        if (c != null){
            System.out.println("Category already existed.");
            result.rejectValue("name", "nameError", "Category already existed.");
            return "../admin/createCategory";
        }

        System.out.println("id = " + id);

        category.setId(id);
        System.out.println(category);
        Category c2 = service.updateCategory(category);
        if (c2 == null){
            attributes.addFlashAttribute("message", "Updating failed.");
        } else {
            attributes.addFlashAttribute("message", "Updated successfully.");
        }
        return "redirect:/admin/categories";

    }



    @GetMapping("/categories/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        service.deleteCategory(id);
        attributes.addFlashAttribute("message", "Deleted successfully.");
        return "redirect:/admin/categories";
    }

}
