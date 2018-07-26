package com.sillykid.app.mine.myrelease.mydynamic;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.entity.main.community.ClassificationListBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.List;

/**
 * 我的发布---发布新动态
 */
public class ReleaseDynamicActivity extends BaseActivity implements ReleaseDynamicContract.View {

    @BindView(id = R.id.ll_category, click = true)
    private LinearLayout ll_category;

    @BindView(id = R.id.tv_category)
    private TextView tv_category;

    @BindView(id = R.id.et_title)
    private EditText et_title;

    @BindView(id = R.id.et_addDescription)
    private EditText et_addDescription;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    @BindView(id = R.id.tv_release, click = true)
    private TextView tv_release;

    private List<ClassificationListBean.DataBean> classificationList;

    private OptionsPickerView pvOptions;

    private int classification_id = 0;

    private int type = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_releasedynamic);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ReleaseDynamicPresenter(this);
        selectClassification();
        showLoadingDialog(getString(R.string.dataLoad));
        ((ReleaseDynamicContract.Presenter) mPresenter).getClassificationList();
    }

    /**
     * 选择银行名称
     */
    @SuppressWarnings("unchecked")
    private void selectClassification() {
        pvOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                classification_id = classificationList.get(options1).getId();
                ((TextView) v).setText(classificationList.get(options1).getName());
            }
        }).build();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        SoftKeyboardUtils.packUpKeyboard(this);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.newTrends), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_category:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_category);
                break;
            case R.id.tv_release:
                showLoadingDialog(getString(R.string.submissionLoad));
//                ((ReleaseDynamicContract.Presenter) mPresenter).postAddPost(et_title.getText().toString().trim(), imgs,
//                        et_addDescription.getText().toString().trim(), classification_id, type);
                break;
        }
    }

    @Override
    public void setPresenter(ReleaseDynamicContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ClassificationListBean classificationListBean = (ClassificationListBean) JsonUtil.json2Obj(success, ClassificationListBean.class);
            classificationList = classificationListBean.getData();
            if (classificationList != null && classificationList.size() > 0) {
                pvOptions.setPicker(classificationList);
            }
            dismissLoadingDialog();
        } else if (flag == 1) {


        } else if (flag == 2) {


        } else if (flag == 3) {


        }


    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }


}
