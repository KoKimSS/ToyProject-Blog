package ToyProject.blogWorld.repository.poster;

import ToyProject.blogWorld.domain.Poster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PosterJpaRepository {
    private final EntityManager em;

    public Poster save(Poster member) {
        em.persist(member);
        return member;
    }
}
