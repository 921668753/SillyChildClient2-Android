package com.sillykid.app.mine.myorder;

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

/**
 * 我的订单
 * Created by Administrator on 2017/9/2.
 */

public class MyOrderActivity extends BaseActivity {

    @BindView(id = R.id.ll_goodOrder, click = true)
    private LinearLayout ll_goodOrder;

    @BindView(id = R.id.tv_goodOrder)
    private TextView tv_goodOrder;


    @BindView(id = R.id.tv_goodOrder1)
    private TextView tv_goodOrder1;


    @BindView(id = R.id.ll_charterOrder, click = true)
    private LinearLayout ll_charterOrder;

    @BindView(id = R.id.tv_charterOrder)
    private TextView tv_charterOrder;

    @BindView(id = R.id.tv_charterOrder1)
    private TextView tv_charterOrder1;

    private BaseFragment baseFragment;
    private BaseFragment baseFragment1;
    private int chageIcon = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myorder);
    }

    @Override
    public void initData() {
        super.initData();
        baseFragment = new GoodOrderFragment();
        baseFragment1 = new CharterOrderFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        if (chageIcon == 0) {
            chageIcon = 0;
            cleanColors(chageIcon);
        } else if (chageIcon == 1) {
            chageIcon = 1;
            cleanColors(chageIcon);
        } else {
            chageIcon = 0;
            cleanColors(chageIcon);
        }

    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myOrder), true, R.id.titlebar);
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
            setSimulateClick(ll_goodOrder, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(ll_charterOrder, 160, 100);
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
        super.changeFragment(R.id.order_content, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_goodOrder:
                cleanColors(0);
                changeFragment(baseFragment);
                chageIcon = 0;
                break;
            case R.id.ll_charterOrder:
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
        tv_goodOrder.setTextColor(getResources().getColor(R.color.textColor));
        tv_goodOrder1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_charterOrder.setTextColor(getResources().getColor(R.color.textColor));
        tv_charterOrder1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        if (chageIcon == 0) {
            tv_goodOrder.setTextColor(getResources().getColor(R.color.greenColors));
            tv_goodOrder1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(baseFragment);
        } else if (chageIcon == 1) {
            tv_charterOrder.setTextColor(getResources().getColor(R.color.greenColors));
            tv_charterOrder1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(baseFragment1);
        } else {
            tv_goodOrder.setTextColor(getResources().getColor(R.color.greenColors));
            tv_goodOrder1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(baseFragment);
        }
    }
}

