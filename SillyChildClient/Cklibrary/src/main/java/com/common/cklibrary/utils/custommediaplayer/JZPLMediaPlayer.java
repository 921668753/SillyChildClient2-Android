package com.common.cklibrary.utils.custommediaplayer;

import android.media.MediaPlayer;
import android.view.Surface;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.kymjs.common.FileUtils;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.PLOnBufferingUpdateListener;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnPreparedListener;
import com.pili.pldroid.player.PLOnSeekCompleteListener;
import com.pili.pldroid.player.PLOnVideoSizeChangedListener;


import cn.jzvd.JZMediaInterface;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZVideoPlayerManager;


/**
 * Created by Nathen on 2017/11/18.
 */

public class JZPLMediaPlayer extends JZMediaInterface implements PLOnPreparedListener, PLOnVideoSizeChangedListener, PLOnCompletionListener, PLOnSeekCompleteListener, PLOnErrorListener, PLOnInfoListener, PLOnBufferingUpdateListener {

    PLMediaPlayer ijkMediaPlayer;

    @Override
    public void start() {
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.start();
        }

    }

    @Override
    public void prepare() {
        AVOptions options = new AVOptions();
        /**     解码方式:
         *      codec＝AVOptions.MEDIA_CODEC_HW_DECODE，硬解
         *      codec=AVOptions.MEDIA_CODEC_SW_DECODE, 软解
         *      codec=AVOptions.MEDIA_CODEC_AUTO, 硬解优先，失败后自动切换到软解
         *      默认值是：MEDIA_CODEC_SW_DECODE
         */
        //     options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_AUTO);

        /**
         * 若设置为 1，则底层会进行一些针对直播流的优化
         */
//        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);

        /**
         * 快开模式，启用后会加快该播放器实例再次打开相同协议的视频流的速度
         */
        options.setInteger(AVOptions.KEY_FAST_OPEN, 1);

//        // 打开重试次数，设置后若打开流地址失败，则会进行重试
//        options.setInteger(AVOptions.KEY_OPEN_RETRY_TIMES, 5);
//
//// 预设置 SDK 的 log 等级， 0-4 分别为 v/d/i/w/e
//        options.setInteger(AVOptions.KEY_LOG_LEVEL, 2);

        // 打开视频时单次 http 请求的超时时间，一次打开过程最多尝试五次,单位为 ms
        // options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        String downloadDirectoryPath = FileUtils.getSaveFolder(StringConstants.VIDEOCACHE).getAbsolutePath();
        options.setString(AVOptions.KEY_CACHE_DIR, downloadDirectoryPath);
        ijkMediaPlayer = new PLMediaPlayer(KJActivityStack.create().topActivity(), options);
        ijkMediaPlayer.setOnPreparedListener(JZPLMediaPlayer.this);
        ijkMediaPlayer.setOnVideoSizeChangedListener(JZPLMediaPlayer.this);
        ijkMediaPlayer.setOnCompletionListener(JZPLMediaPlayer.this);
        ijkMediaPlayer.setOnErrorListener(JZPLMediaPlayer.this);
        ijkMediaPlayer.setOnInfoListener(JZPLMediaPlayer.this);
        ijkMediaPlayer.setOnBufferingUpdateListener(JZPLMediaPlayer.this);
        ijkMediaPlayer.setOnSeekCompleteListener(JZPLMediaPlayer.this);
        //ijkMediaPlayer.setOnTimedTextListener(JZMediaIjkplayer.this);

        try {
            ijkMediaPlayer.setDataSource(currentDataSource.toString());
//            ijkMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            ijkMediaPlayer.setScreenOnWhilePlaying(true);
            ijkMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause() {
        if (ijkMediaPlayer == null) {
            return;
        }
        ijkMediaPlayer.pause();
    }

    @Override
    public boolean isPlaying() {
        return ijkMediaPlayer.isPlaying();
    }

    @Override
    public void seekTo(long time) {
        if (ijkMediaPlayer == null) {
            return;
        }
        ijkMediaPlayer.seekTo(time);
    }

    @Override
    public void release() {
        try {
            if (ijkMediaPlayer != null)
                ijkMediaPlayer.release();
        } catch (NullPointerException e) {

        }
    }

    @Override
    public long getCurrentPosition() {
        try {
            if (ijkMediaPlayer != null) {
                return ijkMediaPlayer.getCurrentPosition();
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
            if (ijkMediaPlayer != null) {
                return ijkMediaPlayer.getDuration();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void setSurface(Surface surface) {
        if (ijkMediaPlayer == null) {
            return;
        }
        ijkMediaPlayer.setSurface(surface);
    }

    @Override
    public void setVolume(float leftVolume, float rightVolume) {
        if (ijkMediaPlayer == null) {
            return;
        }
        ijkMediaPlayer.setVolume(leftVolume, rightVolume);
    }

    @Override
    public void onPrepared(int i) {
        if (ijkMediaPlayer == null) {
            return;
        }
        ijkMediaPlayer.start();
        if (currentDataSource.toString().toLowerCase().contains("mp3")) {
            JZMediaManager.instance().mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (JZVideoPlayerManager.getCurrentJzvd() != null) {
                        JZVideoPlayerManager.getCurrentJzvd().onPrepared();
                    }
                }
            });
        }
    }

    @Override
    public void onVideoSizeChanged(int i, int i1) {
        JZMediaManager.instance().currentVideoWidth = ijkMediaPlayer.getVideoWidth();
        JZMediaManager.instance().currentVideoHeight = ijkMediaPlayer.getVideoHeight();
        JZMediaManager.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManager.getCurrentJzvd() != null) {
                    JZVideoPlayerManager.getCurrentJzvd().onVideoSizeChanged();
                }
            }
        });
    }


    @Override
    public void onCompletion() {
        JZMediaManager.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManager.getCurrentJzvd() != null) {
                    JZVideoPlayerManager.getCurrentJzvd().onAutoCompletion();
                }
            }
        });
    }

    @Override
    public boolean onError(final int i) {
        JZMediaManager.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManager.getCurrentJzvd() != null) {
                    JZVideoPlayerManager.getCurrentJzvd().onError(i, 0);
                }
            }
        });
        return false;
    }

    @Override
    public void onInfo(final int i, final int i1) {
        JZMediaManager.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManager.getCurrentJzvd() != null) {
                    if (i == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                        JZVideoPlayerManager.getCurrentJzvd().onPrepared();
                    } else {
                        JZVideoPlayerManager.getCurrentJzvd().onInfo(i, i1);
                    }
                }
            }
        });
    }

    @Override
    public void onBufferingUpdate(final int i) {
        JZMediaManager.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManager.getCurrentJzvd() != null) {
                    JZVideoPlayerManager.getCurrentJzvd().setBufferProgress(i);
                }
            }
        });
    }

    @Override
    public void onSeekComplete() {
        JZMediaManager.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManager.getCurrentJzvd() != null) {
                    JZVideoPlayerManager.getCurrentJzvd().onSeekComplete();
                }
            }
        });
    }
}
