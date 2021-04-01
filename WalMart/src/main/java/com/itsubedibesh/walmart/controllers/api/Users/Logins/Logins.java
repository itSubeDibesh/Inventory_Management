package com.itsubedibesh.walmart.controllers.api.Users.Logins;

import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;

import javax.persistence.*;

@Entity
@Table(name = "Logins")
public class Logins extends Audit {

    public Logins() {
    }

    public Logins(String phone, String email, String avatar, String userName, String password) {
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.userName = userName;
        this.password = password;
    }

    @Id
    @Column(name="LoginId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Phone",unique = true,nullable = false)
    private String phone;

    @Column(name = "Email",unique = true,nullable = false,columnDefinition = "varchar(500)")
    private String email;

    @Column(name = "Avatar",nullable = true,columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "UserName",unique = true,nullable = false)
    private String userName;

    @Column(name = "Password",nullable = false,columnDefinition = "TEXT")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getImagePath() {
        if (avatar == null || email == null) return null;
        return "src/User_Images/" + email.toLowerCase() + "/" + avatar;
    }
}
