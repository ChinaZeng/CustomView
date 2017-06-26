package com.zzw.customview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzw.customview.R;

import java.util.List;

/**
 * Created by zzw on 2017/6/26.
 * Version:
 * Des:
 */

public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.VH> {

    private Context context;
    private List<String> datas;

    public SimpleRecyclerViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.item_lv, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private TextView textView;

        public VH(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
