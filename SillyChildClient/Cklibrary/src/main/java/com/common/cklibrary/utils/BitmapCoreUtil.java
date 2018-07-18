package com.common.cklibrary.utils;

import android.graphics.Bitmap;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.kymjs.common.FileUtils;
import com.nanchen.compresshelper.CompressHelper;

import java.io.File;

//import com.kymjs.core.bitmap.client.BitmapCore;

/**
 * Created by Administrator on 2017/3/9.
 */

public class BitmapCoreUtil {

//    private static BitmapCore.Builder builder = new BitmapCore.Builder();
//
//    /**
//     * 使用并行模式访问本地图片(默认为串行)
//     */
//    public static void setImgBitmap(View view, String url, int loadResId, int errorResId, int w, int h) {
//        builder.url(url)
//                .view(view)
//                .loadResId(loadResId)
//                .errorResId(errorResId)
//                .size(w, h)
//                //并行访问本地图片
//                .useAsyncLoadDiskImage(true)
//                .doTask();
//
//    }
//
//
//    public static void setImgBitmap(View view, String url, int errorResId, int w, int h) {
//        builder.url(url)
//                .view(view)
//                .errorResId(errorResId)
//                .size(w, h)
//                //并行访问本地图片
//                .useAsyncLoadDiskImage(true)
//                .doTask();
//    }
//
//    public static void setImgBitmap(View view, String url, int w, int h) {
//        builder.url(url)
//                .view(view)
//                .size(w, h)
//                //并行访问本地图片
//                .useAsyncLoadDiskImage(true)
//                .doTask();
//    }
//
//    public static void setImgBitmap(View view, String url) {
//        builder.url(url)
//                .view(view)
//                //并行访问本地图片
//                .useAsyncLoadDiskImage(true)
//                .doTask();
//    }
//
//    /**
//     * 取消一个请求
//     *
//     * @param url
//     */
//    public static void cancelBitmap(String url) {
//        BitmapCore.cancle(url);
//    }

    /**
     * @param oldFile 自定义压缩
     */
    public static File customCompression(File oldFile) {
        String yourFileName = "IMG" + System.currentTimeMillis();
        String destinationDirectoryPath = FileUtils.getSaveFolder(StringConstants.PHOTOPATH).getAbsolutePath();
        // 你也可以自定义压缩
        File newFile = new CompressHelper.Builder(KJActivityStack.create().topActivity())
//                .setMaxWidth(720)  // 默认最大宽度为720
//                .setMaxHeight(960) // 默认最大高度为960
                .setQuality(80)    // 默认压缩质量为80
                .setCompressFormat(Bitmap.CompressFormat.PNG) // 设置默认压缩为jpg格式
                .setFileName(yourFileName) // 设置你的文件名
                .setDestinationDirectoryPath(destinationDirectoryPath)
                .build()
                .compressToFile(oldFile);
        return newFile;
    }

    /**
     * @param oldFilePath 默认压缩
     */
    public static File defaultCompression(String oldFilePath) {
        // 默认的压缩方法，多张图片只需要直接加入循环即可
        File newFile = CompressHelper.getDefault(KJActivityStack.create().topActivity()).compressToFile(new File(oldFilePath));
        return newFile;
    }

}
