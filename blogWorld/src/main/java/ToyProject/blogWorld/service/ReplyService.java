package ToyProject.blogWorld.service;

import ToyProject.blogWorld.domain.Poster;
import ToyProject.blogWorld.domain.Reply;
import ToyProject.blogWorld.domain.User;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import ToyProject.blogWorld.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PosterRepository posterRepository;

    public void createReply(String contents,User user,Long posterId,Long parentsReplyId){
        Poster poster = posterRepository.findById(posterId).get();
        Reply parentsReply =
                parentsReplyId!=null?
                        replyRepository.findById(parentsReplyId).orElse(null) : null;
        replyRepository.save(Reply.createReply(contents,poster,user,parentsReply));
    }
}
