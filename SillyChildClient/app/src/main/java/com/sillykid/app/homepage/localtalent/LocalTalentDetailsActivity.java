package com.sillykid.app.homepage.localtalent;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.LocalTalentDetailsBean;
import com.sillykid.app.homepage.chartercustom.companyguide.CompanyGuideDetailsActivity;
import com.sillykid.app.homepage.homestaysubscribe.HomestayDetailsActivity;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 达人详情
 * Created by Admin on 2017/8/15.
 */
public class LocalTalentDetailsActivity extends BaseActivity implements LocalTalentDetailsContract.View {

    @BindView(id = R.id.img_back, click = true)
    private ImageView img_back;
    //
    @BindView(id = R.id.img_video)
    private ImageView img_video;

    @BindView(id = R.id.videoplayer)
    private JzvdStd videoplayer;


    @BindView(id = R.id.tv_name)
    private TextView tv_name;

    @BindView(id = R.id.tv_shopkeepers)
    private TextView tv_shopkeepers;

    @BindView(id = R.id.tv_landlord)
    private TextView tv_landlord;

    @BindView(id = R.id.tv_companyGuide)
    private TextView tv_companyGuide;

    @BindView(id = R.id.tv_platform)
    private TextView tv_platform;

    @BindView(id = R.id.tv_user)
    private TextView tv_user;


    @BindView(id = R.id.tv_sort)
    private TextView tv_sort;

    @BindView(id = R.id.img_zan, click = true)
    private ImageView img_zan;

    @BindView(id = R.id.tv_shopAround, click = true)
    private TextView tv_shopAround;

    @BindView(id = R.id.tv_goToHouse, click = true)
    private TextView tv_goToHouse;

    @BindView(id = R.id.tv_goForRide, click = true)
    private TextView tv_goForRide;

    @BindView(id = R.id.web_summary)
    private WebView web_summary;

    @BindView(id = R.id.tv_address)
    private TextView tv_address;

