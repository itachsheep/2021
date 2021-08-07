package com.tao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tao.audiovideop.R;
import com.tao.ffmpegDecoder.FFmpegDecoderActivity;
import com.tao.mp3encoder.Mp3EncoderActivity;
import com.tao.utils.LogUtils;

public class AudioVideoMainActivity extends AppCompatActivity {

    private static final String TAG = "AudioVideoMainActivity";
    private static final int REQUEST_OK = 10001;

    private static String[] request_permission = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("main-test");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!checkPermission(request_permission)) {
            ActivityCompat.requestPermissions(this,request_permission,REQUEST_OK);
        }

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void bt_enter_mp3encoder(View view) {
        startActivity(new Intent(this, Mp3EncoderActivity.class));
    }

    public void bt_enter_ffmpeg_decoder(View view) {
        startActivity(new Intent(this, FFmpegDecoderActivity.class));
    }

    public boolean checkPermission(String[] permissions) {
        for (String permission:permissions) {
            if(ActivityCompat.checkSelfPermission(this,permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        LogUtils.d(TAG,"checkPermission ok");
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtils.d(TAG,"onRequestPermissionsResult: " + requestCode);
        if(requestCode != REQUEST_OK) {
            Toast.makeText(this,"权限申请失败",Toast.LENGTH_LONG).show();
        }
    }
}