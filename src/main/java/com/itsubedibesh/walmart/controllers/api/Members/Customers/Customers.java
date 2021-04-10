package com.itsubedibesh.walmart.controllers.api.Members.Customers;

import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;

import javax.persistence.*;

@Entity
@Table(name = "Customers")
public class Customers extends Audit {

    public Customers() {
    }

    public Customers(String tpin, String fullName, String address, String contactNumber) {
        this.tpin = tpin;
        this.fullName = fullName;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    @Id
    @Column(name="CustomerId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "TPIN",unique = true,columnDefinition = "varchar(20)")
    private String tpin;
    @Column(name = "FullName",nullable = false,columnDefinition = "varchar(100)")
    private String fullName;
    @Column(name = "Address",columnDefinition = "varchar(100)")
    private String address;
    @Column(name = "ContactNumber",unique = true,columnDefinition = "varchar(20)")
    private String contactNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTpin() {
        return tpin;
    }

    public void setTpin(String tpin) {
        this.tpin = tpin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
