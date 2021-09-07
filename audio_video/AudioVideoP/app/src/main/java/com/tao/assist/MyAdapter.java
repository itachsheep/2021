/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.assist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tao.audiovideop.R;

import java.util.List;

public class MyAdapter<T> extends RecyclerView.Adapter<MyViewHolder> {
    List<T> mList;
    Context mContext;
    public MyAdapter(Context context, List<T> list) {
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_simple,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        T t = mList.get(position);
        holder.button.setText((String)t);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
