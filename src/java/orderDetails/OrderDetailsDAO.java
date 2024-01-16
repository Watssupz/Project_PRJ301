/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orderDetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBHelper;

/**
 *
 * @author nguye
 */
public class OrderDetailsDAO implements Serializable {

    public List<OrderDetailsDTO> getOrderDetails(int orderID) {
        List<OrderDetailsDTO> list = new ArrayList<>();
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;

            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "SELECT Products.ProductName , OrderDetails.Quantity , Products.UnitPrice , Products.Picture \n" +
                        "FROM OrderDetails \n" +
                        "JOIN Products on OrderDetails.ProductID = Products.ProductID \n" +
                        "WHERE OrderDetails.OrderID = ? \n";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("ProductName");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getDouble("Unitprice");
                    String picture = rs.getString("Picture");
                    OrderDetailsDTO orderDetails = new OrderDetailsDTO(orderID, quantity, productName, price, picture);
                    list.add(orderDetails);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<OrderDetailsDTO> findOrder(String username, int orderID) {
        List<OrderDetailsDTO> list = new ArrayList<>();
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Products.ProductName , OrderDetails.Quantity , Products.UnitPrice , Products.Picture \n" +
                        "FROM OrderDetails \n" +
                        "JOIN Products ON OrderDetails.ProductID = Products.ProductID \n" +
                        "JOIN Orders ON Orders.OrderID = OrderDetails.OrderID \n" +
                        "WHERE Orders.OrderID = ? \n" +
                        "AND Orders.username = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                stm.setString(2, username);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("ProductName");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getDouble("UnitPrice");
                    String picture = rs.getString("Picture");

                    OrderDetailsDTO dto = new OrderDetailsDTO(orderID, quantity, productName, price, picture);
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    // test hàm
    public static void main(String[] args) {
        OrderDetailsDAO dao = new OrderDetailsDAO();
    }

}
