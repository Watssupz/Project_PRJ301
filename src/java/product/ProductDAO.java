/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product;

import cart.CartDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBHelper;
import product.ProductDTO;
import sun.text.normalizer.ICUData;

/**
 *
 * @author nguye
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> productList;
    private List<ProductDTO> searchList;

    public List<ProductDTO> getSearchList() {
        return searchList;
    }

    public List<ProductDTO> getProductList() {
        getProducts();
        return productList;
    }

    public void getProducts() {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT ProductID , CategoryID , ProductName , Quantity , UnitPrice , Picture " +
                        "FROM Products";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    int categoryID = rs.getInt("CategoryID");
                    String productName = rs.getString("ProductName");
                    int quantity = rs.getInt("Quantity");
                    double unitPrice = rs.getDouble("Unitprice");
                    String picture = rs.getString("Picture");

                    ProductDTO dto = new ProductDTO(productID, categoryID, productName, quantity, unitPrice, picture, picture);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkProductExist(int categoryID, String productName, int quantity, double unitPrice, String picture) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT CategoryID , ProductName , Quantity , UnitPrice , Picture\n" +
                        "FROM Products\n" +
                        "WHERE CategoryID = ? \n" +
                        "	AND ProductName = ?\n" +
                        "	AND Quantity = ?\n" +
                        "	AND UnitPrice = ?\n" +
                        "	AND Picture = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryID);
                stm.setString(2, productName);
                stm.setInt(3, quantity);
                stm.setDouble(4, unitPrice);
                stm.setString(5, picture);
                rs = stm.executeQuery();
                while (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addproduct(int categoryID, String productName, int quantity, double unitPrice, String picture, String description) {
        try {
            Connection con = null;
            PreparedStatement stm = null;

            con = DBHelper.makeConnection();
            if (con != null) {

                String sql = "INSERT INTO Products (CategoryID , ProductName , Quantity , UnitPrice , Picture , Description)" +
                        "VALUES (? , ? , ? , ? , ? , ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryID);
                stm.setString(2, productName);
                stm.setInt(3, quantity);
                stm.setDouble(4, unitPrice);
                stm.setString(5, picture);
                stm.setString(6, description);
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

    public boolean deleteProduct(int ProductID) {
        try {
            Connection con = null;
            PreparedStatement stm = null;

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM Products " +
                        "WHERE ProductID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, ProductID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getPriceByPID(int productID) {
        double price = 0;
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT UnitPrice\n" +
                        "FROM Products\n" +
                        "WHERE ProductID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt("Unitprice");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    public List<ProductDTO> searchProduct(String productName) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * \n" +
                        "FROM Products \n" +
                        "WHERE ProductName LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + productName + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    int categoryID = rs.getInt("CategoryID");
                    String prodName = rs.getString("ProductName");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getDouble("UnitPrice");
                    String picture = rs.getString("Picture");
                    String description = rs.getString("Description");
                    ProductDTO dto = new ProductDTO(productID, categoryID, prodName, quantity, price, picture, description);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
                }
                if (this.productList.size() > 0) {
                    return this.productList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addToCart(String username, int productID, int quantity) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Cart(username,ProductID , Quantity)\n" +
                        "VALUES (? , ? , ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setInt(2, productID);
                stm.setInt(3, quantity);
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

    public boolean UpdateQuantityCart(String username, int productID, int quantity) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Cart \n" +
                        "Set Quantity = Quantity + ? \n" +
                        "where username = ? \n" +
                        "and ProductID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, username);
                stm.setInt(3, productID);
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

    public boolean updateQuantityPriceProduct(int productID, int quantity, double price) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Products\n" +
                        "SET Quantity = ? , " +
                        "UnitPrice = ? \n" +
                        "WHERE ProductID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setDouble(2, price);
                stm.setInt(3, productID);
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

    public ProductDTO getProductByID(int productID) {
        ProductDTO dto = null;
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT CategoryID , ProductName , Quantity , UnitPrice , Picture , Description\n" +
                        "FROM Products \n" +
                        "WHERE ProductID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int categoryID = rs.getInt("CategoryID");
                    String productName = rs.getString("ProductName");
                    int quantity = rs.getInt("quantity");
                    double unitPrice = rs.getDouble("UnitPrice");
                    String picture = rs.getString("Picture");
                    String description = rs.getString("description");
                    dto = new ProductDTO(productID, categoryID, productName, quantity, unitPrice, picture, description);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public boolean updateQuantityAfterAddOrder(int productID , int quantity) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Products\n" +
                        "SET Quantity = Quantity - ? \n" +
                        "WHERE ProductID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, productID);
                int row = stm.executeUpdate();
                if(row > 0){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CartDTO> getCartByUsername(String username) {
        List<CartDTO> list = new ArrayList<>();
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Accounts.username , Products.ProductID , Products.CategoryID , Products.ProductName , Cart.Quantity , Products.UnitPrice , Products.Picture , Products.Description\n" +
                        "FROM Cart \n" +
                        "JOIN Products on Cart.ProductID = Products.ProductID \n" +
                        "JOIN Accounts on Cart.username = Accounts.username \n" +
                        "WHERE Accounts.username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    int categoryID = rs.getInt("CategoryID");
                    String productName = rs.getString("ProductName");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getDouble("UnitPrice");
                    String picture = rs.getString("Picture");
                    String description = rs.getString("Description");
                    CartDTO cart = new CartDTO(username, quantity, productID, categoryID, productName, quantity, price, picture, description);
                    list.add(cart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteCartByUserPID(String username, int productID) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM Cart \n" +
                        "WHERE username = ? \n" +
                        "AND ProductID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setInt(2, productID);
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

    public boolean updateQuantityPIDInCart(String username, int productID, int quantity) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Cart \n" +
                        "SET Quantity = ? \n" +
                        "WHERE username = ? \n" +
                        "AND ProductID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, username);
                stm.setInt(3, productID);
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

    public int getQuantityByProductID(int productID) {
        int quantity = 0;
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT Quantity \n" +
                        "FROM Products \n" +
                        "WHERE ProductID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    quantity = rs.getInt("Quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public int getQuantityInCartByPID(String username, int productID) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT quantity\n" +
                        "FROM Cart \n" +
                        "WHERE username = ? \n" +
                        "AND ProductID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setInt(2, productID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt("Quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insertOrderAndReturnKey(String username, String location, String phonenumber, int status, double totalBill) {
        int key = -1;
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Orders (username , OrderDate , Location , PhoneNumber , Status , TotalBill)\n" +
                        "VALUES(? , GETDATE() , ? , ? , ?, ?)";
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, username);
                stm.setString(2, location);
                stm.setString(3, phonenumber);
                stm.setInt(4, 1);
                stm.setDouble(5, totalBill);

                int isExecute = stm.executeUpdate();
                if (isExecute > 0) {
                    rs = stm.getGeneratedKeys();
                    while (rs.next()) {
                        key = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return key;
    }

    public boolean insertIntoOrderDetails(int orderId, int productID, int quantity) {
        try {
            Connection con = null;
            PreparedStatement stm = null;

            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO OrderDetails(OrderID , ProductID , Quantity)\n" +
                        "VALUES (? , ? , ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                stm.setInt(2, productID);
                stm.setInt(3, quantity);
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

    
    // test hàm
    public static void main(String[] args) {
        ProductDAO p = new ProductDAO();
    }

}
