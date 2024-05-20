package itbrains.az.blog.controller;


import itbrains.az.blog.dtos.authdtos.RegisterDto;
import itbrains.az.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {


    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }


    @GetMapping("/register")
    public String register()
    {
        return "register";
    }


    @PostMapping("/register")
    public String register(RegisterDto registerDto)
    {
        boolean res = userService.register(registerDto);
        if (res == false) {
            return "register";
        }
        return "redirect:login";
    }



}
