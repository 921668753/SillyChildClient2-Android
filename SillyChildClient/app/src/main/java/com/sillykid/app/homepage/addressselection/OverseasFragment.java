package com.sillykid.app.homepage.addressselection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.adapter.addressselection.overseas.OverseasAddressClassificationViewAdapter;
import com.sillykid.app.adapter.addressselection.overseas.OverseasHotClassificationViewAdapter;
import com.sillykid.app.entity.IndexCityBean;
import com.sillykid.app.entity.IndexCityBean.ResultBean;
import com.sillykid.app.homepage.addressselection.overseas.OverseasCityActivity;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 海外
 * Created by Admin on 2017/9/5.
 */

public class OverseasFragment extends BaseFragment implements AdapterView.OnItemClickListener, OverseasContract.View {

    private AddressSelectionActivity aty;


//    @BindView(id = R.id.ll_localize, click = true)
//    private LinearLayout ll_localize;

//    @BindView(id = R.id.tvCurrent)
//    private TextView tvCurrent;

    @BindView(id = R.id.lv_addressClassification)
    private ListView lv_addressClassification;

    @BindView(id = R.id.sv_addressClassification)
    private ScrollView sv_addressClassification;


//    @BindView(id = R.id.ll_GPSPositioning)
//    private LinearLayout ll_GPSPositioning;
//    @BindView(id = R.id.ll_city)
//    private LinearLayout ll_city;
//    @BindView(id = R.id.tv_city, click = true)
//    private TextView tv_city;

//    @BindView(id = R.id.ll_historyDestinations)
//    private LinearLayout ll_historyDestinations;
//    @BindView(id = R.id.gv_historyDestinations)
//    private NoScrollGridView gv_historyDestinations;


//    @BindView(id = R.id.ll_hotDestination)
//    private LinearLayout ll_hotDestination;
//    @BindView(id = R.id.tv_hotDestination)
//    private TextView tv_hotDestination;
//    @BindView(id = R.id.gv_hotDestination)
//    private NoScrollGridView gv_hotDestination;


    @BindView(id = R.id.ll_allCountries)
    private LinearLayout ll_allCountries;
    @BindView(id = R.id.tv_allCountriesNum)
    private TextView tv_allCountriesNum;
    @BindView(id = R.id.gv_allCountries)
    private NoScrollGridView gv_allCountries;

    private OverseasAddressClassificationViewAdapter overseasAddressClassificationViewAdapter;

    private List<ResultBean> indexCityList;
//    private List<ResultBean> historyCityList;
    private ResultBean indexCityBean1;
//    private OverseasHotClassificationViewAdapter historyDestinationsViewAdapter;
//    private OverseasHotClassificationViewAdapter hotDestinationViewAdapter;
    private OverseasHotClassificationViewAdapter allCountriesViewAdapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (AddressSelectionActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_overseas, null);
    }

    @Override
    protected void initData() {
        super.initData();
        showLoadingDialog(getString(R.string.dataLoad));
//        historyCityList = new ArrayList<>();
        mPresenter = new OverseasPresenter(this);
        overseasAddressClassificationViewAdapter = new OverseasAddressClassificationViewAdapter(aty);
//        historyDestinationsViewAdapter = new OverseasHotClassificationViewAdapter(aty);
//        hotDestinationViewAdapter = new OverseasHotClassificationViewAdapter(aty);
        allCountriesViewAdapter = new OverseasHotClassificationViewAdapter(aty);
    }

    @Override
    protected void initDataFromThread() {
        super.initDataFromThread();
        ((OverseasContract.Presenter) mPresenter).getIndexCity();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        //  tv_city.setOnClickListener(this);
        lv_addressClassification.setAdapter(overseasAddressClassificationViewAdapter);
        lv_addressClassification.setOnItemClickListener(this);
//        gv_historyDestinations.setAdapter(historyDestinationsViewAdapter);
//        gv_historyDestinations.setOnItemClickListener(this);
//        gv_hotDestination.setAdapter(hotDestinationViewAdapter);
//        gv_hotDestination.setOnItemClickListener(this);
        gv_allCountries.setAdapter(allCountriesViewAdapter);
        gv_allCountries.setOnItemClickListener(this);
//        String locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "locationCity", getString(R.string.locateFailure));
//        tvCurrent.setText(locationCity);
//        readHistory();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
//            case R.id.tv_city:
//             //   PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", tv_city.getText().toString());
//                Intent intent = new Intent();
//                // 获取内容
//                intent.putExtra("selectCity", tv_city.getText().toString());
//                intent.putExtra("selectCityId", 1);
//                intent.putExtra("selectCountry", "");
//                intent.putExtra("selectCountryId", 1);
//                // 设置结果 结果码，一个数据
//                aty.setResult(RESULT_OK, intent);
//                // 结束该activity 结束之后，前面的activity才可以处理结果
//                aty.finish();
//                break;

//            case R.id.ll_localize:
//             //   PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", tvCurrent.getText().toString());
//                Intent intent1 = new Intent();
//                // 获取内容
//                intent1.putExtra("selectCity", tvCurrent.getText().toString());
//                intent1.putExtra("selectCityId", 1);
//                intent1.putExtra("selectCountry", "");
//                intent1.putExtra("selectCountryId", 1);
//                // 设置结果 结果码，一个数据
//                aty.setResult(RESULT_OK, intent1);
//                // 结束该activity 结束之后，前面的activity才可以处理结果
//                aty.finish();
//                break;

        }
    }

    private void setAddressClassification(int position) {
//        if (position == 0) {
//            ll_GPSPositioning.setVisibility(View.GONE);
//            ll_city.setVisibility(View.GONE);
//            if (historyDestinationsViewAdapter.getData() != null && historyDestinationsViewAdapter.getData().size() > 0) {
//                ll_historyDestinations.setVisibility(View.VISIBLE);
//                gv_historyDestinations.setVisibility(View.VISIBLE);
//            } else {
//                ll_historyDestinations.setVisibility(View.GONE);
//                gv_historyDestinations.setVisibility(View.GONE);
//            }
//        } else {
//            ll_GPSPositioning.setVisibility(View.GONE);
//            ll_city.setVisibility(View.GONE);
//            ll_historyDestinations.setVisibility(View.GONE);
//            gv_historyDestinations.setVisibility(View.GONE);
//        }
//        ll_hotDestination.setVisibility(View.GONE);
//        gv_hotDestination.setVisibility(View.GONE);
        ll_allCountries.setVisibility(View.VISIBLE);
        gv_allCountries.setVisibility(View.VISIBLE);
        for (int i = 0; i < indexCityList.size(); i++) {
            if (position == i) {
                indexCityBean1 = indexCityList.get(i);
                indexCityBean1.setStatus(1);
            } else {
                indexCityList.get(i).setStatus(0);
            }
        }
        overseasAddressClassificationViewAdapter.clear();
        overseasAddressClassificationViewAdapter.addNewData(indexCityList);
        ((OverseasContract.Presenter) mPresenter).getChildCity(indexCityBean1.getId());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.lv_addressClassification) {
            setAddressClassification(i);
        }
