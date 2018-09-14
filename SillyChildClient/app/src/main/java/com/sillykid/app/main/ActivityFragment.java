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
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.ActivityBean.DataBean.BannerListBean;
import com.sillykid.app.activity.ActivityDetailActivity;
import com.sillykid.app.activity.PastWonderfulActivity;
import com.sillykid.app.adapter.main.activity.ActivityViewAdapter;
import com.sillykid.app.entity.main.ActivityBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 活动
 */
public class ActivityFragment extends BaseFragment implements ActivityContract.View, BGABanner.Delegate<ImageView, BannerListBean>, BGABanner.Adapter<ImageView, BannerListBean>, BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    /**
     * 往期活动
     */
    @BindView(id = R.id.ll_product1, click = true)
    private LinearLayout ll_product1;

    @BindView(id = R.id.clv_popularActivities)
    private ChildListView clv_popularActivities;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    private ActivityViewAdapter mAdapter;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_activity, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new ActivityPresenter(this);
        mAdapter = new ActivityViewAdapter(aty);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        initBanner();
        clv_popularActivities.setAdapter(mAdapter);
        clv_popularActivities.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ActivityContract.Presenter) mPresenter).getProcessingActivity();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_product1:
                aty.showActivity(aty, PastWonderfulActivity.class);
                break;
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                aty.showActivity(aty, LoginActivity.class);
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
    public void setPresenter(ActivityContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ActivityBean activityBean = (ActivityBean) JsonUtil.json2Obj(success, ActivityBean.class);
            if (activityBean.getData() == null) {
                errorMsg(getString(R.string.serverReturnsDataError), 0);
                return;
            }
            ll_commonError.setVisibility(View.GONE);
            if (activityBean != null && activityBean.getData().getBanner_list().size() > 0) {
                mForegroundBanner.setVisibility(View.VISIBLE);
                processLogic(activityBean.getData().getBanner_list());
            } else {
                mForegroundBanner.setVisibility(View.GONE);
            }
            if (activityBean.getData().getActivity_list() == null || activityBean.getData().getActivity_list().size() <= 0) {
                //  ll_product1.setVisibility(View.GONE);
                clv_popularActivities.setVisibility(View.GONE);
                errorMsg(getString(R.string.noActivity), 0);
            } else {
                //   ll_product1.setVisibility(View.VISIBLE);

                clv_popularActivities.setVisibility(View.VISIBLE);
                mAdapter.clear();
                mAdapter.addNewData(activityBean.getData().getActivity_list());
            }
            dismissLoadingDialog();
        }
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<BannerListBean> list) {
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
        mRefreshLayout.endRefreshing();
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setVisibility(View.VISIBLE);
        tv_button.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            img_err.setImageResource(R.mipmap.no_login);
            tv_hintText.setVisibility(View.GONE);
            tv_button.setText(getString(R.string.login));
            // ViewInject.toast(getString(R.string.reloginPrompting));
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.noActivity))) {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setVisibility(View.GONE);
        } else {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(aty, ActivityDetailActivity.class);
        intent.putExtra("activity_state", mAdapter.getItem(position).getActivity_state());
        intent.putExtra("id", mAdapter.getItem(position).getId());
        intent.putExtra("main_picture", mAdapter.getItem(position).getMain_picture());
        intent.putExtra("title", mAdapter.getItem(position).getTitle());
        intent.putExtra("subtitle", mAdapter.getItem(position).getSubtitle());
        aty.showActivity(aty, intent);
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, BannerListBean model, int position) {
        GlideImageLoader.glideOrdinaryLoader(aty, model.getPicture(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, BannerListBean model, int position) {
        if (StringUtils.isEmpty(model.getLink_address())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getLink_address());
        bannerDetails.putExtra("title", model.getPicture_name());
        aty.showActivity(aty, bannerDetails);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((ActivityContract.Presenter) mPresenter).getProcessingActivity();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }


}
