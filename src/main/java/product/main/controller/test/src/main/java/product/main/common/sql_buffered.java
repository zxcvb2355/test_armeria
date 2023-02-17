package product.main.common;

public class sql_buffered {

    public static String selectAll(){

        StringBuffer sb = new StringBuffer();

        sb.append("SELECT A.PNUM AS PNUM,  \n");
        sb.append("A.PNAME AS PNAME, \n");
        sb.append("A.PIMAGE AS PIMAGE, \n");
        sb.append("A.PPAY AS PPAY  \n");
        sb.append("FROM CPST_PRODUCT A \n");

        return sb.toString();
    }




}
