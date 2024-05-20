package itbrains.az.blog.services;

import itbrains.az.blog.dtos.authdtos.RegisterDto;

public interface UserService{
    boolean register(RegisterDto register);
    boolean confirmEmail(String email, String token);
}
