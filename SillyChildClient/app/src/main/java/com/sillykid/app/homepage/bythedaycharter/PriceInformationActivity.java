package com.sillykid.app.homepage.bythedaycharter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.bythedaycharter.PriceInformationViewAdapter;
import com.sillykid.app.entity.homepage.bythedaycharter.PriceInformationBean;
import com.sillykid.app.homepage.airporttransportation.comments.CharterCommentsActivity;
import com.sillykid.app.homepage.airporttransportation.dialog.CompensationChangeBackDialog;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 价格信息
 */
public class PriceInformationActivity extends BaseActivity implements PriceInformationContract.View, BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String> {

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

    @BindView(id = R.id.tv_serviceHours)
    private TextView tv_serviceHours;

    @BindView(id = R.id.tv_serviceMileage)
    private TextView tv_serviceMileage;

    @BindView(id = R.id.tv_serviceDescription)
    private TextView tv_serviceDescription;

    @BindView(id = R.id.tv_remark)
    private TextView tv_remark;

    @BindView(id = R.id.ll_compensationChangeBack, click = true)
    private LinearLayout ll_compensationChangeBack;
    @BindView(id = R.id.tv_compensationChangeBack)
    private TextView tv_compensationChangeBack;

    @BindView(id = R.id.ll_userEvaluation, click = true)
    private LinearLayout ll_userEvaluation;
    @BindView(id = R.id.tv_userEvaluationNum)
    private TextView tv_userEvaluationNum;

    @BindView(id = R.id.ll_userevaluation1)
    private LinearLayout ll_userevaluation1;

    @BindView(id = R.id.img_head)
    private ImageView img_head;

    @BindView(id = R.id.tv_nickName)
    private TextView tv_nickName;

    @BindView(id = R.id.tv_content)
    private TextView tv_content;

    @BindView(id = R.id.tv_time)
    private TextView tv_time;

    @BindView(id = R.id.img_satisfactionLevel)
    private ImageView img_satisfactionLevel;

    @BindView(id = R.id.ll_giveLike, click = true)
    private LinearLayout ll_giveLike;

    @BindView(id = R.id.img_giveLike)
    private ImageView img_giveLike;

    @BindView(id = R.id.tv_zanNum)
    private TextView tv_zanNum;

    @BindView(id = R.id.tv_nextStep, click = true)
    private TextView tv_nextStep;

    private int product_id = 0;
    private int type = 0;

    private PriceInformationViewAdapter mAdapter;

