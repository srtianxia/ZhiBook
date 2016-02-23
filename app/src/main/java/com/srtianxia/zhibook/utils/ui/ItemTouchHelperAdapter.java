package com.srtianxia.zhibook.utils.ui;


/**
 * Created by srtianxia on 2016/2/23.
 */
public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
