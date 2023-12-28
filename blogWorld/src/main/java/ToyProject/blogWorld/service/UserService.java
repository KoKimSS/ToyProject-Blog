package ToyProject.blogWorld.service;

import ToyProject.blogWorld.domain.User;
import ToyProject.blogWorld.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        User newuser = userRepository.save(user);
        return newuser;
    }

    public Optional<User> login(String userUid, String userUpw) {
        Optional<User> loginUser = userRepository.findByLoginIdAndPassword(userUid, userUpw);
        return loginUser;
    }
}
