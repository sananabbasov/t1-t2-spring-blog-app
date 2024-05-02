package itbrains.az.blog.services.impl;

import itbrains.az.blog.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blog.models.Article;
import itbrains.az.blog.models.Category;
import itbrains.az.blog.repositories.CategoryRepository;
import itbrains.az.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// 123
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void add(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setName(categoryCreateDto.getName());
        categoryRepository.save(category);
    }
}
