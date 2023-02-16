package product.main.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import product.main.domain.ProductVO;

import java.util.List;



public interface ProductRepository extends JpaRepository<ProductVO, String> , CustomRepository{

   /*
    @Query("select new ProductVO(a.pnum, a.pname, a.pimage, a.ppay) from ProductVO a where a.pname = :pname")
    List<ProductVO> findThis(@Param("pname") String pname);
*/

}

