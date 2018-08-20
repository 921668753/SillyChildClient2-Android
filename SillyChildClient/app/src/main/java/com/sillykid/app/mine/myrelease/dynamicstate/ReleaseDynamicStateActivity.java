package com.sillykid.app.mine.myrelease.dynamicstate;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.glide.GlideCatchUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.sillykid.app.R;
import com.sillykid.app.adapter.ImagePickerAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.dialog.PublicPromptDialog;
import com.sillykid.app.dialog.PictureSourceDialog;
import com.sillykid.app.entity.ReleaseStrateItemBean;
import com.sillykid.app.entity.UploadImageBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 我的发布---发布新动态
 * Created by Administrator on 2017/9/2.
 */

public class ReleaseDynamicStateActivity extends BaseActivity implements DynamicStateContract.View,View.OnLongClickListener{

    private DynamicStateContract.Presenter mPresenter;

    @BindView(id=R.id.et_title)
    private EditText et_title;

    @BindView(id=R.id.et_content)
    private EditText et_content;

    @BindView(id=R.id.addlayout)
    private LinearLayout addlayout;

    @BindView(id=R.id.ll_newview,click = true)
    private LinearLayout ll_newview;

    @BindView(id=R.id.tv_repulish ,click = true)
    private TextView tv_repulish;

    private ArrayList<ImageItem> selImageList;//当前选择的所有图片
    private ArrayList<ImageItem> images = null;
    private ImagePickerAdapter adapter;
    private PictureSourceDialog pictureSourceDialog;
    private Intent intentig;
    private Intent intentPreview;
    private String urls="";
    private Thread threadpicture;
    private UploadImageBean uploadimagebean;
    private int urlsuccessnum=0;//上传图片成功的数量
    private int urlerrornum=0;//上传图片失败的数量
    private long fileSize;//每次压缩前的大小
    private long fileSizeNew;//每次压缩后的大小
    private File oldFile;
    private int totalnum=0;//图片总数
//    private upPictureHandler picturehandler;
    private String pictureerror;
    private List<ReleaseStrateItemBean> itemlist;
    private int layoutnumber=-1;
    private int currentdoview=0;
    private File imagefile;
    private PublicPromptDialog publicPromptDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_releasedynamicstate);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter=new DynamicStatePresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        initImagePicker();
        addLayoutView();
//        selImageList = new ArrayList<>();
//        adapter = new ImagePickerAdapter(this, selImageList, NumericConstants.MAXPICTURE,R.mipmap.mine_addpicturexxx);
//        adapter.setOnItemClickListener(this);
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this, 4);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.ll_newview:
                addLayoutView();
                break;
            case R.id.iv_image:
                currentdoview=(Integer) ((View)v.getParent()).getTag();
                PictureDialog();
                break;
            case R.id.tv_repulish:
                if (itemlist!=null&&itemlist.size()>0){
                    urls="";
                    for (ReleaseStrateItemBean bean:itemlist){
                        if (bean!=null&&!TextUtils.isEmpty(bean.getItemurl())){
                            urls+="|"+bean.getItemurl();
                        }
                    }
                }
                showLoadingDialog(getString(R.string.submissionLoad));
                ((DynamicStatePresenter)mPresenter).pulishDynamic(urls,et_title.getText().toString(),et_content.getText().toString(),9);
//                if (picturehandler==null)picturehandler=new upPictureHandler();
//                totalnum=selImageList.size();
//                urlsuccessnum=0;
//                urlerrornum=0;
//                pictureerror="";
//                urls="";
//                upOperation();
                break;
        }
    }

    /**
     * 设置标题
     */
    private void initTitle(){
        ActivityTitleUtils.initToolbar(aty, getString(R.string.newTrends), true, R.id.titlebar);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader=new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setShowCamera(false);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(NumericConstants.MAXPICTURE);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(400);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(500);                         //保存文件的高度。单位像素
        imagePicker.setShowCamera(false);//设置显示相机，默认显示
        imagePicker.setMultiMode(false);
    }

