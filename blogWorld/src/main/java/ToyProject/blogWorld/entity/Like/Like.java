package ToyProject.blogWorld.entity.Like;

import ToyProject.blogWorld.entity.Poster.Poster;
import ToyProject.blogWorld.entity.User.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static ToyProject.blogWorld.entity.Poster.Poster.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id@GeneratedValue
    @Column(name = "like_id")
    Long id;
    String ipAddr;
    boolean state;
    @OneToOne
    User user;
    @ManyToOne
    Poster poster;

    @Builder
    public Like(Long id, String ipAddr, boolean state, User user, Poster poster) {
        this.id = id;
        this.ipAddr = ipAddr;
        this.state = state;
        this.user = user;
        this.poster = poster;
    }

    public static void changeState(Like like, boolean state){
        if(like.state&&!state){
            decrementLikeCount(like.poster);
        }
        if(!like.state&&state){
            incrementLikeCount(like.poster);
        }
        like.state=state;
    }
}
