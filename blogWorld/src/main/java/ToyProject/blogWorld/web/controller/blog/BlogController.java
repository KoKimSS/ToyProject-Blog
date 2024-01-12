package ToyProject.blogWorld.web.controller.blog;

import ToyProject.blogWorld.domain.Category;
import ToyProject.blogWorld.domain.Poster;
import ToyProject.blogWorld.repository.blog.BlogRepository;
import ToyProject.blogWorld.repository.category.CategoryRepository;
import ToyProject.blogWorld.repository.poster.PosterCreateDto;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import ToyProject.blogWorld.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BlogController {
    private final BlogRepository blogRepository;
    private final PosterRepository posterRepository;
    private final CategoryRepository categoryRepository;

    private final BlogService blogService;
    @GetMapping("blog/{blogId}")
    String getBlog(@PathVariable(required = false) Long blogId, Model model) {
        blogService.initBlog(model,blogId);
        return "blog/blog";
    }

    @PostMapping("blog/{blogId}/post")
    String createPoster(@PathVariable(required = false) Long blogId, @ModelAttribute PosterCreateDto posterCreateDto, Model model) {
        Category category = categoryRepository.findById(posterCreateDto.getCategoryId()).get();
        posterRepository.save(Poster.createPoster(posterCreateDto.getTitle(), posterCreateDto.getTitle(), category));
        return "redirect:/blog/" + blogId;
    }

    @GetMapping("blog/{blogId}/{categoryId}")
    String getPostersByCategory(@PathVariable(required = false) Long blogId
            , @PathVariable(required = false) Long categoryId
            , Model model) {
        blogService.initBlog(model,blogId);
        List<Poster> posters = posterRepository.findAllByCategoryId(categoryId).get();
        model.addAttribute("posters", posters);
        return "blog/posterList";
    }
}
