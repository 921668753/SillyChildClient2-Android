package com.sillykid.app.homepage.airporttransportation.airportselect.fragment;

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
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.airporttransportation.airportselect.fragment.AirportClassificationViewAdapter;
import com.sillykid.app.adapter.homepage.airporttransportation.airportselect.fragment.CountryClassificationViewAdapter;
import com.sillykid.app.entity.homepage.airporttransportation.AirportByCountryIdBean;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.fragment.CityClassificationBean;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.fragment.CityClassificationBean.DataBean;
import com.sillykid.app.homepage.airporttransportation.SelectProductAirportTransportationActivity;
import com.sillykid.app.homepage.airporttransportation.airportselect.AirportSelectActivity;

import java.util.List;

/**
 * 城市分类列表
 */
public class AirportClassificationFragment extends BaseFragment implements AirportClassificationContract.View, AdapterView.OnItemClickListener {

    private AirportSelectActivity aty;

    @BindView(id = R.id.lv_country)
    private ListView lv_country;


    @BindView(id = R.id.lv_city)
    private ListView lv_city;

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

    private int classification_id = 0;

    private CountryClassificationViewAdapter mCountryAdapter;
    private List<DataBean> countryClassificationList;
    private DataBean countryBean;
    private AirportClassificationViewAdapter mAirportAdapter;
    private List<AirportByCountryIdBean.DataBean> airportClassificationList;
    private AirportByCountryIdBean.DataBean airportBean;
    private String title = "";
    private int type = 0;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (AirportSelectActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_cityclassification, null);
    }


    @Override
    protected void initData() {
        super.initData();
        mPresenter = new AirportClassificationPresenter(this);
        mCountryAdapter = new CountryClassificationViewAdapter(aty);
        mAirportAdapter = new AirportClassificationViewAdapter(aty);
        title = aty.getIntent().getStringExtra("title");
        type = aty.getIntent().getIntExtra("type", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((AirportClassificationContract.Presenter) mPresenter).getCountryAreaListByParentid(aty, classification_id, type, 0);
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        lv_country.setAdapter(mCountryAdapter);
        lv_country.setOnItemClickListener(this);
        lv_city.setAdapter(mAirportAdapter);
        lv_city.setOnItemClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    ((AirportClassificationContract.Presenter) mPresenter).getCountryAreaListByParentid(aty, countryBean.getId(), type, 0);
                }
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.lv_country) {
            selectCountryClassification(position);
        } else if (parent.getId() == R.id.lv_city) {
            selectCityClassification(position);
            Intent intent = new Intent(aty, SelectProductAirportTransportationActivity.class);
            intent.putExtra("airport_id", airportBean.getId());
            intent.putExtra("title", title);
            intent.putExtra("name", airportBean.getCountry_name() + airportBean.getName() + title);
            intent.putExtra("type", type);
            // 设置结果 结果码，一个数据
//            aty.setResult(RESULT_OK, intent);
//            aty.finish();
            aty.showActivity(aty, intent);
        }
    }

    public void setClassificationId(int classification_id1) {
        classification_id = classification_id1;
//        if (mRefreshLayout != null) {
//            mRefreshLayout.beginRefreshing();
//        }
    }


    @Override
    public void setPresenter(AirportClassificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            CityClassificationBean cityClassificationBean = (CityClassificationBean) JsonUtil.getInstance().json2Obj(success, CityClassificationBean.class);
            if (cityClassificationBean == null || cityClassificationBean.getData() == null || cityClassificationBean.getData().size() <= 0) {
                errorMsg(getString(R.string.noData), 0);
                return;
            }
            lv_country.setVisibility(View.VISIBLE);
            countryClassificationList = cityClassificationBean.getData();
            selectCountryClassification(0);
        } else if (flag == 1) {
            AirportByCountryIdBean airportByCountryIdBean = (AirportByCountryIdBean) JsonUtil.getInstance().json2Obj(success, AirportByCountryIdBean.class);

//            gv_countriesClassification.setVisibility(View.VISIBLE);
//            mGridViewAdapter.clear();
//            mGridViewAdapter.addNewData(airportByCountryIdBean.getData());
//            dismissLoadingDialog();

            if (airportByCountryIdBean == null || airportByCountryIdBean.getData() == null || airportByCountryIdBean.getData().size() <= 0) {
                lv_city.setVisibility(View.INVISIBLE);
                dismissLoadingDialog();
                return;
            }
            lv_city.setVisibility(View.VISIBLE);
            airportClassificationList = airportByCountryIdBean.getData();
            selectCityClassification(0);
        }
        dismissLoadingDialog();
    }


    /**
     * 选中分类
     *
     * @param position
     */
    private void selectCountryClassification(int position) {
        for (int i = 0; i < countryClassificationList.size(); i++) {
            if (position == i || position == i && position == 0) {
                countryBean = countryClassificationList.get(i);
                countryBean.setIsSelected(1);
                ((AirportClassificationContract.Presenter) mPresenter).getAirportByCountryId(aty, countryBean.getId());
            } else {
                countryClassificationList.get(i).setIsSelected(0);
            }
        }
        mCountryAdapter.clear();
        mCountryAdapter.addNewData(countryClassificationList);
    }


    /**
     * 选中分类
     *
     * @param position
     */
    private void selectCityClassification(int position) {
        for (int i = 0; i < airportClassificationList.size(); i++) {
            if (position == i || position == i && position == 0) {
                airportBean = airportClassificationList.get(i);
                airportBean.setIsSelected(1);
            } else {
                airportClassificationList.get(i).setIsSelected(0);
            }
        }
        mAirportAdapter.clear();
        mAirportAdapter.addNewData(airportClassificationList);
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            ll_commonError.setVisibility(View.VISIBLE);
            lv_country.setVisibility(View.GONE);
            lv_city.setVisibility(View.GONE);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCountryAdapter.clear();
        mCountryAdapter = null;
        mAirportAdapter.clear();
        mAirportAdapter = null;
    }

}
