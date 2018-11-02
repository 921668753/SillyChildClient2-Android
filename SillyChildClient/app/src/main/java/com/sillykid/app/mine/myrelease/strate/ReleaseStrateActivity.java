package com.sillykid.app.mine.myrelease.strate;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
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
import com.google.gson.Gson;
import com.kymjs.common.PreferenceHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.sillykid.app.R;
import com.sillykid.app.adapter.ImagePickerAdapter.OnRecyclerViewItemClickListener;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.dialog.ImagePopupWindow;
import com.sillykid.app.dialog.PictureSourceDialog;
import com.sillykid.app.dialog.PublicPromptDialog;
import com.sillykid.app.entity.ReleaseStrateItemBean;
import com.sillykid.app.entity.StrateForPCBean;
import com.sillykid.app.entity.UploadImageBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.sillykid.app.constant.NumericConstants.STATUS;

/**
 * 我的发布---发布新攻略
 * Created by Administrator on 2017/9/2.
 */

public class ReleaseStrateActivity extends BaseActivity implements StrateContract.View,OnRecyclerViewItemClickListener,View.OnLongClickListener {

    @BindView(id=R.id.ll_selectdistrict)
    private LinearLayout ll_allactivity;

    @BindView(id=R.id.et_title)
    private EditText et_title;

    @BindView(id=R.id.ll_selectdistrict,click = true)
    private LinearLayout ll_selectdistrict;
    @BindView(id=R.id.tv_selectdistrict)
    private TextView tv_selectdistrict;

    @BindView(id=R.id.et_profile)
    private EditText et_profile;

    @BindView(id=R.id.addlayout)
    private LinearLayout addlayout;

    @BindView(id=R.id.ll_newview)
    private LinearLayout ll_newview;
    @BindView(id=R.id.iv_newview,click = true)
    private ImageView iv_newview;
    @BindView(id=R.id.tv_newview,click = true)
    private TextView tv_newview;

    @BindView(id=R.id.tv_release ,click = true)
    private TextView tv_release;

//    private upPictureHandler picturehandler;
    private int totalnum;
    private int urlsuccessnum;
    private int urlerrornum;
    private String pictureerror;
    private Intent intentPreview;
    private PictureSourceDialog pictureSourceDialog;
    private Intent intentig;
    private UploadImageBean uploadimagebean;
    private Thread threadpicture;
    private File oldFile;
    private long fileSize;
    private long fileSizeNew;
    private List<ReleaseStrateItemBean> itemlist;
    private int layoutnumber=-1;
    private ArrayList<ImageItem> images;
    private String regionid;//城市id
    private String countryid;//国家id
    private int currentdoview=0;//当前操作的view
    private File imagefile;
    private PublicPromptDialog publicPromptDialog;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_releasestrate);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new StratePresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        addLayoutView();
        initImagePicker();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.iv_newview:
            case R.id.tv_newview:
                addLayoutView();
                break;

            case R.id.ll_selectdistrict:
                Intent intent = new Intent();
           //     intent.setClass(aty, AddressSelectionActivity.class);
                startActivityForResult(intent, STATUS);
                break;

            case R.id.tv_release:
                submitStrate();

//                if (picturehandler==null)picturehandler=new ReleaseStrateActivity.upPictureHandler();
//                totalnum=itemlist.get(layoutnumber).getSelImageList().size();
//                urlsuccessnum=0;
//                urlerrornum=0;
//                pictureerror="";
//                urls="";
//                upOperation();
                break;
            case R.id.iv_image:
