package ToyProject.blogWorld.service;

import ToyProject.blogWorld.entity.Category.Category;
import ToyProject.blogWorld.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public void findCategoryAndAddToModel(Long blogId, Model model) {
        List<Category> categories = categoryRepository.findAllByBlogId(blogId).get();
        model.addAttribute("categories", categories);
    }
}
