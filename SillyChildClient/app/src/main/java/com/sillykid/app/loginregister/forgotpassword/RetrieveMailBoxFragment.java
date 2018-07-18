package com.sillykid.app.loginregister.forgotpassword;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.entity.loginregister.LoginBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.loginregister.register.RegistrationAgreementActivity;
import com.sillykid.app.main.MainActivity;

/**
 * 邮箱注册
 * Created by Admin on 2017/8/10.
 */

public class RetrieveMailBoxFragment extends BaseFragment implements RetrievePasswordContract.View {

    private ForgotPasswordActivity1 aty;

    /**
     * 倒计时内部类
     */
    private TimeCount time;

    /**
     * 邀请码
     */
    @BindView(id = R.id.ll_invitationCode)
    private LinearLayout ll_invitationCode;

    /**
     * 注册协议
     */
    @BindView(id = R.id.ll_agreement)
    private LinearLayout ll_agreement;

    @BindView(id = R.id.tv_agreement, click = true)
    private TextView tv_agreement;
    /**
     * 手机号
     */
    @BindView(id = R.id.et_phone)
    private EditText et_phone;
    /**
     * 验证码
     */
    @BindView(id = R.id.et_code)
    private EditText et_code;
    /**
     * 获取验证码
     */
    @BindView(id = R.id.tv_code, click = true)
    private TextView tv_code;
    /**
     * 密码
     */
    @BindView(id = R.id.et_pwd)
    private EditText et_pwd;
    /**
     * 密码
     */
    @BindView(id = R.id.et_pwd1)
    private EditText et_pwd1;
    /**
     * 注册
     */
    @BindView(id = R.id.tv_registe, click = true)
    private TextView tv_registe;

    /**
     * opt	String
     * 验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String opt = "resetpwd";


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (ForgotPasswordActivity1) getActivity();
        return View.inflate(aty, R.layout.fragment_registermailbox, null);
    }


    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new RetrievePasswordPresenter(this);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
    }

    /**
     * 渲染view
     */
    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        ll_invitationCode.setVisibility(View.GONE);
        ll_agreement.setVisibility(View.GONE);
        tv_registe.setText(getString(R.string.resetPassword));
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_code:
                showLoadingDialog(getString(R.string.sendingLoad));
                ((RetrievePasswordContract.Presenter) mPresenter).postMailCaptcha(et_phone.getText().toString(), opt);
                break;
            case R.id.tv_registe:
                tv_registe.setEnabled(false);
                showLoadingDialog(getString(R.string.submissionLoad));
                if (opt.equals("resetpwd")) {
                    ((RetrievePasswordContract.Presenter) mPresenter).getForgetPasswordByMail(et_phone.getText().toString(), et_code.getText().toString(), et_pwd.getText().toString(), et_pwd1.getText().toString());
                }
                break;
            case R.id.tv_agreement:
                // 注册协议
                aty.showActivity(aty, RegistrationAgreementActivity.class);
                break;
            default:
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
            tv_code.setText("重新验证");
            tv_code.setClickable(true);
            tv_code.setTextColor(getResources().getColor(R.color.greenColors));
            tv_code.setBackgroundResource(R.drawable.shape_code);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_code.setClickable(false);
            tv_code.setText(millisUntilFinished / 1000 + "秒");
            tv_code.setTextColor(getResources().getColor(R.color.hintColors));
            tv_code.setBackgroundResource(R.drawable.shape_code1);
        }
    }

    @Override
    public void getSuccess(String s, int flag) {
        dismissLoadingDialog();
        tv_registe.setEnabled(true);
        if (flag == 0) {
            ViewInject.toast(getString(R.string.testget));
            time.start();
        } else if (flag == 1) {
            LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "userId", bean.getData().getUser_id());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", bean.getData().getToken());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", bean.getData().getExpireTime() + "");
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", bean.getData().getRefreshToken());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
            KJActivityStack.create().finishActivity(LoginActivity.class);
            aty.finish();
        } else if (flag == 2) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            ViewInject.toast(getString(R.string.resetpwd));
            aty.finish();
            KJActivityStack.create().finishToThis(LoginActivity.class, MainActivity.class);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
        tv_registe.setEnabled(true);
    }


    @Override
    public void setPresenter(RetrievePasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        time.cancel();
        time = null;
    }

}
