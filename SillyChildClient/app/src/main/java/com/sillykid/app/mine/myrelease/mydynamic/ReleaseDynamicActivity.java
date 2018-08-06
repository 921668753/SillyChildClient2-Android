package com.sillykid.app.mine.myrelease.mydynamic;


import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.common.pictureselector.FullyGridLayoutManager;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.FileUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.sillykid.app.R;
import com.sillykid.app.adapter.mine.myrelease.mydynamic.GridImageAdapter;
import com.sillykid.app.entity.community.dynamic.DynamicDetailsBean;
import com.sillykid.app.entity.main.community.ClassificationListBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.mycollection.CollectionContract;
import com.sillykid.app.mine.mycollection.dialog.DeleteCollectionDialog;
import com.sillykid.app.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.titlebar.BGATitleBar;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 我的发布---发布新动态
 */
public class ReleaseDynamicActivity extends BaseActivity implements ReleaseDynamicContract.View {

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

    private List<LocalMedia> selectList = null;
    private GridImageAdapter adapter;
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();
    private int aspect_ratio_x = 16, aspect_ratio_y = 9;
    private int maxSelectNum = 9;
    private List<LocalMedia> selectList1;
    private int id = 0;
    private int type = 0;

    private DeleteCollectionDialog deleteCollectionDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_releasedynamic);
    }

    @Override
    public void initData() {
        super.initData();
        id = getIntent().getIntExtra("id", 0);
        type = getIntent().getIntExtra("type", 0);
        themeId = R.style.picture_default_style;
        mPresenter = new ReleaseDynamicPresenter(this);
        selectList1 = new ArrayList<>();
        selectList = new ArrayList<>();
        FullyGridLayoutManager manager = new FullyGridLayoutManager(ReleaseDynamicActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(ReleaseDynamicActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(ReleaseDynamicActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(ReleaseDynamicActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(ReleaseDynamicActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(ReleaseDynamicActivity.this);
                } else {
                    Toast.makeText(ReleaseDynamicActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
        selectClassification();
        initDeleteCollectionDialog();
        showLoadingDialog(getString(R.string.dataLoad));
        ((ReleaseDynamicContract.Presenter) mPresenter).getClassificationList();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            if (selectList.size() > 0) {
                String pictureType = selectList.get(selectList.size() - 1).getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                if (mediaType == 2 || mediaType == 3) {
                    ViewInject.toast(getString(R.string.videoOnlyAddOne));
                    return;
                }
            }
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(ReleaseDynamicActivity.this)
                    .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                    .previewImage(true)// 是否可预览图片
                    .previewVideo(true)// 是否可预览视频
                    .enablePreviewAudio(true) // 是否可播放音频
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    .setOutputCameraPath(FileUtils.getSaveFolder(StringConstants.PHOTOPATH).getAbsolutePath())// 自定义拍照保存路径
//                    .enableCrop(true)// 是否裁剪
//                    .compress(false)// 是否压缩
                    //              .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    //       .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                    .isGif(true)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .openClickSound(false)// 是否开启点击声音
                    .selectionMedia(selectList)// 是否传入已选图片
                    //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled(true) // 裁剪是否可旋转图片
                    //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    //.videoSecond()//显示多少秒以内的视频or音频也可适用
                    //.recordVideoSecond()//录制视频秒数 默认60s
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }


    };

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
    }


    /**
     * 设置标题
     */
    public void initTitle() {
        if (type == 0) {
            ActivityTitleUtils.initToolbar(aty, getString(R.string.newTrends), true, R.id.titlebar);
        } else {
            BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
                @Override
                public void onClickLeftCtv() {
                    super.onClickLeftCtv();
                    aty.finish();
                }

                @Override
                public void onClickRightCtv() {
                    super.onClickRightCtv();
                    if (deleteCollectionDialog == null) {
                        initDeleteCollectionDialog();
                    }
                    if (deleteCollectionDialog != null && !deleteCollectionDialog.isShowing()) {
                        deleteCollectionDialog.show();
                        deleteCollectionDialog.setCollectionId(id);
                    }

                }
            };
            ActivityTitleUtils.initToolbar(aty, getString(R.string.editDynamic), getString(R.string.delete), R.id.titlebar, simpleDelegate);
        }
    }

    /**
     * 弹框
     */
    public void initDeleteCollectionDialog() {
        deleteCollectionDialog = new DeleteCollectionDialog(aty, getString(R.string.determineDeleteDynamic)) {
            @Override
            public void deleteCollectionDo(int addressId) {
                showLoadingDialog(getString(R.string.deleteLoad));
                ((ReleaseDynamicContract.Presenter) mPresenter).postDeletePost(addressId);
            }
        };
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
                if (type == 0) {
                    ((ReleaseDynamicContract.Presenter) mPresenter).postAddPost(et_title.getText().toString().trim(), selectList, et_addDescription.getText().toString().trim(), classification_id);
                    break;
                }
                ((ReleaseDynamicContract.Presenter) mPresenter).postEditPost(et_title.getText().toString().trim(), selectList, et_addDescription.getText().toString().trim(), classification_id, id);
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
            if (type != 0) {
                ((ReleaseDynamicContract.Presenter) mPresenter).getPostDetail(id);
                return;
            }
            dismissLoadingDialog();
        } else if (flag == 1 || flag == 2) {
            selectList.addAll(selectList1);
            selectList.get(selectList.size() - 1).setPath(success);
            adapter.setList(selectList);
            adapter.notifyDataSetChanged();
            dismissLoadingDialog();
        } else if (flag == 3) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.releaseSuccessful));
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
        } else if (flag == 4) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.editDynamicSuccess));
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
        } else if (flag == 5) {
            DynamicDetailsBean dynamicDetailsBean = (DynamicDetailsBean) JsonUtil.getInstance().json2Obj(success, DynamicDetailsBean.class);
            classification_id = dynamicDetailsBean.getData().getClassification_id();
            for (int i = 0; i < classificationList.size(); i++) {
                if (classificationList.get(i).getId() == classification_id) {
                    pvOptions.setSelectOptions(i);
                    tv_category.setText(classificationList.get(i).getName());
                    break;
                }
            }
            et_title.setText(dynamicDetailsBean.getData().getPost_title());
            et_title.setSelection(dynamicDetailsBean.getData().getPost_title().length());
            et_addDescription.setText(dynamicDetailsBean.getData().getContent());
            et_addDescription.setSelection(dynamicDetailsBean.getData().getContent().length());
            for (int i = 0; i < dynamicDetailsBean.getData().getList().size(); i++) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.setPath(dynamicDetailsBean.getData().getList().get(i));
                if (dynamicDetailsBean.getData().getType() == 1) {
                    localMedia.setPictureType("image/jpeg");
                } else {
                    localMedia.setPictureType("video");
                }
                localMedia.setPosition(i);
                selectList.add(localMedia);
            }
            adapter.setList(selectList);
            adapter.notifyDataSetChanged();
            dismissLoadingDialog();
        } else if (flag == 6) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.deleteDynamicSuccess));
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
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
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList1 = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的

//                    for (LocalMedia media : selectList) {
//                        Log.i("图片-----》", media.getPath());
////                        String pictureType = media.getPictureType();
////                        int mediaType = PictureMimeType.pictureToVideo(pictureType);
//                    }
                    LocalMedia media = selectList1.get(selectList1.size() - 1);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    if (mediaType == 1) {
                        ((ReleaseDynamicContract.Presenter) mPresenter).upPictures(media.getPath());
                        break;
                    }
                    ((ReleaseDynamicContract.Presenter) mPresenter).uploadVideo(media.getPath());
                    break;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectList.clear();
        selectList = null;
        selectList1.clear();
        selectList1 = null;
        classificationList.clear();
        classificationList = null;
        adapter = null;
    }
}
