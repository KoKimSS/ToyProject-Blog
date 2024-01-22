package ToyProject.blogWorld.web.controller.user;

import ToyProject.blogWorld.entity.User.User;
import ToyProject.blogWorld.repository.user.UserRepository;
import ToyProject.blogWorld.service.UserService;
import ToyProject.blogWorld.web.form.LoginForm;
import ToyProject.blogWorld.web.form.RegistForm;
import ToyProject.blogWorld.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {


    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    String loginPage() {
        return "user/login";
    }

    @GetMapping("/login/oauth2/code/kakao")
    @ResponseBody
    String kakaoOauthLogin() {
        return "success";
    }


    @GetMapping("/register")
    String registPage(Model model) {
        RegistForm form = new RegistForm();
        model.addAttribute("form", form);
        return "user/register";
    }

    @PostMapping("/register")
    String registUser(@ModelAttribute RegistForm form, Model model) {

        String loginId = form.getLoginId();
        String password = form.getPassword();
        Map<String, String> errors = new HashMap<>();


        if (!errors.isEmpty()) { //에러가 있을 경우 다시 회원가입 창으로
            model.addAttribute("errors", errors);
            model.addAttribute("form", form);
            return "user/register";
        }

        User user = User.createNewUser( form.getLoginId(), bCryptPasswordEncoder.encode(form.getPassword()),form.getUserName(),
                form.getUserPhone(), form.getUserEmail());
        userService.createUser(user);
        return "redirect:/";
    }
}
