/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
public class DispatchController extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String HOME_PAGE = "home.jsp";

    // Servlet Accounts
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String ACCOUNTS_SERVLET = "AccountsServlet"; // get list acc
    private final String REGISTRATION_SERVLET = "RegistrationServlet";
    private final String UPDATE_ROLE_SERVLET = "UpdateServlet";
    private final String DELETE_SERVLET = "DeleteServlet";
    private final String UPDATE_ACCOUNT = "UpdateInforServlet";
    private final String FORGET_PW_SERVLET = "ForgotPWServlet";
    private final String SET_NEWPW = "SetNewPWServlet";

    // Servlet Products
    private final String PRODUCTS_SERVLET = "ProductServlet";
    private final String SEARCH_SERVLET = "SearchServlet";
    private final String ADD_PROD_SERVLET = "AddProductServlet";
    private final String DELETE_PROD_SERVLET = "DeleteProductServlet";
    private final String UPDATE_PROD_SERVLET = "UpdateProductServlet";

    private final String ADD_TO_CART_SERVLET = "AddToCartServlet";
    private final String VIEW_PRODUCT_SERVLET = "ViewServlet";
    private final String CART_SERVLET = "CartServlet";
//    private final String DELETE_CART_SERVLET = "DeleteCartServlet";
//    private final String ORDER_SERVLET = "OrderServlet";
    private final String BILL_SERVLET = "BillServlet";
    private final String ORDER_LIST_SERVLET = "OrderListServlet";
    private final String USER_ORDER_LIST = "UserOrderListServlet";
    private final String GET_ALL_ORDER_SERVLET = "GetAllOrderServlet";
    private final String MANAGER_ORDER = "DetailOrderByAdminServlet";
    private final String CONFIRMED_SERVLET = "ConfirmedServlet";
    private final String DENY_SERVLET = "DenyServlet";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME_PAGE;
        String action = request.getParameter("btAction");

        switch (action) {
            // case Accounts
            case "Manage Account":
                url = ACCOUNTS_SERVLET;
                break;
            case "Delete":
                url = DELETE_SERVLET;
                break;
            case "Login":
                url = LOGIN_SERVLET;
                break;
            case "Logout":
                url = LOGOUT_SERVLET;
                break;
            case "Registration":
                url = REGISTRATION_SERVLET;
                break;
            case "Update":
                url = UPDATE_ROLE_SERVLET;
                break;
            case "Update Password":
                url = UPDATE_ACCOUNT;
                break;
            case "Change":
                url = FORGET_PW_SERVLET;
                break;
            case "SetNewPW":
                url = SET_NEWPW;
                break;

            // case Products
            case "ADD":
                url = ADD_PROD_SERVLET;
                break;
            case "addCart":
                url = ADD_TO_CART_SERVLET;
                break;
            case "viewProduct":
                url = VIEW_PRODUCT_SERVLET;
                break;
            case "delete product":
                url = DELETE_PROD_SERVLET;
                break;
            case "search":
                url = SEARCH_SERVLET;
                break;
            case "Manage Product":
                url = PRODUCTS_SERVLET;
                break;
            case "update product":
                url = UPDATE_PROD_SERVLET;
                break;
            case "Cart":
                url = CART_SERVLET;
                break;
            case "Confirm":
                url = BILL_SERVLET;
                break;
            case "Buy":
                url = ORDER_LIST_SERVLET;
                break;
            case "Orders":
                url = ORDER_LIST_SERVLET;
                break;
            case "Details":
                url = USER_ORDER_LIST;
                break;
            case "Manage Order":
                url = GET_ALL_ORDER_SERVLET;
                break;
            case "Detail":
                url = MANAGER_ORDER;
                break;
            case "Accept":
                url = CONFIRMED_SERVLET;
                break;
            case "Deny":
                url = DENY_SERVLET;
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
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
