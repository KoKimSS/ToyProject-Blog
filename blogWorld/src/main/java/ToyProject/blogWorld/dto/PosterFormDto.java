package ToyProject.blogWorld.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PosterFormDto {
    private Long id;
    private String title;
    private String imgPath;

    private String CategoryName;
    private String contents;
    private LocalDateTime lastModifiedDate;
    private int viewCount;
    private int likeCount;
    public PosterFormDto(Long id, String title,String imgPath, String categoryName, String contents, LocalDateTime lastModifiedDate, int viewCount, int likeCount) {
        this.id = id;
        this.title = title;
        this.imgPath=imgPath;
        this.CategoryName = categoryName;
        this.contents = contents;
        this.lastModifiedDate = lastModifiedDate;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }
}
