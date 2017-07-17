/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Update;

import DTO.PatientDTO;
import Connect.RegistrationDAO;
import Validate.LoginValidate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class UpdateController extends HttpServlet {

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

    public int checkDate(String test) {
        int n = -1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = sdf.parse(test);
            if (!sdf.format(date).equals(test)) {
                n = 0;
                System.out.println("invalid date");
            } else {
                n = 1;
            }
        } catch (ParseException ex) {
            n = 0;
        }
        return n;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
// <editor-fold defaultstate="collapsed" desc="Declare">
            String id = request.getParameter("txtId");
            String name = request.getParameter("txtName");
            String gender = request.getParameter("rdGender");
            String jspDob = request.getParameter("txtDob");
            String dob = jspDob.replace("T", " ");
            String addr = request.getParameter("txtAdd");
            String email = request.getParameter("txtEmail");
            String phone = request.getParameter("txtPhone");

            RegistrationDAO dao = new RegistrationDAO();
            LoginValidate validate = new LoginValidate();
            PatientDTO dto = new PatientDTO(id, name, gender, jspDob, addr, email, phone);

            request.setAttribute("ERROR", validate);
            request.setAttribute("PATIENTINFO", dto);
            boolean check = true;

            if (!phone.matches("[0-9]{9,11}")) {
                validate.setPhoneError("invalid phone number (01887213280|0981875044) ");
                check = false;
            }

            if (!addr.matches("[a-zA-Z0-9\\s[/]]{1,}")) {
                validate.setAddrError("not support special letter");
                check = false;
            }
// </editor-fold>

            if (!name.matches("[a-zA-Z\\s]{2,30}")) {
                validate.setNameError("not support special letter and number");
                check = false;
            }

            if (check == false) {
                url = "patient.jsp";
            } else {
                int result = dao.updatePatient(id, name, gender, dob, addr, email, phone);
                request.setAttribute("NOTI", "update complete!!!");
                url = "patient.jsp";
                if (result == 0) {
                    System.out.println("sai sql");
                    url = "error.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
