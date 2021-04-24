/**
 * @ClassName: MySurfaceView
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.mjnindk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView {
    public MySurfaceView(Context context) {
        this(context,null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
