package com.itsubedibesh.walmart.controllers.api.Inventory.DamageAndExpiry;

import com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseMartProducts.WarehouseAndMartProducts;
import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;

import javax.persistence.*;

@Entity
@Table(name = "DamageAndExpiry")
public class DamageAndExpiry extends Audit {

    public DamageAndExpiry() {
    }

    public DamageAndExpiry(WarehouseAndMartProducts productId, DamageAndExpiryEnum damageAndExpiryEnum, Double quantity) {
        this.productId = productId;
        this.damageAndExpiryEnum = damageAndExpiryEnum;
        this.quantity = quantity;
    }

    @Id
    @Column(name = "DamageAndExpiryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade =  CascadeType.DETACH,optional = false)
    @JoinColumn(name = "ProductId",nullable = false)
    private WarehouseAndMartProducts productId;

    @Column(name = "damageAndExpiryType", nullable = false, columnDefinition = "varchar(10) default 'Damage'")
    @Enumerated(EnumType.STRING)
    private DamageAndExpiryEnum damageAndExpiryEnum;

    @Column(name = "Quantity", nullable = false)
    private Double quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WarehouseAndMartProducts getProductId() {
        return productId;
    }

    public void setProductId(WarehouseAndMartProducts productId) {
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
