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
import com.sillykid.app.homepage.boutiqueline.dialog.CalendarControlBouncedDialog;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.DataUtil;
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

    private List<TravelListBean> travel_list;
    private List<RepastListBean> repast_list;
    private List<StayListBean> stay_list;

    private String travel_preference;

    private String repast_preference;

    private String stay_preference;

    private CalendarControlBouncedDialog calendarControlBouncedDialog;

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
        initCalendarControlBouncedDialog();
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
                if (calendarControlBouncedDialog == null) {
                    initCalendarControlBouncedDialog();
                }
                if (calendarControlBouncedDialog != null && !calendarControlBouncedDialog.isShowing()) {
                    calendarControlBouncedDialog.show();
                }
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
    private void initCalendarControlBouncedDialog() {
        calendarControlBouncedDialog = new CalendarControlBouncedDialog(this) {
            @Override
            public void timeList(String dateStr) {
                tv_travelTime.setText(dateStr);
                travel_time = DataUtil.dateToStamp(dateStr + " 0:0:0");
            }
        };
        calendarControlBouncedDialog.setCanceledOnTouchOutside(false);
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
                travel_preference = travel_list.get(options1).getPreference_name();
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
                repast_preference = repast_list.get(options1).getPreference_name();
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
                stay_preference = stay_list.get(options1).getPreference_name();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (calendarControlBouncedDialog != null && calendarControlBouncedDialog.isShowing()) {
            calendarControlBouncedDialog.cancel();
        }
        calendarControlBouncedDialog = null;
        travel_list.clear();
        travel_list = null;
        repast_list.clear();
        repast_list = null;
        stay_list.clear();
        stay_list = null;
        pvOptions = null;
        pvOptions1 = null;
        pvOptions2 = null;
    }
}
