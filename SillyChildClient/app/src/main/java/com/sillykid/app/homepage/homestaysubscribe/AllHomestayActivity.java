package com.sillykid.app.homepage.homestaysubscribe;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.HomestaySubscribeViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.AllHomestayBean;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 全部民宿
 * Created by Admin on 2017/8/16.
 */

public class AllHomestayActivity extends BaseActivity implements AllHomestayContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener {


    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private HomestaySubscribeViewAdapter mAdapter;

    @BindView(id = R.id.lv_allHomestay)
    private ListView lv_allHomestay;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
    /**
     * 总页码
     */
    private int totalPageNumber = NumericConstants.START_PAGE_NUMBER;
    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_allhomestay);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AllHomestayPresenter(this);
        mAdapter = new HomestaySubscribeViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.allHomestay), true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_allHomestay.setAdapter(mAdapter);
        lv_allHomestay.setOnItemClickListener(this);
//        showLoadingDialog(getString(R.string.dataLoad));
//        ((AllHomestayContract.Presenter) mPresenter).getAllHomestay(mMorePageNumber);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((AllHomestayContract.Presenter) mPresenter).getAllHomestay(mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            return false;
        }
        mMorePageNumber++;
        if (mMorePageNumber > totalPageNumber) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        showLoadingDialog(getString(R.string.dataLoad));
        ((AllHomestayContract.Presenter) mPresenter).getAllHomestay(mMorePageNumber);
        return true;
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_hintText:
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, HomestayDetailsActivity.class);
//        intent.putExtra("order_id", mAdapter.getItem(i).getid());
//        if (mAdapter.getItem(i).getStatus() != null && mAdapter.getItem(i).getStatus().equals("quote")) {
//            intent.putExtra("goods_id", mAdapter.getItem(i).getGoods_id());
//        }
        showActivity(aty, intent);
    }


    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        AllHomestayBean allHomestayBean = (AllHomestayBean) JsonUtil.getInstance().json2Obj(success, AllHomestayBean.class);
        mMorePageNumber = allHomestayBean.getData().getPage();
        totalPageNumber = allHomestayBean.getData().getPageTotal();
        if (allHomestayBean.getData().getList() == null || allHomestayBean.getData().getList().size() == 0) {
            errorMsg(getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(allHomestayBean.getData().getList());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(allHomestayBean.getData().getList());
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        //  toLigon(msg);
        isShowLoadingMore = false;
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
//        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//            mRefreshLayout.endRefreshing();
//        } else {
//            mRefreshLayout.endLoadingMore();
//        }
        if (msg.equals(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.nonetworkxxx);
        } else {
            img_err.setImageResource(R.mipmap.nocontentxxx);
        }
        dismissLoadingDialog();
    }

    @Override
    public void setPresenter(AllHomestayContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
