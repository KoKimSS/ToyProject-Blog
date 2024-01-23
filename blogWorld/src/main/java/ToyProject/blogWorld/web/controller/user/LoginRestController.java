package ToyProject.blogWorld.web.controller.user;

import ToyProject.blogWorld.entity.User.User;
import ToyProject.blogWorld.entity.User.UserDto;
import ToyProject.blogWorld.web.util.ControllerUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoginRestController {
    @PostMapping("/login")
    UserDto login() {
        User user = ControllerUtil.findUserFromAuth();
        System.out.println(user.getName()+" 로그인");
        return new UserDto(user.getId(), user.getLoginId(), user.getPassword());
    }
}
