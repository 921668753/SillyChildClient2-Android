package com.sillykid.app.homepage.chartercustom.chartercommon;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.OptionsPickerView;
import com.sillykid.app.R;
import com.sillykid.app.adapter.CharterListViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.CarInfoBean;
import com.sillykid.app.entity.CharterListBean;
import com.sillykid.app.entity.VehicleTypeBean;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 包车列表
 * Created by Admin on 2017/9/29.
 */

public class CharterListActivity extends BaseActivity implements CharterListContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    /**
     * 车型
     */
    @BindView(id = R.id.ll_modelCar, click = true)
    private LinearLayout ll_modelCar;

    @BindView(id = R.id.tv_modelCar)
    private TextView tv_modelCar;

    /**
     * 预定次数
     */
    @BindView(id = R.id.ll_reservationNumber, click = true)
    private LinearLayout ll_reservationNumber;

    @BindView(id = R.id.img_reservationNumber)
    private ImageView img_reservationNumber;


    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private CharterListViewAdapter mAdapter;

    @BindView(id = R.id.lv_charterlist)
    private ListView lv_charterlist;

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

    private int type = 0;
    private String city;

    private OptionsPickerView modelsOptions;
    private ArrayList<CarInfoBean.ResultBean> options1Items;
    private List<VehicleTypeBean.ResultBean.LevelListBean> options2Items;

    private String car_seat_num = "";
    private String car_level = "";

    private String order_times = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_charterlist);
    }

    @Override
    public void initData() {
        super.initData();
        type = getIntent().getIntExtra("type", 1);
        city = getIntent().getStringExtra("city");
        mPresenter = new CharterListPresenter(this);
        mAdapter = new CharterListViewAdapter(this);
        selectModels();
    }


    /**
     * 初始化控件
     */
    @SuppressWarnings("deprecation")
    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getIntent().getStringExtra("title"), true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_charterlist.setAdapter(mAdapter);
        lv_charterlist.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterListContract.Presenter) mPresenter).getCarWhere();
    }


    /**
     * @param refreshLayout 刷新
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterListContract.Presenter) mPresenter).getPackCarProduct(city, mMorePageNumber, type, car_level, car_seat_num, order_times);
    }

    /**
     * @param refreshLayout 加载
     */
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
        ((CharterListContract.Presenter) mPresenter).getPackCarProduct(city, mMorePageNumber, type, car_level, car_seat_num, order_times);
        return true;
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_modelCar:
                modelsOptions.show(tv_modelCar);
                break;
            case R.id.ll_reservationNumber:
                if (order_times.equals("asc")) {
                    order_times = "desc";
                    img_reservationNumber.setImageResource(R.mipmap.icon_down);
                } else {
                    order_times = "asc";
                    img_reservationNumber.setImageResource(R.mipmap.icon_up);
                }
                mRefreshLayout.beginRefreshing();
                break;

            case R.id.tv_hintText:
                mRefreshLayout.beginRefreshing();
                break;
        }
    }

    /**
     * @param adapterView item点击事件
     * @param view        view
     * @param i           i
     * @param l           i
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, CharterDetailsActivity.class);
        intent.putExtra("id", mAdapter.getItem(i).getId());
        intent.putExtra("type", mAdapter.getItem(i).getType());
        showActivity(aty, intent);
    }

    @Override
    public void setPresenter(CharterListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            CharterListBean charterListBean = (CharterListBean) JsonUtil.getInstance().json2Obj(success, CharterListBean.class);
            totalPageNumber = charterListBean.getData().getTotalPages();
            if (charterListBean.getData().getList() == null || charterListBean.getData().getList().size() == 0) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            for (int i = 0; i < charterListBean.getData().getList().size(); i++) {
                charterListBean.getData().getList().get(i).setType(type);
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(charterListBean.getData().getList());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(charterListBean.getData().getList());
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            dismissLoadingDialog();
        } else if (flag == 2) {
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
                car_seat_num = String.valueOf(options1Items.get(options1).getId());
                car_level = String.valueOf(options2Items.get(option2).getId());
                mRefreshLayout.beginRefreshing();
            }

            @Override
            public void onOptionsCancel(View v) {
                if (((TextView) v).getText().toString().equals(getString(R.string.modelCar))) {
                    return;
                }
                ((TextView) v).setText(getString(R.string.modelCar));
                car_seat_num = "";
                car_level = "";
                mRefreshLayout.beginRefreshing();
            }
        }).setCancelText(getString(R.string.emptying)).build();
    }


    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 2) {
            dismissLoadingDialog();
            return;
        }
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
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
