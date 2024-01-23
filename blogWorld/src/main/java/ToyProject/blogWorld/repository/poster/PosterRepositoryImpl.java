package ToyProject.blogWorld.repository.poster;

import ToyProject.blogWorld.entity.Poster.*;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.io.Serializable;
import java.util.List;

import static ToyProject.blogWorld.entity.Poster.QPoster.*;

@RequiredArgsConstructor
public class PosterRepositoryImpl implements PosterRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PosterSearchDto> searchPage(String keyword, PosterSearchType searchType, PosterSortType sortingType, Pageable pageable) {
        List<PosterSearchDto> results = queryFactory.select(new QPosterSearchDto(
                        poster.id.as("id"),
                        poster.title.as("title"),
                        poster.imgPath.as("imgPath"),
                        poster.contents.as("contents"),
                        poster.lastModifiedDate.as("lastModifiedDate"),
                        poster.viewCount.as("viewCount"),
                        poster.likeCount.as("likeCount")
                )).from(poster)
                .where(search(keyword, searchType))
                .orderBy(getOrderExpression(poster, sortingType))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Poster> countQuery = queryFactory
                .select(poster)
                .from(poster)
                .where(search(keyword, searchType));

        long total = countQuery.fetchCount();

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchCount);

    }

    private BooleanExpression search(String keyword, PosterSearchType searchType) {
        QPoster qPoster = poster;
        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }

        switch (searchType) {
            case TITLE:
                return qPoster.title.containsIgnoreCase(keyword);
            case CONTENTS:
                return qPoster.contents.containsIgnoreCase(keyword);
            case MIX:
                return qPoster.title.containsIgnoreCase(keyword)
                        .or(qPoster.contents.containsIgnoreCase(keyword));
            default:
                throw new IllegalArgumentException("Invalid search type");
        }
    }
    private OrderSpecifier<? extends Serializable> getOrderExpression(QPoster qPoster, PosterSortType sortingType) {
        switch (sortingType) {
            case ACCURACY:
                return qPoster.title.asc().nullsFirst();
            case LATEST:
                return qPoster.lastModifiedDate.desc().nullsLast();
            case POPULAR:
                return qPoster.viewCount.desc().nullsFirst();
            default:
                throw new IllegalArgumentException("Invalid sorting type");
        }
    }
}
