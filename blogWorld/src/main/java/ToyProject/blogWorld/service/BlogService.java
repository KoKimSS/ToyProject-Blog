package ToyProject.blogWorld.service;

import ToyProject.blogWorld.entity.Blog.Blog;
import ToyProject.blogWorld.entity.Category.Category;
import ToyProject.blogWorld.entity.Poster.Poster;
import ToyProject.blogWorld.entity.User.User;
import ToyProject.blogWorld.repository.blog.BlogRepository;
import ToyProject.blogWorld.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static ToyProject.blogWorld.entity.Category.Category.*;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    public List<Blog> getUserBlogList(Long userId) {
        return blogRepository.findByUserId(userId);
    }

    public void createNewBlog(String blogName,User user){
        Blog savedBlog = blogRepository.save(Blog.createBlog(blogName, user));
        categoryRepository.save(createBasicCategory(savedBlog));
    }


    public void deleteBlog(Long blogId) {
        Optional<Blog> byId = blogRepository.findById(blogId);
        Blog blog = byId.orElse(null);
        List<Category> categoryList = blog.getCategoryList();
        categoryList.stream().forEach(category -> {
            category.getPosterList()
                    .stream().forEach(poster -> Poster.setValidFalse(poster));
            categoryRepository.delete(category);
        });
    }

    public boolean isBlogOwner(Long blogId, User user) {
        return user.getId()== getBlogById(blogId).getUser().getId();
    }

    public Blog getBlogById(Long blogId) {
        return blogRepository.findById(blogId).get();
    }
}
