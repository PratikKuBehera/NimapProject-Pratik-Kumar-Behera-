package com.nimap.task.NimapTaskApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name="Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pId")
    private int pId;

    @Column(name="pName")
    private String pName;

    @Column(name="pDescription")
    private String pDescription;

    @Column(name="pPrice")
    private  String pPrice;

    @Column(name="pQuantity")
    private int pQuantity;


    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "cId", nullable = false )
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public Product(int pId, String pName, String pDescription, String pPrice, int pQuantity) {
        this.pId = pId;
        this.pName = pName;
        this.pDescription = pDescription;
        this.pPrice = pPrice;
        this.pQuantity = pQuantity;
    }

    public Product(String pName, String pDescription, String pPrice, int pQuantity) {
        this.pName = pName;
        this.pDescription = pDescription;
        this.pPrice = pPrice;
        this.pQuantity = pQuantity;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                ", pDescription='" + pDescription + '\'' +
                ", pPrice='" + pPrice + '\'' +
                ", pQuantity=" + pQuantity +
                '}';
    }
}
