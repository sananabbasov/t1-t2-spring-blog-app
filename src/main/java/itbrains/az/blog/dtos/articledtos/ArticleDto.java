package itbrains.az.blog.dtos.articledtos;


import itbrains.az.blog.dtos.categorydtos.CategoryDto;
import itbrains.az.blog.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticleDto {
    private Long id;
    private String title;
    private String photoUrl;
    private Date createdDate;
    private Date updatedDate;
    private int viewCount;
    private CategoryDto category;
}
