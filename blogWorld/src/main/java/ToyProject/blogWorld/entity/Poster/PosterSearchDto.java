package ToyProject.blogWorld.entity.Poster;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PosterSearchDto {
    private Long id;
    private String title;
    private String imgPath;
    private String contents;
    private LocalDateTime lastModifiedDate;
    private int viewCount;
    private int likeCount;

    @QueryProjection
    public PosterSearchDto(Long id, String title, String imgPath, String contents, LocalDateTime lastModifiedDate, int viewCount, int likeCount) {
        this.id = id;
        this.title = title;
        this.imgPath = imgPath;
        this.contents = contents;
        this.lastModifiedDate = lastModifiedDate;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }
}
