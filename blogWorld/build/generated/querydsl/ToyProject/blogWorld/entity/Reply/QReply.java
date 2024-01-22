package ToyProject.blogWorld.entity.Reply;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReply is a Querydsl query type for Reply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReply extends EntityPathBase<Reply> {

    private static final long serialVersionUID = 1635619788L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReply reply = new QReply("reply");

    public final ToyProject.blogWorld.entity.baseEntity.QBaseTimeEntity _super = new ToyProject.blogWorld.entity.baseEntity.QBaseTimeEntity(this);

    public final ListPath<Reply, QReply> childReply = this.<Reply, QReply>createList("childReply", Reply.class, QReply.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final QReply parentsReply;

    public final ToyProject.blogWorld.entity.Poster.QPoster poster;

    public final ToyProject.blogWorld.entity.User.QUser user;

    public final BooleanPath valid = createBoolean("valid");

    public QReply(String variable) {
        this(Reply.class, forVariable(variable), INITS);
    }

    public QReply(Path<? extends Reply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReply(PathMetadata metadata, PathInits inits) {
        this(Reply.class, metadata, inits);
    }

    public QReply(Class<? extends Reply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parentsReply = inits.isInitialized("parentsReply") ? new QReply(forProperty("parentsReply"), inits.get("parentsReply")) : null;
        this.poster = inits.isInitialized("poster") ? new ToyProject.blogWorld.entity.Poster.QPoster(forProperty("poster"), inits.get("poster")) : null;
        this.user = inits.isInitialized("user") ? new ToyProject.blogWorld.entity.User.QUser(forProperty("user")) : null;
    }

}

