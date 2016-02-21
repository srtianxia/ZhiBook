package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.ZhiBookModel;
import com.srtianxia.zhibook.model.callback.OnSaveListener;
import com.srtianxia.zhibook.view.IView.IActivityNoteEdit;

/**
 * Created by srtianxia on 2016/2/18.
 *
 * 保存note逻辑 2/18 未完成 presenter
 */
public class NoteEditPresenter {
    private IActivityNoteEdit iActivityNoteEdit;
    private IZhiBookModel iZhiBookModel;

    public NoteEditPresenter(IActivityNoteEdit iActivityNoteEdit){
        this.iActivityNoteEdit = iActivityNoteEdit;
        iZhiBookModel = ZhiBookModel.getInstance();
    }

    public void saveNoteToDB(){
        iZhiBookModel.saveNoteToDB(iActivityNoteEdit.getNoteContent(), 1, new OnSaveListener() {
            @Override
            public void success() {
                iActivityNoteEdit.saveDBsuccess();
            }

            @Override
            public void failure() {
                iActivityNoteEdit.saveDBfailure();
            }
        });
    }

    public void onRelieveView() {
        if (iActivityNoteEdit != null) iActivityNoteEdit = null;
    }
}
