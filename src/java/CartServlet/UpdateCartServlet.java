/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CartServlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import product.ProductDAO;

/**
 *
 * @author nguye
 */
public class UpdateCartServlet extends HttpServlet {

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
        
        PrintWriter out = response.getWriter();

        try {
            String username = (String) request.getSession().getAttribute("USERNAME");
            String buttonValue = request.getParameter("btAction");

            if (buttonValue != null) { // bấm nút SaveChange
                if (buttonValue.startsWith("SaveChange")) {
                    String productID = buttonValue.substring("SaveChange".length());
                    String quantity = request.getParameter("txtQuantity" + productID);

                    //test
                    out.println("A");
                    out.println(productID);
                    out.println(quantity);

                    ProductDAO dao = new ProductDAO();
                    int productID_raw = Integer.parseInt(productID);
                    int quantity_raw = Integer.parseInt(quantity);
                    if (quantity_raw > 0 && quantity_raw <= dao.getQuantityByProductID(productID_raw)) { // update sô lượng in cart trong khoảng [0 ; max_quantity]
                        dao.updateQuantityPIDInCart(username, productID_raw, quantity_raw);
                        response.sendRedirect("DispatchController?txtUsername=" + username + "&btAction=Cart"); // load lại trang sau khi đc cập nhật
                    } else {
                        response.sendRedirect("DispatchController?txtUsername=" + username + "&btAction=Cart"); // load lại trang k thay đổi
                    }

                } else if (buttonValue.startsWith("Delete")) { // bấm nút Delete
                    String productID = buttonValue.substring("Delete".length());

                    // test
                    out.println("B");
                    out.println(productID);

                    int productID_raw = Integer.parseInt(productID);

                    ProductDAO dao = new ProductDAO();
                    if (dao.deleteCartByUserPID(username, productID_raw)) { // xóa product in cart tương ứng với PID
                        response.sendRedirect("DispatchController?txtUsername=" + username + "&btAction=Cart"); // load lại trang sau khi đc cập nhật
                    }

                } else if (buttonValue.equals("Order")) { // bấm nút Order
                    
                    HttpSession session = request.getSession();
                    String[] item = request.getParameterValues("chkItem");
                    session.setAttribute("ORDER_ITEM_LIST", item); // lưu vào session

                    // test
                    out.println(username);
                    out.println(item);

                    if (item != null) {
                        response.sendRedirect("orderInfor.jsp"); // nhảy qua trang nhập ttin ng nhận
                    } else {
                        response.sendRedirect("DispatchController?txtUsername=" + username + "&btAction=Cart"); // load lại trang k thay đổi
                    }
                }
            }

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
