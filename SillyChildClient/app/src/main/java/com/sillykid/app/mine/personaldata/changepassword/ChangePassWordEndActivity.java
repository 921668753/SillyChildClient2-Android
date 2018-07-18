package com.sillykid.app.mine.personaldata.changepassword;

import android.content.Intent;
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
import com.sillykid.app.R;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.main.MainActivity;

/**
 * 我的--个人资料--修改密码第二步
 * Created by Administrator on 2017/9/2.
 */

public class ChangePassWordEndActivity extends BaseActivity implements ChangePassWordContract.View{

    private ChangePassWordContract.Presenter mPresenter;

    @BindView(id=R.id.et_pwd)
    private EditText et_pwd;

    @BindView(id=R.id.et_pwdagain)
    private EditText et_pwdagain;

    @BindView(id=R.id.tv_change,click = true)
    private TextView tv_change;
    private String phone;
    private String code;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_changepasswordend);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new ChangePassWordPresenter(this);
        Intent tagintent=getIntent();
        phone=tagintent.getStringExtra("phone");
        code=tagintent.getStringExtra("code");

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();


    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.changepasswordsecond), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        showLoadingDialog(getString(R.string.submissionLoad));
        mPresenter.changePassWord(phone,code,et_pwd.getText().toString(),et_pwdagain.getText().toString());

    }

    @Override
    public void setPresenter(ChangePassWordContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
//        switch (flag){
//            case 1:
        dismissLoadingDialog();
        PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
        finish();
        KJActivityStack.create().finishToThis(LoginActivity.class,MainActivity.class);
//                mPresenter.relogin(phone,et_pwd.getText().toString());
//                break;
//            case 2:
//                dismissLoadingDialog();
//                finish();
//                break;
//        }
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
        ViewInject.toast(this,msg);
    }
}