package iamdilipkumar.com.androidsecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import iamdilipkumar.com.androidsecurity.utils.JwtUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JwtUtils.generateJJwt();
    }
}
