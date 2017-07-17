<%-- 
    Document   : patient
    Created on : Jun 7, 2017, 7:39:00 PM
    Author     : ldtvu
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <h1> <a style="float: left">Update Profile</a> <a style="float: right"> Welcome, ${sessionScope.USER}!</a></h1>
        <hr>
        <br>
        <br>
        <br>
        <form action="MainController" method="POST" >
            <table border="1">
                <thead>
                    <tr>
                        <td>PatientID</td>
                        <td> 
                            <input type="text" name="txtId" value="${requestScope.PATIENTINFO.id}" readonly> 
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PatientName</td>
                        <td>
                            <input type="text" name="txtName" value="${requestScope.PATIENTINFO.name}" required=""/> ${requestScope.ERROR.nameError}
                        </td>
                    </tr>
                    <tr>
                        <td> Gender </td>
                        <td>
                            <%-- chu y phan nay --%>
                            <input type="radio" name="rdGender" value="1" <c:if test="${requestScope.PATIENTINFO.sex eq '1'}" > checked </c:if> /> Male
                            <input type="radio" name="rdGender" value="0" <c:if test="${requestScope.PATIENTINFO.sex eq '0'}" > checked </c:if>  /> Female
                            </td>
                        </tr>
                        <tr>
                        <%-- chu y phan nay --%>
                        <td> DOB </td>
                        <td>
                            <input type="datetime-local" name="txtDob" value="${requestScope.PATIENTINFO.date}" required="" />${requestScope.ERROR.dobError}
                        </td>
                    </tr>
                    <tr>
                        <td> PatientAddr </td>
                        <td>
                            <input type="text" name="txtAdd" value="${requestScope.PATIENTINFO.addr}" required="" /> ${requestScope.ERROR.addrError}
                        </td>
                    </tr>
                    <tr>
                        <td> Email</td>
                        <td> 
                            <input type="email" name="txtEmail" value="${requestScope.PATIENTINFO.email}" required="" /> ${requestScope.ERROR.emailError}
                        </td>
                    </tr>
                    <tr>
                        <td> Phone </td>
                        <td> 
                            <input type="number" name="txtPhone" value="${requestScope.PATIENTINFO.phone}" required="" /> ${requestScope.ERROR.phoneError}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" name="action" value="Update"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <span style="float: left; font: 22; color: red"> ${requestScope.NOTI}</span>
        </form>

    </body>
</html>
