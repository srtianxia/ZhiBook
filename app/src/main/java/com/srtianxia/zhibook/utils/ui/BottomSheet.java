package com.srtianxia.zhibook.utils.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/**
 * Created by srtianxia on 2016/2/16.
 */
public class BottomSheet extends Dialog implements View.OnClickListener{

    public BottomSheet(Context context) {
        super(context);
    }

    public BottomSheet(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BottomSheet(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void onClick(View v) {

    }
}
