package com.sillykid.app.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.common.cklibrary.utils.myview.ScrollInterceptScrollView;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.HomePageClassificationViewAdapter;
import com.sillykid.app.adapter.main.homepage.MallHomePageViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.main.MallHomePageBean;
import com.sillykid.app.entity.main.MallHomePageBean.DataBean.AdvcatBean;
import com.sillykid.app.entity.main.MallHomePageBean.DataBean.ApiCatTreeBean;
import com.sillykid.app.entity.main.MallHomePageBean.DataBean.HomePageBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.mall.goodslist.GoodsListActivity;
import com.sillykid.app.mall.moreclassification.MoreClassificationActivity;
import com.sillykid.app.mall.search.SearchGoodsActivity;
import com.sillykid.app.utils.SpacesItemDecoration;
import com.sillykid.app.mall.goodslist.goodsdetails.GoodsDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 商城首页
 * Created by Admin on 2017/8/10.
 */
@SuppressLint("NewApi")
public class MallFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, View.OnScrollChangeListener, AdapterView.OnItemClickListener, MallContract.View, BGABanner.Delegate<ImageView, AdvcatBean>, BGABanner.Adapter<ImageView, AdvcatBean>, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.ll_title, click = true)
    private LinearLayout ll_title;

    @BindView(id = R.id.img_search)
    private ImageView img_search;

//    @BindView(id = R.id.et_search)
//    private EditText et_search;

    @BindView(id = R.id.ll_title1, click = true)
    private LinearLayout ll_title1;

    @BindView(id = R.id.img_search1)
    private ImageView img_search1;

//    @BindView(id = R.id.et_search1)
//    private EditText et_search1;


    @BindView(id = R.id.sv_home)
    private ScrollInterceptScrollView sv_home;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    /**
     * 分类
     */
    @BindView(id = R.id.gv_classification)
    private NoScrollGridView gv_classification;

    /**
     * 商品列表
     */
    @BindView(id = R.id.rv)
    private RecyclerView recyclerview;

    public LocationClient mLocationClient = null;

    public BDAbstractLocationListener myListener = null;
//BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明

    private boolean isFirst = true;

    private SpacesItemDecoration spacesItemDecoration = null;

    private MallHomePageViewAdapter mallHomePageViewAdapter = null;

    private HomePageClassificationViewAdapter homePageClassificationViewAdapter = null;

    private Thread thread = null;

    private List<HomePageBean> list = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mall, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MallPresenter(this);
        homePageClassificationViewAdapter = new HomePageClassificationViewAdapter(aty);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        mLocationClient = new LocationClient(aty.getApplicationContext());
        myListener = new MyLocationListener();
        spacesItemDecoration = new SpacesItemDecoration(7, 14);
        mallHomePageViewAdapter = new MallHomePageViewAdapter(recyclerview);
        list = new ArrayList<HomePageBean>();
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        initBanner();
        sv_home.setOnScrollChangeListener(this);
        gv_classification.setAdapter(homePageClassificationViewAdapter);
        gv_classification.setOnItemClickListener(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        //设置item之间的间隔
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(mallHomePageViewAdapter);
        mallHomePageViewAdapter.setOnRVItemClickListener(this);
        //  mallHomePageViewAdapter.addMoreData(addList());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        ((MallContract.Presenter) mPresenter).initLocation(aty, mLocationClient);
        showLoadingDialog(aty.getString(R.string.dataLoad));
        ((MallContract.Presenter) mPresenter).getHomePage();
    }

    /**
     * @param v 控件监听事件
     */
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_title:
            case R.id.ll_title1:
                aty.showActivity(aty, SearchGoodsActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(MallContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            MallHomePageBean mallHomePageBean = (MallHomePageBean) JsonUtil.json2Obj(success, MallHomePageBean.class);
            List<AdvcatBean> advCatBeanList = mallHomePageBean.getData().getAdvcat();
            if (advCatBeanList != null && advCatBeanList.size() > 0) {
                processLogic(advCatBeanList);
            }
            List<ApiCatTreeBean> apiCatTreeBeanList = mallHomePageBean.getData().getApiCatTree();
            if (apiCatTreeBeanList != null && apiCatTreeBeanList.size() > 0) {
                gv_classification.setVisibility(View.VISIBLE);
                homePageClassificationViewAdapter.clear();
                homePageClassificationViewAdapter.addMoreData(apiCatTreeBeanList);
            } else {
                gv_classification.setVisibility(View.GONE);
            }
            if (mallHomePageBean.getData().getHomePage() == null || mallHomePageBean.getData().getHomePage().size() <= 0) {
                dismissLoadingDialog();
                return;
            }
            if (thread != null && !thread.isAlive()) {
                thread.run();
                return;
            }
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    list.clear();
                    for (int i = 0; i < mallHomePageBean.getData().getHomePage().size(); i++) {
                        Bitmap bitmap = GlideImageLoader.load(aty, mallHomePageBean.getData().getHomePage().get(i).getThumbnail());
                        if (bitmap != null) {
                            mallHomePageBean.getData().getHomePage().get(i).setHeight(bitmap.getHeight());
                            mallHomePageBean.getData().getHomePage().get(i).setWidth(bitmap.getWidth());
                        }
                        list.add(mallHomePageBean.getData().getHomePage().get(i));
                    }
                    aty.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mallHomePageViewAdapter.clear();
                            mallHomePageViewAdapter.addNewData(list);
                            dismissLoadingDialog();
                        }
                    });
                }
            });
            thread.start();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 1 && isLogin(msg)) {
            Intent intent = new Intent(aty, LoginActivity.class);
            // intent.putExtra("name", "GetOrderFragment");
            aty.showActivity(aty, intent);
            return;
        }
        ViewInject.toast(msg);
    }

    /**
     * 初始化轮播图
     */
    public void initBanner() {
        mForegroundBanner.setFocusable(true);
        mForegroundBanner.setFocusableInTouchMode(true);
        mForegroundBanner.requestFocus();
        mForegroundBanner.requestFocusFromTouch();
        mForegroundBanner.setAutoPlayAble(true);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setAllowUserScrollable(true);
        mForegroundBanner.setAutoPlayInterval(3000);
        // 初始化方式1：配置数据源的方式1：通过传入数据模型并结合 Adapter 的方式配置数据源。这种方式主要用于加载网络图片，以及实现少于3页时的无限轮播
        mForegroundBanner.setAdapter(this);
        mForegroundBanner.setDelegate(this);
    }

    /**
     * 广告轮播图
     */
    @SuppressWarnings("unchecked")
    private void processLogic(List<AdvcatBean> list) {
        if (list != null && list.size() > 0) {
            if (list.size() == 1) {
                mForegroundBanner.setAutoPlayAble(false);
                mForegroundBanner.setAllowUserScrollable(false);
            } else {
                mForegroundBanner.setAutoPlayAble(true);
                mForegroundBanner.setAllowUserScrollable(true);
            }
            mForegroundBanner.setBackground(null);
            mForegroundBanner.setData(list, null);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.startAutoPlay();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.stopAutoPlay();
        }
    }


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, AdvcatBean model, int position) {
        //   GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView);
        GlideImageLoader.glideOrdinaryLoader(aty, model.getHttpAttUrl(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, AdvcatBean model, int position) {
        if (StringUtils.isEmpty(model.getUrl())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getUrl());
        bannerDetails.putExtra("title", model.getAname());
        aty.showActivity(aty, bannerDetails);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.LOCATION_CODE) {
            ViewInject.toast(aty.getString(R.string.locationRelatedPermission));
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MallContract.Presenter) mPresenter).getHomePage();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY <= 0) {
            ll_title1.setVisibility(View.VISIBLE);
            ll_title.setVisibility(View.GONE);
//            ll_title.setBackgroundColor(Color.TRANSPARENT);
//            img_search.setImageDrawable(null);
//            et_search.setHintTextColor(Color.TRANSPARENT);
        } else {
            ll_title1.setVisibility(View.GONE);
            ll_title.setVisibility(View.VISIBLE);
//            ll_title.setBackgroundColor(Color.TRANSPARENT);
//            img_search.setImageDrawable(null);
//            et_search.setHintTextColor(getResources().getColor(R.color.hintColors));
        }
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("goodName", mallHomePageViewAdapter.getItem(position).getName());
        intent.putExtra("goodsid", mallHomePageViewAdapter.getItem(position).getGoods_id());
        aty.showActivity(aty, intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (homePageClassificationViewAdapter.getItem(i).getCat_id() == -1) {
            Intent moreClassificationIntent = new Intent(aty, MoreClassificationActivity.class);
            aty.showActivity(aty, moreClassificationIntent);
            return;
        }
        Intent beautyCareIntent = new Intent(aty, GoodsListActivity.class);
        beautyCareIntent.putExtra("cat", homePageClassificationViewAdapter.getItem(i).getCat_id());
        aty.showActivity(aty, beautyCareIntent);
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                //获取定位结果
                location.getTime();    //获取定位时间
                location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
                location.getLocType();    //获取定位类型
                location.getLatitude();    //获取纬度信息
                location.getLongitude();    //获取经度信息
                location.getRadius();    //获取定位精准度
                location.getAddrStr();    //获取地址信息
                location.getCountry();    //获取国家信息
                location.getCountryCode();    //获取国家码
                location.getCity();    //获取城市信息
                location.getCityCode();    //获取城市码
                location.getDistrict();    //获取区县信息
                location.getStreet();    //获取街道信息
                location.getStreetNumber();    //获取街道码
                location.getLocationDescribe();    //获取当前位置描述信息
                location.getPoiList();    //获取当前位置周边POI信息
                location.getBuildingID();    //室内精准定位下，获取楼宇ID
                location.getBuildingName();    //室内精准定位下，获取楼宇名称
                location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息
                PreferenceHelper.write(aty, StringConstants.FILENAME, "locationCountry", location.getCountry());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "locationCity", location.getCity());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "location", location.getLongitude() + "," + location.getLatitude());
                PreferenceHelper.write(aty, StringConstants.FILENAME, "latitude", location.getLatitude() + "");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "longitude", location.getLongitude() + "");
                PreferenceHelper.write(aty, StringConstants.FILENAME, "locationAddress", location.getAddrStr());
            //    ((MallHomePageContract.Presenter) mPresenter).postBaiDuUpdateInfo();
                Log.d("tag111", location.getCity());
                if (isFirst) {
                    if (StringUtils.isEmpty(location.getCity())) {
                        PreferenceHelper.write(aty, StringConstants.FILENAME, "locationCity", getString(R.string.allAeservationNumber));
//                        tv_address.setText(getString(R.string.allAeservationNumber));
                        //   PreferenceHelper.write(aty, StringConstants.FILENAME, "location", "");
                    }
                }
                isFirst = false;
                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    //当前为GPS定位结果，可获取以下信息
                    location.getSpeed();    //获取当前速度，单位：公里每小时
                    location.getSatelliteNumber();    //获取当前卫星数
                    location.getAltitude();    //获取海拔高度信息，单位米
                    location.getDirection();    //获取方向信息，单位度
                    //  location.
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                    //当前为网络定位结果，可获取以下信息
                    location.getOperators();    //获取运营商信息

                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                    //当前为网络定位结果

                } else if (location.getLocType() == BDLocation.TypeServerError) {

                    //当前网络定位失败
                    //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com
                    //   ((HomePageContract.Presenter) mPresenter).getHomePage("");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                    //当前网络不通
                    //    ((HomePageContract.Presenter) mPresenter).getHomePage("");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                    //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
                    //可进一步参考onLocDiagnosticMessage中的错误返回码
                    //    ((HomePageContract.Presenter) mPresenter).getHomePage("");
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mallHomePageViewAdapter.clear();
        mallHomePageViewAdapter = null;
        list.clear();
        list = null;
        if (thread != null) {
            thread.interrupted();
        }
        thread = null;
        mLocationClient.unRegisterLocationListener(myListener); //注销掉监听
        mLocationClient.stop(); //停止定位服务
    }
}