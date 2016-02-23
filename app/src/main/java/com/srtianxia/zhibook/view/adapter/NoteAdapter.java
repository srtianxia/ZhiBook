package com.srtianxia.zhibook.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Note;
import com.srtianxia.zhibook.utils.ui.ItemTouchHelperAdapter;
import com.srtianxia.zhibook.view.viewholder.NoteHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srtianxia on 2016/2/17.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteHolder>  implements ItemTouchHelperAdapter {
    private Context context;
    private List<Note> items = new ArrayList<>();
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public NoteAdapter(Context context){
        this.context = context;
    }

    public void setItems(List<Note> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public NoteAdapter(Context context, List<Note> items){
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_note,parent,false);
        NoteHolder holder = new NoteHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final NoteHolder holder, final int position) {
        holder.setData(items.get(position),position);
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

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Note prev = items.remove(fromPosition);
        items.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }
}
