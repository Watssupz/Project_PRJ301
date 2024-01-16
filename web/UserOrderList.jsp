<%-- 
    Document   : UserOrderList
    Created on : Nov 1, 2023, 12:57:09 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "orderDetails.*" %>
<%@page import = "order.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>
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

        <h3 style="margin: 30px">Order Details</h3>

        <%
            String USER = (String) request.getSession().getAttribute("USERNAME");            
            List<OrderDetailsDTO> list = (List<OrderDetailsDTO>) request.getAttribute("ORDER_DETAILS_LIST");
            if(list != null && !list.isEmpty()){
        %>
        <table style="width: 70%">
            <tr style="background-color: #DFD6CD">
                <th class="border border_right" style="text-align: center">NO.</th>
                <th class="border border_right" style="text-align: center">Product Name</th>
                <th class="border border_right" style="text-align: center">Quantity</th>
                <th class="border border_right" style="text-align: center">Price</th>
                <th class="border border_right" style="text-align: center">Picture</th>
            </tr>
            <%
                int a = 1;
                double total = 0;
                int orderID = 0;
                for(OrderDetailsDTO i : list){
                orderID = i.getOrderID();
            %>

            <form action="DispatchController" method="post">
                <tr>
                    <input type="hidden" name="txtOrderID" value="<%=i.getOrderID()%>">
                    <td class="border border_right"><%=a%></td>
                    <td class="border border_right"><%=i.getProductName()%></td>
                    <td class="border border_right"><%=i.getQuantityOrdDetail()%></td>
                    <td class="border border_right"><%=i.getUnitprice()%> $</td>
                    <td style="border: 1px solid #CED4DA"><img src="<%=i.getPicture()%>" alt="anh" class="img-responsive" style="width: 200px;"/></td>
                </tr>
                <%  
                    total += i.getQuantityOrdDetail() * i.getUnitprice();
                    a++;
                    }
                %>
                <tr>
                    <td colspan="4" class="border border_right">
                        Total:
                    </td>
                    <td class="border border_right">
                        <%=total%> $
                    </td>
                </tr>
                <%
                    AccountDAO dao = new AccountDAO();
                    OrderDAO order = new OrderDAO();
                    if(dao.checkAdmin(USER)){
                        if(order.getStatus(orderID) == 1){
                %>
                <tr>
                    <td class="border border_right" colspan="5">
                        <input type="submit" name="btAction" value="Accept">
                        <input type="submit" name="btAction" value="Deny">
                    </td>
                </tr>

                <%
                        }
                    }
                %>

            </form>
        </table>
        <%    
            }  
        %>
    </body>





    <%--
    <body>

    <%@include file="header.jsp" %>

    <h3 style="margin: 30px">Order Details</h3>

    <%
        String USER = (String) request.getSession().getAttribute("USERNAME");            
        List<OrderDetailsDTO> list = (List<OrderDetailsDTO>) request.getAttribute("ORDER_DETAILS_LIST");
        if(list != null && !list.isEmpty()){
    %>
    <table style="width: 70%">
        <tr style="background-color: #DFD6CD">
            <th class="border border_right" style="text-align: center">NO.</th>
            <th class="border border_right" style="text-align: center">Product Name</th>
            <th class="border border_right" style="text-align: center">Quantity</th>
            <th class="border border_right" style="text-align: center">Price</th>
            <th class="border border_right" style="text-align: center">Picture</th>
        </tr>
        <%
            int a = 1;
            double total = 0;
            for(OrderDetailsDTO i : list){
                int orderID = i.getOrderID();
        %>

        <form action="DispatchController" method="post">
            <tr>
            <input type="hidden" name="txtOrderID" value="<%=i.getOrderID()%>">
            <td class="border border_right"><%=a%></td>
            <td class="border border_right"><%=i.getProductName()%></td>
            <td class="border border_right"><%=i.getQuantityOrdDetail()%></td>
            <td class="border border_right"><%=i.getUnitprice()%></td>
            <td style="border: 1px solid #CED4DA"><img src="<%=i.getPicture()%>" alt="anh" class="img-responsive" style="width: 200px;"/></td>
            </tr>
            </form>
            <%
                total += i.getQuantityOrdDetail() * i.getUnitprice();
                a++;
            %>
            <%  
                AccountDAO dao = new AccountDAO();
                OrderDAO order = new OrderDAO();
                if(dao.checkAdmin(USER)){
                    if(order.getStatus(orderID) == 1){
            %>
            <form action="DispatchController" method="post">
            <tr>
                <td colspan="4" class="border border_right">
                    Total:
                </td>
                <td class="border border_right">
                    <%=total%>
                </td>
                <td class="border border_right" colspan="5">
                    <input type="submit" name="btAction" value="Accept">
                    <input type="submit" name="btAction" value="Deny">
                </td>
            </tr>
            </form>
            <%
                    }
                }
            }
            %>
    </table>
    <%    
        }
    %>
</body>
    --%>


</html>