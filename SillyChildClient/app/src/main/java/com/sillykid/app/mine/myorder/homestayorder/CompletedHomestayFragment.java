package com.sillykid.app.mine.myorder.homestayorder;

import android.content.Intent;
import android.os.Bundle;
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
import com.sillykid.app.R;
import com.sillykid.app.adapter.CharterOrderAdapter;
import com.sillykid.app.entity.CharterOrderBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myorder.MyOrderActivity;
import com.sillykid.app.mine.myorder.charterorder.CharterOrderContract;
import com.sillykid.app.mine.myorder.charterorder.CharterOrderPresenter;

import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**我的订单----民宿订单---已完成
 * Created by Administrator on 2017/9/2.
 */

public class CompletedHomestayFragment extends BaseFragment implements AdapterView.OnItemClickListener,BGARefreshLayout.BGARefreshLayoutDelegate,CharterOrderContract.View{
    private MyOrderActivity aty;
    private CharterOrderAdapter mAdapter;
    private CharterOrderContract.Presenter mPresenter;

    @BindView(id=R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id=R.id.lv_order)
    private ListView lv_order;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError,click = true)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.img_err)
    private ImageView img_err;
    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    private CharterOrderBean charterOrderBean;
    private List<CharterOrderBean.ResultBean.ListBean> databean;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyOrderActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_obligationcharter, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new CharterOrderPresenter(this);
        mAdapter = new CharterOrderAdapter(aty);


    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_order.setAdapter(mAdapter);
        lv_order.setOnItemClickListener(this);
//        showLoadingDialog(getString(R.string.dataLoad));
//        mPresenter.getChartOrder(NumericConstants.Completed+"");

    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.ll_commonError:
                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
                    //   PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "getCompanyGuideMessageFragment");
                    Intent intent = new Intent(aty, LoginActivity.class);
                    aty.showActivity(aty, intent);
                    break;
                }
                //  ViewInject.toast("onBGARefreshLayoutBeginRefreshing");
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
//        mRefreshLayout.endRefreshing();
//        showLoadingDialog(getString(R.string.dataLoad));
//        mPresenter.getChartOrder(StringNewConstants.All);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void setPresenter(CharterOrderContract.Presenter presenter) {

    }

    @Override
    public void getSuccess(String success, int flag) {
        charterOrderBean = (CharterOrderBean) JsonUtil.getInstance().json2Obj(success, CharterOrderBean.class);
        if (charterOrderBean==null){
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setText(getString(R.string.otherError)+getString(R.string.clickRefresh));
            dismissLoadingDialog();
            return;
        }
        if (charterOrderBean.getData().getList() == null || charterOrderBean.getData().getList().size() == 0) {
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setText(getString(R.string.youNo));
            dismissLoadingDialog();
            return;
        }
        ll_commonError.setVisibility(View.GONE);
        mAdapter.clear();
        databean=charterOrderBean.getData().getList();
        mAdapter.addNewData(databean);

        dismissLoadingDialog();

    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (flag==1){
            dismissLoadingDialog();
            ViewInject.toast(msg);
        }else{
            ll_commonError.setVisibility(View.VISIBLE);
            if (isLogin(msg)){
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
                PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
                dismissLoadingDialog();
                tv_hintText.setText(getString(R.string.login1));
                //       aty.showActivity(aty, LoginActivity.class);
                return;
            }
            tv_hintText.setText(msg);
            dismissLoadingDialog();
        }

    }
}
