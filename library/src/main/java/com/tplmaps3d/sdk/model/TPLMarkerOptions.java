package com.tplmaps3d.sdk.model;

import com.tplmaps3d.LngLat;

/**
 * Class created by magma3 on 1/27/2017.
 */

public class TPLMarkerOptions {


    private LngLat position;
    private int zIndex;
    private BitmapDescriptor bitmap;
    private String title;
    private String snippet;
    private boolean visible;
    private float rotation;

    public TPLMarkerOptions() {
        icon(BitmapDescriptorFactory.defaultMarker());
    }

    public TPLMarkerOptions(LngLat position, int zIndex, BitmapDescriptor bitmap, String title, String snippet, boolean visible, float rotation) {
        this.position = position;
        this.zIndex = zIndex;
        this.bitmap = bitmap;
        this.title = title;
        this.snippet = snippet;
        this.visible = visible;
        this.rotation = rotation;
    }

    public TPLMarkerOptions position(LngLat position) {
        this.position = position;
        return this;
    }

    public TPLMarkerOptions zIndex(int zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public TPLMarkerOptions icon(BitmapDescriptor bitmap) {
        this.bitmap = bitmap;
        return this;
    }

    public TPLMarkerOptions title(String title) {
        this.title = title;
        return this;

    }

    public TPLMarkerOptions snippet(String snippet) {
        this.snippet = snippet;
        return this;

    }

    public TPLMarkerOptions visible(boolean visible) {
        this.visible = visible;
        return this;

    }

    public TPLMarkerOptions rotation(float rotation) {
        this.rotation = rotation;
        return this;

    }

    public LngLat getPosition() {
        return position;
    }

    public int getzIndex() {
        return zIndex;
    }

    public BitmapDescriptor getIcon() {
        return bitmap;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    public boolean isVisible() {
        return visible;
    }

    public float getRotation() {
        return rotation;
    }
}
