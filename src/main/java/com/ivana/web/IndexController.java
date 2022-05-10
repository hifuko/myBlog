package com.ivana.web;

import com.ivana.MyNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/category")
    public String category(){

        return "category";
    }

    @GetMapping("/tag")
    public String tag(){

        return "tags";
    }

    @GetMapping("/timeline")
    public String timeline(){

        return "timeline";
    }

    @GetMapping("/aboutme")
    public String aboutme(){

        return "aboutMe";
    }



//    @GetMapping("/{id}/{name}")
//    public String index2(@PathVariable Integer id, @PathVariable String name){
//
//        System.out.println("----------------index2 method--------------------");
//        System.out.println("id = " + id + ", name = " + name);
//
//        return "index";
//    }
}
