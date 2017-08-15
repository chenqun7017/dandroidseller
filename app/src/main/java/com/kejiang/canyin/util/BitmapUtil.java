package com.kejiang.canyin.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 处理图片工具类
 * Created by chenqun on 2017/7/24.
 */
public class BitmapUtil {

    /**
     * 获取缩略图
     */
    public static Bitmap getLitleBitmap(Bitmap bitmap) {
        // Bitmap litleBitmap=
        if (bitmap.getWidth() >= 100 && bitmap.getHeight() >= 100) {
            /* 获取原图的中心点 */
            int centerX = bitmap.getWidth() / 2;
            int centerY = bitmap.getHeight() / 2;
            /* 获得小图的起点 */
            int litleStartX = centerX - 50;
            int litleStartY = centerY - 50;
			/* 创建小图 */
            Bitmap litleBitmap = Bitmap.createBitmap(bitmap, litleStartX, litleStartY, 100, 100);
            return litleBitmap;
        } else {
            return bitmap;
        }
    }

    /**
     * 压缩图片 默认是px
     *
     * @param bm
     * @param newWidth  希望的宽度
     * @param newHeight 希望的高度
     */
    public static Bitmap scaleImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        matrix.postRotate(0);
        return Bitmap.createScaledBitmap(bm, newWidth, newHeight, false);
    }

    /**
     * 创建一个图片选择器
     *
     * @param normalState  普通状态的图片
     * @param pressedState 按压状态的图片
     */
    public static StateListDrawable drawableSelector(Drawable normalState, Drawable pressedState) {
        StateListDrawable bg = new StateListDrawable();
        bg.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressedState);
        bg.addState(new int[]{android.R.attr.state_enabled}, normalState);
        bg.addState(new int[]{}, normalState);
        return bg;
    }
    /**
     *
     * @param imgPath
     * @param bitmap
     * @param imgFormat 图片格式
     * @return
     */
    public static String imgToBase64(String imgPath, Bitmap bitmap,String imgFormat) {
        if (imgPath !=null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
        }
        if(bitmap == null){
            //bitmap not found!!
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static Bitmap readBitmap(String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }

    }








}