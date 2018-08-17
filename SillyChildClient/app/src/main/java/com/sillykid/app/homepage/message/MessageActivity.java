package com.sillykid.app.homepage.message;

import android.content.Intent;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.Log;
import com.sillykid.app.R;
import com.sillykid.app.homepage.message.interactivemessage.imuitl.RongIMUtil;

/**
 * 消息
 * Created by Admin on 2017/8/10.
 */

public class MessageActivity extends BaseActivity {

    @BindView(id = R.id.tv_interactiveMessage, click = true)
    private TextView tv_interactiveMessage;

    @BindView(id = R.id.tv_systemMessage, click = true)
    private TextView tv_systemMessage;

    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;

//    @Override
//    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
//        aty = (MainActivity) getActivity();
//        return View.inflate(aty, R.layout.fragment_message, null);
//    }

    @Override
    public void setRootView() {
        setContentView(R.layout.fragment_message);
    }

    @Override
    public void initData() {
        super.initData();
        contentFragment = new InteractiveMessageFragment();
        contentFragment1 = new SystemMessageFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 0);
    }


    @Override
    public void initWidget() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.message), true, R.id.titlebar);
        if (chageIcon == 0) {
            chageIcon = 0;
            cleanColors(0);
            changeFragment(contentFragment);
            RongIMUtil.connectRongIM(this);
        } else if (chageIcon == 1) {
            chageIcon = 1;
            cleanColors(1);
            changeFragment(contentFragment1);
        } else {
            chageIcon = 0;
            cleanColors(0);
            changeFragment(contentFragment);
            RongIMUtil.connectRongIM(this);
        }
    }


    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_interactiveMessage:
                chageIcon = 0;
                cleanColors(0);
                changeFragment(contentFragment);
                break;
            case R.id.tv_systemMessage:
                chageIcon = 1;
                cleanColors(1);
                changeFragment(contentFragment1);
                break;
            default:
                break;
        }
    }

    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
//    @Override
//    public void onChange() {
//        super.onChange();
//
//    }


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
        int newChageIcon = intent.getIntExtra("newChageIcon", 2);
        Log.d("newChageIcon", newChageIcon + "");
        if (newChageIcon == 0) {
            setSimulateClick(tv_interactiveMessage, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(tv_systemMessage, 160, 100);
        } else {
            setSimulateClick(tv_interactiveMessage, 160, 100);
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


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_interactiveMessage.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_systemMessage.setTextColor(getResources().getColor(R.color.titletextcolors));
        if (chageIcon == 0) {
            tv_interactiveMessage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        } else if (chageIcon == 1) {
            tv_systemMessage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        } else {
            tv_interactiveMessage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        }
    }
}