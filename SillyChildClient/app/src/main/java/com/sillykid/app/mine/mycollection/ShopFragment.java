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
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mine.mycollection.ShopViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.mycollection.ShopBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mall.goodslist.shop.ShopActivity;
import com.sillykid.app.mine.mycollection.dialog.DeleteCollectionDialog;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.sillykid.app.constant.NumericConstants.REQUEST_CODE_PREVIEW;

/**
 * 我的收藏中的店铺
 * Created by Administrator on 2017/9/2.
 */
public class ShopFragment extends BaseFragment implements CollectionContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private ShopViewAdapter mAdapter;

    @BindView(id = R.id.lv_shopCollection)
    private ListView lv_shopCollection;

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

    private int type_id = 2;

    private int positionItem = 0;

    private MyCollectionActivity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyCollectionActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_shop, null);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CollectionPresenter(this);
        mAdapter = new ShopViewAdapter(aty);
        initDeleteCollectionDialog();
    }


    /**
     * 弹框
     */
    public void initDeleteCollectionDialog() {
        deleteCollectionDialog = new DeleteCollectionDialog(aty, getString(R.string.determineDeleteShopCollection)) {
            @Override
            public void deleteCollectionDo(int addressId) {
                showLoadingDialog(getString(R.string.deleteLoad));
                ((CollectionContract.Presenter) mPresenter).postUnFavorite(addressId, type_id);
            }
        };
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_shopCollection.setAdapter(mAdapter);
        lv_shopCollection.setOnItemClickListener(this);
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
        ((CollectionContract.Presenter) mPresenter).getFavoriteList(mMorePageNumber, type_id);
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
        ((CollectionContract.Presenter) mPresenter).getFavoriteList(mMorePageNumber, type_id);
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//        Intent intent = new Intent(aty, ShopActivity.class);
//        intent.putExtra("goodName", mAdapter.getItem(position).getName());
//        intent.putExtra("goodsid", mAdapter.getItem(position).getGoods_id());
//        intent.putExtra("isRefresh", 1);
//        startActivityForResult(intent, REQUEST_CODE_PREVIEW);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        positionItem = position;
        if (childView.getId() == R.id.tv_cancelCollection) {
            if (deleteCollectionDialog == null) {
                initDeleteCollectionDialog();
            }
            if (deleteCollectionDialog != null && !deleteCollectionDialog.isShowing()) {
                deleteCollectionDialog.show();
                deleteCollectionDialog.setCollectionId(mAdapter.getItem(position).getStore_id());
            }
        } else if (childView.getId() == R.id.tv_enterStore) {
            Intent intent = new Intent(aty, ShopActivity.class);
            intent.putExtra("storeid", mAdapter.getItem(position).getStore_id());
            intent.putExtra("isRefresh", 0);
            startActivityForResult(intent, REQUEST_CODE_PREVIEW);
        }
    }


    @Override
    public void setPresenter(CollectionContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            ShopBean shopBean = (ShopBean) JsonUtil.getInstance().json2Obj(success, ShopBean.class);
            if (shopBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    shopBean.getData().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noCollectedShop), 0);
                return;
            } else if (shopBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    shopBean.getData().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(shopBean.getData());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(shopBean.getData());
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            mAdapter.removeItem(positionItem);
            mRefreshLayout.beginRefreshing();
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
            } else if (msg.contains(getString(R.string.noCollectedShop))) {
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
            ((CollectionContract.Presenter) mPresenter).getFavoriteList(mMorePageNumber, type_id);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_CODE_PREVIEW && resultCode == RESULT_OK) {
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
        mAdapter.clear();
        mAdapter = null;
    }

}
