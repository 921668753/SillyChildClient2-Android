package com.sillykid.app.homepage.dynamics;

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
import com.sillykid.app.adapter.AllDynamicsViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.AllDynamicsBean;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 全部动态
 * Created by Admin on 2017/8/17.
 */

public class AllDynamicsActivity extends BaseActivity implements AllDynamicsContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;


    @BindView(id = R.id.ll_publishedTime, click = true)
    private LinearLayout ll_publishedTime;

    @BindView(id = R.id.img_publishedTime)
    private ImageView img_publishedTime;

    @BindView(id = R.id.ll_greatNumber, click = true)
    private LinearLayout ll_greatNumber;

    @BindView(id = R.id.img_greatNumber)
    private ImageView img_greatNumber;


    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private AllDynamicsViewAdapter mAdapter;

    @BindView(id = R.id.lv_allDynamics)
    private ListView lv_allDynamics;

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

    String sort_field = "time";

    String sort_type = "desc";


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_alldynamics);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new AllDynamicsPresenter(this);
        mAdapter = new AllDynamicsViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.allDynamics), true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_allDynamics.setAdapter(mAdapter);
        lv_allDynamics.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((AllDynamicsContract.Presenter) mPresenter).getAllDynamics(mMorePageNumber, sort_field, sort_type);
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
        ((AllDynamicsContract.Presenter) mPresenter).getAllDynamics(mMorePageNumber, sort_field, sort_type);
        return true;
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_publishedTime:
                sort_field = "time";
                if (sort_type.equals("desc")) {
                    sort_type = "asc";
                    img_publishedTime.setImageResource(R.mipmap.icon_up);
                } else {
                    sort_type = "desc";
                    img_publishedTime.setImageResource(R.mipmap.icon_down);
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.ll_greatNumber:
                sort_field = "praise";
                if (sort_type.equals("desc")) {
                    sort_type = "asc";
                    img_greatNumber.setImageResource(R.mipmap.icon_up);
                } else {
                    sort_type = "desc";
                    img_greatNumber.setImageResource(R.mipmap.icon_down);
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.tv_hintText:
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, DynamicsDetailsActivity.class);
        intent.putExtra("act_id", mAdapter.getItem(i).getId() + "");
        showActivity(aty, intent);
    }


    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        AllDynamicsBean allDynamicsBean = (AllDynamicsBean) JsonUtil.getInstance().json2Obj(success, AllDynamicsBean.class);
        mMorePageNumber = allDynamicsBean.getData().getP();
        totalPageNumber = allDynamicsBean.getData().getTotalPages();
        if (allDynamicsBean.getData().getList() == null || allDynamicsBean.getData().getList().size() == 0) {
            errorMsg(getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(allDynamicsBean.getData().getList());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(allDynamicsBean.getData().getList());
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
    public void setPresenter(AllDynamicsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
