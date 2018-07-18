package com.sillykid.app.mine.mywallet.paymentpasswordmanagement.setpaymentpassword;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.PayPwdEditText;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.loginregister.LoginActivity;


/**
 * 设置支付密码
 * Created by Administrator on 2017/12/12.
 */

public class SetPaymentPassword1Activity extends BaseActivity implements SetPaymentPasswordContract.View {

    /**
     * 设置支付密码
     */
    @BindView(id = R.id.tv_setPaymentPassword)
    private TextView tv_setPaymentPassword;

    /**
     * 支付密码输入框
     */
    @BindView(id = R.id.et_paymentPassword)
    private PayPwdEditText et_paymentPassword;

    /**
     * 下一步
     */
    @BindView(id = R.id.tv_nextStep, click = true)
    private TextView tv_nextStep;
    private String oldPaymentPassword = "";
    private String paymentPassword = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_paymentpassword);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new SetPaymentPasswordPresenter(this);
        oldPaymentPassword = getIntent().getStringExtra("paymentPassword");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tv_nextStep.setClickable(false);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.setPaymentPassword), true, R.id.titlebar);
        et_paymentPassword.initStyle(R.drawable.edit_num_bg, 6, 0.33f, R.color.bEBEC0Colors, R.color.f2222Colors, 20);
        et_paymentPassword.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调
                paymentPassword = str;
                if (str.length() == 6) {
                    tv_nextStep.setClickable(true);
                    tv_nextStep.setBackgroundResource(R.drawable.shape_login);
                } else {
                    tv_nextStep.setClickable(false);
                    tv_nextStep.setBackgroundResource(R.drawable.shape_login1);
                }
            }
        });
        tv_setPaymentPassword.setText(getString(R.string.pleaseEnterConfirm));
        tv_nextStep.setText(getString(R.string.determine));
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_nextStep:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((SetPaymentPasswordContract.Presenter) mPresenter).postSetPaymentPassword(oldPaymentPassword, paymentPassword);
                break;
        }

    }

    @Override
    public void setPresenter(SetPaymentPasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        KJActivityStack.create().finishActivity(SetPaymentPasswordActivity.class);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "is_pay_password", 1);
        finish();
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
