package com.sillykid.app.mine.myorder.orderevaluation;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhong.ratingbar.RatingBar;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.GlideCatchUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.sillykid.app.R;
import com.sillykid.app.adapter.ImagePickerAdapter;
import com.sillykid.app.constant.NumericConstants;
import com.sillykid.app.dialog.ImagePopupWindow;
import com.sillykid.app.dialog.PublicPromptDialog;
import com.sillykid.app.entity.UploadImageBean;
import com.sillykid.app.loginregister.LoginActivity;
import com.sillykid.app.mine.myorder.goodorder.orderdetails.GoodOrderDetailsActivity;
import com.sillykid.app.utils.GlideImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */

public class PostEvaluationActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener,PostEvaluationContract.View{

    private PostEvaluationContract.Presenter mPresenter;

    @BindView(id=R.id.ll_allactivity)
    private LinearLayout ll_allactivity;

    @BindView(id=R.id.ll_guideScoring)
    private LinearLayout ll_guideScoring;
    @BindView(id=R.id.ll_chooseanonymous_guide ,click = true)
    private LinearLayout ll_chooseanonymous_guide;
    @BindView(id=R.id.iv_chooseanonymous_guide )
    private ImageView iv_chooseanonymous_guide;
    @BindView(id=R.id.rb_rating_guide)
    private RatingBar rb_rating_guide;
    @BindView(id=R.id.et_content_guide)
    private EditText et_content_guide;
    @BindView(id=R.id.recyclerview_guide)
    private RecyclerView recyclerview_guide;

    @BindView(id=R.id.ll_scoring)
    private LinearLayout ll_scoring;
    @BindView(id=R.id.rb_rating)
    private RatingBar rb_rating;
    @BindView(id=R.id.recyclerView)
    private RecyclerView recyclerView;
    @BindView(id=R.id.ll_chooseanonymous ,click = true)
    private LinearLayout ll_chooseanonymous;
    @BindView(id=R.id.iv_chooseanonymous )
    private ImageView iv_chooseanonymous;

    @BindView(id=R.id.tv_cancel ,click = true)
    private TextView tv_cancel;
    @BindView(id=R.id.et_content )
    private TextView et_content;

    @BindView(id=R.id.tv_publish ,click = true)
    private EditText tv_publish;

    private ArrayList<ImageItem> selImageList;//当前选择的所有图片
    private ArrayList<ImageItem> selImageListguide;//司导当前选择的所有图片
    private ImagePickerAdapter adapter;
    private ImagePickerAdapter adapterguide;
    private ArrayList<ImageItem> images = null;
    private int anonymous=0;//0:不匿名。1：匿名
    private int anonymousguide=0;//0:不匿名。1：匿名
    private String orderid;
    private List<String> urllist;//图片网址
    private String urls="";//图片网址
    private List<String> urllistguide;
    private String urlsguide="";//图片网址
    private Thread threadpicture;
    private UploadImageBean uploadimagebean;
    private int urlsuccessnum=0;//上传图片成功的数量
//    private int urlerrornum=0;//上传图片失败的数量
    private long fileSize;//每次压缩前的大小
    private long fileSizeNew;//每次压缩后的大小
    private File oldFile;
    private int totalnum=0;//图片总数
//    private upPictureHandler picturehandler;
    private String pictureerror;
    private int type=0;//订单类型
    private boolean isguide;//是不是在对司导进行评分，用于区分图片选择
    private File imagefile;
    private PublicPromptDialog publicPromptDialog;
    private String seller_id;
    private String line_id;


