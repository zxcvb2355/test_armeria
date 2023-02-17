package product.main.dao;

import product.main.domain.ProductVO;
import product.main.domain.selectSeeData;

import java.util.List;

public interface CustomRepository {
    List<selectSeeData> findThis(String pname);
    List<selectSeeData> selectAll();

    List<selectSeeData> findIdOne(Long pnum);

}
