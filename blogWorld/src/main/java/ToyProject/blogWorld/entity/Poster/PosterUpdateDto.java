package ToyProject.blogWorld.entity.Poster;

import lombok.Data;

@Data
public class PosterUpdateDto {
    private Long id;
    private String title;
    private String contents;
    private Long categoryId;
}
