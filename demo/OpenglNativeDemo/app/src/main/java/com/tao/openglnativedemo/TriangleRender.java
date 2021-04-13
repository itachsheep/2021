/**
 * @ClassName: TriangleRender
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.openglnativedemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public  class TriangleRender implements GLSurfaceView.Renderer {
    private static final String TAG = "TriangleRender";
    static {
        System.loadLibrary("triangle-lib");
    }

    private AssetManager mAssetMgr = null;
    public TriangleRender(Context context) {
        mAssetMgr = context.getAssets();
        if (null == mAssetMgr) {
            LogUtils.d(TAG, "getAssets() return null !");
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        readShaderFile(mAssetMgr);
        doSurfaceCreate();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        doSurfaceChanged(width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        doDrawFrame();
    }

    public native void doSurfaceCreate();
    public native void doSurfaceChanged(int width, int height);
    public native void doDrawFrame();
    public native void readShaderFile(AssetManager assetMgr);

}