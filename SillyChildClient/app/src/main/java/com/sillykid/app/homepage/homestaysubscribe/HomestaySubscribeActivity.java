package com.sillykid.app.homepage.homestaysubscribe;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.adapter.HomestaySubscribeViewAdapter;
import com.sillykid.app.entity.CarInfoBean;
import com.sillykid.app.homepage.addressselection.AddressSelectionActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 民宿预约
 * Created by Admin on 2017/8/16.
 */

public class HomestaySubscribeActivity extends BaseActivity implements HomestaySubscribeContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_address, click = true)
    private TextView tv_address;

    @BindView(id = R.id.lv_homestaySubscribe)
    private ChildListView lv_homestaySubscribe;


    @BindView(id = R.id.ll_destination, click = true)
    private LinearLayout ll_destination;

    @BindView(id = R.id.tv_destination)
    private TextView tv_destination;

    @BindView(id = R.id.ll_date, click = true)
    private LinearLayout ll_date;

    @BindView(id = R.id.tv_date)
    private TextView tv_date;

    @BindView(id = R.id.ll_travelNumber, click = true)
    private LinearLayout ll_travelNumber;

    @BindView(id = R.id.tv_travelNumber)
    private TextView tv_travelNumber;

    @BindView(id = R.id.tv_memberShowAll, click = true)
    private TextView tv_memberShowAll;

    private TimePickerView pvTime;
    private OptionsPickerView pvOptions;

    private HomestaySubscribeViewAdapter mAdapter;

    private String locationCity;

    private CarInfoBean carInfoBean;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_homestaysubscribe);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new HomestaySubscribePresenter(this);
        mAdapter = new HomestaySubscribeViewAdapter(this);
        locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.locateFailure));
        startDatePicker();
        travelNumber();
//        showLoadingDialog(getString(R.string.dataLoad));
//        ((HomestaySubscribeContract.Presenter) mPresenter).getCarInfo();
    }

    /**
     * 初始化控件
     */
    @SuppressWarnings("deprecation")
    @Override
    public void initWidget() {
        super.initWidget();
//        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
//            @Override
//            public void onClickLeftCtv() {
//                super.onClickLeftCtv();
//                aty.finish();
//            }
//
//            @Override
//            public void onClickRightCtv() {
//                super.onClickRightCtv();
//                //  showActivity(aty, RecommendedRecordActivity.class);
//            }
//        };
//        titlebar.setTitleText(getString(R.string.homestaySubscribe));
//        titlebar.setRightText(locationCity);
//        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.arrow));
//        titlebar.setDelegate(simpleDelegate);
        tv_title.setText(getString(R.string.homestaySubscribe));
        tv_address.setText(locationCity);
        // RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_homestaySubscribe.setAdapter(mAdapter);
        lv_homestaySubscribe.setOnItemClickListener(this);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_address:
                Intent intent = new Intent();
                intent.setClass(aty, AddressSelectionActivity.class);
                startActivityForResult(intent, STATUS);
                break;
            case R.id.ll_destination:
                Intent intent1 = new Intent();
                intent1.setClass(aty, AddressSelectionActivity.class);
                startActivityForResult(intent1, 2);
                break;
            case R.id.ll_date:
                pvTime.show(tv_date);
                break;
            case R.id.ll_travelNumber:
                pvOptions.show(tv_travelNumber);
                break;
            case R.id.tv_memberShowAll:
                showActivity(aty, AllHomestayActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, HomestayDetailsActivity.class);
//        intent.putExtra("order_id", mAdapter.getItem(i).getid());
//        if (mAdapter.getItem(i).getStatus() != null && mAdapter.getItem(i).getStatus().equals("quote")) {
//            intent.putExtra("goods_id", mAdapter.getItem(i).getGoods_id());
//        }
        showActivity(aty, intent);
    }

    @Override
    public void setPresenter(HomestaySubscribeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {


        } else if (flag == 1) {
            //     ((HomestaySubscribeContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
            carInfoBean = (CarInfoBean) JsonUtil.json2Obj(success, CarInfoBean.class);
            if (carInfoBean.getData() != null && carInfoBean.getData().size() > 0) {
                pvOptions.setPicker(carInfoBean.getData());
            }
        }


    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mAdapter.clear();
//        mAdapter = null;
    }


    /**
     * 选择日期
     */
    public void startDatePicker() {
        boolean[] type = new boolean[]{true, true, true, false, false, false};
        Calendar calendar = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(calendar.get(Calendar.YEAR) + 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        pvTime = null;
//        pvTime = new TimePickerView.Builder(aty, new TimePickerView.OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                if (date.getTime() <= System.currentTimeMillis()) {
//                    ViewInject.toast(aty.getString(R.string.greateThanCurrentTime));
//                    return;
//                }
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                ((TextView) v).setText(format.format(date));
////                showLoadingDialog(getString(R.string.dataLoad));
////                ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
//            }
//        }).setType(type).setRangDate(startDate, endDate).setLabel("", "", "", "", "", "").build();
    }


    /**
     * 选择旅伴人数
     */
    @SuppressWarnings("unchecked")
    private void travelNumber() {
//        pvOptions = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                ((TextView) v).setText(carInfoBean.getData().get(options1).getSite_num() + "人");
////                travelNumber = carInfoBean.getData().get(options1).getSite_num();
////                showLoadingDialog(getString(R.string.dataLoad));
////                ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
//            }
//        }).build();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            int selectCityId = data.getIntExtra("selectCityId", 0);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", selectCity);
            tv_address.setText(selectCity);
            locationCity = selectCity;
//            showLoadingDialog(getString(R.string.dataLoad));
//            ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
            Log.d("tag888", selectCity);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            int selectCityId = data.getIntExtra("selectCityId", 0);
            tv_destination.setText(selectCity);
            locationCity = selectCity;
            //    showLoadingDialog(getString(R.string.dataLoad));
//            ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
            Log.d("tag888", selectCity);
        }
    }

}
