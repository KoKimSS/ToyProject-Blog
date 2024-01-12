package ToyProject.blogWorld.web.controller.home;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static ToyProject.blogWorld.web.util.ControllerUtil.addUserToModel;

@Slf4j
@Controller
@RequiredArgsConstructor
public class Home {
    @GetMapping("/")
    String homePage(Model model, HttpServletRequest request) {
        addUserToModel(model);
        return "main/home";
    }
}
