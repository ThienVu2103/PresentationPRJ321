/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Insert;

import DTO.PatientDTO;
import Validate.LoginValidate;
import Validate.PatientValidate;
import Connect.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ldtvu
 */
public class InsertPatientController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String url = "error.jsp";
    public final static String insertPatient = "insertPatient.jsp";
    public final static String showPatient = "showPatient.jsp";
    public final static String employee = "employee.jsp";
    public final static String insertSurgery = "insertSurgery.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String action = request.getParameter("action");
            String name = request.getParameter("txtUser");
            request.setAttribute("USER", name);
            
            if (action.equalsIgnoreCase("insertPatient")) {
                url = insertPatient;
            } else if (action.equalsIgnoreCase("Add")) {

                // <editor-fold defaultstate="collapsed" desc="Declare">
                String Id = request.getParameter("txtId");
                String Name = request.getParameter("txtName");
                String gender = request.getParameter("rdGender");
                String dob = request.getParameter("txtDob");
                String newDob = dob.replace("T", " ");

                String Addr = request.getParameter("txtAddr");
                String Email = request.getParameter("txtEmail");
                String Phone = request.getParameter("txtPhone");
                String Pass = request.getParameter("txtPatientPass");
                String[] status = request.getParameterValues("ckStatus");
                //</editor-fold>
                RegistrationDAO dao = new RegistrationDAO();
                PatientValidate validate = new PatientValidate();
                boolean check = true;
                String stt = new String();
                PatientDTO dto = new PatientDTO(Id, Name, gender, dob,
                        Addr, Email, Phone, Pass, "");

                if (status == null) {
                    validate.setStatusError("Not choose yet");
                    check = false;
                } else {//neu nguoi dung ko check bat ki box nao.

                    for (int i = 0; i < status.length; i++) {
                        stt = stt + status[i] + ",";
                    }

                    request.setAttribute("STATUS", stt);
                    dto = new PatientDTO(Id, Name, gender, dob,
                            Addr, Email, Phone, Pass, stt);
                }

                //<editor-fold defaultstate="collapsed" desc="Check">
                if (!Id.matches("(P-ABCD-)[0-9]{1,}")) {
                    validate.setIdError("(P-ABCD-1)");
                    check = false;
                }
                if (dao.checkUser(Id, 0)) {
                    validate.setIdError("Duplicated id");
                    check = false;
                }
                if (!Name.matches("[a-zA-Z\\s]{1,30}")) {
                    validate.setNameError("not support number and special letter");
                    check = false;
                }
                if (Phone.length() < 10 || Phone.length() > 12) {
                    validate.setPhoneError("out of range [10-11]");
                    check = false;
                }

//</editor-fold>
                request.setAttribute("PATIENTINFO", dto);
                if (check) {
                    int n = dao.insertPatient(Id, Name, gender, newDob, Addr, Email, Phone, Pass, stt);
                    if (n != 0) {
                        HttpSession session = request.getSession();
                        session.setAttribute("IDPATIENT", Id);
//lấy dto để sang trag insertSurgery lấy giá trị bỏ vào.
//giống như bình thường.
                        url = insertSurgery;
                    } else {
                        url = "error.jsp";
                    }
                } else {
                    request.setAttribute("ERROR", validate);
                    url = insertPatient;
                }
            }
        } catch (Exception e) {
            log("error at insertPatientController" + e.getMessage());
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
