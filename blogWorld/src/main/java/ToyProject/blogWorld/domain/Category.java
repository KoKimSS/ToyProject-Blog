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
public class Category {
    @Id@GeneratedValue
    @Column(name = "category_id")
    private Long id;
    @JoinColumn(name ="blog_id")
    @ManyToOne(fetch = LAZY)
    private Blog blog;
    @OneToMany(mappedBy = "category")
    private List<Poster> posterList = new ArrayList<>();
    private String categoryName;
    @ManyToOne(fetch = LAZY)
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> childCategoryList= new ArrayList<>();
}
