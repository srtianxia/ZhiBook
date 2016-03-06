package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.presenter.MusicPlayerPresenter;
import com.srtianxia.zhibook.utils.ui.PlayerDisc;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/3/5.
 * 学习dagger2的实例activity 使用dagger2将model层注入presenter层
 */
public class ActivityPlayer extends BaseActivity implements View.OnClickListener,MusicPlayerPresenter.IView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.musics_player_seekbar)
    SeekBar musicsPlayerSeekbar;
    @Bind(R.id.musics_player_play_prev_btn)
    ImageButton musicsPlayerPlayPrevBtn;
    @Bind(R.id.musics_player_play_ctrl_btn)
    ImageButton musicsPlayerPlayCtrlBtn;
    @Bind(R.id.musics_player_play_next_btn)
    ImageButton musicsPlayerPlayNextBtn;
    @Bind(R.id.disc)
    PlayerDisc playerDisc;

    private boolean isPlaying = false;
    private MusicPlayerPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        presenter = new MusicPlayerPresenter(this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle(getString(R.string.toolbar_player));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        musicsPlayerPlayCtrlBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.musics_player_play_ctrl_btn:
                if (isPlaying) {
                    musicsPlayerPlayCtrlBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_play_selector));
                    isPlaying = false;
                    playerDisc.pause();
                    presenter.pause();
                } else {
                    musicsPlayerPlayCtrlBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_pause_normal));
                    isPlaying = true;
                    playerDisc.continuePlay();
                    presenter.start();
                }
                break;
        }
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(ActivityPlayer.this, s, Toast.LENGTH_SHORT).show();
    }
}
