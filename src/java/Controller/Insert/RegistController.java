/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Insert;

import DTO.EmployeeDTO;
import Validate.EmployeeValidation;
import Connect.RegistrationDAO;
import Controller.MainController;
import Validate.LoginValidate;
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
public class RegistController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String url = MainController.error;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String action = request.getParameter("action");
            String oldUsername = request.getParameter("username");
            request.setAttribute("IDEMP", oldUsername);
            if (action.equalsIgnoreCase("Register")) {
//                HttpSession session = request.getSession();
//                if (session != null) {
//                    session.invalidate();
//                }
                url = MainController.regist;

            } else if (action.equalsIgnoreCase("Enroll")) {
                String empId = request.getParameter("txtId");
                String empName = request.getParameter("txtName");
                String identityCard = request.getParameter("txtCard");
                String gender = request.getParameter("rdGender");
                String dob = request.getParameter("txtDob");
                String faculty = request.getParameter("facultyList");
                String pos = request.getParameter("posList");
                String addr = request.getParameter("txtAddress");
                String phone = request.getParameter("txtPhone");
                String email = request.getParameter("txtEmail");
                String empPass = request.getParameter("txtPass");
                String confPass = request.getParameter("txtConfPass");
                String status = request.getParameter("rdStatus");

                HttpSession session = request.getSession();
                session.setAttribute("USER", empName);
//check xem nếu create account thành công thì xem hello có thay đổi ko ?
//checked done
                RegistrationDAO dao = new RegistrationDAO();
                EmployeeDTO dto = new EmployeeDTO(empId, empName, identityCard,
                        gender, dob, faculty, pos, addr, phone, email, empPass, status);
 
                boolean check = false;
                EmployeeValidation validate = new EmployeeValidation();

                if (!empPass.equals(confPass)) {
                    validate.setEmpConfPass("Invalid Pass");
                    check = true;
                }
                if (!empId.matches("(P-ABCD-|E-ABCD-)[0-9]{1,}")) {
                    validate.setEmpIdError("invalid username (P-ABCD-123|E-ABCD-123)");
                    check = true;
                }
                if (dao.checkUser(empId, 1)) {//neeus dunsg ngia la trung id
                    validate.setEmpIdError("Duplicated id");
                    check = true;
                }
                request.setAttribute("INFO", dto);
                request.setAttribute("ERROR", validate);
                if (check) {
                    url = MainController.regist;
                } else {
                    int n = dao.insertEmployee(empId, empName, identityCard,
                            gender, dob, faculty, pos, addr, phone, email,
                            empPass, status);

                    if (n != 0) {
                        request.setAttribute("ERROR", null);//nếu ko bị sung đột với chính trang này khi vô bởi 1 Employee cũ
                        //vì setAttribute cùng là ERROR. nhưng khác validateDTO
                        url = MainController.insertPat;
                        request.setAttribute("INSERT", "you have to insert first.");
                    } else {
                        request.setAttribute("INSERTFAIL", "insert failed");
                        url = MainController.regist;
                    }
                }
            }
        } catch (Exception e) {
            log("Errror at RegistController" + e.getMessage());
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
