package com.sillykid.app.main;

import android.content.Intent;
import android.os.Bundle;
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
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.trip.TripClassificationViewAdapter;
import com.sillykid.app.adapter.trip.TripCompanyGuideViewAdapter;
import com.sillykid.app.adapter.trip.TripGreatHousesViewAdapter;
import com.sillykid.app.adapter.trip.TripHotStrategyViewAdapter;
import com.sillykid.app.entity.TripBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.homepage.chartercustom.CharterCustomActivity;
import com.sillykid.app.homepage.chartercustom.companyguide.AllCompanyGuideActivity;
import com.sillykid.app.homepage.chartercustom.companyguide.CompanyGuideDetailsActivity;
import com.sillykid.app.homepage.homestaysubscribe.HomestayDetailsActivity;
import com.sillykid.app.homepage.homestaysubscribe.HomestaySubscribeActivity;
import com.sillykid.app.homepage.popularstrategy.HotStrategyActivity;
import com.sillykid.app.trip.strategy.StrategyDetailsActivity;
import com.sillykid.app.trip.visa.VisaActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 出行
 * Created by Admin on 2017/8/10.
 */

public class TripFragment extends BaseFragment implements TripContract.View, BGABanner.Delegate<ImageView, TripBean.ResultBean.BannerBean>, BGABanner.Adapter<ImageView, TripBean.ResultBean.BannerBean>, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    /**
     * 刷新
     */
    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    @BindView(id = R.id.gv_tripClassification)
    private NoScrollGridView gv_tripClassification;


    @BindView(id = R.id.ll_greatHouses)
    private LinearLayout ll_greatHouses;

    @BindView(id = R.id.lv_greatHouses)
    private HorizontalListView lv_greatHouses;


    @BindView(id = R.id.ll_reliableCompanyGuide)
    private LinearLayout ll_reliableCompanyGuide;

    @BindView(id = R.id.lv_reliableCompanyGuide)
    private HorizontalListView lv_reliableCompanyGuide;


    @BindView(id = R.id.ll_popularStrategy)
    private LinearLayout ll_popularStrategy;

    @BindView(id = R.id.lv_popularStrategy)
    private HorizontalListView lv_popularStrategy;

    @BindView(id = R.id.tv_greatHouses, click = true)
    private TextView tv_greatHouses;

    @BindView(id = R.id.tv_reliableCompanyGuide, click = true)
    private TextView tv_reliableCompanyGuide;

    @BindView(id = R.id.tv_popularStrategy, click = true)
    private TextView tv_popularStrategy;

    private TripClassificationViewAdapter tripClassificationViewAdapter;
    private TripHotStrategyViewAdapter tripHotStrategyViewAdapter;
    private TripGreatHousesViewAdapter tripGreatHousesViewAdapter;
    private TripCompanyGuideViewAdapter tripCompanyGuideViewAdapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_trip, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new TripPresenter(this);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        tripClassificationViewAdapter = new TripClassificationViewAdapter(aty);
        tripGreatHousesViewAdapter = new TripGreatHousesViewAdapter(aty);
        tripCompanyGuideViewAdapter = new TripCompanyGuideViewAdapter(aty);
        tripHotStrategyViewAdapter = new TripHotStrategyViewAdapter(aty);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        initBanner();
        gv_tripClassification.setAdapter(tripClassificationViewAdapter);
        gv_tripClassification.setOnItemClickListener(this);
        lv_greatHouses.setAdapter(tripGreatHousesViewAdapter);
        lv_greatHouses.setOnItemClickListener(this);
        lv_reliableCompanyGuide.setAdapter(tripCompanyGuideViewAdapter);
        lv_reliableCompanyGuide.setOnItemClickListener(this);
        lv_popularStrategy.setAdapter(tripHotStrategyViewAdapter);
        lv_popularStrategy.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.gv_tripClassification) {
            String name = tripClassificationViewAdapter.getItem(i).getName();
            if (name.contains(getString(R.string.visa))) {
                ViewInject.toast(getActivity().getString(R.string.noDevelopment));
//                Intent intent = new Intent(aty, VisaActivity.class);
//                intent.putExtra("title", getString(R.string.visa));
//                intent.putExtra("type", 0);
//                aty.showActivity(aty, intent);
            } else if (name.contains(getString(R.string.charter))) {
                aty.showActivity(aty, CharterCustomActivity.class);
            } else if (name.contains(getString(R.string.strategy))) {
                Intent intent = new Intent(aty, VisaActivity.class);
                intent.putExtra("title", getString(R.string.strategy));
                intent.putExtra("type", 1);
                aty.showActivity(aty, intent);
            } else if (name.contains(getString(R.string.gastronomy))) {
                ViewInject.toast(getActivity().getString(R.string.noDevelopment));
               // aty.showActivity(aty, GastronomyActivity.class);
            } else if (name.contains(getString(R.string.homestay))) {
                ViewInject.toast(getActivity().getString(R.string.noDevelopment));
              //  aty.showActivity(aty, HomestaySubscribeActivity.class);
            }
        } else if (adapterView.getId() == R.id.lv_greatHouses) {
            Intent intent = new Intent(aty, HomestayDetailsActivity.class);
            //    intent.putExtra("guide_id", popularStrategyHomePageViewAdapter.getItem(i).getGuide_id());
            aty.showActivity(aty, intent);
        } else if (adapterView.getId() == R.id.lv_reliableCompanyGuide) {
            Intent intent = new Intent(aty, CompanyGuideDetailsActivity.class);
            intent.putExtra("drv_id", tripCompanyGuideViewAdapter.getItem(i).getSeller_id() + "");
            aty.showActivity(aty, intent);
        } else if (adapterView.getId() == R.id.lv_popularStrategy) {
            Intent intent = new Intent(aty, StrategyDetailsActivity.class);
            intent.putExtra("guide_id", tripHotStrategyViewAdapter.getItem(i).getGuide_id());
            aty.showActivity(aty, intent);
        }
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_greatHouses:
                aty.showActivity(aty, HomestaySubscribeActivity.class);
                break;
            case R.id.tv_reliableCompanyGuide:
                aty.showActivity(aty, AllCompanyGuideActivity.class);
                break;
            case R.id.tv_popularStrategy:
                aty.showActivity(aty, HotStrategyActivity.class);
                break;
        }
    }

    @Override
    public void setPresenter(TripContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        TripBean tripBean = (TripBean) JsonUtil.getInstance().json2Obj(success, TripBean.class);
        processLogic(tripBean.getData().getBanner());
        if (tripBean.getData().getIndex() == null || tripBean.getData().getIndex().size() == 0 || tripBean.getData().getIndex().isEmpty()) {
        } else {
            tripClassificationViewAdapter.clear();
            tripClassificationViewAdapter.addNewData(tripBean.getData().getIndex());
        }

//        if (tripBean.getData().getIndex() == null || tripBean.getData().getIndex().size() == 0 || tripBean.getData().getIndex().isEmpty()) {
//        ll_greatHouses.setVisibility(View.GONE);
//        lv_greatHouses.setVisibility(View.GONE);
//        } else {
//        ll_greatHouses.setVisibility(View.VISIBLE);
//        lv_greatHouses.setVisibility(View.VISIBLE);
//            tripClassificationViewAdapter.clear();
//            tripClassificationViewAdapter.addNewData(tripBean.getData().getIndex());
//        }


        if (tripBean.getData().getReliable_drv() == null || tripBean.getData().getReliable_drv().size() == 0 || tripBean.getData().getReliable_drv().isEmpty()) {
            ll_reliableCompanyGuide.setVisibility(View.GONE);
            lv_reliableCompanyGuide.setVisibility(View.GONE);
        } else {
            ll_reliableCompanyGuide.setVisibility(View.VISIBLE);
            lv_reliableCompanyGuide.setVisibility(View.VISIBLE);
            tripCompanyGuideViewAdapter.clear();
            tripBean.getData().getReliable_drv().get(tripBean.getData().getReliable_drv().size() - 1).setStatusL("last");
            tripCompanyGuideViewAdapter.addNewData(tripBean.getData().getReliable_drv());
        }
        if (tripBean.getData().getGuideList() == null || tripBean.getData().getGuideList().size() == 0 || tripBean.getData().getGuideList().isEmpty()) {
            ll_popularStrategy.setVisibility(View.GONE);
            lv_popularStrategy.setVisibility(View.GONE);
        } else {
            ll_popularStrategy.setVisibility(View.VISIBLE);
            lv_popularStrategy.setVisibility(View.VISIBLE);
            tripHotStrategyViewAdapter.clear();
            tripBean.getData().getGuideList().get(tripBean.getData().getGuideList().size() - 1).setStatusL("last");
            tripHotStrategyViewAdapter.addNewData(tripBean.getData().getGuideList());
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
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
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<TripBean.ResultBean.BannerBean> list) {
        if (list != null && list.size() > 0) {
            mForegroundBanner.setBackground(null);
            mForegroundBanner.setData(list, null);
        }
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, TripBean.ResultBean.BannerBean model, int position) {
        GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView, R.mipmap.placeholderfigure);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, TripBean.ResultBean.BannerBean model, int position) {
        if (StringUtils.isEmpty(model.getAd_link())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getAd_link());
        bannerDetails.putExtra("title", model.getAd_name());
        aty.showActivity(aty, bannerDetails);
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((TripContract.Presenter) mPresenter).getTrip();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
