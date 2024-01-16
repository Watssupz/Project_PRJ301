/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cart;

import product.ProductDTO;

/**
 *
 * @author nguye
 */
public class CartDTO extends ProductDTO{
    private String username;
    private int quantityInCart;
    
    public CartDTO() {
    }

    public CartDTO(String username, int quantityInCart, int productID, int categoryID, String productName, int quantity, double unitprice, String picture, String description) {
        super(productID, categoryID, productName, quantity, unitprice, picture, description);
        this.username = username;
        this.quantityInCart = quantityInCart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }

    
}
