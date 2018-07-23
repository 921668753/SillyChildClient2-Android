package com.sillykid.app.mine.myrelease;

import android.content.Intent;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.Log;
import com.sillykid.app.R;
import com.sillykid.app.mine.myrelease.mydynamic.MyDynamicFragment;
import com.sillykid.app.mine.myrelease.mystrategy.MyStrategyFragment;

/**
 * 我的发布
 * Created by Administrator on 2018/9/2.
 */
public class MyReleaseActivity extends BaseActivity {

    @BindView(id = R.id.ll_myDynamic, click = true)
    private LinearLayout ll_myDynamic;

    @BindView(id = R.id.tv_myDynamic)
    private TextView tv_myDynamic;

    @BindView(id = R.id.tv_myDynamic1)
    private TextView tv_myDynamic1;

    @BindView(id = R.id.ll_myStrategy, click = true)
    private LinearLayout ll_myStrategy;

    @BindView(id = R.id.tv_myStrategy)
    private TextView tv_myStrategy;

    @BindView(id = R.id.tv_myStrategy1)
    private TextView tv_myStrategy1;

    private BaseFragment baseFragment;
    private BaseFragment baseFragment1;

    private int chageIcon = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myrelease);
    }

    @Override
    public void initData() {
        super.initData();
        baseFragment = new MyDynamicFragment();
        baseFragment1 = new MyStrategyFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        if (chageIcon == 0) {
            cleanColors(0);
            changeFragment(baseFragment);
            chageIcon = 0;
        } else if (chageIcon == 1) {
            cleanColors(1);
            changeFragment(baseFragment1);
            chageIcon = 1;
        } else {
            cleanColors(0);
            changeFragment(baseFragment);
            chageIcon = 0;
        }

    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myRelease), true, R.id.titlebar);
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
        int newChageIcon = intent.getIntExtra("newChageIcon", 0);
        Log.d("newChageIcon", newChageIcon + "");
        if (newChageIcon == 0) {
            setSimulateClick(ll_myDynamic, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(ll_myStrategy, 160, 100);
        } else {
            setSimulateClick(ll_myDynamic, 160, 100);
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

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.release_content, targetFragment);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_myDynamic:
                cleanColors(0);
                changeFragment(baseFragment);
                chageIcon = 0;
                break;
            case R.id.ll_myStrategy:
                cleanColors(1);
                changeFragment(baseFragment1);
                chageIcon = 1;
                break;
        }

    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_myDynamic.setTextColor(getResources().getColor(R.color.textColor));
        tv_myDynamic1.setTextColor(getResources().getColor(R.color.whiteColors));
        tv_myStrategy.setTextColor(getResources().getColor(R.color.textColor));
        tv_myStrategy1.setTextColor(getResources().getColor(R.color.whiteColors));
        if (chageIcon == 0) {
            tv_myDynamic.setTextColor(getResources().getColor(R.color.greenColors));
            tv_myDynamic1.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 1) {
            tv_myStrategy.setTextColor(getResources().getColor(R.color.greenColors));
            tv_myStrategy1.setTextColor(getResources().getColor(R.color.greenColors));
        }
    }

}

