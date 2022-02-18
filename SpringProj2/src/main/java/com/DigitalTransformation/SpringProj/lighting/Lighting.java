package com.DigitalTransformation.SpringProj.lighting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Lighting {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Double Longitude;

    private Double Latitude;

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public int getLightingRating() {
        return LightingRating;
    }

    public void setLightingRating(int lightingRating) {
        LightingRating = lightingRating;
    }

    private int LightingRating;

}
