package ToyProject.blogWorld.dto;

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
