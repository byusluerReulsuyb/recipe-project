package com.byusluer.recipeproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"","/","/index"})
    public String getIndexPage(){

        System.out.println("some message: edwdewewdwfefefedewdwdwewdw545454dewdwdew");
        return "index";
    }
}
