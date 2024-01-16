/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package OrderServlet;

import email.sendMail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import orderDetails.OrderDetailsDAO;
import orderDetails.OrderDetailsDTO;
import product.ProductDAO;

/**
 *
 * @author nguye
 */
public class BillServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // lấy data
            String username = (String) request.getSession().getAttribute("USERNAME");
            String location = request.getParameter("txtLocation");
            String phonenumber = request.getParameter("txtPhonenumber");
            String email = request.getParameter("txtEmail");

            String[] list = (String[]) request.getSession().getAttribute("ORDER_ITEM_LIST");

            PrintWriter out = response.getWriter();

            if (location.isEmpty() || phonenumber.isEmpty() || email.isEmpty()) { // báo lỗi
                String error_empty = "No empty!";
                request.setAttribute("ERROR_EMPTY", error_empty);
                request.getRequestDispatcher("orderInfor.jsp").forward(request, response);
            } else {
                if (phonenumber.matches("^0[0-9]{9}$")) { // format phonenumber

                    if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                        ProductDAO dao = new ProductDAO();

                        // tính tổng bill
                        double totalBill = 0;
                        for (String productID : list) {
                            int productID_raw = Integer.parseInt(productID);
                            int quantity = dao.getQuantityInCartByPID(username, productID_raw);
                            double price = dao.getPriceByPID(productID_raw);
                            totalBill += quantity * price;
                        }

                        // lấy ra orderID của order vừa được add
                        int orderID = dao.insertOrderAndReturnKey(username, location, phonenumber, 1, totalBill);

                        for (String productID : list) {
                            int productID_raw = Integer.parseInt(productID);
                            int quantity = dao.getQuantityInCartByPID(username, productID_raw);

                            // cập nhật lại quantity trong bảng Products
                            dao.updateQuantityAfterAddOrder(productID_raw, quantity);

                            // add vào orderDetails
                            if (dao.insertIntoOrderDetails(orderID, productID_raw, quantity)) {
                                // xóa trong cart
                                dao.deleteCartByUserPID(username, productID_raw);
                           }
                        }

                        OrderDetailsDAO d = new OrderDetailsDAO();
                        List<OrderDetailsDTO> Detail_List = d.getOrderDetails(orderID); // lấy ra list của sản phẩm được add vào order theo orderID
                        request.setAttribute("TOTAL_BILL", totalBill);
                        request.setAttribute("LIST_DETAILS", Detail_List);

                    } else {
                        String error_email_format = "Wrong format email";
                        request.setAttribute("ERROR_EMAIL_FORMAT", error_email_format);
                        request.getRequestDispatcher("orderInfor.jsp").forward(request, response);
                    }
                } else { // báo lỗi
                    String error_phoneNumber_format = "Wrong format phonenumber";
                    request.setAttribute("ERROR_PHONENUMBER_FORMAT", error_phoneNumber_format);
                    request.getRequestDispatcher("orderInfor.jsp").forward(request, response);
                }
            }

            request.getRequestDispatcher("bill.jsp").forward(request, response); // truyền data qua bill.jsp
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
