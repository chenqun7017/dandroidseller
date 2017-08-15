package com.kejiang.canyin.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by chenqun on 2017/8/1.
 * 构造方法中实例化一个LruCache对象，用于存放图片
 */

public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mCache;

    public BitmapCache() {
        int maxMemorySize = 1024 * 1024 * 10;
        mCache = new LruCache<String, Bitmap>(maxMemorySize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String key) {
        return mCache.get(key);
    }

    @Override
    public void putBitmap(String key, Bitmap bitmap) {
        mCache.put(key, bitmap);
    }
}