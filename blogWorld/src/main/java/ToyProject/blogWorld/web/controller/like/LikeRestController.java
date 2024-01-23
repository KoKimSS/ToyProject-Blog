package ToyProject.blogWorld.web.controller.like;

import ToyProject.blogWorld.entity.User.User;
import ToyProject.blogWorld.service.LikeService;
import ToyProject.blogWorld.web.util.ControllerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("/api/like")
@RequiredArgsConstructor
public class LikeRestController {
    private final LikeService likeService;
    @PostMapping("/like")
    public boolean like(HttpServletRequest request,Long posterId,boolean state) {
        return likeService.likeAndReturnState(request.getRemoteAddr(),posterId,state);
    }
}
