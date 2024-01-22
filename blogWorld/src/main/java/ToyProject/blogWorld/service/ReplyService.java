package ToyProject.blogWorld.service;

import ToyProject.blogWorld.entity.Poster.Poster;
import ToyProject.blogWorld.entity.Reply.Reply;
import ToyProject.blogWorld.entity.User.User;
import ToyProject.blogWorld.repository.poster.PosterRepository;
import ToyProject.blogWorld.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
