package com.sillykid.app.adapter.homepage.airporttransportation.comments;

import android.content.Context;
import android.widget.ImageView;

import com.lzy.imagepicker.bean.ImageItem;
import com.sillykid.app.R;
import com.sillykid.app.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Admin on 2017/9/29.
 */

public class CharterCommentsGridViewAdapter extends BGAAdapterViewAdapter<ImageItem> {


    public CharterCommentsGridViewAdapter(Context context) {
        super(context, R.layout.item_imgcomment);
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ImageItem model) {

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model.path, (ImageView) helper.getView(R.id.img_comment), R.mipmap.placeholderfigure);
    }


}
