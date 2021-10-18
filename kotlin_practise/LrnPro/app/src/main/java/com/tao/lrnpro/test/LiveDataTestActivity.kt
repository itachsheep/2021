/**
 * @ClassName:      LiveDataTestActivity.kt
 * @Description:
 *
 * @author          taowei
 * @version         V1.0
 * @Date           2021/10/17 11:28 AM
 */
package com.tao.lrnpro.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tao.lrnpro.LogUtils
import com.tao.lrnpro.R
import kotlinx.coroutines.*

@SuppressLint("SetTextI18n")
class LiveDataTestActivity : AppCompatActivity() {
    //private textView: TextView? = null

    private var textView: TextView? = null
    lateinit var viewModel: LiveDataViewModel

    private val scope = Dispatchers.Default + Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        textView = findViewById(R.id.count_text)
        viewModel = LiveDataViewModel()
        textView?.text = "count = ${viewModel.getScoreValue()}"

        viewModel.getScore().observe(this) {
            textView?.text = "count = $it"
        }

        lifecycle.addObserver(MyLifeCycleObserver(scope,lifecycle))

        GlobalScope.launch {
            val result1 = GlobalScope.async {
                delay(1000)
                1
            }
            val result2 = GlobalScope.async {
                delay(4000)
                4
            }

            val result = result1.await() + result2.await()
//            LogUtils.d("onCreate ---> update textView ddd");
//            textView?.text = "hahah"
            LogUtils.d("onCreate ---> :协程线程id=${Thread.currentThread().id},result = $result")
        }
        LogUtils.d("onCreate end")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("onStart 222")
        CoroutineScope(scope).launch {
            delay(3000)
            LogUtils.d("onStart普通方式开启定位,协程线程id=${Thread.currentThread().id}")
        }
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d("onStop普通方式关闭定位")
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    fun add_count(view: View) {
        /*GlobalScope.launch(Dispatchers.IO) {
            delay(3000L)

        }*/
        viewModel.addScore(100)

    }
}