package com.sillykid.app.homepage;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;
import com.sillykid.app.homepage.airporttransportation.airportselect.search.AirportSearchListActivity;
import com.sillykid.app.homepage.bythedaycharter.cityselect.search.CharterCitySearchListActivity;
import com.sillykid.app.homepage.privatecustom.cityselect.search.CitySearchListActivity;

/**
 * 城市搜索分类选择
 */
public class SearchCityClassificationActivity extends BaseActivity {

    /**
     * 搜索
     */
    @BindView(id = R.id.tv_search)
    private TextView tv_search;

    @BindView(id = R.id.tv_cancel, click = true)
    private TextView tv_cancel;

    @BindView(id = R.id.tv_airportPickup, click = true)
    private TextView tv_airportPickup;

    @BindView(id = R.id.tv_airportDropOff, click = true)
    private TextView tv_airportDropOff;

    @BindView(id = R.id.tv_route, click = true)
    private TextView tv_route;

    @BindView(id = R.id.tv_charter, click = true)
    private TextView tv_charter;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_searchcityclassification);
    }

    @Override
    public void initData() {
        super.initData();

    }


    @Override
    public void initWidget() {
        super.initWidget();
        tv_search.setText(getIntent().getStringExtra("name"));
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_airportPickup:
                Intent intent = new Intent(aty, AirportSearchListActivity.class);
                intent.putExtra("title", getString(R.string.airportPickup));
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("type", 1);
                intent.putExtra("tag", -1);
                showActivity(aty, intent);
                break;
            case R.id.tv_airportDropOff:
                Intent intent1 = new Intent(aty, AirportSearchListActivity.class);
                intent1.putExtra("title", getString(R.string.airportDropOff));
                intent1.putExtra("name", getIntent().getStringExtra("name"));
                intent1.putExtra("type", 2);
                intent1.putExtra("tag", -1);
                showActivity(aty, intent1);
                break;
            case R.id.tv_route:
                Intent intent2 = new Intent(aty, CitySearchListActivity.class);
//                intent2.putExtra("title", getString(R.string.airportDropOff));
                intent2.putExtra("name", getIntent().getStringExtra("name"));
                intent2.putExtra("type", 5);
                intent2.putExtra("tag", -1);
                showActivity(aty, intent2);
                break;
            case R.id.tv_charter:
                Intent intent3 = new Intent(aty, CharterCitySearchListActivity.class);
                intent3.putExtra("title", getString(R.string.byTheDay));
                intent3.putExtra("name", getIntent().getStringExtra("name"));
                intent3.putExtra("type", 3);
                intent3.putExtra("tag", -1);
                showActivity(aty, intent3);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