//        else if (adapterView.getId() == R.id.gv_historyDestinations) {
//       //     PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", historyDestinationsViewAdapter.getItem(i).getName());
//            ResultBean bean = historyDestinationsViewAdapter.getItem(i);
//            Intent intent = new Intent();
//            // 获取内容
//            intent.putExtra("selectCity", bean.getName());
//            intent.putExtra("selectCityId", bean.getId());
//            Log.d("tag", bean.getCountry() + "");
//            intent.putExtra("selectCountry", bean.getCountry());
//            Log.d("tag", bean.getCountry_id() + "");
//            intent.putExtra("selectCountryId", bean.getCountry_id());
//            //  intent.putExtra("selectCountryId", historyDestinationsViewAdapter.getItem(i)());
//            // 设置结果 结果码，一个数据
//            aty.setResult(RESULT_OK, intent);
//            // 结束该activity 结束之后，前面的activity才可以处理结果
//            aty.finish();
//        } else if (adapterView.getId() == R.id.gv_hotDestination) {
////            saveHistory(hotDestinationViewAdapter.getItem(i));
//        //    PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", hotDestinationViewAdapter.getItem(i).getName());
//            Intent intent = new Intent();
//            // 获取内容
//            intent.putExtra("selectCity", hotDestinationViewAdapter.getItem(i).getName());
//            intent.putExtra("selectCityId", hotDestinationViewAdapter.getItem(i).getId());
////            intent.putExtra("selectCountry",  hotDestinationViewAdapter.getItem(i).getCountry());
////            intent.putExtra("selectCountryId", hotDestinationViewAdapter.getItem(i).getCountry_id());
//            // 设置结果 结果码，一个数据
//            aty.setResult(RESULT_OK, intent);
//            // 结束该activity 结束之后，前面的activity才可以处理结果
//            aty.finish();
//        }
        else if (adapterView.getId() == R.id.gv_allCountries) {
            //   saveHistory(allCountriesViewAdapter.getItem(i));
            //   PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", allCountriesViewAdapter.getItem(i).getName());
//            Intent intent = new Intent();
//            // 获取内容
//            intent.putExtra("selectCity", allCountriesViewAdapter.getItem(i).getName());
//            intent.putExtra("selectCityId", allCountriesViewAdapter.getItem(i).getId());
//            // 设置结果 结果码，一个数据
//            aty.setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            //     aty.finish();
            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCountry", allCountriesViewAdapter.getItem(i).getName());
            Intent intent = new Intent();
            intent.setClass(aty, OverseasCityActivity.class);
            intent.putExtra("selectCountry", allCountriesViewAdapter.getItem(i).getName());
            intent.putExtra("selectCountryId", allCountriesViewAdapter.getItem(i).getId());
            startActivityForResult(intent, STATUS);
        }
    }

