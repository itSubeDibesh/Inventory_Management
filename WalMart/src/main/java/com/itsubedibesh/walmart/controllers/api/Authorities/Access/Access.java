package com.itsubedibesh.walmart.controllers.api.Authorities.Access;

import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;

import javax.persistence.*;

@Entity
@Table(name = "Access")
public class Access extends Audit {

    public Access() {
    }

    public Access(String name, String table, AccessEnum createAccess, AccessEnum readAccess, AccessEnum updateAccess, AccessEnum deleteAccess, String description) {
        this.name = name;
        this.table = table;
        this.readAccess = readAccess;
        this.createAccess = createAccess;
        this.updateAccess = updateAccess;
        this.deleteAccess = deleteAccess;
        this.description = description;
    }


    @Id
    @Column(name = "AccessId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "ScoppedTable", nullable = false)
    private String table;

    @Column(name = "CreateAccess", nullable = false, columnDefinition = "varchar(10) default 'Deny'")
    @Enumerated(EnumType.STRING)
    private AccessEnum createAccess = AccessEnum.Deny;

    @Column(name = "ReadAccess", nullable = false, columnDefinition = "varchar(10) default 'Allow'")
    @Enumerated(EnumType.STRING)
    private AccessEnum readAccess = AccessEnum.Allow;

    @Column(name = "UpdateAccess", nullable = false, columnDefinition = "varchar(10) default 'Deny'")
    @Enumerated(EnumType.STRING)
    private AccessEnum updateAccess = AccessEnum.Deny;

    @Column(name = "DeleteAccess", nullable = false, columnDefinition = "varchar(10) default 'Deny'")
    @Enumerated(EnumType.STRING)
    private AccessEnum deleteAccess = AccessEnum.Deny;

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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccessEnum getReadAccess() {
        return readAccess;
    }

    public void setReadAccess(AccessEnum readAccess) {
        this.readAccess = readAccess;
    }

    public AccessEnum getCreateAccess() {
        return createAccess;
    }

    public void setCreateAccess(AccessEnum createAccess) {
        this.createAccess = createAccess;
    }

    public AccessEnum getUpdateAccess() {
        return updateAccess;
    }

    public void setUpdateAccess(AccessEnum updateAccess) {
        this.updateAccess = updateAccess;
    }

    public AccessEnum getDeleteAccess() {
        return deleteAccess;
    }

    public void setDeleteAccess(AccessEnum deleteAccess) {
        this.deleteAccess = deleteAccess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
