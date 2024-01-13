package ToyProject.blogWorld.web.util;

import ToyProject.blogWorld.config.auth.PrincipalDetails;
import ToyProject.blogWorld.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public class ControllerUtil {

    // 유저가 있으면 return user 없으면 return null
    public static User findAndAddUserToModel(Model model) {
        Authentication authentication = getAuthentication();
        if (!isValidAuthentication(authentication)) return null;
        User user = findUserFromAuth(authentication);
        return addUserToModel(model, user);
    }

    private static User addUserToModel(Model model, User user) {
        if (user == null) return null;
        model.addAttribute("user", user);
        return user;
    }

    private static User findUserFromAuth(Authentication authentication) {
        if (!isInstanceOfPrincipalDetails(authentication)) {
            return null;
        }
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        return user;
    }

    private static boolean isInstanceOfPrincipalDetails(Authentication authentication) {
        return authentication.getPrincipal() instanceof PrincipalDetails;
    }

    private static boolean isValidAuthentication(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
