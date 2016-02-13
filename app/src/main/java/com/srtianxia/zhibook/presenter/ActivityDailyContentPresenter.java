package com.srtianxia.zhibook.presenter;

import com.srtianxia.zhibook.model.Imodel.IZhiHuModel;
import com.srtianxia.zhibook.model.ZhiHuModel;
import com.srtianxia.zhibook.model.bean.zhihu.DailyContent;
import com.srtianxia.zhibook.model.callback.OnGetDailyContentListener;
import com.srtianxia.zhibook.view.IView.IActivityDailyContent;

/**
 * Created by srtianxia on 2016/2/12.
 */
public class ActivityDailyContentPresenter {
    private IActivityDailyContent iActivityDailyContent;
    private IZhiHuModel iZhiHuModel;

    public ActivityDailyContentPresenter(IActivityDailyContent iActivityDailyContent){
        this.iActivityDailyContent = iActivityDailyContent;
        iZhiHuModel = ZhiHuModel.getInstance();
    }

    public void getDailyContent(){
        iZhiHuModel.getDailyContent(iActivityDailyContent.getId(), new OnGetDailyContentListener() {
            @Override
            public void success(DailyContent content) {
                iActivityDailyContent.showContentSuccess(content);
            }

            @Override
            public void failure() {

            }
        });
    }
}
