package com.sillykid.app.homepage.privatecustom.cityselect;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.privatecustom.cityselect.CitySearchListViewAdapter;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.CitySearchRListBean;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.CitySearchRListBean.DataBean;
import com.sillykid.app.loginregister.LoginActivity;

import java.util.List;

import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 城市搜索---搜索结果
 */
public class CitySearchListActivity extends BaseActivity implements CitySearchListContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.ll_search, click = true)
    private LinearLayout ll_search;

    @BindView(id = R.id.tv_search)
    private TextView tv_search;

    @BindView(id = R.id.tv_cancel, click = true)
    private TextView tv_cancel;


    @BindView(id = R.id.gv_productAirportTransportation)
    private GridView gv_productAirportTransportation;

    private CitySearchListViewAdapter mAdapter;

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

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_productsearchlist);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CitySearchListPresenter(this);
        mAdapter = new CitySearchListViewAdapter(this);
        name = getIntent().getStringExtra("name");
        showLoadingDialog(getString(R.string.dataLoad));
        ((CitySearchListContract.Presenter) mPresenter).getAreaByName(name);
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
                Intent intent = new Intent(aty, CitySearchActivity.class);
                intent.putExtra("type", getIntent().getIntExtra("type", 0));
                intent.putExtra("tag", 1);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    showLoadingDialog(getString(R.string.dataLoad));
                    ((CitySearchListContract.Presenter) mPresenter).getAreaByName(name);
                    return;
                }
                showActivity(aty, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "CitySearchListcountry_id", mAdapter.getItem(position).getCountry_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "CitySearchListcountry_name", mAdapter.getItem(position).getCountry_name());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "CitySearchListcity_id", mAdapter.getItem(position).getCity_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "CitySearchListcity_name", mAdapter.getItem(position).getCity_name());
        RxBus.getInstance().post(new MsgEvent<String>("RxBusCitySearchListEvent"));
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {// 如果等于1
            name = data.getStringExtra("name");
            tv_search.setText(name);
            ((CitySearchListContract.Presenter) mPresenter).getAreaByName(name);
        }
    }


    @Override
    public void setPresenter(CitySearchListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        CitySearchRListBean citySearchRListBean = (CitySearchRListBean) JsonUtil.getInstance().json2Obj(success, CitySearchRListBean.class);
        List<DataBean> citySearchRList = citySearchRListBean.getData();
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

