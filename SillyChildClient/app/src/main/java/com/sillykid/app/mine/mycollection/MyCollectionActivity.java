package com.sillykid.app.mine.mycollection;

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
 * 我的收藏
 * Created by Administrator on 2017/9/2.
 */

public class MyCollectionActivity extends BaseActivity {

    @BindView(id = R.id.ll_good, click = true)
    private LinearLayout ll_good;
    @BindView(id = R.id.tv_good)
    private TextView tv_good;
    @BindView(id = R.id.tv_good1)
    private TextView tv_good1;

    @BindView(id = R.id.ll_shop, click = true)
    private LinearLayout ll_shop;
    @BindView(id = R.id.tv_shop)
    private TextView tv_shop;
    @BindView(id = R.id.tv_shop1)
    private TextView tv_shop1;

    @BindView(id = R.id.ll_route, click = true)
    private LinearLayout ll_route;
    @BindView(id = R.id.tv_route)
    private TextView tv_route;
    @BindView(id = R.id.tv_route1)
    private TextView tv_route1;

    @BindView(id = R.id.ll_strategy, click = true)
    private LinearLayout ll_strategy;
    @BindView(id = R.id.tv_strategy)
    private TextView tv_strategy;
    @BindView(id = R.id.tv_strategy1)
    private TextView tv_strategy1;

    @BindView(id = R.id.ll_dynamicState, click = true)
    private LinearLayout ll_dynamicState;
    @BindView(id = R.id.tv_dynamicState)
    private TextView tv_dynamicState;
    @BindView(id = R.id.tv_dynamicState1)
    private TextView tv_dynamicState1;

    @BindView(id = R.id.ll_video, click = true)
    private LinearLayout ll_video;
    @BindView(id = R.id.tv_video)
    private TextView tv_video;
    @BindView(id = R.id.tv_video1)
    private TextView tv_video1;

    @BindView(id = R.id.ll_house, click = true)
    private LinearLayout ll_house;
    @BindView(id = R.id.tv_house)
    private TextView tv_house;
    @BindView(id = R.id.tv_house1)
    private TextView tv_house1;

    @BindView(id = R.id.ll_companyGuide, click = true)
    private LinearLayout ll_companyGuide;
    @BindView(id = R.id.tv_companyGuide)
    private TextView tv_companyGuide;
    @BindView(id = R.id.tv_companyGuide1)
    private TextView tv_companyGuide1;

    private BaseFragment baseFragment;
    private BaseFragment baseFragment1;
    private BaseFragment baseFragment2;
    private BaseFragment baseFragment3;
    private BaseFragment baseFragment4;
    private BaseFragment baseFragment5;
    private BaseFragment baseFragment6;
    private BaseFragment baseFragment7;

