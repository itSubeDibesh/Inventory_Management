package com.itsubedibesh.walmart.controllers.web.Authorities.Mappings.RoleAccess;

public class RoleAccessDto {
    private Integer roleId;
    private Integer accessId;
    private Integer id;

    public RoleAccessDto() {
    }

    public RoleAccessDto(Integer roleId, Integer accessId) {
        this.roleId = roleId;
        this.accessId = accessId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
