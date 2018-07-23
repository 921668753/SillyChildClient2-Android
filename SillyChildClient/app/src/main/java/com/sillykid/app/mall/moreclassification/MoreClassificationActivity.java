package com.sillykid.app.mall.moreclassification;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mall.moreclassification.ClassificationViewAdapter;
import com.sillykid.app.adapter.mall.moreclassification.MoreClassificationViewAdapter;
import com.sillykid.app.entity.mall.moreclassification.ClassificationBean;
import com.sillykid.app.entity.mall.moreclassification.MoreClassificationBean;
import com.sillykid.app.mall.goodslist.GoodsListActivity;

import java.util.List;

/**
 * 更多分类
 * Created by Admin on 2017/8/20.
 */

public class MoreClassificationActivity extends BaseActivity implements MoreClassificationContract.View, AdapterView.OnItemClickListener {

    /**
     * 分类
     */
    @BindView(id = R.id.lv_moreClassification)
    private ListView lv_moreClassification;

    /**
     * 分类详情
     */
    @BindView(id = R.id.gv_classification)
    private GridView gv_classification;

    private MoreClassificationViewAdapter moreClassificationViewAdapter = null;

    private ClassificationViewAdapter classificationViewAdapter = null;

    private List<MoreClassificationBean.DataBean> moreClassificationList;

    private MoreClassificationBean.DataBean moreClassificationBean = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_moreclassification);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new MoreClassificationPresenter(this);
        moreClassificationViewAdapter = new MoreClassificationViewAdapter(this);
        classificationViewAdapter = new ClassificationViewAdapter(this);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.moreClassification), true, R.id.titlebar);
        lv_moreClassification.setAdapter(moreClassificationViewAdapter);
        lv_moreClassification.setOnItemClickListener(this);
        gv_classification.setAdapter(classificationViewAdapter);
        gv_classification.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MoreClassificationContract.Presenter) mPresenter).getClassification(0, 0);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.lv_moreClassification) {
            selectClassification(i);
        } else if (adapterView.getId() == R.id.gv_classification) {
            Intent goodsListIntent = new Intent(aty, GoodsListActivity.class);
            goodsListIntent.putExtra("cat", classificationViewAdapter.getItem(i).getCat_id());
            showActivity(aty, goodsListIntent);
        }
    }

    @Override
    public void setPresenter(MoreClassificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            MoreClassificationBean moreClassificationBean = (MoreClassificationBean) JsonUtil.getInstance().json2Obj(success, MoreClassificationBean.class);
            moreClassificationList = moreClassificationBean.getData();
            if (moreClassificationList != null && moreClassificationList.size() > 0) {
                selectClassification(0);
            }
        } else if (flag == 1) {
            ClassificationBean classificationBean = (ClassificationBean) JsonUtil.getInstance().json2Obj(success, ClassificationBean.class);
            classificationViewAdapter.clear();
            classificationViewAdapter.addNewData(classificationBean.getData());
            dismissLoadingDialog();
        }
    }

    /**
     * 选中分类
     *
     * @param position
     */
    private void selectClassification(int position) {
        for (int i = 0; i < moreClassificationList.size(); i++) {
            if (position == i || position == i && position == 0) {
                moreClassificationBean = moreClassificationList.get(i);
                moreClassificationBean.setIsSelected(1);
                ((MoreClassificationContract.Presenter) mPresenter).getClassification(moreClassificationBean.getCat_id(), 1);
            } else {
                moreClassificationList.get(i).setIsSelected(0);
            }
        }
        moreClassificationViewAdapter.clear();
        moreClassificationViewAdapter.addNewData(moreClassificationList);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }


}
