package com.sillykid.app.entity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lzy.imagepicker.bean.ImageItem;
import com.sillykid.app.adapter.ImagePickerAdapter;

import java.util.ArrayList;

/**
 * 发布攻略  动态布局对象
 * Created by Administrator on 2017/9/26.
 */

public class ReleaseStrateItemBean {

    private RecyclerView itemrv;
    private EditText itemet;
    private LinearLayout itemll;
    private ArrayList<ImageItem> selImageList;
    private ArrayList<ImageItem> images;
    private ImagePickerAdapter ipadapter;
    private ImageView itemiv;
    private String itemurl;
    private RelativeLayout rladdimages;
    private LinearLayout llimage;
    private View itemview;

    public RecyclerView getItemrv() {
        return itemrv;
    }

    public void setItemrv(RecyclerView itemrv) {
        this.itemrv = itemrv;
    }

    public EditText getItemet() {
        return itemet;
    }

    public void setItemet(EditText itemet) {
        this.itemet = itemet;
    }

    public LinearLayout getItemll() {
        return itemll;
    }

    public void setItemll(LinearLayout itemll) {
        this.itemll = itemll;
    }

    public ArrayList<ImageItem> getSelImageList() {
        return selImageList;
    }

    public void setSelImageList(ArrayList<ImageItem> selImageList) {
        this.selImageList = selImageList;
    }

    public ArrayList<ImageItem> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageItem> images) {
        this.images = images;
    }

    public ImagePickerAdapter getIpadapter() {
        return ipadapter;
    }

    public void setIpadapter(ImagePickerAdapter ipadapter) {
        this.ipadapter = ipadapter;
    }

    public ImageView getItemiv() {
        return itemiv;
    }

    public void setItemiv(ImageView itemiv) {
        this.itemiv = itemiv;
    }

    public String getItemurl() {
        return itemurl;
    }

    public void setItemurl(String itemurl) {
        this.itemurl = itemurl;
    }

    public RelativeLayout getRladdimages() {
        return rladdimages;
    }

    public void setRladdimages(RelativeLayout rladdimages) {
        this.rladdimages = rladdimages;
    }

    public LinearLayout getLlimage() {
        return llimage;
    }

    public void setLlimage(LinearLayout llimage) {
        this.llimage = llimage;
    }

    public View getItemview() {
        return itemview;
    }

    public void setItemview(View itemview) {
        this.itemview = itemview;
    }
}
