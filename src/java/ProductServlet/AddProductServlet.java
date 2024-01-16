/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ProductServlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.ProductDAO;

/**
 *
 * @author nguye
 */
public class AddProductServlet extends HttpServlet {

    private final String ADD_PROD = "addProduct.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // get data
            String productName = request.getParameter("txtProductName").trim();
            String categoryID = request.getParameter("txtCategoryID").trim();
            String quantity = request.getParameter("txtQuantity").trim();
            String price = request.getParameter("txtPrice").trim();
            String picture = request.getParameter("txtPicture").trim();
            String description = request.getParameter("txtDescription").trim();
            
            int categoryID_raw = 0, quantity_raw = 0;
            double price_raw = 0;

            PrintWriter out = response.getWriter();
            // test
            out.println(productName);
            out.println(categoryID_raw);
            out.println(quantity_raw);
            out.println(price);
            out.println(picture);

            if (productName.isEmpty() || categoryID.isEmpty() || quantity.isEmpty() || price.isEmpty() || picture.isEmpty() || description.isEmpty()) { // báo lỗi
                String error_empty = "No Empty !";
                request.setAttribute("ERROR_EMPTY", error_empty);
            } else {
                ProductDAO dao = new ProductDAO();

                quantity_raw = Integer.parseInt(quantity);
                price_raw = Double.parseDouble(price);

                switch (categoryID) { // set cateID
                    case "nike":
                        categoryID_raw = 1;
                        break;
                    case "adidas":
                        categoryID_raw = 2;
                        break;
                    case "vans":
                        categoryID_raw = 3;
                        break;
                    case "converse":
                        categoryID_raw = 4;
                        break;
                }
                
                if (dao.checkProductExist(categoryID_raw, productName, quantity_raw, price_raw, picture)) { // báo lỗi exist
                    String error_existed = "Product Existed!";
                    request.setAttribute("ERROR_EXISTED", error_existed);
                } else {
                    if (dao.addproduct(categoryID_raw, productName, quantity_raw, price_raw, picture, description)) { // add thành công
                        String successful = "Added product successfully!";
                        request.setAttribute("SUCCESS", successful);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(ADD_PROD).forward(request, response);
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
