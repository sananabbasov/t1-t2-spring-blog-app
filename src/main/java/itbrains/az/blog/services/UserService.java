package itbrains.az.blog.services;

import itbrains.az.blog.dtos.authdtos.RegisterDto;
import itbrains.az.blog.dtos.userdtos.UserAddRoleDto;
import itbrains.az.blog.dtos.userdtos.UserDashboardListDto;
import itbrains.az.blog.dtos.userdtos.UserDto;

import java.util.List;

public interface UserService{
    boolean register(RegisterDto register);
    boolean confirmEmail(String email, String token);
    List<UserDashboardListDto> getDashboardUsers();
    UserDto getUserById(Long id);
    void addRole(UserAddRoleDto userAddRole);

}
