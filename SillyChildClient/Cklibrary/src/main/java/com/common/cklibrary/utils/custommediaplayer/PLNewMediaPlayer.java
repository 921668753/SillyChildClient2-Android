package com.common.cklibrary.utils.custommediaplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.dueeeke.videoplayer.player.AbstractPlayer;
import com.kymjs.common.FileUtils;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.PLOnBufferingUpdateListener;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnPreparedListener;
import com.pili.pldroid.player.PLOnVideoSizeChangedListener;

import java.util.Map;

public class PLNewMediaPlayer extends AbstractPlayer {


    protected PLMediaPlayer mMediaPlayer;
    private boolean isLooping;
    protected Context mAppContext;
    private int mBufferedPercent;

    public PLNewMediaPlayer(Context context) {
        mAppContext = context.getApplicationContext();
    }

    @Override
    public void initPlayer() {
        AVOptions options = new AVOptions();

        /**
         * 快开模式，启用后会加快该播放器实例再次打开相同协议的视频流的速度
         */
        options.setInteger(AVOptions.KEY_FAST_OPEN, 1);
        // 打开视频时单次 http 请求的超时时间，一次打开过程最多尝试五次,单位为 ms
        String downloadDirectoryPath = FileUtils.getSaveFolder(StringConstants.VIDEOCACHE).getAbsolutePath();
        options.setString(AVOptions.KEY_CACHE_DIR, downloadDirectoryPath);
        mMediaPlayer = new PLMediaPlayer(KJActivityStack.create().topActivity(), options);
        mMediaPlayer.setOnErrorListener(onErrorListener);
        mMediaPlayer.setOnCompletionListener(onCompletionListener);
        mMediaPlayer.setOnInfoListener(onInfoListener);
        mMediaPlayer.setOnBufferingUpdateListener(onBufferingUpdateListener);
        mMediaPlayer.setOnPreparedListener(onPreparedListener);
        mMediaPlayer.setOnVideoSizeChangedListener(onVideoSizeChangedListener);
    }

    @Override
    public void setOptions() {

    }

    @Override
    public void setDataSource(String path, Map<String, String> headers) {
        try {
            mMediaPlayer.setDataSource(path);
        } catch (Exception e) {
            mPlayerEventListener.onError();
        }
    }

    @Override
    public void setDataSource(AssetFileDescriptor assetFileDescriptor) {
//        try {
//            mMediaPlayer.setDataSource(fd.getFileDescriptor());
//        } catch (Exception e) {
//            mPlayerEventListener.onError();
//        }
    }


    @Override
    public void pause() {
        if (mMediaPlayer == null)
            return;
        try {
            mMediaPlayer.pause();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        if (mMediaPlayer == null)
            return;
        mMediaPlayer.start();
    }

    @Override
    public void stop() {
        if (mMediaPlayer == null)
            return;
        try {
            mMediaPlayer.stop();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void prepareAsync() {
        if (mMediaPlayer == null)
            return;
        try {
            mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            mPlayerEventListener.onError();
        }
    }

    @Override
    public void reset() {
        release();
        initPlayer();
        mMediaPlayer.setLooping(isLooping);
    }

    @Override
    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    @Override
    public void seekTo(long time) {
        if (mMediaPlayer == null)
            return;
        try {
            mMediaPlayer.seekTo((int) time);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void release() {
        if (mMediaPlayer != null)
            mMediaPlayer.release();
    }

    @Override
    public long getCurrentPosition() {
        try {
            if (mMediaPlayer != null) {
                return mMediaPlayer.getCurrentPosition();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long getDuration() {
        try {
            if (mMediaPlayer != null) {
                return mMediaPlayer.getDuration();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }


    @Override
    public int getBufferedPercentage() {
        return mBufferedPercent;
    }

    @Override
    public void setSurface(Surface surface) {
        mMediaPlayer.setSurface(surface);
    }

    @Override
    public void setDisplay(SurfaceHolder holder) {
        mMediaPlayer.setDisplay(holder);
    }

    @Override
    public void setVolume(float v1, float v2) {
        mMediaPlayer.setVolume(v1, v2);
    }

    @Override
    public void setLooping(boolean isLooping) {
        mMediaPlayer.setLooping(isLooping);
    }

    @Override
    public void setEnableMediaCodec(boolean isEnable) {
//        isEnableMediaCodec = isEnable;
//        int value = isEnable ? 1 : 0;
//        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", value);//开启硬解码
//        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", value);
//        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", value);
    }

    @Override
    public void setSpeed(float speed) {
       // mMediaPlayer.setPlaySpeed(speed);
    }

    @Override
    public long getTcpSpeed() {
        return 0;
    }

    private PLOnErrorListener onErrorListener = new PLOnErrorListener() {
        @Override
        public boolean onError(int i) {
            mPlayerEventListener.onError();
            return true;
        }
    };

    private PLOnCompletionListener onCompletionListener = new PLOnCompletionListener() {
        @Override
        public void onCompletion() {
            mPlayerEventListener.onCompletion();
        }
    };

    private PLOnInfoListener onInfoListener = new PLOnInfoListener() {
        @Override
        public void onInfo(int what, int extra) {
            mPlayerEventListener.onInfo(what, extra);
        }
    };

    private PLOnBufferingUpdateListener onBufferingUpdateListener = new PLOnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(int percent) {
            mBufferedPercent = percent;
        }
    };


    private PLOnPreparedListener onPreparedListener = new PLOnPreparedListener() {
        @Override
        public void onPrepared(int i) {
            mPlayerEventListener.onPrepared();
        }
    };

    private PLOnVideoSizeChangedListener onVideoSizeChangedListener = new PLOnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(int i, int i1) {
            int videoWidth = mMediaPlayer.getVideoWidth();
            int videoHeight = mMediaPlayer.getVideoHeight();
            if (videoWidth != 0 && videoHeight != 0) {
                mPlayerEventListener.onVideoSizeChanged(videoWidth, videoHeight);
            }
        }
    };
}

