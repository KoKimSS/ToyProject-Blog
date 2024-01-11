package ToyProject.blogWorld.domain;

import ToyProject.blogWorld.domain.baseentity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity@Getter
@NoArgsConstructor(access = PROTECTED)
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "poster_id")
    private Long id;
    @OneToOne
    private Poster poster;
    @OneToOne
    private User user;
    private String contents;
    @OneToOne
    private Reply parentsReply;
}
