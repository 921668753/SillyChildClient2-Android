package com.sillykid.app.homepage.chartercustom.addresspoisearch;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.adapter.AddressPoiSearchViewAdapter;
import com.sillykid.app.entity.IndexCityBean;

import java.util.List;

/**
 * 地址poi搜索
 * Created by Admin on 2017/9/5.
 */

public class AddressPOISearchActivity extends BaseActivity implements TextWatcher, AddressPOISearchContract.View, AdapterView.OnItemClickListener, OnGetPoiSearchResultListener {

    /**
     * 返回
     */
    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    /**
     * 输入框
     */
    @BindView(id = R.id.et_cityName)
    private EditText et_cityName;

    /**
     * 取消
     */
    @BindView(id = R.id.img_quxiao, click = true)
    private ImageView img_quxiao;


    @BindView(id = R.id.lv_addressPoiSearch)
    private ChildListView lv_addressPoiSearch;

    private AddressPoiSearchViewAdapter addressPoiSearchViewAdapter;

    private PoiSearch mPoiSearch = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_addresspoisearch);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new AddressPOISearchPresenter(this);
        addressPoiSearchViewAdapter = new AddressPoiSearchViewAdapter(this);
        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        lv_addressPoiSearch.setAdapter(addressPoiSearchViewAdapter);
        lv_addressPoiSearch.setOnItemClickListener(this);
        et_cityName.addTextChangedListener(this);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_quxiao:
                et_cityName.setText("");
                img_quxiao.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

//    @Override
//    protected void onPause() {
//        overridePendingTransition(0, 0);
//        super.onPause();
//    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        ((AddressPOISearchContract.Presenter) mPresenter).getSearchCity(s.toString().trim());
//        img_quxiao.setVisibility(View.VISIBLE);
//        lv_addressPoiSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (et_cityName.getText().toString().length() < 0) {
            return;
        }
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption().keyword(et_cityName.getText().toString().trim()).sortType(PoiSortType.distance_from_near_to_far).radius(1000000);
        mPoiSearch.searchNearby(nearbySearchOption);
    }

    @Override
    public void setPresenter(AddressPOISearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            IndexCityBean indexCityBean = (IndexCityBean) JsonUtil.json2Obj(success, IndexCityBean.class);
            List<IndexCityBean.ResultBean> indexCityList = indexCityBean.getData();
            if (!(indexCityList != null && indexCityList.size() > 0)) {
                errorMsg(aty.getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            addressPoiSearchViewAdapter.clear();
            addressPoiSearchViewAdapter.addNewData(indexCityList);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        //  ViewInject.toast(msg);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        IndexCityBean.ResultBean resultBean = addressPoiSearchViewAdapter.getItem(i);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", resultBean.getName());
        Intent intent = new Intent();
        // 获取内容
        intent.putExtra("selectCity", resultBean.getName());
        intent.putExtra("selectCityId", resultBean.getId());
        // 设置结果 结果码，一个数据
        setResult(RESULT_OK, intent);
        // 结束该activity 结束之后，前面的activity才可以处理结果
        aty.finish();
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        if (poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, poiDetailResult.getName() + ": " + poiDetailResult.getAddress(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPoiSearch.destroy();
        addressPoiSearchViewAdapter.clear();
        addressPoiSearchViewAdapter = null;
    }

}