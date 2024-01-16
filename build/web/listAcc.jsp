<%-- 
    Document   : listAcc
    Created on : Oct 4, 2023, 5:11:50 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="account.AccountDTO" %>
<%@page import="account.AccountDAO" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account</title>
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
        <h3 style="margin: 30px">LIST ACCOUNTS</h3>

        <%
            List<AccountDTO> result = (List<AccountDTO>) request.getAttribute("RESULT");
            if(result != null && !result.isEmpty()){ 
        %>
        <table style="width: 90%">
            <tr style="background-color: #DFD6CD">
                <th class="border border_right" style="text-align: center">Username</th>
                <th class="border border_right" style="text-align: center">Password</th>
                <th class="border border_right" style="text-align: center">Fullname</th>
                <th class="border border_right" style="text-align: center">Phonenumber</th>
                <th class="border border_right" style="text-align: center">Email</th>
                <th class="border border_right" style="text-align: center">Role</th>
                <th class="border border_right" style="text-align: center;  width: 300px">Edit</th>
            </tr>
            <%
            for(AccountDTO acc : result){
            %>
            <tr>
            <form action="DispatchController" method="post">

                <td class="border border_right"><%=acc.getUsername()%></td>
                <td class="border border_right"><%=acc.getPassword()%></td>
                <td class="border border_right"><%=acc.getFullname()%></td>
                <td class="border border_right"><%=acc.getPhoneNumber()%></td>
                <td class="border border_right"><%=acc.getEmail()%></td>
                <!--kiểm tra admin-->
                <%
                if(acc.isRole()){
                %>
                <td class="border border_right"><input type="checkbox" name="chkAdmin" value="Admin" checked="checked"></td>
                    <%
                    } else {
                    %>
                <td class="border border_right"><input type="checkbox" name="chkAdmin" value="User"></td>
                    <%
                    }
                    %>
                <!--update delete button-->
                <td class="border border_right border_bottom">
                    <input type="hidden" name="txtUsername" value="<%=acc.getUsername()%>">
                    <button class="btn btn-secondary" type="submit" value="Update" name="btAction">Update</button>
                    <button class="btn btn-secondary" type="submit" value="Delete" name="btAction">Delete</button>
                </td>
            </form>
        </tr>
        <%
        }
    }
        %>
    </table>
</body>
</html>
