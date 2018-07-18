package com.sillykid.app.main;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.constant.StringNewConstants;
import com.sillykid.app.message.interactivemessage.imuitl.RongCloudEvent;
import com.sillykid.app.message.interactivemessage.imuitl.UserUtil;
import com.sillykid.app.receivers.MainCallBack;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.receivers.MainReceiver;
import com.sillykid.app.services.MainService;
import com.tencent.bugly.beta.Beta;
import com.umeng.analytics.MobclickAgent;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;

import static com.sillykid.app.constant.StringNewConstants.KEY_EXTRAS;
import static com.sillykid.app.constant.StringNewConstants.KEY_MESSAGE;
import static com.sillykid.app.constant.StringNewConstants.MESSAGE_RECEIVED_ACTION;

@SuppressWarnings("deprecation")
public class MainActivity extends BaseActivity implements MainContract.View, MainCallBack {

    @BindView(id = R.id.bottombar_homePage, click = true)
    private LinearLayout bottombar_homePage;

    @BindView(id = R.id.img_homePage)
    private ImageView img_homePage;

    @BindView(id = R.id.tv_homePage)
    private TextView tv_homePage;

    @BindView(id = R.id.bottombar_message, click = true)
    private RelativeLayout bottombar_message;

    @BindView(id = R.id.tv_messageTag)
    public TextView tv_messageTag;

    @BindView(id = R.id.img_message)
    private ImageView img_message;

    @BindView(id = R.id.tv_message)
    private TextView tv_message;

    @BindView(id = R.id.bottombar_activities, click = true)
    private LinearLayout bottombar_activities;

    @BindView(id = R.id.img_activities)
    private ImageView img_activities;

    @BindView(id = R.id.tv_activities)
    private TextView tv_activities;


    @BindView(id = R.id.bottombar_mine, click = true)
    private LinearLayout bottombar_mine;

    @BindView(id = R.id.img_mine)
    private ImageView img_mine;

    @BindView(id = R.id.tv_mine)
    private TextView tv_mine;


    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;
    private BaseFragment contentFragment2;
    private BaseFragment contentFragment3;
    private long firstTime = 0;


    private MessageReceiver mMessageReceiver;

    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon;

    private Intent intentservice;
    private MainReceiver mainReceiver;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MainPresenter(this);
        contentFragment = new MallHomePageFragment();
        contentFragment1 = new MessageFragment();
        contentFragment2 = new ActivitiesFragment();
        contentFragment3 = new MineFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
        registerMessageReceiver();  //   极光推送 used for receive msg
        ((MainContract.Presenter) mPresenter).getChatManagerListener();

