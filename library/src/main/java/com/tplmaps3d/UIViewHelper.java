package com.tplmaps3d;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tplmaps3d.sdk.widgets.Compass;


/**
 * Created by hassanjamil on 2017-01-25.
 *
 * @author hassanjamil
 */

abstract class UIViewHelper {

    //private static final String TAG = UIViewHelper.class.getSimpleName();

    private Context mContext;
    private RelativeLayout vParent;
    private LinearLayout vZoomControlsParent;
    private Compass compass;
    private FloatingActionButton btnMyLocation;

    private int marginControls;

    UIViewHelper(@NonNull Context context) {
        this.mContext = context;

        marginControls = (int) mContext.getResources().getDimension(R.dimen.margin_ui_controls_right);
    }

    RelativeLayout getViewControlsParent() {
        if (vParent != null)
            return vParent;

        try {
            vParent = new RelativeLayout(mContext);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            //layoutParams.setMargins(0, 0, marginControls, 0);
            layoutParams.gravity = Gravity.END;
            vParent.setLayoutParams(layoutParams);
            vParent.setId(R.id.ui_parent);


            return vParent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    LinearLayout getViewZoomControls() {

        if (vZoomControlsParent != null)
            return vZoomControlsParent;

        try {
            // Zoom Controls Parent View
            vZoomControlsParent = new LinearLayout(mContext);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            layoutParams.setMargins(0, marginControls, marginControls, 0);
            vZoomControlsParent.setLayoutParams(layoutParams);
            vZoomControlsParent.setOrientation(LinearLayout.VERTICAL);
            vZoomControlsParent.setId(R.id.ui_parent_zoom_controls);

            // Zoom In
            ImageView btnZoomIn = new ImageView(mContext);
            btnZoomIn.setImageResource(R.drawable.zoom_in);
            btnZoomIn.setId(R.id.ui_button_zoom_in);
            vZoomControlsParent.addView(btnZoomIn);

            // Zoom Out
            ImageView btnZoomOut = new ImageView(mContext);
            btnZoomOut.setImageResource(R.drawable.zoom_out);
            btnZoomOut.setId(R.id.ui_button_zoom_out);
            LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            llParams.setMargins(0, (int) mContext.getResources().getDimension(R.dimen.dp_5), 0, 0);
            btnZoomOut.setLayoutParams(llParams);
            vZoomControlsParent.addView(btnZoomOut);

            return vZoomControlsParent;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    Compass getViewCompass() {

        if (compass != null)
            return compass;

        try {
            compass = new Compass(mContext);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            int margin = (int) mContext.getResources().getDimension(R.dimen.margin_ui_controls_right);
            layoutParams.setMargins(0, margin, margin, 0);
            compass.setLayoutParams(layoutParams);
            compass.setId(R.id.ui_button_compass);

            return compass;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    FloatingActionButton getViewMyLocation() {

        if (btnMyLocation != null)
            return btnMyLocation;

        try {
            /*btnMyLocation = new FloatingActionButton(mContext);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            //layoutParams.setMargins(0, marginControls, marginControls, 0);
            btnMyLocation.setLayoutParams(layoutParams);
            btnMyLocation.setId(R.id.ui_button_compass);
            btnMyLocation.setImageResource(R.drawable.locator);
            btnMyLocation.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));*/


            btnMyLocation = (FloatingActionButton) ((LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.layout_my_location_button, null);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            //layoutParams.setMargins(0, marginControls, marginControls, 0);
            btnMyLocation.setLayoutParams(layoutParams);

            return btnMyLocation;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
