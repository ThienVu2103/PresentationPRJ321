<%-- 
    Document   : employee
    Created on : Jun 7, 2017, 7:39:53 PM
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
                background-image: url("bg.jpg")
            }
        </style>
    </head>
    <body>
        <h1>Hello ${sessionScope.USER}!</h1>

        <form action="MainController" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <td>Patient Name</td>
                        <td> 
                            <input type="search" name="txtSearch" required=""/> 
                            <input type="hidden" name="txtNameEmp" value="${sessionScope.USER}"/>
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td> <input type="submit" name="action" value="Search"/> </td>
                        <td> <input type="reset" name="action" value="Reset"/> </td>
                    </tr>
                </tbody>
            </table>
            <c:if test="${requestScope.LISTINFO.size() == 0}">
                <span>No record found</span>
            </c:if>
                <br/>
            <c:url var="insertPatientLink" value="MainController">
                <c:param name="action" value="insertPatient"/>
                <c:param name="txtUser" value="${sessionScope.USER}"/>
            </c:url>
            <a href="${insertPatientLink}" > Insert Patient </a> <br>

        </form>
    </body>
</html>
