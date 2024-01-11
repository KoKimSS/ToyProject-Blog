package ToyProject.blogWorld.domain;

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

    private static final long serialVersionUID = -1543985018L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPoster poster = new QPoster("poster");

    public final ToyProject.blogWorld.domain.baseentity.QBaseTimeEntity _super = new ToyProject.blogWorld.domain.baseentity.QBaseTimeEntity(this);

    public final QCategory category;

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<Tag, QTag> tagList = this.<Tag, QTag>createList("tagList", Tag.class, QTag.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final StringPath titlePic = createString("titlePic");

    public final BooleanPath valid = createBoolean("valid");

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
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category"), inits.get("category")) : null;
    }

}

