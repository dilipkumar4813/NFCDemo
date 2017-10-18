package iamdilipkumar.com.androidsecurity.utils;

import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created on 18/10/17.
 *
 * @author dilipkumar4813
 */

public class CustomBase64 {

    private static String generateJwtHeader() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("typ", "JWT");
            jsonObject.put("alg", "HS256");
            jsonObject.put("expires_in", 3600);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String header = jsonObject.toString();
        byte[] data = new byte[0];
        try {
            data = header.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    private static String generateJwtClaim() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id", "dilip id");
            jsonObject.put("access_token", "test1234564sdgd34532fgh13423");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String header = jsonObject.toString();
        byte[] data = new byte[0];
        try {
            data = header.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    public static String generateJwtToken() {
        String header = generateJwtHeader();
        String claim = generateJwtClaim();
        String s = header + "." + claim;

        String signature = Base64.encodeToString(s.getBytes(), Base64.DEFAULT);

        Log.d("stay",s);

        return s;
    }
}
