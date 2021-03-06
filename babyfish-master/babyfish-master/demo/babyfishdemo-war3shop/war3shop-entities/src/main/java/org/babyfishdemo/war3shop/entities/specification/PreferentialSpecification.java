package org.babyfishdemo.war3shop.entities.specification;

import java.util.Collection;
import java.util.Date;

import org.babyfishdemo.war3shop.entities.PreferentialActionType;
import org.babyfishdemo.war3shop.entities.PreferentialThresholdType;

/**
 * @author Tao Chen
 */
public class PreferentialSpecification {
    
    private Boolean active;
    
    private Date minDate;
    
    private Date maxDate;
    
    private Collection<PreferentialThresholdType> thresholdTypes;
    
    private Collection<PreferentialActionType> actionTypes;
    
    private Collection<Long> includedProductIds;
    
    private Collection<Long> excludedProductIds;
    
    private Boolean hasGiftProducts;
    
    private Collection<Long> includedGiftProductIds;
    
    private Collection<Long> excludedGiftProductIds;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Collection<PreferentialThresholdType> getThresholdTypes() {
        return thresholdTypes;
    }

    public void setThresholdTypes(Collection<PreferentialThresholdType> thresholdTypes) {
        this.thresholdTypes = thresholdTypes;
    }

    public Collection<PreferentialActionType> getActionTypes() {
        return actionTypes;
    }

    public void setActionTypes(Collection<PreferentialActionType> actionTypes) {
        this.actionTypes = actionTypes;
    }

    public Collection<Long> getIncludedProductIds() {
        return includedProductIds;
    }

    public void setIncludedProductIds(Collection<Long> includedProductIds) {
        this.includedProductIds = includedProductIds;
    }

    public Collection<Long> getExcludedProductIds() {
        return excludedProductIds;
    }

    public void setExcludedProductIds(Collection<Long> excludedProductIds) {
        this.excludedProductIds = excludedProductIds;
    }

    public Boolean getHasGiftProducts() {
        return hasGiftProducts;
    }

    public void setHasGiftProducts(Boolean hasGiftProducts) {
        this.hasGiftProducts = hasGiftProducts;
    }

    public Collection<Long> getIncludedGiftProductIds() {
        return includedGiftProductIds;
    }

    public void setIncludedGiftProductIds(Collection<Long> includedGiftProductIds) {
        this.includedGiftProductIds = includedGiftProductIds;
    }

    public Collection<Long> getExcludedGiftProductIds() {
        return excludedGiftProductIds;
    }

    public void setExcludedGiftProductIds(Collection<Long> excludedGiftProductIds) {
        this.excludedGiftProductIds = excludedGiftProductIds;
    }
}
