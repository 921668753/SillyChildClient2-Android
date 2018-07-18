package com.sillykid.app.mine.mywallet.paymentpasswordmanagement.modifypaymentpassword;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.loginregister.LoginActivity;


/**
 * 修改支付密码
 * Created by Administrator on 2017/12/13.
 */

public class ModifyPaymentPassword2Activity extends BaseActivity implements ModifyPaymentPasswordContract.View {


    /**
     * 姓名
     */
    @BindView(id = R.id.tv_name)
    private TextView tv_name;

    /**
     * 身份证输入框
     */
    @BindView(id = R.id.et_IdNumber)
    private EditText et_IdNumber;

    /**
     * 下一步
     */
    @BindView(id = R.id.tv_nextStep, click = true)
    private TextView tv_nextStep;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_modifypaymentpassword2);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new ModifyPaymentPasswordPresenter(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.modifyPaymentPassword), true, R.id.titlebar);
        String real_name = PreferenceHelper.readString(aty, StringConstants.FILENAME, "real_name", "");
        if (real_name.length() == 2) {
            tv_name.setText("*" + real_name.substring(1));
        } else if (real_name.length() == 3) {
            tv_name.setText("**" + real_name.substring(2));
        } else if (real_name.length() > 3) {
            tv_name.setText("**" + real_name.substring(real_name.length() - 1));
        } else {
            tv_name.setText("**");
        }
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_nextStep:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((ModifyPaymentPasswordContract.Presenter) mPresenter).postVerifyIdNumber(et_IdNumber.getText().toString().trim());
                break;
        }
    }

    @Override
    public void setPresenter(ModifyPaymentPasswordContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        showActivity(aty, ModifyPaymentPassword4Activity.class);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            showActivity(aty, LoginActivity.class);
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }
}
