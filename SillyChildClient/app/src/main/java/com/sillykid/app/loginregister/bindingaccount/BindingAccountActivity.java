package com.sillykid.app.loginregister.bindingaccount;

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
 * 注册
 * 第三方登录 绑定账号
 * Created by ruitu ck on 2016/9/14.
 */

public class BindingAccountActivity extends BaseActivity {


    /**
     * 手机号绑定
     */
    @BindView(id = R.id.ll_bindingPhone, click = true)
    private LinearLayout ll_bindingPhone;

    @BindView(id = R.id.tv_bindingPhone)
    private TextView tv_bindingPhone;
    @BindView(id = R.id.tv_bindingPhone1)
    private TextView tv_bindingPhone1;

    /**
     * 邮箱号绑定
     */
    @BindView(id = R.id.ll_bindingEmail, click = true)
    private LinearLayout ll_bindingEmail;

    @BindView(id = R.id.tv_bindingEmail)
    private TextView tv_bindingEmail;
    @BindView(id = R.id.tv_bindingEmail1)
    private TextView tv_bindingEmail1;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;


    private int chageIcon = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bindingaccount);
    }

    @Override
    public void initData() {
        super.initData();
        contentFragment = new BindingPhoneFragment();
        contentFragment1 = new BindingMailBoxFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        cleanColors(0);
        changeFragment(contentFragment);
        chageIcon = 0;
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.bindingAccount), true, R.id.titlebar);
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
        int newChageIcon = intent.getIntExtra("newChageIcon", 2);
        Log.d("newChageIcon", newChageIcon + "");
        if (newChageIcon == 0) {
            setSimulateClick(ll_bindingPhone, 160, 100);
        } else if (newChageIcon == 1) {
            setSimulateClick(ll_bindingEmail, 160, 100);
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
            case R.id.img_back:
                finish();
                break;

            case R.id.ll_bindingPhone:
                cleanColors(0);
                changeFragment(contentFragment);
                chageIcon = 0;
                break;
            case R.id.ll_bindingEmail:
                cleanColors(1);
                changeFragment(contentFragment1);
                chageIcon = 1;
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
        tv_bindingPhone.setTextColor(getResources().getColor(R.color.tabColors));
        tv_bindingPhone1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_bindingEmail.setTextColor(getResources().getColor(R.color.tabColors));
        tv_bindingEmail1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        if (chageIcon == 0) {
            tv_bindingPhone.setTextColor(getResources().getColor(R.color.greenColors));
            tv_bindingPhone1.setBackgroundColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 1) {
            tv_bindingEmail.setTextColor(getResources().getColor(R.color.greenColors));
            tv_bindingEmail1.setBackgroundColor(getResources().getColor(R.color.greenColors));
        } else {
            tv_bindingPhone.setTextColor(getResources().getColor(R.color.greenColors));
            tv_bindingPhone1.setBackgroundColor(getResources().getColor(R.color.greenColors));
        }
    }
}
