package iamdilipkumar.com.nfcdemo.utils;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created on 20/10/17.
 *
 * @author dilipkumar4813
 */

public class NfcUtils {

    public static NfcAdapter nfcAdapter;

    public static boolean isNfcAvailable(Activity activity) {
        if (nfcAdapter == null) {
            Toast.makeText(activity, "NFC unavailable", Toast.LENGTH_SHORT).show();
            activity.finish();
            return false;
        }

        return true;
    }

    public static boolean isNfcEnabled(Activity activity) {
        if (!nfcAdapter.isEnabled()) {
            Toast.makeText(activity, "NFC is not enabled", Toast.LENGTH_SHORT).show();
            // activity.finish();
            return false;
        }

        return true;
    }

    public static void requestPermission(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
            activity.startActivity(intent);
        } else {
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            activity.startActivity(intent);
        }
    }
}
