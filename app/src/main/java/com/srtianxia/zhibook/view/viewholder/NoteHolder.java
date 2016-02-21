package com.srtianxia.zhibook.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.model.bean.zhibook.Note;

/**
 * Created by srtianxia on 2016/2/17.
 */
public class NoteHolder extends RecyclerView.ViewHolder {
    private TextView tvNotePreview;
    public NoteHolder(View itemView) {
        super(itemView);
        tvNotePreview = (TextView) itemView.findViewById(R.id.tv_note_preview);
    }

    public void setData(Note note,int position){
        tvNotePreview.setText(note.getContent());
    }
}
