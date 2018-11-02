package com.sillykid.app.mine.mycollection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.AllLineViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.AllRoutesBean;

import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的收藏中的路线
 * Created by Administrator on 2017/9/2.
 */

public class RouteFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, CollectionContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_companyGuideMassage)
    private ListView lv_companyGuideMassage;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.img_err)
    private ImageView img_err;
    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;

    private MyCollectionActivity aty;
    private AllLineViewAdapter mAdapter;
    private Intent jumpintent;
    private boolean isShowLoadingMore;
    private int mMorePageNumber = 1;
    private int totalPageNumber;
    private int currentitem = 0;
    private AllRoutesBean allRoutesBean;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyCollectionActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_route, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CollectionPresenter(this);
        mAdapter = new AllLineViewAdapter(aty);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_companyGuideMassage.setAdapter(mAdapter);
        lv_companyGuideMassage.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_hintText:
                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                    //   PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "getCompanyGuideMessageFragment");
                    Intent intent = new Intent(aty, LoginActivity.class);
                    aty.showActivity(aty, intent);
                    tv_hintText.setText(getString(R.string.clickRefresh));
                    break;
                }
                //  ViewInject.toast("onBGARefreshLayoutBeginRefreshing");
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        // ((MyCollectionPresenter) mPresenter).getRouteCollection(0,mMorePageNumber);
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
        //     ((MyCollectionPresenter) mPresenter).getRouteCollection(0,mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(CollectionContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        Log.d("调试", "收藏路线" + success);
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        allRoutesBean = (AllRoutesBean) JsonUtil.json2Obj(success, AllRoutesBean.class);
        if (allRoutesBean == null) {
            errorMsg(getString(R.string.serverReturnsDataNull1), 0);
            return;
        }
        totalPageNumber = allRoutesBean.getData().getTotalPages();
        mMorePageNumber = StringUtils.toInt(allRoutesBean.getData().getP());
        if (allRoutesBean.getData().getList() == null || allRoutesBean.getData().getList().size() == 0) {
            errorMsg(getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(allRoutesBean.getData().getList());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(allRoutesBean.getData().getList());
        }
        mRefreshLayout.endRefreshing();
        mAdapter.clear();
        mAdapter.addNewData(allRoutesBean.getData().getList());
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        isShowLoadingMore = false;
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            dismissLoadingDialog();
            tv_hintText.setText(getString(R.string.login1));
            return;
        }
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        if (msg.equals(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.nonetworkxxx);
        } else {
            img_err.setImageResource(R.mipmap.nocontentxxx);
        }
        dismissLoadingDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        currentitem = i;
//        jumpintent = new Intent(aty, RouteDetailsActivity.class);
//        jumpintent.putExtra("line_id", mAdapter.getItem(i).getLine_id() + "");
//        jumpintent.putExtra("line_title", mAdapter.getItem(i).getLine_title());
//        jumpintent.putExtra("line_price", mAdapter.getItem(i).getLine_price());
        startActivityForResult(jumpintent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 0 && data != null) {
            mAdapter.removeItem(currentitem);
        }
    }
}