    private PriceInformationBean priceInformationBean;
    private CompensationChangeBackDialog compensationChangeBackDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_btdpriceinformation);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new PriceInformationPresenter(this);
        product_id = getIntent().getIntExtra("product_id", 0);
        type = getIntent().getIntExtra("type", 0);
        mAdapter = new PriceInformationViewAdapter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((PriceInformationContract.Presenter) mPresenter).getProductDetails(product_id);
        initDialog();
    }

    private void initDialog() {
        compensationChangeBackDialog = new CompensationChangeBackDialog(this);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.priceInformation), true, R.id.titlebar);
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
                    compensationChangeBackDialog.setText(priceInformationBean.getData().getService_policy_content());
                }
                break;
            case R.id.ll_userEvaluation:
                Intent intent = new Intent(aty, CharterCommentsActivity.class);
                intent.putExtra("product_id", product_id);
                showActivity(aty, intent);
                break;
            case R.id.ll_giveLike:
                showLoadingDialog(getString(R.string.dataLoad));
                ((PriceInformationContract.Presenter) mPresenter).postAddCommentLike(priceInformationBean.getData().getReview_list().get(0).getId(), 3);
                break;
            case R.id.tv_nextStep:
                Intent intent1 = new Intent();
                intent1.setClass(aty, ByTheDayCharterActivity.class);
                intent1.putExtra("title", priceInformationBean.getData().getTitle());
                intent1.putExtra("baggage_number", priceInformationBean.getData().getBaggage_number());
                intent1.putExtra("passenger_number", priceInformationBean.getData().getPassenger_number());
                if (priceInformationBean.getData().getPicture() != null && priceInformationBean.getData().getPicture().size() > 0) {
                    intent1.putExtra("picture", priceInformationBean.getData().getPicture().get(0));
                }
                intent1.putExtra("product_id", product_id);
                showActivity(aty, intent1);
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
    public void setPresenter(PriceInformationContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            priceInformationBean = (PriceInformationBean) JsonUtil.getInstance().json2Obj(success, PriceInformationBean.class);
            if (priceInformationBean.getData().getPicture() != null && priceInformationBean.getData().getPicture().size() > 0) {
                mForegroundBanner.setVisibility(View.VISIBLE);
                processLogic(priceInformationBean.getData().getPicture());
            } else {
                mForegroundBanner.setVisibility(View.GONE);
            }
            tv_modelCar.setText(priceInformationBean.getData().getModel());
            tv_canTakeNumber.setText(priceInformationBean.getData().getPassenger());
            tv_carPrices.setText(getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(priceInformationBean.getData().getPrice())));
            if (priceInformationBean.getData() != null && priceInformationBean.getData().getService() != null && priceInformationBean.getData().getService().size() > 0) {
                gv_containsService.setVisibility(View.VISIBLE);
                tv_containsService.setVisibility(View.VISIBLE);
                mAdapter.clear();
                mAdapter.addNewData(priceInformationBean.getData().getService());
            } else {
                tv_containsService.setVisibility(View.GONE);
                gv_containsService.setVisibility(View.GONE);
            }
            tv_serviceHours.setText(priceInformationBean.getData().getService_duration());
            tv_serviceMileage.setText(priceInformationBean.getData().getService_mileage());
            tv_compensationChangeBack.setText(priceInformationBean.getData().getService_policy());
            tv_serviceDescription.setText(priceInformationBean.getData().getService_description());
            tv_remark.setText(priceInformationBean.getData().getService_note());
            tv_compensationChangeBack.setText(priceInformationBean.getData().getService_policy());
            tv_userEvaluationNum.setText(priceInformationBean.getData().getReview_count() + getString(R.string.comments1));
            if (priceInformationBean.getData().getReview_list() != null && priceInformationBean.getData().getReview_list().size() > 0) {
                ll_userevaluation1.setVisibility(View.VISIBLE);
                GlideImageLoader.glideLoader(this, priceInformationBean.getData().getReview_list().get(0).getFace(), img_head, 0, R.mipmap.avatar);
                tv_nickName.setText(priceInformationBean.getData().getReview_list().get(0).getNickname());
                tv_content.setText(priceInformationBean.getData().getReview_list().get(0).getContent());
                tv_time.setText(DataUtil.formatData(StringUtils.toLong(priceInformationBean.getData().getReview_list().get(0).getCreate_time()), "yyyy.MM.dd"));
                tv_zanNum.setText(priceInformationBean.getData().getReview_list().get(0).getLike_number() + "");
                switch (priceInformationBean.getData().getReview_list().get(0).getSatisfaction_level()) {
                    case 1:
                        img_satisfactionLevel.setImageResource(R.mipmap.comment_star_one);
                        break;
                    case 2:
                        img_satisfactionLevel.setImageResource(R.mipmap.comment_star_two);
                        break;
                    case 3:
                        img_satisfactionLevel.setImageResource(R.mipmap.comment_star_three);
                        break;
                    case 4:
                        img_satisfactionLevel.setImageResource(R.mipmap.comment_star_four);
                        break;
                    case 5:
                        img_satisfactionLevel.setImageResource(R.mipmap.comment_star_five);
                        break;
                }
                if (priceInformationBean.getData().getReview_list().get(0).isIs_like()) {
                    img_giveLike.setImageResource(R.mipmap.dynamic_zan1);
                    tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
                } else {
                    img_giveLike.setImageResource(R.mipmap.dynamic_zan);
                    //       tv_zanNum.setText(getString(R.string.giveLike));
                    tv_zanNum.setTextColor(getResources().getColor(R.color.tabColors));
                }
            } else {
                ll_userevaluation1.setVisibility(View.GONE);
            }
        } else if (flag == 1) {
            if (priceInformationBean.getData().getReview_list().get(0).isIs_like()) {
                tv_zanNum.setText(priceInformationBean.getData().getReview_list().get(0).getLike_number() - 1 + "");
                priceInformationBean.getData().getReview_list().get(0).setLike_number(StringUtils.toInt(tv_zanNum.getText().toString(), 0));
                priceInformationBean.getData().getReview_list().get(0).setIs_like(false);
                img_giveLike.setImageResource(R.mipmap.dynamic_zan);
                tv_zanNum.setTextColor(getResources().getColor(R.color.tabColors));
                ViewInject.toast(getString(R.string.cancelZanSuccess));
            } else {
                tv_zanNum.setText(priceInformationBean.getData().getReview_list().get(0).getLike_number() + 1 + "");
                priceInformationBean.getData().getReview_list().get(0).setLike_number(StringUtils.toInt(tv_zanNum.getText().toString(), 0));
                priceInformationBean.getData().getReview_list().get(0).setIs_like(true);
                img_giveLike.setImageResource(R.mipmap.dynamic_zan1);
                tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getString(R.string.zanSuccess));
            }
        }
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
    }
}
