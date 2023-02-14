package product.main.common;

public class html_buffer {

    public static String seeDataHtml(){

        StringBuffer sb = new StringBuffer();
        sb.append( "<h3>안녕하십니까, test 서버에 오신 것을 환영합니다. </h3><br>");
        sb.append("test서버에서 가지고있는 화장품 정보에 대한 api를 제공하고 있습니다.<br>");
        sb.append("<a href='productsFind'>원하는 data만 보실꺼면 여기를 클릭</a><br>");
        sb.append("<a href='/product'>전체 데이터를 보실꺼면 여기를 클릭</a><br>");
        sb.append("해주세요!<br>");
        sb.append("모든 data의 형태는 JSON 입니다");

        sb.toString();


        return sb.toString();
    }

    public static String findOne(){
        
    StringBuffer sb = new StringBuffer();
    sb.append("상품 이름으로 Data를 찾습니다. 해당 상품의 이름을 입력해주세요<br>");
    sb.append("하위 이름 중 원하는 이름을 주소에 입력해주세요<br>");
    sb.append("형식  : /product/상품명<br>");
    sb.append("리턴 되는 data의 형태는 JSON 입니다.<br><br>");

    return sb.toString();
    }

    public static String selectAll(){
        StringBuffer sb = new StringBuffer();
        sb.append("<h2>물품별 데이터 보기</h2>");
        sb.append("%s");
        sb.append("<h2>항목별 데이터 보기</h2>");
        sb.append("%s");


        return sb.toString();
    }

}
