/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.mp3encoder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.tao.audiovideop.R;
import com.tao.utils.LogUtils;

public class Mp3EncoderActivity extends AppCompatActivity {
    private static final String TAG = "Mp3EncoderActivity";
    static {
        System.loadLibrary("mp3_encoder");
    }

    private static final String VIDEO_PATH = "/111/vocal.pcm";
    private static final String MP3_PATH = "/111/vocal.mp3";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3encoder);

    }

    public void bt_mp3encoder(View view) {
        Mp3Encoder mp3Encoder = new Mp3Encoder();
        String pcmPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + VIDEO_PATH;
        int audioChannels = 2;
        int bitRate = 128 * 1024;
        int sampleRate = 44100;
        String mp3Path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + MP3_PATH;
        LogUtils.d(TAG,"bt_mp3encoder pcmPath: " + pcmPath);
        int ret = mp3Encoder.init(pcmPath, mp3Path, audioChannels, bitRate, sampleRate);
        LogUtils.d(TAG,"bt_mp3encoder ret: " + ret);
        if(ret >= 0) {
            mp3Encoder.encode();
            mp3Encoder.destroy();
        }

    }





}
