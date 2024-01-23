package ToyProject.blogWorld.service;

import ToyProject.blogWorld.entity.Poster.Poster;
import ToyProject.blogWorld.entity.User.User;
import ToyProject.blogWorld.repository.like.LikeRepository;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LikeServiceTest {

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private PosterRepository posterRepository;

    @InjectMocks
    private LikeService likeService;

    @Test
    public void testLikeAndReturnState() {
        // 가짜 데이터 생성
        String ipAddr = "127.0.0.1";
        Long posterId = 1L;
        Boolean state = true;
        User user = User.builder()
                .build();

        // Mock 객체에 대한 Stubbing 설정
        when(likeRepository.findByPosterIdAndUserId(posterId, user.getId()))
                .thenReturn(Optional.empty()); // 좋아요가 없는 상태로 가정

        when(likeRepository.findByPosterIdAndIpAddrAndUserIsNull(posterId, ipAddr))
                .thenReturn(Optional.empty()); // 좋아요가 없는 상태로 가정

        when(posterRepository.findById(posterId)).thenReturn(Optional.of(Poster.builder().build())); // 가짜 Poster 객체 반환

        // 테스트 대상 메서드 호출
        boolean result = likeService.likeAndReturnState(ipAddr, posterId, state);

        // 결과 검증
        // 여기서는 좋아요가 새로 생성되었으므로 반환값은 true로 예상
        assertTrue(result);

        // 좋아요 객체가 save 메서드로 저장되었는지 검증
        verify(likeRepository, times(1)).save(any());
    }
}
