import com.fasterxml.jackson.core.JsonProcessingException;
import com.linecorp.armeria.server.annotation.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootTest
public class TestJson {
    private static Logger logger = LoggerFactory.getLogger(TestJson.class);

    @Test
    public Object Data(@RequestBody JSONObject data) throws JsonProcessingException, ParseException {


        //다건


        String json = data.toJSONString();

        JSONArray jar = new JSONArray();
        JSONParser ps = new JSONParser();
        Object obj = ps.parse(json);

        jar = (JSONArray) obj;
        String[] pname = null;

        if (jar.size() > 0) {
            for (int i = 0; i < jar.size(); i++) {
                JSONObject job = (JSONObject) jar.get(i);

                logger.info((String) job.get("pname"));
                pname = (String[]) job.get("pname");
                logger.info("data" + i + " : " + pname[i]);
            }
        }

        return "#";
    }

}
