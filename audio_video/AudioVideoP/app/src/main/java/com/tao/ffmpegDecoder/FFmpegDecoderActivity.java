/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.ffmpegDecoder;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tao.audiovideop.R;
import com.tao.utils.LogUtils;

public class FFmpegDecoderActivity extends AppCompatActivity {
    private static final String TAG = "FFmpegDecoderActivity";
    private static final String MP3_PATH = "/111/vocal.mp3";
    private static final String PCM_DECODE_PATH = "/111/decode_vocal.pcm";
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
        //new FFmpegDecoder().init("ddd","11");
        String mp3Path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + MP3_PATH;
        String pcmPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + PCM_DECODE_PATH;
        FFmpegDecoder fFmpegDecoder = new FFmpegDecoder();
        fFmpegDecoder.init(mp3Path,pcmPath);
    }
}
