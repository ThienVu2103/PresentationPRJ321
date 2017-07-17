/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validate;

/**
 *
 * @author ldtvu
 */
public class SurgeryValidate {
    private String surgeryIdError, processSurgeryError,
            timeStartError, timeEndError, 
            dateCreateError, statusError, surgeryNameError,
            opeDoctorError, anesError;

    public SurgeryValidate() {
    }
    
    
    public SurgeryValidate(String surgeryIdError, String processSurgeryError, String timeStartError, String timeEndError, String dateCreateError, String statusError, String surgeryNameError, String opeDoctorError, String anesError) {
        this.surgeryIdError = surgeryIdError;
        this.processSurgeryError = processSurgeryError;
        this.timeStartError = timeStartError;
        this.timeEndError = timeEndError;
        this.dateCreateError = dateCreateError;
        this.statusError = statusError;
        this.surgeryNameError = surgeryNameError;
        this.opeDoctorError = opeDoctorError;
        this.anesError = anesError;
    }

    public String getSurgeryIdError() {
        return surgeryIdError;
    }

    public void setSurgeryIdError(String SurgeryIdError) {
        this.surgeryIdError = SurgeryIdError;
    }

    public String getProcessSurgeryError() {
        return processSurgeryError;
    }

    public void setProcessSurgeryError(String processSurgeryError) {
        this.processSurgeryError = processSurgeryError;
    }

    public String getTimeStartError() {
        return timeStartError;
    }

    public void setTimeStartError(String timeStartError) {
        this.timeStartError = timeStartError;
    }

    public String getTimeEndError() {
        return timeEndError;
    }

    public void setTimeEndError(String timeEndError) {
        this.timeEndError = timeEndError;
    }

    public String getDateCreateError() {
        return dateCreateError;
    }

    public void setDateCreateError(String dateCreateError) {
        this.dateCreateError = dateCreateError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getSurgeryNameError() {
        return surgeryNameError;
    }

    public void setSurgeryNameError(String surgeryNameError) {
        this.surgeryNameError = surgeryNameError;
    }

    public String getOpeDoctorError() {
        return opeDoctorError;
    }

    public void setOpeDoctorError(String opeDoctorError) {
        this.opeDoctorError = opeDoctorError;
    }

    public String getAnesError() {
        return anesError;
    }

    public void setAnesError(String anesError) {
        this.anesError = anesError;
    }
    
}
