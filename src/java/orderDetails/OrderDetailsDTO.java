/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orderDetails;

import product.ProductDTO;

/**
 *
 * @author nguye
 */
public class OrderDetailsDTO extends ProductDTO{
    private int orderID;
    private int quantityOrdDetail;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int orderID, int quantityOrdDetail, String productName, double unitprice, String picture) {
        super( productName, unitprice, picture);
        this.orderID = orderID;
        this.quantityOrdDetail = quantityOrdDetail;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQuantityOrdDetail() {
        return quantityOrdDetail;
    }

    public void setQuantityOrdDetail(int quantityOrdDetail) {
        this.quantityOrdDetail = quantityOrdDetail;
    }
    
}
