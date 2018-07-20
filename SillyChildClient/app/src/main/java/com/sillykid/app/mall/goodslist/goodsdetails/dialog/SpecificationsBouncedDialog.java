package com.sillykid.app.mall.goodslist.goodsdetails.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mall.goodslist.goodsdetails.dialog.SpecificationsBouncedViewAdapter;
import com.sillykid.app.adapter.mall.goodslist.goodsdetails.dialog.SpecificationsCvBouncedViewAdapter;
import com.sillykid.app.entity.mall.goodslist.goodsdetails.dialog.SpecificationsBean;
import com.sillykid.app.entity.mall.goodslist.goodsdetails.dialog.SpecificationsBouncedBean.DataBean.SpecValueIdsBean;
import com.sillykid.app.entity.mall.goodslist.goodsdetails.dialog.SpecificationsBouncedBean;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 商品详情--------规格弹框
 * Created by Administrator on 2017/8/21.
 */

public abstract class SpecificationsBouncedDialog extends BaseDialog implements View.OnClickListener, SpecificationsBouncedContract.View, SpecificationsBouncedViewAdapter.OnStatusListener {

    private SweetAlertDialog mLoadingDialog = null;

    private int goodsid = 0;

    private int flag = 0;

    private SpecificationsBouncedContract.Presenter mPresenter;

    private TextView tv_goodNumber;

    private ChildListView clv_specifications;

    private SpecificationsBouncedViewAdapter specificationsBouncedViewAdapter = null;

    private TextView tv_divider;

    private ImageView img_good;

    private TextView tv_inventoryEnough;

    private TextView tv_specifications;

    private TextView tv_money;

    private int product_id = 0;

    private int specificationsSize = 0;

    private int selectePosition = 0;
    private int selectePosition1 = 0;

    private int enable_store = 0;

    private int have_spec = 0;

    private String price = "";


