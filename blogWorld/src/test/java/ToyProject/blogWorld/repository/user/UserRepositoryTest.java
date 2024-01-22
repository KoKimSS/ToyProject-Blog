package ToyProject.blogWorld.repository.user;

import ToyProject.blogWorld.entity.User.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void save() throws Exception {
        //given
        User user = User.createNewUser("loginId", "password", "userName", "01000000000", "seungsu@naver.com");

        //when
        User save = userRepository.save(user);

        //then
        System.out.println("save.getCreatedDate() = " + save.getCreatedDate());
        Assertions.assertThat(save.getLoginId()).isEqualTo("loginId");
    }
}