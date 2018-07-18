package com.sillykid.app.trip.visa;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.trip.VisaViewAdapter;
import com.sillykid.app.entity.VisaBean;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 签证
 * Created by Admin on 2017/8/19.
 */

public class VisaActivity extends BaseActivity implements VisaContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private VisaViewAdapter mAdapter;

    @BindView(id = R.id.lv_visa)
    private ListView lv_visa;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_visa);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new VisaPresenter(this);
        mAdapter = new VisaViewAdapter(this, getIntent().getIntExtra("type", 0));
    }

    @Override
    public void initWidget() {
        super.initWidget();
        String title = getIntent().getStringExtra("title");
        ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        lv_visa.setAdapter(mAdapter);
        //  lv_visa.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(aty, StrategyDetailsActivity.class);
//        intent.putExtra("guide_id", mAdapter.getItem(i).getGuide_id());
////        intent.putExtra("designation", "AllOrderFragment");
//        showActivity(aty, intent);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((VisaContract.Presenter) mPresenter).getVisa();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
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
    public void setPresenter(VisaContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        VisaBean visaBean = (VisaBean) JsonUtil.getInstance().json2Obj(success, VisaBean.class);
        if (visaBean.getData() == null || visaBean.getData().size() == 0) {
            errorMsg(getString(R.string.serverReturnsDataNull), 0);
            return;
        }
        mAdapter.clear();
        List list = new ArrayList();
        for (int i = 0; i < visaBean.getData().size(); i++) {
            if (visaBean.getData().get(i).getList() != null && visaBean.getData().get(i).getList().size() > 0) {
                list.add(visaBean.getData().get(i));
            }
        }
        mAdapter.addNewData(list);
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
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
