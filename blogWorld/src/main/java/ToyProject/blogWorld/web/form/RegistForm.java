package ToyProject.blogWorld.web.form;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistForm {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @NotBlank
    private String userName;
    @Email
    @NotBlank
    private String userEmail;
    @NumberFormat
    private String userPhone;
}
