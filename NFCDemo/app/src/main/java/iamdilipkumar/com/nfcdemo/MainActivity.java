package iamdilipkumar.com.nfcdemo;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import iamdilipkumar.com.nfcdemo.utils.NfcUtils;

import static android.nfc.NdefRecord.createMime;

public class MainActivity extends AppCompatActivity implements NfcAdapter.CreateNdefMessageCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NfcUtils.nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (NfcUtils.isNfcAvailable(this)) {
            if (NfcUtils.isNfcEnabled(this)) {
                transmitData();
            }else{
                NfcUtils.requestPermission(this);
            }
        }
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        String text = ("Beam me up, Android!\n\n" +
                "Beam Time: " + System.currentTimeMillis());
        return new NdefMessage(
                new NdefRecord[]{createMime(
                        "application/vnd.com.example.android.beam", text.getBytes())});
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

    private void transmitData(){
        // Register callback
        NfcUtils.nfcAdapter.setNdefPushMessageCallback(this, this);
    }

    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        String data = new String(msg.getRecords()[0].getPayload());

        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}
