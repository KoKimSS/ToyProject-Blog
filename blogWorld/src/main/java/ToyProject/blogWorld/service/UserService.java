package ToyProject.blogWorld.service;

import ToyProject.blogWorld.domain.User;
import ToyProject.blogWorld.repository.user.UserRepository;
import ToyProject.blogWorld.repository.user.UserRepositoryImpl;
import ToyProject.blogWorld.repository.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRepositoryImpl customUserRepository;

    public User createUser(User user) {
        User newuser = userRepository.save(user);
        return newuser;
    }

    public Optional<User> login(String userUid, String userUpw) {
        Optional<User> loginUser = userRepository.findByLoginIdAndPassword(userUid, userUpw);
        return loginUser;
    }

    public void updateUser(User user, UserUpdateDto userUpdateDto) {
        customUserRepository.update(user.getId(),userUpdateDto);
    }
}
