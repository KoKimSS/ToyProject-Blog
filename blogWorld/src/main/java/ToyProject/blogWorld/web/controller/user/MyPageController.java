package ToyProject.blogWorld.web.controller.user;

import ToyProject.blogWorld.domain.Blog;
import ToyProject.blogWorld.domain.User;
import ToyProject.blogWorld.repository.blog.BlogRepository;
import ToyProject.blogWorld.repository.user.UserUpdateDto;
import ToyProject.blogWorld.service.BlogService;
import ToyProject.blogWorld.service.UserService;
import ToyProject.blogWorld.web.util.ControllerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final BlogService blogService;
    private final UserService userService;
    private final BlogRepository blogRepository;
    @GetMapping("user/{userId}")
    String myPageForm(@PathVariable(name = "userId")Long userId, Model model){
        List<Blog> userBlogList = blogService.getUserBlogList(userId);
        System.out.println("userBlogList = " + userBlogList);
        model.addAttribute("blogList", userBlogList);
        ControllerUtil.addUserToModel(model);
        return "user/mypage";
    }

    @PostMapping("user/{userId}")
    String updateUser(Model model, UserUpdateDto userUpdateDto) {
        User user = ControllerUtil.addUserToModel(model);
        userService.updateUser(user,userUpdateDto);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("user/{userId}/createBlog")
    String createBlogForm(@PathVariable(name = "userId")Long userId,Model model){
        ControllerUtil.addUserToModel(model);
        return "user/createBlog";
    }

    @PostMapping("user/{userId}/deleteBlog/{blogId}")
    String removeBlog(@PathVariable(required = false) Long blogId,Model model) {
        User user = ControllerUtil.addUserToModel(model);
        Optional<Blog> blog = blogRepository.findById(blogId);
        if(blog.get().getUser().getId()==user.getId()){
            blogRepository.deleteById(blogId);
        }else {
            log.info("삭제불가 : 유저가 가지고있는 블로그가 아닙니다");
        }
        return "redirect:/user/" + user.getId();
    }

    @PostMapping("user/{userId}/createBlog")
    String createBlog(Model model,String blogName){
        User user = ControllerUtil.addUserToModel(model);
        blogService.createNewBlog(blogName,user);
        return "redirect:/user/"+user.getId();
    }

}
