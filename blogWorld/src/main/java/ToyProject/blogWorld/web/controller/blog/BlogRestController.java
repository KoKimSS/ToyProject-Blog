package ToyProject.blogWorld.web.controller.blog;

import ToyProject.blogWorld.entity.Blog.Blog;
import ToyProject.blogWorld.entity.Category.CategoryDTO;
import ToyProject.blogWorld.repository.blog.BlogRepository;
import ToyProject.blogWorld.entity.Blog.BlogWithCateDto;
import ToyProject.blogWorld.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
@Slf4j
public class BlogRestController {
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("getAll")
    List<BlogWithCateDto> getAllBlog() {
        List<BlogWithCateDto> blogList = blogRepository.findAllBlogs();
        return blogList;
    }

    @GetMapping("blogApi/{blogId}")
    BlogForm getBlogById(@PathVariable Long blogId) {
        Blog blog = blogRepository.findById(blogId).get();
        List<CategoryDTO> categories = categoryRepository.findAllDtoByBlogId(blogId);
        BlogForm blogForm = new BlogForm(blogId, blog.getName(), categories);
        return blogForm;
    }


}
