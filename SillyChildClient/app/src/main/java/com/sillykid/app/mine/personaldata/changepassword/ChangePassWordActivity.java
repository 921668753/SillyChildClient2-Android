package com.sillykid.app.mine.personaldata.changepassword;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.main.MainActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的--个人资料--修改密码第一步
 * Created by Administrator on 2017/9/2.
 */

public class ChangePassWordActivity extends BaseActivity implements ChangePassWordContract.View{

    private ChangePassWordContract.Presenter mPresenter;

    @BindView(id=R.id.et_phone)
    private EditText et_phone;

    @BindView(id=R.id.tv_getcode,click = true)
    private TextView tv_getcode;

    @BindView(id=R.id.et_code)
    private EditText et_code;

    @BindView(id=R.id.tv_nextstep,click = true)
    private TextView tv_nextstep;

    /**
     * opt	String
     * 验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String opt = "resetpwd";
    private TimeCount time;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_changepassword);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new ChangePassWordPresenter(this);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        tv_nextstep.setClickable(false);

    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.changepasswordfirst), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.tv_getcode:
                tv_nextstep.setClickable(false);
                tv_nextstep.setBackgroundResource(R.drawable.shape_login);
                showLoadingDialog(getString(R.string.sendingLoad));
                mPresenter.getCode(this,et_phone.getText().toString(),opt);
                break;
            case R.id.tv_nextstep:
                inNextstep();
                break;
        }
    }

    private void inNextstep(){
        if (StringUtils.isEmpty(et_phone.getText().toString())) {
            ViewInject.toast(getString(R.string.hintPhoneText));
            return;
        }
        if (et_phone.getText().toString().length() != 11) {
            ViewInject.toast(getString(R.string.hintPhoneText1));
            return;
        }

        if (TextUtils.isEmpty(et_code.getText().toString())){
            ViewInject.toast(getString(R.string.errorCode));
            return;
        }

        Intent jumpintent=new Intent(this,ChangePassWordEndActivity.class);
        jumpintent.putExtra("phone",et_phone.getText().toString());
        jumpintent.putExtra("code",et_code.getText().toString());
        showActivity(this,jumpintent);

    }

    @Override
    public void setPresenter(ChangePassWordContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (time==null)time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        time.start();
        tv_getcode.setClickable(false);
        tv_nextstep.setClickable(true);
        tv_nextstep.setBackgroundResource(R.drawable.shape_login1);
        dismissLoadingDialog();
        ViewInject.toast(getString(R.string.testget));
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            finish();
            KJActivityStack.create().finishToThis(LoginActivity.class,MainActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    /* 定义一个倒计时的内部类 */
    @SuppressWarnings("deprecation")
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_getcode.setText("重新验证");
            tv_getcode.setClickable(true);
            tv_getcode.setTextColor(getResources().getColor(R.color.greenColors));
            tv_getcode.setBackgroundResource(R.drawable.shape_code);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_getcode.setClickable(false);
            tv_getcode.setText(millisUntilFinished / 1000 + "秒");
            tv_getcode.setTextColor(getResources().getColor(R.color.hintColors));
            tv_getcode.setBackgroundResource(R.drawable.shape_code1);
        }
    }
}
