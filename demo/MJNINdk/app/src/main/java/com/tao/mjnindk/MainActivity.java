package com.tao.mjnindk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvVersion;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("life-tip");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        tvVersion = findViewById(R.id.ffm_version);
        tvVersion.setText(stringFromJNI());
    }

    public void get_ffm_version(View view) {
        tvVersion.setText(getFFmpegVersion());
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String getFFmpegVersion();
}