
package com.DigitalTransformation.SpringProj.JSONToJava.pojo;

import java.util.HashMap;
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
    "end_location",
    "html_instructions",
    "polyline",
    "start_location",
    "travel_mode",
    "maneuver"
})
@Generated("jsonschema2pojo")
public class Step {

    @JsonProperty("distance")
    private Distance__1 distance;
    @JsonProperty("duration")
    private Duration__1 duration;
    @JsonProperty("end_location")
    private EndLocation__1 endLocation;
    @JsonProperty("html_instructions")
    private String htmlInstructions;
    @JsonProperty("polyline")
    private Polyline polyline;
    @JsonProperty("start_location")
    private StartLocation__1 startLocation;
    @JsonProperty("travel_mode")
    private String travelMode;
    @JsonProperty("maneuver")
    private String maneuver;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("distance")
    public Distance__1 getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Distance__1 distance) {
        this.distance = distance;
    }

    public Step withDistance(Distance__1 distance) {
        this.distance = distance;
        return this;
    }

    @JsonProperty("duration")
    public Duration__1 getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Duration__1 duration) {
        this.duration = duration;
    }

    public Step withDuration(Duration__1 duration) {
        this.duration = duration;
        return this;
    }

    @JsonProperty("end_location")
    public EndLocation__1 getEndLocation() {
        return endLocation;
    }

    @JsonProperty("end_location")
    public void setEndLocation(EndLocation__1 endLocation) {
        this.endLocation = endLocation;
    }

    public Step withEndLocation(EndLocation__1 endLocation) {
        this.endLocation = endLocation;
        return this;
    }

    @JsonProperty("html_instructions")
    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    @JsonProperty("html_instructions")
    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    public Step withHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
        return this;
    }

    @JsonProperty("polyline")
    public Polyline getPolyline() {
        return polyline;
    }

    @JsonProperty("polyline")
    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public Step withPolyline(Polyline polyline) {
        this.polyline = polyline;
        return this;
    }

    @JsonProperty("start_location")
    public StartLocation__1 getStartLocation() {
        return startLocation;
    }

    @JsonProperty("start_location")
    public void setStartLocation(StartLocation__1 startLocation) {
        this.startLocation = startLocation;
    }

    public Step withStartLocation(StartLocation__1 startLocation) {
        this.startLocation = startLocation;
        return this;
    }

    @JsonProperty("travel_mode")
    public String getTravelMode() {
        return travelMode;
    }

    @JsonProperty("travel_mode")
    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public Step withTravelMode(String travelMode) {
        this.travelMode = travelMode;
        return this;
    }

    @JsonProperty("maneuver")
    public String getManeuver() {
        return maneuver;
    }

    @JsonProperty("maneuver")
    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }

    public Step withManeuver(String maneuver) {
        this.maneuver = maneuver;
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

    public Step withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Step.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("distance");
        sb.append('=');
        sb.append(((this.distance == null)?"<null>":this.distance));
        sb.append(',');
        sb.append("duration");
        sb.append('=');
        sb.append(((this.duration == null)?"<null>":this.duration));
        sb.append(',');
        sb.append("endLocation");
        sb.append('=');
        sb.append(((this.endLocation == null)?"<null>":this.endLocation));
        sb.append(',');
        sb.append("htmlInstructions");
        sb.append('=');
        sb.append(((this.htmlInstructions == null)?"<null>":this.htmlInstructions));
        sb.append(',');
        sb.append("polyline");
        sb.append('=');
        sb.append(((this.polyline == null)?"<null>":this.polyline));
        sb.append(',');
        sb.append("startLocation");
        sb.append('=');
        sb.append(((this.startLocation == null)?"<null>":this.startLocation));
        sb.append(',');
        sb.append("travelMode");
        sb.append('=');
        sb.append(((this.travelMode == null)?"<null>":this.travelMode));
        sb.append(',');
        sb.append("maneuver");
        sb.append('=');
        sb.append(((this.maneuver == null)?"<null>":this.maneuver));
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
        result = ((result* 31)+((this.htmlInstructions == null)? 0 :this.htmlInstructions.hashCode()));
        result = ((result* 31)+((this.distance == null)? 0 :this.distance.hashCode()));
        result = ((result* 31)+((this.startLocation == null)? 0 :this.startLocation.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.endLocation == null)? 0 :this.endLocation.hashCode()));
        result = ((result* 31)+((this.polyline == null)? 0 :this.polyline.hashCode()));
        result = ((result* 31)+((this.maneuver == null)? 0 :this.maneuver.hashCode()));
        result = ((result* 31)+((this.travelMode == null)? 0 :this.travelMode.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Step) == false) {
            return false;
        }
        Step rhs = ((Step) other);
        return ((((((((((this.duration == rhs.duration)||((this.duration!= null)&&this.duration.equals(rhs.duration)))&&((this.htmlInstructions == rhs.htmlInstructions)||((this.htmlInstructions!= null)&&this.htmlInstructions.equals(rhs.htmlInstructions))))&&((this.distance == rhs.distance)||((this.distance!= null)&&this.distance.equals(rhs.distance))))&&((this.startLocation == rhs.startLocation)||((this.startLocation!= null)&&this.startLocation.equals(rhs.startLocation))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.endLocation == rhs.endLocation)||((this.endLocation!= null)&&this.endLocation.equals(rhs.endLocation))))&&((this.polyline == rhs.polyline)||((this.polyline!= null)&&this.polyline.equals(rhs.polyline))))&&((this.maneuver == rhs.maneuver)||((this.maneuver!= null)&&this.maneuver.equals(rhs.maneuver))))&&((this.travelMode == rhs.travelMode)||((this.travelMode!= null)&&this.travelMode.equals(rhs.travelMode))));
    }

}
