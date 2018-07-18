package com.sillykid.app.mine.myorder;

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
import com.sillykid.app.mine.myorder.goodorder.AfterSaleGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.AllGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.CompletedGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.ObligationGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.SendGoodsGoodFragment;
import com.sillykid.app.mine.myorder.goodorder.WaitGoodsGoodFragment;

/**
 * 我的订单
 * Created by Administrator on 2017/9/2.
 */

public class MyOrderActivity extends BaseActivity {

    @BindView(id = R.id.tv_good_obligation, click = true)
    private TextView tv_good_obligation;

    @BindView(id = R.id.tv_good_send, click = true)
    private TextView tv_good_send;


    @BindView(id = R.id.tv_good_wait, click = true)
    private TextView tv_good_wait;


    @BindView(id = R.id.tv_good_completed, click = true)
    private TextView tv_good_completed;


    @BindView(id = R.id.tv_good_afterSale, click = true)
    private TextView tv_good_afterSale;


    @BindView(id = R.id.tv_good_all, click = true)
    private TextView tv_good_all;


    private BaseFragment obligationGoodFragment;
    private BaseFragment sendGoodsGoodFragment;
    private BaseFragment waitGoodsGoodFragment;
    private BaseFragment completedGoodFragment;
    private BaseFragment afterSaleGoodFragment;
    private BaseFragment allGoodFragment;
    private int chageIcon = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myorder);
    }

    @Override
    public void initData() {
        super.initData();
        obligationGoodFragment = new ObligationGoodFragment();
        sendGoodsGoodFragment = new SendGoodsGoodFragment();
        waitGoodsGoodFragment = new WaitGoodsGoodFragment();
        completedGoodFragment = new CompletedGoodFragment();
        afterSaleGoodFragment = new AfterSaleGoodFragment();
        allGoodFragment = new AllGoodFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        if (chageIcon == 0) {
            cleanColors(0);
            changeFragment(obligationGoodFragment);
            chageIcon = 0;
        } else if (chageIcon == 1) {
            cleanColors(1);
            changeFragment(sendGoodsGoodFragment);
            chageIcon = 1;
        } else if (chageIcon == 2) {
            cleanColors(2);
            changeFragment(waitGoodsGoodFragment);
            chageIcon = 2;
        } else if (chageIcon == 3) {
            cleanColors(3);
            changeFragment(completedGoodFragment);
            chageIcon = 3;
        } else if (chageIcon == 4) {
            cleanColors(4);
            changeFragment(afterSaleGoodFragment);
            chageIcon = 4;
        } else if (chageIcon == 5) {
            cleanColors(5);
            changeFragment(allGoodFragment);
            chageIcon = 5;
        } else {
            cleanColors(0);
            changeFragment(obligationGoodFragment);
            chageIcon = 0;
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
            setSimulateClick(tv_good_obligation, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(tv_good_send, 160, 100);
        } else if (newChageIcon == 2) {
            setSimulateClick(tv_good_wait, 160, 100);
        } else if (newChageIcon == 3) {
            setSimulateClick(tv_good_completed, 160, 100);
        } else if (newChageIcon == 4) {
            setSimulateClick(tv_good_afterSale, 160, 100);
        } else if (newChageIcon == 5) {
            setSimulateClick(tv_good_all, 160, 100);
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
            case R.id.tv_good_obligation:
                cleanColors(0);
                changeFragment(obligationGoodFragment);
                chageIcon = 0;
                break;
            case R.id.tv_good_send:
                cleanColors(1);
                changeFragment(sendGoodsGoodFragment);
                chageIcon = 1;
                break;
            case R.id.tv_good_wait:
                cleanColors(2);
                changeFragment(waitGoodsGoodFragment);
                chageIcon = 2;
                break;
            case R.id.tv_good_completed:
                cleanColors(3);
                changeFragment(completedGoodFragment);
                chageIcon = 3;
                break;
            case R.id.tv_good_afterSale:
                cleanColors(4);
                changeFragment(afterSaleGoodFragment);
                chageIcon = 4;
                break;
            case R.id.tv_good_all:
                cleanColors(5);
                changeFragment(allGoodFragment);
                chageIcon = 5;
                break;
        }

    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_good_obligation.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_send.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_wait.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_completed.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_afterSale.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_all.setTextColor(getResources().getColor(R.color.textColor));
        if (chageIcon == 0) {
            tv_good_obligation.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 1) {
            tv_good_send.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 2) {
            tv_good_wait.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 3) {
            tv_good_completed.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 4) {
            tv_good_afterSale.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 5) {
            tv_good_all.setTextColor(getResources().getColor(R.color.greenColors));
        }
    }
}

