package com.sillykid.app.homepage.chartercustom;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.CompanyGuideViewAdapter;
import com.sillykid.app.adapter.CharterCustomClassificationViewAdaper;
import com.sillykid.app.adapter.BoutiqueLineViewAdapter;
import com.sillykid.app.entity.FindDriverBean;
import com.sillykid.app.entity.PackLineBean.ResultBean.BannerBean;
import com.sillykid.app.entity.PackLineBean;
import com.sillykid.app.entity.SearchDriverBean;
import com.sillykid.app.homepage.BannerDetailsActivity;
import com.sillykid.app.homepage.addressselection.newoverseas.NewOverseasCityActivity;
import com.sillykid.app.homepage.chartercustom.chartercommon.CharterListActivity;
import com.sillykid.app.homepage.chartercustom.companyguide.AllCompanyGuideActivity;
import com.sillykid.app.homepage.chartercustom.companyguide.CompanyGuideDetailsActivity;
import com.sillykid.app.homepage.chartercustom.personaltailor.PersonalTailorActivity;
import com.sillykid.app.homepage.chartercustom.routes.AllRoutesActivity;
import com.sillykid.app.homepage.chartercustom.routes.RouteDetailsActivity;
import com.sillykid.app.homepage.chartercustom.singletransport.SingleTransportActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 包车定制
 * Created by Admin on 2017/8/16.
 */
public class CharterCustomActivity extends BaseActivity implements AdapterView.OnItemClickListener, CharterCustomContract.View, BGABanner.Delegate<ImageView, BannerBean>, BGABanner.Adapter<ImageView, BannerBean>, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_address, click = true)
    private TextView tv_address;

    @BindView(id = R.id.et_designatedDriverHint)
    private EditText et_designatedDriverHint;

    @BindView(id = R.id.banner_ad)
    private BGABanner mForegroundBanner;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.gv_chartercustom)
    private NoScrollGridView gv_chartercustom;

    @BindView(id = R.id.ll_selectLine)
    private LinearLayout ll_selectLine;

    @BindView(id = R.id.tv_allSelectLine, click = true)
    private TextView tv_allSelectLine;

    @BindView(id = R.id.lv_selectLine)
    private ChildListView lv_selectLine;

    @BindView(id = R.id.ll_localGuide)
    private LinearLayout ll_localGuide;

    @BindView(id = R.id.tv_allLocalGuide, click = true)
    private TextView tv_allLocalGuide;

    @BindView(id = R.id.lv_localGuide)
    private ChildListView lv_localGuide;

    private CharterCustomClassificationViewAdaper charterCustomClassificationViewAdaper;

    private BoutiqueLineViewAdapter charterCustomViewAdapter;

    private CompanyGuideViewAdapter companyGuideViewAdapter;

    private String locationCity;
    private int isJump = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_chartercustom);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        initBanner();
        charterCustomClassificationViewAdaper = new CharterCustomClassificationViewAdaper(this);
        charterCustomViewAdapter = new BoutiqueLineViewAdapter(this);
        companyGuideViewAdapter = new CompanyGuideViewAdapter(this);
        mPresenter = new CharterCustomPresenter(this);
    }

    /**
     * 初始化控件
     */
    @SuppressWarnings("deprecation")
    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
