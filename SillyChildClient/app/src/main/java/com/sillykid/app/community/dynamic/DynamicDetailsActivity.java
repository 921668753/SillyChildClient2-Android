package com.sillykid.app.community.dynamic;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.HomePageBean;
import com.sillykid.app.loginregister.LoginActivity;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 动态详情
 */
public class DynamicDetailsActivity extends BaseActivity implements DynamicDetailsContract.View , BGABanner.Delegate<ImageView, HomePageBean.ResultBean.AdBean>, BGABanner.Adapter<ImageView, HomePageBean.ResultBean.AdBean>{

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    @BindView(id = R.id.ll_author, click = true)
    private LinearLayout ll_author;

    @BindView(id = R.id.img_head)
    private ImageView img_head;

    @BindView(id = R.id.tv_nickName)
    private TextView tv_nickName;

    @BindView(id = R.id.tv_follow, click = true)
    private TextView tv_follow;

    private int id = 0;

    private String title = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_dynamicdetails);
    }

    @Override
    public void initData() {
        super.initData();
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        mPresenter = new DynamicDetailsPresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((DynamicDetailsContract.Presenter) mPresenter).getDynamicDetails(id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);


    }


    @Override
    public void setPresenter(DynamicDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {

        } else if (flag == 1) {

        } else if (flag == 2) {

        } else if (flag == 3) {

        } else if (flag == 4) {

        } else if (flag == 5) {

        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
        } else {
            ViewInject.toast(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

}
