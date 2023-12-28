package ToyProject.blogWorld.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity@Getter
@NoArgsConstructor(access = PROTECTED)
public class Tag {
    @Id@GeneratedValue
    @Column(name = "tag_id")
    private Long id;
    private String name;
    @ManyToOne(fetch = LAZY)
    private Poster poster;

}
