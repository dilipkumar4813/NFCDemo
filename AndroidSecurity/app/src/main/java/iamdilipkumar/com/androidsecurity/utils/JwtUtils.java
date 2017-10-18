package iamdilipkumar.com.androidsecurity.utils;

import android.util.Log;

import java.util.HashMap;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created on 18/10/17.
 *
 * @author dilipkumar4813
 */

public class JwtUtils {

    public static String generateJJwt() {
        // Key key = MacProvider.generateKey();

        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("expires_in", 3600);
        claims.put("access_token", "test");
        claims.put("user_id", "user checking id");

        String compactJwt = Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "dilipsecretkey")
                .compact();


        Log.d("JWT: ", compactJwt);

        return compactJwt;
    }
}