    private boolean isShopkeepers = false;
    private boolean isCompanyGuide = false;
    private boolean isLandlord = false;
    private String store_id;
    private String user_id;
    private String drv_id;
    private String lable;
    private String talent_id;
    private int is_good = 0;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_localtalentdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new LocalTalentDetailsPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        showLoadingDialog(getString(R.string.dataLoad));
        talent_id = getIntent().getStringExtra("talent_id");
        ((LocalTalentDetailsContract.Presenter) mPresenter).getLocalTalentDetails(talent_id);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_zan:
                showLoadingDialog(getString(R.string.dataLoad));
                ((LocalTalentDetailsContract.Presenter) mPresenter).postPraise(talent_id);
                break;
            case R.id.tv_shopAround:
                //店铺详情
                if (isShopkeepers) {
//                    Intent intent = new Intent(aty, s.class);
//                    intent.putExtra("store_id", store_id);
//                    showActivity(aty, intent);
                    return;
                }
                ViewInject.toast(getString(R.string.notOpenedStore));
                break;
            case R.id.tv_goToHouse:
                //民宿详情
                if (isLandlord) {
                    Intent intent = new Intent(aty, HomestayDetailsActivity.class);
                    intent.putExtra("user_id", user_id);
                    showActivity(aty, intent);
                    return;
                }
                ViewInject.toast(getString(R.string.noHomeStay));
                break;
            case R.id.tv_goForRide:
                //司导详情
                if (isCompanyGuide) {
                    Intent intent = new Intent(aty, CompanyGuideDetailsActivity.class);
                    intent.putExtra("drv_id", drv_id);
                    showActivity(aty, intent);
                    return;
                }
                ViewInject.toast(getString(R.string.notCompanyGuide));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void setPresenter(LocalTalentDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            LocalTalentDetailsBean localTalentDetailsBean = (LocalTalentDetailsBean) JsonUtil.getInstance().json2Obj(success, LocalTalentDetailsBean.class);
            //  GlideImageLoader.glideOrdinaryLoader(aty, localTalentDetailsBean.getData().getCover_img(), img_video);
            if (!StringUtils.isEmpty(localTalentDetailsBean.getData().getVideo_url())) {
                img_video.setVisibility(View.GONE);
                videoplayer.setUp(localTalentDetailsBean.getData().getVideo_url(), "", Jzvd.SCREEN_WINDOW_NORMAL);
                GlideImageLoader.glideOrdinaryLoader(aty, localTalentDetailsBean.getData().getCover_img(), videoplayer.thumbImageView, R.mipmap.placeholderfigure);
            } else {
                img_video.setVisibility(View.VISIBLE);
                GlideImageLoader.glideOrdinaryLoader(aty, localTalentDetailsBean.getData().getCover_img(), img_video, R.mipmap.placeholderfigure);
                videoplayer.setVisibility(View.GONE);
            }
            tv_name.setText(localTalentDetailsBean.getData().getName());
            tv_address.setText(localTalentDetailsBean.getData().getCity());
            if (localTalentDetailsBean.getData().getType_info() != null && localTalentDetailsBean.getData().getType_info().contains(getString(R.string.shopkeepers)) || localTalentDetailsBean.getData().getLable() == 4) {
                isShopkeepers = true;
                store_id = localTalentDetailsBean.getData().getStore_id();
                tv_shopkeepers.setVisibility(View.VISIBLE);
            } else {
                isShopkeepers = false;
                tv_shopkeepers.setVisibility(View.GONE);
            }
            if (localTalentDetailsBean.getData().getType_info() != null && localTalentDetailsBean.getData().getType_info().contains(getString(R.string.landlord)) || localTalentDetailsBean.getData().getLable() == 3) {
                isLandlord = true;
                user_id = localTalentDetailsBean.getData().getUser_id();
                tv_landlord.setVisibility(View.VISIBLE);
            } else {
                isLandlord = false;
                tv_landlord.setVisibility(View.GONE);
            }
            if (localTalentDetailsBean.getData().getType_info() != null && localTalentDetailsBean.getData().getType_info().contains(getString(R.string.companyGuide)) || localTalentDetailsBean.getData().getLable() == 2) {
                isCompanyGuide = true;
                drv_id = localTalentDetailsBean.getData().getSeller_id() + "";
                tv_companyGuide.setVisibility(View.VISIBLE);
            } else {
                isCompanyGuide = false;
                tv_companyGuide.setVisibility(View.GONE);
            }

            if (localTalentDetailsBean.getData().getType_info() != null && localTalentDetailsBean.getData().getType_info().contains(getString(R.string.user)) || localTalentDetailsBean.getData().getLable() == 1) {
                tv_user.setVisibility(View.VISIBLE);
            } else {
                tv_user.setVisibility(View.GONE);
            }

            if (localTalentDetailsBean.getData().getType_info() != null && localTalentDetailsBean.getData().getType_info().contains(getString(R.string.platform)) || localTalentDetailsBean.getData().getIs_admin() == 1) {
                tv_platform.setVisibility(View.VISIBLE);
            } else {
                tv_platform.setVisibility(View.GONE);
            }

//        lable = localTalentDetailsBean.getData().getLable();
            is_good = localTalentDetailsBean.getData().getIs_good();
            if (is_good == 1) {
                img_zan.setImageResource(R.mipmap.btn_favor_pressed);
            } else {
                img_zan.setImageResource(R.mipmap.btn_favor_normal);
            }
            tv_sort.setText(localTalentDetailsBean.getData().getGood_num() + "");
            String code = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><title></title></head><body>" + localTalentDetailsBean.getData().getContent() + "</body></html>";
            web_summary.loadDataWithBaseURL("baseurl", code, "text/html", "utf-8", null);
        } else if (flag == 1) {
            is_good = 1;
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshingHomePageFragment", true);
            img_zan.setImageResource(R.mipmap.btn_favor_pressed);
            tv_sort.setText(StringUtils.toInt(tv_sort.getText().toString(), 0) + 1 + "");
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            Intent intent = new Intent(aty, LoginActivity.class);
            showActivity(aty, intent);
            return;
        }
        ViewInject.toast(msg);
    }
}
