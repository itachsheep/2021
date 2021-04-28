/**
 * @ClassName: ANativeWindowActivity
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.mjnindk;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static com.tao.mjnindk.Constants.VIDEO_RENDER_ANWINDOW;
import static com.tao.mjnindk.FFMediaPlayer.*;

public class ANativeWindowActivity extends AppCompatActivity implements SurfaceHolder.Callback, FFMediaPlayer.EventCallback {
    private static final String TAG = "ANativeWindowActivity";
    private static String[] request_permission = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private static final int REQUEST_OK = 10001;

    private static final String VIDEO_PATH = "/abyteflow/bili_xiguan.mp4";
    FFMediaPlayer mMediaPlayer = null;
    MySurfaceView mSurfaceView;
    private SeekBar mSeekBar = null;
    private boolean mIsTouch = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anative_window);
        if(!checkPermission(request_permission)) {
            ActivityCompat.requestPermissions(this,request_permission,REQUEST_OK);
        }
        mSurfaceView = findViewById(R.id.my_surfaceView);
        mSurfaceView.getHolder().addCallback(this);

        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mIsTouch = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch() called with: progress = [" + seekBar.getProgress() + "]");
                if(mMediaPlayer != null) {
                    mMediaPlayer.seekToPosition(mSeekBar.getProgress());
                    mIsTouch = false;
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtils.d(TAG,"onRequestPermissionsResult: " + requestCode);
        if(requestCode != REQUEST_OK) {
            Toast.makeText(this,"权限申请失败",Toast.LENGTH_LONG).show();
        }
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
    protected void onResume() {
        super.onResume();
        if(mMediaPlayer != null)
            mMediaPlayer.play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mMediaPlayer != null)
            mMediaPlayer.pause();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mMediaPlayer = new FFMediaPlayer();
        mMediaPlayer.addEventCallback(this);
        String videoPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + VIDEO_PATH;
        LogUtils.d(TAG,"surfaceCreated videoPath:" + videoPath);
        mMediaPlayer.init(videoPath,VIDEO_RENDER_ANWINDOW,surfaceHolder.getSurface());
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int w, int h) {
        LogUtils.d(TAG, "surfaceChanged() called with: surfaceHolder = [" + surfaceHolder + "], format = [" + format + "], w = [" + w + "], h = [" + h + "]");
        mMediaPlayer.play();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        LogUtils.d(TAG, "surfaceDestroyed() called with: surfaceHolder = [" + surfaceHolder + "]");
        mMediaPlayer.unInit();
    }

    @Override
    public void onPlayerEvent(int msgType, float msgValue) {
        LogUtils.d(TAG, "onPlayerEvent() called with: msgType = [" + msgType + "], msgValue = [" + msgValue + "]");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (msgType) {
                    case MSG_DECODER_INIT_ERROR:
                        break;
                    case MSG_DECODER_READY:
                        onDecoderReady();
                        break;
                    case MSG_DECODER_DONE:
                        break;
                    case MSG_REQUEST_RENDER:
                        break;
                    case MSG_DECODING_TIME:
                        //if(!mIsTouch)
                        mSeekBar.setProgress((int) msgValue);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void onDecoderReady() {
        int videoWidth = (int) mMediaPlayer.getMediaParams(MEDIA_PARAM_VIDEO_WIDTH);
        int videoHeight = (int) mMediaPlayer.getMediaParams(MEDIA_PARAM_VIDEO_HEIGHT);
        if(videoHeight * videoWidth != 0)
            mSurfaceView.setAspectRatio(videoWidth, videoHeight);

        int duration = (int) mMediaPlayer.getMediaParams(MEDIA_PARAM_VIDEO_DURATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mSeekBar.setMin(0);
        }
        mSeekBar.setMax(duration);
    }
}
