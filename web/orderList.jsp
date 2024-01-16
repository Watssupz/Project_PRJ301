<%-- 
    Document   : orderList
    Created on : Oct 30, 2023, 12:18:24 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "order.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order List</title>
        <style>
            body table{
                border: 1px solid black;
            }
            th , td{
                /*width: 350px;*/
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

        <h3 style="margin: 30px">Your Order</h3>

        <%
            List<OrderDTO> listOrder = (List<OrderDTO>) request.getAttribute("USER_ORSER_LIST");
            if(listOrder != null && !listOrder.isEmpty()){
        %>
        <table style="width: 90%">
            <tr style="background-color: #DFD6CD">
                <th class="border border_right" style="text-align: center">OrderID</th>
                <th class="border border_right" style="text-align: center">Username</th>
                <th class="border border_right" style="text-align: center">Order Date</th>
                <th class="border border_right" style="text-align: center">Location</th>
                <th class="border border_right" style="text-align: center">Phonenumber</th>
                <th class="border border_right" style="text-align: center">Status</th>
                <th class="border border_right" style="text-align: center">Details</th>
            </tr>
            <%
            for(OrderDTO order : listOrder){
            %>
            <tr>

            <form action="DispatchController" method="post">
                <td class="border border_right"><%=order.getOrderID()%></td>
                <td class="border border_right"><%=order.getUsername()%></td>
                <td class="border border_right"><%=order.getOrderDate()%></td>
                <td class="border border_right"><%=order.getLocation()%></td>
                <td class="border border_right"><%=order.getPhonenumber()%></td>

                <%
                    String status = "";
                    switch(order.getStatus()){
                        case 1: status = "Processing";
                            break;
                        case 2: status = "Approved";
                            break;
                        case 3: status = "Denied";
                            break;
                    }
                %>

                <%
                if(order.getStatus() == 1){
                %>
                <td class="border border_right" style="font-weight: bold; color: black"><%=status%></td>
                <%
                }else if(order.getStatus() == 2){
                %>
                <td class="border border_right" style="font-weight: bold; color: green"><%=status%></td>
                <%
                }else if(order.getStatus() == 3){
                %>
                <td class="border border_right" style="font-weight: bold; color: red"><%=status%></td>
                <%
                }
                %>

                <td class="border border_right">
                    <form action="DispatchController" method="post">
                        <input type="hidden" name="txtOrderID" value="<%=order.getOrderID()%>">
                        <input type="hidden" name="txtUsername" value="<%=order.getUsername()%>">
                        <input type="submit" name="btAction" value="Details">
                    </form>
                </td>
            </form>
        </tr>
        <%
            }
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
