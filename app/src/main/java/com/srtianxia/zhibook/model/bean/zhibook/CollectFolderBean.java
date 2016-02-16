package com.srtianxia.zhibook.model.bean.zhibook;

import java.util.List;

/**
 * Created by srtianxia on 2016/2/16.
 */
public class CollectFolderBean {
    private List<CollectFolder> folders;
    private int userId;

    public List<CollectFolder> getFolders() {
        return folders;
    }

    public void setFolders(List<CollectFolder> folders) {
        this.folders = folders;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
