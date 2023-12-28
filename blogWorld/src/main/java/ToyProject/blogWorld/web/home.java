package ToyProject.blogWorld.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class home {
    @GetMapping("/")
    String homePage(Model model, HttpServletRequest request) {
        return "main/bloglist";
    }
}
