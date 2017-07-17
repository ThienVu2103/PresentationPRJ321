/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ldtvu
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public final static String employee = "employee.jsp";
    public final static String regist = "regist.jsp";
    public final static String login = "LoginController";
    public final static String update = "UpdateController";
    public final static String search = "SearchController";
    public final static String error = "error.jsp";
    public final static String register = "RegistController";
    public final static String insertPatient = "InsertPatientController";
    public final static String insertSurgery = "InsertSurgeryController";
    public final static String updateSurgeryRecord = "updateSurgeryController";
    public final static String insertPat = "insertPatient.jsp";
    public final static String patient = "patient.jsp";
    public final static String index = "index.jsp";
    public final static String updateRecord = "updateRecord.jsp";
    public final static String insertSur = "insertSurgery.jsp";
    public final static String showPatient = "showPatient.jsp";
    public String url = error;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String action = request.getParameter("action");
            if(action.equalsIgnoreCase("Login")) {
                url = login;
            } else if(action.equalsIgnoreCase("Search")){
                url = search;
            } else if(action.equalsIgnoreCase("Update")){
                url = update;
            } else if(action.equalsIgnoreCase("Register") || action.equalsIgnoreCase("Enroll")){
                url = register;
            } else if (action.equalsIgnoreCase("insertPatient") || action.equalsIgnoreCase("add")){
                url = insertPatient;
            } else if(action.equalsIgnoreCase("insertSurgery") || action.equalsIgnoreCase("Done")) {
                url = insertSurgery;
            } else if(action.equalsIgnoreCase("updateSurgeryRecord") || action.equalsIgnoreCase("UpdateSurgery")){
                url = updateSurgeryRecord;
            }
        } catch (Exception e) {
            log("Error at MainController"  + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
