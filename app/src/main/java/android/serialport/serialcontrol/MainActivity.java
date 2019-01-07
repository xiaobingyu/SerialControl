package android.serialport.serialcontrol;

import android.serialport.SerialPortFinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity  extends SerialPortActivity {

    EditText mReception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReception = (EditText) findViewById(R.id.EditTextReception);


        final Button mAllOn = (Button)findViewById(R.id.ButtonAllOn);
        mAllOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mOutputStream.write(new String("FF250126EE").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDataReceived(final byte[] buffer, final int size) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (mReception != null) {
                    mReception.append(new String(buffer, 0, size));
                }
            }
        });
    }
}
