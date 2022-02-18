
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
    "bounds",
    "copyrights",
    "legs",
    "overview_polyline",
    "summary",
    "warnings",
    "waypoint_order"
})
@Generated("jsonschema2pojo")
public class Route {

    @JsonProperty("bounds")
    private Bounds bounds;
    @JsonProperty("copyrights")
    private String copyrights;
    @JsonProperty("legs")
    private List<Leg> legs = new ArrayList<Leg>();
    @JsonProperty("overview_polyline")
    private OverviewPolyline overviewPolyline;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("warnings")
    private List<String> warnings = new ArrayList<String>();
    @JsonProperty("waypoint_order")
    private List<Object> waypointOrder = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bounds")
    public Bounds getBounds() {
        return bounds;
    }

    @JsonProperty("bounds")
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Route withBounds(Bounds bounds) {
        this.bounds = bounds;
        return this;
    }

    @JsonProperty("copyrights")
    public String getCopyrights() {
        return copyrights;
    }

    @JsonProperty("copyrights")
    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public Route withCopyrights(String copyrights) {
        this.copyrights = copyrights;
        return this;
    }

    @JsonProperty("legs")
    public List<Leg> getLegs() {
        return legs;
    }

    @JsonProperty("legs")
    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public Route withLegs(List<Leg> legs) {
        this.legs = legs;
        return this;
    }

    @JsonProperty("overview_polyline")
    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    @JsonProperty("overview_polyline")
    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public Route withOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
        return this;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Route withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    @JsonProperty("warnings")
    public List<String> getWarnings() {
        return warnings;
    }

    @JsonProperty("warnings")
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public Route withWarnings(List<String> warnings) {
        this.warnings = warnings;
        return this;
    }

    @JsonProperty("waypoint_order")
    public List<Object> getWaypointOrder() {
        return waypointOrder;
    }

    @JsonProperty("waypoint_order")
    public void setWaypointOrder(List<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
    }

    public Route withWaypointOrder(List<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
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

    public Route withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Route.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bounds");
        sb.append('=');
        sb.append(((this.bounds == null)?"<null>":this.bounds));
        sb.append(',');
        sb.append("copyrights");
        sb.append('=');
        sb.append(((this.copyrights == null)?"<null>":this.copyrights));
        sb.append(',');
        sb.append("legs");
        sb.append('=');
        sb.append(((this.legs == null)?"<null>":this.legs));
        sb.append(',');
        sb.append("overviewPolyline");
        sb.append('=');
        sb.append(((this.overviewPolyline == null)?"<null>":this.overviewPolyline));
        sb.append(',');
        sb.append("summary");
        sb.append('=');
        sb.append(((this.summary == null)?"<null>":this.summary));
        sb.append(',');
        sb.append("warnings");
        sb.append('=');
        sb.append(((this.warnings == null)?"<null>":this.warnings));
        sb.append(',');
        sb.append("waypointOrder");
        sb.append('=');
        sb.append(((this.waypointOrder == null)?"<null>":this.waypointOrder));
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
        result = ((result* 31)+((this.summary == null)? 0 :this.summary.hashCode()));
        result = ((result* 31)+((this.overviewPolyline == null)? 0 :this.overviewPolyline.hashCode()));
        result = ((result* 31)+((this.copyrights == null)? 0 :this.copyrights.hashCode()));
        result = ((result* 31)+((this.legs == null)? 0 :this.legs.hashCode()));
        result = ((result* 31)+((this.warnings == null)? 0 :this.warnings.hashCode()));
        result = ((result* 31)+((this.bounds == null)? 0 :this.bounds.hashCode()));
        result = ((result* 31)+((this.waypointOrder == null)? 0 :this.waypointOrder.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Route) == false) {
            return false;
        }
        Route rhs = ((Route) other);
        return (((((((((this.summary == rhs.summary)||((this.summary!= null)&&this.summary.equals(rhs.summary)))&&((this.overviewPolyline == rhs.overviewPolyline)||((this.overviewPolyline!= null)&&this.overviewPolyline.equals(rhs.overviewPolyline))))&&((this.copyrights == rhs.copyrights)||((this.copyrights!= null)&&this.copyrights.equals(rhs.copyrights))))&&((this.legs == rhs.legs)||((this.legs!= null)&&this.legs.equals(rhs.legs))))&&((this.warnings == rhs.warnings)||((this.warnings!= null)&&this.warnings.equals(rhs.warnings))))&&((this.bounds == rhs.bounds)||((this.bounds!= null)&&this.bounds.equals(rhs.bounds))))&&((this.waypointOrder == rhs.waypointOrder)||((this.waypointOrder!= null)&&this.waypointOrder.equals(rhs.waypointOrder))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