    public SpecificationsBouncedDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_specificationsbounced);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        ImageView img_cancel = (ImageView) findViewById(R.id.img_cancel);
        img_cancel.setOnClickListener(this);
        img_good = (ImageView) findViewById(R.id.img_good);
        tv_specifications = (TextView) findViewById(R.id.tv_specifications);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_inventoryEnough = (TextView) findViewById(R.id.tv_inventoryEnough);
        tv_divider = (TextView) findViewById(R.id.tv_divider);
        clv_specifications = (ChildListView) findViewById(R.id.clv_specifications);
        specificationsBouncedViewAdapter = new SpecificationsBouncedViewAdapter(mContext);
        clv_specifications.setAdapter(specificationsBouncedViewAdapter);
        specificationsBouncedViewAdapter.setOnStatusListener(this);
        TextView tv_subtract = (TextView) findViewById(R.id.tv_subtract);
        tv_subtract.setOnClickListener(this);
        tv_goodNumber = (TextView) findViewById(R.id.tv_goodNumber);
        TextView tv_add = (TextView) findViewById(R.id.tv_add);
        tv_add.setOnClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_cancel:
                dismiss();
                break;
            case R.id.tv_subtract:
                int num = StringUtils.toInt(tv_goodNumber.getText().toString());
                if (num == 1) {
                    ViewInject.toast(mContext.getString(R.string.babyNotReduced));
                    return;
                }
                tv_goodNumber.setText(String.valueOf(num - 1));
                break;
            case R.id.tv_add:
                if (StringUtils.toInt(tv_goodNumber.getText().toString()) > enable_store) {
                    ViewInject.toast(mContext.getString(R.string.inventory) + mContext.getString(R.string.insufficient));
                    return;
                }
                tv_goodNumber.setText(String.valueOf(StringUtils.toInt(tv_goodNumber.getText().toString()) + 1));
                break;
            case R.id.tv_determine:
                if (enable_store <= 0 && have_spec == 1 || product_id == 0 && have_spec == 1) {
                    ViewInject.toast(mContext.getString(R.string.selectProductAttribute));
                    return;
                }
                if (enable_store <= 0) {
                    ViewInject.toast(mContext.getString(R.string.inventory) + mContext.getString(R.string.insufficient));
                    return;
                }
                dismiss();
                mPresenter = null;
                toDo(goodsid, flag, StringUtils.toInt(tv_goodNumber.getText().toString()), product_id);
                break;
        }
    }

    public abstract void toDo(int goodId, int flag, int num, int product_id);

    public void setFlag(int flag, int goodId, String img, String price, int have_spec, int product_id, int store) {
        this.flag = flag;
        this.goodsid = goodId;
        this.product_id = product_id;
        this.have_spec = have_spec;
        this.enable_store = store;
        if (store <= 0) {
            tv_inventoryEnough.setText(mContext.getString(R.string.inventory) + mContext.getString(R.string.insufficient));
        } else {
            tv_inventoryEnough.setText(mContext.getString(R.string.inventory) + store + mContext.getString(R.string.jian));
        }
        tv_goodNumber.setText("1");
        GlideImageLoader.glideOrdinaryLoader(mContext, img, img_good, R.mipmap.placeholderfigure1);
        tv_money.setText(mContext.getString(R.string.renminbi) + price);
        this.price = price;
        if (have_spec == 1) {
            clv_specifications.setVisibility(View.VISIBLE);
            tv_divider.setVisibility(View.VISIBLE);
            tv_specifications.setVisibility(View.VISIBLE);
            mPresenter = new SpecificationsBouncedPresenter(this);
            showLoadingDialog(mContext.getString(R.string.dataLoad));
            mPresenter.getGoodsSpec(mContext, goodsid);
            return;
        }
        tv_specifications.setVisibility(View.GONE);
        tv_divider.setVisibility(View.GONE);
        clv_specifications.setVisibility(View.GONE);
    }


    @Override
    public void setPresenter(SpecificationsBouncedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            SpecificationsBouncedBean specificationsBean = (SpecificationsBouncedBean) JsonUtil.getInstance().json2Obj(success, SpecificationsBouncedBean.class);
            if (specificationsBean.getData() == null || specificationsBean.getData().size() <= 0) {
                tv_divider.setVisibility(View.GONE);
                tv_specifications.setVisibility(View.GONE);
                clv_specifications.setVisibility(View.GONE);
                errorMsg("", 0);
                return;
            }
            specificationsBouncedViewAdapter.clear();
            specificationsBouncedViewAdapter.addNewData(specificationsBean.getData());
            tv_specifications.setText(mContext.getString(R.string.pleaseSelect) + unselectedSpecification());
        } else if (flag == 1) {
            SpecificationsBean specificationsBean = (SpecificationsBean) JsonUtil.getInstance().json2Obj(success, SpecificationsBean.class);
            if (specificationsBean == null || specificationsBean.getData() == null) {
                tv_money.setText(mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(price)));
                tv_inventoryEnough.setText(mContext.getString(R.string.inventory) + mContext.getString(R.string.insufficient));
                specificationsBouncedViewAdapter.getData().get(selectePosition).setIsSelected(0);
                specificationsBouncedViewAdapter.getData().get(selectePosition).getSpecValueIds().get(selectePosition1).setIsSelected(0);
                specificationsBouncedViewAdapter.getData().get(selectePosition).getSpecValueIds().get(selectePosition1).setIsNoClick(1);
                specificationsBouncedViewAdapter.notifyDataSetChanged();
                tv_specifications.setText(mContext.getString(R.string.pleaseSelect) + unselectedSpecification());
                enable_store = 0;
                return;
            }
            if (specificationsBean.getData() != null && StringUtils.toInt(specificationsBean.getData().getEnable_store(), 0) > 0 && specificationsBouncedViewAdapter.getData().size() == specificationsSize) {
                tv_specifications.setText(mContext.getString(R.string.selecte) + specificationsBean.getData().getSpecs());
                tv_money.setText(mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(specificationsBean.getData().getPrice())));
                tv_inventoryEnough.setText(mContext.getString(R.string.inventory) + specificationsBean.getData().getEnable_store() + mContext.getString(R.string.jian));
            } else {
                tv_money.setText(mContext.getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(price)));
                tv_inventoryEnough.setText(mContext.getString(R.string.inventory) + mContext.getString(R.string.insufficient));
                specificationsBouncedViewAdapter.getData().get(selectePosition).setIsSelected(0);
                specificationsBouncedViewAdapter.getData().get(selectePosition).getSpecValueIds().get(selectePosition1).setIsSelected(0);
                specificationsBouncedViewAdapter.getData().get(selectePosition).getSpecValueIds().get(selectePosition1).setIsNoClick(1);
                specificationsBouncedViewAdapter.notifyDataSetChanged();
                tv_specifications.setText(mContext.getString(R.string.pleaseSelect) + unselectedSpecification());
            }
            enable_store = StringUtils.toInt(specificationsBean.getData().getEnable_store(), 0);
            if (enable_store <= 0) {
                return;
            }
            product_id = specificationsBean.getData().getProduct_id();
        } else if (flag == 2) {
            SpecificationsBouncedBean specificationsBean = (SpecificationsBouncedBean) JsonUtil.getInstance().json2Obj(success, SpecificationsBouncedBean.class);
            setIsClick(specificationsBean);
        }
    }

    private void setIsClick(SpecificationsBouncedBean specificationsBean) {
        if (specificationsBean.getData() == null || specificationsBean.getData().size() <= 0) {
            specificationsBouncedViewAdapter.getData().get(selectePosition).setIsSelected(0);
            specificationsBouncedViewAdapter.getData().get(selectePosition).getSpecValueIds().get(selectePosition1).setIsSelected(0);
            specificationsBouncedViewAdapter.getData().get(selectePosition).getSpecValueIds().get(selectePosition1).setIsNoClick(1);
            specificationsBouncedViewAdapter.notifyDataSetChanged();
            return;
        }
        for (int i = 0; i < specificationsBouncedViewAdapter.getData().size(); i++) {
            if (specificationsBouncedViewAdapter.getData().get(i).getIsSelected() == 1) {
                continue;
            }
            for (int j = 0; j < specificationsBean.getData().size(); j++) {
                if (specificationsBean.getData().get(j).getSpec_id() == specificationsBouncedViewAdapter.getData().get(i).getSpec_id() && specificationsBean.getData().get(j).getSpecValueIds().size() > 0) {
                    List<SpecValueIdsBean> list = specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds();
                    List<SpecValueIdsBean> list1 = specificationsBean.getData().get(j).getSpecValueIds();
                    for (int k = 0; k < list.size(); k++) {
                        list.get(k).setIsNoClick(1);
                        list.get(k).setIsSelected(0);
                        for (int l = 0; l < list1.size(); l++) {
                            if (list.get(k).getSpec_value_id() == list1.get(l).getSpec_value_id()) {
                                list.get(k).setIsNoClick(0);
                            }
                        }
                    }
                } else if (specificationsBean.getData().get(j).getSpec_id() == specificationsBouncedViewAdapter.getData().get(i).getSpec_id() && specificationsBean.getData().get(j).getSpecValueIds().size() <= 0) {
                    for (int k = 0; k < specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().size(); k++) {
                        specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().get(k).setIsNoClick(1);
                        specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().get(k).setIsSelected(0);
                    }
                }
            }
        }
        specificationsBouncedViewAdapter.notifyDataSetChanged();
        tv_specifications.setText(mContext.getString(R.string.pleaseSelect) + unselectedSpecification());
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void onSetStatusListener(View view, SpecificationsCvBouncedViewAdapter adapter, int position, int position1) {
        if (adapter.getData().get(position1).getIsNoClick() == 1) {
            return;
        }
        int specifications = -1;
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (position1 == i && adapter.getData().get(i).getIsSelected() == 0) {
                adapter.getData().get(i).setIsSelected(1);
                specificationsBouncedViewAdapter.getData().get(position).setIsSelected(1);
                specifications = adapter.getData().get(position1).getSpec_value_id();
            } else if (position1 == i && adapter.getData().get(i).getIsSelected() == 1) {
                adapter.getData().get(i).setIsSelected(0);
                specificationsBouncedViewAdapter.getData().get(position).setIsSelected(0);
                for (int j = 0; j < adapter.getData().size(); j++) {
                    if (adapter.getData().get(j).getIsNoClick() == 1) {
                        adapter.getData().get(j).setIsNoClick(0);
                    }
                }
            } else {
                adapter.getData().get(i).setIsSelected(0);
            }

        }
        adapter.notifyDataSetChanged();
        if (specifications <= 0) {
            tv_specifications.setText(mContext.getString(R.string.pleaseSelect) + unselectedSpecification());
            return;
        }
        String specifications1 = "";
        specificationsSize = 0;
        for (int i = 0; i < specificationsBouncedViewAdapter.getData().size(); i++) {
            for (int j = 0; j < specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().size(); j++) {
                if (specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().get(j).getIsSelected() == 1) {
                    specificationsSize++;
                    specifications1 = specifications1 + "," + specificationsBouncedViewAdapter.getData().get(i).getSpecValueIds().get(j).getSpec_value_id();
                }
            }
        }
        if (StringUtils.isEmpty(specifications1) || StringUtils.isEmpty(specifications1.substring(1)) || specifications1.substring(1).length() <= 0) {
            tv_specifications.setText(mContext.getString(R.string.pleaseSelect) + unselectedSpecification());
            return;
        }
        selectePosition = position;
        selectePosition1 = position1;
        if (specificationsSize == specificationsBouncedViewAdapter.getData().size()) {
            ((SpecificationsBouncedContract.Presenter) mPresenter).getGoodsProductSpec(mContext, goodsid, specifications1.substring(1));
        } else {
            ((SpecificationsBouncedContract.Presenter) mPresenter).getGoodsProductSpecLeft(mContext, goodsid, specifications1.substring(1));
        }
    }

    /**
     * 获取未选择规格
     */
    private String unselectedSpecification() {
        String specifications = "";
        int unSpecificationsSize = 0;
        for (int i = 0; i < specificationsBouncedViewAdapter.getData().size(); i++) {
            if (specificationsBouncedViewAdapter.getData().get(i).getIsSelected() == 0) {
                unSpecificationsSize++;
                specifications = specifications + "、" + specificationsBouncedViewAdapter.getData().get(i).getSpec_name();
            }
        }
        if (StringUtils.isEmpty(specifications) || StringUtils.isEmpty(specifications.substring(1))) {
            return "";
        }
        if (unSpecificationsSize == specificationsBouncedViewAdapter.getData().size()) {
            clearSelectedSpecification();
        }
        return specifications.substring(1);
    }

    /**
     * 清空选项
     */
    private void clearSelectedSpecification() {
        for (int i = 0; i < specificationsBouncedViewAdapter.getData().size(); i++) {
            specificationsBouncedViewAdapter.getItem(i).setIsSelected(0);
            for (int j = 0; j < specificationsBouncedViewAdapter.getItem(i).getSpecValueIds().size(); j++) {
                specificationsBouncedViewAdapter.getItem(i).getSpecValueIds().get(j).setIsNoClick(0);
            }
        }
        specificationsBouncedViewAdapter.notifyDataSetChanged();
    }


    /**
     * @param title show Dialog
     */
    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(mContext.getResources().getColor(com.common.cklibrary.R.color.dialogLoadingColor));
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setTitleText(title);
        mLoadingDialog.show();
        ((View) mLoadingDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }

    /**
     * 关闭 Dialog
     */
    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            try {
                mLoadingDialog.dismiss();
            } catch (Exception e) {
                mLoadingDialog = null;
            }
        }
        mLoadingDialog = null;
    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            try {
                mLoadingDialog.dismiss();
            } catch (Exception e) {
                mLoadingDialog = null;
            }
        }
        mLoadingDialog = null;
        mPresenter = null;
    }

}
