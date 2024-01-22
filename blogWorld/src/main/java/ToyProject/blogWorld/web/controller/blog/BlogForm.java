package ToyProject.blogWorld.web.controller.blog;

import ToyProject.blogWorld.dto.CategoryDTO;
import lombok.Data;

import java.util.List;

@Data
public class BlogForm {
    public BlogForm(Long id, String name, List<CategoryDTO> categoryList) {
        this.id = id;
        this.name = name;
        this.categoryList = categoryList;
    }

    Long id;
    String name;
    List<CategoryDTO> categoryList;
}
