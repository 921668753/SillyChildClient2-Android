package com.sillykid.app.homepage.privatecustom.cityselect.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.IndexNewBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.privatecustom.cityselect.fragment.RecommendedViewAdapter;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.fragment.RecommendedBean;
import com.sillykid.app.entity.homepage.privatecustom.cityselect.fragment.RecommendedBean.DataBean;
import com.sillykid.app.homepage.privatecustom.cityselect.CitySelectActivity;
import com.sillykid.app.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * 热门目的地
 */
public class RecommendedFragment extends BaseFragment implements CityClassificationContract.View {

    private CitySelectActivity aty;


    @BindView(id = R.id.rv)
    private RecyclerView mRv;

    @BindView(id = R.id.indexBar)
    private IndexNewBar mIndexBar;

    private RecommendedViewAdapter mAdapter;

    private GridLayoutManager mManager;

    //private SuspensionDecoration mDecoration;

    private List<DataBean> mDatas;


    private int classification_id = 0;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (CitySelectActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_recommended, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<DataBean>();
        mPresenter = new CityClassificationPresenter(this);
        mAdapter = new RecommendedViewAdapter(aty, mDatas);
        //      mDecoration = new SuspensionDecoration(aty, mDatas);
        mManager = new GridLayoutManager(aty, 2);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        mRv.setLayoutManager(mManager);
        mRv.setAdapter(mAdapter);
        mAdapter.setViewCallBack(new RecommendedViewAdapter.ViewCallBack() {
            @Override
            public void onClickListener(DataBean dataBean) {
                Intent intent = new Intent();
                // 获取内容
                intent.putExtra("country_id", dataBean.getCountry_id());
                intent.putExtra("country_name", dataBean.getCountry_name());
                intent.putExtra("city_id", dataBean.getCity_id());
                intent.putExtra("city_name", dataBean.getCity_name());
                // 设置结果 结果码，一个数据
                aty.setResult(RESULT_OK, intent);
                // 结束该activity 结束之后，前面的activity才可以处理结果
                aty.finish();
            }
        });
        //   mRv.addItemDecoration(mDecoration);
        //如果add两个，那么按照先后顺序，依次渲染。
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(8, 10);
        mRv.addItemDecoration(spacesItemDecoration);
        mIndexBar.setmPressedShowTextView(null)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager
        showLoadingDialog(getString(R.string.dataLoad));
        ((CityClassificationContract.Presenter) mPresenter).getCountryAreaListByParentid(aty, classification_id, 0);
    }


    public void setClassificationId(int classification_id1) {
        classification_id = classification_id1;
//        if (mRefreshLayout != null) {
//            mRefreshLayout.beginRefreshing();
//        }
    }


    @Override
    public void setPresenter(CityClassificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        RecommendedBean selectCountryBean = (RecommendedBean) JsonUtil.getInstance().json2Obj(success, RecommendedBean.class);
        if (selectCountryBean != null && selectCountryBean.getData() != null && selectCountryBean.getData().size() > 0) {
            //模拟线上加载数据
            mDatas.clear();
            mDatas.addAll(selectCountryBean.getData());
            initDatas(mDatas);
        }
        dismissLoadingDialog();
    }

    /**
     * 组织数据源
     *
     * @return
     */
    private void initDatas(List<DataBean> list) {
        mAdapter.setDatas(list);
        mAdapter.notifyDataSetChanged();
        mIndexBar.setmSourceDatas(list)//设置数据
                .invalidate();
        //  mDecoration.setmDatas(list);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter = null;
        mDatas.clear();
        mDatas = null;
    }

}
