package ToyProject.blogWorld.repository.category;

import ToyProject.blogWorld.entity.Category.Category;
import ToyProject.blogWorld.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<List<Category>> findAllByBlogId(Long blogId);
    @Query("select new ToyProject.blogWorld.entity.Category.CategoryDTO(c.id,c.categoryName) from Category c join c.blog b")
    List<CategoryDTO> findAllDtoByBlogId(Long blogId);
}
