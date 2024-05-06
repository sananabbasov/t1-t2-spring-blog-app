package itbrains.az.blog.services;

import itbrains.az.blog.dtos.articledtos.ArticleCreateDto;
import itbrains.az.blog.dtos.articledtos.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> getArticles();

    void addArticle(ArticleCreateDto articleDto);
}
