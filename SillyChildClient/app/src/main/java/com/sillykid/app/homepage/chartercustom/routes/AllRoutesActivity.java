package com.sillykid.app.homepage.chartercustom.routes;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.view.TimePickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.OptionsPickerView;
import com.kymjs.common.PreferenceHelper;
import com.sillykid.app.R;
import com.sillykid.app.adapter.AllLineViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.AllRoutesBean;
import com.sillykid.app.entity.CarInfoBean;
import com.sillykid.app.entity.VehicleTypeBean;
import com.sillykid.app.homepage.addressselection.newoverseas.NewOverseasCityActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 服务产品
 * Created by Admin on 2017/8/16.
 */
public class AllRoutesActivity extends BaseActivity implements AllRoutesContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_address, click = true)
    private TextView tv_address;

    @BindView(id = R.id.ll_modelCar, click = true)
    private LinearLayout ll_modelCar;

    @BindView(id = R.id.tv_modelCar)
    private TextView tv_modelCar;

    @BindView(id = R.id.ll_reservationNumber, click = true)
    private LinearLayout ll_reservationNumber;

    @BindView(id = R.id.tv_reservationNumber)
    private TextView tv_reservationNumber;

    @BindView(id = R.id.img_reservationNumber)
    private ImageView img_reservationNumber;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private AllLineViewAdapter mAdapter;

    @BindView(id = R.id.lv_allRoutes)
    private ListView lv_allRoutes;

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

    private String seat_num = "";
    private String car_level = "";
    private String line_buy_num = "desc";
    private String timesNum = "";
    private OptionsPickerView pvOptions;
    private TimePickerView pvTime;
    private OptionsPickerView modelsOptions;
    private ArrayList<CarInfoBean.ResultBean> options1Items;
    private List<VehicleTypeBean.ResultBean.LevelListBean> options2Items;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_allroutes);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new AllRoutesPresenter(this);
        mAdapter = new AllLineViewAdapter(this);
        selectModels();
        //   reservationNumber();
    }

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
//                Intent intent = new Intent();
//                intent.setClass(aty, AddressSelectionActivity.class);
//                startActivityForResult(intent, STATUS);
//            }
//        };
        locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
//        titlebar.setTitleText(getString(R.string.allRoutes));
//        titlebar.setRightText(locationCity);
//        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.down));
//        titlebar.setDelegate(simpleDelegate);
        tv_title.setText(getString(R.string.allRoutes));
        tv_address.setText(locationCity);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_allRoutes.setAdapter(mAdapter);
        lv_allRoutes.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((AllRoutesContract.Presenter) mPresenter).getCarWhere();
        // ((AllRoutesContract.Presenter) mPresenter).getAllRoutes(mMorePageNumber, tv_modelCar.getText().toString(), timesNum, locationCity);
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((AllRoutesContract.Presenter) mPresenter).getAllRoutes(mMorePageNumber, seat_num, car_level, line_buy_num, locationCity);
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
        ((AllRoutesContract.Presenter) mPresenter).getAllRoutes(mMorePageNumber, seat_num, car_level, line_buy_num, locationCity);
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
            case R.id.ll_modelCar:
                modelsOptions.show(tv_modelCar);
                break;
            case R.id.ll_reservationNumber:
                if (line_buy_num.equals("desc")) {
                    line_buy_num = "asc";
                    img_reservationNumber.setImageResource(R.mipmap.icon_up);
                } else {
                    line_buy_num = "desc";
                    img_reservationNumber.setImageResource(R.mipmap.icon_down);
                }
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, RouteDetailsActivity.class);
        intent.putExtra("line_id", mAdapter.getItem(i).getLine_id() + "");
        intent.putExtra("line_title", mAdapter.getItem(i).getLine_title());
        intent.putExtra("line_price", mAdapter.getItem(i).getLine_price());
        showActivity(aty, intent);
    }


    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            AllRoutesBean allRoutesBean = (AllRoutesBean) JsonUtil.json2Obj(success, AllRoutesBean.class);
            if (allRoutesBean == null) {
                errorMsg(getString(R.string.serverReturnsDataNull1), 0);
                return;
            }
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
            VehicleTypeBean vehicleTypeBean = (VehicleTypeBean) JsonUtil.json2Obj(success, VehicleTypeBean.class);
            if (vehicleTypeBean.getData().getSeat_list() != null && vehicleTypeBean.getData().getSeat_list().size() > 0) {
                List<Integer> list = vehicleTypeBean.getData().getSeat_list();
                for (int i = 0; i < list.size(); i++) {
                    int seatNum = list.get(i);
                    CarInfoBean.ResultBean resultBean = new CarInfoBean.ResultBean();
                    resultBean.setId(seatNum);
                    resultBean.setSite_num(seatNum + getString(R.string.peopleseat));
                    options1Items.add(resultBean);
                }
            } else {
                options1Items = null;
            }
            if (vehicleTypeBean.getData().getLevel_list() != null && vehicleTypeBean.getData().getLevel_list().size() > 0) {
                options2Items = vehicleTypeBean.getData().getLevel_list();
            } else {
                options2Items = null;
            }
            modelsOptions.setNPicker(options1Items, options2Items, null);
            mRefreshLayout.beginRefreshing();
        }

    }

    @Override
    public void errorMsg(String msg, int flag) {
        //  toLigon(msg);
        isShowLoadingMore = false;
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
//        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//            mRefreshLayout.endRefreshing();
//        } else {
//            mRefreshLayout.endLoadingMore();
//        }
        if (msg.equals(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.nonetworkxxx);
        } else {
            img_err.setImageResource(R.mipmap.nocontentxxx);
        }
        dismissLoadingDialog();
    }

    @Override
    public void setPresenter(AllRoutesContract.Presenter presenter) {
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
            mRefreshLayout.beginRefreshing();
            Log.d("tag888", selectCity);
        }
    }


    /**
     * 选择车型
     */

    public void selectModels() {
        options1Items = new ArrayList<>();
        modelsOptions = new OptionsPickerView.Builder(aty, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ((TextView) v).setText(options1Items.get(options1).getSite_num() + options2Items.get(option2).getName());
                seat_num = String.valueOf(options1Items.get(options1).getId());
                car_level = String.valueOf(options2Items.get(option2).getId());
                mRefreshLayout.beginRefreshing();
            }

            @Override
            public void onOptionsCancel(View v) {
                if (((TextView) v).getText().toString().equals(getString(R.string.modelCar))) {
                    return;
                }
                ((TextView) v).setText(getString(R.string.modelCar));
                seat_num = "";
                car_level = "";
                mRefreshLayout.beginRefreshing();
            }
        }).setCancelText(getString(R.string.emptying)).build();
    }


    /**
     * 选择日期
     */

    public void startDatePicker() {
        boolean[] type = new boolean[]{true, true, true, false, false, false};
        Calendar calendar = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(calendar.get(Calendar.YEAR) - 10, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(calendar.get(Calendar.YEAR) + 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        pvTime = null;
//        pvTime = new TimePickerView.Builder(aty, new TimePickerView.OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {//选中事件回调
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                ((TextView) v).setText(format.format(date));
//                mRefreshLayout.beginRefreshing();
//            }
//        }).setType(type).setRangDate(startDate, endDate).setLabel("", "", "", "", "", "").build();
    }

}
