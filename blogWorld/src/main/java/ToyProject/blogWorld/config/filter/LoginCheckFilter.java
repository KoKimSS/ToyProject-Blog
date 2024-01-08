package ToyProject.blogWorld.config.filter;

import ToyProject.blogWorld.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/register", "/login",
            "/logout","/css/*","/findposter"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest= (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse)  response;

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);
            if (!PatternMatchUtils.simpleMatch(whitelist, requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청{}", requestURI);
                    //로그인으로 redirect 후 로그인 후 다시 원하는 페이지로 이동
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }finally {
            log.info("인증 체크 필터 종료 {}",requestURI);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
