package ToyProject.blogWorld.web.controller.poster;

import ToyProject.blogWorld.domain.Poster;
import ToyProject.blogWorld.domain.User;
import ToyProject.blogWorld.repository.category.CategoryRepository;
import ToyProject.blogWorld.repository.poster.PosterDto;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import ToyProject.blogWorld.repository.reply.ReplyRepository;
import ToyProject.blogWorld.service.BlogService;
import ToyProject.blogWorld.service.CategoryService;
import ToyProject.blogWorld.service.PosterService;
import ToyProject.blogWorld.service.ReplyService;
import groovyjarjarantlr4.v4.gui.JFileChooserConfirmOverwrite;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static ToyProject.blogWorld.domain.Poster.incrementViewCount;
import static ToyProject.blogWorld.web.util.ControllerUtil.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PosterController {

    private final PosterRepository posterRepository;
    private final PosterService posterService;
    private final ReplyRepository replyRepository;
    private final ReplyService replyService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @GetMapping("blog/{blogId}/post")
    String getPosterForm(@PathVariable(required = false) Long blogId, Model model) {
        categoryService.findCategoryAndAddToModel(blogId,model);
        return "blog/createPoster";
    }

    @GetMapping("blog/{blogId}/poster/{posterId}")
    String getPoster(@PathVariable(required = false) Long blogId,
                     @PathVariable(required = false) Long posterId,
                     Model model) {
        categoryService.findCategoryAndAddToModel(blogId,model);
        Poster poster = posterService.findForUserClick(posterId);
        model.addAttribute("poster", poster);
        model.addAttribute("replies",
                replyRepository.findAllByPosterIdAndParentsReplyIsNull(posterId).orElse(null));
        model.addAttribute("isOwner", true);

        return "/poster/poster";
    }

    @GetMapping("blog/{blogId}/poster/{posterId}/edit")
    String getPosterEditForm(@PathVariable(required = false) Long blogId,
                             @PathVariable(required = false) Long posterId,
                             Model model){
        categoryService.findCategoryAndAddToModel(blogId,model);
        Poster poster = posterRepository.findById(posterId).get();
        model.addAttribute("poster", poster);
        return "/blog/editPoster";
    }

    @PostMapping("blog/{blogId}/poster/{posterId}/edit")
    String editPoster(@PathVariable(required = false) Long blogId,
                             @PathVariable(required = false) Long posterId,
                             @ModelAttribute PosterDto posterDto,
                             Model model){
        log.info("수정 시작");
        categoryService.findCategoryAndAddToModel(blogId,model);
        posterService.updatePosterByDto(posterId,posterDto);
        return "redirect:/blog/" + blogId + "/poster/" + posterId;
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
