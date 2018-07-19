package com.sillykid.app.homepage.dynamics;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.dynamics.DynamicsDetailsCommentarieViewAdapter;
import com.sillykid.app.entity.DynamicsDetailsBean;
import com.sillykid.app.entity.DynamicsDetailsBean.ResultBean.CommentsBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.mycollection.MyCollectionActivity;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 动态详情
 * Created by Admin on 2017/8/17.
 */

public class DynamicsDetailsActivity extends BaseActivity implements DynamicsDetailsContract.View, BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String>, BGAOnItemChildClickListener {

    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.img_head)
    private ImageView img_head;

    @BindView(id = R.id.tv_name)
    private TextView tv_name;


    @BindView(id = R.id.tv_attention, click = true)
    private TextView tv_attention;

    @BindView(id = R.id.ll_dynamicComments, click = true)
    private LinearLayout ll_dynamicComments;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_content)
    private TextView tv_content;

    @BindView(id = R.id.ll_time)
    private LinearLayout ll_time;

    @BindView(id = R.id.tv_date)
    private TextView tv_date;

    @BindView(id = R.id.tv_collect)
    private TextView tv_collect;

    @BindView(id = R.id.tv_zan)
    private TextView tv_zan;

    @BindView(id = R.id.tv_commentaries)
    private TextView tv_commentaries;

    @BindView(id = R.id.ll_dynamicsdetailsbottom)
    private LinearLayout ll_dynamicsdetailsbottom;

    @BindView(id = R.id.ll_zan, click = true)
    private LinearLayout ll_zan;

    @BindView(id = R.id.img_zan)
    private ImageView img_zan;

    @BindView(id = R.id.ll_comment, click = true)
    private LinearLayout ll_comment;

    @BindView(id = R.id.ll_dynamicCollection, click = true)
    private LinearLayout ll_dynamicCollection;

    @BindView(id = R.id.img_collect)
    private ImageView img_collect;

    @BindView(id = R.id.ll_dynamicsdetailscomment)
    private LinearLayout ll_dynamicsdetailscomment;


    @BindView(id = R.id.lv_dynamicsComments)
    private ChildListView lv_dynamicsComments;


    @BindView(id = R.id.et_writeComment)
    private EditText et_writeComment;

    @BindView(id = R.id.tv_published, click = true)
    private TextView tv_published;

    private String id;

    /**
     * 关注
     */
    private int isAttention = 0;

    /**
     * 收藏
     */
    private int isCollectDynamic = 0;

    /**
     * 点赞
     */
    private int isPraise = 0;
    private String publish_id;
    private DynamicsDetailsCommentarieViewAdapter dynamicsDetailsCommentarieViewAdapter;
    private int isComment = 0;
    private CommentsBean commentsBean = null;
    private int isPraiseComment = 0;
    private ImageView img_favor;
    private boolean first = false;
    private boolean two = false;
    private DynamicsDetailsBean dynamicsDetailsBean;
    private Intent intentresult;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_dynamicsdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new DynamicsDetailsPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        id = getIntent().getStringExtra("act_id");
        int is_read = getIntent().getIntExtra("is_read", 0);
        if (is_read == 0) {
            ((DynamicsDetailsContract.Presenter) mPresenter).getDynamicsDetails(id);
        } else {
            int message_id = getIntent().getIntExtra("message_id", 0);
            ((DynamicsDetailsContract.Presenter) mPresenter).getReadMessage(message_id + "");
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mForegroundBanner.setFocusable(true);
        mForegroundBanner.setFocusableInTouchMode(true);
        mForegroundBanner.requestFocus();
        initBanner();
        //  StringNewUtils.setFilters(et_writeComment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                resultToCollectionRoute();
                finish();
                break;
            case R.id.tv_attention:
                showLoadingDialog(getString(R.string.dataLoad));
                ((DynamicsDetailsContract.Presenter) mPresenter).getAttention(publish_id, isAttention);
                break;

            case R.id.ll_dynamicComments:
                ((DynamicsDetailsContract.Presenter) mPresenter).toDynamicComments(tv_commentaries, id, publish_id, this);
                break;

            case R.id.ll_zan:
                showLoadingDialog(getString(R.string.dataLoad));
                ((DynamicsDetailsContract.Presenter) mPresenter).praiseDynamicsDetails(id, isPraise);
                break;
            case R.id.ll_comment:
                isComment = 1;
                ((DynamicsDetailsContract.Presenter) mPresenter).changeInputView(et_writeComment, tv_published, isComment);
                ll_dynamicsdetailsbottom.setVisibility(View.GONE);
                ll_dynamicsdetailscomment.setVisibility(View.VISIBLE);
                et_writeComment.setHint(getString(R.string.writeComment));
                SoftKeyboardUtils.ejectKeyboard(et_writeComment);
                break;
            case R.id.ll_dynamicCollection:
                showLoadingDialog(getString(R.string.dataLoad));
                ((DynamicsDetailsContract.Presenter) mPresenter).collectDynamic(id, isCollectDynamic);
                break;
            case R.id.tv_published:
                if (tv_published.getText().toString().equals(getString(R.string.cancel))) {
                    SoftKeyboardUtils.packUpKeyboard(this);
                    ll_dynamicsdetailscomment.setVisibility(View.GONE);
                    ll_dynamicsdetailsbottom.setVisibility(View.VISIBLE);
                    return;
                }
                showLoadingDialog(getString(R.string.dataLoad));
                if (isComment == 1) {
                    ((DynamicsDetailsContract.Presenter) mPresenter).newActionComment(id, publish_id, et_writeComment.getText().toString().trim(), "");
                    return;
                }
                ((DynamicsDetailsContract.Presenter) mPresenter).newActionComment(id, publish_id, et_writeComment.getText().toString().trim(), commentsBean.getId());
                break;
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        commentsBean = dynamicsDetailsCommentarieViewAdapter.getItem(position);
        if (childView.getId() == R.id.tv_revert1) {
            isComment = 0;
            ((DynamicsDetailsContract.Presenter) mPresenter).changeInputView(et_writeComment, tv_published, isComment);
            ll_dynamicsdetailscomment.setVisibility(View.VISIBLE);
            ll_dynamicsdetailsbottom.setVisibility(View.GONE);
            et_writeComment.setHint(getString(R.string.revert) + commentsBean.getOwner().getNickname() + "：");
            SoftKeyboardUtils.ejectKeyboard(et_writeComment);
        } else if (childView.getId() == R.id.img_favor) {
            img_favor = (ImageView) childView.findViewById(R.id.img_favor);
            showLoadingDialog(getString(R.string.dataLoad));
            ((DynamicsDetailsContract.Presenter) mPresenter).praiseDynamicsDetailsComment(commentsBean.getId(), commentsBean.getIsPraise());
        } else if (childView.getId() == R.id.ll_revertNum) {
            if (position == 0) {
                first = true;
            } else {
                two = true;
            }
            loadPageData(dynamicsDetailsBean);
        }

    }


    @Override
    public void setPresenter(DynamicsDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            dynamicsDetailsBean = (DynamicsDetailsBean) JsonUtil.json2Obj(success, DynamicsDetailsBean.class);
            loadPageData(dynamicsDetailsBean);
        } else if (flag == 1) {
            isAttention = 1;
            ViewInject.toast(getString(R.string.attentionSuccess));
            tv_attention.setBackgroundResource(R.drawable.shape_code1);
            tv_attention.setTextColor(getResources().getColor(R.color.hintColors));
            tv_attention.setText(getString(R.string.attention1));
        } else if (flag == 2) {
            isAttention = 0;
            tv_attention.setBackgroundResource(R.drawable.shape_code);
            tv_attention.setTextColor(getResources().getColor(R.color.greenColors));
            tv_attention.setText(getString(R.string.attention));
            ViewInject.toast(getString(R.string.focusSuccess));
        } else if (flag == 3) {
            isPraise = 1;
            tv_zan.setText(StringUtils.toInt(tv_zan.getText().toString(), 0) + 1 + "");
        //    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshingHomePageFragment", true);
            img_zan.setImageResource(R.mipmap.btn_favor_pressed);
            ViewInject.toast(getString(R.string.thumbSuccess));
        } else if (flag == 4) {
            isPraise = 0;
            tv_zan.setText(StringUtils.toInt(tv_zan.getText().toString(), 0) - 1 + "");
            img_zan.setImageResource(R.mipmap.btn_favor_normal1);
            ViewInject.toast(getString(R.string.unThumbSuccess));
        } else if (flag == 5) {
            isCollectDynamic = 1;
            tv_collect.setText(StringUtils.toInt(tv_collect.getText().toString(), 0) + 1 + "");
            img_collect.setImageResource(R.mipmap.btn_collect_pressed);
            ViewInject.toast(getString(R.string.collectionSuccess));
        } else if (flag == 6) {
            isCollectDynamic = 0;
            tv_collect.setText(StringUtils.toInt(tv_collect.getText().toString(), 0) - 1 + "");
            img_collect.setImageResource(R.mipmap.btn_collect_normal1);
            ViewInject.toast(getString(R.string.uncollectible));
        } else if (flag == 7) {
            SoftKeyboardUtils.packUpKeyboard(this);
            ll_dynamicsdetailscomment.setVisibility(View.GONE);
            ll_dynamicsdetailsbottom.setVisibility(View.VISIBLE);
            et_writeComment.setText("");
            ((DynamicsDetailsContract.Presenter) mPresenter).getDynamicsDetails(id);
            ViewInject.toast(getString(R.string.commentSuccess));
            return;
        } else if (flag == 8) {
            //  isPraiseComment = 1;
            img_favor.setImageResource(R.mipmap.btn_favor_pressed);
            ViewInject.toast(getString(R.string.thumbSuccess));
        } else if (flag == 9) {
            //   isPraiseComment = 0;
            img_favor.setImageResource(R.mipmap.btn_favor_normal);
            ViewInject.toast(getString(R.string.unThumbSuccess));
        } else if (flag == 10) {
            ((DynamicsDetailsContract.Presenter) mPresenter).getDynamicsDetails(id);
            return;
        }
        dismissLoadingDialog();
    }


    /**
     * 加载页面数据
     */
    public void loadPageData(DynamicsDetailsBean dynamicsDetailsBean) {
        processLogic(dynamicsDetailsBean.getData().getImg());
        GlideImageLoader.glideLoader(this, dynamicsDetailsBean.getData().getOwner().getAvatar(), img_head, 0);
        tv_name.setText(dynamicsDetailsBean.getData().getOwner().getNickname());
        tv_title.setText(dynamicsDetailsBean.getData().getTitle());
        tv_content.setText(dynamicsDetailsBean.getData().getContent());
        tv_date.setText(dynamicsDetailsBean.getData().getTimeFmt());
        tv_collect.setText(dynamicsDetailsBean.getData().getCollectNum() + "");
        tv_zan.setText(dynamicsDetailsBean.getData().getPraiseNum() + "");
        publish_id = dynamicsDetailsBean.getData().getOwner().getId() + "";
        isAttention = dynamicsDetailsBean.getData().getOwner().getIsAttention();
        if (isAttention == 1) {
            tv_attention.setBackgroundResource(R.drawable.shape_code1);
            tv_attention.setTextColor(getResources().getColor(R.color.hintColors));
            tv_attention.setText(getString(R.string.attention1));
        } else {
            tv_attention.setBackgroundResource(R.drawable.shape_code);
            tv_attention.setTextColor(getResources().getColor(R.color.greenColors));
            tv_attention.setText(getString(R.string.attention));
        }
        isPraise = dynamicsDetailsBean.getData().getIsPraise();
        if (isPraise == 1) {
            img_zan.setImageResource(R.mipmap.btn_favor_pressed);
        } else {
            img_zan.setImageResource(R.mipmap.btn_favor_normal1);
        }
        isCollectDynamic = dynamicsDetailsBean.getData().getIsCollect();
        if (isCollectDynamic == 1) {
            img_collect.setImageResource(R.mipmap.btn_collect_pressed);
        } else {
            img_collect.setImageResource(R.mipmap.btn_collect_normal1);
        }
        if (dynamicsDetailsBean.getData().getComments() == null || dynamicsDetailsBean.getData().getComments().size() == 0) {
            tv_commentaries.setText("0");
        } else {
            tv_commentaries.setText(String.valueOf(dynamicsDetailsBean.getData().getComments().size()));
        }
        dynamicsDetailsCommentarieViewAdapter = null;
        dynamicsDetailsCommentarieViewAdapter = new DynamicsDetailsCommentarieViewAdapter(this, first, two);
        lv_dynamicsComments.setAdapter(dynamicsDetailsCommentarieViewAdapter);
        dynamicsDetailsCommentarieViewAdapter.setOnItemChildClickListener(this);
        List<CommentsBean> list = new ArrayList();
        for (int i = 0; i < dynamicsDetailsBean.getData().getComments().size(); i++) {
            if (i < 2) {
                list.add(dynamicsDetailsBean.getData().getComments().get(i));
            }
        }
        dynamicsDetailsCommentarieViewAdapter.addNewData(list);
    }


    /**
     * 初始化轮播图
     */

    public void initBanner() {
        mForegroundBanner.setAutoPlayAble(true);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setAllowUserScrollable(true);
        mForegroundBanner.setAutoPlayInterval(3000);
        // 初始化方式1：配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播
        mForegroundBanner.setAdapter(this);
        mForegroundBanner.setDelegate(this);
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(String img) {
        if (StringUtils.isEmpty(img)) {
            return;
        }
        List<String> list = new ArrayList<>();
        String[] img1 = img.split("\\|");
        if (img1 != null && img1.length > 0) {
            for (int i = 0; i < img1.length; i++) {
                list.add(img1[i]);
            }
        }
        //  }
        mForegroundBanner.setBackground(null);
        mForegroundBanner.setData(list, null);
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
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            Intent intent = new Intent(aty, LoginActivity.class);
            showActivity(aty, intent);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        GlideImageLoader.glideOrdinaryLoader(aty, model, itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();//此方法不
        resultToCollectionRoute();
        finish();
    }

    /**
     * 返回是否取消收藏的动态到我的，我的发布，收藏的动态
     */
    private void resultToCollectionRoute() {
        if (isCollectDynamic == 0) {
            if (intentresult == null) intentresult = new Intent(this, MyCollectionActivity.class);
            setResult(0, intentresult);
        }
    }


}
