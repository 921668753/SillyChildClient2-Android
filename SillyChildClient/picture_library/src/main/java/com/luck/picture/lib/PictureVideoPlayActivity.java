package com.luck.picture.lib;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.kymjs.common.FileUtils;
import com.luck.picture.lib.widget.MediaController;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnBufferingUpdateListener;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnPreparedListener;
import com.pili.pldroid.player.PLOnSeekCompleteListener;
import com.pili.pldroid.player.widget.PLVideoView;

public class PictureVideoPlayActivity extends PictureBaseActivity implements MediaController.OnClickSpeedAdjustListener, PLOnBufferingUpdateListener, PLOnSeekCompleteListener, PLOnErrorListener, PLOnPreparedListener, PLOnCompletionListener, View.OnClickListener {
    private String video_path = "";
    private ImageView picture_left_back;
    private MediaController mMediaController;
    private PLVideoView mVideoView;
    private ImageView iv_play;
    private long mPositionWhenPaused = -1;

    /**
     * 视频文件缓存保存路径存放的文件名
     */
    public static String VIDEOCACHE = "SHZY/VideoCache";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_activity_video_play);
        video_path = getIntent().getStringExtra("video_path");
        picture_left_back = (ImageView) findViewById(R.id.picture_left_back);
        mVideoView = (PLVideoView) findViewById(R.id.video_view);
        mVideoView.setBackgroundColor(Color.BLACK);
        iv_play = (ImageView) findViewById(R.id.iv_play);
        AVOptions options = new AVOptions();

        /**
         * 若设置为 1，则底层会进行一些针对直播流的优化
         */
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);

        /**
         * 快开模式，启用后会加快该播放器实例再次打开相同协议的视频流的速度
         */
        options.setInteger(AVOptions.KEY_FAST_OPEN, 1);
        String downloadDirectoryPath = FileUtils.getSaveFolder(VIDEOCACHE).getAbsolutePath();
        options.setString(AVOptions.KEY_CACHE_DIR, downloadDirectoryPath);
        mVideoView.setAVOptions(options);
        mVideoView.setOnErrorListener(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnSeekCompleteListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setVideoPath(video_path);
        mMediaController = new MediaController(this);
        mMediaController.setOnClickSpeedAdjustListener(this);
        mVideoView.setMediaController(mMediaController);
        picture_left_back.setOnClickListener(this);
        iv_play.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        // Play Video
        mVideoView.start();
        super.onStart();
    }

    @Override
    public void onPause() {
        // Stop video when the activity is pause.
        mPositionWhenPaused = mVideoView.getCurrentPosition();
        mVideoView.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mVideoView.stopPlayback();
        mMediaController = null;
        mVideoView = null;
        super.onDestroy();
    }

    @Override
    public void onResume() {
        // Resume video player
        if (mPositionWhenPaused >= 0) {
            mVideoView.seekTo(mPositionWhenPaused);
            mPositionWhenPaused = -1;
        }
        mVideoView.start();
        super.onResume();
    }


    @Override
    public void onCompletion() {
        iv_play.setVisibility(View.VISIBLE);
        mMediaController.refreshProgress();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.picture_left_back) {
            finish();
        } else if (id == R.id.iv_play) {
            mVideoView.start();
            iv_play.setVisibility(View.GONE);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ContextWrapper(newBase) {
            @Override
            public Object getSystemService(String name) {
                if (Context.AUDIO_SERVICE.equals(name)) {
                    return getApplicationContext().getSystemService(name);
                }
                return super.getSystemService(name);
            }
        });
    }

//    @Override
//    public void onPrepared(MediaPlayer mp) {

//    }


    @Override
    public void onPrepared(int i) {
////        mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
////            @Override
////            public boolean onInfo(MediaPlayer mp, int what, int extra) {

//                return false;
////            }
////        });
    }

    @Override
    public boolean onError(int i) {


        return false;
    }

    @Override
    public void onClickNormal() {
        // 0x0001/0x0001 = 2
        mVideoView.setPlaySpeed(0X00010001);
    }

    @Override
    public void onClickFaster() {
        // 0x0002/0x0001 = 2
        iv_play.setVisibility(View.GONE);
        mVideoView.setPlaySpeed(0X00020001);
    }

    @Override
    public void onClickSlower() {
        // 0x0001/0x0002 = 0.5
        mVideoView.setPlaySpeed(0X00010002);
    }

    @Override
    public void onSeekComplete() {
        iv_play.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBufferingUpdate(int i) {
        if (i == 0) {
            iv_play.setVisibility(View.VISIBLE);
        } else {
            iv_play.setVisibility(View.GONE);
        }
    }
}
