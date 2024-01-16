<%-- 
    Document   : updateInfor
    Created on : Oct 18, 2023, 1:54:57 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="bootstrap5/css/bootstrap.css">
        <style>
            body{
                background-image: url(https://i.ibb.co/0DV92kL/background.png);
                background-repeat: no-repeat;
                background-size: cover;
                background-attachment: fixed;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                color: #0c4128;
            }
            .container {
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
                width: 400px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            label {
                font-weight: bold;
                display: block;
            }
            input[type="text"], input[type="password"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            input[type="submit"] {
                background-color: #007BFF;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #0056b3;
            }

        </style>
    </head>

    <body>
        <div class="container">
            <h2>Change Password</h2>
            <form action="DispatchController" method="post">
                <div class="form-group">
                    <label for="password">Old Password</label>
                    <input type="password" id="password" name="txtOldPassword">
                </div>
                <div class="form-group">
                    <label for="password">New Password</label>
                    <input type="password" id="password" name="txtNewPassword">
                </div>
                <div class="form-group">
                    <label for="repassword">Repassword</label>
                    <input type="password" id="repassword" name="txtRepassword">
                </div>


                <div class="row">
                    <div class="col-md-6 form-group">
                        <input type="submit" name="btAction" value="Update Password">
                    </div>
                    <div class="col-md-6 form-group">
                        <!--<input type="submit" name="btAction" value="Forget Password">-->
                        <a class="btn btn-primary" href="forgetPW.jsp" style="padding: 10px 15px" >Forget Password</a>
                        <!--<button class="btn btn-secondary" type="submit" name="btAction" value="Forget Password">Forget Password</button>-->
                    </div>
                    <div class="form-group" style="text-align: center">
                        <a class="btn btn-secondary" href="home.jsp" style="padding: 10px 15px;" >Home</a>
                    </div>
                </div>

            </form>
            <%
                if(request.getAttribute("ERROR_OLD_PASS") != null){
                    String error_oldPass = (String) request.getAttribute("ERROR_OLD_PASS");
            %>
            <p style="color: #C63100"><%= error_oldPass%></p>
            <%
                }
            %>

            <%
            if(request.getAttribute("ERROR_NEW_PASS") != null){
                String error_newPass = (String) request.getAttribute("ERROR_NEW_PASS");
            %>
            <p style="color: #C63100"><%= error_newPass%></p>
            <%
                }
            %>

            <%
            if(request.getAttribute("ERROR_RE_PASS") != null){
                String error_rePass = (String) request.getAttribute("ERROR_RE_PASS");
            %>
            <p style="color: #C63100"><%= error_rePass%></p>
            <%
                }
            %>

            <%
            if(request.getAttribute("ERROR_EMPTY") != null){
                String error_empty = (String) request.getAttribute("ERROR_EMPTY");
            %>
            <p style="color: #C63100"><%= error_empty%></p>
            <%
                }
            %>
        </div>
    </body>
</html>