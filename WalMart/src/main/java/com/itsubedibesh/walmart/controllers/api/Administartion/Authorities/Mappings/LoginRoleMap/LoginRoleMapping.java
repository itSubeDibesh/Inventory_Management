package com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Mappings.LoginRoleMap;

import com.itsubedibesh.walmart.controllers.api.Administartion.Authorities.Roles.Roles;
import com.itsubedibesh.walmart.controllers.api.Administartion.Users.Logins.Logins;
import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;

import javax.persistence.*;

@Entity
@Table(name = "LoginRoleMappings")
public class LoginRoleMapping extends Audit {

    public LoginRoleMapping() {
    }

    public LoginRoleMapping(Logins loginId, Roles roleId) {
        this.loginId = loginId;
        this.roleId = roleId;
    }

    @Id
    @Column(name = "LoginRoleMappingId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.DETACH,optional = false)
    @JoinColumn(name = "loginId",nullable = false)
    private Logins loginId;

    @ManyToOne(cascade =  CascadeType.DETACH,optional = false)
    @JoinColumn(name = "RoleId",nullable = false)
    private Roles roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Logins getLoginId() {
        return loginId;
    }

    public void setLoginId(Logins loginId) {
        this.loginId = loginId;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }
}
