package ToyProject.blogWorld.config.auth;

import ToyProject.blogWorld.domain.User;
import ToyProject.blogWorld.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * http://localhost:8080/login 일 때 동작
 */
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService 의 load 동작");
        User user = userRepository.findByLoginId(username).get();
        return new PrincipalDetails(user);
    }
}
