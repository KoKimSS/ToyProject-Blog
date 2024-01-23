package ToyProject.blogWorld.web.controller.poster;

import ToyProject.blogWorld.entity.Poster.PosterFormDto;
import ToyProject.blogWorld.entity.Poster.PosterSearchDto;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import ToyProject.blogWorld.repository.poster.PosterSearchType;
import ToyProject.blogWorld.repository.poster.PosterSortType;
import ToyProject.blogWorld.web.form.PosterSearchForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/poster")
@RequiredArgsConstructor
public class PosterRestController {
    private final PosterRepository posterRepository;

    @GetMapping("/all")
    List<PosterFormDto> getAllPoster() {
        return posterRepository.findAllPosters();
    }

    @GetMapping("/search")
    Page<PosterSearchDto> searchPostersByCondition(@RequestBody PosterSearchForm posterSearchForm) {
        return posterRepository.searchPage(posterSearchForm.getKeyWord(),
                PosterSearchType.toType(posterSearchForm.getSearchType()),
                PosterSortType.toType(posterSearchForm.getSortType()),
                PageRequest.of(posterSearchForm.getPage(), posterSearchForm.getSize()));
    }

}
