package com.sillykid.app.homepage.bythedaycharter.cityselect.search;

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
import com.sillykid.app.adapter.homepage.bythedaycharter.cityselect.search.CitySearchListViewAdapter;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.CitySearchRListBean;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.CitySearchRListBean.DataBean;
import com.sillykid.app.homepage.SearchCityClassificationActivity;
import com.sillykid.app.homepage.bythedaycharter.SelectProductActivity;
import com.sillykid.app.loginregister.LoginActivity;

import java.util.List;

import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 城市搜索---搜索结果
 */
public class CharterCitySearchListActivity extends BaseActivity implements CharterCitySearchListContract.View, AdapterView.OnItemClickListener {

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
    private String title;
    private int type;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_productsearchlist);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CharterCitySearchListPresenter(this);
        mAdapter = new CitySearchListViewAdapter(this);
        name = getIntent().getStringExtra("name");
        title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterCitySearchListContract.Presenter) mPresenter).getCityByName(name);
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
                Intent intent = new Intent(aty, CharterCitySearchActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("type", type);
                intent.putExtra("tag", 1);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    showLoadingDialog(getString(R.string.dataLoad));
                    ((CharterCitySearchListContract.Presenter) mPresenter).getCityByName(name);
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
        Intent intent = new Intent(aty, SelectProductActivity.class);
        intent.putExtra("country_id", mAdapter.getItem(position).getCountry_id());
        intent.putExtra("country_name", mAdapter.getItem(position).getCountry_name());
        intent.putExtra("region_id", mAdapter.getItem(position).getCity_id());
        intent.putExtra("title", title);
        intent.putExtra("name", mAdapter.getItem(position).getCountry_name() + mAdapter.getItem(position).getCity_name() + title);
        intent.putExtra("type", type);
        // 设置结果 结果码，一个数据
//            aty.setResult(RESULT_OK, intent);
//            aty.finish();
        showActivity(aty, intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {// 如果等于1
            name = data.getStringExtra("name");
            tv_search.setText(name);
            ((CharterCitySearchListContract.Presenter) mPresenter).getCityByName(name);
        }
    }


    @Override
    public void setPresenter(CharterCitySearchListContract.Presenter presenter) {
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

