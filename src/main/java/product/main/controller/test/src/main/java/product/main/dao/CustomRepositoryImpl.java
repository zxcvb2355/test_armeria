package product.main.dao;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import product.main.domain.ProductVO;
import product.main.domain.selectSeeData;

import java.util.List;
import static product.main.domain.QProductVO.productVO;

@Repository
@RequiredArgsConstructor
public class CustomRepositoryImpl implements CustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<selectSeeData> findThis(String pname) {
        return jpaQueryFactory.select(
                                                            Projections.constructor(selectSeeData.class, productVO.pnum,
                                                                                                                                       productVO.pname,
                                                                                                                                       productVO.pimage,
                                                                                                                                       productVO.ppay))
                                               .from(productVO)
                                               .where(productVO.pname.eq(pname), productVO.del_yn.eq("Y"))
                                               .fetch();
    }

    @Override
    public List<selectSeeData> selectAll() {
        return jpaQueryFactory.select(
                                                            Projections.constructor(selectSeeData.class, productVO.pnum,
                                                                                                                                       productVO.pname,
                                                                                                                                       productVO.pimage,
                                                                                                                                       productVO.ppay))
                                              .from(productVO)
                                              .where(productVO.del_yn.eq("Y"))
                                              .fetch();
    }

    @Override
    public List<selectSeeData> findIdOne(Long pnum) {
        return jpaQueryFactory.select(
                                                            Projections.constructor(selectSeeData.class, productVO.pnum,
                                                                                                                                       productVO.pname,
                                                                                                                                       productVO.pimage,
                                                                                                                                       productVO.ppay))
                                                .from(productVO)
                                                .where(productVO.pnum.eq(pnum), productVO.del_yn.eq("Y"))
                                                .fetch();
    }
}
