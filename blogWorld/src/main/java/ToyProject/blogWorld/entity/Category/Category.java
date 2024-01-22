package ToyProject.blogWorld.entity.Category;

import ToyProject.blogWorld.entity.Blog.Blog;
import ToyProject.blogWorld.entity.Poster.Poster;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Category {
    @Id@GeneratedValue
    @Column(name = "category_id")
    private Long id;
    @NotBlank
    private String categoryName;
    @JoinColumn(name ="blog_id")
    @ManyToOne(fetch = LAZY)
    private Blog blog;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Poster> posterList = new ArrayList<>();

    public static Category createCategory(String name, Blog blog){
        Category newCate = new Category();
        newCate.categoryName = name;
        newCate.blog = blog;
        return newCate;
    }

    public static Category createBasicCategory(Blog blog){
        Category basicCategory = new Category();
        basicCategory.categoryName = "전체";
        basicCategory.blog = blog;
        return basicCategory;
    }
}
