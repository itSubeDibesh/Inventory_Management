package com.itsubedibesh.wallmart.controllers.api.Categories;

import com.itsubedibesh.wallmart.controllers.configuration.Audit.Audit;

import javax.persistence.*;

@Entity
@Table(name = "Categories")
public class Categories extends Audit {

    /* Defining Constructors
    * */

    public Categories() {
    }

    public Categories( String name, String description) {
        this.name = name;
        this.description = description;
    }

    /* Defining Database Column Elements
    * */
    @Id
    @Column(name="CategoryId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL,optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name = "ParentId")
    private Categories parentId;

    @Column(name = "Name",unique = true,nullable = false)
    private String name;

    @Column(name ="Description",nullable=true,columnDefinition = "TEXT")
    private String description;

    /* Defining Getter Setter For Data Access
    * */
    public long getId() {
        return id;
    }

    public void setParentId(Categories parentId) {
        this.parentId = parentId;
    }

    public Categories getParentId() {
        return parentId;
    }

    public void setId(long id) {
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
