package com.sillykid.app.mine.myorder.orderevaluation;

import android.view.View;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ImagePagerActivity extends BaseActivity implements BGABanner.Delegate<PhotoView, String>, BGABanner.Adapter<PhotoView, String>{

    @BindView(id=R.id.banner_guide_content)
    private BGABanner mForegroundBanner;

    private ArrayList<String> urls;
    private int position=0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bannerimages);
    }

    @Override
    public void initData() {
        super.initData();
        urls=getIntent().getStringArrayListExtra("urls");
        position=getIntent().getIntExtra("position",0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initBanner();
        processLogic(urls);
        mForegroundBanner.setCurrentItem(position);
    }

    /**
     * 初始化轮播图
     */

    public void initBanner() {
        mForegroundBanner.setAutoPlayAble(false);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setAllowUserScrollable(true);
        // 初始化方式1：配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播
        mForegroundBanner.setAdapter(this);
        mForegroundBanner.setDelegate(this);
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<String> list) {
        if (list != null && list.size() > 0) {
            mForegroundBanner.setBackground(null);
            mForegroundBanner.setData(R.layout.bgabanner_item_phoneview,list, null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void fillBannerItem(BGABanner banner, PhotoView itemView, String model, int position) {
        itemView.enable();
        itemView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        GlideImageLoader.glideOrdinaryLoader(aty, model, itemView, R.mipmap.placeholderfigure1);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, PhotoView itemView, String model, int position) {

    }
}
