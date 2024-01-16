/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package order;

import java.sql.Date;

/**
 *
 * @author nguye
 */
public class OrderDTO {
    private int orderID;
    private String username;
    private Date orderDate;
    private String location;
    private String phonenumber;
    private int status;
    private double total;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, String username, Date orderDate, String location, String phonenumber, int status, double total) {
        this.orderID = orderID;
        this.username = username;
        this.orderDate = orderDate;
        this.location = location;
        this.phonenumber = phonenumber;
        this.status = status;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
