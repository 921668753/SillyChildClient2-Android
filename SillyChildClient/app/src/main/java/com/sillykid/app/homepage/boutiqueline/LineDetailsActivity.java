package com.sillykid.app.homepage.boutiqueline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.klavor.widget.RatingBar;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.constant.URLConstants;
import com.sillykid.app.entity.homepage.airporttransportation.airportpickup.AirportPickupBean;
import com.sillykid.app.entity.homepage.airporttransportation.airportpickup.PeopleBean;
import com.sillykid.app.entity.homepage.boutiqueline.LineDetailsBean;
import com.sillykid.app.homepage.airporttransportation.comments.CharterCommentsActivity;
import com.sillykid.app.homepage.airporttransportation.dialog.CompensationChangeBackDialog;
import com.sillykid.app.homepage.boutiqueline.dialog.CalendarControlBouncedDialog;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.sharingceremony.dialog.ShareBouncedDialog;
import com.sillykid.app.utils.DataUtil;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SoftKeyboardUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;


/**
 * 线路详情
 */
public class LineDetailsActivity extends BaseActivity implements LineDetailsContract.View {

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.img_share, click = true)
    private ImageView img_share;

    @BindView(id = R.id.img_picture)
    private ImageView img_picture;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.ratingbar)
    private RatingBar ratingbar;

    @BindView(id = R.id.tv_ratingbar)
    private TextView tv_ratingbar;

    @BindView(id = R.id.tv_experienceBrightSpot)
    private TextView tv_experienceBrightSpot;

    @BindView(id = R.id.web_view)
    private WebViewLayout web_view;

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

    @BindView(id = R.id.img_satisfactionLevel)
    private ImageView img_satisfactionLevel;

    @BindView(id = R.id.tv_content)
    private TextView tv_content;

    @BindView(id = R.id.tv_time)
    private TextView tv_time;

    @BindView(id = R.id.ll_giveLike, click = true)
    private LinearLayout ll_giveLike;

    @BindView(id = R.id.img_giveLike)
    private ImageView img_giveLike;

    @BindView(id = R.id.tv_zanNum)
    private TextView tv_zanNum;

    @BindView(id = R.id.tv_paymentOrder, click = true)
    private TextView tv_paymentOrder;

    private ShareBouncedDialog shareBouncedDialog = null;


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


    @BindView(id = R.id.ll_luggage, click = true)
    private LinearLayout ll_luggage;
    @BindView(id = R.id.tv_luggage)
    private TextView tv_luggage;
    private OptionsPickerView luggageOptions = null;

    @BindView(id = R.id.tv_packagePrice)
    private TextView tv_packagePrice;

    @BindView(id = R.id.tv_orderPriceNoSign)
    private TextView tv_orderPriceNoSign;

    @BindView(id = R.id.et_remark)
    private TextView et_remark;


    private int product_id = 0;
    private String smallImg;
    private String product_name;
    private String subtitle;
    private LineDetailsBean lineDetailsBean;

    private int baggage_number = 0;
    private int passenger_number = 0;
    private int baggage_number1 = 0;
    private int adult_number = 0;
    private int children_number = 0;
    private CompensationChangeBackDialog compensationChangeBackDialog;
    private CalendarControlBouncedDialog calendarControlBouncedDialog;
    private String price = "";


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_linedetails);
    }

    @Override
    public void initData() {
        super.initData();
        product_id = getIntent().getIntExtra("id", 0);
        mPresenter = new LineDetailsPresenter(this);
        initCalendarControlBouncedDialog();
        initDialog();
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
        initShareBouncedDialog();
        showLoadingDialog(getString(R.string.dataLoad));
        ((LineDetailsContract.Presenter) mPresenter).getRouteDetail(product_id);
        web_view.setTitleVisibility(false);
    }

    /**
     * 分享
     */
    private void initShareBouncedDialog() {
        shareBouncedDialog = new ShareBouncedDialog(this) {
            @Override
            public void share(SHARE_MEDIA platform) {
                umShare(platform);
            }
        };
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_share:
                //分享
                if (shareBouncedDialog == null) {
                    initShareBouncedDialog();
                }
                if (shareBouncedDialog != null & !shareBouncedDialog.isShowing()) {
                    shareBouncedDialog.show();
                }
                break;
            case R.id.ll_userEvaluation:
                Intent intent1 = new Intent(aty, CharterCommentsActivity.class);
                intent1.putExtra("product_id", product_id);
                showActivity(aty, intent1);
                break;
            case R.id.ll_giveLike:
                showLoadingDialog(getString(R.string.dataLoad));
                ((LineDetailsContract.Presenter) mPresenter).postAddCommentLike(lineDetailsBean.getData().getReview_list().get(0).getId(), 3);
                break;
            case R.id.ll_compensationChangeBack:
                if (compensationChangeBackDialog == null) {
                    initDialog();
                }
                if (compensationChangeBackDialog != null && !compensationChangeBackDialog.isShowing()) {
                    compensationChangeBackDialog.show();
                    compensationChangeBackDialog.setText(lineDetailsBean.getData().getService_policy_content());
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
            case R.id.ll_luggage:
                SoftKeyboardUtils.packUpKeyboard(this);
                luggageOptions.show(tv_luggage);
                break;
            case R.id.tv_paymentOrder:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((DueDemandContract.Presenter) mPresenter).postAddRequirements(product_id, String.valueOf(timeDeparture),
                        et_contact.getText().toString().trim(), et_contactWay.getText().toString().trim(), String.valueOf(adult_number), String.valueOf(children_number),
                        String.valueOf(baggage_number1), et_remark.getText().toString().trim(), passenger_number, baggage_number);
                break;
        }
    }

    @Override
    public void setPresenter(LineDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            lineDetailsBean = (LineDetailsBean) JsonUtil.getInstance().json2Obj(success, LineDetailsBean.class);
            smallImg = lineDetailsBean.getData().getMain_picture();
            GlideImageLoader.glideOrdinaryLoader(this, smallImg, img_picture, R.mipmap.placeholderfigure);
            product_name = lineDetailsBean.getData().getProduct_name();
            tv_title.setText(product_name);
            ratingbar.setRating((float) StringUtils.toDouble(lineDetailsBean.getData().getRecommended()));
            ratingbar.refreshUI();
            tv_ratingbar.setText(lineDetailsBean.getData().getRecommended() + getString(R.string.minute));
            subtitle = lineDetailsBean.getData().getSubtitle();
            tv_experienceBrightSpot.setText(subtitle);
            String code = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" + lineDetailsBean.getData().getProduct_description()
                    + "</body></html>";
            web_view.loadDataWithBaseURL(null, code, "text/html", "utf-8", null);
            web_view.getWebView().setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tv_userEvaluationNum.setText(lineDetailsBean.getData().getReview_count() + getString(R.string.comments1));
            if (lineDetailsBean.getData().getReview_list() != null && lineDetailsBean.getData().getReview_list().size() > 0) {
                ll_userevaluation1.setVisibility(View.VISIBLE);
                GlideImageLoader.glideLoader(this, lineDetailsBean.getData().getReview_list().get(0).getFace(), img_head, 0, R.mipmap.avatar);
                tv_nickName.setText(lineDetailsBean.getData().getReview_list().get(0).getNickname());
                tv_content.setText(lineDetailsBean.getData().getReview_list().get(0).getContent());
                tv_time.setText(DataUtil.formatData(StringUtils.toLong(lineDetailsBean.getData().getReview_list().get(0).getCreate_time()), "yyyy.MM.dd"));
                tv_zanNum.setText(lineDetailsBean.getData().getReview_list().get(0).getLike_number() + "");
                switch (lineDetailsBean.getData().getReview_list().get(0).getSatisfaction_level()) {
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
                if (lineDetailsBean.getData().getReview_list().get(0).isIs_like()) {
                    img_giveLike.setImageResource(R.mipmap.dynamic_zan1);
                    tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
                } else {
                    img_giveLike.setImageResource(R.mipmap.dynamic_zan);
                    //       tv_zanNum.setText(getString(R.string.giveLike));
                    tv_zanNum.setTextColor(getResources().getColor(R.color.tabColors));
                }
                tv_compensationChangeBack.setText(lineDetailsBean.getData().getService_policy_title());
                price = lineDetailsBean.getData().getPrice();
                tv_packagePrice.setText(lineDetailsBean.getData().getPrice() + getString(R.string.yuanPerson));
                tv_orderPriceNoSign.setText("0.00" + getString(R.string.yuan));
                passenger_number = lineDetailsBean.getData().getPassenger_number();
                baggage_number = lineDetailsBean.getData().getBaggage_number();
                initOptions(passenger_number, baggage_number);
            } else {
                ll_userevaluation1.setVisibility(View.GONE);
            }

        } else if (flag == 1) {
            if (lineDetailsBean.getData().getReview_list().get(0).isIs_like()) {
                tv_zanNum.setText(lineDetailsBean.getData().getReview_list().get(0).getLike_number() - 1 + "");
                lineDetailsBean.getData().getReview_list().get(0).setLike_number(StringUtils.toInt(tv_zanNum.getText().toString(), 0));
                lineDetailsBean.getData().getReview_list().get(0).setIs_like(false);
                img_giveLike.setImageResource(R.mipmap.dynamic_zan);
                tv_zanNum.setTextColor(getResources().getColor(R.color.tabColors));
                ViewInject.toast(getString(R.string.cancelZanSuccess));
            } else {
                tv_zanNum.setText(lineDetailsBean.getData().getReview_list().get(0).getLike_number() + 1 + "");
                lineDetailsBean.getData().getReview_list().get(0).setLike_number(StringUtils.toInt(tv_zanNum.getText().toString(), 0));
                lineDetailsBean.getData().getReview_list().get(0).setIs_like(true);
                img_giveLike.setImageResource(R.mipmap.dynamic_zan1);
                tv_zanNum.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getString(R.string.zanSuccess));
            }
        } else if (flag == 2) {
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
                tv_orderPriceNoSign.setText(MathUtil.keepTwo(adult_number * StringUtils.toDouble(price)) + getString(R.string.yuan));
            }
        }).build();
        adultOptions.setPicker(list);
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

    /**
     * 直接分享
     * SHARE_MEDIA.QQ
     */
    public void umShare(SHARE_MEDIA platform) {
        UMImage thumb = new UMImage(this, smallImg);
        String invite_code = PreferenceHelper.readString(aty, StringConstants.FILENAME, "invite_code", "");
        String url = URLConstants.REGISTERHTML + invite_code;
        UMWeb web = new UMWeb(url);
        web.setTitle(product_name);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(subtitle);
        new ShareAction(aty).setPlatform(platform)
//                .withText("hello")
//                .withMedia(thumb)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            showLoadingDialog(getString(R.string.shareJumpLoad));
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            dismissLoadingDialog();
            Log.d("throw", "platform" + platform);
            ViewInject.toast(getString(R.string.shareSuccess));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            dismissLoadingDialog();
            if (t.getMessage().contains(getString(R.string.authoriseErr3))) {
                ViewInject.toast(getString(R.string.authoriseErr2));
                return;
            }
            ViewInject.toast(getString(R.string.shareError));
            //   ViewInject.toast(t.getMessage());
            Log.d("throw", "throw:" + t.getMessage());
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Log.d("throw", "throw:" + "onCancel");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        if (shareBouncedDialog != null) {
            shareBouncedDialog.cancel();
        }
        shareBouncedDialog = null;
        if (compensationChangeBackDialog != null && compensationChangeBackDialog.isShowing()) {
            compensationChangeBackDialog.cancel();
        }
        compensationChangeBackDialog = null;
        if (calendarControlBouncedDialog != null && calendarControlBouncedDialog.isShowing()) {
            calendarControlBouncedDialog.cancel();
        }
        calendarControlBouncedDialog = null;
//        webViewLayout.removeAllViews();
//        webViewLayout = null;
    }
}
