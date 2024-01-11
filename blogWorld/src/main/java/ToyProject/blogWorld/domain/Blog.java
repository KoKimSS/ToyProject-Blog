package ToyProject.blogWorld.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
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
    private String name;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categoryList;

    public static Blog createBlog(String name, User user) {
        Blog blogEntity = new Blog();
        blogEntity.name=name;
        blogEntity.user=user;
        return blogEntity;
    }
}
