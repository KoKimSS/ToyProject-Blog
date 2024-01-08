package ToyProject.blogWorld.config;

import ToyProject.blogWorld.config.jwt.JwtAuthenticationFilter;
import ToyProject.blogWorld.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
@Configuration
public class SecurityConfig {
    private final UserRepository userRepository;
    private final CorsConfig corsConfig;

    private static final String[] userFilter = {};
    private static final String[] managerFilter = {};
    private static final String[] adminFilter = {};
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests(a->a.antMatchers(userFilter).access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                        .antMatchers(managerFilter).access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                        .antMatchers(adminFilter).access("hasRole('ROLE_ADMIN')")
                        .anyRequest().permitAll());
        return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAuthenticationFilter(authenticationManager));
//                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }

}
