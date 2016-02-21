package com.srtianxia.zhibook.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Note;
import com.srtianxia.zhibook.view.viewholder.NoteHolder;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/17.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {
    private Context context;
    private List<Note> items;
    private LayoutInflater inflater;

    public NoteAdapter(Context context,List<Note> items){
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_note,parent,false);
        NoteHolder holder = new NoteHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        holder.setData(items.get(position),position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
