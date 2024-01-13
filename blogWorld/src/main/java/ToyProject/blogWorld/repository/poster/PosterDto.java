package ToyProject.blogWorld.repository.poster;

import lombok.Data;

@Data
public class PosterDto {
    private String title;
    private String contents;
    private Long categoryId;
}
