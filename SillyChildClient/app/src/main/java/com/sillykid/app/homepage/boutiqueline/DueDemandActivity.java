package com.sillykid.app.homepage.boutiqueline;

import android.content.Intent;
import android.view.View;
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
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.klavor.widget.RatingBar;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.boutiqueline.DueDemandViewAdapter;
import com.sillykid.app.entity.homepage.airporttransportation.airportpickup.AirportPickupBean;
import com.sillykid.app.entity.homepage.airporttransportation.airportpickup.PeopleBean;
import com.sillykid.app.entity.homepage.boutiqueline.DueDemandBean;
import com.sillykid.app.homepage.airporttransportation.dialog.CompensationChangeBackDialog;
import com.sillykid.app.homepage.boutiqueline.dialog.CalendarControlBouncedDialog;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 预定需求
 */
public class DueDemandActivity extends BaseActivity implements DueDemandContract.View, BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String> {

    @BindView(id = R.id.img_picture)
    private ImageView img_picture;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.ratingbar)
    private RatingBar ratingbar;

    @BindView(id = R.id.tv_ratingbar)
    private TextView tv_ratingbar;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    @BindView(id = R.id.tv_modelCar)
    private TextView tv_modelCar;

    @BindView(id = R.id.tv_canTakeNumber)
    private TextView tv_canTakeNumber;

    @BindView(id = R.id.tv_carPrices)
    private TextView tv_carPrices;

    @BindView(id = R.id.tv_containsService)
    private TextView tv_containsService;

    @BindView(id = R.id.gv_containsService)
    private NoScrollGridView gv_containsService;

    @BindView(id = R.id.ll_compensationChangeBack, click = true)
    private LinearLayout ll_compensationChangeBack;
    @BindView(id = R.id.tv_compensationChangeBack)
    private TextView tv_compensationChangeBack;

    @BindView(id = R.id.tv_date, click = true)
    private TextView tv_date;
    private long timeDeparture = 0;

    @BindView(id = R.id.et_contact)
    private TextView et_contact;

    @BindView(id = R.id.et_contactWay)
    private TextView et_contactWay;

    @BindView(id = R.id.ll_adult, click = true)
    private LinearLayout ll_adult;
    @BindView(id = R.id.tv_adult)
    private TextView tv_adult;
    private OptionsPickerView adultOptions = null;

    @BindView(id = R.id.ll_children, click = true)
    private LinearLayout ll_children;
    @BindView(id = R.id.tv_children)
    private TextView tv_children;
    private OptionsPickerView childrenOptions = null;

    @BindView(id = R.id.ll_luggage, click = true)
    private LinearLayout ll_luggage;
    @BindView(id = R.id.tv_luggage)
    private TextView tv_luggage;
    private OptionsPickerView luggageOptions = null;

    @BindView(id = R.id.et_remark)
    private TextView et_remark;

    @BindView(id = R.id.tv_submitOrder, click = true)
    private TextView tv_submitOrder;

    private int product_id = 0;
    private int baggage_number = 0;
    private int passenger_number = 0;
    private int baggage_number1 = 0;
    private int adult_number = 0;
    private int children_number = 0;


    private DueDemandViewAdapter mAdapter;
    private CompensationChangeBackDialog compensationChangeBackDialog;
    private CalendarControlBouncedDialog calendarControlBouncedDialog;
    private DueDemandBean dueDemandBean;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_duedemand);
    }

    @Override
    public void initData() {
        super.initData();
        product_id = getIntent().getIntExtra("product_id", 0);
        mPresenter = new DueDemandPresenter(this);
        mAdapter = new DueDemandViewAdapter(this);
        initCalendarControlBouncedDialog();
        initDialog();
        showLoadingDialog(getString(R.string.dataLoad));
        ((DueDemandContract.Presenter) mPresenter).getProductDetails(product_id);
    }

    /**
     * 选择时间的控件
     */
    private void initCalendarControlBouncedDialog() {
        calendarControlBouncedDialog = new CalendarControlBouncedDialog(this) {
            @Override
            public void timeList(String dateStr) {
                tv_date.setText(dateStr);
                timeDeparture = DataUtil.dateToStamp(dateStr + " 0:0:0");
            }
        };
        calendarControlBouncedDialog.setCanceledOnTouchOutside(false);
    }


    private void initDialog() {
        compensationChangeBackDialog = new CompensationChangeBackDialog(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.route2), true, R.id.titlebar);
        GlideImageLoader.glideOrdinaryLoader(this, getIntent().getStringExtra("smallImg"), img_picture, R.mipmap.placeholderfigure);
        ratingbar.setRating((float) StringUtils.toDouble(getIntent().getStringExtra("recommended").substring(0, getIntent().getStringExtra("recommended").length() - 1)));
        ratingbar.refreshUI();
        tv_title.setText(getIntent().getStringExtra("product_name"));
        tv_ratingbar.setText(getIntent().getStringExtra("recommended"));
        initBanner();
        gv_containsService.setAdapter(mAdapter);
    }


    /**
     * 初始化轮播图
     */
    public void initBanner() {
        mForegroundBanner.setFocusable(true);
        mForegroundBanner.setFocusableInTouchMode(true);
        mForegroundBanner.requestFocus();
        mForegroundBanner.requestFocusFromTouch();
        mForegroundBanner.setAutoPlayAble(true);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setAllowUserScrollable(true);
        mForegroundBanner.setAutoPlayInterval(3000);
        // 初始化方式1：配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播
        mForegroundBanner.setAdapter(this);
        mForegroundBanner.setDelegate(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mForegroundBanner.startAutoPlay();
    }


    @Override
    public void onPause() {
        super.onPause();
        mForegroundBanner.stopAutoPlay();
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_compensationChangeBack:
                if (compensationChangeBackDialog == null) {
                    initDialog();
                }
                if (compensationChangeBackDialog != null && !compensationChangeBackDialog.isShowing()) {
                    compensationChangeBackDialog.show();
                    compensationChangeBackDialog.setText(dueDemandBean.getData().getService_policy_content());
                }
                break;
            case R.id.tv_date:
                SoftKeyboardUtils.packUpKeyboard(this);
                if (calendarControlBouncedDialog == null) {
                    initCalendarControlBouncedDialog();
                }
                if (calendarControlBouncedDialog != null && !calendarControlBouncedDialog.isShowing()) {
                    calendarControlBouncedDialog.show();
                }
                break;
            case R.id.ll_adult:
                SoftKeyboardUtils.packUpKeyboard(this);
                adultOptions.show(tv_adult);
                break;
            case R.id.ll_children:
                SoftKeyboardUtils.packUpKeyboard(this);
                childrenOptions.show(tv_children);
                break;
            case R.id.ll_luggage:
                SoftKeyboardUtils.packUpKeyboard(this);
                luggageOptions.show(tv_luggage);
                break;
            case R.id.tv_submitOrder:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((DueDemandContract.Presenter) mPresenter).postAddRequirements(product_id, String.valueOf(timeDeparture),
                        et_contact.getText().toString().trim(), et_contactWay.getText().toString().trim(), String.valueOf(adult_number), String.valueOf(children_number),
                        String.valueOf(baggage_number1), et_remark.getText().toString().trim(), passenger_number, baggage_number);
                break;
        }
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        //   GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView);
        itemView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        GlideImageLoader.glideOrdinaryLoader(aty, model, itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
//        if (StringUtils.isEmpty(model.getUrl())) {
//            return;
//        }
//        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
//        bannerDetails.putExtra("url", model.getUrl());
//        bannerDetails.putExtra("title", model.getAname());
//        showActivity(aty, bannerDetails);
    }


    @Override
    public void setPresenter(DueDemandContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            dueDemandBean = (DueDemandBean) JsonUtil.getInstance().json2Obj(success, DueDemandBean.class);
            if (dueDemandBean.getData().getPicture() != null && dueDemandBean.getData().getPicture().size() > 0) {
                mForegroundBanner.setVisibility(View.VISIBLE);
                processLogic(dueDemandBean.getData().getPicture());
            } else {
                mForegroundBanner.setVisibility(View.GONE);
            }
            tv_modelCar.setText(dueDemandBean.getData().getModel());
            tv_canTakeNumber.setText(dueDemandBean.getData().getPassenger());
            tv_carPrices.setText(getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(dueDemandBean.getData().getPrice())));
            if (dueDemandBean.getData() != null && dueDemandBean.getData().getService() != null && dueDemandBean.getData().getService().size() > 0) {
                gv_containsService.setVisibility(View.VISIBLE);
                tv_containsService.setVisibility(View.VISIBLE);
                mAdapter.clear();
                mAdapter.addNewData(dueDemandBean.getData().getService());
            } else {
                tv_containsService.setVisibility(View.GONE);
                gv_containsService.setVisibility(View.GONE);
            }
            tv_compensationChangeBack.setText(dueDemandBean.getData().getService_policy());
            passenger_number = dueDemandBean.getData().getPassenger_number();
            baggage_number = dueDemandBean.getData().getBaggage_number();
            initOptions(passenger_number, baggage_number);
        } else if (flag == 1) {
            AirportPickupBean airportPickupBean = (AirportPickupBean) JsonUtil.getInstance().json2Obj(success, AirportPickupBean.class);
            Intent intent = new Intent(aty, LineDetailsPayOrderActivity.class);
            intent.putExtra("requirement_id", airportPickupBean.getData().getRequirement_id());
            intent.putExtra("baggage_passenger", getString(R.string.pickUpNumber) + "≤" + passenger_number + "  " + getString(R.string.baggageNumber1) + "≤" + baggage_number);
            intent.putExtra("title", getIntent().getStringExtra("title"));
            intent.putExtra("picture", getIntent().getStringExtra("picture"));
            showActivity(aty, intent);
        }
        dismissLoadingDialog();
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<String> list) {
        if (list != null && list.size() > 0) {
            if (list.size() == 1) {
                mForegroundBanner.setAutoPlayAble(false);
                mForegroundBanner.setAllowUserScrollable(false);
            } else {
                mForegroundBanner.setAutoPlayAble(true);
                mForegroundBanner.setAllowUserScrollable(true);
            }
            mForegroundBanner.setBackground(null);
            mForegroundBanner.setData(list, null);
        }
    }

    private void initOptions(int passenger_number, int baggage_number) {
        List<PeopleBean> list = new ArrayList<PeopleBean>();
        for (int i = 0; i < passenger_number; i++) {
            PeopleBean peopleBean = new PeopleBean();
            peopleBean.setNum(i + 1);
            peopleBean.setName(i + 1 + getString(R.string.people));
            list.add(peopleBean);
        }
        adultOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                adult_number = list.get(options1).getNum();
                ((TextView) v).setText(list.get(options1).getPickerViewText());
            }
        }).build();
        adultOptions.setPicker(list);
        List<PeopleBean> childrenList = new ArrayList<PeopleBean>();
        for (int i = 0; i < passenger_number; i++) {
            PeopleBean peopleBean = new PeopleBean();
            peopleBean.setNum(i );
            peopleBean.setName(i  + getString(R.string.people));
            childrenList.add(peopleBean);
        }
        childrenOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                children_number = childrenList.get(options1).getNum();
                ((TextView) v).setText(childrenList.get(options1).getPickerViewText());
            }
        }).build();
        childrenOptions.setPicker(childrenList);
        List<PeopleBean> list1 = new ArrayList<PeopleBean>();
        for (int i = 0; i < baggage_number; i++) {
            PeopleBean peopleBean = new PeopleBean();
            peopleBean.setNum(i + 1);
            peopleBean.setName(i + 1 + getString(R.string.jian));
            list1.add(peopleBean);
        }
        luggageOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                baggage_number1 = list1.get(options1).getNum();
                ((TextView) v).setText(list1.get(options1).getPickerViewText());
            }
        }).build();
        luggageOptions.setPicker(list1);
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
        if (compensationChangeBackDialog != null && compensationChangeBackDialog.isShowing()) {
            compensationChangeBackDialog.cancel();
        }
        compensationChangeBackDialog = null;
        if (calendarControlBouncedDialog != null && calendarControlBouncedDialog.isShowing()) {
            calendarControlBouncedDialog.cancel();
        }
        calendarControlBouncedDialog = null;
        adultOptions = null;
        childrenOptions = null;
        luggageOptions = null;
    }


}
