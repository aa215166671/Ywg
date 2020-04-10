package com.kyun.android.baisibudejie.pro.mine.view.Utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class BitmapCache implements ImageLoader.ImageCache{
    private LruCache<String,Bitmap> mCache;

    public BitmapCache(){
        int maxSize= 10 * 1024 * 1024;
        mCache=new LruCache<String, Bitmap>(maxSize){
            protected  int sizeOf(String key,Bitmap bitmap){
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }
    @Override
    public Bitmap getBitmap(String s) {
        return mCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        mCache.put(s,bitmap);
    }
}
