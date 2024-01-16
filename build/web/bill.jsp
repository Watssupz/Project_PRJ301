<%-- 
    Document   : bill
    Created on : Oct 30, 2023, 5:21:35 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "orderDetails.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill</title>
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

        <h3 style="margin: 30px">Bill</h3>

        <%
            List<OrderDetailsDTO> list = (List<OrderDetailsDTO>) request.getAttribute("LIST_DETAILS");
            double totalBill = (double) request.getAttribute("TOTAL_BILL");
            if(list != null && !list.isEmpty()){
        %>
        <table style="width: 70%">
            <tr style="background-color: #DFD6CD">
                <th class="border border_right" style="text-align: center">NO.</th>
                <th class="border border_right" style="text-align: center">Product Name</th>
                <th class="border border_right" style="text-align: center">Picture</th>
                <th class="border border_right" style="text-align: center">Quantity</th>
                <th class="border border_right" style="text-align: center">Price</th>
            </tr>
            <%
                int a = 1;
                for(OrderDetailsDTO i : list){
            %>
            <form action="DispatchController" method="post">
                <tr>
                    <td class="border border_right"><%=a%></td>
                    <td class="border border_right"><%=i.getProductName()%></td>
                    <td style="border: 1px solid #CED4DA"><img src="<%=i.getPicture()%>" alt="anh" class="img-responsive" style="width: 200px;"/></td>
                    <td class="border border_right"><%=i.getQuantityOrdDetail()%></td>
                    <td class="border border_right"><%=i.getUnitprice()%></td>
                </tr>
                <%  
                    a++;
                    }
                %>
                <tr>
                    <td colspan="3" class="border border_right">TOTAL:</td>
                    <td colspan="2"><%=totalBill%></td>
                </tr>

                <td colspan="7" class="border border_right">
                    <input class="btn btn-secondary" type="submit" name="btAction" value="Buy">
                </td>
            </form>
        </table>
        <%    
            }
        %>
    </body>
</html>

