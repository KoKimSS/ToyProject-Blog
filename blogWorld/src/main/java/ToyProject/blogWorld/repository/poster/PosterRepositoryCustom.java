package ToyProject.blogWorld.repository.poster;

import ToyProject.blogWorld.entity.Poster.PosterSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PosterRepositoryCustom {
    Page<PosterSearchDto> searchPage(String keyword, PosterSearchType searchType, PosterSortType sortingType, Pageable pageable);

}
