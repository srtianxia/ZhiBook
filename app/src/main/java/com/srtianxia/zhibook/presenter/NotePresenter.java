package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.bean.zhibook.Note;
import com.srtianxia.zhibook.model.callback.OnGetNoteListener;
import com.srtianxia.zhibook.view.IView.IActivityNote;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/21.
 */
public class NotePresenter {
    private IActivityNote iActivityNote;
    private IZhiBookModel iZhiBookModel;

    public NotePresenter(IActivityNote iActivityNote){
        this.iActivityNote = iActivityNote;
        iZhiBookModel = ZhiBookModel.getInstance();
    }

    public void getNote(){
        iZhiBookModel.getNoteList(0, new OnGetNoteListener() {
            @Override
            public void success(List<Note> notes) {
                iActivityNote.showNoteSuccess(notes);
            }

            @Override
            public void failure() {

            }
        });
    }
    public void onRelieveView() {
        if (iActivityNote != null) iActivityNote = null;
    }
}
