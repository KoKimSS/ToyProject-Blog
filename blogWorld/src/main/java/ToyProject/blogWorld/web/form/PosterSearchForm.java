package ToyProject.blogWorld.web.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
public class PosterSearchForm {
    private String keyWord;
    private String searchType;
    private String sortType;
    private int page;
    private int size;
}
