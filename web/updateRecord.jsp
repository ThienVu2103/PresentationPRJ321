<%-- 
    Document   : updateSurgery
    Created on : Jun 20, 2017, 6:18:58 PM
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
        <h1>Hello ${sessionScope.USER}!</h1>
        <form action="MainController" method="POST">
            <input type="hidden" name="patientId" value="${requestScope.LISTINFO.patientId}"/>
            <input type="hidden" name="patientName" value="${requestScope.LISTINFO.patientName}"/>
            <input type="hidden" name="surgeryName" value="${requestScope.LISTINFO.surgeryName}"/>
            <input type="hidden" name="surgeryId" value="${requestScope.LISTINFO.surgeryId}"/>
            
            
            <input type="hidden" name="sId" value="${requestScope.SID}"/>
            <table border="1">
                <thead>
                    <tr>
                        <td>Patient ID</td>
                        <td>
                            <input type="text" name="txtPatientId" value="${requestScope.INFO.patientId}" readonly=""/> 
                            ${requestScope.ERROR.idError}
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td> Patient Name </td>
                        <td> 
                            <input type="text" name="txtPatientName" value="${requestScope.INFO.patientName}" required="" /> 
                            ${requestScope.ERROR.nameError}
                        </td>
                    </tr>
                    <tr>
                        <td>DOB</td>
                        <td>
                            <input type="date" name="txtDob" value="${requestScope.INFO.dob}" />
                            ${requestScope.ERROR.dobError}
                        </td>
                    </tr>
                    <tr>
                        <td>Gender</td>
                        <td>
                            <input type="radio" name="rdGender" value="1" <c:if test="${requestScope.INFO.gender eq '1'}"> checked="" </c:if> /> Male
                            <input type="radio" name="rdGender" value="0" <c:if test="${requestScope.INFO.gender eq '0'}"> checked="" </c:if> /> Female
                        </td>
                    </tr>
                    <tr>
                        <td>Surgery Name</td>
                        <td> 
                            <input type="text" name="txtSurgeryName" value="${requestScope.INFO.surgeryName}" required="" /> 
                        </td>
                    </tr>
                    <tr>
                        <td>Operated Doctor </td>
                        <td>
                            <input type="text" name="txtOpeDoctor" value="${requestScope.INFO.opDoctor}" required=""/>
                        </td>
                    </tr>
                    <tr>
                        <td>Anesthesiologist</td>
                        <td> 
                            <input type="text" name="txtAnes" value="${requestScope.INFO.anes}" required=""/> 
                        </td>
                    </tr>
                    <tr>
                        <td>Time of Start</td>
                        <td>
                            <input type="datetime-local" name="txtTimeStart" value="${requestScope.INFO.timeStart}" required=""/> ${requestScope.ERROR.timeStart}
                        </td>
                    </tr>
                    <tr>
                        <td>Time of End</td>
                        <td> 
                            <input type="datetime-local" name="txtTimeEnd" value="${requestScope.INFO.timeEnd}" required=""/> ${requestScope.ERROR.timeEnd}
                        </td> 
                    </tr>
                    <tr>
                        <td>Process of Surgery</td>
                        <td> 
                            <input type="text" name="txtProcess" value="${requestScope.INFO.processSurgery}" required="" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" name="action" value="UpdateSurgery"/>
                        </td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
