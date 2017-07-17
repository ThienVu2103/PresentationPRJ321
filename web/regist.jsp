<%-- 
    Document   : regist
    Created on : Jun 13, 2017, 6:50:10 PM
    Author     : ldtvu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <h1>Register!</h1>
        <form action="MainController" method="POST">
            <table border="0">
                <thead>
                    <tr>
                        <td>Employee Id</td>
                        <td>
                            <input type="text" name="txtId" value="${requestScope.IDEMP}" required="" /> 
                            ${requestScope.ERROR.empIdError}
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td> Employee Name </td>
                        <td> 
                            <input type="text" name="txtName" value="${requestScope.INFO.empName}" required=""> 
                            ${requestScope.ERROR.empNameError}
                        </td>
                    </tr>
                    <tr>
                        <td> Identity card </td>
                        <td> 
                            <input type="text" name="txtCard" value="${requestScope.INFO.identityCard}"required=""/> 
                            ${requestScope.ERROR.identityCardError}
                        </td>
                    </tr>
                
                    <tr>
                        <td> Gender </td>
                        <td>
                            <input type="radio" name="rdGender" value="1" <c:if test="${requestScope.INFO.gender eq '1' or requestScope.INFO.gender eq null}">checked </c:if> > Male 
                            <input type="radio" name="rdGender" value="0" <c:if test="${requestScope.INFO.gender eq '0'}"> checked </c:if> > Female
                        </td>
                    </tr>
                    <tr>
                        <td> DOB </td>
                        <td> 
                            <input type="date" name="txtDob" value="${requestScope.INFO.dob}" required=""/> 
                            ${requestScope.ERROR.dobError}
                        </td>
                    </tr>
                    <tr>
                        <td>Faculty</td>
                        <td> 
                            <select name="facultyList">
                                <option value="allergy" <c:if test="${requestScope.INFO.faculty eq 'allergy'}"> selected </c:if> > Allergy </option>
                                <option value="plastic" <c:if test="${requestScope.INFO.faculty eq 'plastic'}"> selected </c:if> > Plastic surgery </option>
                                <option value="urology" <c:if test="${requestScope.INFO.faculty eq 'urology'}"> selected </c:if> > Urology </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Position</td>
                        <td>
                            <select name="posList">
                                <option value="director" <c:if test="${requestScope.INFO.pos eq 'director'}"> selected </c:if>> Director </option>
                                <option value="manager" <c:if test="${requestScope.INFO.pos eq 'manager'}"> selected </c:if> > Manager </option>
                                <option value="doctor" <c:if test="${requestScope.INFO.pos eq 'doctor'}"> selected </c:if> > Doctor </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Employee Address</td>
                        <td>
                            <input type="text" name="txtAddress" value="${requestScope.INFO.addr}" required=""/>
                        ${requestScope.ERROR.addrError}
                        </td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td> 
                            <input type="number" name="txtPhone" value="${requestScope.INFO.phone}" required=""/> 
                            ${requestScope.ERROR.phoneError}
                        </td>
                    </tr>
                    <tr>
                        <td> Email </td>
                        <td> 
                            <input type="email" name="txtEmail" value="${requestScope.INFO.email}"required="" /> 
                            ${requestScope.ERROR.emailError}    
                        </td>
                    </tr>
                    <tr>
                        <td> EmpPass </td>
                        <td>
                            <input type="password" name="txtPass" required=""/>
                            ${requestScope.ERROR.empPassError}
                        </td>
                    </tr>
                    <tr>
                        <td>Confirm EmpPass </td>
                        <td>
                            <input type="password" name="txtConfPass" required=""/>
                            ${requestScope.ERROR.empConfPass}
                        </td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>
                            <input type="radio" name="rdStatus" value="available" <c:if test="${requestScope.INFO.status eq 'available' or requestScope.INFO.gender eq null}"> checked </c:if> /> Available
                            <input type="radio" name="rdStatus" value="working" <c:if test="${requestScope.INFO.status eq 'working'}"> checked</c:if> /> Working
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" name="action" value="Enroll" />
        </form>
    </body>
</html>
