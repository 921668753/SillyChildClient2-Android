package com.sillykid.app.adapter.homepage.goodslist.evaluation;

import android.content.Context;
import android.widget.ImageView;

import com.lzy.imagepicker.bean.ImageItem;
import com.sillykid.app.R;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by Admin on 2017/9/29.
 */

public class CommentsGridViewAdapter extends BGAAdapterViewAdapter<ImageItem> {

    //  private LayoutParams layoutParams;

    public CommentsGridViewAdapter(Context context) {
        super(context, R.layout.item_imgcomment);
//        int widthpixels = context.getResources().getDisplayMetrics().widthPixels;
//        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, (widthpixels - DisplayUtil.dip2px(context, (15 * 5))) / 4);
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ImageItem model) {

        //  helper.getView(R.id.img_vehicles).setLayoutParams(layoutParams);
        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, model.path, (ImageView) helper.getView(R.id.img_comment), R.mipmap.placeholderfigure);

    }


}
