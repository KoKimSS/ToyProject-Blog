package ToyProject.blogWorld.web.controller.poster;

import ToyProject.blogWorld.domain.Poster;
import ToyProject.blogWorld.domain.Reply;
import ToyProject.blogWorld.domain.User;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import ToyProject.blogWorld.repository.reply.ReplyRepository;
import ToyProject.blogWorld.service.BlogService;
import ToyProject.blogWorld.service.ReplyService;
import ToyProject.blogWorld.web.util.ControllerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PosterController {

    private final BlogService blogService;
    private final PosterRepository posterRepository;
    private final ReplyRepository replyRepository;
    private final ReplyService replyService;

    @GetMapping("blog/{blogId}/post")
    String getPosterForm(@PathVariable(required = false) Long blogId, Model model) {
        blogService.initBlog(model,blogId);
        return "blog/createPoster";
    }

    @GetMapping("blog/{blogId}/poster/{posterId}")
    String getPoster(@PathVariable(required = false) Long blogId,
                     @PathVariable(required = false) Long posterId,
                     Model model){
        blogService.initBlog(model,blogId);
        Poster poster = posterRepository.findById(posterId).get();
        model.addAttribute("poster", poster);
        return "/poster/poster";
    }

    @PostMapping("blog/{blogId}/poster/{posterId}/reply")
    String postReply(@PathVariable(required = false) Long blogId,
                     @PathVariable(required = false) Long posterId,
                     @RequestParam(required = false) Long parentsReplyId,
                     @RequestParam String comment,
                     Model model){
        User user = ControllerUtil.addUserToModel(model);
        replyService.createReply(comment,user,posterId,parentsReplyId);
        return "redirect:/blog/{blogId}/poster/"+posterId;
    }
}