//    @Override
//    public void onItemClick(View view, int position) {
//        switch (position) {
//            case NumericConstants.IMAGE_ITEM_ADD:
//                PictureDialog();
//                break;
//            default:
//                if (view.getId()==R.id.iv_delete){
//                    if (images != null&&images.size()>position) {
//                        images.remove(position);
//                        selImageList.clear();
//                        selImageList.addAll(images);
//                        adapter.setImages(selImageList);
//                    }
//                }else{
//                    //打开预览
//                    if (intentPreview==null)intentPreview = new Intent(this, ImagePreviewDelActivity.class);
//                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
//                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
//                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                    startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
//                }
//
//                break;
//        }
//    }

    /**
     * 选择添加图片的方式
     */
    public void PictureDialog() {

        if (pictureSourceDialog==null){
            pictureSourceDialog= new PictureSourceDialog(this) {
                @Override
                public void takePhoto() {
                    takeTouxiang();
                }

                @Override
                public void chooseFromAlbum() {
                    selectPicture();
                }
            };
        }
        pictureSourceDialog.show();

    }

    private void takeTouxiang(){
        intentig = new Intent(this, ImageGridActivity.class);
        intentig.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intentig, NumericConstants.REQUEST_CODE_SELECT);
    }

    private void selectPicture(){
        //打开选择,本次允许选择的数量
        intentig = new Intent(this, ImageGridActivity.class);
        /* 如果需要进入选择的时候显示已经选中的图片，
         * 详情请查看ImagePickerActivity
         * */
        intentig.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
        startActivityForResult(intentig, NumericConstants.REQUEST_CODE_SELECT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == NumericConstants.REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    imagefile=new File(images.get(0).path);
                    imagefile = BitmapCoreUtil.customCompression(imagefile);
                    showLoadingDialog(getString(R.string.crossLoad));
                    ((DynamicStatePresenter)mPresenter).upPictures("file",imagefile,0);
                }
            }
        }
//        else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
//            //预览图片返回
//            if (data != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
//                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
//                if (images != null) {
//                    selImageList.clear();
//                    selImageList.addAll(images);
//                    adapter.setImages(selImageList);
//                }
//            }
//        }
    }

    @Override
    public void setPresenter(DynamicStateContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag==9){
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.successfulReviews));
            finish();
        }else{
            GlideCatchUtil.getInstance().cleanImageDisk();
            uploadimagebean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (uploadimagebean!=null&&uploadimagebean.getData()!=null&&uploadimagebean.getData().getFile()!=null&&!TextUtils.isEmpty(uploadimagebean.getData().getFile().getUrl())) {
                itemlist.get(currentdoview).setItemurl(uploadimagebean.getData().getFile().getUrl());
                GlideImageLoader.glideLoaderRaudio(this,uploadimagebean.getData().getFile().getUrl(),itemlist.get(currentdoview).getItemiv(),3,R.mipmap.placeholderfigure);
                dismissLoadingDialog();
            }else{
                ViewInject.toast("图片上传失败！");
                dismissLoadingDialog();
            }

//            uploadimagebean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
//            if (uploadimagebean!=null&&uploadimagebean.getData()!=null&&uploadimagebean.getData().getFile()!=null&&!TextUtils.isEmpty(uploadimagebean.getData().getFile().getUrl())){
//                urls+="|"+uploadimagebean.getData().getFile().getUrl();
//                urlsuccessnum++;
//                images.remove(urlerrornum);
//                selImageList.remove(urlerrornum);
//                adapter.setImages(selImageList);
//            }else{
////                images.remove(urlerrornum);
////                selImageList.remove(urlerrornum);
////                adapter.setImages(selImageList);
//                urlerrornum++;
//            }
//            upOperation();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        GlideCatchUtil.getInstance().cleanImageDisk();
        Log.e("图片",msg);
        if (isLogin(msg)){
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this,LoginActivity.class);
            finish();
            return;
        }
        dismissLoadingDialog();
        ViewInject.toast(msg);
//        if (flag==9){
//            dismissLoadingDialog();
//            ViewInject.toast(this,msg);
//        }else{
//            urlerrornum++;
//            pictureerror=msg;
////            upOperation();
//        }

    }
