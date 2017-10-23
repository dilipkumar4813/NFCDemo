package iamdilipkumar.com.androidsecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import iamdilipkumar.com.androidsecurity.utils.JwtUtils;
import iamdilipkumar.com.androidsecurity.utils.RsaUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JwtUtils.generateJJwt();

        byte[] rsaEncryption = RsaUtils.encrypt("Dilip");
        Log.d("RSA Encrypt", Base64.encodeToString(rsaEncryption, Base64.DEFAULT));

        byte[] decryptedData = RsaUtils.decrypt(rsaEncryption);

        if (decryptedData != null) {
            String rsaDecryption = new String(decryptedData);
            Log.d("RSA Decrypt", rsaDecryption);
        }

        // Demonstration of building a private key for decryption
        RsaUtils.buildPrivateKey();
    }
}
