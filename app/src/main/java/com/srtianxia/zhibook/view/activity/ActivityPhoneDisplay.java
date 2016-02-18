package com.srtianxia.zhibook.view.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.utils.FileUtils;
import com.srtianxia.zhibook.utils.markdown.MDReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/1/20.
 * 2/17 展示页面存为bitmap
 */
public class ActivityPhoneDisplay extends BaseActivity {
    @Bind(R.id.tv_display)
    TextView tvDisplay;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_display_scrollview)
    NestedScrollView tvDisplayScrollview;

    private MDReader mdReader;
    private static final String DEFAULT_DIR = Environment.getExternalStorageDirectory() + File.separator + "ZhiBook";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_display);
        ButterKnife.bind(this);
        initView();
        checkStorageDir();
        String data = getIntent().getStringExtra("content");
        mdReader = new MDReader(data);
        tvDisplay.setTextKeepState(mdReader.getFormattedContent(), TextView.BufferType.SPANNABLE);
    }

    private void initView() {
        toolbar.setTitle(getString(R.string.toolbar_display));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                Log.d("123","213");
                saveAsBitmap();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkStorageDir() {
        if (FileUtils.isSDCardMounted()) {
            File directory = new File(DEFAULT_DIR);
            if ( !directory.exists() ) {
                directory.mkdir();
            }
        }
    }

    public boolean checkSaveEnv() {
        if (!FileUtils.isSDCardMounted()) {
            Toast.makeText(this, "找不到 SDCard !", Toast.LENGTH_LONG).show();
            return false;
        }
        if ("".equals(mdReader.getContent())) {
            Toast.makeText(this, "没有内容,无法保存 !", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void saveAsBitmap() {
        if (!checkSaveEnv()) {
            return;
        }
        String filepath = DEFAULT_DIR+ File.separator+mdReader.getTitle()+".jpg";
        try {
            FileOutputStream stream = new FileOutputStream(filepath);
            Bitmap bitmap = createBitmap(tvDisplayScrollview);
            if (bitmap!=null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG,  100, stream);
                Toast.makeText(this, "成功保存到:"+filepath, Toast.LENGTH_LONG).show();
            }
            stream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap createBitmap(NestedScrollView v) {
        int width = 0, height = 0;
        for (int i = 0; i < v.getChildCount(); i++) {
            width  += v.getChildAt(i).getWidth();
            height += v.getChildAt(i).getHeight();
        }
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    public static Bitmap createBitmap(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return bitmap;
    }
}
