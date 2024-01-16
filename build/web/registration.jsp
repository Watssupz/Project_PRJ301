<%-- 
    Document   : registration
    Created on : Oct 5, 2023, 9:55:50 PM
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
            <h2>Registration</h2>
            <form action="DispatchController" method="post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="txtUsername">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="txtPassword">
                </div>
                <div class="form-group">
                    <label for="repassword">Repassword</label>
                    <input type="password" id="repassword" name="txtRepassword">
                </div>
                <div class="form-group">
                    <label for="fullname">Fullname</label>
                    <input type="text" id="fullname" name="txtFullname">
                </div>
                <div class="form-group">
                    <label for="phonenumber">PhoneNumber</label>
                    <input type="text" id="phonenumber" name="txtPhonenumber">
                </div>
                <%
                    if(request.getAttribute("ERROR_USERNAME") != null){
                        String error_username = (String) request.getAttribute("ERROR_USERNAME");
                %>
                    <p style="color: #C63100"><%= error_username %></p>
                <%
                    }
                %>
                <%
                    if(request.getAttribute("ERROR_EMPTY") != null){
                        String error_empty = (String) request.getAttribute("ERROR_EMPTY");
                %>
                <p style="color: #C63100"><%=error_empty%></p>
                <%
                    }
                %>
                <%
                    if(request.getAttribute("ERROR_PASS") != null){
                        String error_pass = (String) request.getAttribute("ERROR_PASS");
                %>
                    <p style="color: #C63100"><%= error_pass%></p>
                <%
                    }
                %>
                <%
                    if(request.getAttribute("ERROR_PHONENUMBER_FORMAT") != null){
                        String error_phonenumber = (String) request.getAttribute("ERROR_PHONENUMBER_FORMAT");
                %>
                    <p style="color: #C63100"><%= error_phonenumber%></p>
                <%
                    }
                %>
                <div class="form-group">
                    <input type="submit" name="btAction" value="Registration" >
                </div>

            </form>
        </div>
    </body>
</html>