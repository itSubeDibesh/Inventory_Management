package com.itsubedibesh.walmart.controllers.api.Authorities.Roles;

import com.itsubedibesh.walmart.controllers.configuration.Audit.Audit;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
public class Roles extends Audit {

    public Roles() {
    }

    public Roles(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @Column(name = "RoleId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @Column(name = "Description", nullable = true, columnDefinition = "TEXT")
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
