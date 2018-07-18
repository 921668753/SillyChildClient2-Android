package com.sillykid.app.homepage.popularstrategy;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.HotStrategyViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.HotStrategyBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.trip.strategy.StrategyDetailsActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 热门攻略/地区攻略列表
 * Created by Admin on 2017/9/8.
 */

public class HotStrategyActivity extends BaseActivity implements HotStrategyContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private HotStrategyViewAdapter mAdapter;

    @BindView(id = R.id.lv_regionalStrategy)
    private ListView lv_regionalStrategy;

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
    private String title;
    private String city;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_hotstrategy);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new HotStrategyPresenter(this);
        mAdapter = new HotStrategyViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        title = getIntent().getStringExtra("title");
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_regionalStrategy.setAdapter(mAdapter);
        lv_regionalStrategy.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        if (StringUtils.isEmpty(title)) {
            title = getString(R.string.popularStrategy);
            city = getIntent().getStringExtra("city");
            if (StringUtils.isEmpty(city) || city.equals(getString(R.string.allAeservationNumber))) {
                city = "";
            }
        }
        ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
        mRefreshLayout.beginRefreshing();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "AllOrderFragment");
        Intent intent = new Intent(aty, StrategyDetailsActivity.class);
        intent.putExtra("guide_id", mAdapter.getItem(i).getGuide_id());
//        intent.putExtra("designation", "AllOrderFragment");
        showActivity(aty, intent);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        if (StringUtils.isEmpty(title) || title.equals(getString(R.string.popularStrategy))) {
            ((HotStrategyContract.Presenter) mPresenter).getHotStrategy(city, mMorePageNumber);
        } else {
            ((HotStrategyContract.Presenter) mPresenter).getStrategy(getIntent().getStringExtra("country_name"), mMorePageNumber);
        }
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
        if (StringUtils.isEmpty(title) || title.equals(getString(R.string.popularStrategy))) {
            ((HotStrategyContract.Presenter) mPresenter).getHotStrategy(city, mMorePageNumber);
        } else {
            ((HotStrategyContract.Presenter) mPresenter).getStrategy(getIntent().getStringExtra("country_name"), mMorePageNumber);
        }
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
                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                    //   PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "getHotStrategyFragment");
                    Intent intent = new Intent(aty, LoginActivity.class);
                    showActivity(aty, intent);
                    break;
                }
                //  ViewInject.toast("onBGARefreshLayoutBeginRefreshing");
                mRefreshLayout.beginRefreshing();
                break;
        }
    }


    @Override
    public void setPresenter(HotStrategyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        HotStrategyBean orderBean = (HotStrategyBean) JsonUtil.getInstance().json2Obj(success, HotStrategyBean.class);
        totalPageNumber = orderBean.getData().getTotalPages();
        if (orderBean.getData().getList() == null || orderBean.getData().getList().size() == 0) {
            errorMsg(getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mAdapter.clear();
            mAdapter.addNewData(orderBean.getData().getList());
            mRefreshLayout.endRefreshing();
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(orderBean.getData().getList());
        }
        dismissLoadingDialog();

    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            tv_hintText.setText(getString(R.string.login1));
//            aty.showActivity(aty, LoginActivity.class);
            return;
        }
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
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
