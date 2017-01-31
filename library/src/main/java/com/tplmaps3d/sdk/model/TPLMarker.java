package com.tplmaps3d.sdk.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.tplmaps3d.LngLat;
import com.tplmaps3d.MapController;
import com.tplmaps3d.Marker;

/**
 * Class created by magma3 on 1/27/2017.
 */

public class TPLMarker {

    private final Context context;
    private final MapController mapController;

    private int zIndex;
    private String title;
    private String snippet;
    private Marker marker;
    private LngLat position;

    public TPLMarker(Context context, MapController mapController, com.tplmaps3d.Marker marker, TPLMarkerOptions tplMarkerOptions) {
        this.context = context;
        this.mapController = mapController;
        this.marker = marker;
        setPointOptions(tplMarkerOptions);
    }

    public void remove() {
        mapController.removeMarker(marker);
    }

    public long getId() {
        return marker.getMarkerId();
    }

    public void setPosition(@NonNull LngLat position) {
        if(position == null) {
            throw new IllegalArgumentException("lngLat cannot be null - a position is required.");
        } else {
            this.position = position;
            marker.setPoint(position);
        }
    }

    public LngLat getPosition() {
        return position;
    }

    public void setZIndex(int zIndex) {
        marker.setDrawOrder(zIndex);
        this.zIndex = zIndex;
    }

    public int getZIndex() {
        return this.zIndex;
    }

    public void setIcon(@Nullable BitmapDescriptor bitmap) {
        marker.setBitmap(BitmapDescriptorHelper.Instance().getMarkerIcon(context, bitmap));
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSnippet(@Nullable String snippet) {
        this.snippet = snippet;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setVisible(boolean visible) {
        marker.setVisible(visible);
    }

    public boolean isVisible() {
        return marker.isVisible();
    }

    public void setRotation(float rotation) {
        marker.setRotation(rotation);
    }

    public float getRotation() {
        return marker.getRotation();
    }

    private void setPointOptions(TPLMarkerOptions tplMarkerOptions) {

        if(tplMarkerOptions.getPosition() != null)
            setPosition(tplMarkerOptions.getPosition());

        if(tplMarkerOptions.getIcon() != null)
            marker.setBitmap(BitmapDescriptorHelper.Instance().getMarkerIcon(context, tplMarkerOptions.getIcon()));

        if(tplMarkerOptions.getTitle() != null && !tplMarkerOptions.getTitle().isEmpty())
            setTitle(tplMarkerOptions.getTitle());

        if(tplMarkerOptions.getSnippet() != null && !tplMarkerOptions.getSnippet().isEmpty())
            setSnippet(tplMarkerOptions.getSnippet());
    }
}
