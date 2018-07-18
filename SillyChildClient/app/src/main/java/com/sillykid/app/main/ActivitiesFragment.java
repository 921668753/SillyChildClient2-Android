package com.sillykid.app.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.HorizontalListView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.main.activities.BargainViewAdapter;
import com.sillykid.app.adapter.main.activities.ProductlViewAdapter;
import com.sillykid.app.entity.main.ActivitiesBean;
import com.sillykid.app.entity.main.AdvCatBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.mall.goodslist.GoodsListActivity;
import com.sillykid.app.mall.goodslist.goodsdetails.GoodsDetailsActivity;
import com.sillykid.app.mall.search.SearchGoodsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 活动
 */
public class ActivitiesFragment extends BaseFragment implements ActivitiesContract.View, BGABanner.Delegate<ImageView, AdvCatBean.DataBean>, BGABanner.Adapter<ImageView, AdvCatBean.DataBean>, BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener, BGAOnRVItemClickListener {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 搜索
     */
    @BindView(id = R.id.ll_search, click = true)
    private LinearLayout ll_search;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    /**
     * 特价商品
     */
    @BindView(id = R.id.ll_bargain, click = true)
    private LinearLayout ll_bargain;

    @BindView(id = R.id.tv_seeMore, click = true)
    private TextView tv_seeMore;

    @BindView(id = R.id.hlv_bargain)
    private HorizontalListView hlv_bargain;

    /**
     * 当季热销
     */
    @BindView(id = R.id.tv_productlSeeMore, click = true)
    private TextView tv_productlSeeMore;

    @BindView(id = R.id.rv)
    private RecyclerView recyclerview;

    private SpacesItemDecoration spacesItemDecoration;

    private ProductlViewAdapter productlViewAdapter;

    private BargainViewAdapter bargainViewAdapter;

    private Thread thread = null;

    private List<ActivitiesBean.DataBean.MonthHotBean> list = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_activities, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new ActivitiesPresenter(this);
        bargainViewAdapter = new BargainViewAdapter(aty, hlv_bargain);
        spacesItemDecoration = new SpacesItemDecoration(7, 14);
        productlViewAdapter = new ProductlViewAdapter(recyclerview);
        list = new ArrayList<ActivitiesBean.DataBean.MonthHotBean>();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        initBanner();
        hlv_bargain.setAdapter(bargainViewAdapter);
        hlv_bargain.setOnItemClickListener(this);
        initRecyclerView();
        showLoadingDialog(getString(R.string.dataLoad));
        ((ActivitiesContract.Presenter) mPresenter).getAdvCat();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_search:
                aty.showActivity(aty, SearchGoodsActivity.class);
                break;
            case R.id.tv_seeMore:
                Intent goodsListIntent = new Intent(aty, GoodsListActivity.class);
                goodsListIntent.putExtra("mark", "85");
                aty.showActivity(aty, goodsListIntent);
                break;
            case R.id.tv_productlSeeMore:
                Intent goodsListIntent1 = new Intent(aty, GoodsListActivity.class);
                goodsListIntent1.putExtra("mark", "86");
                aty.showActivity(aty, goodsListIntent1);
                break;
        }
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
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
        //设置item之间的间隔
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(productlViewAdapter);
        productlViewAdapter.setOnRVItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (aty.getChageIcon() == 2) {
            mForegroundBanner.startAutoPlay();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (aty.getChageIcon() == 2) {
            mForegroundBanner.stopAutoPlay();
        }
    }

    @Override
    public void setPresenter(ActivitiesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            AdvCatBean advCatBean = (AdvCatBean) JsonUtil.json2Obj(success, AdvCatBean.class);
            if (advCatBean != null && advCatBean.getData().size() > 0) {
                processLogic(advCatBean.getData());
            }
            ((ActivitiesContract.Presenter) mPresenter).getActivities();
        } else if (flag == 1) {
            ActivitiesBean activitiesBean = (ActivitiesBean) JsonUtil.json2Obj(success, ActivitiesBean.class);
            if (activitiesBean.getData().getSpecial() == null || activitiesBean.getData().getSpecial().size() <= 0) {
                ll_bargain.setVisibility(View.GONE);
                hlv_bargain.setVisibility(View.GONE);
            } else {
                ll_bargain.setVisibility(View.VISIBLE);
                hlv_bargain.setVisibility(View.VISIBLE);
                bargainViewAdapter.clear();
                activitiesBean.getData().getSpecial().get(activitiesBean.getData().getSpecial().size() - 1).setStatus("last");
                bargainViewAdapter.addNewData(activitiesBean.getData().getSpecial());
            }
            if (activitiesBean.getData().getMonthHot() == null || activitiesBean.getData().getMonthHot().size() <= 0) {
                dismissLoadingDialog();
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
                    for (int i = 0; i < activitiesBean.getData().getMonthHot().size(); i++) {
                        Bitmap bitmap = GlideImageLoader.load(aty, activitiesBean.getData().getMonthHot().get(i).getThumbnail());
                        if (bitmap != null) {
                            activitiesBean.getData().getMonthHot().get(i).setHeight(bitmap.getHeight());
                            activitiesBean.getData().getMonthHot().get(i).setWidth(bitmap.getWidth());
                        }
                        list.add(activitiesBean.getData().getMonthHot().get(i));
                    }
                    aty.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            productlViewAdapter.clear();
                            productlViewAdapter.addNewData(list);
                            dismissLoadingDialog();
                        }
                    });
                }
            });
            thread.start();
        }
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<AdvCatBean.DataBean> list) {
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
        if (flag == 1 && isLogin(msg)) {
            Intent intent = new Intent(aty, LoginActivity.class);
            aty.showActivity(aty, intent);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("goodName", bargainViewAdapter.getItem(position).getName());
        intent.putExtra("goodsid", bargainViewAdapter.getItem(position).getGoods_id());
        aty.showActivity(aty, intent);
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("goodName", productlViewAdapter.getItem(position).getName());
        intent.putExtra("goodsid", productlViewAdapter.getItem(position).getGoods_id());
        aty.showActivity(aty, intent);
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, AdvCatBean.DataBean model, int position) {
        GlideImageLoader.glideOrdinaryLoader(aty, model.getHttpAttUrl(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, AdvCatBean.DataBean model, int position) {
        if (StringUtils.isEmpty(model.getUrl())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getUrl());
        bannerDetails.putExtra("title", model.getAname());
        aty.showActivity(aty, bannerDetails);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((ActivitiesContract.Presenter) mPresenter).getActivities();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bargainViewAdapter.clear();
        bargainViewAdapter = null;
        list.clear();
        list = null;
        if (thread != null) {
            thread.interrupted();
        }
        thread = null;
        productlViewAdapter.clear();
        productlViewAdapter = null;
    }


}
