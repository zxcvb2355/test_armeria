package product.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.server.annotation.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;

import com.linecorp.armeria.server.annotation.ExceptionHandler;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import product.main.common.html_buffer;
import product.main.config.ValidationExceptionHandler;

import product.main.domain.ProductVO;
import product.main.service.ProductService;

import java.net.URI;
import java.util.Collections;
import java.util.List;


@RestController
@Validated
@ExceptionHandler(ValidationExceptionHandler.class)
public class HelloAnnotatedService {
    private static Logger logger = LoggerFactory.getLogger(HelloAnnotatedService.class);
    @Autowired(required = false)
    private ProductService productService;


    @Get("/product")
    public HttpResponse selectAll() throws JsonProcessingException {



        logger.info("product 진입");
        List<ProductVO> list = productService.selectAll();
        logger.info("list : " + list.size());

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(list);
        logger.info( "data : " + jsonStr);

        JSONObject json = null;
        JSONArray json_data = null;
        JSONObject pnum = null;
        JSONObject pname = null;
        JSONObject pimage = null;
        JSONObject ppay = null;
        JSONArray numData = null;
        JSONArray nameData = null;
        JSONArray imageData = null;
        JSONArray payData = null;

        numData = new JSONArray();
        nameData = new JSONArray();
        imageData = new JSONArray();
        payData = new JSONArray();

        for(int i=0; i < list.size(); i++){
            numData.add(list.get(i).getPnum());
            nameData.add(list.get(i).getPname());
            imageData.add(list.get(i).getPimage());
            payData.add(list.get(i).getPpay());

        }
        pnum = new JSONObject();
        pnum.put("pnum", numData);

        pname = new JSONObject();
        pname.put("pname", nameData);

        pimage = new JSONObject();
        pimage.put("pimage", imageData);

        ppay = new JSONObject();
        ppay.put("ppay", payData);

        json_data = new JSONArray();
        json_data.add(pnum);
        json_data.add(pname);
        json_data.add(pimage);
        json_data.add(ppay);

        json = new JSONObject();
        json.put("product", json_data);


        return HttpResponse.of(HttpStatus.OK, MediaType.HTML_UTF_8, html_buffer.selectAll(), jsonStr, json.toJSONString());
    }

    @Get("/productsFind")
    public HttpResponse FindOne() throws JsonProcessingException {
        List<ProductVO> list = productService.selectAll();
        logger.info("list : " + list.size());
        String[] dataPname = null;
        JSONObject json = new JSONObject();
        JSONArray jar = new JSONArray();
        for(int i=0; i < list.size(); i++){
            jar.add(list.get(i).getPname());

        }
        json.put("pname", jar);


        String productFind = html_buffer.findOne();

        return HttpResponse.of(HttpStatus.OK, MediaType.HTML_UTF_8, productFind + json.toJSONString());
    }

    @Get("/product/{name}")
    public HttpResponse products(@Param("name") String name) throws JsonProcessingException {


        logger.info("pname : " + name);
        List<ProductVO> list  = productService.findOne(name);
        logger.info("list : " + list.size());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(list);


        return HttpResponse.of(HttpStatus.OK, MediaType.HTML_UTF_8, jsonStr);
    }

    @Get("/seeData")
    public HttpResponse hello() {

        String seeDataHtml = html_buffer.seeDataHtml();

        return HttpResponse.of(HttpStatus.OK, MediaType.HTML_UTF_8, seeDataHtml);
    }
}