package ToyProject.blogWorld.entity.Poster;

import ToyProject.blogWorld.entity.Category.Category;
import ToyProject.blogWorld.entity.Like.Like;
import ToyProject.blogWorld.entity.Reply.Reply;
import ToyProject.blogWorld.entity.Tag.Tag;
import ToyProject.blogWorld.entity.baseEntity.BaseTimeEntity;
import lombok.Builder;
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
    private String imgPath;
    private boolean valid;
    private int viewCount;
    private int likeCount;
    @OneToMany(mappedBy = "poster",cascade = CascadeType.ALL)
    private List<Like> likeList = new ArrayList<>();
    @OneToMany(mappedBy = "poster",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tagList = new ArrayList<>();
    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();


    @Builder
    public Poster(String title, String contents, Category category,int viewCount,int likeCount) {
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }

    @Builder
    public Poster(String title, String contents, Category category) {
        this.title = title;
        this.contents = contents;
        this.category = category;
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
    public static void incrementLikeCount(Poster poster){
        poster.likeCount++;
    }

    public static void decrementLikeCount(Poster poster){
        poster.likeCount--;
    }
}
