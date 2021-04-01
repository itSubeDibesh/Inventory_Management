package com.itsubedibesh.walmart.controllers.api.Authorities.Mappings.LoginRoleMap;

import com.itsubedibesh.walmart.controllers.api.Authorities.Roles.Roles;
import com.itsubedibesh.walmart.controllers.api.Users.Logins.Logins;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loginId")
    private Logins loginId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RoleId")
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
