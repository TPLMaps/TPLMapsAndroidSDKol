package com.tplmaps3d.sdk.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Class created by magma3 on 1/31/2017.
 */

public class BitmapDescriptorFactory {

    public static final String DEFAULT  = "#136734";
    public static final String RED      = "#ff0000";
    public static final String ORANGE   = "#ff4500";
    public static final String YELLOW   = "#ffff00";
    public static final String GREEN    = "#00ff00";

    private static Context context;

    private BitmapDescriptorFactory() {
    }

    public static void init(Context mContext) {
        if(context == null)
            context = mContext;
    }

    public static BitmapDescriptor fromResource(int resId) {
        return new BitmapDescriptor(BitmapDescriptorHelper.DRAWABLE_RES).setDrawableResId(resId);
    }

    public static BitmapDescriptor fromDrawable(Drawable drawable) {
        return new BitmapDescriptor(BitmapDescriptorHelper.DRAWABLE).setDrawable(drawable);
    }

    public static BitmapDescriptor fromAsset(String assetPath) {
        return new BitmapDescriptor(BitmapDescriptorHelper.ASSETS).setAssetPath(assetPath);
    }

    public static BitmapDescriptor fromFile(String filePath) {
        Drawable drawable = Drawable.createFromPath(filePath);
        return new BitmapDescriptor(BitmapDescriptorHelper.FILE).setDrawable(drawable);
    }

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        return new BitmapDescriptor(BitmapDescriptorHelper.BITMAP).setBitmap(bitmap);
    }

    public static BitmapDescriptor defaultMarker() {
        return new BitmapDescriptor(BitmapDescriptorHelper.DEFAULT).setHexColor(DEFAULT);
    }

    public static BitmapDescriptor defaultMarker(String hexColor) {
        return new BitmapDescriptor(BitmapDescriptorHelper.DEFAULT_COLOR).setHexColor(hexColor);
    }
}
