package com.sillykid.app.homepage.addressselection.newoverseas;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.adapter.addressselection.newoverseas.NewOverseasCountryClassificationViewAdapter;
import com.sillykid.app.adapter.addressselection.newoverseas.OverseasAllCityViewAdapter;
import com.sillykid.app.entity.AllChildCityBean;
import com.sillykid.app.entity.AllCountryBean;
import com.sillykid.app.homepage.addressselection.addresssearch.AddressSearchActivity;

import java.util.List;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 国外城市选择列表
 * Created by Admin on 2017/9/25.
 */

public class NewOverseasCityActivity extends BaseActivity implements NewOverseasCityContract.View, AdapterView.OnItemClickListener {

    /**
     * 返回
     */
    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    /**
     * 地址搜索
     */
    @BindView(id = R.id.ll_addressSearch, click = true)
    private LinearLayout ll_addressSearch;

    /**
     * GPS定位地址
     */
    @BindView(id = R.id.tv_city, click = true)
    private TextView tv_city;

    /**
     * 所有国家
     */
    @BindView(id = R.id.lv_allCountry)
    private ListView lv_allCountry;

    private NewOverseasCountryClassificationViewAdapter allCountryAdapter;

    /**
     * 选择全部
     */
    @BindView(id = R.id.tv_selectAll, click = true)
    private TextView tv_selectAll;

    /**
     * 所有城市
     */
    @BindView(id = R.id.gv_allCity)
    private NoScrollGridView gv_allCity;

    private OverseasAllCityViewAdapter allCityAdapter;


    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;

    private String locationCity;
    private List<AllCountryBean.ResultBean> countryList;
    private AllCountryBean.ResultBean countryBean;
    private int flag1 = 0;
    private int isJump = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_newoverseascity);
    }


    @Override
    public void initData() {
        super.initData();
        isJump = getIntent().getIntExtra("isJump", 0);
        mPresenter = new NewOverseasCityPresenter(this);
        allCountryAdapter = new NewOverseasCountryClassificationViewAdapter(this);
        allCityAdapter = new OverseasAllCityViewAdapter(this);
        showLoadingDialog(getString(R.string.dataLoad));
    }

    @Override
    public void initDataFromThread() {
        super.initDataFromThread();
        ((NewOverseasCityContract.Presenter) mPresenter).getAllCountry();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "locationCity", getString(R.string.locateFailure));
        tv_city.setText(getString(R.string.currentLocation) + locationCity);
        lv_allCountry.setAdapter(allCountryAdapter);
        lv_allCountry.setOnItemClickListener(this);
        gv_allCity.setAdapter(allCityAdapter);
        gv_allCity.setOnItemClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.ll_addressSearch:
                Intent intent = new Intent();
                intent.setClass(aty, AddressSearchActivity.class);
                startActivityForResult(intent, STATUS);
                overridePendingTransition(0, 0);
                break;

            case R.id.tv_city:
