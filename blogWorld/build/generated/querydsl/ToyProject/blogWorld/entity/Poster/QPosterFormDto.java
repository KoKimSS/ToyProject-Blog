package ToyProject.blogWorld.entity.Poster;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * ToyProject.blogWorld.entity.Poster.QPosterFormDto is a Querydsl Projection type for PosterFormDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPosterFormDto extends ConstructorExpression<PosterFormDto> {

    private static final long serialVersionUID = 171355943L;

    public QPosterFormDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> imgPath, com.querydsl.core.types.Expression<String> categoryName, com.querydsl.core.types.Expression<String> contents, com.querydsl.core.types.Expression<java.time.LocalDateTime> lastModifiedDate, com.querydsl.core.types.Expression<Integer> viewCount, com.querydsl.core.types.Expression<Integer> likeCount) {
        super(PosterFormDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class, int.class, int.class}, id, title, imgPath, categoryName, contents, lastModifiedDate, viewCount, likeCount);
    }

}

