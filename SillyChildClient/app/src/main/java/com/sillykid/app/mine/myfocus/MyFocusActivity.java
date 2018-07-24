package com.sillykid.app.mine.myfocus;

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
import com.sillykid.app.mine.myfocus.companyguide.CompanyGuideFragment;
import com.sillykid.app.mine.myfocus.merchants.MerchantsFragment;
import com.sillykid.app.mine.myfocus.user.UserFragment;

/**
 * 我的关注
 */
public class MyFocusActivity extends BaseActivity {

    @BindView(id = R.id.ll_user, click = true)
    private LinearLayout ll_user;

    @BindView(id = R.id.tv_user)
    private TextView tv_user;

    @BindView(id = R.id.tv_user1)
    private TextView tv_user1;

    @BindView(id = R.id.ll_companyGuide, click = true)
    private LinearLayout ll_companyGuide;

    @BindView(id = R.id.tv_companyGuide)
    private TextView tv_companyGuide;

    @BindView(id = R.id.tv_companyGuide1)
    private TextView tv_companyGuide1;

    @BindView(id = R.id.ll_merchants, click = true)
    private LinearLayout ll_merchants;

    @BindView(id = R.id.tv_merchants)
    private TextView tv_merchants;

    @BindView(id = R.id.tv_merchants1)
    private TextView tv_merchants1;

    private BaseFragment baseFragment;
    private BaseFragment baseFragment1;
    private BaseFragment baseFragment2;

    private int chageIcon = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myfocus);
    }

    @Override
    public void initData() {
        super.initData();
        baseFragment = new UserFragment();
        baseFragment1 = new CompanyGuideFragment();
        baseFragment2 = new MerchantsFragment();
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
        } else if (chageIcon == 2) {
            cleanColors(2);
            changeFragment(baseFragment2);
            chageIcon = 2;
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myFocus), true, R.id.titlebar);
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
            setSimulateClick(ll_user, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(ll_companyGuide, 160, 100);
        } else if (newChageIcon == 2) {
            setSimulateClick(ll_merchants, 160, 100);
        } else {
            setSimulateClick(ll_user, 160, 100);
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
        super.changeFragment(R.id.focus_content, targetFragment);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_user:
                cleanColors(0);
                changeFragment(baseFragment);
                chageIcon = 0;
                break;
            case R.id.ll_companyGuide:
                cleanColors(1);
                changeFragment(baseFragment1);
                chageIcon = 1;
                break;
            case R.id.ll_merchants:
                cleanColors(2);
                changeFragment(baseFragment2);
                chageIcon = 2;
                break;
        }

    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_user.setTextColor(getResources().getColor(R.color.textColor));
        tv_user1.setTextColor(getResources().getColor(R.color.whiteColors));
        tv_companyGuide.setTextColor(getResources().getColor(R.color.textColor));
        tv_companyGuide1.setTextColor(getResources().getColor(R.color.whiteColors));
        tv_merchants.setTextColor(getResources().getColor(R.color.textColor));
        tv_merchants1.setTextColor(getResources().getColor(R.color.whiteColors));
        if (chageIcon == 0) {
            tv_user.setTextColor(getResources().getColor(R.color.greenColors));
            tv_user1.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 1) {
            tv_companyGuide.setTextColor(getResources().getColor(R.color.greenColors));
            tv_companyGuide1.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 2) {
            tv_merchants.setTextColor(getResources().getColor(R.color.greenColors));
            tv_merchants1.setTextColor(getResources().getColor(R.color.greenColors));
        }
    }

}
