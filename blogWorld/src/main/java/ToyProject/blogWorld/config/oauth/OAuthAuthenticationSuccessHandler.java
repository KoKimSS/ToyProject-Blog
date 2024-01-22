package ToyProject.blogWorld.config.oauth;

import ToyProject.blogWorld.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ToyProject.blogWorld.config.jwt.JwtTokenUtil.getJwtToken;
import static ToyProject.blogWorld.config.jwt.JwtTokenUtil.setTokenToCookie;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Token 발행");
        String redirectUrl = "http://localhost:8080";
        PrincipalDetails oAuth2User = (PrincipalDetails) authentication.getPrincipal();
        // jwt token 발행
        String jwtToken = getJwtToken(oAuth2User);
        setTokenToCookie(response, jwtToken);

        // 응답 헤더의 쿠키에 HttpOnly로 토큰 저장
        log.info("jwt cookie = {}", jwtToken);
        log.info("redirectUrl = {}", redirectUrl);
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}
