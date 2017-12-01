import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.JsonArray;
import java.io.IOException;

public class ParseToJson {
     final static JSONArray getJSonArrayFromEntityString(String retSrc, String key) throws IOException {
        JSONObject result = new JSONObject(retSrc);
        JSONArray tokenList = result.getJSONArray(key);
        return tokenList;
    }

    final static JSONArray getJsonArrayFromJsonObject(JSONObject jsonInput, String key) throws IOException {
        JSONArray tokenList = jsonInput.getJSONArray(key);
        return tokenList;
    }

    final static String getValueFromString(String retScr, String key) throws IOException{
        JSONObject obj = new JSONObject(retScr);

        String myValue = obj.getString(key);
        return myValue;
    }
}
