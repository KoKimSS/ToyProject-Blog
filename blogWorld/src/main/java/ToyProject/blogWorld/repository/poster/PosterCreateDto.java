package ToyProject.blogWorld.repository.poster;

import lombok.Data;

@Data
public class PosterCreateDto {
    private String title;
    private String contents;
    private Long categoryId;
}
