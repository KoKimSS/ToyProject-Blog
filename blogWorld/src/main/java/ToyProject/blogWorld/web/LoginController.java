package ToyProject.blogWorld.web;

import ToyProject.blogWorld.domain.User;
import ToyProject.blogWorld.service.UserService;
import ToyProject.blogWorld.web.form.RegistForm;
import ToyProject.blogWorld.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
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

    @GetMapping("/login")
    String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    String login(HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURL, Model model) {

        Map<String, String> errors = new HashMap<>();
        String userUid = request.getParameter("userUid");
        String userUpw = request.getParameter("userUpw");
        log.info("id ={} pw ={}", userUid, userUpw);
        if (userUid.equals("")) {
            errors.put("userUid", "아이디를 입력하셔야 합니다.");
        }
        if (userUpw.equals("")) {
            errors.put("userUpw", "비밀번호를 입력하셔야 합니다.");
        }
        if (!errors.isEmpty()) { //에러가 있을 경우 다시 회원가입 창으로
            log.info("로그인 값 에러 발생");
            model.addAttribute("errors", errors);
            return "user/login";
        }
        Optional<User> user = userService.login(userUid, userUpw);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            log.info(session.toString());
            session.setAttribute(SessionConst.LOGIN_MEMBER, user.get());
            log.info("리다이렉트 URL = {}", redirectURL);
            return "redirect:" + redirectURL;
        }
        log.info("로그인 정보 안맞음");
        errors.put("login", "로그인 정보가 잘못 됐습니다.");
        model.addAttribute("errors", errors);
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
    String registUser(@ModelAttribute RegistForm form, Model model){

        Map<String, String> errors = new HashMap<>();
        if(!StringUtils.hasText(form.getUserUid())){
            errors.put("userUid","유저 아이디는 필수 입니다."); //유저 이름은 15글자 까지
        }else{
            if (form.getUserUid().length() > 15) {
                errors.put("userUid", "유저 아이디는 15글자까지 입니다.");
            }else{
                if(!validate(form.getUserUid())){
                    errors.put("userUid", "유저 아이디는 영소문자와 숫자로 이루어 집니다.");
                }
            }
        }

        if(!StringUtils.hasText(form.getUserUpw())){
            errors.put("userUpw","유저 비밀번호는 필수 입니다.");
        }else{
            if (form.getUserUpw().length() > 15|| form.getUserUpw().length()<9) {
                errors.put("userUpw", "유저 비밀번호는 9~15글자 입니다.");
            }else{
                log.info("비밀번호 검증={}",validate(form.getUserUpw()));
                if(!validate(form.getUserUpw())){
                    errors.put("userUpw", "유저 비밀번호는 영소문자와 숫자로 이루어 집니다.");
                }
            }
        }

        if(!StringUtils.hasText(form.getUserName())){
            errors.put("userName","유저 이름은 필수 입니다.");
        }
        if(!StringUtils.hasText(form.getUserPhone())){
            errors.put("userPhone","핸드폰 번호는 필수 입니다.");
        }else{
            if(!validatePhoneNumber(form.getUserPhone())){
                errors.put("userPhone","핸드폰 번호는 모두 숫자이며 9자링 이상 입니다.");
            }
        }

        if(!StringUtils.hasText(form.getUserEmail())){
            errors.put("userEmail","이메일은 필수 입니다.");
        }else{
            if (!validateEmail(form.getUserEmail())) {
                errors.put("userEmail","이메일은 형식과 맞지 않습니다.");
            }
        }

        if(!errors.isEmpty()){ //에러가 있을 경우 다시 회원가입 창으로
            model.addAttribute("errors",errors);
            model.addAttribute("form",form);
            return "user/register";
        }
        User user = User.createNewUser(form.getUserName(), form.getUserUid(), form.getUserUpw(),
                form.getUserPhone(), form.getUserEmail());

        userService.createUser(user);

        return "redirect:/";
    }

    public boolean validate(String userId) {
        Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*\\d).+$");
        Matcher m = p.matcher(userId);
        return m.matches();
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("^\\d{9,}$");
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    public boolean validateEmail(String email) {
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)*(\\.[a-zA-Z]{2,})$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
