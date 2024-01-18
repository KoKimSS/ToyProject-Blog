package ToyProject.blogWorld.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ymlController {

//    @Value("${environment}")
//    private String yml;

    @GetMapping("/yml")
    String getYml() {
        return "";
    }
}
