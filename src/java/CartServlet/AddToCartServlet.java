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
import product.ProductDAO;
import product.ProductDTO;

/**
 *
 * @author nguye
 */
public class AddToCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String VIEW_SERVLET = "ViewServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {
            //get data
            String username = request.getParameter("txtUsername");
            String productID = request.getParameter("txtProductID");
            String quantity = request.getParameter("txtQuantity");
            
            if(quantity.isEmpty()){
                quantity = "1"; // set def nếu k nhập = 1
            }
            
            int productID_raw = Integer.parseInt(productID);
            int quantity_raw = Integer.parseInt(quantity);

            ProductDAO dao = new ProductDAO();
            ProductDTO dto = dao.getProductByID(productID_raw); // select ra đối tượng muốn được add
            
            String succesfull = "";
            
            if (dao.addToCart(username, productID_raw, quantity_raw)) { // add vào cart
                succesfull = "Add Successful!";
                out.println("1");
            } else if (dao.UpdateQuantityCart(username, productID_raw, quantity_raw)) { // update quantity in cart
                succesfull = "Add Successful!";
                out.println("2");
            }
            request.setAttribute("SUCCESS", succesfull);
            request.getRequestDispatcher(VIEW_SERVLET).forward(request, response);
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