//    /**
//     * 上传图片
//     */
//    private void upOperation(){
//        showLoadingDialog(getString(R.string.crossLoad)+"\n("+urlsuccessnum+"/"+totalnum+")");
//        if (selImageList!=null&&selImageList.size()>0&&selImageList.size()>urlerrornum){
//            threadpicture=new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i=urlerrornum;i<selImageList.size();i++){
//                        oldFile = new File(selImageList.get(i).path);
//                        if (!(FileUtil.isFileExists(oldFile))) {
//                            urlerrornum++;
//                            continue;
//                        }
//                        fileSize = DataCleanManager.getFileSize(oldFile);
//                        while (fileSize >= StringConstants.COMPRESSION_SIZE){
//                            oldFile = BitmapCoreUtil.customCompression(oldFile);
//                            fileSizeNew=DataCleanManager.getFileSize(oldFile);
//                            if (fileSize==fileSizeNew){
//                                break;
//                            }else{
//                                fileSize = DataCleanManager.getFileSize(oldFile);
//                            }
//                            Log.e("图片",fileSize+"");
//                        }
//                        mPresenter.upPictures("file",oldFile,0);
//                        return;
//                    }
//                    picturehandler.sendEmptyMessage(0);
//                }
//            });
//            threadpicture.start();
//
//        }else{
//            dismissLoadingDialog();
//            mPresenter.pulishDynamic(urls,et_title.getText().toString(),et_content.getText().toString(),9);
//        }
//
//
//    }

    private void addLayoutView(){
        if (addlayout.getChildCount()==NumericConstants.MAXPICTURE){
            ViewInject.toast(getString(R.string.addToMax));
        }else{
            layoutnumber++;
            if (itemlist==null)itemlist=new ArrayList<>();
            View itemview=getLayoutInflater().inflate(R.layout.item_releasestrate,null);
            addlayout.addView(itemview);

//        RecyclerView recyclerView=(RecyclerView) itemview.findViewById(R.id.recyclerView);
//        recyclerView.setOnTouchListener(this);
//        recyclerView.setTag(layoutnumber);
//        ArrayList<ImageItem> selImageList=new ArrayList<>();
//        ImagePickerAdapter adapter = new ImagePickerAdapter(this, selImageList, NumericConstants.MAXPICTURE, R.mipmap.mine_addpicturexxx);
//        adapter.setOnItemClickListener(this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);

            RelativeLayout rl_addimages=(RelativeLayout) itemview.findViewById(R.id.rl_addimages);
            rl_addimages.setTag(layoutnumber);
            ImageView imageView=(ImageView) itemview.findViewById(R.id.iv_image);
            imageView.setOnClickListener(this);
            imageView.setOnLongClickListener(this);

            ReleaseStrateItemBean rsitembean=new ReleaseStrateItemBean();
            rsitembean.setItemview(itemview);
            rsitembean.setItemiv(imageView);
            rsitembean.setRladdimages(rl_addimages);
            itemlist.add(layoutnumber,rsitembean);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        currentdoview=(Integer) ((View)view.getParent()).getTag();
        if (publicPromptDialog ==null){
            publicPromptDialog =new PublicPromptDialog(this,getString(R.string.deleteSelect),getString(R.string.delete)) {
                @Override
                public void doAction() {
                    if (addlayout.getChildCount()==1){
                        ViewInject.toast(getString(R.string.deleteToMin));
                    }else{
                        addlayout.removeView(itemlist.get(currentdoview).getItemview());
                        itemlist.remove(currentdoview);
                        itemlist.add(currentdoview,null);
                    }
                    Log.e("调试",itemlist.size()+"");
                }
            };
        }
        publicPromptDialog.show();
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlideCatchUtil.getInstance().cleanImageDisk();
        GlideCatchUtil.getInstance().cleanCatchDisk();
    }

    //    class upPictureHandler extends android.os.Handler{
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            dismissLoadingDialog();
//            mPresenter.pulishDynamic(urls,et_title.getText().toString(),et_content.getText().toString(),9);
//        }
//    }


}
