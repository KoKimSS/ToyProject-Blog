package ToyProject.blogWorld.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCookieDTO {
    private String username;
    private String session_id;
}
