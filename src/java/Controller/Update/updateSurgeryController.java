/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Update;

import Connect.RegistrationDAO;
import Controller.MainController;
import DTO.ObjectSearch;
import DTO.OldDTO;
import DTO.PatientDTO;
import DTO.PatientSurgeryDTO;
import DTO.SurgeryDTO;
import Validate.PatientValidate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ldtvu
 */
public class updateSurgeryController extends HttpServlet {

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

    public static double calDay(int a, int b, int c) {
        double bb = (b * 1.0) / 12;
        double cc = (c * 1.0) / 365;
        double result = a + bb + cc;
        return result;
    }

    public static double calHour(int a, int b) {
        double bb = (b * 1.0) / 60;
        double result = a + bb;
        return result;
    }

    public static boolean checkDateTimeLocal(String start, String end) {
        StringTokenizer st = new StringTokenizer(start, "- :");

        int sy = Integer.parseInt(st.nextToken());
        int sM = Integer.parseInt(st.nextToken());
        int sd = Integer.parseInt(st.nextToken());
        int sh = Integer.parseInt(st.nextToken());
        int sm = Integer.parseInt(st.nextToken());

        double s = calDay(sy, sM, sd);
        double sth = calHour(sh, sm);
        StringTokenizer se = new StringTokenizer(end, "- :");

        int ey = Integer.parseInt(se.nextToken());
        int eM = Integer.parseInt(se.nextToken());
        int ed = Integer.parseInt(se.nextToken());
        int eh = Integer.parseInt(se.nextToken());
        int em = Integer.parseInt(se.nextToken());

        double e = calDay(ey, eM, ed);
        double enh = calHour(eh, em);

        boolean check = false;
        if (s < e) {
            check = true;
        }
        if (s == e) {
            if (sth < enh) {
                check = true;
            } else {
                check = false;
            }
        }
        return check;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            RegistrationDAO dao = new RegistrationDAO();

            String action = request.getParameter("action");

            String pId = request.getParameter("txtPatientId");
            String pName = request.getParameter("txtPatientName");
            String sName = request.getParameter("txtSurgeryName");
            
            //gửi lên lại để khi lúc sau 1 request gửi đên ko bị mất
            String sex = dao.findGenderPatient(pId);
            String dateOb = dao.findDateOBById(pId);

            if (action.equalsIgnoreCase("updateSurgeryRecord")) {
                String sID = request.getParameter("txtSurgeryId");
                SurgeryDTO surgery = dao.findSurgeryRecord(sID);

                String timeOfStart = surgery.getTimeStart().replace(" ", "T");
                String timeOfEnd = surgery.getTimeEnd().replace(" ", "T");
                String opeDoc = surgery.getOpeDoctor();
                String anesth = surgery.getAnes();
                String processSur = surgery.getProcessSurgery();
                String date = dateOb.trim().substring(0, 10);

                PatientSurgeryDTO oldDto = new PatientSurgeryDTO(pId, pName,
                        date, sex, sName, opeDoc, anesth,
                        timeOfStart, timeOfEnd, processSur);
                request.setAttribute("INFO", oldDto);
                
                
                request.setAttribute("SID", sID);
                
                url = MainController.updateRecord;
            } else if (action.equalsIgnoreCase("UpdateSurgery")) {
                String surgeryId = request.getParameter("sId");
                //missing
                String patientId = request.getParameter("txtPatientId");
                String patientName = request.getParameter("txtPatientName");
                String dob = request.getParameter("txtDob");
                String gender = request.getParameter("rdGender");
                String surgeryName = request.getParameter("txtSurgeryName");
                String opeDoctor = request.getParameter("txtOpeDoctor");
                String anes = request.getParameter("txtAnes");

                String jspTimeStart = request.getParameter("txtTimeStart");
                String timeStart = jspTimeStart.replace("T", " ");//sau dos update voiws bien nay

                String jspTimeEnd = request.getParameter("txtTimeEnd");
                String timeEnd = jspTimeEnd.replace("T", " ");

                String processSurgery = request.getParameter("txtProcess");

                PatientValidate pValide = new PatientValidate();

                PatientSurgeryDTO dto = new PatientSurgeryDTO(patientId,
                        patientName, dob, gender, surgeryName, opeDoctor,
                        anes, jspTimeStart, jspTimeEnd, processSurgery);
                boolean check = true;

                if (!patientName.matches("[a-zA-Z\\s]{2,30}")) {
                    pValide.setNameError("invalid or too long");
                    check = false;
                }
                if (!checkDateTimeLocal(timeStart, timeEnd)) {
                    pValide.setTimeStart("time start have to below time end");
                    pValide.setTimeEnd("time start have to below time end");
                    check = false;
                }
                if (check == false) {
                    request.setAttribute("INFO", dto);
                    request.setAttribute("ERROR", pValide);
                    url = MainController.updateRecord;
                } else {
                    int n = dao.updatePatientFrom(patientId, patientName,
                            dob, gender);
                    int m = dao.updateSurgeryRecord(surgeryId, surgeryName,
                            opeDoctor, anes, timeStart, timeEnd, processSurgery);
                    if (n != 0 && m != 0) {
                        
                        url = MainController.employee;
                    } else {
                        url = MainController.error;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at updateSurgeryController" + e.getMessage());
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
