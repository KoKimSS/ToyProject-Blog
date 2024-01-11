package ToyProject.blogWorld.repository.user;

import ToyProject.blogWorld.domain.QUser;
import ToyProject.blogWorld.domain.User;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    public void update(Long userId, UserUpdateDto userUpdateDto) {
        QUser qUser = QUser.user;
        queryFactory.update(qUser)
                .set(qUser.name, Expressions.asString(userUpdateDto.getName()))
                .set(qUser.email, Expressions.asString(userUpdateDto.getEmail()))
                .set(qUser.phone, Expressions.asString(userUpdateDto.getPhone()))
                .where(qUser.id.eq(userId))
                .execute();
    }
}
