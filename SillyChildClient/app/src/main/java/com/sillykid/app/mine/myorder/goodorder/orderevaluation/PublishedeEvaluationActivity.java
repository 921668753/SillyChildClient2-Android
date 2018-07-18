package com.sillykid.app.mine.myorder.goodorder.orderevaluation;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.azhong.ratingbar.RatingBar;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.sillykid.app.R;
import com.sillykid.app.adapter.ImagePickerAdapter;
import com.sillykid.app.adapter.mine.myorder.orderevaluation.PublishedeEvaluationViewAdapter;
import com.sillykid.app.entity.mine.myorder.goodorder.orderevaluation.PublishedeEvaluationBean.DataBean.MemberCommentExtsBean;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.mine.myorder.OrderDetailBean;
import com.sillykid.app.entity.mine.myorder.OrderDetailBean.DataBeanX.ItemListBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 发表评价
 */
public class PublishedeEvaluationActivity extends BaseActivity implements PublishedeEvaluationContract.View, PublishedeEvaluationViewAdapter.OnStatusListener {

    @BindView(id = R.id.rl_publishedeEvaluation)
    private RecyclerView recyclerview;

    @BindView(id = R.id.rb_descriptionConsistent)
    private RatingBar rb_descriptionConsistent;

    @BindView(id = R.id.rb_logisticsService)
    private RatingBar rb_logisticsService;

    @BindView(id = R.id.rb_serviceAttitude)
    private RatingBar rb_serviceAttitude;

    @BindView(id = R.id.tv_release, click = true)
    private TextView tv_release;

    private int order_id = 0;

    private PublishedeEvaluationViewAdapter mAdapter = null;

    private int selectePosition = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_publishedeevaluation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new PublishedeEvaluationPresenter(this);
        mAdapter = new PublishedeEvaluationViewAdapter(recyclerview);
        order_id = getIntent().getIntExtra("order_id", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((PublishedeEvaluationContract.Presenter) mPresenter).getOrderDetails(order_id);
        initImagePicker();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(5);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(5, 10);
        //设置item之间的间隔
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(mAdapter);
        // rl_publishedeEvaluation.setAdapter(mAdapter);
        mAdapter.setOnStatusListener(this);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.publishedeEvaluation), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_release:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((PublishedeEvaluationContract.Presenter) mPresenter).postCommentCreate(mAdapter.getData(), rb_descriptionConsistent.getStar(), rb_logisticsService.getStar(), rb_serviceAttitude.getStar());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == ImagePicker.RESULT_CODE_ITEMS && requestCode == NumericConstants.REQUEST_CODE_SELECT) {
            //添加图片返回
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            showLoadingDialog(getString(R.string.crossLoad));
            ((PublishedeEvaluationContract.Presenter) mPresenter).upPictures(images.get(0).path);
        } else if (data != null && resultCode == ImagePicker.RESULT_CODE_BACK && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
            //预览图片返回
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
            if (images != null && images.size() > 0) {
                mAdapter.getData().get(selectePosition).getImageList().clear();
                for (int i = 0; i < images.size(); i++) {
                    mAdapter.getData().get(selectePosition).getImageList().add(images.get(i).path);
                }
                mAdapter.notifyItemChanged(selectePosition);
            } else if (images != null && images.size() == 0) {
                mAdapter.getData().get(selectePosition).getImageList().clear();
                mAdapter.notifyItemChanged(selectePosition);
            }
        }
    }

    @Override
    public void setPresenter(PublishedeEvaluationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            OrderDetailBean orderDetailBean = (OrderDetailBean) JsonUtil.getInstance().json2Obj(success, OrderDetailBean.class);
            if (orderDetailBean.getData().getItemList() != null && orderDetailBean.getData().getItemList().size() > 0) {
                mAdapter.clear();
                List<MemberCommentExtsBean> memberCommentExtsBeanList = new ArrayList<MemberCommentExtsBean>();
                for (int i = 0; i < orderDetailBean.getData().getItemList().size(); i++) {
                    ItemListBean itemListBean = orderDetailBean.getData().getItemList().get(i);
                    MemberCommentExtsBean memberCommentExtsBean = new MemberCommentExtsBean();
                    memberCommentExtsBean.setImage(itemListBean.getImage());
                    memberCommentExtsBean.setGoods_id(itemListBean.getGoods_id());
                    memberCommentExtsBean.setName(itemListBean.getName());
                    memberCommentExtsBean.setPrice(itemListBean.getPrice());
                    memberCommentExtsBean.setSpecs(itemListBean.getSpecs());
                    List<String> list = new ArrayList<String>();
                    memberCommentExtsBean.setImageList(list);
                    memberCommentExtsBeanList.add(memberCommentExtsBean);
                }
                mAdapter.addMoreData(memberCommentExtsBeanList);
            }
        } else if (flag == 1) {
            if (mAdapter.getData().get(selectePosition).getImageList() == null) {
                ArrayList<String> list = new ArrayList<>();
                mAdapter.getData().get(selectePosition).setImageList(list);
            }
            mAdapter.getData().get(selectePosition).getImageList().add(success);
            mAdapter.notifyItemChanged(selectePosition);
        } else if (flag == 2) {
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusPublishedeEvaluationEvent"));
            ViewInject.toast(getString(R.string.successfulEvaluation));
            finish();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(this, LoginActivity.class);
            if (flag == 0) {
                finish();
            }
            return;
        }
        ViewInject.toast(msg);
    }


    @Override
    public void onSetStatusListener(View view, ImagePickerAdapter adapter, int position, int position1) {
        selectePosition = position;
        switch (position1) {
            case NumericConstants.IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                Intent intent1 = new Intent(this, ImageGridActivity.class);
                /* 如果需要进入选择的时候显示已经选中的图片，
                 * 详情请查看ImagePickerActivity
                 * */
//                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                startActivityForResult(intent1, NumericConstants.REQUEST_CODE_SELECT);
                break;
            default:
                if (view.getId() == R.id.iv_delete) {
                    if (mAdapter.getData() != null && mAdapter.getData().size() > 0) {
                        mAdapter.getData().get(position).getImageList().remove(position1);
                        mAdapter.notifyItemChanged(selectePosition);
                    }
                } else {
//                    ArrayList<ImageItem> images = new ArrayList<ImageItem>();
//                    for (int i = 0; i < mAdapter.getItem(position).getCommentImgs().size(); i++) {
//                        ImageItem imageItem = new ImageItem();
//                        imageItem.path = mAdapter.getItem(position).getCommentImgs().get(i);
//                        images.add(imageItem);
//                    }
                    //打开预览
                    Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                    //    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position1);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                    startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerview.removeAllViews();
        mAdapter.clear();
        mAdapter = null;
    }
}
