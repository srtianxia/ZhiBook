package com.srtianxia.zhibook.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhihu.Story;
import com.srtianxia.zhibook.view.viewholder.DailyViewHolder;

import java.util.List;

/**
 * Created by srtianxia on 2016/1/23.
 * 添加 headView和上拉加载更多的RecyclerView adapter
 * 2/20 1.46 未完成
 */
public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;

    private Context context;
    private LayoutInflater inflater;
    private View headView;
    private List<Story> items;
    private OnItemClickListener onItemClickListener;

    public static final int PULL_TO_MORE = 0;
    public static final int LOADING_MORE = 1;

    private int load_status = 0;

    public DailyAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public DailyAdapter(Context context,List<Story> items){
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    public void addItems(List<Story> newItems){
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setHeadView(View headView) {
        this.headView = headView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView!=null && viewType == TYPE_HEADER) return new DailyViewHolder(headView);
        if (viewType == TYPE_NORMAL) {
            View view = inflater.inflate(R.layout.item_find_daily, parent, false);
            return new DailyViewHolder(view);
        }else if (viewType == TYPE_FOOTER){
            View viewFoot = inflater.inflate(R.layout.item_foot_daily,parent,false);
            return new FootViewHolder(viewFoot);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(holder);
        if (holder instanceof DailyViewHolder) {
            final DailyViewHolder viewHolder = (DailyViewHolder) holder;
            viewHolder.bindData(items.get(pos), pos);
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onClick(viewHolder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemClickListener.onLongClick(viewHolder.itemView, pos);
                        return false;
                    }
                });
            }
        }else if (holder instanceof FootViewHolder){

        }
    }

    @Override
    public int getItemCount() {
        return headView == null ? items.size() + 1: items.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(headView == null) return TYPE_NORMAL;
        if (position == 0) {
            return TYPE_HEADER;
        } else return TYPE_NORMAL;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headView == null ? position : position - 1;
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_foot_status;
        private ProgressBar pb_foot_status;
        public FootViewHolder(View itemView) {
            super(itemView);

        }
    }
}
