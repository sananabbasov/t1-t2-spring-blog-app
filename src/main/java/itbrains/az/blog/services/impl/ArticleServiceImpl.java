package itbrains.az.blog.services.impl;


import itbrains.az.blog.dtos.articledtos.ArticleCreateDto;
import itbrains.az.blog.dtos.articledtos.ArticleDto;
import itbrains.az.blog.models.Article;
import itbrains.az.blog.models.Category;
import itbrains.az.blog.repositories.ArticleRepository;
import itbrains.az.blog.repositories.CategoryRepository;
import itbrains.az.blog.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ArticleDto> getArticles() {
        List<ArticleDto> articleDtoList = articleRepository.findAll().stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
        return articleDtoList;
    }

    @Override
    public void addArticle(ArticleCreateDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);
        Category category = categoryRepository.findById(articleDto.getCategoryId()).get();
        article.setUpdatedDate(new Date());
        article.setCreatedDate(new Date());
        article.setCategory(category);
        articleRepository.save(article);
    }
}
