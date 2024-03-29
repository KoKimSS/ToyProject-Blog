package ToyProject.blogWorld.config.jwt;

import ToyProject.blogWorld.config.auth.PrincipalDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@Slf4j
public class JwtTokenUtil {
     public static String getJwtToken(PrincipalDetails principalDetailis) {
        return JWT.create()
                .withSubject(principalDetailis.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetailis.getUser().getId())
                .withClaim("loginId", principalDetailis.getUser().getLoginId())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }
}
