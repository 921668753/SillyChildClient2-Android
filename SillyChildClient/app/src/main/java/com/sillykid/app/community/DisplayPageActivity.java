package com.sillykid.app.community;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.community.dynamic.DynamicDetailsContract;
import com.sillykid.app.entity.community.OtherUserInfoBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

/**
 * 个人发布展示页面
 */
public class DisplayPageActivity extends BaseActivity implements DisplayPageContract.View {


    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.iv_minetouxiang)
    private ImageView iv_minetouxiang;

    @BindView(id = R.id.tv_nickname)
    private TextView tv_nickname;

    @BindView(id = R.id.tv_serialNumber)
    private TextView tv_serialNumber;

    @BindView(id = R.id.tv_synopsis)
    private TextView tv_synopsis;


    @BindView(id = R.id.tv_giveLike)
    private TextView tv_giveLike;

    @BindView(id = R.id.tv_follow)
    private TextView tv_follow;

    @BindView(id = R.id.tv_fans)
    private TextView tv_fans;

    @BindView(id = R.id.tv_beCollected)
    private TextView tv_beCollected;

    @BindView(id = R.id.tv_followN, click = true)
    private TextView tv_followN;


    private int user_id = 0;

    private int isRefresh = 0;

    private int is_concern = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_displaypage);
    }

    @Override
    public void initData() {
        super.initData();
        user_id = getIntent().getIntExtra("user_id", 0);
        isRefresh = getIntent().getIntExtra("isRefresh", 0);
        mPresenter = new DisplayPagePresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((DisplayPageContract.Presenter) mPresenter).getOtherUserInfo(user_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();


    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                }
                finish();
                break;
            case R.id.tv_followN:
                String title = getString(R.string.attentionLoad);
                if (is_concern == 1) {
                    title = getString(R.string.cancelCttentionLoad);
                } else {
                    title = getString(R.string.attentionLoad);
                }
                showLoadingDialog(title);
                ((DisplayPageContract.Presenter) mPresenter).postAddConcern(user_id, 1);
                break;
        }

    }

    @Override
    public void setPresenter(DisplayPageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            OtherUserInfoBean otherUserInfoBean = (OtherUserInfoBean) JsonUtil.getInstance().json2Obj(success, OtherUserInfoBean.class);
            GlideImageLoader.glideLoader(this, otherUserInfoBean.getData().getFace(), iv_minetouxiang, 0, R.mipmap.avatar);
            tv_nickname.setText(otherUserInfoBean.getData().getNickname());
            tv_serialNumber.setText(otherUserInfoBean.getData().getShz());
            tv_synopsis.setText(otherUserInfoBean.getData().getSignature());
            tv_follow.setText(otherUserInfoBean.getData().getConcern_number());
            tv_beCollected.setText(otherUserInfoBean.getData().getCollected_number());
            tv_fans.setText(otherUserInfoBean.getData().getFans_number());
            tv_giveLike.setText(otherUserInfoBean.getData().getLike_number());
            is_concern = otherUserInfoBean.getData().getIs_concern();
            if (is_concern == 1) {
                tv_followN.setText(getString(R.string.followed));
                tv_followN.setBackgroundResource(R.drawable.shape_code1);
                tv_followN.setTextColor(getResources().getColor(R.color.tabColors));
            } else {
                tv_followN.setText(getString(R.string.follow));
                tv_followN.setBackgroundResource(R.drawable.shape_displaypage);
                tv_followN.setTextColor(getResources().getColor(R.color.whiteColors));
            }
        } else if (flag == 1) {























        } else if (flag == 2) {
            if (is_concern == 1) {
                is_concern = 0;
                tv_follow.setText(getString(R.string.follow));
                tv_follow.setBackgroundResource(R.drawable.shape_follow);
                tv_follow.setTextColor(getResources().getColor(R.color.greenColors));
                ViewInject.toast(getString(R.string.focusSuccess));
            } else {
                is_concern = 1;
                tv_follow.setText(getString(R.string.followed));
                tv_follow.setBackgroundResource(R.drawable.shape_followed);
                tv_follow.setTextColor(getResources().getColor(R.color.tabColors));
                ViewInject.toast(getString(R.string.attentionSuccess));
            }
            dismissLoadingDialog();
        }
        dismissLoadingDialog();
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
