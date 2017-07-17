package Connect;

import DTO.ObjectSearch;
import DTO.PatientDTO;
import DTO.SurgeryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author ldtvu
 */
public class RegistrationDAO {

    Connection c;
    PreparedStatement pst;
    ResultSet rs;

    public SurgeryDTO findSurgeryRecord(String surgeryId) {
        SurgeryDTO sRecord = null;
        try {
            c = MyConnection.openConnection();
            pst = c.prepareStatement("select OperatedDoctor, Anesthesiologist, "
                    + "TimeOfEnd, \n"
                    + "TimeOfStart, ProcessOfSurgery, Creator_Emp, \n"
                    + "DateOfCreate, Status, PatientID, SurgeryName \n"
                    + "from Surgery\n"
                    + "where Surgery_Record_ID = ?");
            pst.setString(1, surgeryId);
            rs = pst.executeQuery();
            while (rs.next()) {
                String operatedDoctor = rs.getString("OperatedDoctor");
                String Anesthesiologist = rs.getString("Anesthesiologist");
                String TimeOfEnd = rs.getString("TimeOfEnd");
                String TimeOfStart = rs.getString("TimeOfStart");
                String ProcessOfSurgery = rs.getString("ProcessOfSurgery");
                String Creator_Emp = rs.getString("Creator_Emp");
                String DateOfCreate = rs.getString("DateOfCreate");
                String Status = rs.getString("Status");
                String PatientID = rs.getString("PatientID");
                String SurgeryName = rs.getString("SurgeryName");
                sRecord = new SurgeryDTO(surgeryId, ProcessOfSurgery, 
                        TimeOfStart, TimeOfEnd, Creator_Emp, 
                        DateOfCreate, Status, SurgeryName, 
                        operatedDoctor, Anesthesiologist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return sRecord;
    }

    public int updateSurgeryRecord(String sId, String surgeryName,
            String opeDoctor, String anes, String timeStart, String timeEnd,
            String processSurgery) {
        int n = 0;
        try {
            c = MyConnection.openConnection();
            pst = c.prepareCall("update [hospital].[dbo].[Surgery] "
                    + "set OperatedDoctor = ?, Anesthesiologist = ?, "
                    + "TimeOfStart = ?, TimeOfEnd = ?, ProcessOfSurgery = ?, "
                    + "SurgeryName = ? "
                    + " where Surgery_Record_ID = ?");
            pst.setString(1, opeDoctor);
            pst.setString(2, anes);
            pst.setString(3, timeStart);
            pst.setString(4, timeEnd);
            pst.setString(5, processSurgery);
            pst.setString(6, surgeryName);
            pst.setString(7, sId);
            n = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n;
    }

    public int updatePatientFrom(String patientId,
            String patientName, String dob,
            String gender) {
        int n = 0;
        try {
            c = MyConnection.openConnection();
            pst = c.prepareCall("update [hospital].[dbo].[Patient] "
                    + "set PatientName = ?, DOB = ?, Gender = ? "
                    + "where PatientID = ? ");
            pst.setString(1, patientName);
            pst.setString(2, dob);
            pst.setString(3, gender);
            pst.setString(4, patientId);
            n = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n;
    }

    public String findDateOBById(String pId) {
        String dob = null;
        try {
            c = MyConnection.openConnection();
            pst = c.prepareCall("select DOB "
                    + "from [hospital].[dbo].[Patient] "
                    + "where PatientID = ? ");
            pst.setString(1, pId);
            rs = pst.executeQuery();
            if (rs.next()) {
                dob = rs.getString("DOB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dob;
    }

    public String findGenderPatient(String pId) {
        String sex = new String();
        try {
            c = MyConnection.openConnection();
            pst = c.prepareCall("select Gender "
                    + "from [hospital].[dbo].[Patient] "
                    + "where PatientID = ? ");
            pst.setString(1, pId);
            rs = pst.executeQuery();
            if (rs.next()) {
                sex = rs.getString("Gender");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return sex;
    }

    public boolean checkSurgeryId(String surgeryId) {
        boolean check = false;
        try {
            c = MyConnection.openConnection();
            pst = c.prepareCall("select Surgery_Record_ID "
                    + "from [hospital].[dbo].[Surgery] "
                    + "where Surgery_Record_ID = ? ");
            pst.setString(1, surgeryId);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }

    public int insertSurgery(String surgeryId, String processSurgery,
            String timeStart, String timeEnd, String createEmpt, String dateCreate,
            String status, String patientId, String surgeryName,
            String opeDoctor, String anes) {
        int n = 0;
        try {
            c = MyConnection.openConnection();
            pst = c.prepareCall("insert into [hospital].[dbo].[Surgery] "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, surgeryId);
            pst.setString(2, processSurgery);
            pst.setString(3, timeStart);
            pst.setString(4, timeEnd);
            pst.setString(5, createEmpt);
            pst.setString(6, dateCreate);
            pst.setString(7, status);
            pst.setString(8, patientId);
            pst.setString(9, surgeryName);
            pst.setString(10, opeDoctor);
            pst.setString(11, anes);

            n = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n;
    }

    public ArrayList<ObjectSearch> findPatientName(String name, String nameEmp) {
        ArrayList<ObjectSearch> listPatient = new ArrayList<ObjectSearch>();
        try {
            c = MyConnection.openConnection();
            pst = c.prepareStatement("select  p.PatientID, p.PatientName, s.SurgeryName, s.Surgery_Record_ID \n"
                    + "from Patient p, Surgery s, Employee e\n"
                    + "where p.PatientID = s.PatientID\n"
                    + "and e.EmployeeID = s.Creator_Emp \n"
                    + "and p.PatientName like ? and e.EmployeeName = ?");
            pst.setString(1, "%" + name + "%");
            pst.setString(2, nameEmp);
            rs = pst.executeQuery();
            while (rs.next()) {
                String patientId = rs.getString("PatientID");
                String patientName = rs.getString("PatientName");
                String surgeryName = rs.getString("SurgeryName");
                String surgeryId = rs.getString("Surgery_Record_ID");
                ObjectSearch ob = new ObjectSearch(patientId, patientName, surgeryName, surgeryId);
                listPatient.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listPatient;
    }

    public int insertPatient(String id, String name, String gender, String timeDob, String addr,
            String email, String phone, String pass, String status) {
        int n = 0;
        try {
            c = MyConnection.openConnection();
            pst = c.prepareStatement("insert into [hospital].[dbo].[Patient] "
                    + "values(?,?,?,?,?,?,?,?, ?)");
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, gender);
            pst.setString(4, timeDob);
            pst.setString(5, addr);
            pst.setString(6, email);
            pst.setString(7, phone);
            pst.setString(8, pass);
            pst.setString(9, status);

            n = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n;
    }

    public int insertEmployee(String empId, String empName, String identityCard,
            String gender, String dob, String faculty, String pos, String addr, String phone, String email, String pass, String status) {
        int n = 0;
        try {
            c = MyConnection.openConnection();
            pst = c.prepareStatement("insert into [hospital].[dbo].[Employee] "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, empId);
            pst.setString(2, empName);
            pst.setString(3, identityCard);
            pst.setString(4, gender);
            pst.setString(5, dob);
            pst.setString(6, faculty);
            pst.setString(7, pos);
            pst.setString(8, addr);
            pst.setString(9, phone);
            pst.setString(10, email);
            pst.setString(11, pass);
            pst.setString(12, status);

            n = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return n;
    }

    public int updatePatient(String id, String name, String gender, String dob, String addr,
            String email, String phone) {
        int result = 0;
        try {
            c = MyConnection.openConnection();
            pst = c.prepareStatement("update Patient\n"
                    + "	 set PatientName = ?, Gender = ?, DOB = ?, PatientAddr = ?, \n"
                    + "	 Email = ?, Phone = ?\n"
                    + "	 where PatientID = ?");
            pst.setString(1, name);
            pst.setString(2, gender);
            pst.setString(3, dob);
            pst.setString(4, addr);
            pst.setString(5, email);
            pst.setString(6, phone);
            pst.setString(7, id);
            result = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public PatientDTO findPatientByUsername(String Pname) {
        PatientDTO dto = null;
        try {
            c = MyConnection.openConnection();
            pst = c.prepareCall("SELECT [PatientID], \n"
                    + "                    [PatientName], \n"
                    + "                    [Gender], \n"
                    + "                    [DOB], \n"
                    + "                    [PatientAddr], \n"
                    + "                    [Email], \n"
                    + "                    [Phone] \n"
                    + "                   FROM [hospital].[dbo].[Patient]\n"
                    + "                   where [PatientName] = ?");
            pst.setString(1, Pname);
            rs = pst.executeQuery();
            if (rs.next()) {
                String id = rs.getString("PatientID");
                String name = rs.getString("PatientName");
                String gender = rs.getString("Gender");
                String dob = rs.getString("DOB");
                String add = rs.getString("PatientAddr");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                String date = dob.replace(" ", "T");
                dto = new PatientDTO(id, name, gender, date, add, email, phone);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }

    public String findNameByUser(String username, int status) {
        String name = new String();
        try {
            c = MyConnection.openConnection();
            if (status == 1) {
                pst = c.prepareCall("SELECT [EmployeeName] \n"
                        + "  FROM [hospital].[dbo].[Employee] \n"
                        + "  where EmployeeID = ?");
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs.next()) {
                    name = rs.getString("EmployeeName");
                }
            } else {
                pst = c.prepareCall("SELECT [PatientName]\n"
                        + "  FROM [hospital].[dbo].[Patient]\n"
                        + "  where PatientID = ?");
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs.next()) {
                    name = rs.getString("PatientName");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return name;
    }

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkUser(String username, int status) {
        boolean check = false;
        try {
            c = MyConnection.openConnection();
            if (status == 0) {                                  //patient pass
                pst = c.prepareStatement("select PatientID "
                        + "from Patient "
                        + "where PatientID = ?");
            } else {                                            //employee pass
                pst = c.prepareStatement("select EmployeeID "
                        + "from Employee "
                        + "where EmployeeID = ? ");
            }
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkPass(String password, int status) {
        boolean check = false;
        try {
            c = MyConnection.openConnection();
            if (status == 0) {                                  //patient pass
                pst = c.prepareStatement("select PatientPass "
                        + "from Patient "
                        + "where PatientPass = ?");
            } else {                                            //employee pass
                pst = c.prepareStatement("select EmpPass "
                        + "from Employee "
                        + "where EmpPass = ? ");
            }
            pst.setString(1, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkLogin(String username, String password, int status) {
        boolean login = false;
        try {
            c = MyConnection.openConnection();
            if (status == 0) {                          //patient

                pst = c.prepareStatement("select PatientID, PatientPass  \n"
                        + "from Patient \n"
                        + "where PatientID = ? and PatientPass = ?");

            } else {                                    //Employee
                pst = c.prepareStatement("select EmployeeID, EmpPass  "
                        + "from Employee "
                        + "where EmployeeID = ? and EmpPass = ?");
            }
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                login = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return login;
    }

}
