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
import java.util.List;
import product.ProductDAO;
import product.ProductDTO;

/**
 *
 * @author nguye
 */
public class SearchAjaxServlet extends HttpServlet {

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

        // get data
        String username = (String) request.getSession().getAttribute("USERNAME");
        String productName = request.getParameter("txtSearchValue").trim();

        ProductDAO dao = new ProductDAO();

        List<ProductDTO> list = dao.searchProduct(productName); // list được trả về theo tên được tìm kiếm 

        PrintWriter out = response.getWriter();

        for (ProductDTO i : list) { // response ajax
            out.println("<div class=\"col-md-3 col-6 p-1\">\n" +
                    "                    <div class=\"thumbnail border\">\n" +
                    "                        <img src=\""+i.getPicture()+"\" class=\"img-responsive\" style=\"width:100%\" alt=\"Image\">\n" +
                    "                    </div>\n" +
                    "                    <div class=\"bg-light border p-3\">\n" +
                    "                        <p>"+i.getProductName()+"</p>\n" +
                    "                        <p>Price: <span class=\"text-danger\">"+i.getUnitprice()+"$</span></p>\n" +
                    "                        <form action=\"DispatchController\" method=\"POST\">\n" +
                    "                            <button class=\"button-cart text-black-50 rounded-1 p-2 mb-2\" name=\"btAction\" value=\"viewProduct\">\n" +
                    "                                Add to Cart\n" +
                    "                            </button>\n" +
                    "                            <input type=\"hidden\" name=\"txtProductID\" value=\""+i.getProductID()+"\">\n" +
                    "                            <input type=\"hidden\" name=\"txtUsername\" value=\""+username+"\">\n" +
                    "                        </form>\n" +
                    "                    </div>\n" +
                    "                </div>");
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
