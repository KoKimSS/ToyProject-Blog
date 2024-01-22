package ToyProject.blogWorld.web.controller.blog;

import ToyProject.blogWorld.entity.Category.Category;
import ToyProject.blogWorld.entity.Poster.Poster;
import ToyProject.blogWorld.repository.blog.BlogRepository;
import ToyProject.blogWorld.repository.category.CategoryRepository;
import ToyProject.blogWorld.repository.poster.PosterDto;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import ToyProject.blogWorld.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BlogController {
    private final PosterRepository posterRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final BlogRepository blogRepository;

    @GetMapping("blog/{blogId}")
    String getBlog(@PathVariable(required = false) Long blogId, Model model) {
        categoryService.findCategoryAndAddToModel(blogId, model);
        String title = blogRepository.findById(blogId).get().getName();
        model.addAttribute("title", title);
        return "blog/blog";
    }

    @PostMapping("blog/{blogId}/post")
    String createPoster(@PathVariable(required = false) Long blogId, @ModelAttribute PosterDto posterDto, Model model) {
        Category category = categoryRepository.findById(posterDto.getCategoryId()).get();
        posterRepository.save(Poster.createPoster(posterDto.getTitle(), posterDto.getTitle(), category));
        return "redirect:/blog/" + blogId;
    }

    @GetMapping("blog/{blogId}/{categoryId}")
    String getPostersByCategory(@PathVariable(required = false) Long blogId
            , @PathVariable(required = false) Long categoryId
            , Model model) {
        categoryService.findCategoryAndAddToModel(blogId, model);
        model.addAttribute("posters", posterRepository.findAllByCategoryId(categoryId).get());
        return "blog/posterList";
    }
}
