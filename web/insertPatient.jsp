<%-- 
    Document   : insertPatient
    Created on : Jun 17, 2017, 2:04:08 PM
    Author     : ldtvu
--%>

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
            .error {
                color: slategrey
            }
        </style>
    </head>
    <body>
        <h1>Hello ${sessionScope.USER}</h1>
        <span style="color: antiquewhite"> ${requestScope.INSERT} </span>
        <form action="MainController" method="POST" >
            <input type="hidden" name="txtUser" value="${requestScope.USER}"/>
            <!--dong nay la de khi nhap sai van giu dc USER la ai -->
            <table border="0">
                <thead>
                    <tr> 
                        <td>Patient ID</td>
                        <td> 
                            <input type="text" name="txtId" value="${requestScope.PATIENTINFO.id}" required/> 
                            <span class="error">${requestScope.ERROR.idError}</span>
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Patient Name</td>
                        <td> 
                            <input type="text" name="txtName" value="${requestScope.PATIENTINFO.name}" required/> 
                            <span class="error">${requestScope.ERROR.nameError}</span> 
                        </td>
                    </tr>
                    <tr>
                        <td> Gender </td>
                        <td> 
                            <input type="radio" name="rdGender" value="true" <c:if test="${requestScope.PATIENTINFO.sex eq 'true' or requestScope.PATIENTINFO.sex eq null}">checked=""</c:if> /> Male
                            <input type="radio" name="rdGender" value="false"<c:if test="${requestScope.PATIENTINFO.sex eq 'false'}">checked=""</c:if>  /> Female
                        </td>
                    </tr>
                    <tr>
                        <td>DOB</td>
                        <td> 
                            <input type="date" name="txtDob" value="${requestScope.PATIENTINFO.date}" required=""/> 
                        <span class="error">${requestScope.ERROR.dobError} </span>
                        </td>
                    </tr>
                    <tr>
                        <td>Patient address</td>
                        <td> 
                            <input type="text" name="txtAddr" value="${requestScope.PATIENTINFO.addr}" required/> 
                        </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td> <input type="email" name="txtEmail" value="${requestScope.PATIENTINFO.email}" required/> </td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td> 
                            <input type="number" name="txtPhone" value="${requestScope.PATIENTINFO.phone}" required minlength="10" maxlength="11"/> 
                        </td>
                    </tr>
                    <tr>
                        <td>Patient pass</td>
                        <td> <input type="text" name="txtPatientPass" value="password" readonly=""/> </td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>
                            <input type="checkbox" name="ckStatus" value="HIV" <c:if test="${requestScope.STATUS.contains('HIV')}"> checked="" </c:if>> HIV
                            <input type="checkbox" name="ckStatus" value="CANCER" <c:if test="${requestScope.STATUS.contains('CANCER')}"> checked=""</c:if> /> CANCER
                            <input type="checkbox" name="ckStatus" value="PREGNANT" <c:if test="${requestScope.STATUS.contains('PREGNANT')}">checked=""</c:if>/> PREGNANT
                            <br>
                            <span class="error">${requestScope.ERROR.statusError}</span>
                        </td>
                    </tr>
                    <tr>
                    <td>
                        <input type="submit" name="action" value="Add" />
                    </td>
                    
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
