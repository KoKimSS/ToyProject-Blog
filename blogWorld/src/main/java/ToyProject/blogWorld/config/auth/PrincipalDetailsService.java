package ToyProject.blogWorld.config.auth;

import ToyProject.blogWorld.domain.User;
import ToyProject.blogWorld.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * http://localhost:8080/login 일 때 동작
 */
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService 의 load 동작");
        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
//        System.out.println(optionalUser.get());

        return new PrincipalDetails(optionalUser.isPresent()?optionalUser.get():null);
    }
}
