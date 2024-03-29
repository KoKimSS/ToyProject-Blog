package ToyProject.blogWorld.domain;

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
    private boolean valid;
    private int viewCount;
    private int likeCount;
    @OneToMany(mappedBy = "poster",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tagList = new ArrayList<>();
    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();

    public static Poster createPoster(String title, String contents,Category category) {
        Poster poster = new Poster();
        poster.title = title;
        poster.contents = contents;
        poster.category = category;
        return poster;
    }

    public void editPoster(String title, String contents, Category category) {
        this.title = title;
        this.contents = contents;
        this.category = category;
    }

    public static void setValidFalse(Poster poster) {
        poster.category=null;
        poster.valid=false;
    }
    public static void incrementViewCount(Poster poster){
        poster.viewCount++;
    }
}
