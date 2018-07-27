package com.sillykid.app.homepage.privatecustom;

import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.sillykid.app.R;


/**
 * 私人定制  填写需求
 */
public class PrivateCustomActivity extends BaseActivity implements PrivateCustomContract.View {


    private OptionsPickerView pvOptions;

    private OptionsPickerView pvOptions1;

    private OptionsPickerView pvOptions2;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_privatecustom);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new PrivateCustomPresenter(this);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        selectTravelPreferences();
        selectRecommendRestaurant();
        selectRecommendedAccommodation();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.privateOrdering), true, R.id.titlebar);
    }


    /**
     * 选择出行偏好
     */
    @SuppressWarnings("unchecked")
    private void selectTravelPreferences() {
        pvOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                //  bankName = bankList.get(options1).getId();
                //    ((TextView) v).setText(bankList.get(options1).getName());
            }
        }).build();
    }


    /**
     * 选择餐馆偏好
     */
    @SuppressWarnings("unchecked")
    private void selectRecommendRestaurant() {
        pvOptions1 = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                //  bankName = bankList.get(options1).getId();
                //   ((TextView) v).setText(bankList.get(options1).getName());
            }
        }).build();
    }


    /**
     * 选择住宿偏好
     */
    @SuppressWarnings("unchecked")
    private void selectRecommendedAccommodation() {
        pvOptions2 = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                //  bankName = bankList.get(options1).getId();
                //    ((TextView) v).setText(bankList.get(options1).getName());
            }
        }).build();
    }


    @Override
    public void setPresenter(PrivateCustomContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();


    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
    }
}
