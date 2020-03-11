package com.alledrogo.models.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @Column(name = "categoryID")
    private int categoryID;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "categoryPicture")
    private byte[] categoryPicture;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public byte[] getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(byte[] categoryPicture) {
        this.categoryPicture = categoryPicture;
    }
}
