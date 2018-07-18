package com.sillykid.app.homepage.chartercustom.transfer;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.sillykid.app.R;
import com.sillykid.app.main.HomePageContract;
import com.sillykid.app.main.HomePagePresenter;

/**
 * 接送机
 * Created by Admin on 2017/8/16.
 */

public class TransferActivity extends BaseActivity implements HomePageContract.View {

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.ll_airportPickup, click = true)
    private LinearLayout ll_airportPickup;

    @BindView(id = R.id.tv_airportPickup)
    private TextView tv_airportPickup;

    @BindView(id = R.id.tv_airportPickup1)
    private TextView tv_airportPickup1;

    @BindView(id = R.id.ll_airportDropOff, click = true)
    private LinearLayout ll_airportDropOff;

    @BindView(id = R.id.tv_airportDropOff)
    private TextView tv_airportDropOff;

    @BindView(id = R.id.tv_airportDropOff1)
    private TextView tv_airportDropOff1;

    @BindView(id = R.id.img_customerService, click = true)
    private ImageView img_customerService;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;

    private int chageIcon = 0;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_transfer);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new HomePagePresenter(this);
        contentFragment = new AirportPickupFragment();
        contentFragment1 = new AirportDropOffFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        if (chageIcon == 0) {
            //      cleanColors(0);
            changeFragment(contentFragment);
            chageIcon = 0;
            ll_airportPickup.setVisibility(View.VISIBLE);
            ll_airportDropOff.setVisibility(View.GONE);
        } else if (chageIcon == 1) {
            //   cleanColors(1);
            changeFragment(contentFragment1);
            chageIcon = 1;
            ll_airportPickup.setVisibility(View.GONE);
            ll_airportDropOff.setVisibility(View.VISIBLE);
        }
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
//            case R.id.ll_airportPickup:
//                String flyName = aty.getIntent().getStringExtra("flyName");
//                if (!StringUtils.isEmpty(flyName)) {
//                    break;
//                }
//                cleanColors(0);
//                changeFragment(contentFragment);
//                chageIcon = 0;
//                break;
//            case R.id.ll_airportDropOff:
//                String flyName1 = aty.getIntent().getStringExtra("flyName");
//                if (!StringUtils.isEmpty(flyName1)) {
//                    break;
//                }
//                cleanColors(1);
//                changeFragment(contentFragment1);
//                chageIcon = 1;
//                break;
            case R.id.img_customerService:
                ((HomePageContract.Presenter) mPresenter).isLogin(1);
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
        tv_airportPickup.setTextColor(getResources().getColor(R.color.tabColors));
        tv_airportPickup1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        tv_airportDropOff.setTextColor(getResources().getColor(R.color.tabColors));
        tv_airportDropOff1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
        if (chageIcon == 0) {
            tv_airportPickup.setTextColor(getResources().getColor(R.color.greenColors));
            tv_airportPickup1.setBackgroundColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 1) {
            tv_airportDropOff.setTextColor(getResources().getColor(R.color.greenColors));
            tv_airportDropOff1.setBackgroundColor(getResources().getColor(R.color.greenColors));
        } else {
            tv_airportPickup.setTextColor(getResources().getColor(R.color.greenColors));
            tv_airportPickup1.setBackgroundColor(getResources().getColor(R.color.greenColors));
        }
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 1) {
           // showActivity(aty, OverleafActivity.class);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
    }
}
