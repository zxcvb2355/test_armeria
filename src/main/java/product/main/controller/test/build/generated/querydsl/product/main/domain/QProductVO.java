package product.main.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductVO is a Querydsl query type for ProductVO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductVO extends EntityPathBase<ProductVO> {

    private static final long serialVersionUID = -602748172L;

    public static final QProductVO productVO = new QProductVO("productVO");

    public final StringPath del_yn = createString("del_yn");

    public final StringPath pimage = createString("pimage");

    public final StringPath pname = createString("pname");

    public final NumberPath<Long> pnum = createNumber("pnum", Long.class);

    public final NumberPath<Integer> ppay = createNumber("ppay", Integer.class);

    public QProductVO(String variable) {
        super(ProductVO.class, forVariable(variable));
    }

    public QProductVO(Path<? extends ProductVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductVO(PathMetadata metadata) {
        super(ProductVO.class, metadata);
    }

}

