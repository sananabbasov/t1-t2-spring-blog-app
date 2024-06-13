package itbrains.az.blog.controller;


import itbrains.az.blog.dtos.articledtos.ArticleCreateDto;
import itbrains.az.blog.dtos.articledtos.ArticleDto;
import itbrains.az.blog.dtos.articledtos.ArticleUpdateDto;
import itbrains.az.blog.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blog.dtos.categorydtos.CategoryDto;
import itbrains.az.blog.dtos.roledtos.RoleDto;
import itbrains.az.blog.dtos.userdtos.UserAddRoleDto;
import itbrains.az.blog.dtos.userdtos.UserDashboardListDto;
import itbrains.az.blog.dtos.userdtos.UserDto;
import itbrains.az.blog.models.Article;
import itbrains.az.blog.services.ArticleService;
import itbrains.az.blog.services.CategoryService;
import itbrains.az.blog.services.RoleService;
import itbrains.az.blog.services.UserService;
import itbrains.az.blog.services.impl.ArticleServiceImpl;
import itbrains.az.blog.services.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
public class DashboardController {
    final String uploadLocation = getClass().getClassLoader().getResource("static/uploads").toString();
    final Path uploadDirectory = Paths.get(uploadLocation.substring(6, uploadLocation.length()) );

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;


    @Autowired
    private RoleService roleService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";
//                                                                            /src/main/resources/static/uploads


    @GetMapping("/admin")
    public String index() {

        return "/dashboard/home";
    }



    @GetMapping("/admin/category")
    public String category(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/dashboard/category";
    }

    @GetMapping("/admin/category/category-create")
    public String addCategory() {
        return "/dashboard/category-create";
    }


    @PostMapping("/admin/category/create")
    public String addCategory(@ModelAttribute CategoryCreateDto categoryCreateDto) {
        categoryService.add(categoryCreateDto);
        return "redirect:/admin/category";
    }


    @GetMapping("/admin/article")
    public String articleGet(Model model) {
        List<ArticleDto> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "/dashboard/article";
    }


    @GetMapping("/admin/article/create")
    public String articleCreate(Model model) {
        List<CategoryDto> cateogires = categoryService.getAllCategories();
        model.addAttribute("categories", cateogires);
        return "/dashboard/article-create";
    }

    @PostMapping("/admin/article/create")
    public String articleCreate(@ModelAttribute ArticleCreateDto articleDto, @RequestParam("image")MultipartFile image) throws IOException {
        if (!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }
        UUID rand = UUID.randomUUID();
        String filename = rand + image.getOriginalFilename();
        articleDto.setPhotoUrl("/uploads/" + filename);

        Files.copy(image.getInputStream(), uploadDirectory.resolve(filename));
        articleService.addArticle(articleDto);
        return "redirect:/admin/article";
    }


    @GetMapping("/admin/article/remove/{id}")
    public String removeArticle(@PathVariable Long id) {
        {
            articleService.removeArticle(id);
            return "redirect:/admin/article";

        }

    }


    @GetMapping("/admin/article/update/{id}")
    public String updateArticle(@ModelAttribute @PathVariable Long id, Model model) {
        ArticleUpdateDto articleUpdateDto = articleService.findUpdateArticle(id);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("article", articleUpdateDto);
        return "dashboard/article/update";
    }

    @PostMapping("/admin/article/update")
    public String updateArticle(@ModelAttribute ArticleUpdateDto articleUpdateDto) {
        articleService.updateArticle(articleUpdateDto);
        return "redirect:/admin/article";
    }


    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        List<UserDashboardListDto> userList = userService.getDashboardUsers();
        model.addAttribute("users", userList);
        return "/dashboard/auth/user-list";
    }


    @GetMapping("/admin/users/role/{id}")
    public String addRole(@PathVariable Long id, Model model) {
        List<RoleDto> roles = roleService.getRoles();
        UserDto user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "/dashboard/auth/user-role";
    }


    @PostMapping("/admin/users/addrole")
    public String addRole(UserAddRoleDto addRoleDto)
    {
        userService.addRole(addRoleDto);
        return "/dashboard/auth/user-list";
    }
}
