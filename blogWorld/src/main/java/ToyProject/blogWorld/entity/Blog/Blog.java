package ToyProject.blogWorld.entity.Blog;

import ToyProject.blogWorld.entity.Category.Category;
import ToyProject.blogWorld.entity.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.List;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Blog {
    @Id
    @GeneratedValue
    @Column(name = "blog_id")
    private Long id;
    @NotBlank
    private String name;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "blog_img_path",length = 100)
    private String imgPath;

    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categoryList;

    public static Blog createBlog(String name, User user) {
        Blog blogEntity = new Blog();
        blogEntity.name=name;
        blogEntity.user=user;
        return blogEntity;
    }
}
