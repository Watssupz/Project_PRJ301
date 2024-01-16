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
public class RegistrationServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String REGISTRATION_PAGE = "registration.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // get data
        String username = request.getParameter("txtUsername").trim();
        String password = request.getParameter("txtPassword").trim();
        String repassword = request.getParameter("txtRepassword").trim();
        String fullname = request.getParameter("txtFullname").trim();
        String phonenumber = request.getParameter("txtPhonenumber").trim();

        try {
            PrintWriter out = response.getWriter();
            if (username.isEmpty() || password.isEmpty() || repassword.isEmpty() || fullname.isEmpty() || phonenumber.isEmpty()) { //báo lỗi
                String error_empty = "No Empty Valid !";
                request.setAttribute("ERROR_EMPTY", error_empty);
                out.println(error_empty);
                request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
            } else {
                AccountDAO dao = new AccountDAO();
                if (dao.checkUsernameNotExists(username)) { // check tên k tồn tại
                    if (password.equals(repassword)) { // check pw == repassw
                        if (phonenumber.matches("^0[0-9]{9}$")) { // format phonenumber
                            if (dao.registration(username, password, fullname, phonenumber)) { // regis thành công
                                response.sendRedirect(LOGIN_PAGE);
                            }
                        } else { // báo lỗi format phonenumber
                            String error_phoneNumber_format = "Wrong format phonenumber";
                            request.setAttribute("ERROR_PHONENUMBER_FORMAT", error_phoneNumber_format);
                            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
                        }
                    } else { // báo lỗi sai pw != repass
                        String error_pass = "Password & repassword must be duplicate !";
                        request.setAttribute("ERROR_PASS", error_pass);
                        request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
                    }
                } else { // báo lỗi tên tồn tại
                    String error_username = "Username Existed !";
                    request.setAttribute("ERROR_USERNAME", error_username);
                    request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
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
