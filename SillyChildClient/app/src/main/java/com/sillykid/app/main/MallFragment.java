package com.sillykid.app.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.common.cklibrary.utils.myview.ScrollInterceptScrollView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.main.mall.MallClassificationViewAdapter;
import com.sillykid.app.adapter.main.mall.MallViewAdapter;
import com.sillykid.app.entity.main.MallBean;
import com.sillykid.app.entity.main.MallBean.DataBean.AdvcatBean;
import com.sillykid.app.entity.main.MallBean.DataBean.ApiCatTreeBean;
import com.sillykid.app.entity.main.MallBean.DataBean.HomePageBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.mall.goodslist.GoodsListActivity;
import com.sillykid.app.mall.moreclassification.MoreClassificationActivity;
import com.sillykid.app.mall.search.SearchGoodsActivity;
import com.sillykid.app.utils.SpacesItemDecoration;
import com.sillykid.app.mall.goodslist.goodsdetails.GoodsDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 商城首页
 * Created by Admin on 2017/8/10.
 */
@SuppressLint("NewApi")
public class MallFragment extends BaseFragment implements View.OnScrollChangeListener, AdapterView.OnItemClickListener, MallContract.View, BGABanner.Delegate<ImageView, AdvcatBean>, BGABanner.Adapter<ImageView, AdvcatBean>, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.ll_title, click = true)
    private LinearLayout ll_title;

    @BindView(id = R.id.img_search)
    private ImageView img_search;

//    @BindView(id = R.id.et_search)
//    private EditText et_search;

    @BindView(id = R.id.ll_title1, click = true)
    private LinearLayout ll_title1;

    @BindView(id = R.id.img_search1)
    private ImageView img_search1;

//    @BindView(id = R.id.et_search1)
//    private EditText et_search1;


    @BindView(id = R.id.sv_home)
    private ScrollInterceptScrollView sv_home;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    /**
     * 分类
     */
    @BindView(id = R.id.gv_classification)
    private NoScrollGridView gv_classification;

    /**
     * 商品列表
     */
    @BindView(id = R.id.rv)
    private RecyclerView recyclerview;

    private SpacesItemDecoration spacesItemDecoration = null;

    private MallViewAdapter mallHomePageViewAdapter = null;

    private MallClassificationViewAdapter homePageClassificationViewAdapter = null;

    private Thread thread = null;

    private List<HomePageBean> list = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mall, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MallPresenter(this);
        homePageClassificationViewAdapter = new MallClassificationViewAdapter(aty);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        spacesItemDecoration = new SpacesItemDecoration(7, 14);
        mallHomePageViewAdapter = new MallViewAdapter(recyclerview);
        list = new ArrayList<HomePageBean>();
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        initBanner();
        sv_home.setOnScrollChangeListener(this);
        gv_classification.setAdapter(homePageClassificationViewAdapter);
        gv_classification.setOnItemClickListener(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        //设置item之间的间隔
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(mallHomePageViewAdapter);
        mallHomePageViewAdapter.setOnRVItemClickListener(this);
        //  mallHomePageViewAdapter.addMoreData(addList());
        showLoadingDialog(aty.getString(R.string.dataLoad));
        ((MallContract.Presenter) mPresenter).getMall();
    }

    /**
     * @param v 控件监听事件
     */
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_title:
            case R.id.ll_title1:
                aty.showActivity(aty, SearchGoodsActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(MallContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            MallBean mallHomePageBean = (MallBean) JsonUtil.json2Obj(success, MallBean.class);
            List<AdvcatBean> advCatBeanList = mallHomePageBean.getData().getAdvcat();
            if (advCatBeanList != null && advCatBeanList.size() > 0) {
                processLogic(advCatBeanList);
            }
            List<ApiCatTreeBean> apiCatTreeBeanList = mallHomePageBean.getData().getApiCatTree();
            if (apiCatTreeBeanList != null && apiCatTreeBeanList.size() > 0) {
                gv_classification.setVisibility(View.VISIBLE);
                homePageClassificationViewAdapter.clear();
                homePageClassificationViewAdapter.addMoreData(apiCatTreeBeanList);
            } else {
                gv_classification.setVisibility(View.GONE);
            }
            if (mallHomePageBean.getData().getHomePage() == null || mallHomePageBean.getData().getHomePage().size() <= 0) {
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
                    for (int i = 0; i < mallHomePageBean.getData().getHomePage().size(); i++) {
                        Bitmap bitmap = GlideImageLoader.load(aty, mallHomePageBean.getData().getHomePage().get(i).getThumbnail());
                        if (bitmap != null) {
                            mallHomePageBean.getData().getHomePage().get(i).setHeight(bitmap.getHeight());
                            mallHomePageBean.getData().getHomePage().get(i).setWidth(bitmap.getWidth());
                        }
                        list.add(mallHomePageBean.getData().getHomePage().get(i));
                    }
                    aty.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mallHomePageViewAdapter.clear();
                            mallHomePageViewAdapter.addNewData(list);
                            dismissLoadingDialog();
                        }
                    });
                }
            });
            thread.start();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 1 && isLogin(msg)) {
            Intent intent = new Intent(aty, LoginActivity.class);
            // intent.putExtra("name", "GetOrderFragment");
            aty.showActivity(aty, intent);
            return;
        }
        ViewInject.toast(msg);
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

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<AdvcatBean> list) {
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
    public void fillBannerItem(BGABanner banner, ImageView itemView, AdvcatBean model, int position) {
        //   GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView);
        GlideImageLoader.glideOrdinaryLoader(aty, model.getHttpAttUrl(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, AdvcatBean model, int position) {
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
        ((MallContract.Presenter) mPresenter).getMall();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY <= 0) {
            ll_title1.setVisibility(View.VISIBLE);
            ll_title.setVisibility(View.GONE);
//            ll_title.setBackgroundColor(Color.TRANSPARENT);
//            img_search.setImageDrawable(null);
//            et_search.setHintTextColor(Color.TRANSPARENT);
        } else {
            ll_title1.setVisibility(View.GONE);
            ll_title.setVisibility(View.VISIBLE);
//            ll_title.setBackgroundColor(Color.TRANSPARENT);
//            img_search.setImageDrawable(null);
//            et_search.setHintTextColor(getResources().getColor(R.color.hintColors));
        }
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("goodName", mallHomePageViewAdapter.getItem(position).getName());
        intent.putExtra("goodsid", mallHomePageViewAdapter.getItem(position).getGoods_id());
        aty.showActivity(aty, intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (homePageClassificationViewAdapter.getItem(i).getCat_id() == -1) {
            Intent moreClassificationIntent = new Intent(aty, MoreClassificationActivity.class);
            aty.showActivity(aty, moreClassificationIntent);
            return;
        }
        Intent beautyCareIntent = new Intent(aty, GoodsListActivity.class);
        beautyCareIntent.putExtra("cat", homePageClassificationViewAdapter.getItem(i).getCat_id());
        aty.showActivity(aty, beautyCareIntent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mallHomePageViewAdapter.clear();
        mallHomePageViewAdapter = null;
        list.clear();
        list = null;
        if (thread != null) {
            thread.interrupted();
        }
        thread = null;
    }
}