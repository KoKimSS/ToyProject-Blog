package ToyProject.blogWorld.repository.poster;

import ToyProject.blogWorld.entity.Poster.Poster;
import ToyProject.blogWorld.entity.Poster.PosterSearchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Transactional
public class PosterRepositoryImplTest {
    @Autowired
    private PosterRepository posterRepository;

    @Test
    public void searchPageTest() {
        // Given
        int pageSize = 10;
        int pageNum = 1;
        ArrayList<Poster> posters = new ArrayList<>();
        for (int i = 0; i < pageSize * 3; i++) {
            String title ,contents;
            int viewCount,likeCount;
            if((i%pageSize)%2==1){
                title = i/pageSize+" title movie "+i%pageSize; // ex) {pageNum} movie {indexInPage}
                contents = i/pageSize+" contents this movie is " + i%pageSize; //// ex) {pageNum} this movie is {indexInPage}
            }else{
                title = i/pageSize+" title concert "+i%pageSize; // ex) {pageNum} movie {indexInPage}
                contents = i/pageSize+" contents this concert is " + i%pageSize; //// ex) {pageNum} this movie is {indexInPage}
            }
            viewCount = i%pageSize;
            likeCount = pageSize - viewCount;
            Poster poster = Poster.builder().title(title)
                    .contents(contents)
                    .likeCount(likeCount)
                    .viewCount(viewCount).build();
            System.out.println("toString" + poster.getTitle()+ poster.getContents());
            posters.add(poster);
        }
        posterRepository.saveAll(posters);


        // When
        Pageable pageable = PageRequest.of(0, 10); // Adjust the values as needed
//
        Page<PosterSearchDto> resultAccuracy = posterRepository.searchPage("movie", PosterSearchType.TITLE, PosterSortType.ACCURACY, pageable);

        System.out.println("Total Elements: " + resultAccuracy.getTotalElements());
        System.out.println("Total Pages: " + resultAccuracy.getTotalPages());
        System.out.println("Page Number: " + resultAccuracy.getNumber());
        System.out.println("Page Size: " + resultAccuracy.getSize());

        List<PosterSearchDto> content = resultAccuracy.getContent();
        System.out.println("Content:");
        for (PosterSearchDto dto : content) {
            System.out.println("Id: " + dto.getId());
            System.out.println("Title: " + dto.getTitle());
            System.out.println("Image Path: " + dto.getImgPath());
            System.out.println("Contents: " + dto.getContents());
            System.out.println("Last Modified Date: " + dto.getLastModifiedDate());
            System.out.println("View Count: " + dto.getViewCount());
            System.out.println("Like Count: " + dto.getLikeCount());
            System.out.println("--------------");
        }







        // Add more accuracy sorting assertions based on your expected results
//
//        // Test for latest sorting
//        Page<PosterSearchDto> resultLatest = posterRepository.searchPage("movie", PosterSearchType.TITLE, PosterSortType.LATEST, pageable);
//        assertThat(resultLatest.getTotalElements()).isEqualTo(3);
//        assertThat(resultLatest.getContent()).hasSize(3);
//        // Add more latest sorting assertions based on your expected results
//
//        // Test for popularity sorting
//        Page<PosterSearchDto> resultPopularity = posterRepository.searchPage("movie", PosterSearchType.TITLE, PosterSortType.POPULAR, pageable);
//        assertThat(resultPopularity.getTotalElements()).isEqualTo(3);
//        assertThat(resultPopularity.getContent()).hasSize(3);
//        // Add more popularity sorting assertions based on your expected results
    }
}