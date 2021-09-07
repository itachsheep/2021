/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.assist;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tao.audiovideop.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public Button button;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        button = itemView.findViewById(R.id.bt_item);
    }
}
