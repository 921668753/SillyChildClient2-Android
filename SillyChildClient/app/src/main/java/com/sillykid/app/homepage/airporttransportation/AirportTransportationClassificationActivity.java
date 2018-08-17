package com.sillykid.app.homepage.airporttransportation;


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
import com.sillykid.app.entity.mall.moreclassification.ClassificationBean;
import com.sillykid.app.entity.mall.moreclassification.MoreClassificationBean;
import com.sillykid.app.mall.moreclassification.MoreClassificationContract;

import java.util.List;

/**
 * 接送机分类
 */
public class AirportTransportationClassificationActivity extends BaseActivity implements AirportTransportationClassificationContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.lv_countries)
    private ListView lv_countries;

    @BindView(id = R.id.gv_countriesClassification)
    private GridView gv_countriesClassification;


    private AirportTransportationClassificationListViewAdapter mListViewAdapter = null;

    private AirportTransportationClassificationGridViewAdapter mGridViewAdapter = null;

    private List<MoreClassificationBean.DataBean> moreClassificationList;

    private MoreClassificationBean.DataBean moreClassificationBean = null;

    private String title = "";


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
        ((AirportTransportationClassificationContract.Presenter) mPresenter).getClassification(0, 0);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.lv_countries) {
            selectClassification(position);
        } else if (adapterView.getId() == R.id.gv_countriesClassification) {
            Intent intent = new Intent(aty, SelectProductAirportTransportationActivity.class);
            intent.putExtra("cat", mGridViewAdapter.getItem(position).getCat_id());
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
            MoreClassificationBean moreClassificationBean = (MoreClassificationBean) JsonUtil.getInstance().json2Obj(success, MoreClassificationBean.class);
            moreClassificationList = moreClassificationBean.getData();
            if (moreClassificationList != null && moreClassificationList.size() > 0) {
                selectClassification(0);
            }
        } else if (flag == 1) {
            ClassificationBean classificationBean = (ClassificationBean) JsonUtil.getInstance().json2Obj(success, ClassificationBean.class);
            mGridViewAdapter.clear();
            mGridViewAdapter.addNewData(classificationBean.getData());
            dismissLoadingDialog();
        }
    }

    /**
     * 选中分类
     *
     * @param position
     */
    private void selectClassification(int position) {
        for (int i = 0; i < moreClassificationList.size(); i++) {
            if (position == i || position == i && position == 0) {
                moreClassificationBean = moreClassificationList.get(i);
                moreClassificationBean.setIsSelected(1);
                ((MoreClassificationContract.Presenter) mPresenter).getClassification(moreClassificationBean.getCat_id(), 1);
            } else {
                moreClassificationList.get(i).setIsSelected(0);
            }
        }
        mListViewAdapter.clear();
        mListViewAdapter.addNewData(moreClassificationList);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

}
