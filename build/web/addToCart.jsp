<%-- 
    Document   : addToCart
    Created on : Oct 23, 2023, 2:29:38 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="product.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add To Cart</title>
        <style >
            body{
                background-color: gray;
            }

            #detail-block{
                background-color: white;
            }

            .detail{
                margin: 10px 0px;
            }

            .button-cart{
                background-color: #F11F3B !important;
                color: white !important;
            }

        </style>
    </head>
    <body>

        <%@include file="header.jsp" %>

        <div class="container mt-5 mb-5">
            <%
                String username = (String) request.getSession().getAttribute("USERNAME");
                ProductDTO i = (ProductDTO) request.getAttribute("DTO");
                String brandName = "";
                int categoryID = i.getCategoryID();
                switch(categoryID){
                    case 1:
                        brandName = "Nike";
                        break;
                    case 2:
                        brandName = "Adidas";
                        break;
                    case 3:
                        brandName = "Vans";
                        break;
                    case 4:
                        brandName = "Converse";
                        break;
                }
            %>


            <div class="row">
                <div class="col-md-6" id="thumbnail">
                    <img class="w-100" src="<%=i.getPicture()%>" alt="">
                </div>
                <div class="col-md-6" id="detail-block" style="padding: 20px 30px;">
                    <div id="detail-content">
                        <div class="title">
                            <h3><%=i.getProductName()%></h3>
                        </div>
                        <hr class="divider">
                        <h5>Price: <span class="text-danger"><%=i.getUnitprice()%>$</span></h5>
                        <hr class="divider">
                        <div class="detail">
                            <h6>Brand: <span class="text-danger"><%=brandName%></span></h6>
                        </div>

                        <div class="detail">
                            <div class="row">
                                <form action="DispatchController" method="post">
                                    <div class="col-md-3">
                                        <input class="form-control" type="number" name="txtQuantity" min="1" placeholder="1">
                                    </div>

                                    <div class="col-md-3 ">
                                        <button class="form-control button-cart" name="btAction" value="addCart" >Add</button>
                                        <input type="hidden" name="txtUsername" value="<%=username%>">
                                        <input type="hidden" name="txtProductID" value="<%=i.getProductID()%>">
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="detail">
                            <div class="description bg-secondary text-white rounded-1 p-2">
                                <h6>Description:</h6>
                                <%=i.getDescription()%>
                            </div>
                        </div>

                    </div>

                </div>
                <%
                    if(request.getAttribute("SUCCESS") != null){
                        String success = (String) request.getAttribute("SUCCESS");
                %>
                <p style="color: #C63100; text-align: center"><%=success%></p>
                <%
                    }
                %>
            </div>

        </div>
    </body>
</html>
