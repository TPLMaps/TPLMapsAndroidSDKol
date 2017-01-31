package com.tplmaps3d;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.tplmaps3d.sdk.model.BitmapDescriptorFactory;
import com.tplmaps3d.sdk.model.TPLMarker;
import com.tplmaps3d.sdk.model.TPLMarkerOptions;

import java.util.List;

/**
 * {@code MapView} is a View for displaying a map.
 */
public class MapView extends FrameLayout implements TouchInput.TapResponder {

    protected GLSurfaceView glSurfaceView;
    protected MapController mapController;
    protected AsyncTask<Void, Void, Boolean> getMapTask;

    public MapView(Context context) {

        super(context);

        configureGLSurfaceView();

        initializeSDKDefaults();
    }

    public MapView(Context context, AttributeSet attrs) {

        super(context, attrs);

        configureGLSurfaceView();

        initializeSDKDefaults();
    }

    /**
     * Interface for receiving a {@code MapController} once it is ready to be used
     */
    public interface OnMapReadyCallback {

        /**
         * Called when the map is ready to be used
         *
         * @param mapController A non-null {@code MapController} instance for this {@code MapView}
         */
        void onMapReady(MapController mapController);

    }

    /**
     * Construct a {@code MapController} asynchronously; may only be called from the UI thread
     *
     * @param callback      The object to receive the resulting MapController in a callback;
     *                      the callback will be made on the UI thread
     * @param sceneFilePath Location of the YAML scene file within the asset bundle
     */
    private void getMapAsync(@NonNull final OnMapReadyCallback callback,
                             @NonNull final String sceneFilePath) {

        getMapAsync(callback, sceneFilePath, null);
    }

    /**
     * Construct a {@code MapController} asynchronously; may only be called from the UI thread
     *
     * @param callback      The object to receive the resulting MapController in a callback;
     *                      the callback will be made on the UI thread
     * @param sceneFilePath Location of the YAML scene file within the asset bundle
     * @param sceneUpdates  List of SceneUpdate to be applied when loading this scene
     */
    private void getMapAsync(@NonNull final OnMapReadyCallback callback,
                             @NonNull final String sceneFilePath,
                             final List<SceneUpdate> sceneUpdates) {

        disposeTask();

        final MapController mapInstance = getMapInstance();

        getMapTask = new AsyncTask<Void, Void, Boolean>() {

            @Override
            @SuppressWarnings("WrongThread")
            protected Boolean doInBackground(Void... params) {
                mapInstance.init();
                mapInstance.loadSceneFile(sceneFilePath, sceneUpdates);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean ok) {
                addView(glSurfaceView);
                disposeMap();
                mapController = mapInstance;
                mapController.setMapView(MapView.this);
                callback.onMapReady(mapController);

                // Defaults for sdk initialization
                mapController.setTapResponder(MapView.this);
            }

            @Override
            protected void onCancelled(Boolean ok) {
                mapInstance.dispose();
            }

        }.execute();

    }

    protected MapController getMapInstance() {
        return MapController.getInstance(glSurfaceView);
    }

    protected void configureGLSurfaceView() {

        glSurfaceView = new GLSurfaceView(getContext());
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setPreserveEGLContextOnPause(true);
        glSurfaceView.setEGLConfigChooser(new ConfigChooser(8, 8, 8, 0, 16, 0));

    }

    protected void disposeTask() {

        if (getMapTask != null) {
            // MapController is being initialized, so we'll dispose it in the onCancelled callback.
            getMapTask.cancel(true);
        }
        getMapTask = null;

    }

    protected void disposeMap() {

        if (mapController != null) {
            // MapController has been initialized, so we'll dispose it now.
            mapController.dispose();
        }
        mapController = null;

    }

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    public void onCreate(Bundle savedInstanceState) {

    }

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    public void onResume() {

    }

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    public void onPause() {

    }

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    public void onDestroy() {

        disposeTask();
        disposeMap();
        disposeMapController();

    }

    /**
     * You must call this method from the parent Activity/Fragment's corresponding method.
     */
    public void onLowMemory() {

    }


    //////////////////////////////////////// SDK Work - START //////////////////////////////////////

    private void initializeSDKDefaults() {
        BitmapDescriptorFactory.init(getContext());

    }

    public MapController getMapController() {
        return mapController;
    }

    public void disposeMapController() {
        if (mapController != null)
            mapController.onDestroy();
    }

    public void loadMapAsync(@NonNull final OnMapReadyCallback callback) {
        getMapAsync(callback, getSceneFile());
    }

    public void loadMapAsync(@NonNull final OnMapReadyCallback callback, final List<SceneUpdate> sceneUpdates) {
        getMapAsync(callback, getSceneFile(), sceneUpdates);
    }

    private String getSceneFile() {
        // if Android API Level < 23 then building texture rendering issue will occur thats why 2 different
        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) ? "tplscene50.yaml" : "tplscene.yaml";
    }

    /*
     * Markers
     */

    public final TPLMarker addMarker(TPLMarkerOptions TPLMarkerOptions) {
        Marker marker = mapController.addMarker();
        marker.setStyling("{ style: 'points', interactive: true, color: 'white', size: [50px, 50px], order: 2000, collide: false }");
        return mapController.addPoint(marker, TPLMarkerOptions);
    }

    public final void setOnMarkerClickListener(final OnMarkerClickListener markerClickListener) {
        if(markerClickListener == null) {
            mapController.setMarkerPickListener(null);
        } else {
            mapController.setMarkerPickListener(new MapController.MarkerPickListener() {
                public void onMarkerPick(final MarkerPickResult markerPickResult, float positionX, float positionY) {
                    if (markerPickResult == null || markerPickResult.getMarker() == null)
                        return;

                    final long markerId = markerPickResult.getMarker().getMarkerId();
                    final TPLMarker tplMarker = mapController.pointById(markerId);
                    ((Activity) getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LngLat lngLat = tplMarker.getPosition();
                            mapController.setPositionEased(lngLat, 500);
                            markerClickListener.onMarkerClick(mapController.pointById(markerId)); }
                    });
                }
            });
        }
    }

    @Override
    public boolean onSingleTapUp(float x, float y) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(float x, float y) {
        mapController.pickFeature(x, y);
        mapController.pickLabel(x, y);
        mapController.pickMarker(x, y);
        return false;
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(TPLMarker TPLMarker);
    }


    //////////////////////////////////////// SDK Work - END ////////////////////////////////////////

}
