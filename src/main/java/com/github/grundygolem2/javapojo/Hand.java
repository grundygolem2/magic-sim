
package com.github.grundygolem2.javapojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Hand
 * <p>
 * A Magic-Sim hand definition
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "containsCards",
    "excludesCards",
    "containsTags",
    "excludesTags",
    "maxKeepSize"
})
public class Hand {

    /**
     * Array of card names that should be included in the hand
     * Duplicates will be required multiple times
     * Cards specified here will not be considered for containsTags checking
     * 
     */
    @JsonProperty("containsCards")
    @JsonPropertyDescription("Array of card names that should be included in the hand\nDuplicates will be required multiple times\nCards specified here will not be considered for containsTags checking")
    private List<String> containsCards = new ArrayList<String>();
    /**
     * Array of card names that must be missing in the hand
     * 
     */
    @JsonProperty("excludesCards")
    @JsonPropertyDescription("Array of card names that must be missing in the hand")
    private List<String> excludesCards = new ArrayList<String>();
    /**
     * Array of tags that must be included in the hand
     * Duplicates will be required multiple times
     * Does not consider cards specified in containsCards
     * 
     */
    @JsonProperty("containsTags")
    @JsonPropertyDescription("Array of tags that must be included in the hand\nDuplicates will be required multiple times\nDoes not consider cards specified in containsCards")
    private List<String> containsTags = new ArrayList<String>();
    /**
     * Array of tags that must not be included on any cards in the hand
     * 
     */
    @JsonProperty("excludesTags")
    @JsonPropertyDescription("Array of tags that must not be included on any cards in the hand")
    private List<String> excludesTags = new ArrayList<String>();
    /**
     * Hands that satisfy this set of rules are only kept if they're smaller than this number
     * If a hand satisfied multiple, the largest is chosen
     * (Required)
     * 
     */
    @JsonProperty("maxKeepSize")
    @JsonPropertyDescription("Hands that satisfy this set of rules are only kept if they're smaller than this number\nIf a hand satisfied multiple, the largest is chosen")
    private Integer maxKeepSize;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Array of card names that should be included in the hand
     * Duplicates will be required multiple times
     * Cards specified here will not be considered for containsTags checking
     * 
     */
    @JsonProperty("containsCards")
    public List<String> getContainsCards() {
        return containsCards;
    }

    /**
     * Array of card names that should be included in the hand
     * Duplicates will be required multiple times
     * Cards specified here will not be considered for containsTags checking
     * 
     */
    @JsonProperty("containsCards")
    public void setContainsCards(List<String> containsCards) {
        this.containsCards = containsCards;
    }

    /**
     * Array of card names that must be missing in the hand
     * 
     */
    @JsonProperty("excludesCards")
    public List<String> getExcludesCards() {
        return excludesCards;
    }

    /**
     * Array of card names that must be missing in the hand
     * 
     */
    @JsonProperty("excludesCards")
    public void setExcludesCards(List<String> excludesCards) {
        this.excludesCards = excludesCards;
    }

    /**
     * Array of tags that must be included in the hand
     * Duplicates will be required multiple times
     * Does not consider cards specified in containsCards
     * 
     */
    @JsonProperty("containsTags")
    public List<String> getContainsTags() {
        return containsTags;
    }

    /**
     * Array of tags that must be included in the hand
     * Duplicates will be required multiple times
     * Does not consider cards specified in containsCards
     * 
     */
    @JsonProperty("containsTags")
    public void setContainsTags(List<String> containsTags) {
        this.containsTags = containsTags;
    }

    /**
     * Array of tags that must not be included on any cards in the hand
     * 
     */
    @JsonProperty("excludesTags")
    public List<String> getExcludesTags() {
        return excludesTags;
    }

    /**
     * Array of tags that must not be included on any cards in the hand
     * 
     */
    @JsonProperty("excludesTags")
    public void setExcludesTags(List<String> excludesTags) {
        this.excludesTags = excludesTags;
    }

    /**
     * Hands that satisfy this set of rules are only kept if they're smaller than this number
     * If a hand satisfied multiple, the largest is chosen
     * (Required)
     * 
     */
    @JsonProperty("maxKeepSize")
    public Integer getMaxKeepSize() {
        return maxKeepSize;
    }

    /**
     * Hands that satisfy this set of rules are only kept if they're smaller than this number
     * If a hand satisfied multiple, the largest is chosen
     * (Required)
     * 
     */
    @JsonProperty("maxKeepSize")
    public void setMaxKeepSize(Integer maxKeepSize) {
        this.maxKeepSize = maxKeepSize;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("containsCards", containsCards).append("excludesCards", excludesCards).append("containsTags", containsTags).append("excludesTags", excludesTags).append("maxKeepSize", maxKeepSize).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(containsCards).append(containsTags).append(excludesCards).append(maxKeepSize).append(additionalProperties).append(excludesTags).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Hand) == false) {
            return false;
        }
        Hand rhs = ((Hand) other);
        return new EqualsBuilder().append(containsCards, rhs.containsCards).append(containsTags, rhs.containsTags).append(excludesCards, rhs.excludesCards).append(maxKeepSize, rhs.maxKeepSize).append(additionalProperties, rhs.additionalProperties).append(excludesTags, rhs.excludesTags).isEquals();
    }

}
