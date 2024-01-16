<%-- 
    Document   : listProduct
    Created on : Oct 20, 2023, 4:08:44 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="product.ProductDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Product</title>
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
        <h3 style="margin: 30px">LIST PRODUCT</h3>

        <%
            List<ProductDTO> listProd = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if(listProd != null && !listProd.isEmpty()){ 
        %>
        <table style="width: 90%">
            <tr style="background-color: #DFD6CD">
                <th class="border border_right" style="text-align: center">Product ID</th>
                <th class="border border_right" style="text-align: center">Category ID</th>
                <th class="border border_right" style="text-align: center">Product Name</th>
                <th class="border border_right" style="text-align: center">Quantity</th>
                <th class="border border_right" style="text-align: center">Price</th>
                <th class="border border_right" style="text-align: center">Picture</th>
                <th class="border border_right" style="text-align: center">Action</th>
            </tr>
            <%
            boolean isEvenRow = false; // Để xác định hàng chẵn hoặc lẻ.
            for(ProductDTO prod : listProd){
            %>
            <tr>
            <form action="DispatchController" method="post">

                <td class="border border_right"><%=prod.getProductID()%></td>
                <td class="border border_right"><%=prod.getCategoryID()%></td>
                <td class="border border_right"><%=prod.getProductName()%></td>
                <td class="border border_right"><input type="text" name="txtQuantity" value="<%=prod.getQuantity()%>"></td>
                <td class="border border_right"><input type="text" name="txtPrice" value="<%=prod.getUnitprice()%>">$ </td>
                <!--<td class="border border_right"><%=prod.getPicture()%></td>-->
                <td style="border: 1px solid #CED4DA"><img src="<%=prod.getPicture()%>" alt="anh" class="img-responsive" style="width: 200px;"/></td>


                <!--update delete button-->
                <td class="border border_right border_bottom">
                    <input type="hidden" name="txtProductID" value="<%=prod.getProductID()%>">
                    <button class="btn btn-secondary" type="submit" value="update product" name="btAction">Update</button>
                    <button class="btn btn-secondary" type="submit" value="delete product" name="btAction">Delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    }
    %>
</table>

</body>
</html>
