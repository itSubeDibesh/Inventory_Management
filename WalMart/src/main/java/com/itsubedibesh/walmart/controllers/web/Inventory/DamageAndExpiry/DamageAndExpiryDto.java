package com.itsubedibesh.walmart.controllers.web.Inventory.DamageAndExpiry;

import com.itsubedibesh.walmart.controllers.api.Inventory.DamageAndExpiry.DamageAndExpiryEnum;

public class DamageAndExpiryDto {
    public DamageAndExpiryDto() {
    }

    public DamageAndExpiryDto(long productId, DamageAndExpiryEnum damageAndExpiryEnum, Double quantity) {
        this.productId = productId;
        this.damageAndExpiryEnum = damageAndExpiryEnum;
        this.quantity = quantity;
    }

    private long id;
    private long productId;
    private DamageAndExpiryEnum damageAndExpiryEnum;
    private Double quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public DamageAndExpiryEnum getDamageAndExpiryEnum() {
        return damageAndExpiryEnum;
    }

    public void setDamageAndExpiryEnum(DamageAndExpiryEnum damageAndExpiryEnum) {
        this.damageAndExpiryEnum = damageAndExpiryEnum;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