    @Override
    public void setRootView() {
       setContentView(R.layout.activity_postevaluation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter=new PostEvaluationPresenter(this);
        orderid=getIntent().getStringExtra("air_id");
        type=getIntent().getIntExtra("type",0);
        if (type==3){
            line_id=getIntent().getStringExtra("line_id");
            seller_id=getIntent().getStringExtra("seller_id");
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        initImagePicker();
//        ll_guideScoring.setVisibility(View.GONE);
//        if(type==3){
//            ll_guideScoring.setVisibility(View.VISIBLE);
//            tv_scoringtype.setText(getString(R.string.routesScoring));
//            selImageListguide = new ArrayList<>();
//            adapterguide = new ImagePickerAdapter(this, selImageListguide, NumericConstants.MAXPICTURE,R.mipmap.mine_xiangjiaddxxx);
//            adapterguide.setOnItemClickListener(this);
//            GridLayoutManager gridLayoutManagerguide=new GridLayoutManager(this, 4);
//            recyclerview_guide.setLayoutManager(gridLayoutManagerguide);
//            recyclerview_guide.setHasFixedSize(true);
//            recyclerview_guide.setAdapter(adapterguide);
//            urllistguide=new ArrayList<>();
//        }
        if(type!=4&&type!=5){//单次接送&私人定制
            ll_scoring.setVisibility(View.VISIBLE);
        }
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, NumericConstants.MAXPICTURE,R.mipmap.mine_xiangjiaddxxx);
        adapter.setOnItemClickListener(this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        urllist=new ArrayList<>();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.ll_chooseanonymous:
                if (anonymous==0){
                    anonymous=1;
                    iv_chooseanonymous.setImageResource(R.mipmap.mineaddress_selectxxx);
                }else{
                    anonymous=0;
                    iv_chooseanonymous.setImageResource(R.mipmap.mineaddress_unselectxxx);
                }
                break;
            case R.id.ll_chooseanonymous_guide:
                if (anonymousguide==0){
                    anonymousguide=1;
                    iv_chooseanonymous_guide.setImageResource(R.mipmap.mineaddress_selectxxx);
                }else{
                    anonymousguide=0;
                    iv_chooseanonymous_guide.setImageResource(R.mipmap.mineaddress_unselectxxx);
                }
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_publish:
//                if (picturehandler==null)picturehandler=new upPictureHandler();
//                totalnum=selImageList.size();
//                urlsuccessnum=0;
//                urlerrornum=0;
//                pictureerror="";
//                upOperation();

                if ((rb_rating.getStar()==0&&type!=4&&type!=5)||rb_rating_guide.getStar()==0){
                    if (publicPromptDialog ==null){
                        publicPromptDialog =new PublicPromptDialog(aty) {
                            @Override
                            public void doAction() {

                            }
                        };
                    }
                    publicPromptDialog.show();
                    publicPromptDialog.setContent(getString(R.string.scoreRemind));
                    publicPromptDialog.setBtnContent(getString(R.string.confirm));
                }else{
                    pullEvaluation();
                }
                break;
        }

    }

    /**
     * 设置标题
     */
    private void initTitle(){
        ActivityTitleUtils.initToolbar(aty, getString(R.string.postEvaluation), true, R.id.titlebar);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (((View)view.getParent()).getId()==R.id.recyclerview_guide||((View)((View)((View)view.getParent()).getParent()).getParent()).getId()==R.id.recyclerview_guide){
            isguide=true;
        }else{
            isguide=false;
        }
        switch (position) {
            case NumericConstants.IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                Intent intent1 = new Intent(PostEvaluationActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                startActivityForResult(intent1, NumericConstants.REQUEST_CODE_SELECT);
                break;
            default:
                if (view.getId()==R.id.iv_delete){
                    if (isguide){
                        if (selImageListguide != null&&selImageListguide.size()>position) {
                            selImageListguide.remove(position);
                            urllistguide.remove(position);
                            adapterguide.setImages(selImageListguide);
                        }
                    }else{
                        if (selImageList != null&&selImageList.size()>position) {
                            selImageList.remove(position);
                            urllist.remove(position);
                            adapter.setImages(selImageList);
                        }
                    }
                }else{
                    //打开预览
                    if (isguide){
                        ImagePopupWindow imagePopupWindow = new ImagePopupWindow(this, getWindow(), urllistguide.get(position));
                        imagePopupWindow.showAtLocation(ll_allactivity, Gravity.CENTER, 0, 0);
                    }else{
                        ImagePopupWindow imagePopupWindow = new ImagePopupWindow(this, getWindow(), urllist.get(position));
                        imagePopupWindow.showAtLocation(ll_allactivity, Gravity.CENTER, 0, 0);
                    }

//                    Intent intentPreview = new Intent(PostEvaluationActivity.this, ImagePreviewDelActivity.class);
//                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
//                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
//                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                    startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                }

                break;
        }
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader=new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(NumericConstants.MAXPICTURE);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
//        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusHeight(400);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
//        imagePicker.setOutPutY(500);                         //保存文件的高度。单位像素
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == NumericConstants.REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images!=null){
                    imagefile=new File(images.get(0).path);
                    imagefile = BitmapCoreUtil.customCompression(imagefile);
                    showLoadingDialog(getString(R.string.crossLoad));
                    mPresenter.upPictures("file",imagefile,0);
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
//            mPresenter.postEvaluation(orderid,rb_rating.getStar(),urls,anonymous,et_content.getText().toString(),9);
//        }
//
//
//    }

    @Override
    public void setPresenter(PostEvaluationContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag==9){
            dismissLoadingDialog();
//            if (urlerrornum==0){
                ViewInject.toast(getString(R.string.successfulReviews));
                KJActivityStack.create().finishActivity(GoodOrderDetailsActivity.class);
                finish();
//            }
        }else{
            GlideCatchUtil.getInstance().cleanImageDisk();
            uploadimagebean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
            if (uploadimagebean!=null&&uploadimagebean.getData()!=null&&uploadimagebean.getData().getFile()!=null&&!TextUtils.isEmpty(uploadimagebean.getData().getFile().getUrl())) {
                if(isguide){
                    urllistguide.add(uploadimagebean.getData().getFile().getUrl());
                    selImageListguide.addAll(images);
                    adapterguide.setImages(selImageListguide);
                }else{
                    urllist.add(uploadimagebean.getData().getFile().getUrl());
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
                dismissLoadingDialog();
            }else{
                ViewInject.toast("图片上传失败！");
                dismissLoadingDialog();
            }

//
//            if (uploadimagebean!=null&&uploadimagebean.getData()!=null&&uploadimagebean.getData().getFile()!=null&&!TextUtils.isEmpty(uploadimagebean.getData().getFile().getUrl())){
//                urls+="|"+uploadimagebean.getData().getFile().getUrl();
//                urlsuccessnum++;
//                images.remove(urlerrornum);
//                selImageList.remove(urlerrornum);
//                adapter.setImages(selImageList);
//            }else{
//                images.remove(urlerrornum);
//                selImageList.remove(urlerrornum);
//                adapter.setImages(selImageList);
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
        if (flag==9){
            dismissLoadingDialog();
            ViewInject.toast(this,msg);
        }
//        else{
//            urlerrornum++;
//            pictureerror=msg;
////            upOperation();
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selImageList=null;
        images=null;
        adapter=null;
        GlideCatchUtil.getInstance().cleanImageDisk();
        GlideCatchUtil.getInstance().cleanCatchDisk();
    }

    /**
     * 上传评价
     */
    private void pullEvaluation(){
//        if(type==3){
//            urlsguide="";
//            if (urllistguide.size()>0){
//                for (String sguide:urllistguide){
//                    if (!TextUtils.isEmpty(sguide)) {
//                        urlsguide += "|" + sguide;
//                    }
//                }
//            }
//            urls="";
//            if (urllist.size()>0){
//                for (String s:urllist){
//                    if (!TextUtils.isEmpty(s)) {
//                        urls += "|" + s;
//                    }
//                }
//            }
//            showLoadingDialog(getString(R.string.submissionLoad));
//            mPresenter.postEvaluationRoute(orderid,seller_id,rb_rating_guide.getStar()+"",et_content_guide.getText().toString(),urlsguide,anonymousguide+"",line_id,rb_rating.getStar()+"",et_content.getText().toString(),urls,anonymous+"",9);
//        }else{
            urls="";
            if (urllist.size()>0){
                for (String s:urllist){
                    if (!TextUtils.isEmpty(s)) {
                        urls += "|" + s;
                    }
                }
            }
            showLoadingDialog(getString(R.string.submissionLoad));
            mPresenter.postEvaluation(orderid,type,rb_rating_guide.getStar(),rb_rating.getStar(),urls,anonymous,et_content.getText().toString(),9);

//        }
    }

//    class upPictureHandler extends android.os.Handler{
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            dismissLoadingDialog();
//            mPresenter.postEvaluation(orderid,rb_rating.getStar(),urls,anonymous,et_content.getText().toString(),9);
//        }
//    }
}
