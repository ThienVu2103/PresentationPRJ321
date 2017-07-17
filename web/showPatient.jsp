<%-- 
    Document   : showPatient
    Created on : Jun 19, 2017, 8:51:05 PM
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
        
        
        
        <c:if test="${not empty requestScope.LISTINFO}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Patient ID</th>
                        <th>Patient Name</th>
                        <th>Surgery Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.LISTINFO}" var="dto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.patientId}</td>
                            <td>${dto.patientName}</td>
                            <td>${dto.surgeryName}</td>
<!--                            <td>${dto.surgeryId}</td>-->
                            <td>
                                <c:url var="updateSurgery" value="MainController">
                                    <c:param name="action" value="updateSurgeryRecord"/>
                                    <c:param name="txtPatientId" value="${dto.patientId}"/>
                                    <c:param name="txtPatientName" value="${dto.patientName}"/>
                                    <c:param name="txtSurgeryName" value="${dto.surgeryName}"/>
                                    <c:param name="txtSurgeryId" value="${dto.surgeryId}" />
                                    <!--them fields vào để hiện lúc update-->  
                                </c:url>
                                <a href="${updateSurgery}">Update</a>   
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
