package ToyProject.blogWorld.web.controller.poster;

import ToyProject.blogWorld.entity.User.User;
import ToyProject.blogWorld.repository.poster.PosterDto;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import ToyProject.blogWorld.repository.reply.ReplyRepository;
import ToyProject.blogWorld.service.BlogService;
import ToyProject.blogWorld.service.CategoryService;
import ToyProject.blogWorld.service.PosterService;
import ToyProject.blogWorld.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ToyProject.blogWorld.web.util.ControllerUtil.findAndAddUserToModel;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PosterController {

    private final PosterRepository posterRepository;
    private final PosterService posterService;
    private final ReplyRepository replyRepository;
    private final ReplyService replyService;
    private final CategoryService categoryService;
    private final BlogService blogService;

    @GetMapping("blog/{blogId}/post")
    String getPosterForm(@PathVariable(required = false) Long blogId, Model model) {
        categoryService.findCategoryAndAddToModel(blogId, model);
        return "blog/createPoster";
    }

    @GetMapping("blog/{blogId}/poster/{posterId}")
    String getPoster(@PathVariable(required = false) Long blogId,
                     @PathVariable(required = false) Long posterId,
                     Model model) {
        categoryService.findCategoryAndAddToModel(blogId, model);
        User user = findAndAddUserToModel(model);
        model.addAttribute("poster", posterService.findForUserClick(posterId));
        model.addAttribute("replies",
                replyRepository.findAllByPosterIdAndParentsReplyIsNull(posterId).orElse(null));
        model.addAttribute("isOwner", blogService.isBlogOwner(blogId, user));
        log.info("getPoster 수행");
        return "poster/poster";
    }

    @GetMapping("blog/{blogId}/poster/{posterId}/edit")
    String getPosterEditForm(@PathVariable(required = false) Long blogId,
                             @PathVariable(required = false) Long posterId,
                             Model model) {
        categoryService.findCategoryAndAddToModel(blogId, model);
        model.addAttribute("poster", posterRepository.findById(posterId).get());
        return "blog/editPoster";
    }

    @RequestMapping(value = "blog/{blogId}/poster/{posterId}/edit", method = RequestMethod.PATCH)
    void editPoster(@PathVariable(required = false) Long blogId,
                                      @PathVariable(required = false) Long posterId,
                                      @ModelAttribute PosterDto posterDto,
                                      Model model,
                                      HttpServletResponse response) throws IOException {
        categoryService.findCategoryAndAddToModel(blogId, model);
        posterService.updatePosterByDto(posterId, posterDto);
        String redirectUrl = "/blog/" + blogId + "/poster/" + posterId;
        System.out.println("redirectUrl = " + redirectUrl);
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.sendRedirect(redirectUrl);
        response.setHeader("redirectUrl",redirectUrl);
        // 리다이렉션을 원하는 경우
    }

    @PostMapping("blog/{blogId}/poster/{posterId}/reply")
    String postReply(@PathVariable(required = false) Long blogId,
                     @PathVariable(required = false) Long posterId,
                     @RequestParam(required = false) Long parentsReplyId,
                     @RequestParam String comment,
                     Model model) {
        User user = findAndAddUserToModel(model);
        replyService.createReply(comment, user, posterId, parentsReplyId);
        return "redirect:/blog/" + blogId + "/poster/" + posterId;
    }
}
