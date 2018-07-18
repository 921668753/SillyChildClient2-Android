package com.sillykid.app.trip.gastronomy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.trip.GastronomyViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.dialog.gastronomy.GastronomyBouncedDialog;
import com.sillykid.app.dialog.gastronomy.NearBouncedDialog;
import com.sillykid.app.dialog.gastronomy.PriceBouncedDialog;
import com.sillykid.app.entity.GastronomyBean;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 美食
 * Created by Admin on 2017/9/4.
 */

public class GastronomyActivity extends BaseActivity implements GastronomyContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.img_back, click = true)
    private TextView img_back;

    @BindView(id = R.id.accordion, click = true)
    private EditText et_checkProduct;

    @BindView(id = R.id.ll_startingpoint, click = true)
    private LinearLayout ll_startingpoint;
    @BindView(id = R.id.tv_destination)
    private TextView tv_destination;

    @BindView(id = R.id.ll_gastronomy, click = true)
    private LinearLayout ll_gastronomy;
    @BindView(id = R.id.tv_gastronomy)
    private TextView tv_gastronomy;

    @BindView(id = R.id.ll_price, click = true)
    private LinearLayout ll_price;
    @BindView(id = R.id.tv_price)
    private TextView tv_price;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private GastronomyViewAdapter mAdapter;

    @BindView(id = R.id.lv_gastronomy)
    private ListView lv_gastronomy;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
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

    private NearBouncedDialog nearBouncedDialog;

    private GastronomyBouncedDialog gastronomyBouncedDialog;

    private PriceBouncedDialog priceBouncedDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_gastronomy);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new GastronomyPresenter(this);
        mAdapter = new GastronomyViewAdapter(this);
        initNearBouncedDialog();
        initGastronomyBouncedDialog();
        initPriceBouncedDialog();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_gastronomy.setAdapter(mAdapter);
        lv_gastronomy.setOnItemClickListener(this);
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
//        showLoadingDialog(getString(R.string.dataLoad));
//        ((GastronomyContract.Presenter) mPresenter).getGastronomy(mMorePageNumber, "", "", "");
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.ll_startingpoint:
                //附近
                nearBouncedDialog.show();
                break;
            case R.id.ll_gastronomy:
                //美食
                gastronomyBouncedDialog.show();
                break;
            case R.id.ll_price:
                //价格
                priceBouncedDialog.show();
                break;
            case R.id.tv_hintText:

                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, DetailsActivity.class);
        intent.putExtra("id", "");
        showActivity(aty, intent);
    }

    /**
     * 附近
     */
    private void initNearBouncedDialog() {
        nearBouncedDialog = new NearBouncedDialog(this);
    }

    /**
     * 美食
     */
    private void initGastronomyBouncedDialog() {
        gastronomyBouncedDialog = new GastronomyBouncedDialog(this);
    }

    /**
     * 价格
     */
    private void initPriceBouncedDialog() {
        priceBouncedDialog = new PriceBouncedDialog(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((GastronomyContract.Presenter) mPresenter).getGastronomy(mMorePageNumber, "", "", "");
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
        ((GastronomyContract.Presenter) mPresenter).getGastronomy(mMorePageNumber, "", "", "");
        return true;
    }

    @Override
    public void setPresenter(GastronomyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        GastronomyBean gastronomyBean = (GastronomyBean) JsonUtil.json2Obj(success, GastronomyBean.class);
        if (gastronomyBean == null) {
            errorMsg(getString(R.string.serverReturnsDataNull1), 0);
            return;
        }
        totalPageNumber = gastronomyBean.getData().getTotalPages();
        if (gastronomyBean.getData().getList() == null || gastronomyBean.getData().getList().size() == 0) {
            errorMsg(getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(gastronomyBean.getData().getList());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(gastronomyBean.getData().getList());
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        isShowLoadingMore = false;
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
        } else {
            mRefreshLayout.endLoadingMore();
        }
        //ViewInject.toast(msg);
    }


}
