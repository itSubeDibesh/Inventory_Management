package com.itsubedibesh.walmart.controllers.api.Inventory.WarehouseAndMarts.WarehouseAndMart;

import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;

import javax.persistence.*;

@Entity
@Table(name = "WareHouseAndMart")
public class WareHouseAndMart extends Audit {

    public WareHouseAndMart() {
    }

    public WareHouseAndMart(String name, WarehouseMartTypeSelection type, String address, String contact, String description) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.contact = contact;
        this.description = description;
    }

    @Id
    @Column(name = "WORMId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "InventoryName",nullable = false, unique = true,columnDefinition = "varchar(200)")
    private String name;

    @Column(name = "InventoryType", nullable = false, columnDefinition = "varchar(15) default 'Warehouse'")
    @Enumerated(EnumType.STRING)
    private WarehouseMartTypeSelection type;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Contact", nullable = false)
    private String contact;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WarehouseMartTypeSelection getType() {
        return type;
    }

    public void setType(WarehouseMartTypeSelection type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
