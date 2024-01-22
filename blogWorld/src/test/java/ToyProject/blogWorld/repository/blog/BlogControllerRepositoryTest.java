package ToyProject.blogWorld.repository.blog;

import ToyProject.blogWorld.entity.Blog.Blog;
import ToyProject.blogWorld.entity.User.User;
import ToyProject.blogWorld.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Slf4j
class BlogControllerRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BlogRepository blogRepository;

    @Test
    public void save() throws Exception {
        //given
        User user = User.createNewUser("seungsu", "994499", "승수", "01012345678", "seungsu@naver.com");
        User savedUser = userRepository.save(user);
        Blog blog = Blog.createBlog("블로그이름", savedUser);

        //when
        Blog savedBlog = blogRepository.save(blog);

        //then
        Assertions.assertThat(savedBlog).isEqualTo(blog);
    }

    @Test
    public void update() throws Exception {
        //given

        //when

        //then

    }
}