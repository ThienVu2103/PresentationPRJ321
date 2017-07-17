/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Login;

import DTO.PatientDTO;
import Connect.RegistrationDAO;
import Controller.MainController;
import Validate.LoginValidate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ldtvu
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public String url = MainController.error;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String name = new String();
            RegistrationDAO dao = new RegistrationDAO();
            LoginValidate validate = new LoginValidate();
            boolean check = false;

            if (!username.matches("(P-ABCD-|E-ABCD-)[0-9]{1,}")) {
                validate.setUsernameError("invalid username (P-ABCD-123|E-ABCD-123)");
                check = true;
            }

            int status = -1;
            request.setAttribute("STUSER", username);
            if (username.contains("P")) {
                status = 0;//0 là Patient
                if (!dao.checkUser(username, status)) {
                    validate.setUsernameError("Username Patient not found");
                    check = true;
//<editor-fold defaultstate="collapsed" desc="Info">
/*
co nghĩa khi mà checkUser đúng thì lưu lại để r kiểm tra pass
nếu pass sai thì thôgn báo và đồng thời giữ giá trị username ban đầu nhập vào
ĐẶC Biêt:
khi mà username nhập đúng form nhưng chưa có trong database thì lưu lại
ở vòng else. thông báo là chưa có username như vậy
                     */
//</editor-fold>
                }
              
                if (dao.checkPass(password, status)) {
                    name = dao.findNameByUser(username, 0);//vi sang trang ke welcome la ten user
                    session.setAttribute("USER", name);
                    PatientDTO dto = dao.findPatientByUsername(name);
                    request.setAttribute("PATIENTINFO", dto);
//<editor-fold defaultstate="collapsed" desc="Note">
//vì ko có 1 buttom rõ ràng để vào patient
//vì login xong phân quyền và vào luôn là update nếu là patient
//</editor-fold>
                    url = MainController.patient;
                } else {
                    validate.setPasswordError("Password Patient not found");
                    check = true;
                }

            } else if (username.contains("E")) {
                status = 1;
                if (!dao.checkUser(username, status)) {
                    validate.setUsernameError("Username Employeee not found");

                    request.setAttribute("IDEMP", username);
//<editor-fold defaultstate="collapsed" desc="Note">
//truyen di theo hyperlink
//</editor-fold>
                    request.setAttribute("REGIST", "Creat a new Employee");
                    check = true;
                }
                if (dao.checkPass(password, status)) {

                    name = dao.findNameByUser(username, 1);
                    session.setAttribute("USER", name);
                    session.setAttribute("ID", username);
//<editor-fold defaultstate="collapsed" desc="Note">
//co 2 cái cần truyền dưới dạng là 1 sesseion
//</editor-fold>
                 
                    url = MainController.employee;
                } else {
                    validate.setPasswordError("Password Employee not found");
                    check = true;
                }
            }
            request.setAttribute("ERROR", validate);
            if (check) {
                url = MainController.index;
            }
        } catch (Exception e) {
            log("Error at LoginController" + e.getMessage());
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
