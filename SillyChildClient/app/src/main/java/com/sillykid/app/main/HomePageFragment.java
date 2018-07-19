package com.sillykid.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.myview.HorizontalListView;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.BuildConfig;
import com.sillykid.app.R;
import com.sillykid.app.adapter.homepage.HotRegionViewAdapter;
import com.sillykid.app.adapter.homepage.BoutiqueLineViewAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.HomePageBean;
import com.sillykid.app.entity.HomePageBean.ResultBean.AdBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.homepage.addressselection.newoverseas.NewOverseasCityActivity;
import com.sillykid.app.homepage.airportpickup.AirportPickupActivity;
import com.sillykid.app.homepage.boutiqueline.BoutiqueLineActivity;
import com.sillykid.app.homepage.localtalent.LocalTalentActivity;
import com.sillykid.app.homepage.localtalent.LocalTalentDetailsActivity;
import com.sillykid.app.homepage.popularstrategy.HotStrategyActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.trip.strategy.StrategyDetailsActivity;
import com.sillykid.app.utils.DisplayUtil;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import pub.devrel.easypermissions.EasyPermissions;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 首页
 * Created by Admin on 2017/8/10.
 */
public class HomePageFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, HomePageContract.View, BGABanner.Delegate<ImageView, AdBean>, BGABanner.Adapter<ImageView, AdBean>, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    /**
     * 客服
     */
    @BindView(id = R.id.tv_tag)
    private TextView tv_tag;

    @BindView(id = R.id.img_customerService, click = true)
    private ImageView img_customerService;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    /**
     * 轮播图
     */
    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    /**
     * 签证
     */
    @BindView(id = R.id.ll_visa, click = true)
    private LinearLayout ll_visa;

    /**
     * 攻略
     */
    @BindView(id = R.id.ll_strategy, click = true)
    private LinearLayout ll_strategy;

    /**
     * 线路
     */
    @BindView(id = R.id.ll_route, click = true)
    private LinearLayout ll_route;

    /**
     * 活动
     */
    @BindView(id = R.id.ll_activity, click = true)
    private LinearLayout ll_activity;

    /**
     * 热门视频  更多视频
     */
    @BindView(id = R.id.ll_hotVideo)
    private LinearLayout ll_hotVideo;

    @BindView(id = R.id.tv_moreVideo, click = true)
    private TextView tv_moreVideo;

    @BindView(id = R.id.hlv_hotVideo)
    private HorizontalListView hlv_hotVideo;

    /**
     * 热门地区  更多城市
     */
    @BindView(id = R.id.ll_hotRegion)
    private LinearLayout ll_hotRegion;

    @BindView(id = R.id.tv_moreCities, click = true)
    private TextView tv_moreCities;

    @BindView(id = R.id.hlv_hotRegion)
    private HorizontalListView hlv_hotRegion;


    @BindView(id = R.id.clv_hpStrategy)
    private ChildListView clv_hpStrategy;

    public LocationClient mLocationClient = null;

    public BDAbstractLocationListener myListener = null;
//BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明

    private boolean isFirst = true;

    private HotRegionViewAdapter hotRegionViewAdapter;
    private BoutiqueLineViewAdapter boutiqueLineViewAdapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_homepage, null);
    }

    @Override
    protected void initData() {
        super.initData();
//        screenAdaptation();
//        mPresenter = new HomePagePresenter(this);
//        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
//        hotRegionViewAdapter = new HotRegionViewAdapter(aty, hlv_hotRegion);
//        boutiqueLineViewAdapter = new BoutiqueLineViewAdapter(aty);
//        mLocationClient = new LocationClient(aty.getApplicationContext());
//        myListener = new MyLocationListener();
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
//        initBanner();
//        hlv_hotRegion.setAdapter(hotRegionViewAdapter);
//        hlv_hotRegion.setOnItemClickListener(this);
//        clv_boutiqueLine.setAdapter(boutiqueLineViewAdapter);
//        clv_boutiqueLine.setOnItemClickListener(this);
//        //声明LocationClient类
//        mLocationClient.registerLocationListener(myListener);
//        //注册监听函数
//        ((HomePagePresenter) mPresenter).initLocation(aty, mLocationClient);
//        showLoadingDialog(aty.getString(R.string.dataLoad));
//        ((HomePagePresenter) mPresenter).getHomePage("");
//        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(BuildConfig.HUANXIN_IM);
//        try {
//            if (conversation.getUnreadMsgCount() > 0) {
//                tv_tag.setVisibility(View.GONE);
//            } else {
//                tv_tag.setVisibility(View.GONE);
//            }
//        } catch (Exception e) {
//            tv_tag.setVisibility(View.GONE);
//        }
    }

    /**
     * @param v 控件监听事件
     */
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_address:
                Intent intent = new Intent();
                intent.setClass(aty, NewOverseasCityActivity.class);
                startActivityForResult(intent, STATUS);
                //  aty.showActivityForResult(aty, AddressSelectionActivity.class, STATUS);
                break;
            case R.id.img_customerService:
                ((HomePagePresenter) mPresenter).isLogin(1);
                break;
            case R.id.ll_airportPickup:
                aty.showActivity(aty, AirportPickupActivity.class);
                break;
