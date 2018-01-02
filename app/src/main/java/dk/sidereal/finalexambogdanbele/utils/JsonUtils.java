package dk.sidereal.finalexambogdanbele.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Bogdan on 12/27/2017.
 */

public class JsonUtils {

    public static String toJson(Product product){
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("name", product.getName());
            jsonObj.put("expiration_date", product.getExpirationDate() );
            return jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;


    }


}
