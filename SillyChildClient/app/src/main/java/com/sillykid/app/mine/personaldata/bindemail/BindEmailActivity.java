package com.sillykid.app.mine.personaldata.bindemail;

import android.os.CountDownTimer;
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
import com.sillykid.app.utils.AccountValidatorUtil;

/**
 * 绑定邮箱
 * Created by Administrator on 2017/9/2.
 */

public class BindEmailActivity extends BaseActivity implements BindEmailContract.View{

    private BindEmailContract.Presenter mPresenter;
    /**
     * opt	String
     * 验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String posttype = "bind";

    @BindView(id = R.id.tv_getcode , click = true)
    private TextView tv_getcode;

    @BindView(id = R.id.et_email , click = true)
    private EditText et_email;

    @BindView(id = R.id.et_code , click = true)
    private EditText et_code;

    @BindView(id = R.id.tv_true , click = true)
    private TextView tv_true;
    private TimeCount time;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bindemail);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new BindEmailPresenter(this);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        tv_true.setClickable(false);
        tv_true.setBackgroundResource(R.drawable.shape_login);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.tv_getcode:
                if (StringUtils.isEmpty(et_email.getText().toString())) {
                    ViewInject.toast(getString(R.string.hintEmailText));
                    return;
                }
                if (!AccountValidatorUtil.isEmail(et_email.getText().toString())) {
                    ViewInject.toast(getString(R.string.hintEmailText1));
                    return;
                }
                tv_true.setClickable(false);
                tv_true.setBackgroundResource(R.drawable.shape_login);
                showLoadingDialog(getString(R.string.sendingLoad));
                mPresenter.postCode(et_email.getText().toString(),posttype);
                break;
            case R.id.tv_true:
                showLoadingDialog(getString(R.string.submissionLoad));
                 mPresenter.bindEmail(et_email.getText().toString(), et_code.getText().toString());
                break;

        }

    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.bindEmail), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(BindEmailContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        switch (flag){
            case 0:
                if (time==null)time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                time.start();
                tv_getcode.setClickable(false);
                tv_true.setClickable(true);
                tv_true.setBackgroundResource(R.drawable.shape_login1);
                dismissLoadingDialog();
                ViewInject.toast(getString(R.string.testget));
                break;
            case 1:
                dismissLoadingDialog();
                setResult(1,getIntent().putExtra("email",et_email.getText().toString()));
                finish();
                break;
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            finish();
            KJActivityStack.create().finishToThis(LoginActivity.class,MainActivity.class);
            return;
        }
        switch (flag){
            case 0:
                if (time==null)time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
                time.start();
                dismissLoadingDialog();
                ViewInject.toast(msg);
                break;
            case 1:
                dismissLoadingDialog();
                ViewInject.toast(msg);
                break;
        }

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
