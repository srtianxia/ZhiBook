package com.srtianxia.zhibook.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Question;
import com.srtianxia.zhibook.view.viewholder.QuestionHolder;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/6.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder> {
    private Context context;
    private List<Question> items;
    private LayoutInflater inflater;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public QuestionAdapter(Context context,List<Question> items){
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home_question,parent,false);
        QuestionHolder holder = new QuestionHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final QuestionHolder holder, final int position) {
        holder.bindData(items.get(position),position);
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(holder.itemView,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(holder.itemView,position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
