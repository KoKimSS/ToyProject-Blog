package ToyProject.blogWorld.domain;

import ToyProject.blogWorld.domain.baseentity.BaseEntity;
import ToyProject.blogWorld.domain.baseentity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString(of = {"id","username","name",})
public class User extends BaseEntity {
    @Id@GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String profileImage;
    @ColumnDefault("true")
    private boolean valid;
    @OneToMany(mappedBy = "user")
    private List<Blog> blogList = new ArrayList<>();

    public static User createNewUser(String UserName, String Id, String Pw, String Phone, String Email) {
        User user = new User();
        user.name=UserName;
        user.loginId=Id;
        user.password=Pw;
        user.phone=Phone;
        user.email = Email;
        return user;
    }
}
