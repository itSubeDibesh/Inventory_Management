package com.itsubedibesh.walmart.controllers.api.Authorities.RoleAccessMap;

import com.itsubedibesh.walmart.controllers.api.Authorities.Access.Access;
import com.itsubedibesh.walmart.controllers.api.Authorities.Roles.Roles;
import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;

import javax.persistence.*;

@Entity
@Table(name = "RoleAccessMappings")
public class RoleAccessMapping extends Audit {

    public RoleAccessMapping() {
    }

    public RoleAccessMapping(Roles roleId, Access accessId) {
        this.roleId = roleId;
        this.accessId = accessId;
    }

    @Id
    @Column(name = "RoleAccessMappingId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "RoleId")
    private Roles roleId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "AccessId")
    private Access accessId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    public Access getAccessId() {
        return accessId;
    }

    public void setAccessId(Access accessId) {
        this.accessId = accessId;
    }
}
