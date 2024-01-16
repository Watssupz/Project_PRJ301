<%-- 
    Document   : home
    Created on : Oct 3, 2023, 3:30:42 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="product.ProductDAO" %>
<%@page import="product.ProductDTO" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="bootstrap5/css/bootstrap-grid.min.css">-->
        <link rel="stylesheet" href="bootstrap5/css/bootstrap.css"/>
        <title>Shoes Store</title>
        <style>
            .banner img{
                width: 100%;
                height: 350px;
            }
            .item{
                text-align: center;
                border: 1px solid black;
                margin-bottom: 20px;
            }
        </style>
    </head>

    <body>
        <!--header-->
        <%@include file="header.jsp" %>

        <!--body-->
        <%@include file="body.jsp" %>      

        <!--footer-->
        <%@include file="footer.jsp" %>
    </body>
</html>
