package ToyProject.blogWorld.domain;

import ToyProject.blogWorld.domain.baseentity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity@Getter
@NoArgsConstructor(access = PROTECTED)
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "poster_id")
    private Long id;
    @ManyToOne
    private Poster poster;
    @OneToOne
    private User user;
    private String contents;
    private boolean valid;
    @ManyToOne
    private Reply parentsReply;
    @OneToMany(mappedBy = "parentsReply")
    private List<Reply> childReply;

    public static Reply createReply(String contents, Poster poster, User user,Reply parentsReply) {
        Reply reply = new Reply();
        reply.contents=contents;
        reply.poster=poster;
        reply.user=user;
        reply.parentsReply = parentsReply;
        return reply;
    }
}
