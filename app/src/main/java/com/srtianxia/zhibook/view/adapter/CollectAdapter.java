package com.srtianxia.zhibook.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Collect;
import com.srtianxia.zhibook.view.viewholder.CollectHolder;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/15.
 */
public class CollectAdapter extends RecyclerView.Adapter<CollectHolder> {
    private List<Collect> items;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;



    public void setData(List<Collect> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CollectAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public CollectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_find_collect,parent,false);
        CollectHolder holder = new CollectHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CollectHolder holder, int position) {
        holder.setData(items.get(position),position);
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
