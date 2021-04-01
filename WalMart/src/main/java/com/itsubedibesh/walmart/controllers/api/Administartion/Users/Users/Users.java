package com.itsubedibesh.walmart.controllers.api.Administartion.Users.Users;

import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Users")
public class Users extends Audit {

    public Users() {
    }

    public Users(Logins loginId, String fullName, String contactNumber, String address, Date dob, GendersEnum gender, String tpin) {
        this.loginId = loginId;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.tpin = tpin;
    }

    @Id
    @Column(name = "UserId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.DETACH,optional = false)
    @JoinColumn(name = "LoginId",nullable = false)
    private Logins loginId;

    @Column(name = "FullName",nullable = false)
    private String fullName;

    @Column(name = "ContactNumber",unique = true,columnDefinition = "varchar(20)")
    private String contactNumber;

    @Column(name = "Address",columnDefinition = "varchar(100)")
    private String address;

    @Column(name = "DOB", nullable = false,columnDefinition = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Column(name = "Gender", nullable = false, columnDefinition = "varchar(10) default 'None'")
    @Enumerated(EnumType.STRING)
    private GendersEnum gender;

    @Column(name = "TPIN",unique = true,columnDefinition = "varchar(20)")
    private String tpin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Logins getLoginId() {
        return loginId;
    }

    public void setLoginId(Logins loginId) {
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
