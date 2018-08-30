package com.sillykid.app.homepage.airporttransportation.search;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.airporttransportation.SelectProductAirportTransportationViewAdapter;
import com.sillykid.app.entity.homepage.airporttransportation.SelectProductAirportTransportationBean;
import com.sillykid.app.homepage.airporttransportation.PriceInformationActivity;
import com.sillykid.app.loginregister.LoginActivity;

import java.util.List;

import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 产品搜索---搜索结果
 */
public class ProductSearchListActivity extends BaseActivity implements ProductSearchListContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.ll_search, click = true)
    private LinearLayout ll_search;

    @BindView(id = R.id.tv_search)
    private TextView tv_search;

    @BindView(id = R.id.tv_cancel, click = true)
    private TextView tv_cancel;


    @BindView(id = R.id.gv_productAirportTransportation)
    private GridView gv_productAirportTransportation;

    private SelectProductAirportTransportationViewAdapter mAdapter;

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

    private int type = 0;
    private String name = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_productsearchlist);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ProductSearchListPresenter(this);
        mAdapter = new SelectProductAirportTransportationViewAdapter(this);
        name = getIntent().getStringExtra("name");
        type = getIntent().getIntExtra("type", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ProductSearchListContract.Presenter) mPresenter).getProductByAirportId(name, type);
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
                Intent intent = new Intent(aty, ProductSearchActivity.class);
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
                    ((ProductSearchListContract.Presenter) mPresenter).getProductByAirportId(name, type);
                    return;
                }
                showActivity(aty, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(aty, PriceInformationActivity.class);
        intent.putExtra("product_id", mAdapter.getItem(position).getId());
        intent.putExtra("type", type);
        showActivity(aty, intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {// 如果等于1
            name = data.getStringExtra("name");
            tv_search.setText(name);
            ((ProductSearchListContract.Presenter) mPresenter).getProductByAirportId(name, type);
        }
    }


    @Override
    public void setPresenter(ProductSearchListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        SelectProductAirportTransportationBean selectProductAirportTransportationBean = (SelectProductAirportTransportationBean) JsonUtil.getInstance().json2Obj(success, SelectProductAirportTransportationBean.class);
        List<SelectProductAirportTransportationBean.DataBean> selectProductAirportTransportationList = selectProductAirportTransportationBean.getData();
        if (selectProductAirportTransportationList == null || selectProductAirportTransportationList.size() <= 0) {
            errorMsg(getString(R.string.noProduct), 0);
            return;
        }
        gv_productAirportTransportation.setVisibility(View.VISIBLE);
        ll_commonError.setVisibility(View.GONE);
        mAdapter.clear();
        mAdapter.addNewData(selectProductAirportTransportationList);
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
        } else if (msg.contains(getString(R.string.noProduct))) {
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

