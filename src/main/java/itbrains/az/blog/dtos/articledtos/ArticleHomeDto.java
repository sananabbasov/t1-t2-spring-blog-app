package itbrains.az.blog.dtos.articledtos;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticleHomeDto {
    private Long id;
    private String title;
    private String subTitle;
    private Date createdDate;
}
