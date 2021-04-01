package com.itsubedibesh.walmart.controllers.web.Users.Users;

import com.itsubedibesh.walmart.controllers.api.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.api.Users.Users.GendersEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

public class UsersDto {
    public UsersDto() {
    }

    public UsersDto(long loginId, String fullName, String contactNumber, String address, Date dob, GendersEnum gender, String tpin) {
        this.loginId = loginId;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.tpin = tpin;
    }

    private long id;
    private long loginId;
    private String fullName;
    private String contactNumber;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private GendersEnum gender;
    private String tpin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLoginId() {
        return loginId;
    }

    public void setLoginId(long loginId) {
        this.loginId = loginId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public GendersEnum getGender() {
        return gender;
    }

    public void setGender(GendersEnum gender) {
        this.gender = gender;
    }

    public String getTpin() {
        return tpin;
    }

    public void setTpin(String tpin) {
        this.tpin = tpin;
    }
}
