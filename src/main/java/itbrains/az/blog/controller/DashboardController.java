package itbrains.az.blog.controller;


import itbrains.az.blog.dtos.articledtos.ArticleCreateDto;
import itbrains.az.blog.dtos.articledtos.ArticleDto;
import itbrains.az.blog.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blog.dtos.categorydtos.CategoryDto;
import itbrains.az.blog.models.Article;
import itbrains.az.blog.services.ArticleService;
import itbrains.az.blog.services.CategoryService;
import itbrains.az.blog.services.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private ArticleService articleService;

    @GetMapping("/admin")
    public String index()
    {

        return "/dashboard/home";
    }


    @GetMapping("/admin/category")
    public String category(Model model)
    {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return "/dashboard/category";
    }

    @GetMapping("/admin/category/category-create")
    public String addCategory()
    {
        return "/dashboard/category-create";
    }


    @PostMapping("/admin/category/create")
    public String addCategory(@ModelAttribute CategoryCreateDto categoryCreateDto)
    {
        categoryService.add(categoryCreateDto);
        return "redirect:/admin/category";
    }


    @GetMapping("/admin/article")
    public String articleGet(Model model)
    {
        List<ArticleDto> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "/dashboard/article";
    }



    @GetMapping("/admin/article/create")
    public String articleCreate(Model model)
    {
        List<CategoryDto> cateogires = categoryService.getAllCategories();
        model.addAttribute("categories", cateogires);
        return "/dashboard/article-create";
    }

    @PostMapping("/admin/article/create")
    public String articleCreate(@ModelAttribute ArticleCreateDto articleDto)
    {
        articleService.addArticle(articleDto);
        return "redirect:/admin/article";
    }


    @GetMapping("/admin/article/remove/{id}")
    public String removeArticle(@ModelAttribute @PathVariable Long id){
        {
            articleService.removeArticle(id);
            return "redirect:/admin/article";

        }

    }
}
