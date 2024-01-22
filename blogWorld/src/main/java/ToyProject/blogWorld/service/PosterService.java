package ToyProject.blogWorld.service;

import ToyProject.blogWorld.entity.Category.Category;
import ToyProject.blogWorld.entity.Poster.Poster;
import ToyProject.blogWorld.repository.category.CategoryRepository;
import ToyProject.blogWorld.repository.poster.PosterDto;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ToyProject.blogWorld.entity.Poster.Poster.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PosterService {
    private final PosterRepository posterRepository;
    private final CategoryRepository categoryRepository;
    public void updatePosterByDto(Long posterId,PosterDto posterDto) {
        Poster poster = posterRepository.findById(posterId).get();
        Category category = categoryRepository.findById(posterDto.getCategoryId()).get();
        poster.editPoster(posterDto.getTitle(),posterDto.getContents(),category);
        System.out.println("poster.getContents() = " + poster.getContents());
    }

    public Poster findForUserClick(Long posterId){
        Poster poster = posterRepository.findById(posterId).get();
        incrementViewCount(poster);
        return poster;
    }
}
