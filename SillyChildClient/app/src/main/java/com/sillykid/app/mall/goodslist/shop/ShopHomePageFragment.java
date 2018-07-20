package com.sillykid.app.mall.goodslist.shop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mall.goodslist.shop.ShopHomePageViewAdapter;
import com.sillykid.app.entity.mall.goodslist.shop.ShopHomePageBean;
import com.sillykid.app.entity.mall.goodslist.shop.StoreImageBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.utils.SpacesItemDecoration;
import com.sillykid.app.mall.goodslist.goodsdetails.GoodsDetailsActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 店铺首页
 * Created by Admin on 2017/8/21.
 */

public class ShopHomePageFragment extends BaseFragment implements ShopHomePageContract.View, BGABanner.Delegate<ImageView, StoreImageBean.DataBean>, BGABanner.Adapter<ImageView, StoreImageBean.DataBean>, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    private ShopActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    /**
     * 商品列表
     */
    @BindView(id = R.id.rv)
    private RecyclerView recyclerview;

    private SpacesItemDecoration spacesItemDecoration;

    private StaggeredGridLayoutManager layoutManager;

    private ShopHomePageViewAdapter shopHomepageAdapter;

    private int storeid = 0;

    private Thread thread = null;

    private List<ShopHomePageBean.DataBean> list = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (ShopActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_shophomepage, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new ShopHomePagePresenter(this);
        spacesItemDecoration = new SpacesItemDecoration(7, 14);
        shopHomepageAdapter = new ShopHomePageViewAdapter(recyclerview);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        list = new ArrayList<ShopHomePageBean.DataBean>();
        storeid = aty.getIntent().getIntExtra("storeid", 0);
        ((ShopHomePageContract.Presenter) mPresenter).getStoreImage(storeid);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        initBanner();
        initRecyclerView();
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

    /**
     * 设置RecyclerView控件部分属性
     */
    private void initRecyclerView() {
        layoutManager.setAutoMeasureEnabled(true);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        //设置item之间的间隔
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(shopHomepageAdapter);
        shopHomepageAdapter.setOnRVItemClickListener(this);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.startAutoPlay();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.stopAutoPlay();
        }
    }


    @Override
    public void setPresenter(ShopHomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            StoreImageBean storeImageBean = (StoreImageBean) JsonUtil.getInstance().json2Obj(success, StoreImageBean.class);
            List<StoreImageBean.DataBean> advCatBeanList = storeImageBean.getData();
            if (advCatBeanList != null && advCatBeanList.size() > 0) {
                processLogic(advCatBeanList);
            }
            ((ShopHomePageContract.Presenter) mPresenter).getStoreIndexGoods(storeid);
        } else if (flag == 1) {
            ShopHomePageBean shopHomePageBean = (ShopHomePageBean) JsonUtil.getInstance().json2Obj(success, ShopHomePageBean.class);
            if (shopHomePageBean.getData() == null || shopHomePageBean.getData().size() == 0) {
                errorMsg(getString(R.string.noale), 1);
                return;
            }
            if (thread != null && !thread.isAlive()) {
                thread.run();
                return;
            }
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    list.clear();
                    for (int i = 0; i < shopHomePageBean.getData().size(); i++) {
                        Bitmap bitmap = GlideImageLoader.load(aty, shopHomePageBean.getData().get(i).getThumbnail());
                        if (bitmap != null) {
                            shopHomePageBean.getData().get(i).setHeight(bitmap.getHeight());
                            shopHomePageBean.getData().get(i).setWidth(bitmap.getWidth());
                        }
                        list.add(shopHomePageBean.getData().get(i));
                    }
                    aty.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            shopHomepageAdapter.clear();
                            shopHomepageAdapter.addNewData(list);
                            dismissLoadingDialog();
                        }
                    });
                }
            });
            thread.start();
        }
        // dismissLoadingDialog();
    }


    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<StoreImageBean.DataBean> list) {
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
        ViewInject.toast(msg);
        dismissLoadingDialog();
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("goodName", shopHomepageAdapter.getItem(position).getName());
        intent.putExtra("goodsid", shopHomepageAdapter.getItem(position).getGoods_id());
        aty.showActivity(aty, intent);
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, StoreImageBean.DataBean model, int position) {
        GlideImageLoader.glideOrdinaryLoader(aty, model.getImg(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, StoreImageBean.DataBean model, int position) {
        if (StringUtils.isEmpty(model.getSilde_url())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getSilde_url());
        // bannerDetails.putExtra("title", model.get());
        aty.showActivity(aty, bannerDetails);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((ShopHomePageContract.Presenter) mPresenter).getStoreIndexGoods(storeid);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shopHomepageAdapter.clear();
        shopHomepageAdapter = null;
        list.clear();
        list = null;
        if (thread != null) {
            thread.interrupted();
        }
        thread = null;
    }
}
