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
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.security.Permission;

import static com.tao.mjnindk.Constants.VIDEO_RENDER_ANWINDOW;

public class ANativeWindowActivity extends AppCompatActivity implements SurfaceHolder.Callback, FFMediaPlayer.EventCallback {
    private static final String TAG = "ANativeWindowActivity";
    private static String[] request_permission = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private static final int REQUEST_OK = 10001;

    private static final String VIDEO_PATH = "/abyteflow/bili_xiguan.mp4";
    FFMediaPlayer ffMediaPlayer = null;
    SurfaceView surfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anative_window);
        if(!checkPermission(request_permission)) {
            ActivityCompat.requestPermissions(this,request_permission,REQUEST_OK);
        }
        surfaceView = findViewById(R.id.my_surfaceView);
        surfaceView.getHolder().addCallback(this);
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
        //todo
    }

    @Override
    protected void onPause() {
        super.onPause();
        //todo;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        ffMediaPlayer = new FFMediaPlayer();
        ffMediaPlayer.addEventCallback(this);
        String videoPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + VIDEO_PATH;
        LogUtils.d(TAG,"surfaceCreated videoPath:" + videoPath);
        ffMediaPlayer.init(videoPath,VIDEO_RENDER_ANWINDOW,surfaceHolder.getSurface());
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onPlayerEvent(int msgType, float msgValue) {

    }
}