//    /**
//     * 保存历史
//     */
//    private void saveHistory(ResultBean resultBean) {
//        if (historyCityList.size() > 0) {
//            for (int i = 0; i < historyCityList.size(); i++) {
//                if (historyCityList.get(i).getName().equals(resultBean.getName())) {
//                    historyCityList.remove(i);
//                }
//            }
//        }
//        historyCityList.add(resultBean);
//        if (historyCityList.size() > 4) {
//            historyCityList.remove(0);
//        }
//        BaseResult<List<ResultBean>> baseResult = new BaseResult<>();
//        baseResult.setMsg("");
//        baseResult.setStatus(1);
//        Collections.reverse(historyCityList);
//        baseResult.setResult(historyCityList);
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "overseasHistory", JsonUtil.getInstance().obj2JsonString(baseResult));
//    }

//    /**
//     * 读取历史
//     */
//    private void readHistory() {
//        String overseasHistory = PreferenceHelper.readString(aty, StringConstants.FILENAME, "overseasHistory", null);
//        if (StringUtils.isEmpty(overseasHistory)) {
//            ll_historyDestinations.setVisibility(View.GONE);
//            gv_historyDestinations.setVisibility(View.GONE);
//            return;
//        }
//        Log.d("tag", overseasHistory);
//        ll_historyDestinations.setVisibility(View.VISIBLE);
//        gv_historyDestinations.setVisibility(View.VISIBLE);
//        IndexCityBean indexCityBean = (IndexCityBean) JsonUtil.json2Obj(overseasHistory, IndexCityBean.class);
//        historyCityList = indexCityBean.getResult();
//        historyDestinationsViewAdapter.clear();
//        historyDestinationsViewAdapter.addNewData(historyCityList);
//    }

    @Override
    public void setPresenter(OverseasContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            IndexCityBean indexCityBean = (IndexCityBean) JsonUtil.json2Obj(success, IndexCityBean.class);
            indexCityList = indexCityBean.getData();
            if (!(indexCityList != null && indexCityList.size() > 0)) {
                errorMsg(aty.getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            sv_addressClassification.setVisibility(View.VISIBLE);
            setAddressClassification(0);
        } else if (flag == 1) {
            IndexCityBean indexCityBean = (IndexCityBean) JsonUtil.json2Obj(success, IndexCityBean.class);
            List<ResultBean> childCityList = indexCityBean.getData();
            if (!(childCityList != null && childCityList.size() > 0)) {
//                ll_hotDestination.setVisibility(View.GONE);
//                gv_hotDestination.setVisibility(View.GONE);
                errorMsg(aty.getString(R.string.serverReturnsDataNull), 1);
                return;
            }
//            tv_hotDestination.setText(String.valueOf(childCityList.size()));
//            ll_hotDestination.setVisibility(View.GONE);
//            gv_hotDestination.setVisibility(View.GONE);
//            hotDestinationViewAdapter.clear();
//            hotDestinationViewAdapter.addNewData(childCityList);
            dismissLoadingDialog();
        } else if (flag == 2) {
            IndexCityBean indexCityBean = (IndexCityBean) JsonUtil.json2Obj(success, IndexCityBean.class);
            List<ResultBean> childCityList = indexCityBean.getData();
            ((OverseasContract.Presenter) mPresenter).getChildHotCity(indexCityBean1.getId());
            if (!(childCityList != null && childCityList.size() > 0)) {
                ll_allCountries.setVisibility(View.GONE);
                gv_allCountries.setVisibility(View.GONE);
                errorMsg(aty.getString(R.string.serverReturnsDataNull), 3);
                return;
            }
            tv_allCountriesNum.setText(String.valueOf(childCityList.size()));
            ll_allCountries.setVisibility(View.VISIBLE);
            gv_allCountries.setVisibility(View.VISIBLE);
            allCountriesViewAdapter.clear();
            allCountriesViewAdapter.addNewData(childCityList);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 0) {
            sv_addressClassification.setVisibility(View.GONE);
        } else if (flag == 1) {
//            ll_hotDestination.setVisibility(View.GONE);
//            gv_hotDestination.setVisibility(View.GONE);
        } else if (flag == 2) {
            ll_allCountries.setVisibility(View.GONE);
            gv_allCountries.setVisibility(View.GONE);
        }
        dismissLoadingDialog();
        //  ViewInject.toast(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        historyCityList.clear();
//        historyCityList = null;
        overseasAddressClassificationViewAdapter.clear();
        overseasAddressClassificationViewAdapter = null;
//        historyDestinationsViewAdapter.clear();
//        historyDestinationsViewAdapter = null;
//        hotDestinationViewAdapter.clear();
//        hotDestinationViewAdapter = null;
        allCountriesViewAdapter.clear();
        allCountriesViewAdapter = null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            // 设置结果 结果码，一个数据
            aty.setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
        }
    }


}