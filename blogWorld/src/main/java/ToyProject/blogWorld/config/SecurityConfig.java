package ToyProject.blogWorld.config;

import ToyProject.blogWorld.config.jwt.JwtAuthenticationFilter;
import ToyProject.blogWorld.config.jwt.JwtAuthorizationFilter;
import ToyProject.blogWorld.config.oauth.OAuthAuthenticationSuccessHandler;
import ToyProject.blogWorld.config.oauth.PrincipalOauth2UserService;
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

@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
@Configuration
public class SecurityConfig {
    private static final String[] userFilter = {"/user/**"};
    private static final String[] managerFilter = {"/manager/**"};
    private static final String[] adminFilter = {"/admin/**"};
    private final PrincipalOauth2UserService principalOauth2UserService;
    private final UserRepository userRepository;
    private final CorsConfig corsConfig;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new MyCustomDsl())
                .and()
                .authorizeRequests(a -> {
                    try {
                        a
                                .antMatchers(userFilter).access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                                .antMatchers(managerFilter).access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                                .antMatchers(adminFilter).access("hasRole('ROLE_ADMIN')")
                                .anyRequest().permitAll();
//                                .and()
//                                .formLogin(form -> form
//                                        .loginPage("/login")
//                                        .loginProcessingUrl("/login")
//                                        .defaultSuccessUrl("/"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        http.oauth2Login(oauth2Configurer -> oauth2Configurer
                .loginPage("/login")
                .userInfoEndpoint(userInfoEndpointConfig -> {
                    userInfoEndpointConfig.userService(principalOauth2UserService);
                })
                .successHandler(new OAuthAuthenticationSuccessHandler()));
        return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");
            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(jwtAuthenticationFilter)
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }

}
