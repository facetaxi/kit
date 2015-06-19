package com.example;

import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class GeoMapActivity extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.geomap);

        //
        final MapView mMapView = (MapView) findViewById(R.id.mapView);

// Получаем MapController
        MapController mMapController = mMapView.getController();

// Перемещаем карту на заданные координаты
        double latitude=45;
        double longitude  =45;
        GeoPoint point = new GeoPoint(
                (int)(latitude * 1e6),
                (int)(longitude * 1e6)
        );
        mMapController.animateTo(point);
        //mMapController.animateTo(new GeoPoint(60.113337, 55.151317));

        mMapController.setZoom(15);
        //
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}