package product.main.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import product.main.dao.ProductRepository;
import product.main.domain.ProductVO;
import product.main.domain.selectSeeData;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;


    @Override
    public List<selectSeeData> selectAll() {
        logger.info("서비스 진입");
        return productRepository.selectAll();
    }

    @Override
    public List<selectSeeData> findOne(String pname) {
        return  productRepository.findThis(pname);
    }

    @Transactional
    @Override
    public String insert(ProductVO pvo) {

        productRepository.save(pvo);


        return "등록된 데이터";
    }

    @Override
    public String update(ProductVO pvo) {

        productRepository.save(pvo);

        return "업데이트된 데이터";
    }

    @Override
    public List<selectSeeData> findIdOne(Long pnum) {
        return productRepository.findIdOne(pnum);
    }

    @Override
    public String delete(ProductVO pvo) {

        productRepository.save(pvo);
        return "삭제 완료";
    }
}
