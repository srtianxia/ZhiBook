package com.srtianxia.zhibook.model.callback;

import com.srtianxia.zhibook.model.bean.zhibook.Note;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/21.
 */
public interface OnGetNoteListener {
    void success(List<Note> notes);
    void failure();
}
