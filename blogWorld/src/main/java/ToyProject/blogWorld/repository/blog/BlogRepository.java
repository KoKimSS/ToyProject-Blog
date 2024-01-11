package ToyProject.blogWorld.repository.blog;

import ToyProject.blogWorld.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findByUserId(Long userId);

    // 블로그 이름 중복 체크
    boolean existsByName(String name);
}
