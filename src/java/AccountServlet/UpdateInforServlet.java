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
public class UpdateInforServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String UPDATE_INFOR_PAGE = "updateInfor.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            
            // get data
            String username = (String) request.getSession().getAttribute("USERNAME");
            String oldPass = request.getParameter("txtOldPassword");
            String newPass = request.getParameter("txtNewPassword");
            String rePass = request.getParameter("txtRepassword");
            PrintWriter out = response.getWriter();
            // test
            out.println(username);

            AccountDAO dao = new AccountDAO();
            String old = dao.getOldPassByUsername(username); // lấy oldPass theo pk

            if (oldPass.isEmpty() || newPass.isEmpty() || rePass.isEmpty()) { // báo lỗi
                String error_empty = "No Empty Value!";
                request.setAttribute("ERROR_EMPTY", error_empty);
                request.getRequestDispatcher(UPDATE_INFOR_PAGE).forward(request, response);
            } else {
                if (oldPass.equals(old)) { // oldPass(pass vừa nhập) == pass cũ trong DB
                    if (!newPass.equals(oldPass)) { // newpass != oldPass
                        out.println("abc");
                        if (newPass.equals(rePass)) { // newPass == rePass
                            if (dao.updateInformation(username, newPass)) {
                                response.sendRedirect(LOGIN_PAGE);
                            }
                        } else { // báo lỗi
                            String error_rePass = "Repassword must be the same with the new ones";
                            request.setAttribute("ERROR_RE_PASS", error_rePass);
                            request.getRequestDispatcher(UPDATE_INFOR_PAGE).forward(request, response);
                        }
                    } else {// báo lỗi
                        String error_newPass = "New password must be different from the older ones!";
                        request.setAttribute("ERROR_NEW_PASS", error_newPass);
                        request.getRequestDispatcher(UPDATE_INFOR_PAGE).forward(request, response);
                    }
                } else {// báo lỗi
                    String error_oldPass = "Old Password Wrong!";
                    request.setAttribute("ERROR_OLD_PASS", error_oldPass);
                    request.getRequestDispatcher(UPDATE_INFOR_PAGE).forward(request, response);
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
