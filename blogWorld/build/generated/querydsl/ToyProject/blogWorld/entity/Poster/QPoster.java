package ToyProject.blogWorld.entity.Poster;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPoster is a Querydsl query type for Poster
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoster extends EntityPathBase<Poster> {

    private static final long serialVersionUID = 931270836L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPoster poster = new QPoster("poster");

    public final ToyProject.blogWorld.entity.baseEntity.QBaseTimeEntity _super = new ToyProject.blogWorld.entity.baseEntity.QBaseTimeEntity(this);

    public final ToyProject.blogWorld.entity.Category.QCategory category;

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgPath = createString("imgPath");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final ListPath<ToyProject.blogWorld.entity.Reply.Reply, ToyProject.blogWorld.entity.Reply.QReply> replyList = this.<ToyProject.blogWorld.entity.Reply.Reply, ToyProject.blogWorld.entity.Reply.QReply>createList("replyList", ToyProject.blogWorld.entity.Reply.Reply.class, ToyProject.blogWorld.entity.Reply.QReply.class, PathInits.DIRECT2);

    public final ListPath<ToyProject.blogWorld.entity.Tag.Tag, ToyProject.blogWorld.entity.Tag.QTag> tagList = this.<ToyProject.blogWorld.entity.Tag.Tag, ToyProject.blogWorld.entity.Tag.QTag>createList("tagList", ToyProject.blogWorld.entity.Tag.Tag.class, ToyProject.blogWorld.entity.Tag.QTag.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final BooleanPath valid = createBoolean("valid");

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QPoster(String variable) {
        this(Poster.class, forVariable(variable), INITS);
    }

    public QPoster(Path<? extends Poster> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPoster(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPoster(PathMetadata metadata, PathInits inits) {
        this(Poster.class, metadata, inits);
    }

    public QPoster(Class<? extends Poster> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new ToyProject.blogWorld.entity.Category.QCategory(forProperty("category"), inits.get("category")) : null;
    }

}

