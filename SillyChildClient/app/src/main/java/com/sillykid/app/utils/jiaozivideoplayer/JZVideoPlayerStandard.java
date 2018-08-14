package com.sillykid.app.utils.jiaozivideoplayer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.community.DisplayPageActivity;
import com.sillykid.app.community.dynamic.dynamiccomments.DynamicCommentsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZUserActionStandard;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;

/**
 * Created by Nathen
 * On 2018/08/18 16:15
 */
public class JZVideoPlayerStandard extends JZVideoPlayer implements JZVideoPlayerStandardContract.View {

    protected static Timer DISMISS_CONTROL_VIEW_TIMER;

    public ImageView backButton;
    public ProgressBar bottomProgressBar, loadingProgressBar;
    public TextView titleTextView;
    public ImageView thumbImageView;
    public ImageView tinyBackImageView;
    public LinearLayout batteryTimeLayout;
    public ImageView batteryLevel;
    public TextView videoCurrentTime;
    public TextView replayTextView;
    public TextView clarity;
    public PopupWindow clarityPopWindow;
    public TextView mRetryBtn;
    public LinearLayout mRetryLayout;

    private LinearLayout ll_bottom;
    private LinearLayout ll_zan;
    private ImageView img_zan;
    private TextView tv_zan;
    private TextView tv_zanNum;
    private LinearLayout ll_collection;
    private ImageView img_collection;
    private TextView tv_collectionNum;
    private TextView tv_collection;
    private LinearLayout ll_comment;
    private TextView tv_commentNum;
    private LinearLayout ll_content;
    private TextView tv_content;
    private TextView tv_more1;
    private ImageView img_head;
    private TextView title1;
    private TextView tv_follow;
    private int is_concern = 0;
    private int is_like = 0;
    private int is_collect = 0;
    private int id = 0;
    private int user_id = 0;
    private int type = 1;
    private int type_id = 3;
    private JZVideoPlayerStandardContract.Presenter mPresenter = null;

    protected DismissControlViewTimerTask mDismissControlViewTimerTask;
    protected Dialog mProgressDialog;
    protected ProgressBar mDialogProgressBar;
    protected TextView mDialogSeekTime;
    protected TextView mDialogTotalTime;
    protected ImageView mDialogIcon;
    protected Dialog mVolumeDialog;
    protected ProgressBar mDialogVolumeProgressBar;
    protected TextView mDialogVolumeTextView;
    protected ImageView mDialogVolumeImageView;
    protected Dialog mBrightnessDialog;
    protected ProgressBar mDialogBrightnessProgressBar;
    protected TextView mDialogBrightnessTextView;
    public static long LAST_GET_BATTERYLEVEL_TIME = 0;
    public static int LAST_GET_BATTERYLEVEL_PERCENT = 70;


