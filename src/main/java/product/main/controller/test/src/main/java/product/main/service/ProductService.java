package product.main.service;



import product.main.domain.ProductVO;
import product.main.domain.selectSeeData;

import java.util.List;

public interface ProductService {

    public List<selectSeeData> selectAll();
    public List<selectSeeData> findOne(String pname);

    public String insert(ProductVO pvo);

    public String update(ProductVO pvo);

    public List<selectSeeData> findIdOne(Long pnum);

    public String delete(ProductVO pvo);

}
