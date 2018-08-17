package com.sillykid.app.homepage.airporttransportation;

import android.widget.EditText;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.sillykid.app.R;
import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 价格信息
 */
public class PriceInformationActivity extends BaseActivity implements PriceInformationContract.View {

    @BindView(id = R.id.tv_productName)
    private TextView tv_productName;

    @BindView(id = R.id.banner_ad)
    private BGABanner banner_ad;

    @BindView(id = R.id.tv_modelCar)
    private TextView tv_modelCar;

    @BindView(id = R.id.tv_canTakeNumber)
    private TextView tv_canTakeNumber;

    @BindView(id = R.id.tv_carPrices)
    private TextView tv_carPrices;

    @BindView(id = R.id.gv_containsService)
    private NoScrollGridView gv_containsService;


    @BindView(id = R.id.tv_serviceDescription)
    private TextView tv_serviceDescription;

    @BindView(id = R.id.et_remark)
    private EditText et_remark;


    @BindView(id = R.id.tv_nextStep, click = true)
    private TextView tv_nextStep;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_priceinformation);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.priceInformation), true, R.id.titlebar);
    }


    @Override
    public void setPresenter(PriceInformationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }


}
