package com.sillykid.app.mine.mycollection;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;

/**
 * 我的收藏
 * Created by Administrator on 2017/9/2.
 */

public class MyCollectionActivity1 extends BaseActivity {

    @BindView(id=R.id.ll_charter ,click = true)
    private LinearLayout ll_charter;
    @BindView(id=R.id.tv_charter )
    private TextView tv_charter;
    @BindView(id=R.id.v_charter )
    private View v_charter;

    @BindView(id=R.id.ll_route ,click = true)
    private LinearLayout ll_route;
    @BindView(id=R.id.tv_route )
    private TextView tv_route;
    @BindView(id=R.id.v_route )
    private View v_route;

    @BindView(id=R.id.ll_good ,click = true)
    private LinearLayout ll_good;
    @BindView(id=R.id.tv_good )
    private TextView tv_good;
    @BindView(id=R.id.v_good )
    private View v_good;

    @BindView(id=R.id.ll_house ,click = true)
    private LinearLayout ll_house;
    @BindView(id=R.id.tv_house )
    private TextView tv_house;
    @BindView(id=R.id.v_house)
    private View v_house;

    @BindView(id=R.id.ll_shop ,click = true)
    private LinearLayout ll_shop;
    @BindView(id=R.id.tv_shop )
    private TextView tv_shop;
    @BindView(id=R.id.v_shop )
    private View v_shop;
    private MyCollectionActivity goodFragment;
    private RouteFragment routeFragment;
    private HouseFragment houseFragment;
    private ShopFragment shopFragment;
    private int chageIcon;
    private CharterCollectionFragment charterCollectionFragment;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mycollection);
    }

    @Override
    public void initData() {
        super.initData();
        charterCollectionFragment=new CharterCollectionFragment();
        routeFragment = new RouteFragment();
        houseFragment = new HouseFragment();
      //  goodFragment = new MyCollectionActivity();
        shopFragment = new ShopFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        cleanColors();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.ll_charter:
                chageIcon=0;
                cleanColors();
                break;
            case R.id.ll_route:
                chageIcon=1;
                cleanColors();
                break;
            case R.id.ll_house:
                ViewInject.toast(getString(R.string.noDevelopment));
//                chageIcon=2;
//                cleanColors();
                break;
            case R.id.ll_good:
                ViewInject.toast(getString(R.string.noDevelopment));
//                chageIcon=3;
//                cleanColors();
                break;
            case R.id.ll_shop:
                ViewInject.toast(getString(R.string.noDevelopment));
//                chageIcon=4;
//                cleanColors();
                break;
        }
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fl_mycollection, targetFragment);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myCollection), true, R.id.titlebar);
    }

    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors() {
        tv_charter.setTextColor(getResources().getColor(R.color.hintColors));
        v_charter.setBackgroundResource(android.R.color.transparent);
        tv_route.setTextColor(getResources().getColor(R.color.hintColors));
        v_route.setBackgroundResource(android.R.color.transparent);
        tv_house.setTextColor(getResources().getColor(R.color.hintColors));
        v_house.setBackgroundResource(android.R.color.transparent);
        tv_good.setTextColor(getResources().getColor(R.color.hintColors));
        v_good.setBackgroundResource(android.R.color.transparent);
        tv_shop.setTextColor(getResources().getColor(R.color.hintColors));
        v_shop.setBackgroundResource(android.R.color.transparent);
        switch (chageIcon){
            case 0:
                tv_charter.setTextColor(getResources().getColor(R.color.greenColors));
                v_charter.setBackgroundResource(R.color.greenColors);
                changeFragment(charterCollectionFragment);
                break;
            case 1:
                tv_route.setTextColor(getResources().getColor(R.color.greenColors));
                v_route.setBackgroundResource(R.color.greenColors);
                changeFragment(routeFragment);
                break;
            case 2:
                tv_house.setTextColor(getResources().getColor(R.color.greenColors));
                v_house.setBackgroundResource(R.color.greenColors);
                changeFragment(houseFragment);
                break;
            case 3:
                tv_good.setTextColor(getResources().getColor(R.color.greenColors));
                v_good.setBackgroundResource(R.color.greenColors);
              //  changeFragment(goodFragment);
                break;
            case 4:
                tv_shop.setTextColor(getResources().getColor(R.color.greenColors));
                v_shop.setBackgroundResource(R.color.greenColors);
                changeFragment(shopFragment);
                break;
        }
    }

    public int getChageIcon() {
        return chageIcon;
    }

}
