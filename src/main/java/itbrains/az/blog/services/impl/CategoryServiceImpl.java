package itbrains.az.blog.services.impl;

import itbrains.az.blog.dtos.categorydtos.CategoryCreateDto;
import itbrains.az.blog.dtos.categorydtos.CategoryDto;
import itbrains.az.blog.models.Article;
import itbrains.az.blog.models.Category;
import itbrains.az.blog.repositories.CategoryRepository;
import itbrains.az.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


// 123
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void add(CategoryCreateDto categoryCreateDto) {

        Category category = modelMapper.map(categoryCreateDto,Category.class);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categories = categoryRepository.findAll().stream().map(category-> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categories;
    }
}
