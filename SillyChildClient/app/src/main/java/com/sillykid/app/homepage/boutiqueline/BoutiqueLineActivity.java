package com.sillykid.app.homepage.boutiqueline;

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
import com.sillykid.app.homepage.boutiqueline.fragment.CitySelectionFragment;
import com.sillykid.app.homepage.boutiqueline.fragment.HotRecommendedFragment;

/**
 * 精品线路
 */
public class BoutiqueLineActivity extends BaseActivity {

    @BindView(id = R.id.ll_chosecity, click = true)
    private LinearLayout ll_chosecity;

    @BindView(id = R.id.tv_chosecity)
    private TextView tv_chosecity;

    @BindView(id = R.id.tv_chosecity1)
    private TextView tv_chosecity1;

    @BindView(id = R.id.ll_hotRecommended, click = true)
    private LinearLayout ll_hotRecommended;

    @BindView(id = R.id.tv_hotRecommended)
    private TextView tv_hotRecommended;

    @BindView(id = R.id.tv_hotRecommended1)
    private TextView tv_hotRecommended1;

    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_boutiqueline);
    }


    @Override
    public void initData() {
        super.initData();
        contentFragment = new CitySelectionFragment();
        contentFragment1 = new HotRecommendedFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        cleanColors(chageIcon);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.boutiqueLine), true, R.id.titlebar);
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
        int newChageIcon = intent.getIntExtra("newChageIcon", 5);
        Log.d("newChageIcon", newChageIcon + "");
        if (newChageIcon == 0) {
            setSimulateClick(ll_chosecity, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(ll_hotRecommended, 160, 100);
        } else {
            setSimulateClick(ll_chosecity, 160, 100);
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
            case R.id.ll_chosecity:
                chageIcon = 0;
                cleanColors(chageIcon);
                break;
            case R.id.ll_hotRecommended:
                chageIcon = 1;
                cleanColors(chageIcon);
                break;
            default:
                break;
        }
    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_chosecity.setTextColor(getResources().getColor(R.color.textColor));
        tv_chosecity1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_hotRecommended.setTextColor(getResources().getColor(R.color.textColor));
        tv_hotRecommended1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        if (chageIcon == 0) {
            tv_chosecity.setTextColor(getResources().getColor(R.color.greenColors));
            tv_chosecity1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(contentFragment);
        } else if (chageIcon == 1) {
            tv_hotRecommended.setTextColor(getResources().getColor(R.color.greenColors));
            tv_hotRecommended1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(contentFragment1);
        } else {
            tv_chosecity.setTextColor(getResources().getColor(R.color.greenColors));
            tv_chosecity1.setBackgroundColor(getResources().getColor(R.color.greenColors));
            changeFragment(contentFragment);
        }
    }


}
