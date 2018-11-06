
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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Deck
 * <p>
 * A Magic-Sim deck definition
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cardCounts"
})
public class Deck {

    @JsonProperty("cardCounts")
    private List<CardCount> cardCounts = new ArrayList<CardCount>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cardCounts")
    public List<CardCount> getCardCounts() {
        return cardCounts;
    }

    @JsonProperty("cardCounts")
    public void setCardCounts(List<CardCount> cardCounts) {
        this.cardCounts = cardCounts;
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
        return new ToStringBuilder(this).append("cardCounts", cardCounts).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cardCounts).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Deck) == false) {
            return false;
        }
        Deck rhs = ((Deck) other);
        return new EqualsBuilder().append(cardCounts, rhs.cardCounts).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
