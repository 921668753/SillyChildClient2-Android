package com.sillykid.app.homepage.chartercustom.companyguide;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.adapter.AllCompanyGuideViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.AllCompanyGuideBean;
import com.sillykid.app.entity.CarInfoBean;
import com.sillykid.app.homepage.addressselection.newoverseas.NewOverseasCityActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 全部司导
 * Created by Admin on 2017/8/16.
 */

public class AllCompanyGuideActivity extends BaseActivity implements AllCompanyGuideContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_address, click = true)
    private TextView tv_address;

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

    /**
     * 刷新控件
     */
    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private AllCompanyGuideViewAdapter mAdapter;

    @BindView(id = R.id.lv_allcompanyguide)
    private ListView lv_allcompanyguide;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;

    /**
     * 总页码
     */
    private int totalPageNumber = NumericConstants.START_PAGE_NUMBER;

    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;

    private String locationCity;

    private TimePickerView pvTime;
    private OptionsPickerView pvOptions;

    private String travelNumber = "";
    private CarInfoBean carInfoBean;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_allcompanyguide);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AllCompanyGuidePresenter(this);
        mAdapter = new AllCompanyGuideViewAdapter(this);
        locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
        startDatePicker();
        travelNumber();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void initWidget() {
        super.initWidget();
        tv_title.setText(getString(R.string.allCompanyGuide));
        tv_address.setText(locationCity);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_allcompanyguide.setAdapter(mAdapter);
        lv_allcompanyguide.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        mRefreshLayout.beginRefreshing();
        //  ((AllCompanyGuideContract.Presenter) mPresenter).getCarInfo();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            return false;
        }
        mMorePageNumber++;
        if (mMorePageNumber > totalPageNumber) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        showLoadingDialog(getString(R.string.dataLoad));
        ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
        return true;
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_address:
                Intent intent1 = new Intent();
                intent1.setClass(aty, NewOverseasCityActivity.class);
                startActivityForResult(intent1, STATUS);
                break;
            case R.id.tv_hintText:
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.ll_destination:
                Intent intent = new Intent();
                intent.setClass(aty, NewOverseasCityActivity.class);
                startActivityForResult(intent, 2);
                break;
            case R.id.ll_date:
                pvTime.show(tv_date);
                break;
            case R.id.ll_travelNumber:
                pvOptions.show(tv_travelNumber);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, CompanyGuideDetailsActivity.class);
        intent.putExtra("drv_id", String.valueOf(mAdapter.getItem(i).getSeller_id()));
        showActivity(aty, intent);
    }


    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            AllCompanyGuideBean allRoutesBean = (AllCompanyGuideBean) JsonUtil.getInstance().json2Obj(success, AllCompanyGuideBean.class);
            totalPageNumber = allRoutesBean.getData().getTotalPages();
            if (allRoutesBean.getData().getList() == null || allRoutesBean.getData().getList().size() == 0) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(allRoutesBean.getData().getList());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(allRoutesBean.getData().getList());
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
            carInfoBean = (CarInfoBean) JsonUtil.json2Obj(success, CarInfoBean.class);
            if (carInfoBean.getData() != null && carInfoBean.getData().size() > 0) {
                pvOptions.setPicker(carInfoBean.getData());
            }
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        //  toLigon(msg);
        isShowLoadingMore = false;
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        if (msg.equals(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.nonetworkxxx);
        } else {
            img_err.setImageResource(R.mipmap.nocontentxxx);
        }
        dismissLoadingDialog();
    }

    @Override
    public void setPresenter(AllCompanyGuideContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
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
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshingHomePageFragment", true);
            showLoadingDialog(getString(R.string.dataLoad));
            ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
            Log.d("tag888", selectCity);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            int selectCityId = data.getIntExtra("selectCityId", 0);
            tv_destination.setText(selectCity);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", tv_address.getText().toString());
            locationCity = selectCity;
            showLoadingDialog(getString(R.string.dataLoad));
            ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
            Log.d("tag888", selectCity);
        }
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
//                showLoadingDialog(getString(R.string.dataLoad));
//                ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
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
//                travelNumber = carInfoBean.getData().get(options1).getSite_num();
//                showLoadingDialog(getString(R.string.dataLoad));
//                ((AllCompanyGuideContract.Presenter) mPresenter).getAllCompanyGuide(mMorePageNumber, tv_date.getText().toString(), locationCity, travelNumber);
//            }
//        }).build();
    }
}
