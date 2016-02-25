package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.ChatModel;
import com.srtianxia.zhibook.model.Imodel.IChatModel;
import com.srtianxia.zhibook.model.bean.ChatBean;
import com.srtianxia.zhibook.model.callback.OnChatListener;
import com.srtianxia.zhibook.view.IView.IActivityChat;

/**
 * Created by srtianxia on 2016/2/21.
 */
public class ChatPresenter {
    private IChatModel iChatModel;
    private IActivityChat iActivityChat;

    public ChatPresenter(IActivityChat iActivityChat){
        this.iActivityChat = iActivityChat;
        this.iChatModel = ChatModel.getInstance();
    }

    public void sendMsg(ChatBean chatBean){
        iChatModel.postMsg(chatBean.getText(), "", new OnChatListener() {
            @Override
            public void success(ChatBean chatBean) {
                    iActivityChat.onResponse(chatBean);
            }
        });
    }

    public void onRelieveView() {
        if (iActivityChat != null) iActivityChat = null;
    }
}
