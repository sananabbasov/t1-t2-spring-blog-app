package itbrains.az.blog.services;

import itbrains.az.blog.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blog.dtos.categorydtos.CategoryDto;
import itbrains.az.blog.models.Article;

import java.util.List;

public interface CategoryService {
    // DTO
    void add(CategoryCreateDto categoryCreateDto);
    List<CategoryDto> getAllCategories();
}
