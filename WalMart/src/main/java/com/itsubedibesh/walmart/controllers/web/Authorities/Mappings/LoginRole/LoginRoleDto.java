package com.itsubedibesh.walmart.controllers.web.Authorities.Mappings.LoginRole;

public class LoginRoleDto {
    private Integer roleId;
    private long loginId;
    private Integer id;

    public LoginRoleDto() {
    }

    public LoginRoleDto(Integer roleId, long loginId) {
        this.roleId = roleId;
        this.loginId = loginId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public long getLoginId() {
        return loginId;
    }

    public void setLoginId(long loginId) {
        this.loginId = loginId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
