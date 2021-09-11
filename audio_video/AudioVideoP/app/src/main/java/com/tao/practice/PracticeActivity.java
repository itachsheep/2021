/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.practice;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tao.assist.MyAdapter;
import com.tao.assist.OnItemClickListener;
import com.tao.audiovideop.R;
import com.tao.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PracticeActivity extends AppCompatActivity {
    private static final String TAG = "PracticeActivity";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        recyclerView = findViewById(R.id.recyclerView);
        initView();
    }

    private void initView() {
        ArrayList<String> list = new ArrayList<>();
        list.add("simdjson-test");
        list.add("NewMalloc-test");
        list.add("测试3");
        list.add("测试4");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(getBaseContext(),list);
        myAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NotNull View view, int position) {
                LogUtils.d(TAG,"onItemClick pos: " + position);
                /*Toast.makeText(PracticeActivity.this,"hello " + position,
                        Toast.LENGTH_SHORT).show();*/
                dealItemClick(position);
            }
        });
        recyclerView.setAdapter(myAdapter);

    }

    private void dealItemClick(int position) {
        switch (position) {
            case 0:
                testSimdJson();
                break;
            case 1:
                new Grammar().testNewMalloc();
                break;
        }
    }

    private static final String JSON_PATH = "/111/twitter.json";
    private void testSimdJson() {
        LogUtils.d(TAG,"testSimdJson");
        String jsonPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + JSON_PATH;
        //String jsonPath = "/storage/self/primary/111/twitter.json";
        SimdJson simdJson = new SimdJson();
        simdJson.testSimdJson(jsonPath);
    }
}
