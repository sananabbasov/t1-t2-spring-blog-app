package itbrains.az.blog.services.impl;


import itbrains.az.blog.dtos.articledtos.*;
import itbrains.az.blog.helpers.SeoHelper;
import itbrains.az.blog.models.Article;
import itbrains.az.blog.models.Category;
import itbrains.az.blog.repositories.ArticleRepository;
import itbrains.az.blog.repositories.CategoryRepository;
import itbrains.az.blog.services.ArticleService;
import itbrains.az.blog.services.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ArticleDto> getArticles() {
        List<ArticleDto> articleDtoList = articleRepository.findAll().stream()
                .filter(x->x.getIsDeleted() == false)
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
        return articleDtoList;
    }

    @Override
    public void addArticle(ArticleCreateDto articleDto) {
        try {
            Article article = new Article();
            article.setUpdatedDate(new Date());
            article.setCreatedDate(new Date());
            article.setTitle(articleDto.getTitle());
            SeoHelper seoHelper = new SeoHelper();
            article.setSeoUrl(seoHelper.seoUrlHelper(articleDto.getTitle()));
            article.setDescription(articleDto.getDescription());
            Category category = categoryRepository.findById(articleDto.getCategoryId()).get();
            article.setCategory(category);
            articleRepository.save(article);
        }catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ArticleHomeDto> getHomeArticles() {

        List<ArticleHomeDto> articleDtoList = articleRepository.findAll().stream()
                .filter(x->x.getIsDeleted() == false)
                .map(article -> modelMapper.map(article, ArticleHomeDto.class))
                .collect(Collectors.toList());
        return articleDtoList;
    }

    @Override
    public void updateArticle(ArticleUpdateDto articleDto) {
        Article findArticle = articleRepository.findById(articleDto.getId()).orElseThrow();
        Category category = categoryRepository.findById(articleDto.getCategoryId()).orElseThrow();
        findArticle.setId(articleDto.getId());
        findArticle.setTitle(articleDto.getTitle());
        SeoHelper seoHelper = new SeoHelper();
        findArticle.setSeoUrl(seoHelper.seoUrlHelper(articleDto.getTitle()));
        findArticle.setDescription(articleDto.getDescription());
        findArticle.setUpdatedDate(new Date());
        findArticle.setPhotoUrl(articleDto.getPhotoUrl());
        findArticle.setCategory(category);
        articleRepository.saveAndFlush(findArticle);
    }

    @Override
    public ArticleUpdateDto findUpdateArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleUpdateDto articleUpdateDto = modelMapper.map(article, ArticleUpdateDto.class);
        return articleUpdateDto;
    }

    @Override
    public ArticleDetailDto articleDetail(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleDetailDto articleUpdateDto = modelMapper.map(article, ArticleDetailDto.class);
        return articleUpdateDto;
    }

    @Override
    public void removeArticle(Long articleId) {
        Article article =  articleRepository.findById(articleId).orElseThrow();
        article.setIsDeleted(true);
        articleRepository.save(article);
    }



}
