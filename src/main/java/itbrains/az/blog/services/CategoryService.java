package itbrains.az.blog.services;

import itbrains.az.blog.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blog.models.Article;

public interface CategoryService {
    // DTO
    void add(CategoryCreateDto categoryCreateDto);
}
