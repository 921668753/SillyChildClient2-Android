package com.sillykid.app.homepage.chartercustom.routes;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.main.UserInfoBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.main.MainActivity;
import com.sillykid.app.mine.myorder.charterorder.orderdetails.CharterOrderDetailsActivity;

/**
 * 支付成功
 * Created by Admin on 2017/9/3.
 */

public class PaySuccessActivity extends BaseActivity implements PaySuccessContract.View{

    @BindView(id=R.id.tv_paymoney)
    private TextView tv_paymoney;

    @BindView(id=R.id.tv_paytype)
    private TextView tv_paytype;

    @BindView(id=R.id.tv_seeorder,click = true)
    private TextView tv_seeorder;

    @BindView(id=R.id.tv_backhome,click = true)
    private TextView tv_backhome;
    private String orderid;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_paysuccess);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new PaySuccessPresenter(this);
        orderid=getIntent().getStringExtra("orderid");
        tv_paymoney.setText(getIntent().getStringExtra("paymoney"));
        switch (getIntent().getIntExtra("paytype",-1)){
            case NumericConstants.orderBalance:
                tv_paytype.setText(getString(R.string.balancePay));
                break;
            case NumericConstants.orderWX:
                tv_paytype.setText(getString(R.string.weChatPay));
                break;
            case NumericConstants.orderAplipay:
                tv_paytype.setText(getString(R.string.alipayToPay));
                break;
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        showLoadingDialog(getString(R.string.dataLoad));
        ((PaySuccessPresenter)mPresenter).getInfo();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        Intent jumpintent;
        switch (v.getId()){
            case R.id.tv_seeorder:
                KJActivityStack.create().finishActivity(CharterOrderDetailsActivity.class);
                jumpintent=new Intent(this, CharterOrderDetailsActivity.class);
                jumpintent.putExtra("airid",orderid);
                showActivity(this,jumpintent);
                finish();
                break;
            case R.id.tv_backhome:
                jumpintent=new Intent(this, MainActivity.class);
                jumpintent.putExtra("chageIcon",0);
                showActivity(this,jumpintent);
                KJActivityStack.create().finishOthersActivity(MainActivity.class);
                finish();
                break;
        }
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.paySuccess), true, R.id.titlebar);
    }


    @Override
    public void setPresenter(PaySuccessContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        UserInfoBean userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
        if (userInfoBean!=null&&userInfoBean.getData()!=null){
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
          //  PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money", userInfoBean.getData().getUser_money());
          //  PreferenceHelper.write(aty, StringConstants.FILENAME, "user_money_fmt", userInfoBean.getData().getUser_money_fmt());
        }
        dismissLoadingDialog();

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this,LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
