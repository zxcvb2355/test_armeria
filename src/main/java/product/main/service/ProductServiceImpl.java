package product.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.main.dao.ProductRepository;
import product.main.domain.ProductVO;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired(required = false)
    private ProductRepository productRepository;


    @Override
    public List<ProductVO> selectAll() {
        System.out.println("서비스 진입");
        return productRepository.findAll();
    }

    @Override
    public List<ProductVO> findOne(String pname) {
        return  productRepository.findThis(pname);
    }
}
