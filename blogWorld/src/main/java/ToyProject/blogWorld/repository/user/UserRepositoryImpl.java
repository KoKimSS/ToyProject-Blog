package ToyProject.blogWorld.repository.user;

import ToyProject.blogWorld.domain.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static ToyProject.blogWorld.domain.QUser.*;

public class UserRepositoryImpl implements UserRepositoryCustom{

private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public void update(Long userId, UserUpdateDto userUpdateDto) {
       // queryFactory.update(user)
     //           .set()
    }
}
