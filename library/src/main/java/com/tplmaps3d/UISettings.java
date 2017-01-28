package com.tplmaps3d;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by hassanjamil on 2017-01-24.
 * @author hassanjamil
 */

public class UISettings {

    private MapView mMapView;
    UIController controller;

    UISettings(@NonNull MapView mapView) {
        mMapView = mapView;
        controller = new UIController(getContext(), mMapView);
    }

    Context getContext() {
        return ((mMapView == null) ? null : mMapView.getContext());
    }

    public void showZoomControls(boolean show) {
        if(show)
            controller.loadZoomControls();
        else
            controller.removeZoomControls();
    }

    public void showCompass(boolean show) {
        if(show)
            controller.loadCompass();
        else
            controller.removeCompass();
    }

    public void showMyLocationButton(boolean show) {

    }
}
