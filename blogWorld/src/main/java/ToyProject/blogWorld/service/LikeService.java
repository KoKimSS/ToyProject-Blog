package ToyProject.blogWorld.service;

import ToyProject.blogWorld.entity.Like.Like;
import ToyProject.blogWorld.entity.Poster.Poster;
import ToyProject.blogWorld.entity.User.User;
import ToyProject.blogWorld.repository.like.LikeRepository;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import ToyProject.blogWorld.web.util.ControllerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static ToyProject.blogWorld.entity.Like.Like.changeState;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PosterRepository posterRepository;

    @Transactional
    public boolean likeAndReturnState(String ipAddr, Long posterId, Boolean state) {
        User user = ControllerUtil.findUserFromAuth();
        Optional<Like> optionalLike;
        //사용자가 로그인 되어있으면
        if (user != null) {
            optionalLike = likeRepository.findByPosterIdAndUserId(posterId, user.getId());
        } else {
            optionalLike = likeRepository.findByPosterIdAndIpAddrAndUserIsNull(posterId, ipAddr);
        }
        //사용자의 좋아요가 존재한다면
        if (optionalLike.isPresent()) {
            Like like = optionalLike.get();
            changeState(like, state);
            return like.isState();
        }

        //존재하지 않으면 like 생성
        Poster poster = posterRepository.findById(posterId).get();
        Like like = Like.builder()
                .ipAddr(ipAddr)
                .state(true)
                .user(user)
                .poster(poster).build();
        Poster.incrementLikeCount(poster);
        likeRepository.save(like);
        return true;
    }

}
