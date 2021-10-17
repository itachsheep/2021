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
import androidx.lifecycle.ViewModel
import com.tao.lrnpro.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SetTextI18n")
class LiveDataTestActivity : AppCompatActivity() {
    //private textView: TextView? = null

    private var textView: TextView? = null
    lateinit var viewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        textView = findViewById(R.id.count_text)
        viewModel = LiveDataViewModel()
        textView?.text = "count = ${viewModel.getScoreValue()}"

        viewModel.getScore().observe(this) {
            textView?.text = "count = $it"
        }
    }

    fun add_count(view: View) {
        /*GlobalScope.launch(Dispatchers.IO) {
            delay(3000L)

        }*/
        viewModel.addScore(100)

    }
}