    private int chageIcon;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mycollection);
    }

    @Override
    public void initData() {
        super.initData();
        baseFragment = new GoodFragment();
        baseFragment1 = new ShopFragment();
       // baseFragment2 = new RouteFragment();
        baseFragment3 = new StrategyFragment();
        baseFragment4 = new DynamicStateFragment();
        baseFragment5 = new VideoFragment();
        baseFragment6 = new HouseFragment();
        baseFragment7 = new CompanyGuideFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        cleanColors(chageIcon);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_good:
                chageIcon = 0;
                cleanColors(chageIcon);
                break;
            case R.id.ll_shop:
                chageIcon = 1;
                cleanColors(chageIcon);
                break;
            case R.id.ll_route:
                chageIcon = 2;
                cleanColors(chageIcon);
                break;
            case R.id.ll_strategy:
                chageIcon = 3;
                cleanColors(chageIcon);
                break;
            case R.id.ll_dynamicState:
                chageIcon = 4;
                cleanColors(chageIcon);
                break;
            case R.id.ll_video:
                chageIcon = 5;
                cleanColors(chageIcon);
                break;
            case R.id.ll_house:
                chageIcon = 6;
                cleanColors(chageIcon);
                break;
            case R.id.ll_companyGuide:
                chageIcon = 7;
                cleanColors(chageIcon);
                break;
        }
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fl_mycollection, targetFragment);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myCollection), true, R.id.titlebar);
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
            setSimulateClick(ll_good, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(ll_shop, 160, 100);
        } else if (newChageIcon == 2) {
            setSimulateClick(ll_route, 160, 100);
        } else if (newChageIcon == 3) {
            setSimulateClick(ll_strategy, 160, 100);
        } else if (newChageIcon == 4) {
            setSimulateClick(ll_dynamicState, 160, 100);
        } else if (newChageIcon == 5) {
            setSimulateClick(ll_video, 160, 100);
        } else if (newChageIcon == 6) {
            setSimulateClick(ll_house, 160, 100);
        } else if (newChageIcon == 7) {
            setSimulateClick(ll_companyGuide, 160, 100);
        } else {
            setSimulateClick(ll_good, 160, 100);
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
        tv_good.setTextColor(getResources().getColor(R.color.textColor));
        tv_good1.setBackgroundResource(R.color.whiteColors);

        tv_shop.setTextColor(getResources().getColor(R.color.textColor));
        tv_shop1.setBackgroundResource(R.color.whiteColors);

        tv_route.setTextColor(getResources().getColor(R.color.textColor));
        tv_route1.setBackgroundResource(R.color.whiteColors);

        tv_strategy.setTextColor(getResources().getColor(R.color.textColor));
        tv_strategy1.setBackgroundResource(R.color.whiteColors);

        tv_dynamicState.setTextColor(getResources().getColor(R.color.textColor));
        tv_dynamicState1.setBackgroundResource(R.color.whiteColors);

        tv_video.setTextColor(getResources().getColor(R.color.textColor));
        tv_video1.setBackgroundResource(R.color.whiteColors);

        tv_house.setTextColor(getResources().getColor(R.color.textColor));
        tv_house1.setBackgroundResource(R.color.whiteColors);

        tv_companyGuide.setTextColor(getResources().getColor(R.color.textColor));
        tv_companyGuide1.setBackgroundResource(R.color.whiteColors);

        switch (chageIcon) {
            case 0:
                tv_good.setTextColor(getResources().getColor(R.color.greenColors));
                tv_good1.setBackgroundResource(R.color.greenColors);
                changeFragment(baseFragment);
                break;
            case 1:
                tv_shop.setTextColor(getResources().getColor(R.color.greenColors));
                tv_shop1.setBackgroundResource(R.color.greenColors);
                changeFragment(baseFragment1);
                break;
            case 2:
                tv_route.setTextColor(getResources().getColor(R.color.greenColors));
                tv_route1.setBackgroundResource(R.color.greenColors);
                changeFragment(baseFragment2);
                break;
            case 3:
                tv_strategy.setTextColor(getResources().getColor(R.color.greenColors));
                tv_strategy1.setBackgroundResource(R.color.greenColors);
                changeFragment(baseFragment3);
                break;
            case 4:
                tv_dynamicState.setTextColor(getResources().getColor(R.color.greenColors));
                tv_dynamicState1.setBackgroundResource(R.color.greenColors);
                changeFragment(baseFragment4);
                break;
            case 5:
                tv_video.setTextColor(getResources().getColor(R.color.greenColors));
                tv_video1.setBackgroundResource(R.color.greenColors);
                changeFragment(baseFragment5);
                break;
            case 6:
                tv_house.setTextColor(getResources().getColor(R.color.greenColors));
                tv_house1.setBackgroundResource(R.color.greenColors);
                changeFragment(baseFragment6);
                break;
            case 7:
                tv_companyGuide.setTextColor(getResources().getColor(R.color.greenColors));
                tv_companyGuide1.setBackgroundResource(R.color.greenColors);
                changeFragment(baseFragment7);
                break;
        }
    }

    public int getChageIcon() {
        return chageIcon;
    }

}
