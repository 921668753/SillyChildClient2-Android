package com.sillykid.app.community.dynamic;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.HomePageBean.ResultBean.AdBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 动态详情
 */
public class DynamicDetailsActivity extends BaseActivity implements DynamicDetailsContract.View, BGABanner.Delegate<ImageView, AdBean>, BGABanner.Adapter<ImageView, AdBean> {

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    @BindView(id = R.id.ll_author, click = true)
    private LinearLayout ll_author;

    @BindView(id = R.id.img_head)
    private ImageView img_head;

    @BindView(id = R.id.tv_nickName)
    private TextView tv_nickName;

    @BindView(id = R.id.tv_follow, click = true)
    private TextView tv_follow;

    private int id = 0;

    private String title = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_dynamicdetails);
    }

    @Override
    public void initData() {
        super.initData();
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        mPresenter = new DynamicDetailsPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((DynamicDetailsContract.Presenter) mPresenter).getDynamicDetails(id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
        initBanner();
    }

    /**
     * 初始化轮播图
     */
    public void initBanner() {
        mForegroundBanner.setAutoPlayAble(true);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setAllowUserScrollable(true);
        mForegroundBanner.setAutoPlayInterval(3000);
        // 初始化方式1：配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播
        mForegroundBanner.setAdapter(this);
        mForegroundBanner.setDelegate(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_author:

                break;
            case R.id.tv_follow:
                showLoadingDialog(getString(R.string.dataLoad));
                //((DynamicDetailsContract.Presenter)mPresenter).postAddConcern();
                break;
        }
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<AdBean> list) {
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
    public void fillBannerItem(BGABanner banner, ImageView itemView, AdBean model, int position) {
        GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, AdBean model, int position) {
        if (StringUtils.isEmpty(model.getAd_link())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getAd_link());
        bannerDetails.putExtra("title", model.getAd_name());
        showActivity(aty, bannerDetails);
    }


    @Override
    public void setPresenter(DynamicDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {

        } else if (flag == 1) {

        } else if (flag == 2) {

        } else if (flag == 3) {

        } else if (flag == 4) {

        } else if (flag == 5) {

        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
        } else {
            ViewInject.toast(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


}
