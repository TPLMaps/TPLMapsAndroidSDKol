package com.tplmaps3d.geometry;

import com.tplmaps3d.LngLat;

import java.util.Map;

/**
 * {@code Point} is a single LngLat and its properties.
 * <p>
 * Users do not need to use this class.
 */
public class Point extends Geometry {

    public Point(LngLat point, Map<String, String> properties) {
        this.coordinates = new double[2];
        coordinates[0] = point.longitude;
        coordinates[1] = point.latitude;
        if (properties != null) {
            this.properties = getStringMapAsArray(properties);
        }

    }
}
