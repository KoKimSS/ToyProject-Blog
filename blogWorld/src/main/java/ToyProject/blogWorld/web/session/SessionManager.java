package ToyProject.blogWorld.web.session;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";

    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();//동시접속 문제

    public void createSession(Object value, HttpServletResponse response) {
        //세션 id 생성
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);
        Cookie mySessionCookie = new Cookie("SESSION_COOKIE_NAME", sessionId);
        response.addCookie(mySessionCookie);
    }

    public Object getSession(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        return findCookie(request);
    }

    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request);
        log.info("세션삭제 시작");
        if (sessionCookie != null) {
            Object remove = sessionStore.remove(sessionCookie.getValue());
            log.info("세션삭제={}", remove.getClass());
        }
    }

    private static Cookie findCookie(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
                .findAny()
                .orElse(null);
    }
}
