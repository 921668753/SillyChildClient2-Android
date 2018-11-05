package com.sillykid.app.homepage.airporttransportation.airportselect.search;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.airporttransportation.airportselect.search.AirportSearchListViewAdapter;
import com.sillykid.app.entity.homepage.airporttransportation.airportselect.search.AirportSearchListBean;
import com.sillykid.app.entity.homepage.airporttransportation.airportselect.search.AirportSearchListBean.DataBean;
import com.sillykid.app.homepage.SearchCityClassificationActivity;
import com.sillykid.app.homepage.airporttransportation.SelectProductAirportTransportationActivity;
import com.sillykid.app.loginregister.LoginActivity;

import java.util.List;

import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 机场搜索---搜索结果
 */
public class AirportSearchListActivity extends BaseActivity implements AirportSearchListContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.ll_search, click = true)
    private LinearLayout ll_search;

    @BindView(id = R.id.tv_search)
    private TextView tv_search;

    @BindView(id = R.id.tv_cancel, click = true)
    private TextView tv_cancel;

    @BindView(id = R.id.gv_productAirportTransportation)
    private GridView gv_productAirportTransportation;

    private AirportSearchListViewAdapter mAdapter;

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

    private String name = "";
    private int type = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_airportsearchlist);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AirportSearchListPresenter(this);
        mAdapter = new AirportSearchListViewAdapter(this);
        name = getIntent().getStringExtra("name");
        type = getIntent().getIntExtra("type", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((AirportSearchListContract.Presenter) mPresenter).getAirportByName(name);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        tv_search.setText(getIntent().getStringExtra("name"));
        gv_productAirportTransportation.setAdapter(mAdapter);
        gv_productAirportTransportation.setOnItemClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_search:
                if (getIntent().getIntExtra("tag", 0) == -1) {
                    KJActivityStack.create().finishActivity(SearchCityClassificationActivity.class);
                }
                Intent intent = new Intent(aty, AirportSearchActivity.class);
                intent.putExtra("tag", 1);
                intent.putExtra("title", getIntent().getStringExtra("title"));
                intent.putExtra("type", type);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    showLoadingDialog(getString(R.string.dataLoad));
                    ((AirportSearchListContract.Presenter) mPresenter).getAirportByName(name);
                    return;
                }
                showActivity(aty, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (getIntent().getIntExtra("tag", 0) == -1) {
            KJActivityStack.create().finishActivity(SearchCityClassificationActivity.class);
        }
        Intent intent = new Intent(aty, SelectProductAirportTransportationActivity.class);
        intent.putExtra("airport_id", mAdapter.getItem(position).getCity_id());
        intent.putExtra("title", getIntent().getStringExtra("title"));
        intent.putExtra("name", mAdapter.getItem(position).getCity_name() + mAdapter.getItem(position).getAirport_name() + getIntent().getStringExtra("title"));
        intent.putExtra("type", type);
        showActivity(aty, intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {// 如果等于1
            name = data.getStringExtra("name");
            tv_search.setText(name);
            ((AirportSearchListContract.Presenter) mPresenter).getAirportByName(name);
        }
    }


    @Override
    public void setPresenter(AirportSearchListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        AirportSearchListBean airportSearchListBean = (AirportSearchListBean) JsonUtil.getInstance().json2Obj(success, AirportSearchListBean.class);
        List<DataBean> citySearchRList = airportSearchListBean.getData();
        if (citySearchRList == null || citySearchRList.size() <= 0) {
            errorMsg(getString(R.string.noData), 0);
            return;
        }
        gv_productAirportTransportation.setVisibility(View.VISIBLE);
        ll_commonError.setVisibility(View.GONE);
        mAdapter.clear();
        mAdapter.addNewData(citySearchRList);
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        gv_productAirportTransportation.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setVisibility(View.VISIBLE);
        tv_button.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            img_err.setImageResource(R.mipmap.no_login);
            tv_hintText.setVisibility(View.GONE);
            tv_button.setText(getString(R.string.login));
            showActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.noData))) {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setVisibility(View.GONE);
        } else {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        }
    }
}

