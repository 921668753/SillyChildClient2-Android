package com.sillykid.app.homepage.privatecustom;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.privatecustom.CategoryListBean;
import com.sillykid.app.entity.homepage.privatecustom.CategoryListBean.DataBean.RepastListBean;
import com.sillykid.app.entity.homepage.privatecustom.CategoryListBean.DataBean.StayListBean;
import com.sillykid.app.entity.homepage.privatecustom.CategoryListBean.DataBean.TravelListBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 私人定制  填写需求
 */
public class PrivateCustomActivity extends BaseActivity implements PrivateCustomContract.View {

    @BindView(id = R.id.img_picture)
    private ImageView img_picture;

    @BindView(id = R.id.tv_content)
    private TextView tv_content;

    @BindView(id = R.id.ll_travelTime, click = true)
    private LinearLayout ll_travelTime;

    @BindView(id = R.id.tv_travelTime)
    private TextView tv_travelTime;

    @BindView(id = R.id.et_destination)
    private EditText et_destination;

    @BindView(id = R.id.et_playNumberDays)
    private EditText et_playNumberDays;

    @BindView(id = R.id.ll_travelPreferences, click = true)
    private LinearLayout ll_travelPreferences;

    @BindView(id = R.id.tv_travelPreferences)
    private TextView tv_travelPreferences;

    @BindView(id = R.id.ll_recommendRestaurant, click = true)
    private LinearLayout ll_recommendRestaurant;

    @BindView(id = R.id.tv_recommendRestaurant)
    private TextView tv_recommendRestaurant;


    @BindView(id = R.id.ll_recommendedAccommodation, click = true)
    private LinearLayout ll_recommendedAccommodation;

    @BindView(id = R.id.tv_recommendedAccommodation)
    private TextView tv_recommendedAccommodation;


    @BindView(id = R.id.et_remark)
    private EditText et_remark;


    @BindView(id = R.id.tv_customizedItinerary, click = true)
    private TextView tv_customizedItinerary;

    private OptionsPickerView pvOptions;

    private OptionsPickerView pvOptions1;

    private OptionsPickerView pvOptions2;

    private long travel_time = 0;

    private TimePickerView pvTime = null;
    private List<TravelListBean> travel_list;
    private List<RepastListBean> repast_list;
    private List<StayListBean> stay_list;

    private int travel_preference = 0;

    private int repast_preference = 0;

    private int stay_preference = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_privatecustom);
    }


    @Override
    public void initData() {
        super.initData();
        travel_list = new ArrayList<TravelListBean>();
        repast_list = new ArrayList<RepastListBean>();
        stay_list = new ArrayList<StayListBean>();
        mPresenter = new PrivateCustomPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        selectTravelTime();
        selectTravelPreferences();
        selectRecommendRestaurant();
        selectRecommendedAccommodation();
        showLoadingDialog(getString(R.string.dataLoad));
        ((PrivateCustomContract.Presenter) mPresenter).getCategoryList();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.privateOrdering), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_travelTime:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvTime.show(tv_travelTime);
                break;
            case R.id.ll_travelPreferences:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_travelPreferences);
                break;
            case R.id.ll_recommendRestaurant:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions1.show(tv_recommendRestaurant);
                break;
            case R.id.ll_recommendedAccommodation:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions2.show(tv_recommendedAccommodation);
                break;
            case R.id.tv_customizedItinerary:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((PrivateCustomContract.Presenter) mPresenter).postAddCustomized(travel_time, et_destination.getText().toString().trim(),
                        et_playNumberDays.getText().toString(), travel_preference, repast_preference, stay_preference, et_remark.getText().toString().trim());
                break;
        }
    }

    /**
     * 选择出行时间
     */
    @SuppressWarnings("unchecked")
    private void selectTravelTime() {
        //控制时间范围
        boolean[] type = new boolean[]{true, true, true, false, false, false};
        Calendar calendar = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(calendar.get(Calendar.YEAR) + 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        pvTime = null;
        pvTime = new TimePickerBuilder(aty, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                if (date.getTime() < System.currentTimeMillis()) {
                    ViewInject.toast(aty.getString(R.string.greateThanCurrentTime));
                    return;
                }
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                travel_time = date.getTime() / 1000;
                ((TextView) v).setText(format.format(date));
            }
        }).setType(type).setRangDate(startDate, endDate).setLabel("", "", "", "", "", "").build();
        pvTime.setDate(calendar);
    }


    /**
     * 选择出行偏好
     */
    @SuppressWarnings("unchecked")
    private void selectTravelPreferences() {
        pvOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                travel_preference = travel_list.get(options1).getId();
                ((TextView) v).setText(travel_list.get(options1).getPreference_name());
            }
        }).build();
    }


    /**
     * 选择餐馆偏好
     */
    @SuppressWarnings("unchecked")
    private void selectRecommendRestaurant() {
        pvOptions1 = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                repast_preference = repast_list.get(options1).getId();
                ((TextView) v).setText(repast_list.get(options1).getPreference_name());
            }
        }).build();
    }


    /**
     * 选择住宿偏好
     */
    @SuppressWarnings("unchecked")
    private void selectRecommendedAccommodation() {
        pvOptions2 = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {


            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                stay_preference = stay_list.get(options1).getId();
                ((TextView) v).setText(stay_list.get(options1).getPreference_name());
            }
        }).build();
    }


    @Override
    public void setPresenter(PrivateCustomContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            CategoryListBean categoryListBean = (CategoryListBean) JsonUtil.getInstance().json2Obj(success, CategoryListBean.class);
            GlideImageLoader.glideOrdinaryLoader(this, categoryListBean.getData().getPicture(), img_picture, R.mipmap.placeholderfigure);
            tv_content.setText(categoryListBean.getData().getContent());
            travel_list = categoryListBean.getData().getTravel_list();
            pvOptions.setPicker(travel_list);
            repast_list = categoryListBean.getData().getRepast_list();
            pvOptions1.setPicker(repast_list);
            stay_list = categoryListBean.getData().getStay_list();
            pvOptions2.setPicker(stay_list);
            dismissLoadingDialog();
        } else if (flag == 1) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.customizedItineraryS));
            finish();
        }

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
