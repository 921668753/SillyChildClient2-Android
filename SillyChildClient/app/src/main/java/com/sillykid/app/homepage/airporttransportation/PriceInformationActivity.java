package com.sillykid.app.homepage.airporttransportation;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.airporttransportation.PriceInformationViewAdapter;
import com.sillykid.app.entity.homepage.airporttransportation.PriceInformationBean;
import com.sillykid.app.homepage.airporttransportation.airportdropoff.AirportDropOffActivity;
import com.sillykid.app.homepage.airporttransportation.airportpickup.AirportPickupActivity;
import com.sillykid.app.homepage.airporttransportation.comments.CharterCommentsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 价格信息
 */
public class PriceInformationActivity extends BaseActivity implements PriceInformationContract.View, BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String> {

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

    @BindView(id = R.id.tv_containsService)
    private TextView tv_containsService;

    @BindView(id = R.id.gv_containsService)
    private NoScrollGridView gv_containsService;


    @BindView(id = R.id.tv_serviceDescription)
    private TextView tv_serviceDescription;

    @BindView(id = R.id.tv_remark)
    private TextView tv_remark;

    @BindView(id = R.id.ll_userEvaluation, click = true)
    private LinearLayout ll_userEvaluation;

    @BindView(id = R.id.tv_nextStep, click = true)
    private TextView tv_nextStep;

    private int product_id = 0;
    private int type = 0;

    private PriceInformationViewAdapter mAdapter;

    private PriceInformationBean priceInformationBean;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_priceinformation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new PriceInformationPresenter(this);
        product_id = getIntent().getIntExtra("product_id", 0);
        type = getIntent().getIntExtra("type", 0);
        mAdapter = new PriceInformationViewAdapter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((PriceInformationContract.Presenter) mPresenter).getProductDetails(product_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.priceInformation), true, R.id.titlebar);
        initBanner();
        gv_containsService.setAdapter(mAdapter);
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
            case R.id.ll_userEvaluation:
                Intent intent = new Intent(aty, CharterCommentsActivity.class);
                intent.putExtra("product_id", product_id);
                showActivity(aty, intent);
                break;
            case R.id.tv_nextStep:
                if (priceInformationBean == null || priceInformationBean.getData() == null || StringUtils.isEmpty(priceInformationBean.getData().getTitle())) {
                    return;
                }
                Intent intent1 = new Intent();
                if (type == 1) {
                    intent1.setClass(aty, AirportPickupActivity.class);
                } else {
                    intent1.setClass(aty, AirportDropOffActivity.class);
                }
                intent1.putExtra("airport_name", priceInformationBean.getData().getAirport_name());
                intent1.putExtra("title", priceInformationBean.getData().getTitle());
                intent1.putExtra("baggage_number", priceInformationBean.getData().getBaggage_number());
                intent1.putExtra("passenger_number", priceInformationBean.getData().getPassenger_number());
                if (priceInformationBean.getData().getPicture() != null && priceInformationBean.getData().getPicture().size() > 0) {
                    intent1.putExtra("picture", priceInformationBean.getData().getPicture().get(0));
                }
                intent1.putExtra("product_id", product_id);
                showActivity(aty, intent1);
                break;
        }
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        //   GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView);
        itemView.setScaleType(ImageView.ScaleType.FIT_XY);
        GlideImageLoader.glideOrdinaryLoader(aty, model, itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
//        if (StringUtils.isEmpty(model.getUrl())) {
//            return;
//        }
//        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
//        bannerDetails.putExtra("url", model.getUrl());
//        bannerDetails.putExtra("title", model.getAname());
//        showActivity(aty, bannerDetails);
    }

    @Override
    public void setPresenter(PriceInformationContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            priceInformationBean = (PriceInformationBean) JsonUtil.getInstance().json2Obj(success, PriceInformationBean.class);
            tv_productName.setText(priceInformationBean.getData().getTitle());
            if (priceInformationBean.getData().getPicture() != null && priceInformationBean.getData().getPicture().size() > 0) {
                mForegroundBanner.setVisibility(View.VISIBLE);
                processLogic(priceInformationBean.getData().getPicture());
            } else {
                mForegroundBanner.setVisibility(View.GONE);
            }
            tv_modelCar.setText(priceInformationBean.getData().getModel());
            tv_canTakeNumber.setText(priceInformationBean.getData().getPassenger());
            tv_carPrices.setText(MathUtil.keepTwo(StringUtils.toDouble(priceInformationBean.getData().getPrice())));
            if (priceInformationBean.getData() != null && priceInformationBean.getData().getService() != null && priceInformationBean.getData().getService().size() > 0) {
                gv_containsService.setVisibility(View.VISIBLE);
                tv_containsService.setVisibility(View.VISIBLE);
                mAdapter.clear();
                mAdapter.addNewData(priceInformationBean.getData().getService());
            } else {
                tv_containsService.setVisibility(View.GONE);
                gv_containsService.setVisibility(View.GONE);
            }
            tv_serviceDescription.setText(priceInformationBean.getData().getService_description());
            tv_remark.setText(priceInformationBean.getData().getService_note());

        }
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<String> list) {
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
