package ToyProject.blogWorld.entity.Blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlog is a Querydsl query type for Blog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlog extends EntityPathBase<Blog> {

    private static final long serialVersionUID = 682849556L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlog blog = new QBlog("blog");

    public final ListPath<ToyProject.blogWorld.entity.Category.Category, ToyProject.blogWorld.entity.Category.QCategory> categoryList = this.<ToyProject.blogWorld.entity.Category.Category, ToyProject.blogWorld.entity.Category.QCategory>createList("categoryList", ToyProject.blogWorld.entity.Category.Category.class, ToyProject.blogWorld.entity.Category.QCategory.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgPath = createString("imgPath");

    public final StringPath name = createString("name");

    public final ToyProject.blogWorld.entity.User.QUser user;

    public QBlog(String variable) {
        this(Blog.class, forVariable(variable), INITS);
    }

    public QBlog(Path<? extends Blog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlog(PathMetadata metadata, PathInits inits) {
        this(Blog.class, metadata, inits);
    }

    public QBlog(Class<? extends Blog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new ToyProject.blogWorld.entity.User.QUser(forProperty("user")) : null;
    }

}

