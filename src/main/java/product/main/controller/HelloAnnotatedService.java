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
import com.linecorp.armeria.server.annotation.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import product.main.common.html_buffer;
import product.main.config.ValidationExceptionHandler;

import product.main.domain.ProductVO;
import product.main.domain.selectSeeData;
import product.main.service.ProductService;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;


@RestController
@Validated
@ExceptionHandler(ValidationExceptionHandler.class)
@RequiredArgsConstructor
public class HelloAnnotatedService {
    private static Logger logger = LoggerFactory.getLogger(HelloAnnotatedService.class);

    private final ProductService productService;


    @Get("/product")
    public Object selectAll() throws JsonProcessingException {



        logger.info("product 진입");
        List<selectSeeData> list = productService.selectAll();
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


        return HttpResponse.of(HttpStatus.OK, MediaType.JSON,jsonStr  + "\n" + "\n" +   json.toJSONString());
    }

    @Get("/productsFind")
    public HttpResponse FindOne() throws JsonProcessingException {
        List<selectSeeData> list = productService.selectAll();
        logger.info("list : " + list.size());
        String[] dataPname = null;
        JSONObject json = new JSONObject();
        JSONArray jar = new JSONArray();
        for(int i=0; i < list.size(); i++){
            jar.add(list.get(i).getPname());

        }
        json.put("pname", jar);


        String productFind = html_buffer.findOne();

        return HttpResponse.of(HttpStatus.OK, MediaType.JSON, json.toJSONString());
    }

    @Get("/product/{name}")
    public HttpResponse products(@Param("name") String name) throws JsonProcessingException {


        logger.info("pname : " + name);
        List<selectSeeData> list  = productService.findOne(name);
        logger.info("list : " + list.size());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(list);


        return HttpResponse.of(HttpStatus.OK, MediaType.JSON, jsonStr);
    }

    @Post("/Data")
        public Object Data(@RequestBody JSONObject data) throws JsonProcessingException {

        ProductVO pvo = null;
        pvo = new ProductVO();

        pvo.setPname((String)data.get("pname"));
        String pname = pvo.getPname();
        logger.info("pname : " + pname);
        pvo.setPimage((String)data.get("pimage"));
        pvo.setPpay((Integer)data.get("ppay"));
        pvo.setDel_yn("Y");

        String value = productService.insert(pvo);

        List<selectSeeData> list  = productService.findOne(pname);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(list);

        return HttpResponse.of(HttpStatus.OK, MediaType.PLAIN_TEXT_UTF_8, value + "\n" + jsonStr);
    }

    @Put("/reData")
    public Object reData(@RequestBody JSONObject  reData) throws JsonProcessingException {

       ProductVO pvo = null;
        pvo = new ProductVO();
        pvo.setPnum(Long.parseLong(String.valueOf(reData.get("pnum"))));
        Long pnum = pvo.getPnum();
        logger.info("pnum : " + pnum);
        pvo.setPname((String)reData.get("pname"));
        pvo.setPimage((String)reData.get("pimage"));
        pvo.setPpay((Integer)reData.get("ppay"));
        pvo.setDel_yn("Y");

        String value = productService.update(pvo);

        List<selectSeeData> list = productService.findIdOne(pnum);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(list);


        return HttpResponse.of(HttpStatus.OK, MediaType.PLAIN_TEXT_UTF_8, value + "\n" + jsonStr);
    }

   @Put("/cleanData")
   public Object cleanData(@RequestBody JSONObject  cData) throws JsonProcessingException {

        ProductVO pvo = null;
       pvo = new ProductVO();
       Long pnum = Long.parseLong(String.valueOf(cData.get("pnum")));

       List<selectSeeData> list = productService.findIdOne(pnum);

       if(list.size() == 0){
           return "해당 데이터 없음";
       }

       pvo.setPnum(pnum);
       pvo.setPname(list.get(0).getPname());
       pvo.setPimage(list.get(0).getPimage());
       pvo.setPpay(list.get(0).getPpay());
       pvo.setDel_yn("N");

       String value = productService.delete(pvo);

       
        return HttpResponse.of(HttpStatus.OK, MediaType.PLAIN_TEXT_UTF_8, value);
   }

}