<%-- 
    Document   : index
    Created on : May 29, 2017, 8:48:32 PM
    Author     : ldtvu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                background-image: url("ashe.jpg") 
            }
        </style>
    </head>
    <body>
        <h1>Hospital System!</h1>
        <form action="MainController" method="POST">
            <table border="0">
                <thead>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="txtUsername" value="${STUSER}" required=""/> ${ERROR.usernameError}</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="txtPassword" required="" /> ${ERROR.passwordError}</td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="action" value="Login" ></td>
                    </tr>
                </tbody>
            </table>
            <c:url var="createLink" value="MainController" >
                <c:param name="action" value="Register"/>
                <c:param name="username" value="${requestScope.IDEMP}"/>
            </c:url>
            <a href="${createLink}"> ${REGIST} </a>
        </form>
    </body>
</html>
