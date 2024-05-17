package itbrains.az.blog.services;

import itbrains.az.blog.dtos.articledtos.*;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> getArticles();

    void addArticle(ArticleCreateDto articleDto);
    List<ArticleHomeDto> getHomeArticles();
    void updateArticle(ArticleUpdateDto articleDto);
    ArticleUpdateDto findUpdateArticle(Long id);
    ArticleDetailDto articleDetail(Long id);

    void removeArticle(Long articleId);
}
