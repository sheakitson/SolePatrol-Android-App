
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
/**
 Generated from the class: @package com.DigitalTransformation.SpringProj.JSONToJava;
 Based on the link: https://www.jsonschema2pojo.org/
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "category",
    "location_type",
    "location",
    "context",
    "outcome_status",
    "persistent_id",
    "id",
    "location_subtype",
    "month"
})
@Generated("jsonschema2pojo")
public class Crime {

    @JsonProperty("category")
    private String category;
    @JsonProperty("location_type")
    private String locationType;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("context")
    private String context;
    @JsonProperty("outcome_status")
    private Object outcomeStatus;
    @JsonProperty("persistent_id")
    private String persistentId;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("location_subtype")
    private String locationSubtype;
    @JsonProperty("month")
    private String month;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    public Crime withCategory(String category) {
        this.category = category;
        return this;
    }

    @JsonProperty("location_type")
    public String getLocationType() {
        return locationType;
    }

    @JsonProperty("location_type")
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Crime withLocationType(String locationType) {
        this.locationType = locationType;
        return this;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public Crime withLocation(Location location) {
        this.location = location;
        return this;
    }

    @JsonProperty("context")
    public String getContext() {
        return context;
    }

    @JsonProperty("context")
    public void setContext(String context) {
        this.context = context;
    }

    public Crime withContext(String context) {
        this.context = context;
        return this;
    }

    @JsonProperty("outcome_status")
    public Object getOutcomeStatus() {
        return outcomeStatus;
    }

    @JsonProperty("outcome_status")
    public void setOutcomeStatus(Object outcomeStatus) {
        this.outcomeStatus = outcomeStatus;
    }

    public Crime withOutcomeStatus(Object outcomeStatus) {
        this.outcomeStatus = outcomeStatus;
        return this;
    }

    @JsonProperty("persistent_id")
    public String getPersistentId() {
        return persistentId;
    }

    @JsonProperty("persistent_id")
    public void setPersistentId(String persistentId) {
        this.persistentId = persistentId;
    }

    public Crime withPersistentId(String persistentId) {
        this.persistentId = persistentId;
        return this;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Crime withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("location_subtype")
    public String getLocationSubtype() {
        return locationSubtype;
    }

    @JsonProperty("location_subtype")
    public void setLocationSubtype(String locationSubtype) {
        this.locationSubtype = locationSubtype;
    }

    public Crime withLocationSubtype(String locationSubtype) {
        this.locationSubtype = locationSubtype;
        return this;
    }

    @JsonProperty("month")
    public String getMonth() {
        return month;
    }

    @JsonProperty("month")
    public void setMonth(String month) {
        this.month = month;
    }

    public Crime withMonth(String month) {
        this.month = month;
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

    public Crime withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Crime.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("category");
        sb.append('=');
        sb.append(((this.category == null)?"<null>":this.category));
        sb.append(',');
        sb.append("locationType");
        sb.append('=');
        sb.append(((this.locationType == null)?"<null>":this.locationType));
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(((this.location == null)?"<null>":this.location));
        sb.append(',');
        sb.append("context");
        sb.append('=');
        sb.append(((this.context == null)?"<null>":this.context));
        sb.append(',');
        sb.append("outcomeStatus");
        sb.append('=');
        sb.append(((this.outcomeStatus == null)?"<null>":this.outcomeStatus));
        sb.append(',');
        sb.append("persistentId");
        sb.append('=');
        sb.append(((this.persistentId == null)?"<null>":this.persistentId));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("locationSubtype");
        sb.append('=');
        sb.append(((this.locationSubtype == null)?"<null>":this.locationSubtype));
        sb.append(',');
        sb.append("month");
        sb.append('=');
        sb.append(((this.month == null)?"<null>":this.month));
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
        result = ((result* 31)+((this.persistentId == null)? 0 :this.persistentId.hashCode()));
        result = ((result* 31)+((this.month == null)? 0 :this.month.hashCode()));
        result = ((result* 31)+((this.context == null)? 0 :this.context.hashCode()));
        result = ((result* 31)+((this.outcomeStatus == null)? 0 :this.outcomeStatus.hashCode()));
        result = ((result* 31)+((this.locationType == null)? 0 :this.locationType.hashCode()));
        result = ((result* 31)+((this.locationSubtype == null)? 0 :this.locationSubtype.hashCode()));
        result = ((result* 31)+((this.location == null)? 0 :this.location.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.category == null)? 0 :this.category.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Crime) == false) {
            return false;
        }
        Crime rhs = ((Crime) other);
        return (((((((((((this.persistentId == rhs.persistentId)||((this.persistentId!= null)&&this.persistentId.equals(rhs.persistentId)))&&((this.month == rhs.month)||((this.month!= null)&&this.month.equals(rhs.month))))&&((this.context == rhs.context)||((this.context!= null)&&this.context.equals(rhs.context))))&&((this.outcomeStatus == rhs.outcomeStatus)||((this.outcomeStatus!= null)&&this.outcomeStatus.equals(rhs.outcomeStatus))))&&((this.locationType == rhs.locationType)||((this.locationType!= null)&&this.locationType.equals(rhs.locationType))))&&((this.locationSubtype == rhs.locationSubtype)||((this.locationSubtype!= null)&&this.locationSubtype.equals(rhs.locationSubtype))))&&((this.location == rhs.location)||((this.location!= null)&&this.location.equals(rhs.location))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.category == rhs.category)||((this.category!= null)&&this.category.equals(rhs.category))));
    }

}