//                ImagePopupWindow imagePopupWindow = new ImagePopupWindow(this, getWindow(), itemlist.get(currentdoview).getItemurl());
//                imagePopupWindow.showAtLocation(ll_allactivity, Gravity.CENTER, 0, 0);
                currentdoview=(Integer) ((View)v.getParent()).getTag();
                PictureDialog();
                break;
        }
    }

    /**
     * 设置标题
     */
    private void initTitle(){
        ActivityTitleUtils.initToolbar(aty, getString(R.string.newStrategy), true, R.id.titlebar);
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

    @Override
    public void onItemClick(View view, int position) {
        layoutnumber=(Integer) ((View)view.getParent()).getTag();
        switch (position) {
            case NumericConstants.IMAGE_ITEM_ADD:
                PictureDialog();
                break;
            default:
                if (view.getId()==R.id.iv_delete){
                    if (itemlist.get(layoutnumber).getSelImageList() != null&&itemlist.get(layoutnumber).getSelImageList().size()>position) {
                        itemlist.get(layoutnumber).getSelImageList().remove(position);
                        itemlist.get(layoutnumber).getIpadapter().setImages(itemlist.get(layoutnumber).getSelImageList());
                        if (itemlist.get(layoutnumber).getSelImageList().size()==0){
                            itemlist.get(layoutnumber).getItemll().setVisibility(View.VISIBLE);
                            itemlist.get(layoutnumber).getItemrv().setVisibility(View.GONE);
                        }else {
                            itemlist.get(layoutnumber).getItemll().setVisibility(View.GONE);
                            itemlist.get(layoutnumber).getItemrv().setVisibility(View.VISIBLE);
                        }
                    }
                }else{
                    //打开预览
                    ImagePopupWindow imagePopupWindow = new ImagePopupWindow(this, getWindow(), itemlist.get(currentdoview).getItemurl());
                    imagePopupWindow.showAtLocation(ll_allactivity, Gravity.CENTER, 0, 0);
//                    if (intentPreview==null)intentPreview = new Intent(this, ImagePreviewDelActivity.class);
//                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) itemlist.get(layoutnumber).getIpadapter().getImages());
//                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
//                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                    startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                }

                break;
        }
    }

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
                    ((StratePresenter)mPresenter).upPictures("file",imagefile,0);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
//            //预览图片返回
//            if (data != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
//                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
//                if (images != null) {
//                    itemlist.get(layoutnumber).getSelImageList().clear();
//                    itemlist.get(layoutnumber).getSelImageList().addAll(images);
//                    itemlist.get(layoutnumber).getIpadapter().setImages(itemlist.get(layoutnumber).getSelImageList());
//                    if (itemlist.get(layoutnumber).getSelImageList().size()==0){
//                        itemlist.get(layoutnumber).getItemll().setVisibility(View.VISIBLE);
//                        itemlist.get(layoutnumber).getItemrv().setVisibility(View.GONE);
//                    }else{
//                        itemlist.get(layoutnumber).getItemll().setVisibility(View.GONE);
//                        itemlist.get(layoutnumber).getItemrv().setVisibility(View.VISIBLE);
//                    }
//                }
//            }
        }else if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
            String selectCity = data.getStringExtra("selectCity");
            regionid = data.getIntExtra("selectCityId", 0)+"";
            String selectCountry = data.getStringExtra("selectCountry");
            countryid = data.getIntExtra("selectCountryId", 0)+"";
            if (!TextUtils.isEmpty(selectCity)&&selectCity.equals(getString(R.string.locateFailure))){
                tv_selectdistrict.setText("");
            }else{
                tv_selectdistrict.setText(selectCountry+"•" +selectCity);
            }

        }
    }

    @Override
    public void setPresenter(StrateContract.Presenter presenter) {
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
//                itemlist.get(layoutnumber).getSelImageList().remove(urlerrornum);
//                itemlist.get(layoutnumber).getIpadapter().setImages(itemlist.get(layoutnumber).getSelImageList());
//            }else{
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
            return;
        }
        if (flag==9){
            dismissLoadingDialog();
            ViewInject.toast(this,msg);
        }else{
//            urlerrornum++;
//            pictureerror=msg;
//            upOperation();
            ViewInject.toast(msg);
            dismissLoadingDialog();
        }

    }
//    /**
//     * 上传图片
//     */
//    private void upOperation(){
//        showLoadingDialog(getString(R.string.crossLoad)+"\n("+urlsuccessnum+"/"+totalnum+")");
//        if (itemlist.get(layoutnumber).getSelImageList()!=null&&itemlist.get(layoutnumber).getSelImageList().size()>0&&itemlist.get(layoutnumber).getSelImageList().size()>urlerrornum){
//            threadpicture=new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i=urlerrornum;i<itemlist.get(layoutnumber).getSelImageList().size();i++){
//                        oldFile = new File(itemlist.get(layoutnumber).getSelImageList().get(i).path);
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
//                        ((StratePresenter)mPresenter).upPictures("file",oldFile,0);
//                        return;
//                    }
//                    picturehandler.sendEmptyMessage(0);
//                }
//            });
//            threadpicture.start();
//
//        }else{
//            dismissLoadingDialog();
//            ((StratePresenter)mPresenter).pulishStrate(urls,et_title.getText().toString(),countryid,regionid,et_profile.getText().toString(),itemlist.get(layoutnumber).getItemet().getText().toString(),9);
//        }
//
//
//    }

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
                }
            };
        }
        publicPromptDialog.show();
        return false;
    }

//    class upPictureHandler extends android.os.Handler{
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            dismissLoadingDialog();
//            ((StratePresenter)mPresenter).pulishStrate(urls,et_title.getText().toString(),countryid,regionid,et_profile.getText().toString(),itemlist.get(layoutnumber).getItemet().getText().toString(),9);
//        }
//    }

    private void addLayoutView(){
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
        EditText et_content=(EditText) itemview.findViewById(R.id.et_content);
        et_content.setVisibility(View.VISIBLE);
        et_content.setMovementMethod(ScrollingMovementMethod.getInstance());

        ReleaseStrateItemBean rsitembean=new ReleaseStrateItemBean();
        rsitembean.setItemview(itemview);
        rsitembean.setItemiv(imageView);
        rsitembean.setRladdimages(rl_addimages);
        rsitembean.setItemet(et_content);
        itemlist.add(layoutnumber,rsitembean);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlideCatchUtil.getInstance().cleanImageDisk();
        GlideCatchUtil.getInstance().cleanCatchDisk();
    }

    private void submitStrate(){
        List<StrateForPCBean> strateForPCBeanList = null;
        if (itemlist!=null&&itemlist.size()>0){
            for (ReleaseStrateItemBean bean:itemlist){
                if (bean!=null){
                    if (TextUtils.isEmpty(bean.getItemurl())||TextUtils.isEmpty(bean.getItemet().getText().toString())){
                        ViewInject.toast(getString(R.string.fillPrompt));
                        return;
                    }else{
                        if (strateForPCBeanList==null)strateForPCBeanList=new ArrayList<>();
                        StrateForPCBean strateForPCBean=new StrateForPCBean();
                        strateForPCBean.setImg(bean.getItemurl());
                        strateForPCBean.setContent(bean.getItemet().getText().toString());
                        strateForPCBeanList.add(strateForPCBean);
                    }
                }
            }
        }
        showLoadingDialog(getString(R.string.submissionLoad));
        ((StratePresenter)mPresenter).pulishStrate(et_title.getText().toString(),countryid,regionid,tv_selectdistrict.getText().toString(),et_profile.getText().toString(),new Gson().toJson(strateForPCBeanList),9);
    }
}