//        mRefreshLayout.beginRefreshing();
        tv_title.setText(getString(R.string.charterCustom));
        gv_chartercustom.setAdapter(charterCustomClassificationViewAdaper);
        gv_chartercustom.setOnItemClickListener(this);
        lv_selectLine.setAdapter(charterCustomViewAdapter);
        lv_selectLine.setOnItemClickListener(this);
        lv_localGuide.setAdapter(companyGuideViewAdapter);
        lv_localGuide.setOnItemClickListener(this);
        //设置监听事件
        et_designatedDriverHint.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                et_designatedDriverHint.clearFocus(); //失去焦点
                et_designatedDriverHint.requestFocus();//获取焦点
                // 修改回车键功能
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(CharterCustomActivity.this
                                    .getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    //接下来在这里做你自己想要做的事，实现自己的业务。
                    showLoadingDialog(getString(R.string.dataLoad));
                    ((CharterCustomContract.Presenter) mPresenter).getFindDriver(et_designatedDriverHint.getText().toString().trim());
                }
                return false;
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_address:
                Intent intent = new Intent();
                intent.setClass(aty, NewOverseasCityActivity.class);
                startActivityForResult(intent, STATUS);
                break;
            case R.id.tv_allSelectLine:
                showActivity(aty, AllRoutesActivity.class);
                break;
            case R.id.tv_allLocalGuide:
                showActivity(aty, AllCompanyGuideActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.gv_chartercustom) {
            if (charterCustomClassificationViewAdaper.getItem(i).getName().equals(getString(R.string.quickReservation))) {
                ((CharterCustomContract.Presenter) mPresenter).isLogin(1);
            } else if (charterCustomClassificationViewAdaper.getItem(i).getName().equals(getString(R.string.privateOrder))) {
                ((CharterCustomContract.Presenter) mPresenter).isLogin(2);
            } else if (charterCustomClassificationViewAdaper.getItem(i).getName().equals(getString(R.string.byTheDay))) {
                ((CharterCustomContract.Presenter) mPresenter).isLogin(3);
            } else if (charterCustomClassificationViewAdaper.getItem(i).getName().equals(getString(R.string.airportPickup))) {
                ((CharterCustomContract.Presenter) mPresenter).isLogin(4);
            } else if (charterCustomClassificationViewAdaper.getItem(i).getName().equals(getString(R.string.airportDropOff))) {
                ((CharterCustomContract.Presenter) mPresenter).isLogin(5);
            } else if (charterCustomClassificationViewAdaper.getItem(i).getName().equals(getString(R.string.singleTransport))) {
                ((CharterCustomContract.Presenter) mPresenter).isLogin(6);
            }
        } else if (adapterView.getId() == R.id.lv_selectLine) {
            Intent intent = new Intent(aty, RouteDetailsActivity.class);
            intent.putExtra("seller_id", charterCustomViewAdapter.getItem(i).getSeller_id());
            intent.putExtra("line_id", charterCustomViewAdapter.getItem(i).getLine_id() + "");
            intent.putExtra("line_title", charterCustomViewAdapter.getItem(i).getLine_title());
            intent.putExtra("line_price", charterCustomViewAdapter.getItem(i).getLine_price());
            showActivity(aty, intent);
        } else if (adapterView.getId() == R.id.lv_localGuide) {
            Intent intent = new Intent(aty, CompanyGuideDetailsActivity.class);
            intent.putExtra("drv_id", companyGuideViewAdapter.getItem(i).getSeller_id());
//            intent.putExtra("coverImg", companyGuideViewAdapter.getItem(i).getSeller_id());
//            intent.putExtra("title", companyGuideViewAdapter.getItem(i).get());
            showActivity(aty, intent);
        }
    }

    @Override
    public void setPresenter(CharterCustomContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            Log.d("tag111", success);
            PackLineBean charterCustomBean = (PackLineBean) JsonUtil.json2Obj(success, PackLineBean.class);
            if (charterCustomBean == null) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            processLogic(charterCustomBean.getData().getBanner());
            if (charterCustomBean.getData().getIndex() == null || charterCustomBean.getData().getIndex().size() == 0 || charterCustomBean.getData().getIndex().isEmpty()) {
            } else {
                charterCustomClassificationViewAdaper.clear();
                charterCustomClassificationViewAdaper.addNewData(charterCustomBean.getData().getIndex());
            }
            if (charterCustomBean.getData().getLine() == null || charterCustomBean.getData().getLine().size() == 0 || charterCustomBean.getData().getLine().isEmpty()) {
                ll_selectLine.setVisibility(View.GONE);
                lv_selectLine.setVisibility(View.GONE);
            } else {
                ll_selectLine.setVisibility(View.VISIBLE);
                lv_selectLine.setVisibility(View.VISIBLE);
                charterCustomViewAdapter.clear();
                charterCustomViewAdapter.addNewData(charterCustomBean.getData().getLine());
            }
            if (charterCustomBean.getData().getDriver() == null || charterCustomBean.getData().getDriver().size() == 0 || charterCustomBean.getData().getDriver().isEmpty()) {
                ll_localGuide.setVisibility(View.GONE);
                lv_localGuide.setVisibility(View.GONE);
            } else {
                ll_localGuide.setVisibility(View.VISIBLE);
                lv_localGuide.setVisibility(View.VISIBLE);
                companyGuideViewAdapter.clear();
                companyGuideViewAdapter.addNewData(charterCustomBean.getData().getDriver());
            }
        } else if (flag == 1) {
            showActivity(aty, AllRoutesActivity.class);
        } else if (flag == 2) {
            String selectCity = PreferenceHelper.readString(this, StringConstants.FILENAME, "selectCity", "");
            if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.allAeservationNumber))) {
                errorMsg(KJActivityStack.create().topActivity().getString(R.string.travelCity2), 0);
                Intent intent = new Intent();
                intent.setClass(aty, NewOverseasCityActivity.class);
                intent.putExtra("isJump", 1);
                startActivityForResult(intent, STATUS);
                return;
            }
            showActivity(aty, PersonalTailorActivity.class);
        } else if (flag == 3) {
            Intent intent = new Intent(aty, CharterListActivity.class);
            intent.putExtra("type", 2);
            intent.putExtra("title", getString(R.string.charter));
            intent.putExtra("city", locationCity);
            showActivity(aty, intent);
        } else if (flag == 4) {
            Intent intent = new Intent(aty, CharterListActivity.class);
            intent.putExtra("type", 1);
            intent.putExtra("title", getString(R.string.airportPickup));
            intent.putExtra("city", locationCity);
            showActivity(aty, intent);
        } else if (flag == 5) {
            Intent intent = new Intent(aty, CharterListActivity.class);
            intent.putExtra("type", 3);
            intent.putExtra("title", getString(R.string.airportDropOff));
            intent.putExtra("city", locationCity);
            showActivity(aty, intent);
        } else if (flag == 6) {
            String selectCity = PreferenceHelper.readString(this, StringConstants.FILENAME, "selectCity", "");
            if (StringUtils.isEmpty(selectCity) || selectCity.contains(KJActivityStack.create().topActivity().getString(R.string.allAeservationNumber))) {
                errorMsg(KJActivityStack.create().topActivity().getString(R.string.travelCity2), 0);
                Intent intent = new Intent();
                intent.setClass(aty, NewOverseasCityActivity.class);
                intent.putExtra("isJump", 2);
                startActivityForResult(intent, STATUS);
                return;
            }
            showActivity(aty, SingleTransportActivity.class);
        } else if (flag == 7) {
            SearchDriverBean searchDriverBean = (SearchDriverBean) JsonUtil.getInstance().json2Obj(success, SearchDriverBean.class);
            if (searchDriverBean.getData() == null || searchDriverBean.getData().size() <= 0) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            et_designatedDriverHint.setText("");
            Intent intent = new Intent(aty, CompanyGuideDetailsActivity.class);
            intent.putExtra("drv_id", searchDriverBean.getData().get(0).getSeller_id());
            showActivity(aty, intent);
        } else if (flag == 8) {
            FindDriverBean findDriverBean = (FindDriverBean) JsonUtil.getInstance().json2Obj(success, FindDriverBean.class);
            if (findDriverBean.getData() == null) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            Intent intent = new Intent(aty, CompanyGuideDetailsActivity.class);
            intent.putExtra("drv_id", findDriverBean.getData().getSeller_id());
            showActivity(aty, intent);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        et_designatedDriverHint.clearFocus(); //失去焦点
        et_designatedDriverHint.requestFocus();//获取焦点
        if (flag == 1) {
            if (isLogin(msg)) {
                Intent intent = new Intent(aty, LoginActivity.class);
                // intent.putExtra("name", "GetOrderFragment");
                showActivity(aty, intent);
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
    private void processLogic(List<BannerBean> list) {
        if (list != null && list.size() > 0) {
            mForegroundBanner.setBackground(null);
            mForegroundBanner.setData(list, null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mForegroundBanner.startAutoPlay();
        if (isJump == 0) {
            locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
            tv_address.setText(locationCity);
            showLoadingDialog(getString(R.string.dataLoad));
            ((CharterCustomContract.Presenter) mPresenter).getCharterCustom(locationCity);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mForegroundBanner.stopAutoPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        charterCustomClassificationViewAdaper.clear();
        charterCustomClassificationViewAdaper = null;
        charterCustomViewAdapter.clear();
        charterCustomViewAdapter = null;
        companyGuideViewAdapter.clear();
        companyGuideViewAdapter = null;
    }


    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, BannerBean model, int position) {
        if (StringUtils.isEmpty(model.getAd_link())) {
            return;
        }
        Intent bannerDetails = new Intent(aty, BannerDetailsActivity.class);
        bannerDetails.putExtra("url", model.getAd_link());
        bannerDetails.putExtra("title", model.getAd_name());
        showActivity(aty, bannerDetails);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            String selectCountry = data.getStringExtra("selectCountry");
            isJump = data.getIntExtra("isJump", 0);
            int selectCountryId = data.getIntExtra("selectCountryId", 0);
            int selectCityId = data.getIntExtra("selectCityId", 0);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", selectCity);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCountry", selectCountry);
            tv_address.setText(selectCity);
            locationCity = selectCity;
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshingHomePageFragment", true);
            if (isJump == 1) {
                showActivity(aty, PersonalTailorActivity.class);
                return;
            } else if (isJump == 2) {
                showActivity(aty, SingleTransportActivity.class);
                return;
            }
            showLoadingDialog(getString(R.string.dataLoad));
            ((CharterCustomContract.Presenter) mPresenter).getCharterCustom(locationCity);
            Log.d("tag888", selectCity);
        }
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, BannerBean model, int position) {
        GlideImageLoader.glideOrdinaryLoader(aty, model.getAd_code(), itemView, R.mipmap.placeholderfigure2);
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
        tv_address.setText(locationCity);
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterCustomContract.Presenter) mPresenter).getCharterCustom(locationCity);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
