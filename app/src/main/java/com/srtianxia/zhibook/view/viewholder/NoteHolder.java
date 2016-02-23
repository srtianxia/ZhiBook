package com.srtianxia.zhibook.view.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Note;
import com.srtianxia.zhibook.utils.ui.ItemTouchHelperViewHolder;

/**
 * Created by srtianxia on 2016/2/17.
 */
public class NoteHolder extends RecyclerView.ViewHolder implements
        ItemTouchHelperViewHolder {
    private TextView tvNotePreview;
    private TextView tvNoteTime;
    public NoteHolder(View itemView) {
        super(itemView);
        tvNotePreview = (TextView) itemView.findViewById(R.id.tv_note_preview);
        tvNoteTime = (TextView) itemView.findViewById(R.id.tv_note_time);
    }

    public void setData(Note note,int position){
        tvNotePreview.setText(note.getContent());
        tvNoteTime.setText(note.getDate());
    }

    @Override
    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        itemView.setBackgroundColor(0);
    }
}