    private BroadcastReceiver battertReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                int percent = level * 100 / scale;
                LAST_GET_BATTERYLEVEL_PERCENT = percent;
                setBatteryLevel();
                getContext().unregisterReceiver(battertReceiver);
            }
        }
    };

    public JZVideoPlayerStandard(Context context) {
        super(context);
    }

    public JZVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);

        ll_bottom = findViewById(R.id.ll_bottom1);
        ll_zan = findViewById(R.id.ll_zan1);
        img_zan = findViewById(R.id.img_zan1);
        tv_zan = findViewById(R.id.tv_zan1);
        tv_zanNum = findViewById(R.id.tv_zanNum1);
        ll_collection = findViewById(R.id.ll_collection1);
        img_collection = findViewById(R.id.img_collection1);
        tv_collection = findViewById(R.id.tv_collection1);
        tv_collectionNum = findViewById(R.id.tv_collectionNum1);
        ll_comment = findViewById(R.id.ll_comment1);
        tv_commentNum = findViewById(R.id.tv_commentNum1);
        ll_content = findViewById(R.id.ll_content1);
        tv_content = findViewById(R.id.tv_content1);
        tv_more1 = findViewById(R.id.tv_more1);
        img_head = findViewById(R.id.img_head1);
        title1 = findViewById(R.id.title1);
        tv_follow = findViewById(R.id.tv_follow1);

        batteryTimeLayout = findViewById(R.id.battery_time_layout);
        bottomProgressBar = findViewById(R.id.bottom_progress);
        titleTextView = findViewById(R.id.title);
        backButton = findViewById(R.id.back);
        thumbImageView = findViewById(R.id.thumb);
        loadingProgressBar = findViewById(R.id.loading);
        tinyBackImageView = findViewById(R.id.back_tiny);
        batteryLevel = findViewById(R.id.battery_level);
        videoCurrentTime = findViewById(R.id.video_current_time);
        replayTextView = findViewById(R.id.replay_text);
        clarity = findViewById(R.id.clarity);
        mRetryBtn = findViewById(R.id.retry_btn);
        mRetryLayout = findViewById(R.id.retry_layout);

        thumbImageView.setOnClickListener(this);
        backButton.setOnClickListener(this);
        tinyBackImageView.setOnClickListener(this);
        clarity.setOnClickListener(this);
        mRetryBtn.setOnClickListener(this);
    }

    public void setUp(Object[] dataSourceObjects, int defaultUrlMapIndex, int screen, Object... objects) {
        super.setUp(dataSourceObjects, defaultUrlMapIndex, screen, objects);
        if (objects.length != 0) titleTextView.setText(objects[0].toString());
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            fullscreenButton.setImageResource(cn.jzvd.R.drawable.jz_shrink);
            backButton.setVisibility(View.VISIBLE);
            img_head.setVisibility(View.VISIBLE);
            title1.setVisibility(View.VISIBLE);
            tv_follow.setVisibility(View.VISIBLE);
            tinyBackImageView.setVisibility(View.INVISIBLE);
            batteryTimeLayout.setVisibility(View.GONE);
            if (((LinkedHashMap) dataSourceObjects[0]).size() == 1) {
                clarity.setVisibility(GONE);
            } else {
                clarity.setText(JZUtils.getKeyFromDataSource(dataSourceObjects, currentUrlMapIndex));
                clarity.setVisibility(View.VISIBLE);
            }
            changeStartButtonSize((int) getResources().getDimension(cn.jzvd.R.dimen.jz_start_button_w_h_fullscreen));
            setView(objects);
        } else if (currentScreen == SCREEN_WINDOW_NORMAL
                || currentScreen == SCREEN_WINDOW_LIST) {
            fullscreenButton.setImageResource(cn.jzvd.R.drawable.jz_enlarge);
            backButton.setVisibility(View.GONE);
            img_head.setVisibility(View.GONE);
            title1.setVisibility(View.GONE);
            tv_follow.setVisibility(View.GONE);
            tinyBackImageView.setVisibility(View.INVISIBLE);
            changeStartButtonSize((int) getResources().getDimension(cn.jzvd.R.dimen.jz_start_button_w_h_normal));
            batteryTimeLayout.setVisibility(View.GONE);
            clarity.setVisibility(View.GONE);
        } else if (currentScreen == SCREEN_WINDOW_TINY) {
            tinyBackImageView.setVisibility(View.VISIBLE);
            setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                    View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
            batteryTimeLayout.setVisibility(View.GONE);
            clarity.setVisibility(View.GONE);
        }
        setSystemTimeAndBattery();

        if (tmp_test_back) {
            tmp_test_back = false;
            JZVideoPlayerManager.setFirstFloor(this);
            backPress();
        }
    }

    private void setView(Object... objects) {
        mPresenter = new JZVideoPlayerStandardPresenter(this);
        ll_collection = findViewById(R.id.ll_collection1);
        img_collection = findViewById(R.id.img_collection1);
        tv_collection = findViewById(R.id.tv_collection1);
        GlideImageLoader.glideLoader(getContext(), objects[1].toString(), img_head, 0, R.mipmap.avatar);
        title1.setText(objects[2].toString());
        if (objects.length != 0 && StringUtils.toInt(objects[3].toString(), -1) == 0) {
            tv_follow.setVisibility(View.VISIBLE);
            is_concern = 0;
            tv_follow.setText(getResources().getString(R.string.follow));
            tv_follow.setBackgroundResource(R.drawable.shape_followdd);
            tv_follow.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (objects.length != 0 && StringUtils.toInt(objects[3].toString(), -1) == 1) {
            tv_follow.setVisibility(View.VISIBLE);
            is_concern = 1;
            tv_follow.setText(getResources().getString(R.string.followed));
            tv_follow.setBackgroundResource(R.drawable.shape_followed1);
            tv_follow.setTextColor(getResources().getColor(R.color.whiteColors));
        } else {
            tv_follow.setVisibility(View.GONE);
        }
        tv_follow.setOnClickListener(this);
        tv_more1.setOnClickListener(this);
        tv_content.setText(objects[4].toString());
        is_like = StringUtils.toInt(objects[5].toString(), 0);
        if (is_like == 1) {
            img_zan.setImageResource(R.mipmap.dynamicdetails_zan1);
            tv_zan.setTextColor(getResources().getColor(R.color.greenColors));
            tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
        } else {
            img_zan.setImageResource(R.mipmap.dynamicdetails_zan);
            tv_zan.setTextColor(getResources().getColor(R.color.textColor));
            tv_zanNum.setTextColor(getResources().getColor(R.color.textColor));
        }
        tv_zanNum.setText(objects[6].toString());
        img_head.setOnClickListener(this);
        title1.setOnClickListener(this);
        ll_zan.setOnClickListener(this);
        tv_collectionNum.setText(objects[8].toString());
        is_collect = StringUtils.toInt(objects[7].toString(), 0);
        if (is_collect == 1) {
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection1);
            tv_collection.setTextColor(getResources().getColor(R.color.greenColors));
            tv_collectionNum.setTextColor(getResources().getColor(R.color.greenColors));
        } else {
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection);
            tv_collection.setTextColor(getResources().getColor(R.color.textColor));
            tv_collectionNum.setTextColor(getResources().getColor(R.color.textColor));
        }
        ll_collection.setOnClickListener(this);
        tv_commentNum.setText(objects[9].toString());
        ll_comment.setOnClickListener(this);
        user_id = StringUtils.toInt(objects[10].toString(), 0);
        type = StringUtils.toInt(objects[11].toString(), 1);
        id = StringUtils.toInt(objects[12].toString(), 0);
        type_id = StringUtils.toInt(objects[13].toString(), 3);
    }


    public void changeStartButtonSize(int size) {
        ViewGroup.LayoutParams lp = startButton.getLayoutParams();
        lp.height = size;
        lp.width = size;
        lp = loadingProgressBar.getLayoutParams();
        lp.height = size;
        lp.width = size;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_new_standard;
    }

    @Override
    public void onStateNormal() {
        super.onStateNormal();
        changeUiToNormal();
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
        changeUiToPreparing();
    }

    @Override
    public void onStatePreparingChangingUrl(int urlMapIndex, long seekToInAdvance) {
        super.onStatePreparingChangingUrl(urlMapIndex, seekToInAdvance);
        loadingProgressBar.setVisibility(VISIBLE);
        startButton.setVisibility(INVISIBLE);
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
        changeUiToPlayingClear();
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
        changeUiToPauseShow();
        cancelDismissControlViewTimer();
    }

    @Override
    public void onStateError() {
        super.onStateError();
        changeUiToError();
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        changeUiToComplete();
        cancelDismissControlViewTimer();
        bottomProgressBar.setProgress(100);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();
        if (id == R.id.surface_container) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    startDismissControlViewTimer();
                    if (mChangePosition) {
                        long duration = getDuration();
                        int progress = (int) (mSeekTimePosition * 100 / (duration == 0 ? 1 : duration));
                        bottomProgressBar.setProgress(progress);
                    }
                    if (!mChangePosition && !mChangeVolume) {
                        onEvent(JZUserActionStandard.ON_CLICK_BLANK);
                        onClickUiToggle();
                    }
                    break;
            }
        } else if (id == R.id.bottom_seek_progress) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    cancelDismissControlViewTimer();
                    break;
                case MotionEvent.ACTION_UP:
                    startDismissControlViewTimer();
                    break;
            }
        }
        return super.onTouch(v, event);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.thumb) {
            if (dataSourceObjects == null || JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex) == null) {
                Toast.makeText(getContext(), getResources().getString(R.string.no_url), Toast.LENGTH_SHORT).show();
                return;
            }
            if (currentState == CURRENT_STATE_NORMAL) {
                if (!JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex).toString().startsWith("file") &&
                        !JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex).toString().startsWith("/") &&
                        !JZUtils.isWifiConnected(getContext()) && !WIFI_TIP_DIALOG_SHOWED) {
                    showWifiDialog();
                    return;
                }
                startVideo();
                onEvent(JZUserActionStandard.ON_CLICK_START_THUMB);//开始的事件应该在播放之后，此处特殊
            } else if (currentState == CURRENT_STATE_AUTO_COMPLETE) {
                onClickUiToggle();
            }
        } else if (i == R.id.surface_container) {
            startDismissControlViewTimer();
        } else if (i == R.id.back || i == R.id.tv_more1) {
            backPress();
        } else if (i == R.id.tv_follow) {
            mPresenter.postAddConcern(user_id, type);
        } else if (i == R.id.img_head || i == R.id.title1) {
            Intent intent1 = new Intent(getContext(), DisplayPageActivity.class);
            intent1.putExtra("user_id", user_id);
            intent1.putExtra("isRefresh", 0);
            getContext().startActivity(intent1);
        } else if (i == R.id.ll_zan1) {
            mPresenter.postAddLike(id, type);
        } else if (i == R.id.ll_collection1) {
            if (is_collect == 1) {
                mPresenter.postUnfavorite(id, type_id);
            } else {
                mPresenter.postAddFavorite(id, type_id);
            }
        } else if (i == R.id.ll_comment1) {
            Intent intent1 = new Intent(getContext(), DynamicCommentsActivity.class);
            intent1.putExtra("id", id);
            intent1.putExtra("type", type);
            getContext().startActivity(intent1);
        } else if (i == R.id.back_tiny) {
            if (JZVideoPlayerManager.getFirstFloor().currentScreen == JZVideoPlayer.SCREEN_WINDOW_LIST) {
                quitFullscreenOrTinyWindow();
            } else {
                backPress();
            }
        } else if (i == R.id.clarity) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout layout = (LinearLayout) inflater.inflate(cn.jzvd.R.layout.jz_layout_clarity, null);

            OnClickListener mQualityListener = new OnClickListener() {
                public void onClick(View v) {
                    int index = (int) v.getTag();
                    onStatePreparingChangingUrl(index, getCurrentPositionWhenPlaying());
                    clarity.setText(JZUtils.getKeyFromDataSource(dataSourceObjects, currentUrlMapIndex));
                    for (int j = 0; j < layout.getChildCount(); j++) {//设置点击之后的颜色
                        if (j == currentUrlMapIndex) {
                            ((TextView) layout.getChildAt(j)).setTextColor(Color.parseColor("#fff85959"));
                        } else {
                            ((TextView) layout.getChildAt(j)).setTextColor(Color.parseColor("#ffffff"));
                        }
                    }
                    if (clarityPopWindow != null) {
                        clarityPopWindow.dismiss();
                    }
                }
            };

            for (int j = 0; j < ((LinkedHashMap) dataSourceObjects[0]).size(); j++) {
                String key = JZUtils.getKeyFromDataSource(dataSourceObjects, j);
                TextView clarityItem = (TextView) View.inflate(getContext(), cn.jzvd.R.layout.jz_layout_clarity_item, null);
                clarityItem.setText(key);
                clarityItem.setTag(j);
                layout.addView(clarityItem, j);
                clarityItem.setOnClickListener(mQualityListener);
                if (j == currentUrlMapIndex) {
                    clarityItem.setTextColor(Color.parseColor("#fff85959"));
                }
            }

            clarityPopWindow = new PopupWindow(layout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
            clarityPopWindow.setContentView(layout);
            clarityPopWindow.showAsDropDown(clarity);
            layout.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int offsetX = clarity.getMeasuredWidth() / 3;
            int offsetY = clarity.getMeasuredHeight() / 3;
            clarityPopWindow.update(clarity, -offsetX, -offsetY, Math.round(layout.getMeasuredWidth() * 2), layout.getMeasuredHeight());
        } else if (i == R.id.retry_btn) {
            if (dataSourceObjects == null || JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex) == null) {
                Toast.makeText(getContext(), getResources().getString(cn.jzvd.R.string.no_url), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex).toString().startsWith("file") && !
                    JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex).toString().startsWith("/") &&
                    !JZUtils.isWifiConnected(getContext()) && !WIFI_TIP_DIALOG_SHOWED) {
                showWifiDialog();
                return;
            }
            initTextureView();//和开始播放的代码重复
            addTextureView();
            JZMediaManager.setDataSource(dataSourceObjects);
            JZMediaManager.setCurrentDataSource(JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex));
            onStatePreparing();
            onEvent(JZUserAction.ON_CLICK_START_ERROR);
        }
    }

    @Override
    public void showWifiDialog() {
        super.showWifiDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getResources().getString(cn.jzvd.R.string.tips_not_wifi));
        builder.setPositiveButton(getResources().getString(cn.jzvd.R.string.tips_not_wifi_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                onEvent(JZUserActionStandard.ON_CLICK_START_WIFIDIALOG);
                startVideo();
                WIFI_TIP_DIALOG_SHOWED = true;
            }
        });
        builder.setNegativeButton(getResources().getString(cn.jzvd.R.string.tips_not_wifi_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                clearFloatScreen();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        super.onStartTrackingTouch(seekBar);
        cancelDismissControlViewTimer();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        super.onStopTrackingTouch(seekBar);
        if (currentState == CURRENT_STATE_PLAYING) {
            dissmissControlView();
        } else {
            startDismissControlViewTimer();
        }
    }

    public void onClickUiToggle() {
        if (bottomContainer.getVisibility() != View.VISIBLE) {
            setSystemTimeAndBattery();
            clarity.setText(JZUtils.getKeyFromDataSource(dataSourceObjects, currentUrlMapIndex));
        }
        if (currentState == CURRENT_STATE_PREPARING) {
            changeUiToPreparing();
            if (bottomContainer.getVisibility() == View.VISIBLE) {
            } else {
                setSystemTimeAndBattery();
            }
        } else if (currentState == CURRENT_STATE_PLAYING) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPlayingClear();
            } else {
                changeUiToPlayingShow();
            }
        } else if (currentState == CURRENT_STATE_PAUSE) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPauseClear();
            } else {
                changeUiToPauseShow();
            }
        }
    }

    public void setSystemTimeAndBattery() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        videoCurrentTime.setText(dateFormater.format(date));
        if ((System.currentTimeMillis() - LAST_GET_BATTERYLEVEL_TIME) > 30000) {
            LAST_GET_BATTERYLEVEL_TIME = System.currentTimeMillis();
            getContext().registerReceiver(
                    battertReceiver,
                    new IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            );
        } else {
            setBatteryLevel();
        }
    }

    public void setBatteryLevel() {
        int percent = LAST_GET_BATTERYLEVEL_PERCENT;
        if (percent < 15) {
            batteryLevel.setBackgroundResource(cn.jzvd.R.drawable.jz_battery_level_10);
        } else if (percent >= 15 && percent < 40) {
            batteryLevel.setBackgroundResource(cn.jzvd.R.drawable.jz_battery_level_30);
        } else if (percent >= 40 && percent < 60) {
            batteryLevel.setBackgroundResource(cn.jzvd.R.drawable.jz_battery_level_50);
        } else if (percent >= 60 && percent < 80) {
            batteryLevel.setBackgroundResource(cn.jzvd.R.drawable.jz_battery_level_70);
        } else if (percent >= 80 && percent < 95) {
            batteryLevel.setBackgroundResource(cn.jzvd.R.drawable.jz_battery_level_90);
        } else if (percent >= 95 && percent <= 100) {
            batteryLevel.setBackgroundResource(cn.jzvd.R.drawable.jz_battery_level_100);
        }
    }

    public void onCLickUiToggleToClear() {
        if (currentState == CURRENT_STATE_PREPARING) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPreparing();
            } else {
            }
        } else if (currentState == CURRENT_STATE_PLAYING) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPlayingClear();
            } else {
            }
        } else if (currentState == CURRENT_STATE_PAUSE) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPauseClear();
            } else {
            }
        } else if (currentState == CURRENT_STATE_AUTO_COMPLETE) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToComplete();
            } else {
            }
        }
    }

    @Override
    public void setProgressAndText(int progress, long position, long duration) {
        super.setProgressAndText(progress, position, duration);
        if (progress != 0) bottomProgressBar.setProgress(progress);
    }

    @Override
    public void setBufferProgress(int bufferProgress) {
        super.setBufferProgress(bufferProgress);
        if (bufferProgress != 0) bottomProgressBar.setSecondaryProgress(bufferProgress);
    }

    @Override
    public void resetProgressAndTime() {
        super.resetProgressAndTime();
        bottomProgressBar.setProgress(0);
        bottomProgressBar.setSecondaryProgress(0);
    }

    public void changeUiToNormal() {
        switch (currentScreen) {
            case SCREEN_WINDOW_NORMAL:
            case SCREEN_WINDOW_LIST:
                setAllControlsVisiblity(View.VISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                ll_bottom.setVisibility(View.GONE);
                ll_content.setVisibility(View.GONE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }
    }

    public void changeUiToPreparing() {
        switch (currentScreen) {
            case SCREEN_WINDOW_NORMAL:
            case SCREEN_WINDOW_LIST:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                ll_bottom.setVisibility(View.GONE);
                ll_content.setVisibility(View.GONE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToPlayingShow() {
        switch (currentScreen) {
            case SCREEN_WINDOW_NORMAL:
            case SCREEN_WINDOW_LIST:
                setAllControlsVisiblity(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                ll_bottom.setVisibility(View.GONE);
                ll_content.setVisibility(View.GONE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToPlayingClear() {
        switch (currentScreen) {
            case SCREEN_WINDOW_NORMAL:
            case SCREEN_WINDOW_LIST:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                ll_bottom.setVisibility(View.GONE);
                ll_content.setVisibility(View.GONE);
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToPauseShow() {
        switch (currentScreen) {
            case SCREEN_WINDOW_NORMAL:
            case SCREEN_WINDOW_LIST:
                setAllControlsVisiblity(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                ll_bottom.setVisibility(View.GONE);
                ll_content.setVisibility(View.GONE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }
    }

    public void changeUiToPauseClear() {
        switch (currentScreen) {
            case SCREEN_WINDOW_NORMAL:
            case SCREEN_WINDOW_LIST:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                ll_bottom.setVisibility(View.GONE);
                ll_content.setVisibility(View.GONE);
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToComplete() {
        switch (currentScreen) {
            case SCREEN_WINDOW_NORMAL:
            case SCREEN_WINDOW_LIST:
                setAllControlsVisiblity(View.VISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                ll_bottom.setVisibility(View.GONE);
                ll_content.setVisibility(View.GONE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void changeUiToError() {
        switch (currentScreen) {
            case SCREEN_WINDOW_NORMAL:
            case SCREEN_WINDOW_LIST:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                ll_bottom.setVisibility(View.GONE);
                ll_content.setVisibility(View.GONE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                updateStartImage();
                break;
            case SCREEN_WINDOW_TINY:
                break;
        }

    }

    public void setAllControlsVisiblity(int topCon, int bottomCon, int startBtn, int loadingPro,
                                        int thumbImg, int bottomPro, int retryLayout) {
        topContainer.setVisibility(topCon);
        bottomContainer.setVisibility(bottomCon);
        startButton.setVisibility(startBtn);
        ll_bottom.setVisibility(topCon);
        ll_content.setVisibility(topCon);
        loadingProgressBar.setVisibility(loadingPro);
        thumbImageView.setVisibility(thumbImg);
        bottomProgressBar.setVisibility(bottomPro);
        mRetryLayout.setVisibility(retryLayout);
    }

    public void updateStartImage() {
        if (currentState == CURRENT_STATE_PLAYING) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(cn.jzvd.R.drawable.jz_click_pause_selector);
            replayTextView.setVisibility(INVISIBLE);
        } else if (currentState == CURRENT_STATE_ERROR) {
            startButton.setVisibility(INVISIBLE);
            replayTextView.setVisibility(INVISIBLE);
        } else if (currentState == CURRENT_STATE_AUTO_COMPLETE) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(cn.jzvd.R.drawable.jz_click_replay_selector);
            replayTextView.setVisibility(VISIBLE);
        } else {
            startButton.setImageResource(cn.jzvd.R.drawable.jz_click_play_selector);
            replayTextView.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void showProgressDialog(float deltaX, String seekTime, long seekTimePosition, String totalTime, long totalTimeDuration) {
        super.showProgressDialog(deltaX, seekTime, seekTimePosition, totalTime, totalTimeDuration);
        if (mProgressDialog == null) {
            View localView = LayoutInflater.from(getContext()).inflate(cn.jzvd.R.layout.jz_dialog_progress, null);
            mDialogProgressBar = localView.findViewById(cn.jzvd.R.id.duration_progressbar);
            mDialogSeekTime = localView.findViewById(cn.jzvd.R.id.tv_current);
            mDialogTotalTime = localView.findViewById(cn.jzvd.R.id.tv_duration);
            mDialogIcon = localView.findViewById(cn.jzvd.R.id.duration_image_tip);
            mProgressDialog = createDialogWithView(localView);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }

        mDialogSeekTime.setText(seekTime);
        mDialogTotalTime.setText(" / " + totalTime);
        mDialogProgressBar.setProgress(totalTimeDuration <= 0 ? 0 : (int) (seekTimePosition * 100 / totalTimeDuration));
        if (deltaX > 0) {
            mDialogIcon.setBackgroundResource(cn.jzvd.R.drawable.jz_forward_icon);
        } else {
            mDialogIcon.setBackgroundResource(cn.jzvd.R.drawable.jz_backward_icon);
        }
        onCLickUiToggleToClear();
    }

    @Override
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showVolumeDialog(float deltaY, int volumePercent) {
        super.showVolumeDialog(deltaY, volumePercent);
        if (mVolumeDialog == null) {
            View localView = LayoutInflater.from(getContext()).inflate(cn.jzvd.R.layout.jz_dialog_volume, null);
            mDialogVolumeImageView = localView.findViewById(cn.jzvd.R.id.volume_image_tip);
            mDialogVolumeTextView = localView.findViewById(cn.jzvd.R.id.tv_volume);
            mDialogVolumeProgressBar = localView.findViewById(cn.jzvd.R.id.volume_progressbar);
            mVolumeDialog = createDialogWithView(localView);
        }
        if (!mVolumeDialog.isShowing()) {
            mVolumeDialog.show();
        }
        if (volumePercent <= 0) {
            mDialogVolumeImageView.setBackgroundResource(cn.jzvd.R.drawable.jz_close_volume);
        } else {
            mDialogVolumeImageView.setBackgroundResource(cn.jzvd.R.drawable.jz_add_volume);
        }
        if (volumePercent > 100) {
            volumePercent = 100;
        } else if (volumePercent < 0) {
            volumePercent = 0;
        }
        mDialogVolumeTextView.setText(volumePercent + "%");
        mDialogVolumeProgressBar.setProgress(volumePercent);
        onCLickUiToggleToClear();
    }

    @Override
    public void dismissVolumeDialog() {
        super.dismissVolumeDialog();
        if (mVolumeDialog != null) {
            mVolumeDialog.dismiss();
        }
    }

    @Override
    public void showBrightnessDialog(int brightnessPercent) {
        super.showBrightnessDialog(brightnessPercent);
        if (mBrightnessDialog == null) {
            View localView = LayoutInflater.from(getContext()).inflate(cn.jzvd.R.layout.jz_dialog_brightness, null);
            mDialogBrightnessTextView = localView.findViewById(cn.jzvd.R.id.tv_brightness);
            mDialogBrightnessProgressBar = localView.findViewById(cn.jzvd.R.id.brightness_progressbar);
            mBrightnessDialog = createDialogWithView(localView);
        }
        if (!mBrightnessDialog.isShowing()) {
            mBrightnessDialog.show();
        }
        if (brightnessPercent > 100) {
            brightnessPercent = 100;
        } else if (brightnessPercent < 0) {
            brightnessPercent = 0;
        }
        mDialogBrightnessTextView.setText(brightnessPercent + "%");
        mDialogBrightnessProgressBar.setProgress(brightnessPercent);
        onCLickUiToggleToClear();
    }

    @Override
    public void dismissBrightnessDialog() {
        super.dismissBrightnessDialog();
        if (mBrightnessDialog != null) {
            mBrightnessDialog.dismiss();
        }
    }

    public Dialog createDialogWithView(View localView) {
        Dialog dialog = new Dialog(getContext(), cn.jzvd.R.style.jz_style_dialog_progress);
        dialog.setContentView(localView);
        Window window = dialog.getWindow();
        window.addFlags(Window.FEATURE_ACTION_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        window.setLayout(-2, -2);
        WindowManager.LayoutParams localLayoutParams = window.getAttributes();
        localLayoutParams.gravity = Gravity.CENTER;
        window.setAttributes(localLayoutParams);
        return dialog;
    }

    public void startDismissControlViewTimer() {
        cancelDismissControlViewTimer();
        DISMISS_CONTROL_VIEW_TIMER = new Timer();
        mDismissControlViewTimerTask = new DismissControlViewTimerTask();
        DISMISS_CONTROL_VIEW_TIMER.schedule(mDismissControlViewTimerTask, 2500);
    }

    public void cancelDismissControlViewTimer() {
        if (DISMISS_CONTROL_VIEW_TIMER != null) {
            DISMISS_CONTROL_VIEW_TIMER.cancel();
        }
        if (mDismissControlViewTimerTask != null) {
            mDismissControlViewTimerTask.cancel();
        }

    }

    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        cancelDismissControlViewTimer();
    }

    @Override
    public void onCompletion() {
        super.onCompletion();
        cancelDismissControlViewTimer();
        if (clarityPopWindow != null) {
            clarityPopWindow.dismiss();
        }
    }

    public void dissmissControlView() {
        if (currentState != CURRENT_STATE_NORMAL
                && currentState != CURRENT_STATE_ERROR
                && currentState != CURRENT_STATE_AUTO_COMPLETE) {
            post(new Runnable() {
                @Override
                public void run() {
                    bottomContainer.setVisibility(View.INVISIBLE);
                    topContainer.setVisibility(View.VISIBLE);
                    startButton.setVisibility(View.INVISIBLE);
                    if (clarityPopWindow != null) {
                        clarityPopWindow.dismiss();
                    }
                    if (currentScreen != SCREEN_WINDOW_TINY) {
                        bottomProgressBar.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    @Override
    public void setPresenter(JZVideoPlayerStandardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            if (is_concern == 1) {
                is_concern = 0;
                tv_follow.setText(getResources().getString(R.string.follow));
                tv_follow.setBackgroundResource(R.drawable.shape_followdd);
                tv_follow.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getResources().getString(R.string.focusSuccess));
                /**
                 * 发送消息
                 */
                RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsUnFocusEvent"));
            } else {
                is_concern = 1;
                tv_follow.setText(getResources().getString(R.string.followed));
                tv_follow.setBackgroundResource(R.drawable.shape_followed1);
                tv_follow.setTextColor(getResources().getColor(R.color.whiteColors));
                ViewInject.toast(getResources().getString(R.string.attentionSuccess));
                /**
                 * 发送消息
                 */
                RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsFocusEvent"));
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            dismissLoadingDialog();
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection);
            tv_collection.setTextColor(getResources().getColor(R.color.textColor));
            tv_collectionNum.setTextColor(getResources().getColor(R.color.textColor));
            is_collect = 0;
            tv_collectionNum.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) - 1 + "");
            ViewInject.toast(getResources().getString(R.string.uncollectible));
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsUnCollectionEvent"));
        } else if (flag == 2) {
            dismissLoadingDialog();
            is_collect = 1;
            img_collection.setImageResource(R.mipmap.dynamicdetails_collection1);
            tv_collection.setTextColor(getResources().getColor(R.color.greenColors));
            tv_collectionNum.setText(StringUtils.toInt(tv_collectionNum.getText().toString(), 0) + 1 + "");
            tv_collectionNum.setTextColor(getResources().getColor(R.color.greenColors));
            ViewInject.toast(getResources().getString(R.string.collectionSuccess));
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsCollectionEvent"));
        } else if (flag == 3) {
            if (is_like == 1) {
                is_like = 0;
                tv_zanNum.setText(StringUtils.toInt(tv_zanNum.getText().toString(), 0) - 1 + "");
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan);
                tv_zan.setTextColor(getResources().getColor(R.color.textColor));
                tv_zanNum.setTextColor(getResources().getColor(R.color.textColor));
                ViewInject.toast(getResources().getString(R.string.cancelZanSuccess));
                /**
                 * 发送消息
                 */
                RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsCancelZanEvent"));
            } else {
                is_like = 1;
                tv_zanNum.setText(StringUtils.toInt(tv_zanNum.getText().toString(), 0) + 1 + "");
                img_zan.setImageResource(R.mipmap.dynamicdetails_zan1);
                tv_zan.setTextColor(getResources().getColor(R.color.greenColors));
                tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getResources().getString(R.string.zanSuccess));
                /**
                 * 发送消息
                 */
                RxBus.getInstance().post(new MsgEvent<String>("RxBusDynamicDetailsZanEvent"));
            }
            dismissLoadingDialog();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (isLogin(msg)) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            getContext().startActivity(intent);
        } else {
            ViewInject.toast(msg);
        }
    }

    public boolean isLogin(String mag) {
        if (StringUtils.isEmpty(mag) || mag != null && mag.equals("-10001")) {
            return true;
        }
        return false;
    }

    @Override
    public void showLoadingDialog(String title) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    public class DismissControlViewTimerTask extends TimerTask {

        @Override
        public void run() {
            dissmissControlView();
        }
    }
}
