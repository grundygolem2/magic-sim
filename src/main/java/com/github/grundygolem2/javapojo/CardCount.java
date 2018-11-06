
package com.github.grundygolem2.javapojo;

import java.util.HashMap;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "quantity"
})
public class CardCount {

    /**
     * WotC-issued name of the card - must map to a defined card in the Cards file
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("WotC-issued name of the card - must map to a defined card in the Cards file")
    private String name;
    /**
     * Quantity of the card in the deck; non-negative integers only
     * (Required)
     * 
     */
    @JsonProperty("quantity")
    @JsonPropertyDescription("Quantity of the card in the deck; non-negative integers only")
    private Integer quantity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * WotC-issued name of the card - must map to a defined card in the Cards file
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * WotC-issued name of the card - must map to a defined card in the Cards file
     * (Required)
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Quantity of the card in the deck; non-negative integers only
     * (Required)
     * 
     */
    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Quantity of the card in the deck; non-negative integers only
     * (Required)
     * 
     */
    @JsonProperty("quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        return new ToStringBuilder(this).append("name", name).append("quantity", quantity).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(quantity).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CardCount) == false) {
            return false;
        }
        CardCount rhs = ((CardCount) other);
        return new EqualsBuilder().append(name, rhs.name).append(quantity, rhs.quantity).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
