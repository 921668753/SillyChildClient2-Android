package com.sillykid.app.homepage.localtalent;

import android.content.Intent;
import android.util.Log;
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
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.adapter.LocalTalentViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.LocalTalentBean;
import com.sillykid.app.homepage.addressselection.newoverseas.NewOverseasCityActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 当地达人
 * Created by Admin on 2017/8/15.
 */
public class LocalTalentActivity extends BaseActivity implements LocalTalentContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_address, click = true)
    private TextView tv_address;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private LocalTalentViewAdapter mAdapter;

    @BindView(id = R.id.lv_localtalent1)
    private ListView lv_localtalent1;

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
    private String locationCity;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_localtalent);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new LocalTalentPresenter(this);
        mAdapter = new LocalTalentViewAdapter(this);
    }

    /**
     * 初始化控件
     */
    @SuppressWarnings("deprecation")
    @Override
    public void initWidget() {
        super.initWidget();
        tv_title.setText(getString(R.string.localTalent));
        locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
        tv_address.setText(locationCity);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_localtalent1.setAdapter(mAdapter);
        lv_localtalent1.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((LocalTalentContract.Presenter) mPresenter).getLocalTalent(mMorePageNumber, locationCity);
    }


    /**
     * @param refreshLayout 刷新
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((LocalTalentContract.Presenter) mPresenter).getLocalTalent(mMorePageNumber, locationCity);
    }

    /**
     * @param refreshLayout 加载
     */
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
        ((LocalTalentContract.Presenter) mPresenter).getLocalTalent(mMorePageNumber, locationCity);
        return true;
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_address:
                Intent intent = new Intent();
                intent.setClass(aty, NewOverseasCityActivity.class);
                startActivityForResult(intent, STATUS);
                break;
            case R.id.tv_hintText:
                mRefreshLayout.beginRefreshing();
                break;
        }
    }


    /**
     * @param adapterView item点击事件
     * @param view        view
     * @param i           i
     * @param l           i
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, LocalTalentDetailsActivity.class);
        intent.putExtra("talent_id", mAdapter.getItem(i).getTalent_id());
        showActivity(aty, intent);
    }

    @Override
    public void errorMsg(String msg, int flag) {
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
    public void setPresenter(LocalTalentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        LocalTalentBean LocalTalentBean = (LocalTalentBean) JsonUtil.getInstance().json2Obj(success, LocalTalentBean.class);
        totalPageNumber = LocalTalentBean.getData().getTotalPages();
        if (LocalTalentBean.getData().getList() == null || LocalTalentBean.getData().getList().size() == 0) {
            errorMsg(getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(LocalTalentBean.getData().getList());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(LocalTalentBean.getData().getList());
        }
        dismissLoadingDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", selectCity);
            locationCity = selectCity;
            int selectCityId = data.getIntExtra("selectCityId", 0);
            tv_address.setText(selectCity);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshingHomePageFragment", true);
            showLoadingDialog(getString(R.string.dataLoad));
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((LocalTalentContract.Presenter) mPresenter).getLocalTalent(mMorePageNumber, locationCity);
            Log.d("tag888", selectCity);
        }
    }

}
