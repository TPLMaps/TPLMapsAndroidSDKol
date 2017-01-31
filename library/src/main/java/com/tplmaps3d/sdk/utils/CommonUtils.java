package com.tplmaps3d.sdk.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hassanjamil on 2017-01-30.
 *
 * @author hassanjamil
 */

public class CommonUtils {

    private static Toast toast;

    public static void showToastShort(Context context, String text, boolean cancelIfAlreadyShown) {

        if (toast != null && cancelIfAlreadyShown)
            toast.cancel();

        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
