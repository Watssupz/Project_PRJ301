/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product;

/**
 *
 * @author nguye
 */
public class ProductDTO {
    private int productID;
    private int categoryID;
    private String productName;
    private int quantity;
    private double unitprice;
    private String picture;
    private String description;

    public ProductDTO() {
    }

    public ProductDTO( String productName, double unitprice, String picture) {
        this.productName = productName;
        this.unitprice = unitprice;
        this.picture = picture;
    }
    
    public ProductDTO(int productID, int categoryID, String productName, int quantity, double unitprice, String picture, String description) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.productName = productName;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.picture = picture;
        this.description = description;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
