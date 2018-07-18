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
import com.sillykid.app.adapter.CharterListViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.CharterListBean;
import com.sillykid.app.homepage.chartercustom.chartercommon.CharterDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的收藏中的包车
 * Created by Administrator on 2017/9/2.
 */

public class CharterCollectionFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate,MyCollectionContract.View,AdapterView.OnItemClickListener{

    @BindView(id=R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id=R.id.lv_companyGuideMassage)
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

    private MyCollectionActivity1 aty;
    private CharterListViewAdapter mAdapter;
    private Intent jumpintent;
    private boolean isShowLoadingMore;
    private int mMorePageNumber=1;
    private int totalPageNumber;
    private int currentitem=0;
    private CharterListBean charterListBean;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyCollectionActivity1) getActivity();
        return View.inflate(aty, R.layout.fragment_route, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter=new MyCollectionPresenter(this);
        mAdapter = new CharterListViewAdapter(aty);
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
        switch (v.getId()){
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
       // ((MyCollectionPresenter) mPresenter).getCharterCollection(mMorePageNumber,NumericConstants.LOADCOUNT);
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
      //  ((MyCollectionPresenter) mPresenter).getCharterCollection(mMorePageNumber,NumericConstants.LOADCOUNT);
        return true;
    }

    @Override
    public void setPresenter(MyCollectionContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        Log.d("调试","收藏包车"+success);
        isShowLoadingMore = true;
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        charterListBean = (CharterListBean) JsonUtil.getInstance().json2Obj(success, CharterListBean.class);
        if (charterListBean == null) {
            errorMsg(getString(R.string.serverReturnsDataNull1), 0);
            return;
        }
        totalPageNumber = charterListBean.getData().getTotalPages();
        mMorePageNumber = StringUtils.toInt(charterListBean.getData().getP());
        if (charterListBean.getData().getList() == null || charterListBean.getData().getList().size() == 0) {
            errorMsg(getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(charterListBean.getData().getList());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(charterListBean.getData().getList());
        }
        mRefreshLayout.endRefreshing();
        mAdapter.clear();
        mAdapter.addNewData(charterListBean.getData().getList());
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
        currentitem=i;
        jumpintent = new Intent(aty, CharterDetailsActivity.class);
        jumpintent.putExtra("id", mAdapter.getItem(i).getId());
        jumpintent.putExtra("type", mAdapter.getItem(i).getType());
        startActivityForResult(jumpintent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==0&&data!=null){
            mAdapter.removeItem(currentitem);
        }
    }
}
