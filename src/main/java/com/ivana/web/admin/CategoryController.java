package com.ivana.web.admin;

import com.ivana.pojo.Category;
import com.ivana.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final String TO_SAVE_CATEGORY = "../admin/saveCategory";
    private final String REDIRECT_TO_LIST = "redirect:/admin/categories";

    @Autowired
    private CategoryService service;

    @GetMapping()
    public String list(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page", service.listCategory(pageable));
        return "../admin/manageCategories";
    }

    @GetMapping("/toSave")
    public String toSavePage(Model model){
        model.addAttribute("category", new Category());
        return TO_SAVE_CATEGORY;
    }

    @PostMapping()
    public String create(@Valid Category category, BindingResult result, RedirectAttributes attributes){ //name will be automatically encapsulated into the cat object
        if (result.hasErrors()){
            return TO_SAVE_CATEGORY;
        }

        Category c = service.getByName(category.getName());
        if (c != null){
            System.out.println("Category already existed.");
            result.rejectValue("name", "nameError", "Category already existed.");
            return TO_SAVE_CATEGORY;
        }

        Category c2 = service.addCategory(category);
        if (c2 == null){
            attributes.addFlashAttribute("message", "Creating a new category failed.");
        } else {
            attributes.addFlashAttribute("message", "Created a new category successfully.");
        }
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){
        Category c = service.getCategory(id);
        model.addAttribute("category", c);
        return TO_SAVE_CATEGORY;
    }

    @PostMapping("/{id}")
    public String update(@Valid Category category, BindingResult result, @PathVariable("id") Long id, RedirectAttributes attributes){
        if (result.hasErrors()){
            return TO_SAVE_CATEGORY;
        }

        Category c = service.getByName(category.getName());
        if (c != null && !Objects.equals(c.getId(), id)){
            System.out.println("Category already existed.");
            result.rejectValue("name", "nameError", "Category already existed.");
            return TO_SAVE_CATEGORY;
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
        return REDIRECT_TO_LIST;

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        service.deleteCategory(id);
        attributes.addFlashAttribute("message", "Deleted successfully.");
        return REDIRECT_TO_LIST;
    }
}
