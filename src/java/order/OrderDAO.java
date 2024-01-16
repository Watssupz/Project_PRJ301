/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBHelper;

/**
 *
 * @author nguye
 */
public class OrderDAO implements Serializable {

    public List<OrderDTO> getListOrderByUsername(String username) {
        List<OrderDTO> list = new ArrayList<>();
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT *\n" +
                        "FROM Orders\n" +
                        "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    Date orderDate = rs.getDate("OrderDate");
                    String location = rs.getString("Location");
                    String phonenumber = rs.getString("PhoneNumber");
                    int status = rs.getInt("Status");
                    double total = rs.getDouble("TotalBill");

                    OrderDTO dto = new OrderDTO(orderID, username, orderDate, location, phonenumber, status, total);
                    list.add(dto);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<OrderDTO> getAllOrder() {
        List<OrderDTO> list = new ArrayList<>();
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM Orders";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    String username = rs.getString("username");
                    Date orderDate = rs.getDate("OrderDate");
                    String location = rs.getString("Location");
                    String phonenumber = rs.getString("PhoneNumber");
                    int status = rs.getInt("Status");
                    double total = rs.getDouble("TotalBill");

                    OrderDTO dto = new OrderDTO(orderID, username, orderDate, location, phonenumber, status, total);
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateStatus(int orderID, int status) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Orders \n" +
                        "SET Status = ? \n" +
                        "WHERE OrderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setInt(2, orderID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getStatus(int orderID) {
        int status = 0;
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Status\n" +
                        "FROM Orders\n" +
                        "WHERE OrderID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                while(rs.next()){
                    status = rs.getInt("Status");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public String getNameByOrderID(int orderID) {
        String name = null;
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT username\n" +
                        "FROM Orders\n " +
                        "WHERE OrderID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }
    
    // test hàm
    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        System.out.println(dao.getNameByOrderID(7));
    }

}
