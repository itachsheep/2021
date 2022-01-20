/**
 * @ClassName:      TriangleRender.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2022/1/20 10:56 PM
 */
package com.tao.cpp_lrn

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class TriangleRender: GLSurfaceView.Renderer {
    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        AudioPlay.nativeGlesInit()
    }

    override fun onSurfaceChanged(p0: GL10?, width: Int, height: Int) {
        AudioPlay.nativeGlesResize(width,height)
    }

    override fun onDrawFrame(p0: GL10?) {
        AudioPlay.nativeGlesRender()
    }
}