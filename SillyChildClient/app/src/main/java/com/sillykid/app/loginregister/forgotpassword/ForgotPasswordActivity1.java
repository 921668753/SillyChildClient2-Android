package com.sillykid.app.loginregister.forgotpassword;

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
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;

/**
 * 忘记密码
 * Created by ruitu ck on 2016/9/14.
 */

public class ForgotPasswordActivity1 extends BaseActivity {


//    /**
//     * 手机号注册
//     */
//    @BindView(id = R.id.ll_registerPhone, click = true)
//    private LinearLayout ll_registerPhone;
//
//    @BindView(id = R.id.tv_registerPhone)
//    private TextView tv_registerPhone;
//    @BindView(id = R.id.tv_registerPhone1)
//    private TextView tv_registerPhone1;
//
//    /**
//     * 邮箱号注册
//     */
//    @BindView(id = R.id.ll_registerMailBox, click = true)
//    private LinearLayout ll_registerMailBox;
//
//    @BindView(id = R.id.tv_registerMailBox)
//    private TextView tv_registerMailBox;
//    @BindView(id = R.id.tv_registerMailBox1)
//    private TextView tv_registerMailBox1;
//
//    private BaseFragment contentFragment;
//    private BaseFragment contentFragment1;
//
//
//    private int chageIcon = 0;
//
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_register);
    }
//
//    @Override
//    public void initData() {
//        super.initData();
//       // contentFragment = new RetrievePasswordActivity();
//        contentFragment1 = new RetrieveMailBoxFragment();
//        chageIcon = getIntent().getIntExtra("chageIcon", 0);
//    }
//
//    @Override
//    public void initWidget() {
//        super.initWidget();
//        initTitle();
//        tv_registerPhone.setText(getString(R.string.retrievePhonePassword));
//        tv_registerMailBox.setText(getString(R.string.retrieveEmailPassword));
//        cleanColors(0);
//        changeFragment(contentFragment);
//        chageIcon = 0;
//    }
//
//    /**
//     * 设置标题
//     */
//    public void initTitle() {
//        String title = getIntent().getStringExtra("title");
//        if (StringUtils.isEmpty(title)) {
//            title = getString(R.string.forgotPassword1);
//        }
//        ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
//    }
//
//
//    public void changeFragment(BaseFragment targetFragment) {
//        super.changeFragment(R.id.main_content, targetFragment);
//    }
//
//
//    /**
//     * Activity的启动模式变为singleTask
//     * 调用onNewIntent(Intent intent)方法。
//     * Fragment调用的时候，一定要在onResume方法中。
//     *
//     * @param intent
//     */
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        int newChageIcon = intent.getIntExtra("newChageIcon", 2);
//        Log.d("newChageIcon", newChageIcon + "");
//        if (newChageIcon == 0) {
//            setSimulateClick(ll_registerPhone, 160, 100);
//        } else if (newChageIcon == 1) {
//            setSimulateClick(ll_registerMailBox, 160, 100);
//        }
//    }
//
//    /**
//     * 模拟点击
//     *
//     * @param view
//     * @param x
//     * @param y
//     */
//    private void setSimulateClick(View view, float x, float y) {
//        long downTime = SystemClock.uptimeMillis();
//        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
//                MotionEvent.ACTION_DOWN, x, y, 0);
//        downTime += 1000;
//        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
//                MotionEvent.ACTION_UP, x, y, 0);
//        view.onTouchEvent(downEvent);
//        view.onTouchEvent(upEvent);
//        downEvent.recycle();
//        upEvent.recycle();
//    }
//
//    @Override
//    public void widgetClick(View v) {
//        super.widgetClick(v);
//        switch (v.getId()) {
//            case R.id.img_back:
//                finish();
//                break;
//
//            case R.id.ll_registerPhone:
//                cleanColors(0);
//                changeFragment(contentFragment);
//                chageIcon = 0;
//                break;
//            case R.id.ll_registerMailBox:
//                cleanColors(1);
//                changeFragment(contentFragment1);
//                chageIcon = 1;
//                break;
//            default:
//                break;
//        }
//    }
//
//
//    /**
//     * 清除颜色，并添加颜色
//     */
//    @SuppressWarnings("deprecation")
//    public void cleanColors(int chageIcon) {
//        tv_registerPhone.setTextColor(getResources().getColor(R.color.tabColors));
//        tv_registerPhone1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
//        tv_registerMailBox.setTextColor(getResources().getColor(R.color.tabColors));
//        tv_registerMailBox1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
//        if (chageIcon == 0) {
//            tv_registerPhone.setTextColor(getResources().getColor(R.color.greenColors));
//            tv_registerPhone1.setBackgroundColor(getResources().getColor(R.color.greenColors));
//        } else if (chageIcon == 1) {
//            tv_registerMailBox.setTextColor(getResources().getColor(R.color.greenColors));
//            tv_registerMailBox1.setBackgroundColor(getResources().getColor(R.color.greenColors));
//        } else {
//            tv_registerPhone.setTextColor(getResources().getColor(R.color.greenColors));
//            tv_registerPhone1.setBackgroundColor(getResources().getColor(R.color.greenColors));
//        }
//    }
}
