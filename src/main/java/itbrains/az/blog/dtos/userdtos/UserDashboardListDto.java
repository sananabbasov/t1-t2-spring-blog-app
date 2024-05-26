package itbrains.az.blog.dtos.userdtos;


import itbrains.az.blog.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDashboardListDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean emailConfirmed;
    private List<Role> roles = new ArrayList<>();
}