        mainReceiver = new MainReceiver(this);
        IntentFilter intentFilter = new IntentFilter(StringNewConstants.MainServiceAction);
        registerReceiver(mainReceiver, intentFilter);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initColors();
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }

    /**
     * Activity的启动模式变为singleTask
     * 调用onNewIntent(Intent intent)方法。
     * Fragment调用的时候，一定要在onResume方法中。
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int newChageIcon = intent.getIntExtra("newChageIcon", 4);
        Log.d("newChageIcon", newChageIcon + "");
        if (newChageIcon == 0) {
            setSimulateClick(bottombar_homePage, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(bottombar_message, 160, 100);
        } else if (newChageIcon == 2) {
            setSimulateClick(bottombar_activities, 160, 100);
        } else if (newChageIcon == 3) {
        }
    }

    /**
     * 模拟点击
     *
     * @param view
     * @param x
     * @param y
     */
    private void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.bottombar_homePage:
                cleanColors(0);
                break;
            case R.id.bottombar_message:
                ((MainContract.Presenter) mPresenter).getIsLogin(this, 0);
                break;
            case R.id.bottombar_activities:
                cleanColors(2);
                break;
            case R.id.bottombar_mine:
                cleanColors(3);
                break;
            default:
                break;
        }
    }


    /**
     * 退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    //如果两次按键时间间隔大于2秒，则不退出
//                    Toast.makeText(this,"111111",Toast.LENGTH_SHORT).show();
                    ViewInject.toast(this, getString(R.string.clickAgainExitProgram));
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {
                    //  int i = 1 / 0;
                    //   KjBitmapUtil.getInstance().getKjBitmap().cleanCache();
                    MobclickAgent.onProfileSignOff();//关闭账号统计     退出登录也加
                    JPushInterface.stopCrashHandler(getApplication());//JPush关闭CrashLog上报
                    MobclickAgent.onKillProcess(aty);
                    if (!StringUtils.isEmpty(UserUtil.getResTokenInfo(this))) {
                        //在mainActivity中是否需要重新注册消息数量监听， 只有被挤出融云后才需要
                        //清除融云信息，退出登陆
                        RongIM.getInstance().logout();
                    }
                    Beta.unInit();
                    RongCloudEvent.getInstance().removeUnReadMessageCountChangedObserver();
                    //第一个参数为是否解绑推送的devicetoken
                    KJActivityStack.create().appExit(aty);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
        //极光推送 定制声音、震动、闪灯等 Notification 样式。
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MainActivity.this);
//        builder.statusBarDrawable = R.mipmap.ic_launcher;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder);
    }


    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("JPush", "JPush1");
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!StringUtils.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                Log.d("JPush", "JPush");
                //   mRefreshLayout.beginRefreshing();
                //  setCostomMsg(showMsg.toString());
            }
        }
    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int position) {
        if (position != chageIcon) {
            switch (chageIcon) {
                case 0:
                    img_homePage.setImageResource(R.mipmap.tab_home);
                    tv_homePage.setTextColor(getResources().getColor(R.color.textColor));
                    break;
                case 1:
                    img_message.setImageResource(R.mipmap.tab_message);
                    tv_message.setTextColor(getResources().getColor(R.color.textColor));
                    break;
                case 2:
                    img_activities.setImageResource(R.mipmap.tab_activity);
                    tv_activities.setTextColor(getResources().getColor(R.color.textColor));
                    break;
                case 3:
                    img_mine.setImageResource(R.mipmap.tab_personal);
                    tv_mine.setTextColor(getResources().getColor(R.color.textColor));
                    break;
            }
            chageIcon = position;
            switch (chageIcon) {
                case 0:
                    img_homePage.setImageResource(R.mipmap.tab_home_selected);
                    tv_homePage.setTextColor(getResources().getColor(R.color.ea73Colors));
                    changeFragment(contentFragment);
                    break;
                case 1:
                    img_message.setImageResource(R.mipmap.tab_message_selected);
                    tv_message.setTextColor(getResources().getColor(R.color.ffe206Colors));
                    changeFragment(contentFragment1);
                    break;
                case 2:
                    img_activities.setImageResource(R.mipmap.tab_activity_selected);
                    tv_activities.setTextColor(getResources().getColor(R.color.f3516dColors));
                    changeFragment(contentFragment2);
                    break;
                case 3:
                    img_mine.setImageResource(R.mipmap.tab_personal_selected);
                    tv_mine.setTextColor(getResources().getColor(R.color.e9e5Colors));
                    changeFragment(contentFragment3);
                    break;
            }
        }

    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void initColors() {
        switch (chageIcon) {
            case 0:
                img_homePage.setImageResource(R.mipmap.tab_home_selected);
                tv_homePage.setTextColor(getResources().getColor(R.color.greenColors));
                changeFragment(contentFragment);
                break;
            case 1:
                img_message.setImageResource(R.mipmap.tab_message_selected);
                tv_message.setTextColor(getResources().getColor(R.color.greenColors));
                changeFragment(contentFragment1);
                break;
            case 2:
                img_activities.setImageResource(R.mipmap.tab_activity_selected);
                tv_activities.setTextColor(getResources().getColor(R.color.greenColors));
                changeFragment(contentFragment2);
                break;
            case 3:
                img_mine.setImageResource(R.mipmap.tab_personal_selected);
                tv_mine.setTextColor(getResources().getColor(R.color.greenColors));
                changeFragment(contentFragment3);
                break;
        }

    }


    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            cleanColors(1);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        intentservice = new Intent(MainActivity.this, MainService.class);
        startService(intentservice);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 0 && isLogin(msg) || flag == 1 && isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
        } else if (flag == 0) {
            cleanColors(1);
        }
    }

    @Override
    public void msgStyle(boolean havemsg) {
        if (havemsg) {
            tv_messageTag.setVisibility(View.VISIBLE);
        } else {
            tv_messageTag.setVisibility(View.GONE);
        }
    }

    public int getChageIcon() {
        return chageIcon;
    }

    @Override
    protected void onDestroy() {
        if (mMessageReceiver != null) {
            unregisterReceiver(mMessageReceiver);
            mMessageReceiver = null;
        }
        if (mainReceiver != null) {
            unregisterReceiver(mainReceiver);
            mainReceiver = null;
        }
        if (intentservice != null) {
            stopService(intentservice);
            intentservice = null;
        }
        super.onDestroy();
    }
}
