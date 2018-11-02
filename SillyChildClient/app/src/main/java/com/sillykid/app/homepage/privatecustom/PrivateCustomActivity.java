package com.sillykid.app.homepage.privatecustom;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.homepage.airporttransportation.airportpickup.PeopleBean;
import com.sillykid.app.entity.homepage.privatecustom.CategoryListBean;
import com.sillykid.app.entity.homepage.privatecustom.CategoryListBean.DataBean.RepastListBean;
import com.sillykid.app.entity.homepage.privatecustom.CategoryListBean.DataBean.StayListBean;
import com.sillykid.app.entity.homepage.privatecustom.CategoryListBean.DataBean.TravelListBean;
import com.sillykid.app.homepage.boutiqueline.dialog.CalendarControlBouncedDialog;
import com.sillykid.app.homepage.message.interactivemessage.imuitl.RongIMUtil;
import com.sillykid.app.homepage.privatecustom.cityselect.CitySelectActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;

import static com.sillykid.app.constant.NumericConstants.RESULT_CODE_GET;


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

    @BindView(id = R.id.ll_destination, click = true)
    private LinearLayout ll_destination;

    @BindView(id = R.id.tv_destination)
    private TextView tv_destination;

    @BindView(id = R.id.ll_playNumberDays, click = true)
    private LinearLayout ll_playNumberDays;

    @BindView(id = R.id.tv_playNumberDays)
    private TextView tv_playNumberDays;

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

    private int day_number = 0;

    private String travel_preference;

    private String repast_preference;

    private String stay_preference;

    private CalendarControlBouncedDialog calendarControlBouncedDialog;
    private OptionsPickerView dayOptions;
    private String service_id = "";
    private String service_name = "";

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
        selectPlayNumberDays();
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
            case R.id.ll_destination:
                SoftKeyboardUtils.packUpKeyboard(this);
                Intent intent = new Intent(this, CitySelectActivity.class);
                intent.putExtra("type", 4);
                startActivityForResult(intent, RESULT_CODE_GET);
                break;
            case R.id.ll_playNumberDays:
                SoftKeyboardUtils.packUpKeyboard(this);
                dayOptions.show(tv_playNumberDays);
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
                ((PrivateCustomContract.Presenter) mPresenter).postAddCustomized(travel_time, tv_destination.getText().toString().trim(),
                        String.valueOf(day_number), travel_preference, repast_preference, stay_preference, et_remark.getText().toString().trim());
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
     * 选择出行游玩天数
     */
    @SuppressWarnings("unchecked")
    private void selectPlayNumberDays() {
        List<PeopleBean> list = new ArrayList<PeopleBean>();
        for (int i = 0; i < 30; i++) {
            PeopleBean peopleBean = new PeopleBean();
            peopleBean.setNum(i + 1);
            peopleBean.setName(i + 1 + getString(R.string.day1));
            list.add(peopleBean);
        }
        dayOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                day_number = list.get(options1).getNum();
                ((TextView) v).setText(list.get(options1).getPickerViewText());
            }
        }).build();
        dayOptions.setPicker(list);
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
            service_id = categoryListBean.getData().getService_id();
            service_name = categoryListBean.getData().getService_name();
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
            if (StringUtils.isEmpty(service_id)) {
                finish();
                return;
            }
            showLoadingDialog(getString(R.string.customerServiceLoad));
            RongIMUtil.connectRongIM(aty);
            //首先需要构造使用客服者的用户信息
            dismissLoadingDialog();
            CSCustomServiceInfo csInfo = RongIMUtil.getCSCustomServiceInfo(aty);
            /**
             * 启动客户服聊天界面。
             * @param context           应用上下文。
             * @param customerServiceId 要与之聊天的客服 Id。
             * @param title             聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
             * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
             */
            RongIM.getInstance().startCustomerServiceChat(aty, service_id, service_name, csInfo);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE_GET && resultCode == RESULT_OK) {// 如果等于1
            int country_id = data.getIntExtra("country_id", 0);
            String country_name = data.getStringExtra("country_name");
            int city_id = data.getIntExtra("city_id", 0);
            String city_name = data.getStringExtra("city_name");
            tv_destination.setText(country_name + " " + city_name);
        }
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
