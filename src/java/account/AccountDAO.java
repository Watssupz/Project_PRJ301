/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package account;

import java.io.Serializable;
import java.sql.Connection;
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
public class AccountDAO implements Serializable {
    
    private List<AccountDTO> accounts;
    
    public List<AccountDTO> getAccounts() {
        return accounts;
    }
    
    public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
        
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select username " +
                        "FROM Accounts " +
                        "WHERE username = ? " +
                        "AND password = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()) {
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
    
    public void getAllAccounts() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT username , password , fullname , phonenumber , email , role " +
                        "FROM Accounts " +
                        "ORDER BY role DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phoneNumber = rs.getString("phonenumber");
                    String email = rs.getString("email");
                    
                    boolean role = rs.getBoolean("role");
                    
                    AccountDTO dto = new AccountDTO(username, password, fullname, phoneNumber, email, role);
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public boolean checkAdmin(String username) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT username " +
                        "FROM Accounts " +
                        "WHERE username = ? " +
                        "AND role = 1 ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
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
    
    public boolean checkUsernameNotExists(String username) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            String query = "SELECT * FROM Accounts " +
                    "WHERE username = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, username);
            rs = stm.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean forgetPassword(String username, String phonenumber) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * \n" +
                        "FROM Accounts \n" +
                        "WHERE username = ? \n" +
                        "AND phonenumber = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, phonenumber);
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
    
    public boolean registration(String username, String password, String fullname, String phonenumber) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Accounts (username , password , fullname , phonenumber , role)" +
                        "VALUES (? , ? , ? , ? , 0)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setString(4, phonenumber);
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
    
    public boolean updateRole(String username, boolean role) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Accounts " +
                        "SET role = ? " +
                        "WHERE username = ? ";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, role);
                stm.setString(2, username);
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
    
    public boolean deleteRecord(String username) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM Accounts \n" +
                        "WHERE username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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
    
    public String getOldPassByUsername(String username) {
        String password = null;
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT password " +
                        "FROM Accounts " +
                        "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    password = rs.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return password;
    }
    
    public boolean updateInformation(String username, String password) {
        try {
            Connection con = null;
            PreparedStatement stm = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql
                        = "UPDATE Accounts " +
                        "SET password = ? " +
                        "where username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, username);
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
    
    public String getMailByUsername(String username) {
        String email = null;
        try {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT email " +
                        "FROM Accounts " +
                        "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    email = rs.getString("email");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return email;
    }
    
    
    // test hàm
    public static void main(String[] args) {
        AccountDAO a = new AccountDAO();
        List<AccountDTO> dto = a.getAccounts();
    }
    
}
