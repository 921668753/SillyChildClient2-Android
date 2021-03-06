package com.sillykid.app.homepage.airporttransportation.airportselect;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.airporttransportation.airportselect.AirportSelectClassificationListViewAdapter;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.CitySelectListBean;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.CitySelectListBean.DataBean;
import com.sillykid.app.homepage.airporttransportation.airportselect.fragment.AirportClassificationFragment;
import com.sillykid.app.homepage.airporttransportation.airportselect.fragment.RecommendedFragment;
import com.sillykid.app.homepage.airporttransportation.airportselect.search.AirportSearchActivity;

import java.util.ArrayList;

/**
 * 机场选择
 */
public class AirportSelectActivity extends BaseActivity implements AirportSelectContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.ll_search, click = true)
    private LinearLayout ll_search;

    @BindView(id = R.id.tv_search)
    private TextView tv_search;

    @BindView(id = R.id.tv_cancel, click = true)
    private TextView tv_cancel;

    @BindView(id = R.id.lv_classification)
    private ListView lv_classification;

    private AirportSelectClassificationListViewAdapter mAdapter;
    private ArrayList<AirportClassificationFragment> list;

    private RecommendedFragment recommendedFragment;

    private String title = "";
    private int type = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_cityselect);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new AirportSelectPresenter(this);
        mAdapter = new AirportSelectClassificationListViewAdapter(this);
        list = new ArrayList<AirportClassificationFragment>();
        title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((AirportSelectContract.Presenter) mPresenter).getCountryAreaList(type, 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        if (type == 1) {
            tv_search.setHint(getString(R.string.airportWillLandAt));
        } else if (type == 2) {
            tv_search.setHint(getString(R.string.airportArriveAt));
        }
        lv_classification.setAdapter(mAdapter);
        lv_classification.setOnItemClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_search:
                Intent intent = new Intent(aty, AirportSearchActivity.class);
                intent.putExtra("type", type);
                intent.putExtra("title", title);
                showActivity(aty, intent);
                break;
            case R.id.tv_cancel:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectClassification(position);
    }

    @Override
    public void setPresenter(AirportSelectContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        CitySelectListBean citySelectListBean = (CitySelectListBean) JsonUtil.getInstance().json2Obj(success, CitySelectListBean.class);
        if (citySelectListBean == null || citySelectListBean.getData() == null || citySelectListBean.getData().size() <= 0) {
            return;
        }
        for (int i = 0; i < citySelectListBean.getData().size(); i++) {
            if (i == 0) {
                DataBean citySelectBean = citySelectListBean.getData().get(i);
                citySelectBean.setIsSelected(1);
                if (!StringUtils.isEmpty(citySelectListBean.getData().get(i).getName()) && citySelectListBean.getData().get(i).getName().startsWith(getString(R.string.recommended))) {
                    recommendedFragment = new RecommendedFragment();
                }
            } else {
                citySelectListBean.getData().get(i).setIsSelected(0);
            }
            AirportClassificationFragment baseFragment = new AirportClassificationFragment();
            list.add(baseFragment);
        }
        mAdapter.clear();
        mAdapter.addNewData(citySelectListBean.getData());
        selectClassification(0);
    }

    /**
     * 选中分类
     *
     * @param position
     */
    private void selectClassification(int position) {
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            if (position == i && !StringUtils.isEmpty(mAdapter.getData().get(i).getName()) && mAdapter.getData().get(i).getName().startsWith(getString(R.string.recommended))) {
                mAdapter.getData().get(i).setIsSelected(1);
                changeFragment(recommendedFragment);
                recommendedFragment.setClassificationId(mAdapter.getData().get(i).getId());
            } else if (position == i && !StringUtils.isEmpty(mAdapter.getData().get(i).getName())) {
                mAdapter.getData().get(i).setIsSelected(1);
                changeFragment(list.get(position));
                list.get(position).setClassificationId(mAdapter.getData().get(i).getId());
            } else {
                mAdapter.getData().get(i).setIsSelected(0);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusCitySearchListEvent")) {
            int country_id = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "CitySearchListcountry_id", 0);
            String country_name = PreferenceHelper.readString(aty, StringConstants.FILENAME, "CitySearchListcountry_name", "");
            int city_id = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "CitySearchListcity_id", 0);
            String city_name = PreferenceHelper.readString(aty, StringConstants.FILENAME, "CitySearchListcity_name", "");
            Intent intent = getIntent();
            intent.putExtra("country_id", country_id);
            intent.putExtra("country_name", country_name);
            intent.putExtra("city_id", city_id);
            intent.putExtra("city_name", city_name);
            setResult(RESULT_OK, intent);
            finish();
        }
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.select_content, targetFragment);
    }

}
