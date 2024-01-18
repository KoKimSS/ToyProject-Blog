package ToyProject.blogWorld.domain;

import ToyProject.blogWorld.domain.baseentity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
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
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String profileImage;
    private String roles; //"USER ADMIN"
    private String provider;
    private String providerId;
    @ColumnDefault("true")
    private boolean valid;
    @OneToMany(mappedBy = "user")
    private List<Blog> blogList = new ArrayList<>();

    public static User createNewUser(String loginId, String Pw, String UserName, String Phone, String Email) {
        User user = new User();
        user.loginId = loginId;
        user.name = UserName;
        user.password = Pw;
        user.phone = Phone;
        user.email = Email;
        user.roles = "ROLE_USER"; //임시로 지정
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