//            case R.id.ll_boutiqueLine:
//                aty.showActivity(aty, BoutiqueLineActivity.class);
//                break;
//            case R.id.ll_privateOrdering:
//                Intent intent1 = new Intent(aty, MainActivity.class);
//                intent1.putExtra("newChageIcon", 2);
//                aty.showActivity(aty, intent1);
//                break;
//            case R.id.ll_byTheDay:
////                Intent intent1 = new Intent(aty, MainActivity.class);
////                intent1.putExtra("newChageIcon", 2);
////                aty.showActivity(aty, intent1);
//                break;
            case R.id.ll_airportDropOff:
//                Intent intent1 = new Intent(aty, MainActivity.class);
//                intent1.putExtra("newChageIcon", 2);
//                aty.showActivity(aty, intent1);
                break;
            case R.id.tv_moreCities:
                aty.showActivity(aty, LocalTalentActivity.class);
                break;
//            case R.id.tv_moreLines:
//                Intent intent2 = new Intent(aty, HotStrategyActivity.class);
//                //    intent2.putExtra("city", tv_address.getText().toString());
//                aty.showActivity(aty, intent2);
//                break;
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
            processLogic(homePageBean.getData().getAd());
            if (homePageBean.getData().getAction() == null) {
                dismissLoadingDialog();
                return;
            }
            if (homePageBean.getData().getAction().getLocal() == null || homePageBean.getData().getAction().getLocal().size() == 0 || homePageBean.getData().getAction().getLocal().isEmpty()) {
                ll_hotRegion.setVisibility(View.GONE);
                hlv_hotRegion.setVisibility(View.GONE);
            } else {
                ll_hotRegion.setVisibility(View.VISIBLE);
                hlv_hotRegion.setVisibility(View.VISIBLE);
                hotRegionViewAdapter.clear();
                homePageBean.getData().getAction().getLocal().get(homePageBean.getData().getAction().getLocal().size() - 1).setStatusL("last");
                hotRegionViewAdapter.addNewData(homePageBean.getData().getAction().getLocal());
            }
//            if (homePageBean.getData().getAction().getHot() == null || homePageBean.getData().getAction().getHot().size() == 0 || homePageBean.getData().getAction().getHot().isEmpty()) {
//                ll_boutiqueLine1.setVisibility(View.GONE);
//                clv_boutiqueLine.setVisibility(View.GONE);
//            } else {
//                ll_boutiqueLine1.setVisibility(View.VISIBLE);
//                clv_boutiqueLine.setVisibility(View.VISIBLE);
//                boutiqueLineViewAdapter.clear();
//                boutiqueLineViewAdapter.addNewData(homePageBean.getData().getAction().getHot());
//            }
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
                Intent intent = new Intent(aty, LoginActivity.class);
                // intent.putExtra("name", "GetOrderFragment");
                aty.showActivity(aty, intent);
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
    private void processLogic(List<AdBean> list) {
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
//        boolean isRefreshingChangeHomePageFragment = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshingChangeHomePageFragment", false);
//        if (isRefreshingChangeHomePageFragment) {
//            String locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
//            tv_address.setText(locationCity);
//            showLoadingDialog(getString(R.string.dataLoad));
//            if (tv_address.getText().toString().equals(getString(R.string.allAeservationNumber))) {
//                ((HomePageContract.Presenter) mPresenter).getHomePage("");
//            } else {
//                ((HomePageContract.Presenter) mPresenter).getHomePage(tv_address.getText().toString());
//            }
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (aty.getChageIcon() == 0) {
            mForegroundBanner.stopAutoPlay();
        }
    }


    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, AdBean model, int position) {
        //   GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView);
        GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView, R.mipmap.placeholderfigure2);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, AdBean model, int position) {
        if (StringUtils.isEmpty(model.getAd_link())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getAd_link());
        bannerDetails.putExtra("title", model.getAd_name());
        aty.showActivity(aty, bannerDetails);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.hlv_hotRegion) {
            Intent intent = new Intent(aty, LocalTalentDetailsActivity.class);
            intent.putExtra("talent_id", hotRegionViewAdapter.getItem(i).getTalent_id());
            aty.showActivity(aty, intent);
        } else if (adapterView.getId() == R.id.clv_hpStrategy) {
            Intent intent = new Intent(aty, StrategyDetailsActivity.class);
            intent.putExtra("guide_id", boutiqueLineViewAdapter.getItem(i).getGuide_id());
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
        String locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
        showLoadingDialog(getString(R.string.dataLoad));
        //   if (tv_address.getText().toString().equals(getString(R.string.allAeservationNumber))) {
        ((HomePageContract.Presenter) mPresenter).getHomePage("");
//        } else {
//            ((HomePageContract.Presenter) mPresenter).getHomePage(tv_address.getText().toString());
//        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
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

    private void screenAdaptation() {
        int width = DisplayUtil.getWidthForScreen(aty, 0, 0, 0);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, (int) (width / 750f * 260));
        mForegroundBanner.setLayoutParams(layoutParams);
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, (int) (width / 750f * 210));
        layoutParams.setMargins(0, DisplayUtil.dip2px(aty, 5), 0, 0);
    }

}
