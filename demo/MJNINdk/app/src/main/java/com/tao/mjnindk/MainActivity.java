package com.tao.mjnindk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView tvVersion;
    private static  final String [] EXAMPLE_LIST = {
            "FFmpeg + ANativeWindow player",
            "FFmpeg + OpenGL ES player",
            "FFmpeg + OpenSL ES visual audio player",
            "FFmpeg + OpenGL ES VR player",
            "FFmpeg + single video recorder",
            "FFmpeg + single audio recorder",
            "FFmpeg + AV recorder"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        tvVersion = findViewById(R.id.ffm_version);
        tvVersion.setText(FFMediaPlayer.stringFromJNI());
    }

    public void get_ffm_version(View view) {
        tvVersion.setText(FFMediaPlayer.getFFmpegVersion());
    }

    public void ffm_begin(View view) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        MyAdapter myAdapter = new MyAdapter(Arrays.asList(EXAMPLE_LIST));
        recyclerView.setAdapter(myAdapter);
        myAdapter.addOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                LogUtils.d(TAG,"ffm_begin pos: " + pos);

                switch (pos) {
                    case 0:
                        startActivity(new Intent(MainActivity.this,ANativeWindowActivity.class));
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        });

    }

    class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView tvContent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
        }

        public void setContent(String content) {
            tvContent.setText(content);
        }

    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {
        List<String> data;
        View.OnClickListener mOnClickListener;

        public MyAdapter(List<String> datas) {
            data = datas;
        }

        public void addOnClickListener(View.OnClickListener onClickListener) {
            mOnClickListener = onClickListener;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,
                    parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            view.setOnClickListener(this);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.setContent(data.get(position));
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onClick(View view) {
            LogUtils.d(TAG,"onClick: " + mOnClickListener);
            if (mOnClickListener != null) {
//                mOnClickListener.onItemClick(v, (Integer) v.getTag());
                mOnClickListener.onClick(view);
            }
        }
    }
}