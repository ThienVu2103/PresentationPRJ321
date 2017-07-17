package DTO;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author ldtvu
 */
public class PatientDTO {
    private String id, name;
    private boolean gender;
    private String sex;
    private Timestamp dob;
    private String date;
    private String addr, email, phone;
    private String pass;
    private String status;

    public PatientDTO(String id, String name, String sex, String date, String addr, String email, String phone, String pass, String status) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.date = date;
        this.addr = addr;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
        this.status = status;
    }
    
    public PatientDTO(String id, String name, String sex, Timestamp dob, 
            String addr, String email, String phone, String pass, String status) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.addr = addr;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
        this.status = status;
    }
    
    
    
    public PatientDTO() {
    }

    public PatientDTO(String id, String name, String sex, String date, String addr, String email, String phone) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.date = date;
        this.addr = addr;
        this.email = email;
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PatientDTO(String id, String name, String sex, Timestamp dob, String addr, String email, String phone) {
        this.id = id;//1
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.addr = addr;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
}
