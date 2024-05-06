package itbrains.az.blog.dtos.articledtos;


import itbrains.az.blog.models.Category;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ArticleCreateDto {
    private String title;
    private String subTitle;
    private String description;
    private String photoUrl;
    private Long categoryId;
}
