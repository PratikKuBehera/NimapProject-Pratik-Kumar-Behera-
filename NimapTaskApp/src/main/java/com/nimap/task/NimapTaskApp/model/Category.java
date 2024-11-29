package com.nimap.task.NimapTaskApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cId")
    private int cId;

    @Column(name="cName")
    private String cName;


    @Column(name="cDescription")
    private String cDescription;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public Category(int cId, String cName, String cDescription) {
        this.cId = cId;
        this.cName = cName;
        this.cDescription = cDescription;
    }

    public Category(String cName, String cDescription) {
        this.cName = cName;
        this.cDescription = cDescription;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cDescription='" + cDescription + '\'' +
                '}';
    }
}
