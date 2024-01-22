package ToyProject.blogWorld.validation;

import ToyProject.blogWorld.entity.User.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


public class UserValidationTest {

    Validator validator;

    @BeforeEach
    public void before(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    @Test
    public void createUser_validation_성공 () {
        //given
        String loginId = "abc123";
        String pw = "abc123";
        String name = "abc123";
        String phone = "abc123";
        String email = "abc123";

        //when
        User newUser = User.createNewUser(loginId, pw, name, phone, email);
        Set<ConstraintViolation<User>> validate = validator.validate(newUser);

        //then
        Assertions.assertThat(validate.size()).isEqualTo(1);
    }

    @Test
    public void createUser_validation_실패() throws Exception {
        //given
        String loginId = "";
        String pw = "abc123";
        String name = "abc123";
        String phone = "abc123";
        String email = "abc123";
        //when

        //then

    }
}
