package ToyProject.blogWorld.domain;

import ToyProject.blogWorld.domain.baseentity.BaseEntity;
import ToyProject.blogWorld.domain.baseentity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity@Getter
@NoArgsConstructor(access = PROTECTED)
public class Poster extends BaseTimeEntity {
    @Id@GeneratedValue
    @Column(name = "poster_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private String title;
    private String contents;
    private String titlePic;
    private boolean valid;
    @OneToMany(mappedBy = "poster")
    private List<Tag> tagList = new ArrayList<>();
}
