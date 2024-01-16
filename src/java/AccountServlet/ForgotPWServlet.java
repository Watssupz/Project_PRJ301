/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountServlet;

import account.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
public class ForgotPWServlet extends HttpServlet {

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
        out.println("ABC");

        try {
            // get data
            String username = (String) request.getSession().getAttribute("USERNAME");
            String username_input = request.getParameter("txtUsername");
            String phonenumber = request.getParameter("txtPhonenumber");
            
            // test
            out.println(username);
            out.println(username_input);
            out.println(phonenumber);

            if (username_input.isEmpty() || phonenumber.isEmpty()) { // báo lỗi 
                String error_empty = "No empty, please enter!";
                request.setAttribute("ERROR_EMPTY", error_empty);
            } else {
                if (username_input.equals(username)) { // tên vừa nhập fai trùng tên đã đăng nhập
                    AccountDAO dao = new AccountDAO();
                    if (dao.forgetPassword(username, phonenumber)) { // set new PW
                        response.sendRedirect("setNewPW.jsp");
                    } else { // báo lỗi nhập sai ttin
                        String error_input = "Username or phonenumber invalid!";
                        request.setAttribute("ERROR_INPUT", error_input);
                    }
                } else { // báo lỗi
                    String error_input = "Username or phonenumber invalid!";
                    request.setAttribute("ERROR_INPUT", error_input);
                }
            }
            request.getRequestDispatcher("forgetPW.jsp").forward(request, response);
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
