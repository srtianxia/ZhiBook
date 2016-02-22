package com.srtianxia.zhibook.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.srtianxia.zhibook.app.API;
import com.srtianxia.zhibook.app.APP;
import com.srtianxia.zhibook.model.Imodel.IZhiBookModel;
import com.srtianxia.zhibook.model.bean.zhibook.AnswerBean;
import com.srtianxia.zhibook.model.bean.zhibook.CollectFolderBean;
import com.srtianxia.zhibook.model.bean.zhibook.EssayBean;
import com.srtianxia.zhibook.model.bean.zhibook.Note;
import com.srtianxia.zhibook.model.bean.zhibook.QuestionBean;
import com.srtianxia.zhibook.model.callback.OnCollectListener;
import com.srtianxia.zhibook.model.callback.OnGetAnswerListener;
import com.srtianxia.zhibook.model.callback.OnGetCollectListener;
import com.srtianxia.zhibook.model.callback.OnGetNoteListener;
import com.srtianxia.zhibook.model.callback.OnGetQuestionListener;
import com.srtianxia.zhibook.model.callback.OnPraiseListener;
import com.srtianxia.zhibook.model.callback.OnSaveListener;
import com.srtianxia.zhibook.model.callback.OnUploadListener;
import com.srtianxia.zhibook.utils.db.DataBaseHelper;
import com.srtianxia.zhibook.utils.http.OkHttpUtils;
import com.srtianxia.zhibook.utils.http.RetrofitAPI;
import com.srtianxia.zhibook.utils.http.callback.OkHttpUtilsCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by srtianxia on 2016/2/11.
 */
public class ZhiBookModel implements IZhiBookModel {

    private static final String TAG = "ZhiBookModel";
    private static ZhiBookModel zhiBookModel = new ZhiBookModel();
    private Retrofit retrofit;
    private RetrofitAPI retrofitAPI;

    private ZhiBookModel(){
        retrofit = new Retrofit.Builder().
                baseUrl(RetrofitAPI.BASIC_URL).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    public static ZhiBookModel getInstance(){
        return zhiBookModel;
    }

    @Override
    public void setQuestion(String title, String content, String token) {

    }

    @Override
    public void getQuestion(final OnGetQuestionListener listener) {
        retrofitAPI.getQuestion().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<QuestionBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.failure(e.getMessage());
                    }

                    @Override
                    public void onNext(QuestionBean questionBean) {
                        listener.success(questionBean.getQuestions());
                    }
                });
    }

    @Override
    public void setAnswer() {

    }

    @Override
    public void getAnswer(String questionId, final OnGetAnswerListener listener) {
        retrofitAPI.getAnswer(questionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AnswerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(AnswerBean answerBean) {
                        listener.success(answerBean.getAnswers());
                    }
                });
    }

    @Override
    public void praise(int i, final OnPraiseListener listener) {
        final Handler handler = new Handler();
        OkHttpUtils.asyPost("http://115.28.64.168/zhishu/addPraise.php", new OkHttpUtilsCallback() {
            @Override
            public void onResponse(Response response, String status) throws IOException {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        listener.add();
//                    }
//                });
                Log.d("123",""+response.body().string());
            }
        },new OkHttpUtils.Param("id",String.valueOf(i)));
    }

    @Override
    public void getCollectionFolder(String token, final OnGetCollectListener listener) {
        retrofitAPI.getCollection(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectFolderBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(CollectFolderBean bean) {
                        listener.success(bean);
                    }
                });
    }

    @Override
    public void addCollectionFolder(String token, String folder, final OnCollectListener listener) {
//        retrofitAPI.addFolder(token,folder)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG,e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.d(TAG,s);
//                    }
//                });
        OkHttpUtils.asyPost(API.addCollectFolder, new OkHttpUtilsCallback() {
            @Override
            public void onResponse(Response response, String status) throws IOException {
                listener.success();
            }
        },new OkHttpUtils.Param("token",token),new OkHttpUtils.Param("folder",folder));
    }

    @Override
    public void setCollect(String token, int answerId, OnCollectListener listener) {

    }

    @Override
    public void cancelCollect(String token, int answerId, OnCollectListener listener) {

    }



    @Override
    public void addNote(String token, String title, String content, String authorId, String isPrivate, OnSaveListener listener) {
        retrofitAPI.setEssay(title,content,token,isPrivate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EssayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(EssayBean essayBean) {

                    }
                });
    }

    @Override
    public void saveNoteToDB(String content, Integer authorId, OnSaveListener listener) {
        //考虑下这里存数据应该在io线程中，不应该在主线程
        DataBaseHelper dataBaseHelper = new DataBaseHelper(APP.getContext(),
                "zhibook.db",null,1);
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content",content);
        values.put("authorId",authorId);
        if (db.insert("note",null,values)!= -1){
            values.clear();
            listener.success();
        }else {
            values.clear();
            listener.failure();
        }

    }

    @Override
    public void getNoteList(Integer authorId, OnGetNoteListener listener) {
        List<Note> notes = new ArrayList<>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(APP.getContext(),
                "zhibook.db",null,1);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM note WHERE authorId = "+ authorId,null);
        if (cursor.moveToFirst()){
            do {
                notes.add(new Note(
                        cursor.getString(cursor.getColumnIndex("content")),
                        cursor.getInt(cursor.getColumnIndex("authorId")),
                        cursor.getInt(cursor.getColumnIndex("id"))));
            }while (cursor.moveToNext());
        }
        cursor.close();
        if (notes.size()!=0){
            listener.success(notes);
        }else {
            listener.failure();
        }
    }

    @Override
    public void upLoadHead(Uri uri, final String token, final OnUploadListener listener) {
        BTPFileResponse response = BmobProFile.getInstance(APP.getContext()).upload(uri.getPath(), new UploadListener() {
            @Override
            public void onSuccess(String fileName,String url,BmobFile file) {
                Log.i("bmob","文件上传成功："+fileName+",可访问的文件地址："+file.getUrl());
                OkHttpUtils.asyPost(API.updatePersonInfo, new OkHttpUtilsCallback() {
                    @Override
                    public void onResponse(Response response, String status) throws IOException {

                    }
                },new OkHttpUtils.Param("token",token),new OkHttpUtils.Param("headurl",file.getUrl()));
            }

            @Override
            public void onProgress(int progress) {
                Log.i("bmob","onProgress :"+progress);
                listener.progress(progress);
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                Log.i("bmob","文件上传失败："+errormsg);
            }
        });
    }


}
