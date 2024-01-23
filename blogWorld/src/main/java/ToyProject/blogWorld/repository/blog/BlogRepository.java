package ToyProject.blogWorld.repository.blog;

import ToyProject.blogWorld.entity.Blog.Blog;
import ToyProject.blogWorld.entity.Blog.BlogWithCateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findByUserId(Long userId);
    List<Blog> findAll();

    @Query("SELECT new ToyProject.blogWorld.entity.Blog.BlogWithCateDto(b.id, b.name,b.imgPath ,c.id, c.categoryName) FROM Blog b JOIN b.categoryList c")
    List<BlogWithCateDto> findAllBlogs();


    // 블로그 이름 중복 체크
    boolean existsByName(String name);
}
