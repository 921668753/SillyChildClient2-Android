package com.sillykid.app.mall.goodslist.shop;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.sillykid.app.R;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mall.goodslist.shop.ShopBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.mycollection.CollectionContract;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 店铺      店铺首页  全部商品
 * Created by Admin on 2017/8/21.
 */

public class ShopActivity extends BaseActivity implements ShopContract.View {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.tv_shopHomepage, click = true)
    private TextView tv_shopHomepage;

    @BindView(id = R.id.tv_allGoods, click = true)
    private TextView tv_allGoods;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;

    private int chageIcon = 0;
    private int storeid = 0;

    private boolean favorited = false;

    private int isRefresh = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_shop);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ShopPresenter(this);
        contentFragment = new ShopHomePageFragment();
        contentFragment1 = new AllGoodsFragment();
        storeid = aty.getIntent().getIntExtra("storeid", 0);
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        titlebar.setTitleText(R.string.shop);
        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.store_collection1));
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                if (isRefresh == 1) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                }
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                showLoadingDialog(getString(R.string.dataLoad));
                if (!favorited) {
                    ((ShopContract.Presenter) mPresenter).postFavoriteAdd(storeid);
                } else {
                    ((ShopContract.Presenter) mPresenter).postUnfavorite(storeid);
                }
            }
        };
        titlebar.setDelegate(simpleDelegate);
        if (chageIcon == 0) {
            cleanColors(0);
            changeFragment(contentFragment);
            chageIcon = 0;
        } else if (chageIcon == 1) {
            cleanColors(1);
            changeFragment(contentFragment1);
            chageIcon = 1;
        }
        ((ShopContract.Presenter) mPresenter).getCheckFavorited(storeid);
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_shopHomepage:
                cleanColors(0);
                changeFragment(contentFragment);
                chageIcon = 0;
                break;
            case R.id.tv_allGoods:
                cleanColors(1);
                changeFragment(contentFragment1);
                chageIcon = 1;
                break;
            default:
                break;
        }
    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_shopHomepage.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_allGoods.setTextColor(getResources().getColor(R.color.titletextcolors));
        if (chageIcon == 0) {
            tv_shopHomepage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        } else if (chageIcon == 1) {
            tv_allGoods.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        } else {
            tv_shopHomepage.setTextColor(getResources().getColor(R.color.loginBackgroundColors));
        }
    }

    public int getChageIcon() {
        return chageIcon;
    }

    @Override
    public void setPresenter(ShopContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ShopBean shopBean = (ShopBean) JsonUtil.getInstance().json2Obj(success, ShopBean.class);
            if (shopBean.getData().getResult() == 1) {
                favorited = true;
            } else {
                favorited = false;
            }
            if (favorited) {
                titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.store_collection));
            } else {
                titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.store_collection1));
            }
        } else if (flag == 1) {
            favorited = true;
            isRefresh = 1;
            titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.store_collection));
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.collectionSuccess));
        } else if (flag == 2) {
            favorited = false;
            isRefresh = 1;
            titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.store_collection1));
            ViewInject.toast(getString(R.string.uncollectible));
            dismissLoadingDialog();
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null) {
            ((ShopContract.Presenter) mPresenter).getCheckFavorited(storeid);
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

}
