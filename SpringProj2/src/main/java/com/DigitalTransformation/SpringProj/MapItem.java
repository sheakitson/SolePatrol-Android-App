package com.DigitalTransformation.SpringProj;

/**
 *The main map items class
 *This is used when creating templates for objects which use the routes longitudes and latitudes
 */

public class MapItem {

    private double longitude;
    private double latitude;

    public MapItem() {
    }

    public MapItem(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
