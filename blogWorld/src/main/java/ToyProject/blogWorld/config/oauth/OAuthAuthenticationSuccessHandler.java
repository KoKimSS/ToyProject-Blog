package ToyProject.blogWorld.config.oauth;

import ToyProject.blogWorld.config.auth.PrincipalDetails;
import ToyProject.blogWorld.config.jwt.JwtProperties;
import ToyProject.blogWorld.config.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

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
        String jwtToken = JwtTokenUtil.getJwtToken(oAuth2User);
        jwtToken = URLEncoder.encode(JwtProperties.TOKEN_PREFIX+jwtToken, "utf-8");
        Cookie cookie = new Cookie(JwtProperties.HEADER_STRING, jwtToken);
        cookie.setPath("/");
        cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);

        response.addCookie(cookie);

        // 응답 헤더의 쿠키에 HttpOnly로 토큰 저장
        log.info("jwt cookie = {}",jwtToken);
        log.info("redirectUrl = {}",redirectUrl);
        getRedirectStrategy().sendRedirect(request, response,redirectUrl);
    }
}
