/**
 * @ClassName: TriangleGLSurfaceView
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.openglnativedemo;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class TriangleGLSurfaceView extends GLSurfaceView {

    public TriangleGLSurfaceView(Context context) {
        this(context,null);
    }

    public TriangleGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 设置OpenGl ES的版本
        setEGLContextClientVersion(3);
        // 设置与当前GLSurfaceView绑定的Renderer
        setRenderer(new TriangleRender(context));
        // 设置渲染的模式
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }
}
