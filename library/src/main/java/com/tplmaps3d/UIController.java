package com.tplmaps3d;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by hassanjamil on 2017-01-25.
 * @author hassanjamil
 */

class UIController extends UIViewHelper {

    //private static final String TAG = UIController.class.getSimpleName();

    private MapView mMapView;

    private RelativeLayout vParentControls;

    UIController(@NonNull Context context, @NonNull MapView mapView) {
        super(context);

        mMapView = mapView;
    }

    private void loadUIControlsRootView() {
        if(mMapView.findViewById(R.id.ui_parent) == null ||  vParentControls == null) {
            vParentControls = getViewControlsParent();
            mMapView.addView(vParentControls);
        }
    }

    private void removeUIControlsRootView() {
        if(mMapView.findViewById(R.id.ui_parent) != null ||  vParentControls != null) {
            mMapView.removeView(vParentControls);
            vParentControls = null;
        }
    }




    /*
     * ZOOM CONTROLS
     */

    void loadZoomControls() {

        loadUIControlsRootView();

        if(vParentControls == null || mMapView == null)
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

        if(vParentControls == null || mMapView == null)
            return;

        vParentControls.removeView(getViewZoomControls());
    }




    /*
     * COMPASS
     */

    void loadCompass() {

        loadUIControlsRootView();

        if(vParentControls == null || mMapView == null)
            return;

        removeCompass();

        vParentControls.addView(getViewCompass());

        mMapView.invalidate();
    }

    void removeCompass() {

        if(vParentControls == null || mMapView == null)
            return;

        vParentControls.removeView(getViewCompass());
    }
}