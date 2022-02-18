
package com.DigitalTransformation.SpringProj.JSONToJava.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "distance",
    "duration",
    "end_address",
    "end_location",
    "start_address",
    "start_location",
    "steps",
    "traffic_speed_entry",
    "via_waypoint"
})
@Generated("jsonschema2pojo")
public class Leg {

    @JsonProperty("distance")
    private Distance distance;
    @JsonProperty("duration")
    private Duration duration;
    @JsonProperty("end_address")
    private String endAddress;
    @JsonProperty("end_location")
    private EndLocation endLocation;
    @JsonProperty("start_address")
    private String startAddress;
    @JsonProperty("start_location")
    private StartLocation startLocation;
    @JsonProperty("steps")
    private List<Step> steps = new ArrayList<Step>();
    @JsonProperty("traffic_speed_entry")
    private List<Object> trafficSpeedEntry = new ArrayList<Object>();
    @JsonProperty("via_waypoint")
    private List<Object> viaWaypoint = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("distance")
    public Distance getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Leg withDistance(Distance distance) {
        this.distance = distance;
        return this;
    }

    @JsonProperty("duration")
    public Duration getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Leg withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    @JsonProperty("end_address")
    public String getEndAddress() {
        return endAddress;
    }

    @JsonProperty("end_address")
    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public Leg withEndAddress(String endAddress) {
        this.endAddress = endAddress;
        return this;
    }

    @JsonProperty("end_location")
    public EndLocation getEndLocation() {
        return endLocation;
    }

    @JsonProperty("end_location")
    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    public Leg withEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
        return this;
    }

    @JsonProperty("start_address")
    public String getStartAddress() {
        return startAddress;
    }

    @JsonProperty("start_address")
    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public Leg withStartAddress(String startAddress) {
        this.startAddress = startAddress;
        return this;
    }

    @JsonProperty("start_location")
    public StartLocation getStartLocation() {
        return startLocation;
    }

    @JsonProperty("start_location")
    public void setStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    public Leg withStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
        return this;
    }

    @JsonProperty("steps")
    public List<Step> getSteps() {
        return steps;
    }

    @JsonProperty("steps")
    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Leg withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    @JsonProperty("traffic_speed_entry")
    public List<Object> getTrafficSpeedEntry() {
        return trafficSpeedEntry;
    }

    @JsonProperty("traffic_speed_entry")
    public void setTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
        this.trafficSpeedEntry = trafficSpeedEntry;
    }

    public Leg withTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
        this.trafficSpeedEntry = trafficSpeedEntry;
        return this;
    }

    @JsonProperty("via_waypoint")
    public List<Object> getViaWaypoint() {
        return viaWaypoint;
    }

    @JsonProperty("via_waypoint")
    public void setViaWaypoint(List<Object> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
    }

    public Leg withViaWaypoint(List<Object> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Leg withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Leg.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("distance");
        sb.append('=');
        sb.append(((this.distance == null)?"<null>":this.distance));
        sb.append(',');
        sb.append("duration");
        sb.append('=');
        sb.append(((this.duration == null)?"<null>":this.duration));
        sb.append(',');
        sb.append("endAddress");
        sb.append('=');
        sb.append(((this.endAddress == null)?"<null>":this.endAddress));
        sb.append(',');
        sb.append("endLocation");
        sb.append('=');
        sb.append(((this.endLocation == null)?"<null>":this.endLocation));
        sb.append(',');
        sb.append("startAddress");
        sb.append('=');
        sb.append(((this.startAddress == null)?"<null>":this.startAddress));
        sb.append(',');
        sb.append("startLocation");
        sb.append('=');
        sb.append(((this.startLocation == null)?"<null>":this.startLocation));
        sb.append(',');
        sb.append("steps");
        sb.append('=');
        sb.append(((this.steps == null)?"<null>":this.steps));
        sb.append(',');
        sb.append("trafficSpeedEntry");
        sb.append('=');
        sb.append(((this.trafficSpeedEntry == null)?"<null>":this.trafficSpeedEntry));
        sb.append(',');
        sb.append("viaWaypoint");
        sb.append('=');
        sb.append(((this.viaWaypoint == null)?"<null>":this.viaWaypoint));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.duration == null)? 0 :this.duration.hashCode()));
        result = ((result* 31)+((this.distance == null)? 0 :this.distance.hashCode()));
        result = ((result* 31)+((this.startLocation == null)? 0 :this.startLocation.hashCode()));
        result = ((result* 31)+((this.startAddress == null)? 0 :this.startAddress.hashCode()));
        result = ((result* 31)+((this.trafficSpeedEntry == null)? 0 :this.trafficSpeedEntry.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.viaWaypoint == null)? 0 :this.viaWaypoint.hashCode()));
        result = ((result* 31)+((this.steps == null)? 0 :this.steps.hashCode()));
        result = ((result* 31)+((this.endAddress == null)? 0 :this.endAddress.hashCode()));
        result = ((result* 31)+((this.endLocation == null)? 0 :this.endLocation.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Leg) == false) {
            return false;
        }
        Leg rhs = ((Leg) other);
        return (((((((((((this.duration == rhs.duration)||((this.duration!= null)&&this.duration.equals(rhs.duration)))&&((this.distance == rhs.distance)||((this.distance!= null)&&this.distance.equals(rhs.distance))))&&((this.startLocation == rhs.startLocation)||((this.startLocation!= null)&&this.startLocation.equals(rhs.startLocation))))&&((this.startAddress == rhs.startAddress)||((this.startAddress!= null)&&this.startAddress.equals(rhs.startAddress))))&&((this.trafficSpeedEntry == rhs.trafficSpeedEntry)||((this.trafficSpeedEntry!= null)&&this.trafficSpeedEntry.equals(rhs.trafficSpeedEntry))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.viaWaypoint == rhs.viaWaypoint)||((this.viaWaypoint!= null)&&this.viaWaypoint.equals(rhs.viaWaypoint))))&&((this.steps == rhs.steps)||((this.steps!= null)&&this.steps.equals(rhs.steps))))&&((this.endAddress == rhs.endAddress)||((this.endAddress!= null)&&this.endAddress.equals(rhs.endAddress))))&&((this.endLocation == rhs.endLocation)||((this.endLocation!= null)&&this.endLocation.equals(rhs.endLocation))));
    }

}
