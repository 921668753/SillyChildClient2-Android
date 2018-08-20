package com.sillykid.app.homepage.bythedaycharter;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.airporttransportation.AirportTransportationClassificationGridViewAdapter;
import com.sillykid.app.adapter.homepage.airporttransportation.AirportTransportationClassificationListViewAdapter;
import com.sillykid.app.entity.homepage.airporttransportation.AirportByCountryIdBean;
import com.sillykid.app.entity.homepage.airporttransportation.AirportCountryListBean;
import com.sillykid.app.homepage.airporttransportation.AirportTransportationClassificationContract;
import com.sillykid.app.homepage.airporttransportation.AirportTransportationClassificationPresenter;

import java.util.List;

/**
 * 按天包车分类
 */
public class ByTheDayCharterClassificationActivity extends BaseActivity implements AirportTransportationClassificationContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.lv_countries)
    private ListView lv_countries;

    @BindView(id = R.id.gv_countriesClassification)
    private GridView gv_countriesClassification;

    private AirportTransportationClassificationListViewAdapter mListViewAdapter = null;

    private AirportTransportationClassificationGridViewAdapter mGridViewAdapter = null;

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
        mPresenter = new AirportTransportationClassificationPresenter(this);
        mListViewAdapter = new AirportTransportationClassificationListViewAdapter(this);
        mGridViewAdapter = new AirportTransportationClassificationGridViewAdapter(this);
        title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
        lv_countries.setAdapter(mListViewAdapter);
        lv_countries.setOnItemClickListener(this);
        gv_countriesClassification.setAdapter(mGridViewAdapter);
        gv_countriesClassification.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((AirportTransportationClassificationContract.Presenter) mPresenter).getAirportCountryList();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.lv_countries) {
            selectClassification(position);
        } else if (adapterView.getId() == R.id.gv_countriesClassification) {
            Intent intent = new Intent(aty, SelectProductActivity.class);
            intent.putExtra("airport_id", mGridViewAdapter.getItem(position).getAirport_id());
            intent.putExtra("name", mGridViewAdapter.getItem(position).getCountry_name() + mGridViewAdapter.getItem(position).getRegion_name() + mGridViewAdapter.getItem(position).getAirport_name() + title);
            intent.putExtra("type", type);
            showActivity(aty, intent);
        }
    }

    @Override
    public void setPresenter(AirportTransportationClassificationContract.Presenter presenter) {
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
            AirportByCountryIdBean airportByCountryIdBean = (AirportByCountryIdBean) JsonUtil.getInstance().json2Obj(success, AirportByCountryIdBean.class);
            mGridViewAdapter.clear();
            mGridViewAdapter.addNewData(airportByCountryIdBean.getData());
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
                ((AirportTransportationClassificationContract.Presenter) mPresenter).getAirportByCountryId(airportCountryBean.getCountry_id());
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
        ViewInject.toast(msg);
    }

}
