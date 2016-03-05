package com.srtianxia.zhibook.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.srtianxia.zhibook.R;
import com.srtianxia.zhibook.app.BaseActivity;
import com.srtianxia.zhibook.utils.ui.PlayerDisc;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by srtianxia on 2016/3/4.
 */
public class ActivityPlayer extends BaseActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
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
                } else {
                    musicsPlayerPlayCtrlBtn.setImageDrawable(getResources().getDrawable(R.drawable.btn_pause_normal));
                    isPlaying = true;
                    playerDisc.continuePlay();
                }
                break;
        }
    }
}
