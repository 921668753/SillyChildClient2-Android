package com.sillykid.app.mall.goodslist.shop;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;

/**
 * 店铺      店铺首页  全部商品
 * Created by Admin on 2017/8/21.
 */

public class ShopActivity extends BaseActivity {

    @BindView(id = R.id.tv_shopHomepage, click = true)
    private TextView tv_shopHomepage;

    @BindView(id = R.id.tv_allGoods, click = true)
    private TextView tv_allGoods;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;

    private int chageIcon = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_shop);
    }

    @Override
    public void initData() {
        super.initData();
        contentFragment = new ShopHomePageFragment();
        contentFragment1 = new AllGoodsFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.shop), true, R.id.titlebar);
        if (chageIcon == 0) {
            cleanColors(0);
            changeFragment(contentFragment);
            chageIcon = 0;
        } else if (chageIcon == 1) {
            cleanColors(1);
            changeFragment(contentFragment1);
            chageIcon = 1;
        }
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
}
