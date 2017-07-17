/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Insert;

import DTO.PatientDTO;
import Connect.RegistrationDAO;
import Controller.MainController;
import Controller.Update.updateSurgeryController;
import static Controller.Update.updateSurgeryController.checkDateTimeLocal;
import DTO.SurgeryDTO;
import Validate.SurgeryValidate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ldtvu
 */
public class InsertSurgeryController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public String url = "error.jsp";
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String action = request.getParameter("action");
            RegistrationDAO dao = new RegistrationDAO();

            if (action.equalsIgnoreCase("add")) {
                url = MainController.insertSur;
            } else if (action.equalsIgnoreCase("Done")) {

                String surgeryId = request.getParameter("txtSurgeryId");
                String processSurgery = request.getParameter("txtProcess");
                
                String timeStart = request.getParameter("txtTimeStart");
                String newTimeStart = timeStart.replace("T", " ");
                
                String timeEnd = request.getParameter("txtTimeEnd");
                String newTimeEnd = timeEnd.replace("T", " ");
                
                String createEmpt = request.getParameter("txtEmployee");
                
                String dateCreate = request.getParameter("txtDateCreate");
                String newDateCreate = dateCreate.replace("T", " ");
                
                String status = request.getParameter("rdStatus");
                String patientId = request.getParameter("txtPatientId");
                String surgeryName = request.getParameter("txtSurgeryName");
                String OpeDoctor = request.getParameter("txtOperated");
                String anes = request.getParameter("txtAnes");

                boolean check = false;
                SurgeryValidate validate = new SurgeryValidate();
                SurgeryDTO dto = new SurgeryDTO(surgeryId, processSurgery,
                        timeStart, timeEnd, createEmpt, dateCreate, status, surgeryName, OpeDoctor, anes);
                
//<editor-fold defaultstate="collapsed" desc="check">
                if (!surgeryId.matches("(S-ABCD-)[0-9]{1,}")) {
                    validate.setSurgeryIdError("S-ABCD-");
                    check = true;
                }
                if(dao.checkSurgeryId(surgeryId)){
                    validate.setSurgeryIdError("Duplicated Id");
                    check = true;
                }
                if (!checkDateTimeLocal(newDateCreate, newTimeStart)) {
                    validate.setDateCreateError("time create have to below time start");
                    validate.setTimeStartError("time create have to below time start");
                    check = true;
                }
                if (!checkDateTimeLocal(newTimeStart, newTimeEnd)) {
                    validate.setTimeStartError("time start have to below time end");
                    validate.setTimeEndError("time start have to below time end");
                    check = true;
                }
//</editor-fold>

                if (check) {
                    request.setAttribute("ERROR", validate);
                    request.setAttribute("SURGERYINFO", dto);
                    url = MainController.insertSur;
                } else {
                    int n = dao.insertSurgery(surgeryId, processSurgery, newTimeStart,
                            newTimeEnd, createEmpt, newDateCreate, status, patientId,
                            surgeryName, OpeDoctor, anes);
                    if (n != 0) {
                        url = MainController.employee;
                    } else {
                        url = MainController.error;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at InsertSurgeryController " + e.getMessage());
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
