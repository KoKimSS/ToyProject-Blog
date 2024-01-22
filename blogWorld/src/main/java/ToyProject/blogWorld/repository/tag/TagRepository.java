package ToyProject.blogWorld.repository.tag;

import ToyProject.blogWorld.entity.Tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
