package ToyProject.blogWorld.repository.reply;

import ToyProject.blogWorld.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    Optional<List<Reply>>findAllByPosterIdAndParentsReplyIsNull(Long posterId);
}
