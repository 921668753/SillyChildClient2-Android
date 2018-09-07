package com.sillykid.app.loginregister.bindingaccount;

import android.content.Intent;
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
import com.sillykid.app.loginregister.SelectCountryCodeActivity;
import com.sillykid.app.loginregister.register.RegistrationAgreementActivity;

import static android.app.Activity.RESULT_OK;

/**
 * 绑定 手机号
 * Created by Admin on 2017/8/10.
 */

public class BindingPhoneFragment extends BaseFragment implements BindingContract.View {

    private BindingAccountActivity aty;

    /**
     * 倒计时内部类
     */
    private TimeCount time;

    /**
     * 注册协议
     */
    @BindView(id = R.id.ll_agreement)
    private LinearLayout ll_agreement;

    @BindView(id = R.id.tv_agreement, click = true)
    private TextView tv_agreement;

    /**
     * 国码
     */
    @BindView(id = R.id.ll_areaCode, click = true)
    private LinearLayout ll_areaCode;

    @BindView(id = R.id.tv_areaCode)
    private TextView tv_areaCode;

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
     * 绑定
     */
    @BindView(id = R.id.tv_binding, click = true)
    private TextView tv_binding;

    /**
     * opt	String
     * 验证码类型 reg=注册 restpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    private String opt = "bind";

    private String countroy_code = "86";

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (BindingAccountActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_bindingphone, null);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new BindingPresenter(this);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
    }

    /**
     * 渲染view
     */
    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_areaCode:
                Intent intent = new Intent(aty, SelectCountryCodeActivity.class);
                startActivityForResult(intent, 1);
//                aty.showActivity(aty, intent);
                break;
            case R.id.tv_code:
                showLoadingDialog(getString(R.string.sendingLoad));
                ((BindingContract.Presenter) mPresenter).postCode(et_phone.getText().toString(), countroy_code, opt);
                break;
            case R.id.tv_binding:
                tv_binding.setEnabled(false);
                showLoadingDialog(getString(R.string.submissionLoad));
                ((BindingContract.Presenter) mPresenter).postBindingPhone(getActivity().getIntent().getStringExtra("openid"),
                        getActivity().getIntent().getStringExtra("from"), et_phone.getText().toString(), countroy_code, et_code.getText().toString(), "");
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
        tv_binding.setEnabled(true);
        if (flag == 0) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.testget));
            time.start();
        } else if (flag == 1) {
            ((BindingContract.Presenter) mPresenter).postThirdToLogin(getActivity().getIntent().getStringExtra("openid"),
                    getActivity().getIntent().getStringExtra("from"), getActivity().getIntent().getStringExtra("nickname"), getActivity().getIntent().getStringExtra("head_pic"), getActivity().getIntent().getIntExtra("sex", 0));
        } else if (flag == 2) {
            dismissLoadingDialog();
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", false);
            KJActivityStack.create().finishActivity(LoginActivity.class);
            aty.finish();
        } else if (flag == 3) {
            LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "email", bean.getData().getEmail());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "accountNumber", bean.getData().getMobile());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", bean.getData().getToken());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", bean.getData().getExpireTime());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", false);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "mobile", bean.getData().getMobile());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "head_pic", bean.getData().getHead_pic());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "nickname", bean.getData().getNickname());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "countroy_code", bean.getData().getCountroy_code());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "userId", bean.getData().getUser_id());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "hx_user_name", bean.getData().getHx_user_name());
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "hx_password", bean.getData().getHx_password());
//            ((BindingContract.Presenter) mPresenter).loginHuanXin(bean.getData().getHx_user_name(), bean.getData().getHx_password());
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        aty.runOnUiThread(new Runnable() {
            public void run() {
                ViewInject.toast(msg);
            }
        });
        //   ViewInject.toast(msg);
        tv_binding.setEnabled(true);
    }


    @Override
    public void setPresenter(BindingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        time.cancel();
        time = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {// 如果等于1
            // 说明是我们的那次请求
            // 目的：区分请求，不同的请求要做不同的处理
            countroy_code = data.getStringExtra("areaCode");
            tv_areaCode.setText("+" + countroy_code);
        }
    }
}
