<%-- 
    Document   : insertSurgery
    Created on : Jun 20, 2017, 7:49:04 PM
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
        <form action="MainController" method="POST" >
            
            <table border="1">
                <thead>
                    <tr>
                        <td>Surgery ID</td>
                        <td>
                            <input type="text" name="txtSurgeryId" value="${requestScope.SURGERYINFO.surgeryId}"
                                   required="" maxlength="15"/> 
                            ${requestScope.ERROR.surgeryIdError}
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Process of Surgery</td>
                        <td>
                            <input type="text" name="txtProcess" value="${requestScope.SURGERYINFO.processSurgery}" required="" />
                        </td>
                    </tr>
                    <tr>
                        <td>Time of start</td>
                        <td>
                            <input type="datetime-local" name="txtTimeStart" value="${requestScope.SURGERYINFO.timeStart}"
                                   required="" /> 
                        ${requestScope.ERROR.timeStartError}
                        </td>
                    </tr>
                    <tr>
                        <td>Time of end</td>
                        <td>
                            <input type="datetime-local" name="txtTimeEnd" value="${requestScope.SURGERYINFO.timeEnd}" required=""/> 
                        ${requestScope.ERROR.timeEndError}
                        </td>
                    </tr>
                    <tr>
                        <td>Create Employee</td>
                        <td>
                            <input type="text" name="txtEmployee" value="${sessionScope.ID}" readonly=""/> 
                        </td>
                    </tr>
                    <tr>
                        <td>Date of create</td>
                        <td>
                            <input type="datetime-local" name="txtDateCreate" value="${requestScope.SURGERYINFO.dateCreate}" />
                        ${requestScope.ERROR.dateCreateError}
                        </td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>  
                            <input type="radio" name="rdStatus" value="done" <c:if test="${requestScope.SURGERYINFO.status eq 'done'}" > checked=""</c:if> /> Done
                            <input type="radio" name="rdStatus" value="doing" <c:if test="${requestScope.SURGERYINFO.status eq 'doing' or requestScope.SURGERYINFO.status eq null}"> checked=""</c:if> /> Doing
                        </td>
                    </tr>
                    <tr>
                        <td>Patient Id</td>
                        <td>
                            <input type="text" name="txtPatientId" value="${sessionScope.IDPATIENT}" readonly="" />
                        </td>
                    </tr>
                    <tr>
                        <td>Surgery Name</td>
                        <td>
                            <input type="text" name="txtSurgeryName" value="${requestScope.SURGERYINFO.surgeryName}" required=""/>
                        </td>
                    </tr>
                    <tr>
                        <td>Operated Doctor</td>
                        <td>
                            <input type="text" name="txtOperated" value="${sessionScope.USER}" readonly=""/>
                        </td>
                    </tr>
                    <tr>
                        <td>Anesthesiologist</td>
                        <td>
                            <input type="text" name="txtAnes" value="${requestScope.SURGERYINFO.anes}" required=""/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" name="action" value="Done"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
