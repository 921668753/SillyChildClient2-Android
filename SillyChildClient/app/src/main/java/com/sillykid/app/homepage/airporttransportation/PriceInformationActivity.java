package com.sillykid.app.homepage.airporttransportation;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.MallBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 价格信息
 */
public class PriceInformationActivity extends BaseActivity implements PriceInformationContract.View, BGABanner.Delegate<ImageView, MallBean.DataBean.AdvcatBean>, BGABanner.Adapter<ImageView, MallBean.DataBean.AdvcatBean> {

    @BindView(id = R.id.tv_productName)
    private TextView tv_productName;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

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
        mPresenter = new PriceInformationPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.priceInformation), true, R.id.titlebar);
        initBanner();
    }

    /**
     * 初始化轮播图
     */
    public void initBanner() {
        mForegroundBanner.setFocusable(true);
        mForegroundBanner.setFocusableInTouchMode(true);
        mForegroundBanner.requestFocus();
        mForegroundBanner.requestFocusFromTouch();
        mForegroundBanner.setAutoPlayAble(true);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setAllowUserScrollable(true);
        mForegroundBanner.setAutoPlayInterval(3000);
        // 初始化方式1：配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播
        mForegroundBanner.setAdapter(this);
        mForegroundBanner.setDelegate(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mForegroundBanner.startAutoPlay();
    }


    @Override
    public void onPause() {
        super.onPause();
        mForegroundBanner.stopAutoPlay();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_nextStep:


                break;
        }
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, MallBean.DataBean.AdvcatBean model, int position) {
        //   GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView);
        GlideImageLoader.glideOrdinaryLoader(aty, model.getHttpAttUrl(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, MallBean.DataBean.AdvcatBean model, int position) {
        if (StringUtils.isEmpty(model.getUrl())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getUrl());
        bannerDetails.putExtra("title", model.getAname());
        showActivity(aty, bannerDetails);
    }

    @Override
    public void setPresenter(PriceInformationContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<MallBean.DataBean.AdvcatBean> list) {
        if (list != null && list.size() > 0) {
            if (list.size() == 1) {
                mForegroundBanner.setAutoPlayAble(false);
                mForegroundBanner.setAllowUserScrollable(false);
            } else {
                mForegroundBanner.setAutoPlayAble(true);
                mForegroundBanner.setAllowUserScrollable(true);
            }
            mForegroundBanner.setBackground(null);
            mForegroundBanner.setData(list, null);
        }
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
