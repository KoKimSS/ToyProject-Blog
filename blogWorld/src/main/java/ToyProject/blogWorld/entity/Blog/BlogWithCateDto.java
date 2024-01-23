package ToyProject.blogWorld.entity.Blog;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogWithCateDto {
    private Long blogId;
    private String blogName;
    private String imgPath;
    private Long categoryId;
    private String categoryName;
}
