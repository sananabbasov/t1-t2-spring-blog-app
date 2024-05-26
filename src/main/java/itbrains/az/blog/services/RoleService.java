package itbrains.az.blog.services;

import itbrains.az.blog.dtos.roledtos.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getRoles();
}
