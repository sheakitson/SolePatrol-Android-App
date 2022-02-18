package com.DigitalTransformation.SpringProj.traffic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Traffic {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Float Longitude;

    private Float Latitude;

    public Float getLongitude() {
        return Longitude;
    }

    public void setLongitude(Float longitude) {
        Longitude = longitude;
    }

    public Float getLatitude() {
        return Latitude;
    }

    public void setLatitude(Float latitude) {
        Latitude = latitude;
    }

    public int getTrafficRating() {
        return TrafficRating;
    }

    public void setTrafficRating(int lightingRating) {
        TrafficRating = lightingRating;
    }

    private int TrafficRating;

}
