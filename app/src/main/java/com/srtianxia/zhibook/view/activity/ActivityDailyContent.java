package com.srtianxia.zhibook.view.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.model.bean.zhihu.DailyContent;
import com.srtianxia.zhibook.presenter.DailyContentPresenter;
import com.srtianxia.zhibook.view.IView.IActivityDailyContent;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/2/12.
 */
public class ActivityDailyContent extends BaseActivity implements IActivityDailyContent {
    @Bind(R.id.iv_header)
    SimpleDraweeView ivHeader;
    @Bind(R.id.tv_source)
    TextView tvSource;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.wv_news)
    WebView wvNews;
    @Bind(R.id.nested_view)
    NestedScrollView nestedView;
    @Bind(R.id.cpb_loading)
    ContentLoadingProgressBar cpbLoading;


    private String dailyId;
    private DailyContentPresenter presenter;

    private String shareUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_content);
        ButterKnife.bind(this);
        initView();
        presenter = new DailyContentPresenter(this);
        dailyId = getIntent().getStringExtra("id");
        presenter.getDailyContent();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nestedView.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        nestedView.setElevation(0);
        wvNews.getSettings().setJavaScriptEnabled(true);
        wvNews.getSettings().setLoadsImagesAutomatically(true);
        //设置 缓存模式
        wvNews.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        wvNews.getSettings().setDomStorageEnabled(true);

        collapsingToolbarLayout.setTitle(getText(R.string.find_zhihu));
    }

    @Override
    public String getId() {
        return dailyId;
    }

    @Override
    public void showContentSuccess(DailyContent dailyContent) {
        shareUrl = dailyContent.getShareUrl();
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + dailyContent.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        wvNews.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
        ivHeader.setImageURI(Uri.parse(dailyContent.getImage()));
        Log.d("123",shareUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        presenter.onRelieveView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_daily_content,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareUrl);
                shareIntent.setType("text/url");
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
