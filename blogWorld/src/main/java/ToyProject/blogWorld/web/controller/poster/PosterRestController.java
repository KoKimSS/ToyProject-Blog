package ToyProject.blogWorld.web.controller.poster;

import ToyProject.blogWorld.dto.PosterFormDto;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<PosterFormDto> allPosters = posterRepository.findAllPosters();
        return allPosters;
    }
}
