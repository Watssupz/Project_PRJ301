<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="account.AccountDTO" %>
<%@page import="account.AccountDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="bootstrap5/css/bootstrap.css">
        <script src="bootstrap5/js/bootstrap.bundle.min.js"></script>
        <style>
            #header1{
                background-color: #9d9e91;
            }

            #logo img{
                width: 70px;
                height: 70px;
            }

            #login_logout a{
                text-decoration: none;
                color: black;
            }
        </style>
        <!--<script src="Bootstrap_5/js/bootstrap.bundle.min.js"></script>-->
    </head>
    <body style="margin-bottom: 10px ">
        <div class="container-fluid">
            <div class="row" id="header1">
                <div class="col-md-2 text-center" id="logo">
                    <a href="home.jsp">
                        <img src="https://i.ibb.co/RCRt2qy/logo-page.png" alt="logo">
                    </a>
                </div>
                <div class="col-md-7 my-auto" id="searchbar-form">
                    <form action="DispatchController" method="get">
                        <div class="input-group">
                            <input oninput="searchByName(this)" type="text" class="form-control" placeholder="Search" name="txtSearchValue">
                            <div class="input-group-btn">
                                <button class="btn btn-secondary" type="submit" name="btAction" value="search">Search</button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col-md-3 my-auto text-center" >
                    <%
                        String USERNAME = (String) request.getSession().getAttribute("USERNAME");
                        if(USERNAME == null){
                    %>
                    <div class="btn-group" id="login_logout">
                        <a class="btn btn-link" href="login.jsp">Login</a>
                        <a class="btn btn-link" href="registration.jsp">Register</a>
                    </div>
                    <%
                        }else{
                    %>
                    <div class="dropdown">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown">
                            <%=USERNAME%>
                        </button>
                        <ul class="dropdown-menu">
                            <%
                                AccountDAO dao = new AccountDAO();
                                if(dao.checkAdmin(USERNAME)){
                                dao.getAllAccounts();
                            %>
                            <form action="DispatchController" method="post">
                                <input class="dropdown-item" type="submit" name="btAction" value="Manage Account">
                                <input class="dropdown-item" type="submit" name="btAction" value="Manage Product">
                                <input class="dropdown-item" type="submit" name="btAction" value="Manage Order">
                            </form>
                            <li><a class="dropdown-item" href="addProduct.jsp">Add Product</a></li>
                            <%    
                                }else{
                            %>
                            <form action="DispatchController">
                                <input type="hidden" name="txtUsername" value="<%=USERNAME%>">
                                <input class="dropdown-item" type="submit" name="btAction" value="Cart">
                                <input class="dropdown-item" type="submit" name="btAction" value="Orders">
                            </form>
                            <%
                                }
                            %>
                            <form action="DispatchController" method="post">
                                <li><a class="dropdown-item" href="updateInfor.jsp">Account</a></li>
                                <input class="dropdown-item" type="submit" name="btAction" value="Logout">
                            </form>
                        </ul>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
