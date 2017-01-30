package com.tplmaps3d;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.RelativeLayout;

import com.tplmaps3d.sdk.utils.CommonUtils;
import com.tplmaps3d.sdk.widgets.Compass;

/**
 * Created by hassanjamil on 2017-01-25.
 *
 * @author hassanjamil
 */

class UIController extends UIViewHelper {

    //private static final String TAG = UIController.class.getSimpleName();

    private Context mContext;

    private MapView mMapView;

    private RelativeLayout vParentControls;


    UIController(@NonNull Context context, @NonNull MapView mapView) {
        super(context);

        mContext = context;

        mMapView = mapView;
    }

    private void loadUIControlsRootView() {
        if (mMapView.findViewById(R.id.ui_parent) == null || vParentControls == null) {
            vParentControls = getViewControlsParent();
            mMapView.addView(vParentControls);
        }
    }

    void removeUIControlsRootView() {
        if (mMapView.findViewById(R.id.ui_parent) != null || vParentControls != null) {
            mMapView.removeView(vParentControls);
            vParentControls = null;
        }
    }




    /*
     * ZOOM CONTROLS
     */

    void loadZoomControls() {

        loadUIControlsRootView();

        if (vParentControls == null || mMapView == null)
            return;

        removeZoomControls();

        vParentControls.addView(getViewZoomControls());

        vParentControls.findViewById(R.id.ui_button_zoom_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMapView.getMapController().setZoomEased(mMapView.getMapController().getZoom() + 1.f, 600);
            }
        });

        vParentControls.findViewById(R.id.ui_button_zoom_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMapView.getMapController().setZoomEased(mMapView.getMapController().getZoom() - 1.f, 600);
            }
        });

        mMapView.invalidate();
    }

    void removeZoomControls() {

        if (vParentControls == null || mMapView == null)
            return;

        vParentControls.removeView(getViewZoomControls());
    }




    /*
     * COMPASS
     */

    void loadCompass() {

        loadUIControlsRootView();

        if (vParentControls == null || mMapView == null)
            return;

        removeCompass();

        final Compass compass = getViewCompass();

        compass.setOrientation((int) Math.toDegrees(mMapView.getMapController().getRotation()));

        compass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compass.setOrientation(0);
                mMapView.getMapController().setRotationEased(0, 750);
            }
        });

        vParentControls.addView(compass);

        mMapView.invalidate();
    }

    /**
     * Set orientation of {@code Compass} between 0 - 360 degrees
     *
     * @param degrees value in degree unit
     */
    void setCompassOrientation(int degrees) {
        degrees = Math.abs(degrees);

        if (degrees < 0 && degrees > 360)
            return;

        Compass compass = (Compass) vParentControls.findViewById(R.id.ui_button_compass);

        if (compass != null)
            compass.setOrientation(degrees);
    }

    void removeCompass() {

        if (vParentControls == null || mMapView == null)
            return;

        vParentControls.removeView(getViewCompass());
    }





    /**
     * MY LOCATION
     */

    void loadMyLocationControl() {

        loadUIControlsRootView();

        if (vParentControls == null || mMapView == null)
            return;

        removeMyLocationControl();

        final FloatingActionButton btnMyLocation = getViewMyLocation();

        btnMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.showToastShort(mContext, "My Location Button is Clicked", true);
            }
        });

        vParentControls.addView(btnMyLocation);

        mMapView.invalidate();
    }

    void removeMyLocationControl() {

        if (vParentControls == null || mMapView == null)
            return;

        vParentControls.removeView(getViewMyLocation());
    }

}