<%-- 
    Document   : searchProduct
    Created on : Oct 20, 2023, 2:14:04 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="product.*" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%
        ProductDAO dao = new ProductDAO();
        List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("SEARCH_LIST");
        if(listProduct != null){
        %>
        <div class="container bg-3 mt-5">
            <h3 class="text-center text-danger">Products</h3><br>
            <div class="row" id="content">
                <%
                    String username = (String)request.getSession().getAttribute("USERNAME");
                    for(ProductDTO i : listProduct){
                %>    
                <div class="col-md-3 col-6 p-1">
                    <div class="thumbnail border">
                        <img src="<%=i.getPicture()%>" class="img-responsive" style="width:100%" alt="Image">
                    </div>
                    <div class="bg-light border p-3">
                        <p><%=i.getProductName()%></p>
                        <p>Price: <span class="text-danger"><%=i.getUnitprice()%>$</span></p>
                        <form action="DispatchController" method="POST">
                            <button class="button-cart text-black-50 rounded-1 p-2 mb-2" name="btAction" value="viewProduct">
                                Add to Cart
                            </button>
                            <input type="hidden" name="txtProductID" value="<%=i.getProductID()%>">
                            <input type="hidden" name="txtUsername" value="<%=username%>">
                        </form>
                    </div>
                </div>
                <%   
                    }
                %>
            </div>    

        </div>
        <%
            }
        %> 


        <%
            if(request.getAttribute("ERROR_NOT_FOUND") != null){
                String error_notFound = (String) request.getAttribute("ERROR_NOT_FOUND");
        %>
        <p style="color: #C63100; text-align: center; font-size: 100px"><%= error_notFound %></p>
        <%
        }
        %>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script>
            function searchByName(param) {
                var txtSearch = param.value;
                $.ajax({
                url: "/Assignment_PRJ301_final_bestSeller/SearchAjaxServlet",
                type: "get",
                data: {
                    txtSearchValue: txtSearch
                },
                success: function (data) {
                    var row = document.getElementById("content");
                    row.innerHTML = data;
                },
                error: function (xhr) {
                }
                });
            }

        </script>


    </body>
</html>
