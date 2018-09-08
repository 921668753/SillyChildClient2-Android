package com.sillykid.app.homepage.boutiqueline.selectcity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.airporttransportation.AirportTransportationClassificationListViewAdapter;
import com.sillykid.app.adapter.homepage.boutiqueline.selectcity.SelectCityGridViewAdapter;
import com.sillykid.app.entity.homepage.airporttransportation.AirportCountryListBean;
import com.sillykid.app.entity.homepage.boutiqueline.selectcity.SelectCityBean;

import java.util.List;

/**
 * 选择城市
 */
public class SelectCityActivity extends BaseActivity implements SelectCityContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.lv_countries)
    private ListView lv_countries;

    @BindView(id = R.id.gv_countriesClassification)
    private GridView gv_countriesClassification;

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

    private AirportTransportationClassificationListViewAdapter mListViewAdapter = null;

    private SelectCityGridViewAdapter mGridViewAdapter = null;

    private List<AirportCountryListBean.DataBean> airportCountryList;

    private AirportCountryListBean.DataBean airportCountryBean = null;

    private String title = "";
    private int type = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bythedaycharterclassification);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SelectCityPresenter(this);
        mListViewAdapter = new AirportTransportationClassificationListViewAdapter(this);
        mGridViewAdapter = new SelectCityGridViewAdapter(this);
        // title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.selectCity), true, R.id.titlebar);
        lv_countries.setAdapter(mListViewAdapter);
        lv_countries.setOnItemClickListener(this);
        gv_countriesClassification.setAdapter(mGridViewAdapter);
        gv_countriesClassification.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((SelectCityContract.Presenter) mPresenter).getAirportCountryList();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    ((SelectCityContract.Presenter) mPresenter).getRouteRegion(airportCountryBean.getCountry_id());
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.lv_countries) {
            selectClassification(position);
        } else if (adapterView.getId() == R.id.gv_countriesClassification) {
            Intent intent = new Intent();
            intent.putExtra("region_id", mGridViewAdapter.getItem(position).getRegion_id());
            intent.putExtra("region_name", mGridViewAdapter.getItem(position).getCountry_name() + mGridViewAdapter.getItem(position).getRegion_name());
            intent.putExtra("type", type);
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void setPresenter(SelectCityContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            AirportCountryListBean airportCountryListBean = (AirportCountryListBean) JsonUtil.getInstance().json2Obj(success, AirportCountryListBean.class);
            airportCountryList = airportCountryListBean.getData();
            if (airportCountryListBean != null && airportCountryList.size() > 0) {
                selectClassification(0);
            }
        } else if (flag == 1) {
            SelectCityBean selectCityBean = (SelectCityBean) JsonUtil.getInstance().json2Obj(success, SelectCityBean.class);
            if (selectCityBean.getData() == null || selectCityBean.getData().size() <= 0) {
                errorMsg(getString(R.string.noData), 1);
                return;
            }
            ll_commonError.setVisibility(View.GONE);
            gv_countriesClassification.setVisibility(View.VISIBLE);
            mGridViewAdapter.clear();
            mGridViewAdapter.addNewData(selectCityBean.getData());
            dismissLoadingDialog();
        }
    }

    /**
     * 选中分类
     *
     * @param position
     */
    private void selectClassification(int position) {
        for (int i = 0; i < airportCountryList.size(); i++) {
            if (position == i || position == i && position == 0) {
                airportCountryBean = airportCountryList.get(i);
                airportCountryBean.setIsSelected(1);
                ((SelectCityContract.Presenter) mPresenter).getRouteRegion(airportCountryBean.getCountry_id());
            } else {
                airportCountryList.get(i).setIsSelected(0);
            }
        }
        mListViewAdapter.clear();
        mListViewAdapter.addNewData(airportCountryList);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 1) {
            ll_commonError.setVisibility(View.VISIBLE);
            gv_countriesClassification.setVisibility(View.GONE);
            if (msg.contains(getString(R.string.checkNetwork))) {
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
            return;
        }
        ViewInject.toast(msg);
    }

}
