package ToyProject.blogWorld.repository.poster;

import ToyProject.blogWorld.entity.Poster.Poster;
import ToyProject.blogWorld.entity.Poster.PosterFormDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface PosterRepository extends JpaRepository<Poster, Long>,PosterRepositoryCustom, QuerydslPredicateExecutor<Poster> {
    Optional<List<Poster>> findAllByCategoryId(Long categoryId);

    @Query("SELECT new ToyProject.blogWorld.entity.Poster.PosterFormDto(p.id, p.title,p.imgPath,c.categoryName,p.contents,p.lastModifiedDate,p.viewCount,p.likeCount) " +
            "FROM Poster p JOIN p.category c")
    List<PosterFormDto> findAllPosters();


}
