package com.tplmaps3d.sdk.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.tplmaps3d.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class created by magma3 on 1/31/2017.
 */

class BitmapDescriptorHelper {

    static final int DEFAULT = 0;
    static final int DEFAULT_COLOR = 1;
    static final int DRAWABLE = 2;
    static final int DRAWABLE_RES = 3;
    static final int ASSETS = 4;
    static final int FILE = 5;
    static final int BITMAP = 6;

    private static BitmapDescriptorHelper instance;
    private BitmapDescriptorHelper() {}

    public static BitmapDescriptorHelper Instance()
    {
        if (instance == null)
            instance = new BitmapDescriptorHelper();
        return instance;
    }

    Bitmap getMarkerIcon(Context context, BitmapDescriptor bitmapDescriptor)
    {
        Bitmap bitmap = null;

        switch (bitmapDescriptor.getDescriptor()) {

            case DEFAULT:
                bitmap = getDefaultDrawableFromVector(context);
                break;

            case DEFAULT_COLOR:
                bitmap = getDefaultDrawableFromVector(context, bitmapDescriptor.getHexColor());
                break;

            case DRAWABLE:
                bitmap = getBitmapFromDrawable(context, bitmapDescriptor.getDrawable());
                break;

            case DRAWABLE_RES:
                bitmap = getBitmapFromDrawableResId(context, bitmapDescriptor.getDrawableResId());
                break;

            case ASSETS:
                bitmap = getBitmapFromAsset(context, bitmapDescriptor.getAssetPath());
                break;
        }

        return bitmap;
    }

    private static Bitmap getDefaultDrawableFromVector(Context context) {
        Drawable drawable = DrawableHelper
                .withContext(context)
                .withColor(Color.parseColor(BitmapDescriptorFactory.DEFAULT))
                .withDrawable(R.drawable.marker_default_icon)
                .tint()
                .get();

        return getBitmapFromVectorDrawable(drawable);
    }

    private static Bitmap getDefaultDrawableFromVector(Context context, String hexColor) {
        Drawable drawable = DrawableHelper
                .withContext(context)
                .withColor(Color.parseColor(hexColor))
                .withDrawable(R.drawable.marker_default_icon)
                .tint()
                .get();

        return getBitmapFromVectorDrawable(drawable);
    }



    private static Bitmap getBitmapFromVectorDrawable(Drawable drawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private static Bitmap getBitmapFromDrawableResId(Context context, int drawableId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId, options);
        return bitmap;
    }

    private static Bitmap getBitmapFromDrawable(Context context, Drawable drawable) {
        int density = context.getResources().getDisplayMetrics().densityDpi;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        bitmapDrawable.setTargetDensity(density);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        bitmap.setDensity(density);
        return bitmap;
    }

    private static Bitmap getBitmapFromAsset(Context context, String path) {
        AssetManager assetManager = context.getAssets();
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            is = assetManager.open(path);
            bitmap = BitmapFactory.decodeStream(is);
        } catch (final IOException e) {
            bitmap = null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
        }
        return bitmap;
    }

}
