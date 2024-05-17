package itbrains.az.blog.controller;


import itbrains.az.blog.dtos.articledtos.ArticleDto;
import itbrains.az.blog.dtos.articledtos.ArticleHomeDto;
import itbrains.az.blog.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blog.repositories.CategoryRepository;
import itbrains.az.blog.services.ArticleService;
import itbrains.az.blog.services.CategoryService;
import itbrains.az.blog.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService; // 123
    @GetMapping("/")
    public String index(Model model)
    {
        List<ArticleHomeDto> homeArticles =  articleService.getHomeArticles();
        model.addAttribute("articles",homeArticles);
        return "Home";
    }



}
