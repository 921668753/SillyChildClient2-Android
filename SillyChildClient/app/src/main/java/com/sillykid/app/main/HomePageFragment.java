package com.sillykid.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.HorizontalListView;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.main.homepage.BoutiqueLineViewAdapter;
import com.sillykid.app.adapter.main.homepage.HotVideoViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.main.HomePageBean;
import com.sillykid.app.entity.main.HomePageBean.DataBean.BannerListBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.homepage.hotvideo.HotVideoActivity;
import com.sillykid.app.homepage.hotvideo.VideoDetailsActivity;
import com.sillykid.app.homepage.privatecustom.PrivateCustomActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mall.goodslist.GoodsListActivity;
import com.sillykid.app.mall.goodslist.goodsdetails.GoodsDetailsActivity;
import com.sillykid.app.utils.GlideImageLoader;
import com.sillykid.app.utils.SpacesItemDecoration;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 首页
 * Created by Admin on 2018/8/10.
 */
public class HomePageFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, HomePageContract.View, BGABanner.Delegate<ImageView, BannerListBean>, BGABanner.Adapter<ImageView, BannerListBean>, AdapterView.OnItemClickListener, BGAOnRVItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    /**
     * 客服
     */
    @BindView(id = R.id.img_customerService, click = true)
    private ImageView img_customerService;

    /**
     * 消息
     */
    @BindView(id = R.id.rl_message, click = true)
    private RelativeLayout rl_message;

    @BindView(id = R.id.tv_tag)
    private ImageView tv_tag;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    /**
     * 接机
     */
    @BindView(id = R.id.ll_airportPickup, click = true)
    private LinearLayout ll_airportPickup;

    /**
     * 按天包车
     */
    @BindView(id = R.id.ll_byTheDay, click = true)
    private LinearLayout ll_byTheDay;

    /**
     * 私人定制
     */
    @BindView(id = R.id.ll_privateOrdering, click = true)
    private LinearLayout ll_privateOrdering;

    /**
     * 精品线路
     */
    @BindView(id = R.id.ll_boutiqueLine, click = true)
    private LinearLayout ll_boutiqueLine;

    /**
     * 送机
     */
    @BindView(id = R.id.ll_airportDropOff, click = true)
    private LinearLayout ll_airportDropOff;

    /**
     * 热门视频  更多视频
     */
    @BindView(id = R.id.ll_hotVideo, click = true)
    private LinearLayout ll_hotVideo;

    @BindView(id = R.id.hlv_hotVideo)
    private HorizontalListView hlv_hotVideo;

    private HotVideoViewAdapter hotVideoViewAdapter;

    /**
     * 精品线路  更多线路
     */
    @BindView(id = R.id.ll_boutiqueLine1, click = true)
    private LinearLayout ll_boutiqueLine1;

    @BindView(id = R.id.rv)
    private RecyclerView recyclerView;

    private BoutiqueLineViewAdapter boutiqueLineViewAdapter;

    public LocationClient mLocationClient = null;

    public BDAbstractLocationListener myListener = null;
//BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明

    private boolean isFirst = true;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_homepage, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new HomePagePresenter(this);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        hotVideoViewAdapter = new HotVideoViewAdapter(aty, hlv_hotVideo);
        boutiqueLineViewAdapter = new BoutiqueLineViewAdapter(recyclerView);
        mLocationClient = new LocationClient(aty.getApplicationContext());
        myListener = new MyLocationListener();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        initBanner();
        hlv_hotVideo.setAdapter(hotVideoViewAdapter);
        hlv_hotVideo.setOnItemClickListener(this);
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        ((HomePagePresenter) mPresenter).initLocation(aty, mLocationClient);
        LinearLayoutManager layoutManager = new LinearLayoutManager(aty);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置item之间的间隔
        recyclerView.addItemDecoration(BGADivider.newShapeDivider()
                .setStartSkipCount(1)
                .setSizeDp(1)
                .setColorResource(R.color.background, false));
        recyclerView.setAdapter(boutiqueLineViewAdapter);
        boutiqueLineViewAdapter.setOnRVItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    /**
     * @param v 控件监听事件
     */
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_customerService:
                // ((HomePagePresenter) mPresenter).isLogin(1);


                break;
            case R.id.ll_airportPickup:
                Intent intent = new Intent(aty, GoodsListActivity.class);
                intent.putExtra("cat", 480);
                aty.showActivity(aty, intent);
                break;
            case R.id.ll_byTheDay:
                Intent intent1 = new Intent(aty, GoodsListActivity.class);
                intent1.putExtra("cat", 483);
                aty.showActivity(aty, intent1);
                break;
            case R.id.ll_privateOrdering:
                Intent intent2 = new Intent(aty, PrivateCustomActivity.class);
                // intent1.putExtra("newChageIcon", 2);
                aty.showActivity(aty, intent2);
                break;
            case R.id.ll_airportDropOff:
                Intent intent3 = new Intent(aty, GoodsListActivity.class);
                intent3.putExtra("cat", 482);
                aty.showActivity(aty, intent3);
                break;
            case R.id.ll_hotVideo:
                aty.showActivity(aty, HotVideoActivity.class);
                break;
            case R.id.ll_boutiqueLine:
            case R.id.ll_boutiqueLine1:
                Intent intent4 = new Intent(aty, GoodsListActivity.class);
                intent4.putExtra("cat", 484);
                aty.showActivity(aty, intent4);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            HomePageBean homePageBean = (HomePageBean) JsonUtil.getInstance().json2Obj(success, HomePageBean.class);
            processLogic(homePageBean.getData().getBanner_list());
            if (homePageBean.getData().getVideo_list() != null && homePageBean.getData().getVideo_list().size() > 0) {
                ll_hotVideo.setVisibility(View.VISIBLE);
                hlv_hotVideo.setVisibility(View.VISIBLE);
                hotVideoViewAdapter.clear();
                homePageBean.getData().getVideo_list().get(homePageBean.getData().getVideo_list().size() - 1).setStatusL("last");
                hotVideoViewAdapter.addNewData(homePageBean.getData().getVideo_list());
            } else {
                ll_hotVideo.setVisibility(View.GONE);
                hlv_hotVideo.setVisibility(View.GONE);
            }
            if (homePageBean.getData().getGoods_list() == null || homePageBean.getData().getGoods_list().size() <= 0 || homePageBean.getData().getGoods_list().isEmpty()) {
                ll_boutiqueLine1.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            } else {
                ll_boutiqueLine1.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                boutiqueLineViewAdapter.clear();
                boutiqueLineViewAdapter.addNewData(homePageBean.getData().getGoods_list());
            }
        } else if (flag == 1) {
            dismissLoadingDialog();
            tv_tag.setVisibility(View.GONE);
            // aty.showActivity(aty, OverleafActivity.class);
        }
        dismissLoadingDialog();

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 1) {
            if (isLogin(msg)) {
                aty.showActivity(aty, LoginActivity.class);
                return;
            }
        }
        ViewInject.toast(msg);
    }

    /**
     * 初始化轮播图
     */
    public void initBanner() {
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
    private void processLogic(List<BannerListBean> list) {
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
    public void onChange() {
        super.onChange();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.stopAutoPlay();
        }
    }


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, BannerListBean model, int position) {
        GlideImageLoader.glideOrdinaryLoader(aty, model.getImage_url(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, BannerListBean model, int position) {
        if (StringUtils.isEmpty(model.getImage_link_url())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getImage_link_url());
        //bannerDetails.putExtra("title", model.get());
        aty.showActivity(aty, bannerDetails);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.hlv_hotVideo) {
            Intent intent = new Intent(aty, VideoDetailsActivity.class);
            intent.putExtra("id", hotVideoViewAdapter.getItem(i).getId());
            intent.putExtra("title", hotVideoViewAdapter.getItem(i).getVideo_title());
            aty.showActivity(aty, intent);
        }
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
        ((HomePageContract.Presenter) mPresenter).getHomePageData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        Intent intent = new Intent(aty, GoodsDetailsActivity.class);
        intent.putExtra("goodName", boutiqueLineViewAdapter.getItem(position).getName());
        intent.putExtra("goodsid", boutiqueLineViewAdapter.getItem(position).getGoods_id());
        aty.showActivity(aty, intent);
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
                Log.d("tag111", location.getCity());
                if (isFirst) {
                    if (StringUtils.isEmpty(location.getCity())) {
                        PreferenceHelper.write(aty, StringConstants.FILENAME, "locationCity", getString(R.string.allAeservationNumber));
//                        tv_address.setText(getString(R.string.allAeservationNumber));
//                        ((HomePageContract.Presenter) mPresenter).getHomePage("");
                        PreferenceHelper.write(aty, StringConstants.FILENAME, "location", "");
                    } else {
//                        tv_address.setText(location.getCity());
                        //           PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", location.getCity());
                        //  ((HomePageContract.Presenter) mPresenter).getHomePage(location.getCity());
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
        hotVideoViewAdapter.clear();
        hotVideoViewAdapter = null;
        boutiqueLineViewAdapter.clear();
        hotVideoViewAdapter = null;
        mLocationClient.unRegisterLocationListener(myListener); //注销掉监听
        mLocationClient.stop(); //停止定位服务
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
//            String selectCity = data.getStringExtra("selectCity");
//            int selectCityId = data.getIntExtra("selectCityId", 0);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", selectCity);
//            showLoadingDialog(aty.getString(R.string.dataLoad));
//            ((HomePageContract.Presenter) mPresenter).getHomePage(selectCity);
//            Log.d("tag888", selectCity);
//        }
    }
}
