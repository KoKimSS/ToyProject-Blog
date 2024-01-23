package ToyProject.blogWorld.repository.like;

import ToyProject.blogWorld.entity.Like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findByPosterIdAndIpAddrAndUserIsNull(Long posterId, String ipAddr);
    Optional<Like> findByPosterIdAndUserId(Long posterId, Long userId);


}
