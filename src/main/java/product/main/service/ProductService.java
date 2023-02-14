package product.main.service;



import product.main.domain.ProductVO;

import java.util.List;

public interface ProductService {

    public List<ProductVO> selectAll();
    public List<ProductVO> findOne(String pname);

}
