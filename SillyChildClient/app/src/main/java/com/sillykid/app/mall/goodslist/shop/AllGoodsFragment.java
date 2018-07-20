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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mall.goodslist.shop.AllGoodsViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mall.goodslist.shop.AllGoodsBean;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SpacesItemDecoration;
import com.sillykid.app.mall.goodslist.goodsdetails.GoodsDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 全部商品
 * Created by Admin on 2017/8/21.
 */

public class AllGoodsFragment extends BaseFragment implements AllGoodsContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    private ShopActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.tv_comprehensive, click = true)
    private TextView tv_comprehensive;

    @BindView(id = R.id.tv_sales, click = true)
    private TextView tv_sales;

    @BindView(id = R.id.tv_price, click = true)
    private TextView tv_price;

    /**
     * 商品列表
     */
    @BindView(id = R.id.rv)
    private RecyclerView recyclerView;

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


    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;


    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;

    private AllGoodsViewAdapter mAdapter;

    private StaggeredGridLayoutManager layoutManager;

    private SpacesItemDecoration spacesItemDecoration;

    private int storeid = 0;
    private String order = "desc";
    private int key = 0;
    private List<AllGoodsBean.DataBean> list = null;
    private Thread thread = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (ShopActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_allgoods, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new AllGoodsPresenter(this);
        mAdapter = new AllGoodsViewAdapter(recyclerView);
        spacesItemDecoration = new SpacesItemDecoration(7, 14);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//不设置的话，图片闪烁错位，有可能有整列错位的情况。
        storeid = aty.getIntent().getIntExtra("storeid", 0);
        list = new ArrayList<AllGoodsBean.DataBean>();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        initRecyclerView();
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 设置RecyclerView控件部分属性
     */
    private void initRecyclerView() {
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        //设置item之间的间隔
        recyclerView.addItemDecoration(spacesItemDecoration);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnRVItemClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_comprehensive:
                key = 0;
                if (order.equals("desc")) {
                    order = "asc";
                } else {
                    order = "desc";
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.tv_sales:
                key = 3;
                if (order.equals("desc")) {
                    order = "asc";
                } else {
                    order = "desc";
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.tv_price:
                key = 2;
                if (order.equals("desc")) {
                    order = "asc";
                } else {
                    order = "desc";
                }
                mRefreshLayout.beginRefreshing();
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

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("goodName", mAdapter.getItem(position).getName());
        intent.putExtra("goodsid", mAdapter.getItem(position).getGoods_id());
        aty.showActivity(aty, intent);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((AllGoodsContract.Presenter) mPresenter).getStoreGoodsList(storeid, key, order, mMorePageNumber, "");
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        mMorePageNumber++;
        showLoadingDialog(getString(R.string.dataLoad));
        ((AllGoodsContract.Presenter) mPresenter).getStoreGoodsList(storeid, key, order, mMorePageNumber, "");
        return true;
    }

    @Override
    public void setPresenter(AllGoodsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        mRefreshLayout.setPullDownRefreshEnable(true);
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        AllGoodsBean allGoodsBean = (AllGoodsBean) JsonUtil.getInstance().json2Obj(success, AllGoodsBean.class);
        if (allGoodsBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                allGoodsBean.getData().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            errorMsg(getString(R.string.noale), 1);
            return;
        } else if (allGoodsBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                allGoodsBean.getData().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
            ViewInject.toast(getString(R.string.noMoreData));
            isShowLoadingMore = false;
            dismissLoadingDialog();
            mRefreshLayout.endLoadingMore();
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
                for (int i = 0; i < allGoodsBean.getData().size(); i++) {
                    Bitmap bitmap = GlideImageLoader.load(aty, allGoodsBean.getData().get(i).getThumbnail());
                    if (bitmap != null) {
                        allGoodsBean.getData().get(i).setHeight(bitmap.getHeight());
                        allGoodsBean.getData().get(i).setWidth(bitmap.getWidth());
                    }
                    list.add(allGoodsBean.getData().get(i));
                }
                aty.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                            mRefreshLayout.endRefreshing();
                            mAdapter.clear();
                            mAdapter.addNewData(list);
                        } else {
                            mRefreshLayout.endLoadingMore();
                            mAdapter.addMoreData(list);
                        }
                        dismissLoadingDialog();
                    }
                });
            }
        });
        thread.start();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        //  if (flag == 0) {
        isShowLoadingMore = false;
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
        } else {
            mRefreshLayout.endLoadingMore();
        }
        mRefreshLayout.setPullDownRefreshEnable(false);
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setVisibility(View.VISIBLE);
        tv_button.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            img_err.setImageResource(R.mipmap.no_login);
            tv_hintText.setVisibility(View.GONE);
            tv_button.setText(getString(R.string.login));
            // ViewInject.toast(getString(R.string.reloginPrompting));
            aty.showActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.noAddress))) {
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
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
        list.clear();
        list = null;
        if (thread != null) {
            thread.interrupted();
        }
        thread = null;
    }

}
