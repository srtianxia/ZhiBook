package com.srtianxia.zhibook.model.Imodel;

import android.net.Uri;

import com.srtianxia.zhibook.model.callback.OnCollectListener;
import com.srtianxia.zhibook.model.callback.OnGetAnswerListener;
import com.srtianxia.zhibook.model.callback.OnGetCollectListener;
import com.srtianxia.zhibook.model.callback.OnGetNoteListener;
import com.srtianxia.zhibook.model.callback.OnGetQuestionListener;
import com.srtianxia.zhibook.model.callback.OnPraiseListener;
import com.srtianxia.zhibook.model.callback.OnSaveListener;
import com.srtianxia.zhibook.model.callback.OnSetAnswerListener;
import com.srtianxia.zhibook.model.callback.OnSetQuestionListener;
import com.srtianxia.zhibook.model.callback.OnUploadListener;

/**
 * Created by srtianxia on 2016/2/11.
 */
public interface IZhiBookModel {
    void setQuestion(String title, String content, String token, OnSetQuestionListener listener);
    void getQuestion(OnGetQuestionListener listener);

    void setAnswer(String content, String questionId, String token,
                   OnSetAnswerListener listener);
    void getAnswer(String questionId, OnGetAnswerListener listener);

    void praise(int i, OnPraiseListener listener);

    void getCollectionFolder(String token,OnGetCollectListener listener);
    void addCollectionFolder(String token,String folder,OnCollectListener listener);
    void setCollect(String token,int answerId,OnCollectListener listener);
    void cancelCollect(String token,int answerId,OnCollectListener listener);
    void addNote(String token, String title, String content,
                 String authorId, String isPrivate, OnSaveListener listener);
    //数据库操作
    void saveNoteToDB(String content,Integer authorId,OnSaveListener listener);
    void getNoteList(Integer authorId,OnGetNoteListener listener);

    //bmob
    void upLoadHead(Uri uri,String authorId,OnUploadListener listener);

    //
    void upLoadPic(Uri uri,OnUploadListener listener);
}
