package com.srtianxia.zhibook.model;

import com.srtianxia.zhibook.model.Imodel.IFileModel;

/**
 * Created by srtianxia on 2016/2/17.
 */
public class FileModel implements IFileModel {
    private static FileModel instance = new FileModel();
    private FileModel(){}
    public static FileModel getInstance(){
        return instance;
    }


}
