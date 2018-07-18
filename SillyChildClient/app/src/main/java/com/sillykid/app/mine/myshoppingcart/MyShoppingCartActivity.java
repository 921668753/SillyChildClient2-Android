package com.sillykid.app.mine.myshoppingcart;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mine.myshoppingcart.MyShoppingCartViewAdapter;
import com.sillykid.app.entity.mine.myshoppingcart.MyShoppingCartBean;
import com.sillykid.app.entity.mine.myshoppingcart.MyShoppingCartBean.DataBean.StorelistBean.GoodslistBean;
import com.sillykid.app.homepage.goodslist.goodsdetails.GoodsDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myshoppingcart.makesureorder.MakeSureOrderActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的购物车
 * Created by Administrator on 2017/9/2.
 */

public class MyShoppingCartActivity extends BaseActivity implements MyShoppingCartContract.View, AbsListView.OnScrollListener, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.tv_delete, click = true)
    private TextView tv_delete;


    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 商品列表
     */
    @BindView(id = R.id.lv_myShoppingCart)
    private ListView lv_myShoppingCart;

    /**
     * 全选
     */

    @BindView(id = R.id.ll_bottom)
    private LinearLayout ll_bottom;

    @BindView(id = R.id.ll_allcheck, click = true)
    private LinearLayout ll_allcheck;

    @BindView(id = R.id.img_unselected)
    private ImageView img_unselected;


    /**
     * 合计
     */
    @BindView(id = R.id.tv_total)
    private TextView tv_total;
    @BindView(id = R.id.tv_renminbi)
    private TextView tv_renminbi;

    /**
     * 价格
     */
    @BindView(id = R.id.tv_money)
    private TextView tv_money;

    /**
     * 去结算
     */
    @BindView(id = R.id.tv_settleAccounts, click = true)
    private TextView tv_settleAccounts;

    /**
     * 是否编辑
     */
    private int isEdit = 0;

    private MyShoppingCartViewAdapter mAdapter = null;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    private int checkPosition = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myshoppingcart);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyShoppingCartPresenter(this);
        mAdapter = new MyShoppingCartViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, this, false);
        lv_myShoppingCart.setAdapter(mAdapter);
        lv_myShoppingCart.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        lv_myShoppingCart.setOnScrollListener(this);
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        titlebar.setTitleText(R.string.shoppingCart);
        titlebar.setRightText(R.string.edit);
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.textColor));
        titlebar.getRightCtv().setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tv_delete.setVisibility(View.GONE);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                if (isEdit == 0) {
                    completeStatus();
                } else {
                    editStatus();
                }
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }

    /**
     * 编辑
     */
    private void editStatus() {
        isEdit = 0;
        setEdit(mAdapter.getData());
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.textColor));
        titlebar.setRightText(R.string.edit);
        tv_delete.setVisibility(View.GONE);
        tv_total.setVisibility(View.VISIBLE);
        tv_renminbi.setVisibility(View.VISIBLE);
        tv_money.setVisibility(View.VISIBLE);
        tv_settleAccounts.setVisibility(View.VISIBLE);
    }


    /**
     * 完成
     */
    private void completeStatus() {
        isEdit = 1;
        setEdit(mAdapter.getData());
        mAdapter.closeOpenedSwipeItemLayoutWithAnim();
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.hintColors));
        titlebar.setRightText(R.string.complete);
        tv_delete.setVisibility(View.VISIBLE);
        tv_total.setVisibility(View.GONE);
        tv_renminbi.setVisibility(View.GONE);
        tv_money.setVisibility(View.GONE);
        tv_settleAccounts.setVisibility(View.GONE);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_allcheck:
                if (isAllSelected(mAdapter.getData())) {
                    setAllSelected(mAdapter.getData(), 0);
                    img_unselected.setImageResource(R.mipmap.shopping_cart_unselected);
                    return;
                }
                setAllSelected(mAdapter.getData(), 1);
                img_unselected.setImageResource(R.mipmap.shopping_cart_selected);
                break;
            case R.id.tv_delete:
                ((MyShoppingCartContract.Presenter) mPresenter).postDeleteGood(mAdapter.getData());
                break;
            case R.id.tv_settleAccounts:
                String list = ((MyShoppingCartContract.Presenter) mPresenter).getCartIdList(mAdapter.getData());
                if (StringUtils.isEmpty(list) || list.length() <= 0) {
                    ViewInject.toast(getString(R.string.notChosenAnything));
                    return;
                }
                Intent intent = new Intent(aty, MakeSureOrderActivity.class);
                intent.putExtra("goodslistBean", list);
                intent.putExtra("totalPrice", tv_money.getText().toString().trim());
                startActivity(intent);
                break;
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                showActivity(this, LoginActivity.class);
                break;
        }
    }

    @Override
    public void setPresenter(MyShoppingCartContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        mRefreshLayout.setPullDownRefreshEnable(false);
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setVisibility(View.VISIBLE);
        tv_button.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            img_err.setImageResource(R.mipmap.no_login);
            tv_hintText.setVisibility(View.GONE);
            tv_button.setText(getString(R.string.login));
            //   ViewInject.toast(getString(R.string.reloginPrompting));
            showActivity(this, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.notAnythingAdd))) {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setVisibility(View.GONE);
            ll_bottom.setVisibility(View.GONE);
        } else {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("goodName", mAdapter.getItem(position).getName());
        intent.putExtra("goodsid", mAdapter.getItem(position).getGoods_id());
        showActivity(aty, intent);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        checkPosition = position;
        if (childView.getId() == R.id.img_checkbox) {
            ImageView img_checkbox = (ImageView) childView.findViewById(R.id.img_checkbox);
            if (mAdapter.getItem(position).getIsSelected() == 0) {
                img_checkbox.setImageResource(R.mipmap.shopping_cart_selected);
                mAdapter.getItem(position).setIsSelected(1);
                tv_money.setText(calculatePrice(mAdapter.getData()));
                if (isAllSelected(mAdapter.getData())) {
                    img_unselected.setImageResource(R.mipmap.shopping_cart_selected);
                    return;
                }
                img_unselected.setImageResource(R.mipmap.shopping_cart_unselected);
                return;
            }
            img_checkbox.setImageResource(R.mipmap.shopping_cart_unselected);
            mAdapter.getItem(position).setIsSelected(0);
            tv_money.setText(calculatePrice(mAdapter.getData()));
            img_unselected.setImageResource(R.mipmap.shopping_cart_unselected);
        } else if (childView.getId() == R.id.tv_delete) {
            ((MyShoppingCartContract.Presenter) mPresenter).postDeleteGood(mAdapter.getItem(position));
        } else if (childView.getId() == R.id.tv_subtract) {
            if (mAdapter.getItem(position).getNum() == 1) {
                ViewInject.toast(getString(R.string.babyNotReduced));
                return;
            }
            showLoadingDialog(getString(R.string.dataLoad));
            ((MyShoppingCartContract.Presenter) mPresenter).postCartUpdate(mAdapter.getItem(position).getId(), mAdapter.getItem(position).getNum() - 1, mAdapter.getItem(position).getProduct_id(), 2);
        } else if (childView.getId() == R.id.tv_add) {
            showLoadingDialog(getString(R.string.dataLoad));
            ((MyShoppingCartContract.Presenter) mPresenter).postCartUpdate(mAdapter.getItem(position).getId(), mAdapter.getItem(position).getNum() + 1, mAdapter.getItem(position).getProduct_id(), 3);
        }
    }

    @Override
    public void getSuccess(String success, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(true);
        if (flag == 0) {
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            MyShoppingCartBean myShoppingCartBean = (MyShoppingCartBean) JsonUtil.getInstance().json2Obj(success, MyShoppingCartBean.class);
            mAdapter.closeOpenedSwipeItemLayoutWithAnim();
            if (myShoppingCartBean.getData() == null || myShoppingCartBean.getData().getStorelist() == null || myShoppingCartBean.getData().getStorelist().size() <= 0) {
                errorMsg(getString(R.string.notAnythingAdd), 0);
                editStatus();
                return;
            }
            List<GoodslistBean> list = new ArrayList<GoodslistBean>();
            for (int i = 0; i < myShoppingCartBean.getData().getStorelist().size(); i++) {
                if (myShoppingCartBean.getData().getStorelist().get(i) != null && myShoppingCartBean.getData().getStorelist().get(i).getGoodslist() != null
                        && myShoppingCartBean.getData().getStorelist().get(i).getGoodslist().size() > 0) {
                    list.addAll(myShoppingCartBean.getData().getStorelist().get(i).getGoodslist());
                }
            }
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setIsSelected(0);
                list.get(i).setIsEdit(isEdit);
            }
            mAdapter.clear();
            mAdapter.addNewData(list);
            tv_money.setText("0.00");
            img_unselected.setImageResource(R.mipmap.shopping_cart_unselected);
            mRefreshLayout.endRefreshing();
            dismissLoadingDialog();
        } else if (flag == 1) {
            for (int i = 0; i < mAdapter.getData().size(); i++) {
                if (mAdapter.getData().get(i).getIsSelected() == 1) {
                    mAdapter.removeItem(i);
                    i--;
                }
            }
            if (mAdapter.getData() == null || mAdapter.getData().size() == 0) {
                isEdit = 0;
                titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.textColor));
                titlebar.setRightText(R.string.edit);
                errorMsg(getString(R.string.notAnythingAdd), 1);
            }
            tv_money.setText(calculatePrice(mAdapter.getData()));
            dismissLoadingDialog();
        } else if (flag == 2) {
            int num = mAdapter.getItem(checkPosition).getNum() - 1;
            mAdapter.getItem(checkPosition).setNum(num);
            mAdapter.notifyDataSetChanged();
            tv_money.setText(calculatePrice(mAdapter.getData()));
            dismissLoadingDialog();
        } else if (flag == 3) {
            int num = mAdapter.getItem(checkPosition).getNum() + 1;
            mAdapter.getItem(checkPosition).setNum(num);
            mAdapter.notifyDataSetChanged();
            tv_money.setText(calculatePrice(mAdapter.getData()));
            dismissLoadingDialog();
        } else if (flag == 4) {
            mAdapter.closeOpenedSwipeItemLayoutWithAnim();
            for (int i = 0; i < mAdapter.getData().size(); i++) {
                if (i == checkPosition) {
                    mAdapter.removeItem(i);
                }
            }
            tv_money.setText(calculatePrice(mAdapter.getData()));
            dismissLoadingDialog();
        }
    }

    /**
     * 计算价格
     */
    private String calculatePrice(List<GoodslistBean> list) {
        double totalPrice = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsSelected() == 1) {
                int num = list.get(i).getNum();
                double price = StringUtils.toDouble(list.get(i).getPrice());
                totalPrice = totalPrice + price * num;
            }
        }
        return MathUtil.keepTwo(totalPrice);
    }

    /**
     * 设置编辑状态
     */
    private void setEdit(List<GoodslistBean> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIsEdit(isEdit);
        }
        mAdapter.notifyDataSetChanged();
    }


    /**
     * 是否是全选
     */
    private boolean isAllSelected(List<GoodslistBean> list) {
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsSelected() == 1) {
                num++;
            }
        }
        if (num == list.size()) {
            return true;
        }
        return false;
    }


    /**
     * 设置全选状态
     */
    private void setAllSelected(List<GoodslistBean> list, int isSelected) {
        double totalPrice = 0;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIsSelected(isSelected);
            if (list.get(i).getIsSelected() == 1) {
                int num = list.get(i).getNum();
                double price = StringUtils.toDouble(list.get(i).getPrice());
                totalPrice = totalPrice + price * num;
            }
        }
        mAdapter.notifyDataSetChanged();
        tv_money.setText(MathUtil.keepTwo(totalPrice));
    }


    /**
     * 获取所有的选中
     */
    private ArrayList<GoodslistBean> getAllSelected(List<GoodslistBean> list) {
        ArrayList<GoodslistBean> list1 = new ArrayList<GoodslistBean>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsSelected() == 1) {
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyShoppingCartContract.Presenter) mPresenter).getMyShoppingCartList();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL == scrollState) {
            mAdapter.closeOpenedSwipeItemLayoutWithAnim();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusMyShoppingCartEvent") && mPresenter != null) {
            ((MyShoppingCartContract.Presenter) mPresenter).getMyShoppingCartList();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
