/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ldtvu
 */
public class SurgeryDTO {
    private String surgeryId, processSurgery,
            timeStart, timeEnd, createEmp,
            dateCreate, status, surgeryName,
            opeDoctor, anes;

    public SurgeryDTO() {
    }

    public SurgeryDTO(String surgeryId, String processSurgery, String timeStart, String timeEnd, String createEmp, String dateCreate, String status, String surgeryName, String opeDoctor, String anes) {
        this.surgeryId = surgeryId;
        this.processSurgery = processSurgery;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.createEmp = createEmp;
        this.dateCreate = dateCreate;
        this.status = status;
        this.surgeryName = surgeryName;
        this.opeDoctor = opeDoctor;
        this.anes = anes;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public String getSurgeryId() {
        return surgeryId;
    }

    public void setSurgeryId(String SurgeryId) {
        this.surgeryId = SurgeryId;
    }

    public String getProcessSurgery() {
        return processSurgery;
    }

    public void setProcessSurgery(String processSurgery) {
        this.processSurgery = processSurgery;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSurgeryName() {
        return surgeryName;
    }

    public void setSurgeryName(String surgeryName) {
        this.surgeryName = surgeryName;
    }

    public String getOpeDoctor() {
        return opeDoctor;
    }

    public void setOpeDoctor(String opeDoctor) {
        this.opeDoctor = opeDoctor;
    }

    public String getAnes() {
        return anes;
    }

    public void setAnes(String anes) {
        this.anes = anes;
    }

    
}
