package ToyProject.blogWorld.entity.User;

import ToyProject.blogWorld.entity.Blog.Blog;
import ToyProject.blogWorld.entity.baseEntity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;


@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString(of = {"id", "name"})
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    @NotBlank(message = "로그인 아이디는 필수 값 입니다")
    @Column(nullable = false,unique = true)
    private String loginId;
    @NotBlank(message = "비밀번호는 필수 값 입니다")
    @Column(nullable = false)
    private String password;
    @NotBlank(message = "이름은 필수 값 입니다")
    @Column(nullable = false)
    private String name;
    private String phone;
    @Email
    private String email;
    private String profileImage;
    private String roles; //"USER ADMIN"
    private String provider;
    private String providerId;
    @ColumnDefault("true")
    private boolean valid;
    @OneToMany(mappedBy = "user")
    private List<Blog> blogList = new ArrayList<>();

    @Builder
    public User(String loginId, String pw, String name, String phone, String email) {
        this.loginId = loginId;
        this.password = pw;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public static User createNewUser(String loginId, String pw, String name, String phone, String email) {
        User user = new User();
        user.loginId = loginId;
        user.password = pw;
        user.name = name;
        user.email = email;
        user.roles = "ROLE_USER";
        user.phone = phone;
        return user;
    }

    public static User createOauthUser(String OAuthId, String username, String password, String email, String provider, String providerId) {
        User user = new User();
        user.loginId = OAuthId;
        user.name = username;
        user.password = password;
        user.email = email;
        user.roles = "ROLE_USER";
        user.provider = provider;
        user.providerId = providerId;
        return user;
    }

    public List<String> getRoleList() {
        if (hasRole()) {
            return rolesToRoleList();
        }
        return new ArrayList<>();
    }

    private List<String> rolesToRoleList() {
        return Arrays.asList(this.roles.split(" "));
    }

    private boolean hasRole() {
        return !this.roles.equals(null);
    }
}
