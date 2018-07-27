package com.sillykid.app.mine.myrelease.mydynamic;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ImagePreviewNoDelActivity;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.sillykid.app.R;
import com.sillykid.app.adapter.ImagePickerAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.entity.main.community.ClassificationListBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的发布---发布新动态
 */
public class ReleaseDynamicActivity extends BaseActivity implements ReleaseDynamicContract.View, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    @BindView(id = R.id.ll_category, click = true)
    private LinearLayout ll_category;

    @BindView(id = R.id.tv_category)
    private TextView tv_category;

    @BindView(id = R.id.et_title)
    private EditText et_title;

    @BindView(id = R.id.et_addDescription)
    private EditText et_addDescription;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    @BindView(id = R.id.tv_release, click = true)
    private TextView tv_release;

    private List<ClassificationListBean.DataBean> classificationList;

    private OptionsPickerView pvOptions;

    private int classification_id = 0;

    private int type = 0;

    private List<ImageItem> selImageList;

    private List<ImageItem> images;

    private List<String> urllist;
    private ImagePickerAdapter adapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_releasedynamic);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ReleaseDynamicPresenter(this);
        selImageList = new ArrayList<>();
        urllist = new ArrayList<String>();
        adapter = new ImagePickerAdapter(this, selImageList, NumericConstants.MAXPICTURE, R.mipmap.feedback_add_pictures);
        selectClassification();
        showLoadingDialog(getString(R.string.dataLoad));
        ((ReleaseDynamicContract.Presenter) mPresenter).getClassificationList();
    }

    /**
     * 选择银行名称
     */
    @SuppressWarnings("unchecked")
    private void selectClassification() {
        pvOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                classification_id = classificationList.get(options1).getId();
                ((TextView) v).setText(classificationList.get(options1).getName());
            }
        }).build();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        initImagePicker();
        adapter.setOnItemClickListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(NumericConstants.MAXPICTURE);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
    }







    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.newTrends), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_category:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_category);
                break;
            case R.id.tv_release:
                showLoadingDialog(getString(R.string.submissionLoad));
//                ((ReleaseDynamicContract.Presenter) mPresenter).postAddPost(et_title.getText().toString().trim(), imgs,
//                        et_addDescription.getText().toString().trim(), classification_id, type);
                break;
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
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
                    if (selImageList != null && selImageList.size() > position) {
                        selImageList.remove(position);
                        urllist.remove(position);
                        adapter.setImages(selImageList);
                    }
                } else {
                    //打开预览
                    Intent intentPreview = new Intent(this, ImagePreviewNoDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                    startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                }
                break;
        }
    }


    @Override
    public void setPresenter(ReleaseDynamicContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ClassificationListBean classificationListBean = (ClassificationListBean) JsonUtil.json2Obj(success, ClassificationListBean.class);
            classificationList = classificationListBean.getData();
            if (classificationList != null && classificationList.size() > 0) {
                pvOptions.setPicker(classificationList);
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            urllist.add(success);
            selImageList.addAll(images);
            adapter.setImages(selImageList);
            dismissLoadingDialog();
        } else if (flag == 2) {


        } else if (flag == 3) {


        }


    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == ImagePicker.RESULT_CODE_ITEMS && requestCode == NumericConstants.REQUEST_CODE_SELECT) {
            //添加图片返回
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            showLoadingDialog(getString(R.string.crossLoad));
            ((ReleaseDynamicContract.Presenter) mPresenter).upPictures(images.get(0).path);
        } else if (data != null && resultCode == ImagePicker.RESULT_CODE_BACK && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
            //预览图片返回
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
            if (images != null) {
                selImageList.clear();
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        } else {
            ViewInject.toast(getString(R.string.noData));
        }
    }


}
