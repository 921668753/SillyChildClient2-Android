package com.sillykid.app.mine.mycollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mine.mycollection.GoodViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.mycollection.MyCollectionBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mall.goodslist.goodsdetails.GoodsDetailsActivity;
import com.sillykid.app.mall.goodslist.goodsdetails.dialog.SpecificationsBouncedDialog;
import com.sillykid.app.mine.mycollection.dialog.DeleteCollectionDialog;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE;

/**
 * 我的收藏中的动态
 * Created by Administrator on 2017/9/2.
 */

public class DynamicStateFragment extends BaseFragment implements MyCollectionContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private GoodViewAdapter mAdapter;

    @BindView(id = R.id.lv_myCollection)
    private ListView lv_myCollection;

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

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;

    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;


    private DeleteCollectionDialog deleteCollectionDialog = null;

    private SpecificationsBouncedDialog addCartGoodDialog = null;

    private int positionItem = 0;

    private MyCollectionActivity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyCollectionActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_good, null);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyCollectionPresenter(this);
        mAdapter = new GoodViewAdapter(aty);
        initDeleteCollectionDialog();
        initAddCartGoodDialog();
    }


    /**
     * 弹框
     */
    public void initDeleteCollectionDialog() {
        deleteCollectionDialog = new DeleteCollectionDialog(aty, getString(R.string.determineDeleteCollection)) {
            @Override
            public void deleteCollectionDo(int addressId) {
                showLoadingDialog(getString(R.string.deleteLoad));
                ((MyCollectionContract.Presenter) mPresenter).postUnFavoriteGood(addressId);
            }
        };
    }

    public void initAddCartGoodDialog() {
        addCartGoodDialog = new SpecificationsBouncedDialog(aty) {
            @Override
            public void toDo(int goodId, int flag, int num1, int product_id) {
                showLoadingDialog(getString(R.string.addLoad));
                ((MyCollectionContract.Presenter) mPresenter).postAddCartGood(goodId, num1, product_id);
            }
        };
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myCollection), true, R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_myCollection.setAdapter(mAdapter);
        lv_myCollection.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                aty.showActivity(aty, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyCollectionContract.Presenter) mPresenter).getFavoriteGoodList(mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        mMorePageNumber++;
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyCollectionContract.Presenter) mPresenter).getFavoriteGoodList(mMorePageNumber);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("goodName", mAdapter.getItem(position).getName());
        intent.putExtra("goodsid", mAdapter.getItem(position).getGoods_id());
        intent.putExtra("isRefresh", 1);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        positionItem = position;
        if (childView.getId() == R.id.img_delete) {
            if (deleteCollectionDialog == null) {
                initDeleteCollectionDialog();
            }
            if (deleteCollectionDialog != null && !deleteCollectionDialog.isShowing()) {
                deleteCollectionDialog.show();
                deleteCollectionDialog.setCollectionId(mAdapter.getItem(position).getGoods_id());
            }
        } else if (childView.getId() == R.id.img_shoppingCart) {
            int store = StringUtils.toInt(mAdapter.getItem(position).getStore(), 0);
            if (store <= 0) {
                ViewInject.toast(getString(R.string.inventory) + getString(R.string.insufficient));
                return;
            }
            if (addCartGoodDialog == null) {
                initAddCartGoodDialog();
            }
            if (addCartGoodDialog != null && !addCartGoodDialog.isShowing()) {
                addCartGoodDialog.show();
                addCartGoodDialog.setFlag(0, mAdapter.getItem(position).getGoods_id(), mAdapter.getItem(position).getSmall(),
                        MathUtil.keepTwo(StringUtils.toDouble(mAdapter.getItem(position).getPrice())), mAdapter.getItem(position).getHave_spec(),
                        mAdapter.getItem(position).getProduct_id(), store);
            }
        }
    }


    @Override
    public void setPresenter(MyCollectionContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            MyCollectionBean myCollectionBean = (MyCollectionBean) JsonUtil.getInstance().json2Obj(success, MyCollectionBean.class);
            if (myCollectionBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    myCollectionBean.getData().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noCollectedGoods), 0);
                return;
            } else if (myCollectionBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    myCollectionBean.getData().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(myCollectionBean.getData());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(myCollectionBean.getData());
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            mAdapter.removeItem(positionItem);
            mRefreshLayout.beginRefreshing();
        } else if (flag == 2) {
            addCartGoodDialog.dismissLoadingDialog();
            ViewInject.toast(getString(R.string.addCartSuccess));
            dismissLoadingDialog();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            isShowLoadingMore = false;
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
            mRefreshLayout.setPullDownRefreshEnable(false);
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setVisibility(View.VISIBLE);
            tv_button.setVisibility(View.VISIBLE);
            if (isLogin(msg)) {
                img_err.setImageResource(R.mipmap.no_login);
                tv_hintText.setVisibility(View.GONE);
                tv_button.setText(getString(R.string.login));
                // ViewInject.toast(getString(R.string.reloginPrompting));
                aty.showActivity(aty, LoginActivity.class);
                return;
            } else if (msg.contains(getString(R.string.checkNetwork))) {
                img_err.setImageResource(R.mipmap.no_network);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            } else if (msg.contains(getString(R.string.noCollectedGoods))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
            } else {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            }
        } else {
            mRefreshLayout.setPullDownRefreshEnable(true);
            mRefreshLayout.setVisibility(View.VISIBLE);
            ll_commonError.setVisibility(View.GONE);
            ViewInject.toast(msg);
            if (flag == 2) {
                addCartGoodDialog.dismissLoadingDialog();
            }
        }
    }


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((MyCollectionContract.Presenter) mPresenter).getFavoriteGoodList(mMorePageNumber);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (deleteCollectionDialog != null) {
            deleteCollectionDialog.cancel();
        }
        deleteCollectionDialog = null;

        if (addCartGoodDialog != null) {
            addCartGoodDialog.cancel();
        }
        addCartGoodDialog = null;
        mAdapter.clear();
        mAdapter = null;
    }

}