//                Intent intent1 = new Intent();
//                // 获取内容
//                intent1.putExtra("selectCity", locationCity);
//                intent1.putExtra("selectCityId", 1);
//                intent1.putExtra("selectCountry", "");
//                intent1.putExtra("selectCountryId", 1);
//                // 设置结果 结果码，一个数据
//                aty.setResult(RESULT_OK, intent1);
//                // 结束该activity 结束之后，前面的activity才可以处理结果
//                aty.finish();
                break;

            case R.id.tv_selectAll:
                Intent intent2 = new Intent();
                // 获取内容
                intent2.putExtra("selectCity", getString(R.string.allAeservationNumber));
                intent2.putExtra("selectCityId", 1);
                intent2.putExtra("selectCountry", "");
                intent2.putExtra("selectCountryId", 1);
                // 设置结果 结果码，一个数据
                aty.setResult(RESULT_OK, intent2);
                // 结束该activity 结束之后，前面的activity才可以处理结果
                aty.finish();
                break;
            case R.id.tv_hintText:
                showLoadingDialog(getString(R.string.dataLoad));
                if (flag1 == 0) {
                    ((NewOverseasCityContract.Presenter) mPresenter).getAllCountry();
                } else {
                    ((NewOverseasCityContract.Presenter) mPresenter).getAllChildCity(countryBean.getId());
                }
                break;
        }
    }


    @Override
    public void setPresenter(NewOverseasCityContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        ll_commonError.setVisibility(View.GONE);
        flag1 = flag;
        if (flag == 0) {
            AllCountryBean allCountryBean = (AllCountryBean) JsonUtil.getInstance().json2Obj(success, AllCountryBean.class);
            countryList = allCountryBean.getData();
            if (!(countryList != null && countryList.size() > 0)) {
                errorMsg(aty.getString(R.string.emporarilyNoData), 0);
                return;
            }
            AllCountryBean.ResultBean resultBean = new AllCountryBean.ResultBean();
            resultBean.setId(0);
            resultBean.setName(getString(R.string.allAeservationNumber));
            countryList.add(0, resultBean);
            setAddressClassification(0);
        } else if (flag == 1) {
            AllChildCityBean allChildCityBean = (AllChildCityBean) JsonUtil.getInstance().json2Obj(success, AllChildCityBean.class);
            List<AllChildCityBean.ResultBean> cityList = allChildCityBean.getData();
            if (!(cityList != null && cityList.size() > 0)) {
                errorMsg(aty.getString(R.string.emporarilyNoData), 1);
                return;
            }
            gv_allCity.setVisibility(View.VISIBLE);
            allCityAdapter.clear();
            allCityAdapter.addNewData(cityList);
            dismissLoadingDialog();
        }
    }


    @Override
    public void errorMsg(String msg, int flag) {
        flag1 = flag;
        if (flag == 0) {
            lv_allCountry.setVisibility(View.GONE);
            gv_allCity.setVisibility(View.GONE);
        } else {
            gv_allCity.setVisibility(View.GONE);
        }
        ll_commonError.setVisibility(View.VISIBLE);
        ll_commonError.setBackgroundResource(R.color.whiteColors);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        if (msg.equals(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.nonetworkxxx);
        } else {
            img_err.setImageResource(R.mipmap.nocontentxxx);
        }
        dismissLoadingDialog();
        //   ViewInject.toast(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        allCountryAdapter.clear();
        allCountryAdapter = null;
        allCityAdapter.clear();
        allCityAdapter = null;
    }

    private void setAddressClassification(int position) {
        if (countryList.get(position).getName().contains(getString(R.string.allAeservationNumber))) {
            tv_selectAll.setVisibility(View.VISIBLE);
        } else {
            tv_selectAll.setVisibility(View.GONE);
        }
        for (int i = 0; i < countryList.size(); i++) {
            if (position == i) {
                countryBean = countryList.get(i);
                countryBean.setStatus(1);
            } else {
                countryList.get(i).setStatus(0);
            }
        }
        allCountryAdapter.clear();
        allCountryAdapter.addNewData(countryList);
        ((NewOverseasCityContract.Presenter) mPresenter).getAllChildCity(countryBean.getId());
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.lv_allCountry) {
            setAddressClassification(i);
        } else if (adapterView.getId() == R.id.gv_allCity) {
            AllChildCityBean.ResultBean bean = allCityAdapter.getItem(i);
            Intent intent = new Intent();
            // 获取内容
            intent.putExtra("selectCity", bean.getName());
            intent.putExtra("selectCityId", bean.getId());
            intent.putExtra("selectCountryId", bean.getCountry_id());
            Log.d("tag", bean.getCountry_id() + "");
            if (countryBean.getName().equals(getString(R.string.allAeservationNumber))) {
                for (int j = 0; j < countryList.size(); j++) {
                    if (countryList.get(j).getId() == bean.getCountry_id()) {
                        intent.putExtra("selectCountry", countryList.get(j).getName());
                        Log.d("tag", countryList.get(j).getName() + "");
                    }
                }
            } else {
                intent.putExtra("selectCountry", countryBean.getName());
                Log.d("tag", countryBean.getName() + "");
            }
            intent.putExtra("isJump", isJump);
            // 设置结果 结果码，一个数据
            aty.setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            int selectCityId = data.getIntExtra("selectCityId", 0);
            String selectCountry = data.getStringExtra("selectCountry");
            int selectCountryId = data.getIntExtra("selectCountryId", 0);
            Log.d("tag888", selectCity);
            Intent intent = new Intent();
            // 获取内容
            intent.putExtra("selectCity", selectCity);
            intent.putExtra("selectCityId", selectCityId);
            intent.putExtra("selectCountry", selectCountry);
            intent.putExtra("selectCountryId", selectCountryId);
            intent.putExtra("isJump", isJump);
            // 设置结果 结果码，一个数据
            aty.setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
        }
    }
}
