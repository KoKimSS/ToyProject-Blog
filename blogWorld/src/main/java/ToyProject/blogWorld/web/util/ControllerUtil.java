package ToyProject.blogWorld.web.util;

import ToyProject.blogWorld.config.auth.PrincipalDetails;
import ToyProject.blogWorld.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public class ControllerUtil {

    // 유저가 있으면 return user 없으면 return null
    public static User addUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Authentication이 PrincipalDetails 타입인 경우에만 형변환
            if (authentication.getPrincipal() instanceof PrincipalDetails) {
                PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
                User user = principal.getUser();
                // Model에 사용자 정보 추가
                model.addAttribute("user", user);
                return user;
            }
        }
        return null;
    }

    public static User getUserFromAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Authentication이 PrincipalDetails 타입인 경우에만 형변환
            if (authentication.getPrincipal() instanceof PrincipalDetails) {
                PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
                User user = principal.getUser();
                return user;
            }
        }
        return null;
    }
}
