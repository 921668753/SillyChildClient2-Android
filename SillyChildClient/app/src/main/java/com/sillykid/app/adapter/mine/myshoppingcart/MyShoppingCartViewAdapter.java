package com.sillykid.app.adapter.mine.myshoppingcart;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.sillykid.app.R;
import com.sillykid.app.entity.mine.myshoppingcart.MyShoppingCartBean.DataBean.StorelistBean.GoodslistBean;
import com.sillykid.app.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.swipeitemlayout.BGASwipeItemLayout;

/**
 * 我的购物车适配器
 */
public class MyShoppingCartViewAdapter extends BGAAdapterViewAdapter<GoodslistBean> {

    /**
     * 当前处于打开状态的item
     */
    private List<BGASwipeItemLayout> mOpenedSil = new ArrayList<>();

    public MyShoppingCartViewAdapter(Context context) {
        super(context, R.layout.item_shopgoods);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        BGASwipeItemLayout swipeItemLayout = viewHolderHelper.getView(R.id.item_myShoppingCart);
        swipeItemLayout.setDelegate(new BGASwipeItemLayout.BGASwipeItemLayoutDelegate() {
            @Override
            public void onBGASwipeItemLayoutOpened(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
                mOpenedSil.add(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutClosed(BGASwipeItemLayout swipeItemLayout) {
                mOpenedSil.remove(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutStartOpen(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
            }
        });
        viewHolderHelper.setItemChildClickListener(R.id.img_checkbox);
        viewHolderHelper.setItemChildClickListener(R.id.tv_delete);
        viewHolderHelper.setItemChildClickListener(R.id.tv_subtract);
        viewHolderHelper.setItemChildClickListener(R.id.tv_add);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, GoodslistBean listBean) {
        BGASwipeItemLayout swipeItemLayout = viewHolderHelper.getView(R.id.item_myShoppingCart);
        if (listBean.getIsEdit() == 0) {
            // viewHolderHelper.setVisibility(R.id.img_checkbox, View.GONE);
            swipeItemLayout.setSwipeAble(true);
        } else {
            swipeItemLayout.setSwipeAble(false);
        }

        if (listBean.getIsSelected() == 0) {
            viewHolderHelper.setImageResource(R.id.img_checkbox, R.mipmap.shopping_cart_unselected);
        } else {
            viewHolderHelper.setImageResource(R.id.img_checkbox, R.mipmap.shopping_cart_selected);
        }
        //  viewHolderHelper.setVisibility(R.id.img_checkbox, View.VISIBLE);


        /**
         *商品图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getImage_default(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);

        /**
         *商品名字
         */
        viewHolderHelper.setText(R.id.tv_goodName, listBean.getName());

        /**
         *商品简介
         */
        viewHolderHelper.setText(R.id.tv_goodSynopsis, listBean.getSpecs());

        /**
         *商品价格
         */
        viewHolderHelper.setText(R.id.tv_money, MathUtil.keepTwo(StringUtils.toDouble(listBean.getPrice())));

        /**
         *商品数量
         */
        viewHolderHelper.setText(R.id.tv_goodNumber, String.valueOf(listBean.getNum()));

    }

    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

    public void closeOpenedSwipeItemLayout() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.close();
        }
        mOpenedSil.clear();
    }
}
