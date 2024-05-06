package itbrains.az.blog.controller;


import itbrains.az.blog.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blog.repositories.CategoryRepository;
import itbrains.az.blog.services.CategoryService;
import itbrains.az.blog.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService; // 123
    @GetMapping("/")
    public String index()
    {
        return "Home";
    }

    @PostMapping("/create")
    public String Create(@ModelAttribute CategoryCreateDto categoryCreateDto)
    {
        categoryService.add(categoryCreateDto);
        return "Home";
    }
}
