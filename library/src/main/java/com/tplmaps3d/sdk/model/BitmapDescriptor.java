package com.tplmaps3d.sdk.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Class created by magma3 on 1/31/2017.
 */

class BitmapDescriptor {

    int descriptor;

    String hexColor;
    String assetPath;
    String filePath;
    int drawableResId;
    Drawable drawable;
    Bitmap bitmap;

    public BitmapDescriptor(int descriptor) {
        this.descriptor = descriptor;
    }

    public BitmapDescriptor setDescriptor(int descriptor) {
        this.descriptor = descriptor;
        return this;
    }

    public BitmapDescriptor setHexColor(String hexColor) {
        this.hexColor = hexColor;
        return this;
    }

    public BitmapDescriptor setAssetPath(String assetPath) {
        this.assetPath = assetPath;
        return this;
    }

    public BitmapDescriptor setDrawableResId(int drawableResId) {
        this.drawableResId = drawableResId;
        return this;
    }

    public BitmapDescriptor setDrawable(Drawable drawable) {
        this.drawable = drawable;
        return this;
    }

    public BitmapDescriptor setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }

    public BitmapDescriptor setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public int getDescriptor() {
        return descriptor;
    }

    public String getHexColor() {
        return hexColor;
    }

    public String getAssetPath() {
        return assetPath;
    }

    public int getDrawableResId() {
        return drawableResId;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getFilePath() {
        return filePath;
    }
}
