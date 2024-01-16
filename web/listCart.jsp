<%-- 
    Document   : listCart
    Created on : Oct 23, 2023, 11:35:45 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="cart.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Cart</title>
        <style>
            body table{
                border: 1px solid black;
            }
            th , td{
                height: 50px;
            }
            td{
                text-align: center;
            }
            h3{
                text-align: center;
            }
            .home a{
                text-decoration: none;
            }
            table {
                border-collapse: collapse;
                width: 100%;
                margin: auto;
                margin-bottom: 20px;
            }
            table, th, td {
                border: 1px solid black;
            }
        </style>
    </head>
    <body>

        <%@include file="header.jsp" %>

        <h3 style="margin: 30px">Product In Your Cart</h3>

        <%
            List<CartDTO> listCart = (List<CartDTO>) request.getAttribute("LIST_CART");
            if(listCart != null && !listCart.isEmpty()){
        %>
        <table style="width: 90%">
            <tr style="background-color: #DFD6CD">
                <th class="border border_right" style="text-align: center">Product ID</th>
                <th class="border border_right" style="text-align: center">Product Name</th>
                <th class="border border_right" style="text-align: center">Quantity</th>
                <th class="border border_right" style="text-align: center">Price</th>
                <th class="border border_right" style="text-align: center">Picture</th>
                <th class="border border_right" style="text-align: center">Action</th>
                <th class="border border_right" style="text-align: center"></th>
            </tr>

            <form action="UpdateCartServlet" method="post">
                <%
                for (CartDTO cart : listCart) {
                %>
                <tr>
                    <td class="border border_right"><%=cart.getProductID()%></td>
                    <td class="border border_right"><%=cart.getProductName()%></td>
                    <td class="border border_right"><input type="text" name="txtQuantity<%=cart.getProductID()%>" value="<%=cart.getQuantity()%>"></td>
                    <td class="border border_right"><%=cart.getUnitprice()%>$</td>
                    <td style="border: 1px solid #CED4DA"><img src="<%=cart.getPicture()%>" alt="anh" class="img-responsive" style="width: 200px;"/></td>

                    <td class="border border_right border_bottom">
                        <input type="hidden"  value="<%=cart.getProductID()%>">
                        <button class="btn btn-secondary" type="submit" value="SaveChange<%=cart.getProductID()%>" name="btAction" >Save Change</button>
                        <button class="btn btn-secondary" type="submit" value="Delete<%=cart.getProductID()%>" name="btAction">Delete</button>
                    </td>
                    <td class="border border_right border_bottom">
                        <input type="checkbox" name="chkItem" value="<%=cart.getProductID()%>">
                    </td>
                </tr>
                <%
                }
                %>
                <tr>
                    <td class="border border_right border_bottom" colspan="7">
                        <input class="btn btn-secondary" type="submit" name="btAction" value="Order">
                    </td>
                </tr>
            </form>

            <%
            }else{
            %>
            <p style="color: #C63100; text-align: center">Nothing here, you haven't add any product </p>
            <p style="color: #C63100; text-align: center"><span><a style="text-decoration: none" href="home.jsp">Click here</a></span> to get your own shoes</p>
            <%
            }
            %>

        </table>

    </body>
</html>
