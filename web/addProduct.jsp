<%-- 
    Document   : addProduct
    Created on : Oct 18, 2023, 9:39:02 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
        <style>
            .row{
                margin: 20px;
            }
            .container{
                background-color: #DFD6CD;
                padding: 20px 10px;
                border-radius: 5px;
            }
        </style>

    </head>
    <body>
        <%@include file="header.jsp" %>

        <h3 style="text-align: center; margin: 20px;">ADD PRODUCT</h3>
        <div class="container">
            <form action="DispatchController" method="post">
                <div class="row">
                    <div class="col-md-3" style="text-align: center;"> 
                        <label for="ProductName" >Product Name</label> 
                    </div>
                    <div class="col-md-9" > 
                        <input class="form-control" type="text" name="txtProductName" placeholder="ABC"> 
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3" style="text-align: center;"> 
                        <label for="Category">Category</label> 
                    </div>
                    <div class="col-md-9"> 
                        <select class="form-control" name="txtCategoryID">
                            <option value="nike">Nike</option>
                            <option value="adidas">Adidas</option>
                            <option value="vans">Vans</option>
                            <option value="converse">Converse</option>
                        </select> 
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3" style="text-align: center;"> 
                        <label for="quantity" >Quantity</label> 
                    </div>
                    <div class="col-md-9" > 
                        <input class="form-control" type="number" name="txtQuantity" min="1" placeholder="1"> 
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3" style="text-align: center;"> 
                        <label for="price" >Price</label> 
                    </div>
                    <div class="col-md-9" > 
                        <input class="form-control" type="number" name="txtPrice" min="1" placeholder="1"> 
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3" style="text-align: center;"> 
                        <label for="picture" >Picture</label> 
                    </div>
                    <div class="col-md-9" > 
                        <input class="form-control" type="text" name="txtPicture" placeholder="image link"> 
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-3" style="text-align: center;"> 
                        <label for="picture" >Description</label> 
                    </div>
                    <div class="col-md-9" > 
                        <input class="form-control" type="text" name="txtDescription"> 
                    </div>
                </div>
                
                <div class="row bt" style="margin-top: 10px; margin-bottom: unset" > 
                    <input class="form-control" style="width: fit-content; margin: auto; " type="submit" name="btAction" value="ADD"> 
                </div>
            </form>    
            <%
                if(request.getAttribute("SUCCESS") != null){
                String success = (String) request.getAttribute("SUCCESS");
            %>
            <p style="color: #C63100; text-align: center; margin-bottom: unset "><%=success%></p>
            <%
                }
            %> 

            <%
                if(request.getAttribute("ERROR_EXISTED") != null){
                String error_existed = (String) request.getAttribute("ERROR_EXISTED");
            %>
            <p style="color: #C63100; text-align: center; margin-bottom: unset "><%=error_existed%></p>
            <%
                }
            %>

            <%
                if(request.getAttribute("ERROR_EMPTY") != null){
                String error_empty = (String) request.getAttribute("ERROR_EMPTY");
            %>
            <p style="color: #C63100; text-align: center; margin-bottom: unset "><%=error_empty%></p>
            <%
                }
            %>

        </div>

    </body>
</html>
