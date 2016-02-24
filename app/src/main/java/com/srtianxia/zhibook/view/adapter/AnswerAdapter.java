package com.srtianxia.zhibook.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Answer;
import com.srtianxia.zhibook.view.viewholder.AnswerHolder;
import com.srtianxia.zhibook.view.viewholder.DailyViewHolder;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/8.
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;

    private Context context;
    private List<Answer> items;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private View headView;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setHeadView(View headView) {
        this.headView = headView;
    }

    public AnswerAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<Answer> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public AnswerAdapter(Context context, List<Answer> items){
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public AnswerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView!=null && viewType == TYPE_HEADER) return new AnswerHolder(headView);
        View view = inflater.inflate(R.layout.item_answer,parent,false);
        AnswerHolder holder = new AnswerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AnswerHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(holder);
        holder.bindData(items.get(pos), pos);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (items!=null) {
            return headView == null ? items.size() : items.size() + 1;
        }else return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(headView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headView == null ? position : position - 1;
    }
}
