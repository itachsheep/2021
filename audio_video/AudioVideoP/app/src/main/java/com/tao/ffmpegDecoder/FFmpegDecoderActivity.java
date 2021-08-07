/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.ffmpegDecoder;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tao.audiovideop.R;
import com.tao.utils.LogUtils;

public class FFmpegDecoderActivity extends AppCompatActivity {
    private static final String TAG = "FFmpegDecoderActivity";

    static {
        System.loadLibrary("ffmpeg_decoder");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmpeg_decoder);
    }

    public void bt_ffmpeg_decoder(View view) {
        LogUtils.d(TAG,"bt_ffmpeg_decoder");
        new FFmpegDecoder().init("ddd","11");
    }
}
